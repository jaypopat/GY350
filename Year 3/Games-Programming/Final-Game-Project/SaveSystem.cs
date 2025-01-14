using System;
using UnityEngine;

public static class SaveSystem
{
    private const string COINS_KEY = "Coins";
    private const string LEVEL_KEY = "Level";
    private const string GAME_TIME_KEY = "GameTime";
    private const string SCORE_KEY = "Score";
    private const string ENEMIES_KILLED_KEY = "EnemiesKilled";

    public static void SaveGame(SaveData data)
    {
        PlayerPrefs.SetInt(COINS_KEY, data.coins);
        PlayerPrefs.SetInt(LEVEL_KEY, data.currentLevel);
        PlayerPrefs.SetFloat(GAME_TIME_KEY, data.gameTime);
        PlayerPrefs.SetInt(SCORE_KEY, data.score);
        PlayerPrefs.SetInt(ENEMIES_KILLED_KEY, data.enemiesKilled);

        PlayerPrefs.Save();
    }

    public static SaveData LoadGame()
    {
        var data = new SaveData
        {
            coins = PlayerPrefs.GetInt(COINS_KEY, 0),
            currentLevel = PlayerPrefs.GetInt(LEVEL_KEY, 0),
            gameTime = PlayerPrefs.GetFloat(GAME_TIME_KEY, 0f),
            score = PlayerPrefs.GetInt(SCORE_KEY, 0),
            enemiesKilled = PlayerPrefs.GetInt(ENEMIES_KILLED_KEY, 0)
        };
        
        Debug.Log($"Loaded data: Coins={data.coins}, Level={data.currentLevel}, Score={data.score}, EnemiesKilled={data.enemiesKilled}");

        return data;
    }

    public static void DeleteSave()
    {
        PlayerPrefs.DeleteAll();
    }
}
