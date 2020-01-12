/*works with shadertoy*/
#ifdef GL_ES
precision mediump float;
#endif

vec2 N22(vec2 p)
{
    vec3 a = fract(p.xyx * vec3(123.34, 234.34, 345.65));
    a += dot(a, a + 34.45);
    return fract(vec2(a.x * a.y, a.y * a.z));
}

void main()
{
    vec2 uv = (2. * gl_FragCoord.xy - iResolution.xy) / iResolution.y;
    
    float m = 0.;
    float t = iTime;

    float minDist = 100.;
    float cellIndex = 0.;

    for(float i = 0.; i < 50.; i++)
    {
        vec2 n = N22(vec2(i));
        vec2 p = sin(n * t);

        float d = length(uv - p);
        m += smoothstep(.05, .01, d);

        if(d < minDist)
        {
            minDist = d;
            cellIndex = i;
        }
    }

    vec3 col = vec3(cellIndex / 50.);

    gl_FragColor = vec4(col, 1.0);
}