Shader "Unlit/SmoothOutline"
{
    Properties
    {
        _Color("Main Color", Color) = (.5, .5, .5, 1)
        _MainTex ("Texture", 2D) = "white" {}
        _OutlineColor("Outline color", Color) = (0,0,0,1)
        _OutlineThickness("Outline thickness", Range(1.0, 5.0)) = 1.01
    }

    CGINCLUDE
    #include "UnityCG.cginc"

    struct appdata
    {
        float4 vertex : POSITION;
        float2 uv : TEXCOORD0;
    };


    struct v2f
    {
        float4 pos : POSITION;
        float4 color : COLOR;
        float3 normal : NORMAL;
    };

    float4 _OutlineColor;
    float _OutlineThickness;

    v2f vert(appdata v)
    {
        v.vertex.xyz *= _OutlineThickness;

        v2f o;
        o.pos = UnityObjectToClipPos(v.vertex);
        o.color = _OutlineColor;
        return o;
    }


    ENDCG
    SubShader
    {
        Pass // Rendering the Outline
        {
            ZWrite Off

            CGPROGRAM

            #pragma vertex vert
            #pragma fragment frag
            
            half4 frag(v2f i) : COLOR
            {
                return i.color;
            }

            ENDCG
        }

        Tags { "RenderType"="Opaque" }
        LOD 100

        Pass
        {
            ZWrite On

            Material
            {
                Diffuse[_Color]
                Ambient[_Color]
            }

            Lighting On
            
            SetTexture[_MainTex]
            {
                ConstantColor[_Color]
            }

            SetTexture[_MainTex]
            {
                Combine previous * primary DOUBLE
            }
        
        }  
    }
}
