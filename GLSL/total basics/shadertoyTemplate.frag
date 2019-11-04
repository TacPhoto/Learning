/*works with shadertoy*/

void main()
{
    vec2 uv = gl_FragCoord.xy / iResolution.xy;
    uv.x *= iResolution.x / iResolution.y;
    uv -= .5;

    vec3 col = vec3(.0);

    gl_FragColor = vec4(col, 1.0);
}