/*works with shadertoy*/

float Band(float start, float end, float middlex, float blur)
{
	float step1 = smoothstep(start - blur, start + blur, middlex);   
	float step2 = smoothstep(end + blur, end - blur, middlex);   
    return step1 * step2;
}    

float Rect(vec2 middle, float left, float right, float bottom, float top, float blur)
{
    float band1 = Band(left, right, middle.x, blur);
    float band2 = Band(bottom, top, middle.y, blur);

    return band1 * band2;
}

void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    vec2 uv = fragCoord/iResolution.xy;
    vec2 middle = uv - 0.5; //sets origin to the middle
    middle.x *= iResolution.x/iResolution.y; //correct distorion (screen ratio)

    vec3 col = vec3(1.0, 1.0, 1.0);

	float mask = 0.0;
    
    mask = Rect(middle, -0.2, 0.2, -0.3, 0.3, 0.01);
    
    col *= mask;
    fragColor = vec4(col,1.0) ;
}