using TMPro;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class UIManager : MonoBehaviour

{
    public static UIManager Instance;
    public GameObject damageTextPrefab;
    public GameObject healthTextPrefab;
    public GameObject goldTextPrefab;

    public Canvas gameCanvas;
    
    public TextMeshProUGUI timerText;
    public TextMeshProUGUI scoreText;
    public TextMeshProUGUI levelText;
    public TextMeshProUGUI coinsText;
    
    public float textOffset = 50f;
    public Color damageColor = Color.red;
    public Color healColor = Color.green;
    public Color goldColor = Color.yellow;
    
    public GameObject gameOverPanel;
    public TextMeshProUGUI finalScoreText;
    public Button restartButton;
    public Button quitButton;
    
    public GameObject pausePanel;  
    
    public Button resumeButton;    
    public Button quitButtonPause;       
    
    private void Start()
    {
        if (gameOverPanel != null)
            gameOverPanel.SetActive(false);
    
        if (pausePanel != null)
            pausePanel.SetActive(false);
        
        // game over onclick listeners
        if (restartButton != null)
            restartButton.onClick.AddListener(OnRestartClicked);
        if (quitButton != null)
            quitButton.onClick.AddListener(OnQuitClicked);
        
        // pause menu onclick listeners
        if (resumeButton != null)
            resumeButton.onClick.AddListener(OnResumeClicked);
        if (quitButtonPause != null)
            quitButtonPause.onClick.AddListener(OnQuitClicked);
        
        
    }
    private void OnResumeClicked()
    {
        GameManager.Instance.ResumeGame();
    }

    


    public void ShowGameOverScreen()
    {
        if (gameOverPanel == null) return;
        
        var panelImage = gameOverPanel.GetComponent<Image>();
        panelImage.color = new Color(0, 0, 0, 1f);
        gameOverPanel.SetActive(true);
        
        if (finalScoreText != null)
        {
            finalScoreText.text = $"Final Score:{GameManager.Instance.score}";
        }
    }


    private void OnRestartClicked()
    {
        gameOverPanel.SetActive(false);
        pausePanel.SetActive(false);
        GameManager.Instance.RestartLevel();
    }

    private void OnQuitClicked()
    {
        GameManager.Instance.ExitGame();
    }
    public void ShowPauseMenu()
    {
        pausePanel.SetActive(true);
    }

    public void HidePauseMenu()
    {
        pausePanel.SetActive(false);
    }
    public void HideGameOverMenu()
    {
        gameOverPanel.SetActive(false);
    }
    

    private void Awake()
    {
        if (Instance == null)
        {
            Instance = this;
            DontDestroyOnLoad(gameObject);
        }
        else
        {
            Destroy(gameObject);
        }
    }


    public void CharacterTookDamage(GameObject character, int damageReceived)
    {
        if (GameManager.Instance.currentState == GameManager.GameState.GameOver) return;
        
        if (character == null || damageTextPrefab == null) return;

        Vector3 spawnPosition = Camera.main.WorldToScreenPoint(character.transform.position);
        spawnPosition.y += textOffset;

        var textObj = Instantiate(damageTextPrefab, spawnPosition, Quaternion.identity, gameCanvas.transform);
        var tmpText = textObj.GetComponent<TMP_Text>();
        if (tmpText == null) return;
        tmpText.text = damageReceived.ToString();
        tmpText.color = damageColor;
    }
    public void characterHealed(GameObject character, int healAmount)
    {
        if (GameManager.Instance.currentState == GameManager.GameState.GameOver) return;
        
        if (character == null || healthTextPrefab == null) return;

        Vector3 spawnPosition = Camera.main.WorldToScreenPoint(character.transform.position);
        spawnPosition.y += textOffset;

        var textObj = Instantiate(healthTextPrefab, spawnPosition, Quaternion.identity, gameCanvas.transform);
        var tmpText = textObj.GetComponent<TMP_Text>();
        if (tmpText == null) return;
        tmpText.text = healAmount.ToString();
        tmpText.color = healColor;
    }
    public void coinsCollected(GameObject character, int coins)
    {
        if (GameManager.Instance.currentState == GameManager.GameState.GameOver) return;
        
        if (character == null || goldTextPrefab == null) return;

        Vector3 spawnPosition = Camera.main.WorldToScreenPoint(character.transform.position);
        spawnPosition.y += textOffset;

        var textObj = Instantiate(goldTextPrefab, spawnPosition, Quaternion.identity, gameCanvas.transform);
        var tmpText = textObj.GetComponent<TMP_Text>();
        if (tmpText == null) return;

        tmpText.text = coins + " coins";
        tmpText.color = goldColor;
    }
    public void UpdateTimer(float time)
    {
        
        Debug.Log("Updating timer to " + time);
        timerText.text = Mathf.CeilToInt(time) + " seconds";    
    }


    public void UpdateScore(int score)
    {
        scoreText.text = "Score: " + score;
    }

    public void UpdateLevel(int level)
    {
        Debug.Log("Updating level to " + level);
        levelText.text = "Level: " + level;
    }

    public void UpdateCoins(int coins)
    {
        coinsText.text = "Coins: " + coins;
    }

}