using UnityEngine;
using System.Collections;

public class Fractal : MonoBehaviour
{
    public Mesh mesh;
    public Material material;
    public int maxDepth;
    public float childScale;

    private int depth;
    private Material[] materials;

    private static Vector3[] childDirections = {
        Vector3.up,
        Vector3.right,
        Vector3.left,
        Vector3.forward,
        Vector3.back
    };

    private static Quaternion[] childOrientations = {
        Quaternion.identity,
        Quaternion.Euler(0f, 0f, -90f),
        Quaternion.Euler(0f, 0f, 90f),
        Quaternion.Euler(90f, 0f, 0f),
        Quaternion.Euler(-90f, 0f, 0f)
    };
    private void InitializeMaterials()
    {
        materials = new Material[maxDepth + 1];
        for (int i = 0; i <= maxDepth; i++)
        {
            materials[i] = new Material(material);
            materials[i].color =
                Color.Lerp(Color.white, Color.red, Random.Range(0f, 1f));
        }
    }

    private void Initialize(Fractal parent, int childIndex)
    {
        mesh = parent.mesh;
        materials = parent.materials;
        maxDepth = parent.maxDepth;
        depth = parent.depth + 1;
        childScale = parent.childScale;
        transform.parent = parent.transform;

        transform.localScale = Vector3.one * childScale;
        transform.localPosition = childDirections[childIndex] * (.5f + .5f * childScale);
        transform.localRotation = childOrientations[childIndex];
    }

    private IEnumerator CreateChildren()
    {
        yield return new WaitForSeconds(Random.Range(.1f, .5f));
        for (int i = 0; i < childDirections.Length; i++)
            new GameObject("Fractal Child").AddComponent<Fractal>().Initialize(this, i);

    }

    private void Start()
    {
        if (materials == null)
            InitializeMaterials();

        gameObject.AddComponent<MeshFilter>().mesh = mesh;
        gameObject.AddComponent<MeshRenderer>().material = materials[depth];

        if (depth < maxDepth)
            StartCoroutine(CreateChildren());
    }
}