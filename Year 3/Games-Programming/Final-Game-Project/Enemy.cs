using System.Collections;
using UnityEngine;

public class Enemy : MonoBehaviour
{
    [Header("Enemy Stats")]
    public float moveSpeed = 2f;
    public float detectionRange = 5f;
    public int damage = 10;
    public float attackRange = 1.5f;
    public float attackCooldown = 2f;
    public bool isFlying = false;
    public int maxHealth = 100;
    public int currentHealth;

    private Transform player;
    private Animator animator;
    private float lastAttackTime;
    public bool isDead = false;
    private SpriteRenderer spriteRenderer;

    private void Start()
    {
        InitializeEnemy();
    }

    private void Update()
    {
        if (isDead) return;

        float distanceToPlayer = Vector2.Distance(transform.position, player.position);

        if (distanceToPlayer <= detectionRange)
        {
            if (distanceToPlayer <= attackRange && Time.time >= lastAttackTime + attackCooldown)
            {
                Attack();
            }
            else
            {
                MoveTowardsPlayer();
            }
        }
        else
        {
            if (!isFlying)
            {
                animator.SetBool("isMoving", false);
            }
        }
    }
    private void InitializeEnemy()
    {
        player = GameObject.FindGameObjectWithTag("Player").transform;
        animator = GetComponent<Animator>();
        spriteRenderer = GetComponent<SpriteRenderer>();
        currentHealth = maxHealth;
    }
    public void ResetEnemy()
    {
        InitializeEnemy();
        currentHealth = maxHealth;
        isDead = false;
        
        GetComponent<Collider2D>().enabled = true;
        enabled = true;
        if (animator != null)
        {
            animator.Rebind(); // resetting the animator parameters to the default state
            animator.Update(0f); // updates immediately to this new state set above
        }
        lastAttackTime = 0f;
        if (!isFlying)
        {
            animator.SetBool("isMoving", false);
        }
    }


    void MoveTowardsPlayer()
    {
        Vector2 direction = (player.position - transform.position).normalized;
        transform.Translate(direction * (moveSpeed * Time.deltaTime));

        if (!isFlying) // ismoving is only done for the goblin as the flight eye is always flying
        {
            animator.SetBool("isMoving", true);
        }

        // flipping sprite based on movement direction
        spriteRenderer.flipX = direction.x < 0;
    }

    private void Attack()
    {
        lastAttackTime = Time.time;
        animator.SetTrigger("Attack");
    
        // Check for player in attack range and deal damage
        Collider2D playerCollider = Physics2D.OverlapCircle(transform.position, attackRange, LayerMask.GetMask("Player"));
        if (playerCollider != null)
        {
            PlayerController player = playerCollider.GetComponent<PlayerController>();
            if (player != null)
            {
                player.TakeDamage(damage);
            }
        }
    }


    public void TakeDamage(int damage)
    {
        Debug.Log($"Enemy took {damage} damage");
        if (isDead) return;

        currentHealth -= damage;
        animator.SetTrigger("TakeHit");

        if (currentHealth <= 0)
        {
            GameManager.Instance.enemiesKilled++;
            GameManager.Instance.AddScore(10);
            isDead = true;
            animator.SetTrigger("Death");
            GetComponent<Collider2D>().enabled = false;
            enabled = false;
            //Destroy(gameObject, 1f);
            StartCoroutine(DeactivateAfterDelay(1f));
        }
    }
    private IEnumerator DeactivateAfterDelay(float delay)
    {
        yield return new WaitForSeconds(delay);
        var spawner = FindObjectOfType<EnemySpawner>();
        if (spawner != null)
        {
            spawner.RemoveFromActiveEnemies(gameObject);
        }
        ObjectPool.Instance.ReturnToPool(gameObject.name.Replace("(Clone)", ""), gameObject);
        // doing the above just so the pool keys match the game object names
    }

}
