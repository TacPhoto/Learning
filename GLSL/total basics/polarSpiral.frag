/*works with shadertoy*/

void main()
{
    vec2 uv = gl_FragCoord.xy / iResolution.xy;
    uv.x *= iResolution.x / iResolution.y;
    uv -= .5;
    vec3 col = vec3(.0);

    vec2 st = vec2(atan(uv.x, uv.y), length(uv));

    uv = vec2(st.x / 6.2831 + .5 + iTime * .05 + st.y, st.y);

    float x = uv.x * 5.;
    float m = min(fract(x), fract(1. - x));
    float c = smoothstep(0., .1, m * .8 + .1 - uv.y); //zigzag


    col = vec3(c);
    //col = vec3(st.x / 6.2831 + .5);
    gl_FragColor = vec4(col, 1.);
}