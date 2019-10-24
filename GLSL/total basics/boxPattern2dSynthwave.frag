/*works with shadertoy*/
/*boxPattern3d.frag variant which is more uniform accorss whole screen and uses no 3d at all*/

//#define STRIPES
//#define MIRROR

precision mediump float;
#define S(a, b, t) smoothstep(a, b, t)

float Noise(float t)
{
    return fract(sin(t * 3500.) * 6500.);
}


vec2 Pattern(vec2 uv, float t)
{
    t *= 40.;

    #ifdef MIRROR
    uv = abs(uv);
    #endif
    #ifdef STRIPES
    uv.y = abs(uv.x);
    #endif

    vec2 a = vec2(4., 1.); //aspect ratio
    vec2 st = uv * a;

    vec2 id = floor(st); //column id
    
    float n = fract(sin(id.x * 76.34) * 728.34); //noise

    st.y += t * .22; //offset in time
    st.y += n; //offset y depending on id
    id = floor(st);
    st = fract(st) - .5;    
    //st = fract(st) - .1; //darker version

    t = t + fract(sin(id.x * 76.33 + id.y * 100.24) * 777.33) * 6.283; //timing randomization
    //t = t + fract(sin(id.y * 76.33 + id.y * 100.24) * 777.33) * 6.283; //make waves

    float y = -sin(t) * .4; //y offset
    
    vec2 p1 = vec2((st.x * uv.x), y + 1.3);
    vec2 o1 = (uv - p1) / a;
     
   return vec2((1. * o1 * 4.));
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

    boxPattern = Pattern(uv * 5.,t) * .5;
    boxPattern += Pattern(uv * 7.,t) * .5;
    
    vec3 col = vec3(0.);

    col = (uv.y -boxPattern.y * .5) * vec3(.2, .1, .5);
    fragColor = vec4(col, 1.);
}