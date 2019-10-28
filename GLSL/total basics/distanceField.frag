/*works with shadertoy*/

void main()
{
    vec2 uv = gl_FragCoord.xy / iResolution.xy;
    uv.x *= iResolution.x / iResolution.y;
    uv -= .5;

    vec3 col = vec3(.0);

    vec2 source = abs(uv) - sin(iTime / 20.);
    float d = length(source); 

    col = vec3(fract(d * 10.));

    gl_FragColor = vec4(col, 1.);
}