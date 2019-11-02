/*works with shadertoy*/

float plot(vec2 uv, float plotted){
  return  smoothstep( plotted-0.01, plotted, uv.y) -
          smoothstep( plotted, plotted+0.01, uv.y);
}


float almostIdentity( float x, float m, float n )
{
    if( x > m ) return x;
    float a = 2.0 * n - m;
    float b = 2.0 * m - 3.0 * n;
    float t = x / m;
    return (a * t + b) * t * t + n;
}


void main()
{
    vec2 uv = gl_FragCoord.xy / iResolution.xy;
    uv.x *= iResolution.x / iResolution.y;
    vec3 col = vec3(.0);

    float y = almostIdentity(uv.x, .3, .2);

    float plotted = plot(uv, y);
    col = (1.0-plotted) * col + plotted * vec3(0.0,1.0,0.0);

	gl_FragColor = vec4(col,1.0);
}