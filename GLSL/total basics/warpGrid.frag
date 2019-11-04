/*works with shadertoy*/

void main()
{
    vec2 uv = gl_FragCoord.xy / iResolution.xy;
    uv.x *= iResolution.x / iResolution.y;
	//uv -= .5;
    vec3 col = vec3(0.0);

    col += sin(uv.x * cos(iTime / 30.) * 60.);
    col += sin(uv.y * cos(iTime / 15.) * 10.);

    col += cos(uv.y * cos(iTime / 30.) * 60.);
    col += sin(uv.x * cos(iTime / 15.) * 10.);

    col *= sin(iTime / 10.) * .5;

    gl_FragColor = vec4(col,1.0);
}