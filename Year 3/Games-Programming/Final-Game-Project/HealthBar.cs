using TMPro;
using UnityEngine;
using UnityEngine.UI;

public class HealthBar : MonoBehaviour
{
    public Slider slider;
    public TMP_Text hp;
    private PlayerController player;

    private void Start()
    {
        if (slider == null)
        {
            slider = GetComponent<Slider>();
        }
    }

    private void Update()
    {
        if (player == null) // after game ends player dies hence we find on update()
        {
            player = FindObjectOfType<PlayerController>();
            if (player == null) return;
            slider.value = (float)player.currentHealth / player.maxHealth;
            if (hp != null)
            {
                hp.text = $"{player.currentHealth}/{player.maxHealth}";
            }
        }
        else
        {
            UpdateHealthDisplay();
        }
    }

    private void UpdateHealthDisplay()
    {
        if (player == null || slider == null) return;
    
        slider.value = (float)player.currentHealth / player.maxHealth;

        if (hp != null)
        {
            hp.text = $"{player.currentHealth}/{player.maxHealth}";
        }
    }
}