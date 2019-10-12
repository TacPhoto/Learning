/*works with shadertoy*/
precision mediump float;
#define S(a, b, t) smoothstep(a, b, t)

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


float Bokeh(ray r, vec3 p, float size, float blur)
{
    float d = DistRay(r, p);
    
    float c = S(size, size * (1. - blur), d);
    c *= mix(.6, 1., S(size * .8, size, d));
    return c;
}


void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    float t = iTime;
    vec2 uv = fragCoord/iResolution.xy; // Normalized pixel coordinates (0 to 1)
	uv -= .5; //set origin to middle of the screen
    uv.x *= iResolution.x / iResolution.y; //correct aspect ratio

	vec3 camPos = vec3(0., .2, 0.);
    vec3 lookat = vec3(0., .2, 1.);
    
    ray r = GetRay(uv, camPos, lookat, 2.);
    
    vec3 p = vec3(0., 0., 5.); //test point
    
    
    float c = Bokeh(r, p, .3, .1);
    
    vec3 col = vec3(1., .7, .3) * c;
    
    fragColor = vec4(col, 1.);
}