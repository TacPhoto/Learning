/*works with shadertoy*/
#ifdef GL_ES
precision mediump float;
#endif

float circleShape(vec2 uv, float radius)
    {
    return step(radius, length(uv - vec2(0.5)));
    }

void main()
{
    vec2 uv = gl_FragCoord.xy / iResolution.xy;
    uv.x *= iResolution.x / iResolution.y; //correct aspect ratio
    
    vec3 color = vec3(0.0);

    float circle = circleShape(uv, 0.2);

    color = vec3(circle);

    gl_FragColor = vec4(color, 1.0);

}