/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Y = 1 FOR X(0; 1) AND 0 FOR X(INF; 0) AND X(1, INF)                                                              //
//                                                                                                                 //
//6 DIFFERENT IMPLEMENTATIONS FOR PERFORMANCE MEASURING                                                            //
//FOR REALLY ACCURRATE RESULTS, IT SHOULD BE TESTED ON GPU AS WELL                                                 //
//DO NOT ONLY MEASURE TIME BUT ALSO CHECK NUMBER OF INSTRUCTIONS USED, NUMBER OF CYCLES AND CORES/REGISTERS IN USE //
//                                                                                                                 //
//METHOD2 NEEDS THE LOWEST NUMBER OF CYCLES TO EXECUTE (XBOX SHADER COMPILER CONFIRMED)                            //
//HOWEVER METHOD6 USES MORE ACTIVE WAVEFRONTS AND LESS VRegs AND SRegs                                             //
//                                                                                                                 //
//CREDITS TO Tech Art Aid METHODS 1 AND 6 + SOME HELP                                                              //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

#include "pch.h"
#include <iostream>
#include <chrono>
#include <emmintrin.h> //SSE
#include <cmath>

using namespace std::chrono;

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//PLEASE USE THESE MACROS TO SWITCH BETWEEN DIFFERENT FUNCTION IMPLEMENTATIONS
//#pragma optimize("", off) //YOU SHOULD NOT ACTUALLY USE IT. I RECOMMEND MEASURING TIMES IN RELEASE MODE
//#define CMATH     //USE CMARH FOR min(), max(), ceil(), floor(), (DOES NOT USE modf() FOR frac())
//#define SSE       //USE SSE FOR floor(), min() AND max(). CEIL WILL BASE ON THE floor(). IF CMATH IS USED, SSE OVERRIDES floor() and ceil()
#define BENCHMARK //MEASURE TIME
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


#ifdef BENCHMARK
class Timer
{
public:
	Timer()
	{
		m_start = std::chrono::high_resolution_clock::now();
	}

	~Timer()
	{
		auto end = std::chrono::high_resolution_clock::now();
		duration<double> elapsed = end - m_start;
		std::cout << elapsed.count() << " seconds\n\n";
	}

private:
	high_resolution_clock::time_point m_start;
};
#endif //BENCHMARK


inline float simple_frac(float x);
inline float simple_saturate(float x);
inline float simple_sign(float x);
inline float simple_max(float a, float b);
inline float simple_min(float a, float b);
inline float simple_floor(float x);
inline float simple_ceil(float x);
#ifdef SSE
inline static float floor_sse2(float x);
float min_sse(float a, float b);
float max_sse(float a, float b);
#endif //SSE


float simple_sign(float x)
{
	return (x >= 0.0f) ? -1.0f : 1.0f; //It frankly needs less cycles than https://developer.download.nvidia.com/cg/sign.html for a normal float. Tested on xbox shader compiler
}


#ifdef SSE
float min_sse(float a, float b) //by https://stackoverflow.com/users/827332/spat
{
	//Branchless SSE min.
	_mm_store_ss(&a, _mm_min_ss(_mm_set_ss(a), _mm_set_ss(b)));
	return a;
}


float max_sse(float a, float b) //by https://stackoverflow.com/users/827332/spat
{
	//Branchless SSE max.
	_mm_store_ss(&a, _mm_max_ss(_mm_set_ss(a), _mm_set_ss(b)));
	return a;
}


static float floor_sse2(float x) //taken from https://gist.github.com/mmozeiko/c70f495da7b3f91ad785 , thanks mmozeuiko for your implementation! Very interesting however it did not execute faster for me
{
	__m128 f = _mm_set_ss(x);
	__m128 one = _mm_set_ss(1.0f);

	__m128 t = _mm_cvtepi32_ps(_mm_cvttps_epi32(f));
	__m128 r = _mm_sub_ps(t, _mm_and_ps(_mm_cmplt_ps(f, t), one));

	return _mm_cvtss_f32(r);
}
#endif //SSE


float simple_max(float a, float b)
{
#if defined(SSE) && !defined(CMATH)
	return max_sse(a, b);
#endif
#ifdef CMATH
	return fmax(a, b);
#endif
	return a > b ? a : b;
}


float simple_min(float a, float b)
{
#if defined(SSE) && !defined(CMATH)
	return min_sse(a, b);
#endif
#ifdef CMATH
	return fmin(a, b);
#endif
	return a < b ? a : b;
}


