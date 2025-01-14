using UnityEngine;

public class LevelSystem : MonoBehaviour
{
    [Header("Level Settings")]
    [SerializeField] public int currentLevel = 1;
    [SerializeField] public float levelDuration = 60f;
    [SerializeField] private float currentSpawnRate;
    [SerializeField] private float currentHealthMultiplier;
    
    
    public EnemySpawner enemySpawner;

    public void OnLevelStart()
    {
        float spawnMultiplier = 1 + (currentLevel * 0.2f);
        float healthMultiplier = 1 + (currentLevel * 0.1f);
        
        // each level, spawn rate is faster and enemy health is increased level 1 is 1.2x, level 2 is 1.4x, etc. (base is 100 hence 110 120 130 etc)
        // so since the original spawn rate is 3 seconds this is reduced down etc
        
        enemySpawner.ReduceSpawnInterval(spawnMultiplier);
        enemySpawner.IncreaseEnemyHealth(healthMultiplier);
        
        // only for a read only view to debug status during level updates
        currentSpawnRate = enemySpawner.initialSpawnInterval / spawnMultiplier;
        currentHealthMultiplier = healthMultiplier;
        
        Debug.Log($"level {currentLevel} started");
    }
}