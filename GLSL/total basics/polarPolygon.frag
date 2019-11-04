/*works with shadertoy*/

const float PI = 3.1415926535;


float polygonShape(vec2 position, float radius, float sides)
{
    position = position * 2. - 1.;
    float angle = atan(position.x, position.y);
    float slice = PI * 2.0 / sides;

    return step(radius, cos(floor(0.5 + angle / slice) * slice - angle) *length(position));
}


void main()
{
    vec2 uv = gl_FragCoord.xy / iResolution.xy;
    uv.x *= iResolution.x / iResolution.y;
    vec3 col = vec3(.0);

    float sides = mix(0., 8., sin(iTime / 2.));

    float polygon = polygonShape(uv, .6, sides);

    col = vec3(polygon);
    gl_FragColor = vec4(col, 1.);
}