float simple_floor(float x)
{
#ifdef SSE
	return floor_sse2(x);
#endif //SSE
#if defined(CMATH) && !defined(SSE)
	return floor(x);
#endif
	return (float)(int)x;
}


float simple_ceil(float x)
{
#if defined(CMATH) && !defined(SSE)
	return ceil(x);
#endif
	return simple_floor(x) + 1.f;
}


float simple_frac(float x)
{
	return x - simple_floor(x);
}


float simple_saturate(float x)
{
	return simple_max(0.0f, simple_min(1.0f, x));
}


int main()
{
	const float reps = 200.0f; //the number here is smaller than actual by 100 000 times

	//METHOD 1
	{
		float accum = 0.0f;
		std::cout << "ceil(frac(saturate(x))) :\n";
#ifdef BENCHMARK
		Timer timer;
#endif //BENCHMARK

		for (float x = 0.0f; x < reps; x += 0.00001f)
		{
			float a = simple_saturate(x);
			a = simple_frac(a);
			a = simple_ceil(a);
			accum = sin(accum + a);
		}
		std::cout << "Accum: " << accum << "\n";
	}

	//METHOD 2
	{
		float accum = 0.0f;
		std::cout << "sign(-x * (x - 1.0)) / 2.0 + 1.0 :\n";
#ifdef BENCHMARK
		Timer timer;
#endif //BENCHMARK

		for (float x = 0.0f; x < reps; x += 0.00001f)
		{
			float a = -x * (x - 1.0f);
			a = simple_sign(a);
			a = a * .5f + 1.0f; //mad
			accum = sin(accum + a);
		}
		std::cout << "Accum: " << accum << "\n";
	}

	//METHOD 3
	{
		float accum = 0.0f;
		std::cout << "ceil(saturate(-x * (x - 1.0))) :\n";
#ifdef BENCHMARK
		Timer timer;
#endif //BENCHMARK

		for (float x = 0.0f; x < reps; x += 0.00001f)
		{
			float a = -x * (x - 1.0f);
			a = simple_saturate(a);
			a = simple_ceil(a);
			accum = sin(accum + a);
		}
		std::cout << "Accum: " << accum << "\n";
	}

	//METHOD 4
	{
		float accum = 0.0f;
		std::cout << "sign(x) - sign(x - 1.0) :\n";
#ifdef BENCHMARK
		Timer timer;
#endif //BENCHMARK

		for (float x = 0.0f; x < reps; x += 0.00001f)
		{
			float a = simple_sign(x);
			a -= simple_sign(x - 1.f);
			accum = sin(accum + a);
		}
		std::cout << "Accum: " << accum << "\n";
	}

	//METHOD 5
	{
		float accum = 0.0f;
		std::cout << "( sign(x) * (-sign(x - 1.0)) ) / 2.0 + 0.5 :\n";
#ifdef BENCHMARK
		Timer timer;
#endif //BENCHMARK

		for (float x = 0.0f; x < reps; x += 0.00001f)
		{
			float a = simple_sign(x);
			a *= -simple_sign(x - 1.f);
			a = a * .5f + .5f; //mad
			accum = sin(accum + a);
		}
		std::cout << "Accum: " << accum << "\n";
	}

	//METHOD 6
	{
		float accum = 0.0f;
		std::cout << "(float)( a == saturate(a) ) :\n";
#ifdef BENCHMARK
		Timer timer;
#endif //BENCHMARK

		for (float x = 0.0f; x < reps; x += 0.00001f)
		{
			float a = float(x == simple_saturate(x));
			accum = sin(accum + a);

		}
		std::cout << "Accum: " << accum << "\n";
	}

	std::cout << "Repetitions per test: " << reps * 100000.0f << std::endl;

#if defined(SSE) && !defined(CMATH)
	std::cout << "//////////////////////////////////////////////////////////////////////////////////\n";
	std::cout << "SSE implementation was used for floor(), max() and min(). ceil() inherited from floor\n";
#endif //SSE
#if defined(CMATH) && !defined(SSE)
	std::cout << "//////////////////////////////////////////////////////////////////////////////////\n";
	std::cout << "cmath was used for min(), max(), ceil(), floor(), (did not use modf for frac)\n";
#endif //CMATH
#if defined(CMATH) && defined(SSE)
	std::cout << "//////////////////////////////////////////////////////////////////////////////////\n";
	std::cout << "cmath was used for min(), max() (did not use modf for frac)\n";
	std::cout << "SSE implementation was used for floor(). ceil() inherits from floor\n";
#endif //CMATH and SSE

}
