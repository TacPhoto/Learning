#ifdef GL_ES
precision mediump float;
#endif
#define shadertoy
#ifdef shadertoy
    #define u_resolution iResolution.xy
#endif
#ifndef shadertoy
    uniform vec2 u_resolution;
#endif

float rectangle(vec2 position, vec2 scale)
{
    scale = vec2(0.5) - scale * 0.5;
    vec2 shaper = vec2(step(scale.x, position.x), step(scale.y, position.y));
    shaper *= vec2(step(scale.x, 1.0 - position.x), step(scale.y, 1.0 - position.y));
    return shaper.x * shaper.y;
}

void main()
{
    vec2 position = gl_FragCoord.xy / u_resolution;

    float rectangle = rectangle(position, vec2(vec2(0.9, 0.3)));
    vec3 color = vec3(rectangle);

    gl_FragColor = vec4(color, 1.0);
}