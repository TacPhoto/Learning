using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ReplacementShader : MonoBehaviour
{
    public Shader ReplacementMaterial;
    void OnEnable()
    {
        if (ReplacementMaterial != null)
            GetComponent<Camera>().SetReplacementShader(ReplacementMaterial, "RenderType");
    }


    void OnDisable()
    {
        GetComponent<Camera>().ResetReplacementShader();
    }
}
