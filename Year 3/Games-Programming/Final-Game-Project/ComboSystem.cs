/*using System;
using UnityEngine;

public class ComboSystem : MonoBehaviour
{
    public ComboMove[] availableCombos;
    public float maxComboTime = 0.5f;
    public int maxComboMultiplier = 4;
    public float comboRadius = 2f;

    public LayerMask enemyLayer;
    private int currentCombo;
    private readonly KeyCode[] currentInputs = new KeyCode[4];
    private int inputCount;
    private float lastInputTime;
    
    private void Update()
    {
        // TODO - pefrom input key filtering
    }

    private void HandleInput(KeyCode key)
    {
        if (inputCount < currentInputs.Length)
        {
            currentInputs[inputCount] = key;
            inputCount++;
            lastInputTime = Time.time;

            CheckForCombo();
        }
    }

    private void CheckForCombo()
    {
        for (combo in availableCombos)
            if (IsComboMatch(combo))
            {
                ExecuteCombo(combo);
                ResetCombo();
                break;
            }
    }

    private bool IsComboMatch()
    {
        // todo
    }

    private void ExecuteCombo(ComboMove combo)
    {
        var damage = Mathf.RoundToInt(combo.damage * currentCombo);
        DealDamageToNearbyEnemies(damage);

        currentCombo = Mathf.Min(currentCombo + 1, maxComboMultiplier);
    }

    private void DealDamageToNearbyEnemies(int damage)
    {
        var hitEnemies = Physics2D.OverlapCircleAll(transform.position, comboRadius, enemyLayer);
        foreach (var enemy in hitEnemies)
        {
            var enemyObj = enemy.GetComponent<Enemy>();
            if (enemyObj != null) enemyObj.TakeDamage(damage);
        }
    }

    private void ResetCombo()
    {
        inputCount = 0;
        currentCombo = 0;
    }
    
    public class ComboMove
    {
        public string name;
        public KeyCode[] sequence;
        public float damage;
    }
}
*/