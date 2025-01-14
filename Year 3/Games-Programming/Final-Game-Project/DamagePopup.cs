using UnityEngine;
using TMPro;

public class DamagePopup : MonoBehaviour
{
    [SerializeField] private Vector3 moveSpeed = new Vector3(0, 75, 0);
    [SerializeField] private float timeToFade = 1f;

    private RectTransform textTransform;
    private TextMeshProUGUI textMeshPro;
    private float timeElapsed;
    private Color startColor;

    private void Awake()
    {
        textTransform = GetComponent<RectTransform>();
        textMeshPro = GetComponent<TextMeshProUGUI>();
        startColor = textMeshPro.color;
    }

    private void Update()
    {
        // this is because the popup used to stay on screen after the game was over
        if (GameManager.Instance.currentState == GameManager.GameState.GameOver)
        {
            Destroy(gameObject);
            return;
        }

        Move();
        Fade();
    }


    private void Move()
    {
        textTransform.position += moveSpeed * Time.deltaTime;
    }

    private void Fade()
    {
        timeElapsed += Time.deltaTime;

        if (timeElapsed < timeToFade)
        {
            var fadeAlpha = Mathf.Lerp(startColor.a, 0, timeElapsed / timeToFade);
            textMeshPro.color = new Color(startColor.r, startColor.g, startColor.b, fadeAlpha);
        }
        else
        {
            Destroy(gameObject);
        }
    }
}