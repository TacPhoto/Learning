/*works with shadertoy*/
#define PI 3.14159265359
#define TWO_PI 6.28318530718

void main()
{
    vec2 uv = gl_FragCoord.xy / iResolution.xy;
    uv.x *= iResolution.x / iResolution.y;
    uv -= .5;

    vec3 col = vec3(.0);

    float d = .0;
    float sides = 6.;

    //angle, radius from current pixel
    float a = atan(uv.x, uv.y) + PI;
    float r = TWO_PI / sides;

    //shaping
    d = cos(floor(.5 + a / r) * r - a) * length(uv) + .2;

    col = vec3(1. - smoothstep(.4, .41, d));
    gl_FragColor = vec4(col, 1.);
}