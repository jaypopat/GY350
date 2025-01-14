using UnityEngine;

public class CoinCollectible : Collectible
{
    [SerializeField] private int value = 10;
    
    public override void Collect(GameObject player)
    {
        GameManager.Instance.AddCoins(value);
        UIManager.Instance.coinsCollected(player, value);
        AudioManager.Instance.PlaySFX("coins");
    }
}