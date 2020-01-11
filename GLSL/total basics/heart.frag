/*works with shadertoy*/
#define S(a, b, t) smoothstep(a, b, t)
#define HEART_COLOR vec3(1., .05, .05)


float smoothMax(float a, float b, float k)
{
    float h = clamp((b-a) / k + .5, 0., 1.);
    return mix(a, b, h) + h * (1. - h)* k * .5;
}

float heart(vec2 uv, float blur)
{
    float radius = .25;
    blur *= radius;

    uv.x *= .7;
    uv.y -= smoothMax(sqrt(abs(uv.x)) / 2., blur, .1);
    uv.y += .1 + blur * .5;

    float d = length(uv);

    return S(radius + blur, radius - blur - .01, d);
}



void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    vec2 uv = (fragCoord-iResolution.xy * .5) / iResolution.y;
    
    vec2 m = iMouse.xy/iResolution.xy;
    
    float c = heart(uv, m.y);

    vec3 col = vec3(c * HEART_COLOR);

    fragColor = vec4(col,1.0);
}