using UnityEngine;

public class PersistentCanvas : MonoBehaviour 
{
    private static PersistentCanvas Instance;

    private void Awake()
    {
        if (Instance == null)
        {
            Instance = this;
            DontDestroyOnLoad(gameObject);
        }
        else
        {
            Destroy(gameObject);
        }
    }
}