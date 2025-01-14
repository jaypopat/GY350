using UnityEngine;
using System.Collections.Generic;
using UnityEngine.Pool;

public class CollectibleSpawner : MonoBehaviour
{
    [System.Serializable]
    public class SpawnableCollectible
    {
        public GameObject prefab;
        public float spawnWeight = 1f;
    }

    public List<SpawnableCollectible> collectibles;
    public float spawnInterval = 5f;
    public float minSpawnRadius = 3f;
    public float maxSpawnRadius = 8f;
    public int maxCollectibles = 10;
    public Transform player;
    private float nextSpawnTime;
    private List<GameObject> activeCollectibles = new List<GameObject>();

    private void Start()
    {
        nextSpawnTime = Time.time + spawnInterval;
        if (player == null)
        {
            player = GameObject.FindGameObjectWithTag("Player")?.transform;
        }
    }

    private void Update()
    {
        activeCollectibles.RemoveAll(collectible => collectible == null);

        if (Time.time >= nextSpawnTime && activeCollectibles.Count < maxCollectibles)
        {
            SpawnCollectible();
            nextSpawnTime = Time.time + spawnInterval;
        }
    }

    private void SpawnCollectible()
    {
        Vector2 spawnPoint = GetSpawnPoint();
        if (spawnPoint == Vector2.negativeInfinity)
        {
            Debug.LogWarning("Skipping collectible spawn due to invalid spawn point.");
            return;
        }

        GameObject collectiblePrefab = SelectCollectibleToSpawn();
        //GameObject spawnedCollectible = Instantiate(collectiblePrefab, spawnPoint, Quaternion.identity);
        //spawnedCollectible.transform.localScale = new Vector3(0.25f, 0.25f, 1);
        //activeCollectibles.Add(spawnedCollectible);
        
        
        GameObject spawnedCollectible = ObjectPool.Instance.SpawnFromPool(collectiblePrefab.name, spawnPoint, Quaternion.identity);

        if (spawnedCollectible == null) return;
        spawnedCollectible.transform.localScale = new Vector3(0.25f, 0.25f, 1);
        activeCollectibles.Add(spawnedCollectible);
    }


    private Vector2 GetSpawnPoint()
    {
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
                return hit.point + Vector2.up * 1f; // adjusted the spawn offset above ground
            }
        }

        Debug.Log("could not find a valid point");
        return player.position + Vector3.up * 3f;
    }


    private GameObject SelectCollectibleToSpawn()
    {
        var totalWeight = 0f;
        for (var index = 0; index < collectibles.Count; index++)
        {
            var collectible = collectibles[index];
            totalWeight += collectible.spawnWeight;
        }

        var random = Random.Range(0f, totalWeight);
        var weightSum = 0f;

        foreach (var collectible in collectibles)
        {
            weightSum += collectible.spawnWeight;
            if (random <= weightSum)
            {
                return collectible.prefab;
            }
        }

        return collectibles[0].prefab;
    }
}
