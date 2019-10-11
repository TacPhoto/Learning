/*works with shadertoy*/
precision mediump float;

float DistLine(vec3 Ro, vec3 Rd, vec3 p) //rendering ray
{
    return length(cross(p - Ro, Rd)) / length(Rd);
}


void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    vec2 uv = fragCoord/iResolution.xy; // Normalized pixel coordinates (0 to 1)
	uv -= .5; //set origin to middle of the screen
    uv.x *= iResolution.x / iResolution.y; //correct aspect ratio
    
    vec3 Ro = vec3(0. , 0., -2.); //Ray Origin 
    vec3 Rd = vec3(uv.x, uv.y, 0.) - Ro; //Ray Direction
    
    float t = iTime;
    vec3 p = vec3(sin(t), 0., 1. + cos(t)); //point
    
    float d = DistLine(Ro, Rd, p);
    
    d = smoothstep(.1, .09, d);

    fragColor = vec4(d);
}