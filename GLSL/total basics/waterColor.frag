/*works with shadertoy*/

void main()
{
    vec2 uv = 6. * gl_FragCoord.xy / iResolution.xy;
    uv.x *= iResolution.x / iResolution.y;
	//uv -= .5;
    for(float i = 1.; i < 8.; i++)
    {
        float m = i * .3;
        uv += vec2(.7 / i * sin(i *uv.y + iTime + m) + .8, .4 / i * sin(uv.x + iTime + m) + 1.6);
    }

    vec3 col = vec3(0.5 * sin(uv.x) + .5, .5 * sin(uv.y) + .5, sin(uv.x + uv.y));

    float grey = .21 * col.r + .71 * col.g + .07 * col.b; 
    grey = mix(.2, 1., grey);

    col = grey * vec3(0.349, 0.847, 0.909);

    gl_FragColor = vec4(col,1.0);
}