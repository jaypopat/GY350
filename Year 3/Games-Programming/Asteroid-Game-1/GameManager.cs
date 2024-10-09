using UnityEngine;

public class GameManager : MonoBehaviour
{
    public int currentGameLevel = 1; // Level determines number of asteroids
    public GameObject asteroidPrefab; // Reference to the asteroid prefab

    private Camera _mainCamera;
    private float _screenBottom;
    private float _screenLeft;
    private float _screenRight;
    private float _screenTop;

    private void Start()
    {
        _mainCamera = Camera.main;
        _mainCamera.transform.position = new Vector3(0, 30, 0);
        _mainCamera.transform.LookAt(new Vector3(0, 0, 0), Vector3.up);

        CalculateScreenBoundaries();        

        StartNextLevel(); // instantiate the asteroids according to the current game level
    }

    private void CalculateScreenBoundaries()
    {
        // Calculate screen boundaries and converted to world coordinates to instantiate asteroids
        _screenLeft = _mainCamera.ScreenToWorldPoint(new Vector3(0, 0, _mainCamera.transform.position.y)).x;
        _screenRight = _mainCamera.ScreenToWorldPoint(new Vector3(Screen.width, 0, _mainCamera.transform.position.y)).x;
        _screenTop = _mainCamera.ScreenToWorldPoint(new Vector3(0, Screen.height, _mainCamera.transform.position.y)).z;
        _screenBottom = _mainCamera.ScreenToWorldPoint(new Vector3(0, 0, _mainCamera.transform.position.y)).z;
    }

    private void StartNextLevel()
    {
        // Number of asteroids to spawn based on the current game level
        var numberOfAsteroids = currentGameLevel * 5;

        for (var i = 0; i < numberOfAsteroids; i++)
        {
            var position = Vector3.zero;
            var edge = Random.Range(0, 4);

            switch (edge)
            {
                case 0: // Left edge
                    position = new Vector3(_screenLeft, 0f, Random.Range(_screenBottom, _screenTop));
                    break;
                case 1: // Right edge
                    position = new Vector3(_screenRight, 0f, Random.Range(_screenBottom, _screenTop));
                    break;
                case 2: // Top edge
                    position = new Vector3(Random.Range(_screenLeft, _screenRight), 0f, _screenTop);
                    break;
                case 3: // Bottom edge
                    position = new Vector3(Random.Range(_screenLeft, _screenRight), 0f, _screenBottom);
                    break;
            }

            Instantiate(asteroidPrefab, position, Quaternion.identity);

            Debug.Log("Asteroid spawned at " + position);
        }
    }
}