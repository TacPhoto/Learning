using UnityEngine.UI;
using UnityEngine;

[RequireComponent(typeof(FPSCounter))]

public class FPSDisplay : MonoBehaviour
{
    public Text fpsLabel;

    FPSCounter fpsCounter;

    static string[] fpsCountStrings = { //this way we avoid temp string allocations
    "00fps", "01fps", "02fps", "03fps", "04fps", "05fps", "06fps", "07fps", "08fps", "09fps",
    "10fps", "11fps", "12fps", "13fps", "14fps", "15fps", "16fps", "17fps", "18fps", "19fps",
    "20fps", "21fps", "22fps", "23fps", "24fps", "25fps", "26fps", "27fps", "28fps", "29fps",
    "30fps", "31fps", "32fps", "33fps", "34fps", "35fps", "36fps", "37fps", "38fps", "39fps",
    "40fps", "41fps", "42fps", "43fps", "44fps", "45fps", "46fps", "47fps", "48fps", "49fps",
    "50fps", "51fps", "52fps", "53fps", "54fps", "55fps", "56fps", "57fps", "58fps", "59fps",
    "60fps", "61fps", "62fps", "63fps", "64fps", "65fps", "66fps", "67fps", "68fps", "69fps",
    "70fps", "71fps", "72fps", "73fps", "74fps", "75fps", "76fps", "77fps", "78fps", "79fps",
    "80fps", "81fps", "82fps", "83fps", "84fps", "85fps", "86fps", "87fps", "88fps", "89fps",
    "90fps", "91fps", "92fps", "93fps", "94fps", "95fps", "96fps", "97fps", "98fps", "99fps"
    };

    private void Awake()
    {
        fpsCounter = GetComponent<FPSCounter>();
    }

    private void Update()
    {
        fpsLabel.text = fpsCountStrings[Mathf.Clamp(fpsCounter.FPS, 0, 99)];
    }
}
