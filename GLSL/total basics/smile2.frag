/*works with shadertoy, define TO_GLSL to make it work with VS_Code GLSL Viewer*/
#ifdef GL_ES
precision mediump float;
#endif

//#define TO_GLSL

#ifndef TO_GLSL
#define DECLAREUNIFORMS 0
#endif

#ifdef TO_GLSL
#define iResolution u_resolution
#define fragColor gl_FragColor
#define fragCoord gl_FragCoord.xy
#define mainImage(x, y) main()
#define iMouse u_mouse
#define iTime u_time
#define DECLAREUNIFORMS 1
#if(DECLAREUNIFORMS)
	uniform vec2 u_resolution;
	uniform vec2 iMouse;
	uniform float iTime;
#endif
#endif

#define S(a, b, t) smoothstep(a, b, t)
#define sat(x) clamp(x, 0., 1.)


float remap01(float a, float b, float t)
{
    return sat((t-a)/ (b-a));
}


float remap(float a, float b, float c, float d, float t)
{
    return sat(((t-a)/(b-a)) * (d-c) + c);    
}


vec2 within(vec2 uv, vec4 rect)
{
	return (uv - rect.xy) / (rect.zw - rect.xy);
}


vec4 Brow(vec2 uv)
{
    float y = uv.y;
    uv.y += uv.x * .85 - .35;
    uv.x -= .15;
    uv -= .5;
    
    vec4 col = vec4(.0);
    
    float blur = .1;
    float d1 = length(uv);
    float s1 = S(.45, .45 - blur, d1);
    float d2 = length(uv - vec2(.12, -.25) * .75);
    float s2 = S(.5, .5 - blur, d2);
    
    float browMask = sat(s1 - s2);

    float colMask = remap01(.7, .8, y) * .75;
    colMask *= S(.6, .9, browMask);

    vec4 browCol = mix(vec4(.4, .2, .2, 1.), vec4(.6, .4, .3, 1.), browMask);
    
    float shadowMask = sat(s1-s2);
    
    col = mix(col, vec4(0.,0.,0.,1.), S(.0, 1., shadowMask)*.5);  
    col = mix(col, browCol, S(.2, .4, browMask));
    
   	return  col;
                 
}


vec4 Eye(vec2 uv, float side, vec2 m, float smile)
{
    uv -= .55;
    uv.x *= side;
    
    float d = length(uv);

    vec4 irisCol = vec4(.3, .5, 1., 1.);
    vec4 col = mix(vec4(1.), irisCol, S(.1, .7, d) * .5);
    col.a = S(.5, .48, d);

    col.rgb *= 1. - S(.45, .5, d) * .5 * sat(-uv.y - uv.x * side); //shadow
    
    d = length(uv - m*.545);
    float irisMask = S(.3, .28, d);
    col.rgb = mix(col.rgb, vec3(0.), irisMask); //iris outline
    irisCol.rgb *= 1. + S(.3, .05, d);
    
    d = length(uv - m*.545);
    
    col.rgb = mix(col.rgb, irisCol.rgb, S(.28, .25, d)); 
    
    float pupilSize = mix(irisMask * .3, .235, 2.*smile);
    float pupilMask = S(pupilSize, pupilSize * 0.9, d);
    pupilMask *= S(-pupilSize * .9, pupilSize * 0.9, d) * 1.6; //blueish overlay
	pupilMask *= irisMask;
    
    col.rgb = mix(col.rgb, vec3(0.), pupilMask); //pupil
    
    float highlight = S(.1, .09, length(uv - vec2(-.15, .15)));
    highlight += S(.09, .01, length(uv + vec2(-.09, .09)));
    highlight += S(.3, .01, length(uv + vec2(-.08, .08))) *.3;

    col.rgb = mix(col.rgb, vec3(1.), highlight);
                      
    return col;
}


vec4 Mouth(vec2 uv)
{
    uv.y += .2;
    uv -= .5;
    uv.y -= uv.x * uv.x * 1.3;
	uv.y *= 2.;
	vec4 col = vec4(.5, .18, .05, 1.);
    float d = length(uv);
    
    col.a = S(.45, .43,d);
    
    float td = length(uv - vec2(0., .6));
    
    vec3 toothCol = vec3(1.) *S(.6, .35, d) ;
    col.rgb = mix(col.rgb, toothCol, S(.4, .37, td));
    
    td = length(uv + vec2(.0, .5));
    col.rgb = mix(col.rgb, vec3(1., .5, .5), S(.5, .2, td));
    
    return col;
}


vec4 Head(vec2 uv)
{
	vec4 col = vec4(.9 , .65, .1, 1.);
    
    float d = length(uv);
    
    col.a = S(.5, .49, d);
    
    float edgeShade = remap01(.35, .5, d);
    edgeShade *= edgeShade;
    col.rgb *= 1. - edgeShade * .5;
    
    col.rgb = mix(col.rgb, vec3(.6, .3, .1), S(.47, .48, d)); //contour
    
    float highlight = S(.41, .405, d);
    highlight *= remap(.41, -.1, .75, 0., uv.y);
    highlight *= S(.185, .19, length(uv - vec2(.21, .1)));

    col.rgb = mix(col.rgb, vec3(1.), highlight);
    
    d = length(uv - vec2(.23, -.2));
    float cheek = S(.2, .01, d) * .4;
    cheek *= S(.18, .16, d);
    
    col.rgb = mix(col.rgb, vec3(1., .1, .1), cheek);
    
    return col;
}


vec4 Smiley(vec2 uv, vec2 m, float smile)
{
	vec4 col = vec4(0.);
    
    float side = sign(uv.x);
    uv.x = abs(uv.x);
    vec4 head = Head(uv);
    vec4 eye = Eye(within(uv, vec4(.03, -.1, .37, .25)), side, m, smile);
    vec4 mouth = Mouth(within(uv, vec4(-.3, -.4, .3, .01)));
    vec4 brow = Brow(within(uv, vec4(.03, .2, .4, .4)));

    col = mix(col, head, head.a);
    col = mix(col, eye, eye.a);
    col = mix(col, mouth, mouth.a);
    col = mix(col, brow, brow.a);

    return col;
}


void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    vec2 uv = fragCoord/iResolution.xy;
	uv -= 0.5;
	uv.x *= iResolution.x/iResolution.y;

    vec2 m = iMouse.xy / iResolution.xy;
    m -= .5;
    
    float smile = cos(iTime) * .5 + .5;

    fragColor = Smiley(uv, m, smile);
}