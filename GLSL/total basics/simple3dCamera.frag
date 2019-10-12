/*works with shadertoy*/
precision mediump float;

float DistLine(vec3 Ro, vec3 Rd, vec3 p) //rendering ray
{
    return length(cross(p - Ro, Rd)) / length(Rd);
}


float DrawPoint(vec3 Ro, vec3 Rd, vec3 p)
{
    float d = DistLine(Ro, Rd, p);
    return smoothstep(.06, .05, d);
}


void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    float t = iTime;
    vec2 uv = fragCoord/iResolution.xy; // Normalized pixel coordinates (0 to 1)
	uv -= .5; //set origin to middle of the screen
    uv.x *= iResolution.x / iResolution.y; //correct aspect ratio

   	float zoom = 1.;
    vec3 Ro = vec3(3. * sin(t) , 2., -3. * cos(t)); //Ray Origin aka Camera Origin
    vec3 lookat = vec3(.5); //LookAt Point
    
    vec3 f = (normalize(lookat - Ro)); //forward vector
    vec3 r = cross(vec3(0., 1., 0.), f); //right vector
    vec3 u = cross(f, r); //up vector
	
    vec3 c = Ro + f * zoom; //center of the screen
    
    float d = 0.; //point initialization
    vec3 i = c + (uv.x * r) + (uv.y * u); //intersection
    
    vec3 Rd = i - Ro; //Ray Direction
    
    ////initialize cube
    	d += DrawPoint(Ro, Rd, vec3(0.,0.,0.));
    	d += DrawPoint(Ro, Rd, vec3(0.,1.,1.));
    	d += DrawPoint(Ro, Rd, vec3(0.,1.,0.));
    	d += DrawPoint(Ro, Rd, vec3(0.,0.,1.));

    	d += DrawPoint(Ro, Rd, vec3(1.,0.,0.));
    	d += DrawPoint(Ro, Rd, vec3(1.,1.,1.));
    	d += DrawPoint(Ro, Rd, vec3(1.,1.,0.));
    	d += DrawPoint(Ro, Rd, vec3(1.,0.,1.));   
    ////cube initialized
      
    fragColor = vec4(d);
}