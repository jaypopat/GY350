using UnityEngine;

public class HealthPotionCollectible : Collectible
{
    [SerializeField] private int healAmount = 20;
    
    public override void Collect(GameObject player)
    {
        var playerController = player.GetComponent<PlayerController>();
        if (playerController == null) return;

        var currentHealth = playerController.currentHealth;
        var maxHealth = playerController.maxHealth;

        // calculating the actual heal amount
        var actualHealAmount = Mathf.Min(healAmount, maxHealth - currentHealth);

        // applying the heal
        playerController.currentHealth = Mathf.Min(currentHealth + actualHealAmount, maxHealth);

        // Report the actual heal amount
        UIManager.Instance.characterHealed(player, actualHealAmount);
        AudioManager.Instance.PlaySFX("shield");
    }
}