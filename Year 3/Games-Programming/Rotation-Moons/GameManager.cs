using UnityEngine;

public class GameManager : MonoBehaviour {
    // References to Mars and its moons
    public GameObject mars;
    public GameObject deimos;
    public GameObject phobos;

    // Rotation speeds for moons and camera
    public float moonRotationSpeed = 10f;
    public float cameraRotationSpeed = 10f;
    public Vector3 cameraStartPosition = new Vector3(0f, 0f, 200f);

    private Camera _mainCamera; // Main camera reference

    void Start() {
        InitializeCamera(); // Set up camera
        ApplyMarsRotation(); // Start Mars rotation
    }

    void Update() {
        RotateMoons();          // Rotate moons each frame
        HandleCameraRotation(); // Handle camera input
        _mainCamera.transform.LookAt(mars.transform); // Focus camera on Mars
    }

    private void InitializeCamera() {
        _mainCamera = Camera.main; // Get main camera
        _mainCamera.transform.position = cameraStartPosition; // Set initial position
        _mainCamera.transform.LookAt(mars.transform); // Look at Mars
    }

    private void ApplyMarsRotation() {
        Rigidbody marsRigidbody = mars.GetComponent<Rigidbody>();
        marsRigidbody.AddTorque(new Vector3(0f, 10f, 0f)); // Apply rotation to Mars
    }

    private void RotateMoons() {
        RotateAroundMars(deimos); // Rotate Deimos
        RotateAroundMars(phobos);  // Rotate Phobos     
    }           

    private void RotateAroundMars(GameObject moon) {
        moon.transform.RotateAround(mars.transform.position, Vector3.up, moonRotationSpeed * Time.deltaTime);
    }

    private void HandleCameraRotation() {
        // Rotate camera based on user input
        if (Input.GetKey(KeyCode.RightArrow)) {
            _mainCamera.transform.RotateAround(mars.transform.position, Vector3.up, cameraRotationSpeed * Time.deltaTime);
        }
        if (Input.GetKey(KeyCode.LeftArrow)) {
            _mainCamera.transform.RotateAround(mars.transform.position, Vector3.down, cameraRotationSpeed * Time.deltaTime);
        }
        if (Input.GetKey(KeyCode.UpArrow)) {
            _mainCamera.transform.RotateAround(mars.transform.position, Vector3.right, cameraRotationSpeed * Time.deltaTime);
        }
        if (Input.GetKey(KeyCode.DownArrow)) {
            _mainCamera.transform.RotateAround(mars.transform.position, Vector3.left, cameraRotationSpeed * Time.deltaTime);
        }
    }
}
