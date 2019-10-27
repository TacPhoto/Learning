float noise(float n, float seed)
    {
    return fract(sin(n * seed) * seed * 1235.54);
    }


void main()
{
    vec2 uv = gl_FragCoord.xy;
    vec3 color = vec3(0.0);

    color = vec3( (noise(uv.x, 456.) + noise(uv.y, 427.)) / 2.);

    gl_FragColor = vec4(color, 1.0);
}