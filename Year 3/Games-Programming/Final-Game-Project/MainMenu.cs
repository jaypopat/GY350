using UnityEngine;
using UnityEngine.SceneManagement;

public class MainMenu : MonoBehaviour
{
    void Start()
    {

    }

    void Update()
    {

    }

    public void playGame()
    {
        SceneManager.LoadSceneAsync("Main");
        GameManager.Instance.StartGame();
    }

    public void quitGame()
    {
        Application.Quit();
    }
}
