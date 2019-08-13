/*works with shadertoy*/

float Circle(vec2 uv, vec2 offset, float radius, float blur)
{
	float distance = length(uv - offset);
	float c = smoothstep(radius, radius - blur, distance);
	return c;
}

void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    
    vec2 uv = fragCoord/iResolution.xy;
    vec2 middle = uv - 0.5; //sets origin to the middle
    middle.x *= iResolution.x/iResolution.y; //correct distorion (screen ratio)

    vec3 col = vec3(0.4, 0.3, 0.8);
	float c = Circle(middle, vec2(0.0, 0.0), 0.4, 0.005);
    
    c -= Circle(middle, vec2(-0.1, 0.1), 0.05, 0.005) + Circle(middle, vec2(0.1, 0.1), 0.05, 0.005);
    c -= Circle(middle, vec2(-0.1, -0.1), 0.05, 0.005) + Circle(middle, vec2(0.1, -0.1), 0.05, 0.005);

    col *= c; //mask color
    fragColor = vec4(col,1.0);
}