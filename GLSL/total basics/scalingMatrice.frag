/*works with shadertoy*/
mat2 scale(vec2 scale)
{
    return mat2(scale.x, 0.0,
                0.0, scale.y);

}


void main(){
    vec2 uv = gl_FragCoord.xy/iResolution.xy;
    vec3 color = vec3(0.0);

    uv *= scale(vec2(sin(iTime)) + 1.);

    color = vec3(uv.x, uv.y, 0.);
    gl_FragColor = vec4(color,1.0);
}