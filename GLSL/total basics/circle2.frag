/*works with shadertoy*/
void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    
    vec2 uv = fragCoord/iResolution.xy;
    vec2 middle = uv - 0.5; //sets origin to the middle
    middle.x *= iResolution.x/iResolution.y; //correct distorion (screen ratio)

    float distance = length(middle);
    float radius = 0.3;
    float c = smoothstep(radius, radius - 0.005, distance);

    //if( distance < radius ) c = 1.0; else c = 0.0; //rough circle
    
    fragColor = vec4(vec3(c),1.0);
}