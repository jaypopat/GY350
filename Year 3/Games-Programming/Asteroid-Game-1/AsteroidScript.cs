using UnityEngine;

public class AsteroidScript : MonoBehaviour
{
    private Camera _mainCamera;
    private Rigidbody _rb;

    private void Start()
    {
        
        // Adjusted scale for the asteroid
        transform.localScale = new Vector3(0.1f, 0.1f, 0.1f); 
        
        // Turn off gravity
        _rb = GetComponent<Rigidbody>();
        _rb.useGravity = false;

        // Set a random direction for the asteroid with adjusted speed
        var randomDirection = new Vector3(Random.Range(-1f, 1f), 0f, Random.Range(-1f, 1f)).normalized;
        var randomSpeed = Random.Range(5f, 15f); // Adjusted speed range
        _rb.AddForce(randomDirection * randomSpeed, ForceMode.Impulse);

        // Get the main camera
        _mainCamera = Camera.main;

       
        // Start checking if the asteroid is offscreen
        InvokeRepeating(nameof(CheckOffscreen), 0f, 0.2f); // Check every 0.2 seconds
    }

    private void CheckOffscreen()
        // using viewport point as it makes it easier to detect off screen occurences
    {
        var viewportPosition = _mainCamera.WorldToViewportPoint(transform.position);

        // If asteroid moves off one side of the screen, reposition it to the opposite side
        if (viewportPosition.x < 0) viewportPosition.x = 1;
        else if (viewportPosition.x > 1) viewportPosition.x = 0;

        if (viewportPosition.y < 0) viewportPosition.y = 1;
        else if (viewportPosition.y > 1) viewportPosition.y = 0;

        // Convert back to world coordinates
        transform.position = _mainCamera.ViewportToWorldPoint(viewportPosition);
    }
}