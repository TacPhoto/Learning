using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;


public class GuessTheNumber : MonoBehaviour
{
    public InputField input;
    public Text infoText;

    private int guessNumber;
    private int usersGuess;
    
    void Start()
    {
        guessNumber = Random.RandomRange(0, 100);
    }

    public void CheckGuess()
    {
        usersGuess = int.Parse(input.text);

        if (usersGuess == guessNumber)
            infoText.text = "You WON!";
        else if (usersGuess > guessNumber)
            infoText.text = "A bit too much";
        else
            infoText.text = "A bit more";

        input.text = "";
        
    }
}
