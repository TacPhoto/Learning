/*works with shadertoy*/
void main(){
    vec2 uv = gl_FragCoord.xy/iResolution.xy; //normalized uv

    vec3 color = vec3(0.0);

    vec2 bottomLeft = step(vec2(.1), uv);
    vec2 topRight = step(vec2(.1), (1. - uv));

    color = vec3( bottomLeft.x * bottomLeft.y * topRight.x * topRight.y );

    gl_FragColor = vec4(color,1.0);
}