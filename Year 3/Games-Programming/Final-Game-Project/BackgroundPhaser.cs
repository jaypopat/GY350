using UnityEngine;

public class BackgroundPhaser : MonoBehaviour
{
    public float parallaxSpeed = 0.5f;
    private float startPositionX;
    private float spriteWidth;
    private Camera mainCamera;

    private void Start()
    {
        startPositionX = transform.position.x;
        spriteWidth = GetComponent<SpriteRenderer>().bounds.size.x;
        mainCamera = Camera.main;
    }

    private void Update()
    {
        var distanceToMove = mainCamera.transform.position.x * parallaxSpeed;
        transform.position = new Vector3(startPositionX + distanceToMove, transform.position.y, transform.position.z);

        var relativeDistance = mainCamera.transform.position.x * (1 - parallaxSpeed);

        if (relativeDistance > startPositionX + spriteWidth)
        {
            startPositionX += spriteWidth;
        }
        else if (relativeDistance < startPositionX - spriteWidth)
        {
            startPositionX -= spriteWidth;
        }
    }
}