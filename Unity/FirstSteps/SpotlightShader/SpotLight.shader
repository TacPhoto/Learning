Shader "Unlit/SpotLight"
{
    Properties
    {
        _MainTex ("Texture", 2D) = "white" {}
        _CharacterPos("Character position", vector) = (0,0,0,0)
        _CircleRadius("Spotlight size", Range(0, 20)) = 3
        _RingSize("Ring size", Range(0, 5)) = 1
        _ColorTint("Outer color tint", Color) = (0,0,0,0)
    }
    SubShader
    {
        Tags { "RenderType"="Opaque" }
        LOD 100

        Pass
        {
            CGPROGRAM
            #pragma vertex vert
            #pragma fragment frag
            // make fog work
            #pragma multi_compile_fog

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

                float dist : TEXCOORD1;
            };

            sampler2D _MainTex;
            float4 _MainTex_ST;
            float4 _CharacterPos;
            float4 _ColorTint;
            float _CircleRadius;
            float _RingSize;
            
            v2f vert (appdata v)
            {
                v2f o;
                o.vertex = UnityObjectToClipPos(v.vertex);
                o.uv = TRANSFORM_TEX(v.uv, _MainTex);

                float3 worldPos = mul(unity_ObjectToWorld, v.vertex).xyz;
                o.dist = distance(worldPos, _CharacterPos.xyz);

                return o;
            }

            fixed4 frag (v2f i) : SV_Target
            {
                fixed4 col = _ColorTint;
                bool inCircle = i.dist < _CircleRadius;

                if(inCircle)//player spotlight
                    col = tex2D(_MainTex, i.uv);
                else if(!inCircle && i.dist < _CircleRadius + _RingSize)//blending
                {
                    float blendStrength = i.dist - _CircleRadius;
                    col = lerp(tex2D(_MainTex, i.uv), _ColorTint, blendStrength / _RingSize);
                }

                return col;
            }
            ENDCG
        }
    }
}
