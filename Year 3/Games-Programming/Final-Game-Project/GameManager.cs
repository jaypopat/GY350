using System;
using System.Collections;
using UnityEngine;
using UnityEngine.SceneManagement;

public class GameManager : MonoBehaviour
{
    public static GameManager Instance;

    public enum GameState
    {
        MainMenu,
        Playing,
        Paused,
        GameOver
    }

    [Header("Game State")]
    public GameState currentState;

    private float gameTime;
    
    [Header("Player Data")]
    public int coins;
   
    public int enemiesKilled = 0;
    public int score;
    
    public float levelDuration = 60f;
    private float remainingTime;


    public LevelSystem levelSystem;
    public PlayerController playerController;

    private void Awake()
    {
        if (Instance == null)
        {
            Instance = this;
        }
    }
    private void Start()
    {
        // below line is for testing purposes only
        SaveSystem.DeleteSave();
        LoadGameData();
        currentState = GameState.Playing;
    }

    private void Update()
    {
        if (currentState == GameState.Playing)
        {
            remainingTime -= Time.deltaTime;
            if (remainingTime <= 0)
            {
                StartNextLevel();
                return; // Add this line to prevent further execution
            }
            UIManager.Instance.UpdateTimer(remainingTime);
        }

        // Handle pause and exit input early return if not esc
        if (!Input.GetKeyDown(KeyCode.Escape)) return;
        switch (currentState)
        {
            case GameState.Playing:
                PauseGame();
                break;
            case GameState.Paused:
                ResumeGame();
                break;
            case GameState.MainMenu:
            case GameState.GameOver:
                ExitGame();
                break;
            default:
                throw new ArgumentOutOfRangeException();
        }

    }

    public void ExitGame()
    {
        Application.Quit();
        Debug.Log("Exiting game"); 
    }


    public void StartGame()
    {
        // even though disabled at start precautionary measure
        UIManager.Instance.HidePauseMenu();
        UIManager.Instance.HideGameOverMenu();
        currentState = GameState.Playing;
        Time.timeScale = 1f;
        
        levelSystem.currentLevel = 1;
        score = 0;
        coins = 0;
        remainingTime = levelDuration; // 60 sec
        if (playerController != null)
        {
            playerController.currentHealth = playerController.maxHealth;
        }

        UIManager.Instance.UpdateTimer(remainingTime);
        UIManager.Instance.UpdateLevel(levelSystem.currentLevel);
        UIManager.Instance.UpdateScore(score);
        UIManager.Instance.UpdateCoins(coins);
    }
    
    public void AddScore(int points)
    {
        score += points + enemiesKilled * 10;
        
        UIManager.Instance.UpdateScore(score);
        
    }

    public void GameOver()
    {
        currentState = GameState.GameOver;
        Time.timeScale = 0f; 
        UIManager.Instance.ShowGameOverScreen();
    }


    public void AddCoins(int amount)
    {
        coins += amount;
        
        UIManager.Instance.UpdateCoins(coins);
        
    }

    public void RestartLevel()
    {
        UIManager.Instance.HidePauseMenu();
        UIManager.Instance.HideGameOverMenu();
        Time.timeScale = 1f;
        currentState = GameState.Playing;
        // playing same scene again so the player is back in scene after death
        SceneManager.LoadScene(SceneManager.GetActiveScene().name);
        StartGame();
    }

    private void PauseGame()
    {
        currentState = GameState.Paused;
        Time.timeScale = 0f;
        UIManager.Instance.ShowPauseMenu();
    }

    public void ResumeGame()
    {
        currentState = GameState.Playing;
        Time.timeScale = 1f;
        UIManager.Instance.HidePauseMenu();
    }

    private void StartNextLevel()
    {
        remainingTime = levelDuration;
        levelSystem.currentLevel++;
    
        GameObject[] enemies = GameObject.FindGameObjectsWithTag("Enemy");
        foreach (var enemy in enemies)
        {
            var enemyObj = enemy.GetComponent<Enemy>();
            enemyObj.TakeDamage(int.MaxValue);
        }


        if (playerController != null)
        {
            playerController.currentHealth = playerController.maxHealth; // resetting the player health to max
        }

        // Update UI
        UIManager.Instance.UpdateLevel(levelSystem.currentLevel);
        UIManager.Instance.UpdateTimer(remainingTime);

        // pause briefly before starting the next level
        StartCoroutine(StartNextLevelAfterDelay(1f));
    }


    private IEnumerator StartNextLevelAfterDelay(float delay)
    {
        yield return new WaitForSeconds(delay);
        currentState = GameState.Playing;
        levelSystem.OnLevelStart();
        
    }
    private void SaveGameData()
    {
        SaveSystem.SaveGame(new SaveData
        {
            coins = coins,
            currentLevel = levelSystem.currentLevel,
            gameTime = gameTime,
            score = score,
            enemiesKilled = enemiesKilled
        });
    }

    private void LoadGameData()
    {
        var data = SaveSystem.LoadGame();
        if (data == null) return;

        coins = data.coins;
        levelSystem.currentLevel = data.currentLevel;
        gameTime = data.gameTime;
        score = data.score;
        enemiesKilled = data.enemiesKilled;

        if (UIManager.Instance == null) return;
        UIManager.Instance.UpdateLevel(levelSystem.currentLevel);
        UIManager.Instance.UpdateScore(score);
        UIManager.Instance.UpdateCoins(coins);
    }
    

    private void OnApplicationQuit()
    {
        SaveGameData();
    }
} 