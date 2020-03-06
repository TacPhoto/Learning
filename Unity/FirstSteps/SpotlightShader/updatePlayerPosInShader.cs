using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class updatePlayerPosInShader : MonoBehaviour
{
    public Material mat;
    public string propertyName;
    public Transform player;

    public void Update(){
        if(player != null)
            mat.SetVector(propertyName, player.position);
        else
            Debug.log("Assign the player property");
    }
}
