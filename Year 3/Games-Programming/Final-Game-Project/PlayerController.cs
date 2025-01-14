using UnityEngine;
using System.Collections;

public class PlayerController : MonoBehaviour


{
    private GameManager gameManager;
    [Header("Movement speed and force")]
    public float moveSpeed = 5f;
    public float jumpForce = 10f;
    
    public LayerMask groundLayer;
    
    [Header("Combat etc")]
    public float attackRange = 0.5f;
    public int attackDamage = 20;
    public float invulnerabilityTime = 1f;
    
    [Header("Player Stats")]
    public int maxHealth = 100;
    public int currentHealth;

    // Private fields
    private Rigidbody2D rb;
    private Animator animator;
    private bool isGrounded;
    private bool isWallSliding;
    private bool isDashing;
    private int jumpCount;
    private float moveHorizontal;
    private bool isFacingRight = true;
    private bool isAttacking;
    private float dashTimeLeft;
    private float lastDashTime;
    private float invulnerabilityTimeLeft;
    private bool isRunning;
    
    
    private bool _canDoubleJump = false;
// couldve gated these using skilltree - abilities such as wall climb etc etc
// which can be unlocked using coins 

    
    
    
    [SerializeField] private float groundCheckRadius = 0.2f;
    [SerializeField] public int maxJumps = 2;

    
    [Header("Combat")]
    private int currentCombo;
    private float lastAttackTime;
    private float comboResetTime = 1.5f;

    
    void Start()
    {
        gameManager = GameManager.Instance;
        rb = GetComponent<Rigidbody2D>();
        animator = GetComponent<Animator>();
        currentHealth = maxHealth;
    }

    private void Update()
    {
        if (!isAttacking)
        {
            HandleMovement();
            HandleCombat();
        }
    
        UpdateAnimations();
        UpdateInvulnerability();
        CheckGround();
    }


    private void CheckGround()
    {
        RaycastHit2D hit = Physics2D.Raycast(transform.position, Vector2.down, 10f, groundLayer);
        isGrounded = hit.collider != null;
    
        if (isGrounded)
        {
            jumpCount = 0;
        }
    }


    private void UpdateInvulnerability()
    {
        if (invulnerabilityTimeLeft > 0)
        {
            invulnerabilityTimeLeft -= Time.deltaTime;
        }
    }

    private void HandleMovement()
    {
        moveHorizontal = Input.GetAxisRaw("Horizontal");
        isRunning = Mathf.Abs(moveHorizontal) > 0;
        float currentSpeed = moveSpeed;

        if (!isDashing)
        {
            rb.velocity = new Vector2(moveHorizontal * currentSpeed, rb.velocity.y);
        }

        if (Input.GetButtonDown("Jump"))
        {
            if (isGrounded || (jumpCount < maxJumps))
            {
                Jump();
            }
        }


        FlipCharacter();
    }

    private void Jump()
    {
        rb.velocity = new Vector2(rb.velocity.x, 0f);
        rb.AddForce(Vector2.up * jumpForce, ForceMode2D.Impulse);
        animator.SetTrigger("Jump");
        jumpCount++;
    }
    private void FlipCharacter()
    {
        if (moveHorizontal > 0 && !isFacingRight || moveHorizontal < 0 && isFacingRight)
        {
            isFacingRight = !isFacingRight;
            transform.localScale = new Vector3(-transform.localScale.x, transform.localScale.y, transform.localScale.z);
        }
    }

    public void TakeDamage(int damage)
    {
        Debug.Log($"Player took {damage} damage");
        if (invulnerabilityTimeLeft > 0) return;

        currentHealth -= damage;
        invulnerabilityTimeLeft = invulnerabilityTime;
        animator.SetTrigger("Hurt");
        UIManager.Instance.CharacterTookDamage(gameObject, damage);
        Debug.Log("Player health: " + currentHealth);

        StartCoroutine(FlashSprite());
        
        if (currentHealth <= 0)
        {
            Die();
        }
    }

    private void Die()
    {
        animator.SetTrigger("Death");
        rb.velocity = Vector2.zero;
        rb.isKinematic = true;
        GetComponent<Collider2D>().enabled = false;
        StartCoroutine(HandleDeath());
    }

    private IEnumerator HandleDeath()
    {
        yield return new WaitForSeconds(animator.GetCurrentAnimatorStateInfo(0).length);
        gameObject.SetActive(false);
        gameManager.GameOver();
    }


    private void UpdateAnimations()
    {
        animator.SetInteger("AnimState", isRunning ? 1 : 0);
        animator.SetFloat("AirSpeedY", rb.velocity.y);
        animator.SetBool("isGrounded", isGrounded);
        animator.SetBool("isWallSliding", isWallSliding);
        animator.SetBool("isRunning",isRunning);
    }

    private IEnumerator FlashSprite()
    {
        SpriteRenderer sprite = GetComponent<SpriteRenderer>();
        Color originalColor = sprite.color;
        float flashDuration = invulnerabilityTime;
        float elapsed = 0;
    
        while (elapsed < flashDuration)
        {
            sprite.color = new Color(1, 1, 1, 0.5f);
            yield return new WaitForSeconds(0.1f);
            sprite.color = originalColor;
            yield return new WaitForSeconds(0.1f);
            elapsed += 0.2f;
        }
        sprite.color = originalColor;
    }

    
    private void HandleCombat()
    {
        if (Input.GetMouseButtonDown(0) && !isAttacking)
        {
            ExecuteAttack();
        }

        if (Input.GetMouseButtonDown(1))
        {
            animator.SetTrigger("Block");
            animator.SetBool("IdleBlock", true);
        }
        else if (Input.GetMouseButtonUp(1))
        {
            animator.SetBool("IdleBlock", false);
        }

        if (Input.GetKeyDown(KeyCode.Space) && !isAttacking && !isWallSliding)
        {
            animator.SetTrigger("Jump");
        }
    }


    private void ExecuteAttack()
    {
        if (Time.time - lastAttackTime > comboResetTime)
        {
            currentCombo = 0;
        }

        currentCombo++;
        if (currentCombo > 3)
            currentCombo = 1;

        animator.SetTrigger($"Attack{currentCombo}");
        AudioManager.Instance.PlaySFX("sword");
    
        Vector2 attackPosition = transform.position;

        if (transform.localScale.x < 0) // Facing left
        {
            attackPosition -= Vector2.right * attackRange; // Offset left because facing left
        }
        else
        {
            attackPosition += Vector2.right * attackRange; // Offset right because facing right
        }

        var hitEnemies = Physics2D.OverlapCircleAll(
                attackPosition, 
                attackRange, 
                LayerMask.GetMask("Enemy")
            );

        
        Debug.Log("hitting enemies " + hitEnemies.Length);

        foreach (var enemy in hitEnemies)
        {
            var enemyObj = enemy.GetComponent<Enemy>();
            if (enemyObj != null)
            {
                enemyObj.TakeDamage(attackDamage);
                Debug.Log("dealing damage to" + enemyObj.name);
            }
        }

        lastAttackTime = Time.time;
        StartCoroutine(ResetAttackFlag());
    }

    private IEnumerator ResetAttackFlag()
    {
        yield return new WaitForSeconds(0.4f);
        isAttacking = false;
    }
}
