/*works with shadertoy*/

float plot(vec2 uv, float plotted){
  return  smoothstep( plotted-0.01, plotted, uv.y) -
          smoothstep( plotted, plotted+0.01, uv.y);
}


void main()
{
    vec2 uv = gl_FragCoord.xy / iResolution.xy;
    uv.x *= iResolution.x / iResolution.y;
    vec3 col = vec3(.0);

    float y = uv.x;

    float plotted = plot(uv, y);
    col = (1.0-plotted) * col + plotted * vec3(0.0,1.0,0.0);

	gl_FragColor = vec4(col,1.0);
}