/*works with shadertoy*/
/*based on streetLights.frag rain effect*/

precision mediump float;
#define S(a, b, t) smoothstep(a, b, t)

float Noise(float t)
{
    return fract(sin(t * 3500.) * 6500.);
}


vec4 Noise4(float t)
{
    return fract(sin(t *vec4(123., 1200., 3500., 8374.) * vec4(6500., 350., 8700., 1760. )));
}


struct ray
{
    vec3 o, d; //origin, direction
};


ray GetRay(vec2 uv, vec3 camPos, vec3 lookat, float zoom)
{
    ray a;
    a.o = camPos;
    
    vec3 f = normalize(lookat - camPos); //forward vector
    vec3 r = cross(vec3(0., 1., 0.), f); //right vector
    vec3 u = cross(f, r); //camera Up vector
    vec3 c = a.o + f * zoom; //center of the screen
    vec3 i = c + (uv.x * r) + (uv.y * u); //intersection
    
    a.d = normalize(i - a.o);
    
    return a;
}


vec3 ClosestPoint(ray r, vec3 p)
{
    return r.o + max(0., dot(p-r.o, r.d))*r.d;
}


float DistRay(ray r, vec3 p)
{
	return length(p - ClosestPoint(r, p));
}


vec2 Rain(vec2 uv, float t)
{
    t *= 40.;
    
    vec2 a = vec2(3., 1.); //aspect ratio
    vec2 st = uv * a;
    
    vec2 id = floor(st); //column id
    
    float n = fract(sin(id.x * 76.34) * 718.34); //noise
    
    st.y += t * .22;
    st.y += n; //offset y depending on id
    id = floor(st);
    st = fract(st) - .5;    

    t += fract(sin(id.x * 76.33 + id.y * 100.24) * 777.33) * 6.283; //timing randomization
    float y = -sin(t + sin(t + sin(t) * .5)) * .43; //y offset

    vec2 p1 = vec2(id.x, y);
    vec2 o1 = (st - p1) / a + .05; //offset1
    float d = length(o1);
    
    float m1 = S(.0, n, d);
        
    return vec2((m1 * o1 * 30.));
}


void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    vec2 uv = fragCoord/iResolution.xy; // Normalized pixel coordinates (0 to 1)
	uv -= .5; //set origin to middle of the screen
    uv.x *= iResolution.x / iResolution.y; //correct aspect ratio

    vec2 m = iMouse.xy/iResolution.xy; //normalized mouse pos
    float t = iTime * .05 + m.x;

	vec3 camPos = vec3(.5, .2, 0.);
    vec3 lookat = vec3(.5, .2, 1.);
    
    vec2 boxPattern = vec2(0.);

    boxPattern = Rain(uv * 5.,t) * .5;
    boxPattern += Rain(uv * 7.,t) * .5;

    ray r = GetRay(uv - boxPattern * .5, camPos, lookat, 2.);
    
    vec3 col = vec3(0.);

    col += (r.d.y + .25) * vec3(.2, .1, .5); //gradient overlay
	
    fragColor = vec4(col, 1.);
}