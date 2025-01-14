/*
using System;
using System.Collections;
using UnityEngine;

public class PowerUpSystem : MonoBehaviour
{
    public PowerUpEffect[] availablePowerUps;

    private bool hasFireTrail;
    private bool isGiant;
    private bool isTimeSlowed;

    private PlayerController player;

    private void Start()
    {
        player = GetComponent<PlayerController>();
    }

    public void ActivatePowerUp(string powerUpType)
    {
        switch (powerUpType)
        {
    
            case "Giant":
                StartCoroutine(GiantPowerUp());
                break;
            case "GhostMode":
                StartCoroutine(GhostModePowerUp());
                break;
        }
    }

    private IEnumerator GiantPowerUp()
    {
        var originalScale = transform.localScale;
        transform.localScale *= 2f;
        player.jumpForce *= 1.5f;
        player.attackDamage *= 2;
        isGiant = true;

        yield return new WaitForSeconds(10f);

        transform.localScale = originalScale;
        player.jumpForce /= 1.5f;
        player.attackDamage /= 2;
        isGiant = false;
    }


    private IEnumerator GhostModePowerUp()
    {
        player.GetComponent<SpriteRenderer>().color = new Color(1f, 1f, 1f, 0.5f);
        Physics2D.IgnoreLayerCollision(gameObject.layer, LayerMask.NameToLayer("Enemy"), true);

        yield return new WaitForSeconds(5f);

        player.GetComponent<SpriteRenderer>().color = Color.white;
    }


    [Serializable]
    public class PowerUpEffect
    {
        public string name;
        public float duration;
        public float magnitude;
        public GameObject visualEffect;
    }
}*/