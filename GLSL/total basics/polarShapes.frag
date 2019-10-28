/*works with shadertoy*/

void main()
{
    vec2 uv = gl_FragCoord.xy / iResolution.xy;
    uv.x *= iResolution.x / iResolution.y;
    uv -= .5;

    vec3 col = vec3(.0);

    float r = length(uv) * 2.;
    float a = atan(uv.y, uv.x);
    float f = cos(a * 7. + iTime * 2.);

    col = vec3( 1. - smoothstep(f, f + 0.02, r) );
    col -= vec3( 1. - smoothstep(f - 0.4, f, r) );

    uv.x += .2;
    col *= uv.x + uv.y;

    gl_FragColor = vec4(col, 1.);
}