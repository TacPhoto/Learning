using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BlendingController : MonoBehaviour
{
    private Material mat;

    private void Start()
    {
        mat = GetComponent<Renderer>().material;
    }

    private void Update()
    {
        mat.SetFloat("_Blending", (Mathf.Sin(Time.time) * .5f + .5f));
    }
}
