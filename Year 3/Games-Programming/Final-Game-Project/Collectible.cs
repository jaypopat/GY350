using UnityEngine;

public abstract class Collectible : MonoBehaviour
{
    [SerializeField] private float bobHeight = 0.5f;
    [SerializeField] private float bobSpeed = 2f;
    
    private Vector2 startPos;
    
    protected virtual void Start()
    {
        startPos = transform.position;
    }

    private void Update()
    {
        // making the collectible bob up and down
        var newY = startPos.y + (Mathf.Sin(Time.time * bobSpeed) * bobHeight);
        transform.position = new Vector2(transform.position.x, newY);
    }
    
    private void OnTriggerEnter2D(Collider2D other)
    {
        if (other.CompareTag("Player"))
        {
            Debug.Log("Player detected in OnTriggerEnter2D");
            Collect(other.gameObject);
            Destroy(gameObject);
        }
        else
        {
            Debug.Log("Non-player object collided: " + other.name);
        }
    }

    public abstract void Collect(GameObject player);
}