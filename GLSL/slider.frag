/*works with shadertoy*/

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
    t *= .8;
    
    vec2 a = vec2(3., 1.); //aspect ratio
    vec2 st = uv * a;
    
    vec2 id = floor(st); //column id
    
    float n = fract(sin(id.x * 760.34) * 1278.34); //noise
    
    st.y += .22 + n + t;

    id = floor(st);
    st = fract(st) - .5 + uv.x;    
    
    float m1 = S(.07, .0, 1.);
        
    float m2 = S(.3 * (.5), .0, st.x);
    
    if(st.x > .3 || st.y>.47) m1 = 1.;
    

    return vec2(m1 * 2.);
}


void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    float t = iTime;
    vec2 uv = fragCoord/iResolution.xy; // Normalized pixel coordinates (0 to 1)
	uv -= .5; //set origin to middle of the screen
    uv.x *= iResolution.x / iResolution.y; //correct aspect ratio

    vec3 col = vec3(.3 , .3, .3);

    vec2 overlay = vec2(0.);
    
    overlay = Rain(vec2(uv.y * 5., uv.x * 5.),-t) * .5;

    col = vec3(overlay, 0.);

    fragColor = vec4(col, 1.);
}