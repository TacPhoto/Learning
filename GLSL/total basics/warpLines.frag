/*works with shadertoy*/

void main()
{
    vec2 uv = gl_FragCoord.xy / iResolution.xy;
    uv.x *= iResolution.x / iResolution.y;
    float col;

    float m = uv.x * 50.;
    col += sin( m + cos(iTime + uv.y * 10. + sin(m + iTime)) );
    col *= 2.;
    col += cos( uv.x * 20. + sin(iTime + uv.y * 10. + cos(m + iTime)) );
    col += cos( uv.x * 30. + sin(iTime + uv.y * 10. + cos(m + iTime)) );
    col += cos( uv.x * 10. + sin(iTime + uv.y * 10. + cos(m + iTime)) );


    gl_FragColor = vec4(col + uv.y, col + uv.x, col, 1.0);
}