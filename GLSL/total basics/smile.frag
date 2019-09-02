/*works with shadertoy*/

float Circle(vec2 uv, vec2 offset, float radius, float blur)
{
	float distance = length(uv - offset);
	float c = smoothstep(radius, radius - blur, distance);
	return c;
}

float Smiley(vec2 middle, vec2 offset, float size)
{
	float c = Circle(middle, vec2(0.0, 0.0), (size / 2.5), 0.005);
    
    c -= Circle(middle, vec2(-0.1, 0.1), (size / 20.0), 0.005) + Circle(middle, vec2(0.1, 0.1), (size / 20.0), 0.005);

    float mouth = Circle(middle, vec2(0.0, 0.0), (size / 3.4), 0.01);
    mouth -= Circle(middle, vec2(0.0, 0.1), (size / 3.2), 0.01);
	if ( mouth < 0.5 ) mouth = 0.0;

    return c - mouth;
}

void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    
    vec2 uv = fragCoord/iResolution.xy;
    vec2 middle = uv - 0.5; //sets origin to the middle
    middle.x *= iResolution.x/iResolution.y; //correct distorion (screen ratio)

    vec3 col = vec3(0.4, 0.3, 0.8);
	
    float mask = Smiley(middle, vec2 (0.0, 0.0), 1.0);
    
    col *= mask;
    fragColor = vec4(col,1.0);
}