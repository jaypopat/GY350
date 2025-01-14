using UnityEngine;
using System.Collections.Generic;

public class EnemySpawner : MonoBehaviour
{
    [System.Serializable]
    public class SpawnableEnemy
    {
        public GameObject prefab;
        public string enemyName;
        public float spawnWeight = 1f;
    }

    public List<SpawnableEnemy> enemies;
    public float initialSpawnInterval = 3f;
    private float currentSpawnInterval;
    public float minSpawnRadius = 2f;
    public float maxSpawnRadius = 10f;
    public int maxEnemies = 5;
    public Transform player;
    private float currentHealthMultiplier = 1f;

    private float nextSpawnTime;
    private List<GameObject> activeEnemies = new List<GameObject>();

    private void Start()
    {
        currentSpawnInterval = initialSpawnInterval;
        nextSpawnTime = Time.time + currentSpawnInterval;
        if (player == null)
        {
            player = GameObject.FindGameObjectWithTag("Player").transform;
        }
    }

    private void Update()
    {
        for (int i = activeEnemies.Count - 1; i >= 0; i--)
        {
            if (!activeEnemies[i].activeInHierarchy)
            {
                activeEnemies.RemoveAt(i);
            }
        }

        if (Time.time >= nextSpawnTime && activeEnemies.Count < maxEnemies)
        {
            SpawnEnemy();
            nextSpawnTime = Time.time + currentSpawnInterval;
        }
    }
    public void ReduceSpawnInterval(float reductionFactor)
    {
        currentSpawnInterval /= reductionFactor;
        Debug.Log($"Spawn interval reduced to {currentSpawnInterval} seconds");
    }

    public void IncreaseEnemyHealth(float healthIncrease)
    {
        currentHealthMultiplier = healthIncrease;
        Debug.Log($"Enemy health multiplier set to {currentHealthMultiplier}");
    }
    private void SpawnEnemy()
    {
        Vector2 spawnPoint = GetSpawnPoint();
        GameObject enemyPrefab = SelectEnemyToSpawn();
        
        // GameObject enemy = Instantiate(enemyPrefab, spawnPoint, Quaternion.identity);
        
        
        GameObject enemy = ObjectPool.Instance.SpawnFromPool(enemyPrefab.name, spawnPoint, Quaternion.identity);
        
        Enemy enemyComponent = enemy.GetComponent<Enemy>();
        if (enemyComponent == null) return;
        enemyComponent.maxHealth = Mathf.RoundToInt(enemyComponent.maxHealth * currentHealthMultiplier);
        enemyComponent.ResetEnemy();
        activeEnemies.Add(enemy);

    }
    public void RemoveFromActiveEnemies(GameObject enemy)
    {
        activeEnemies.Remove(enemy);
    }


    private Vector2 GetSpawnPoint()
    {
        if (player == null) return Vector2.zero;

        for (int i = 0; i < 30; i++)
        {
            float angle = Random.Range(0f, 360f);
            Vector2 direction = Quaternion.Euler(0, 0, angle) * Vector2.right;
            float distance = Random.Range(minSpawnRadius, maxSpawnRadius);
            Vector2 potentialPoint = (Vector2)player.position + direction * distance;

            Vector2 rayStart = potentialPoint + Vector2.up * 10f;
            RaycastHit2D hit = Physics2D.Raycast(rayStart, Vector2.down, 20f, LayerMask.GetMask("Ground"));

            if (hit.collider != null)
            {
                return hit.point + Vector2.up * 1f; // adjusted spawn offset above ground
            }
        }

        Debug.Log("could not find a valid enemy spawn point.");
        return player.position + Vector3.up * 3f;
    }

    private GameObject SelectEnemyToSpawn()
    {
        float totalWeight = 0f;
        foreach (var enemy in enemies)
        {
            totalWeight += enemy.spawnWeight;
        }

        float random = Random.Range(0f, totalWeight);
        float weightSum = 0f;

        foreach (var enemy in enemies)
        {
            weightSum += enemy.spawnWeight;
            if (random <= weightSum)
            {
                return enemy.prefab;
            }
        }

        return enemies[0].prefab;
    }
}
