void mainImage( out vec4 fragColor, in vec2 fragCoord )
{
    // Normalized pixel coordinates (from 0 to 1)
    vec2 uv = fragCoord/iResolution.xy;

    vec3 col = vec3(1., 1., 1.);

    float repeats = 20.;

    float cx = floor(repeats * uv.x);
    float cy = floor(repeats * uv.y);

    float checker = mod(cx + cy, 2.0);

    col *= checker;

    fragColor = vec4(col,1.0);
}