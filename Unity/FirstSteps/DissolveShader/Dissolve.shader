﻿Shader "Unlit/Dissolve"
{
    Properties
    {
        _MainTex ("Texture", 2D) = "white" {}
        _DissolveTexture("Dissolve Textere", 2D) = "white" {}
        _DissolveY("Current Y of the dissolve", Float) = 0
        _DissolveSize("Size of the effect", Float) = 2
        _Starting("Starting Y point", Float) = -10
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
                float3 worldPos : TEXCOORD1;
            };

            sampler2D _MainTex;
            float4 _MainTex_ST;
            sampler2D _DissolveTexture;
            float _DissolveY;
            float _DissolveSize;
            float _Starting;

            v2f vert (appdata v)
            {
                v2f o;
                o.vertex = UnityObjectToClipPos(v.vertex);
                o.uv = TRANSFORM_TEX(v.uv, _MainTex);
                o.worldPos = mul(unity_ObjectToWorld, v.vertex).xyz;
                return o;
            }

            fixed4 frag (v2f i) : SV_Target
            {
                fixed4 col = tex2D(_MainTex, i.uv);

                float transition = _DissolveY - i.worldPos.y;
                clip(_Starting + (transition + (tex2D(_DissolveTexture, i.uv) * _DissolveSize)));

                return col;
            }
            ENDCG
        }
    }
}
