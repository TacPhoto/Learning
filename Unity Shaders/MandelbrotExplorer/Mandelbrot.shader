Shader "Explorer/Mandelbrot"
{
    Properties
    {
        _MainTex ("Texture", 2D) = "white" {}
        _Area ("Area", vector) = (0, 0, 4, 4)
        _Angle ("Angle", range(-3.1415, 3.1415)) = 0
        _MaxIter("Max Number Of Iterations", range(1, 255)) = 255
        _Color("Color", range(0,1)) = .78
        _Repeat("Repeat", float) = 1
        _Speed("Gradient Speed", range(1, 30)) = 1
        _Mode("Mode", range(0,1)) = 1
        [Toggle(SymetryY)]_YSymetry("Symetry Y", float) = 0
        [Toggle(SymetryX)]_XSymetry("Symetry X", float) = 0
        [Toggle(SymetryX)]_MidSymetry("Middle Symetry", float) = 0

    }
    SubShader
    {
        // No culling or depth
        Cull Off ZWrite Off ZTest Always

        Pass
        {
            CGPROGRAM
            #pragma vertex vert
            #pragma fragment frag

            #include "UnityCG.cginc"

            struct appdata
            {
                float4 vertex : POSITION;
                float2 uv : TEXCOORD0;
            };

            struct v2f
            {
                float2 uv : TEXCOORD0;
                float4 vertex : SV_POSITION;
            };

            v2f vert (appdata v)
            {
                v2f o;
                o.vertex = UnityObjectToClipPos(v.vertex);
                o.uv = v.uv;
                return o;
            }

            float4 _Area;
            float _Angle, _MaxIter, _Color, _Repeat, _Speed, _Mode;
            float _YSymetry, _XSymetry, _MidSymetry;
            sampler2D _MainTex;


            float2 rot(float2 p, float pivot, float angle) //p - point
            {
                float s = sin(angle);
                float c = cos(angle);

                p -= pivot;
                p = float2(p.x * c - p.y * s, p.x * s + p.y * c);
                p += pivot;

                return p;
            }


            fixed4 frag (v2f i) : SV_Target
            {
                float2 uv = i.uv -.5;
                uv.x = _XSymetry ? abs(uv.x) : uv.x;
                uv.y = _YSymetry ? abs(uv.y) : uv.y;

                float2 c = _Area.xy + (uv - .5) * _Area.zw;
                c = rot(c, _Area.xy, _Angle);

                float r = 20;
                float r2 = r * r;
                float2 z, zPrevious;
                float iter;
                for(iter = 0; iter < 255; iter++)
                {
                    zPrevious = rot(z, 0, _Time.y);
                    z = float2(z.x * z.x - z.y * z.y, 2*z.x * z.y) + c;
                    float breakFactor = lerp(dot(z,z) , dot(z, zPrevious), _Mode);
                    if(breakFactor > r) break;

                }

                if(iter > _MaxIter) return 0;

                float dist = length(z); //from origin
                //float fracIter = (dist - r) / (r2 - r); //remaps value range (linear interp))
                float fracIter = log2(log(dist) / log(r)); //double exponential interpolation
                iter -=lerp(fracIter, 0, _Mode); //fix banding
                float m = sqrt(iter / _MaxIter);
                
                float4 col = sin(float4(.3, .45, .65, 1) * m * 20) * .5 + .5; //procedural color
                col = tex2D(_MainTex, float2(m * _Repeat + _Time.y * _Speed, _Color));

                float4 col2 = col * smoothstep(3, 0, fracIter);
                float angle = atan2(z.x, z.y); //-pi and pi
                col2 *= 1 + sin(angle * 2 + _Time.y * 4) * .1;

                col = lerp(col, col2, _Mode);
                
                

                return col;
            }
            ENDCG
        }
    }
}
