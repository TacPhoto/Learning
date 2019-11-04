/*works with shadertoy*/

void main()
{
    vec2 uv = gl_FragCoord.xy / iResolution.xy;
    uv.x *= iResolution.x / iResolution.y;
    float col = .0;

    col += sin(uv.x * 6. + sin(iTime + uv.y * 90. + cos(uv.x * 30. + iTime * 2.))) * .5;

    gl_FragColor = vec4(col + uv.x, col + uv.x, col, 1.0);
}