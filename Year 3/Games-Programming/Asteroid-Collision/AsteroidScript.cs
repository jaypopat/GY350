    using System.Collections;
    using System.Collections.Generic;
    using UnityEngine;

    public class AsteroidScript : MonoBehaviour
    {
        public GameObject mars;
        public float forceMagnitude = 30f;
        public float boundaryOffset = 300f;
        private Rigidbody asteroidRigidbody;

        void Start()
        {
            asteroidRigidbody = GetComponent<Rigidbody>();
            
            // Set asteroid spawn position relative to Mars, with some randomness
            Vector3 randomSpawnPosition = new Vector3(
                Random.Range(-250f, -100f), 
                Random.Range(-250f, 250f), 
                mars.transform.position.z
            );
            transform.position = randomSpawnPosition;

            // Calculate direction towards Mars and apply initial impulse force
            Vector3 directionToMars = (mars.transform.position - transform.position).normalized;
            asteroidRigidbody.AddForce(directionToMars * forceMagnitude, ForceMode.Impulse);
        }

        void Update()
        {
            // Check if the asteroid is off screen
            if (IsOffScreen())
            {
                Destroy(gameObject);
            }
        }

        bool IsOffScreen()
        {
            // Get the screen position of the asteroid
            Vector3 screenPos = Camera.main.WorldToScreenPoint(transform.position);

            // Check if it's out of bounds with the specified offset
            return screenPos.x < -boundaryOffset || screenPos.x > Screen.width + boundaryOffset ||
                   screenPos.y < -boundaryOffset || screenPos.y > Screen.height + boundaryOffset;
        }

        void OnTriggerEnter(Collider other)
        {
            if (other.gameObject.name == "mars1")
            {
                Destroy(gameObject);
            }
        }
    }
