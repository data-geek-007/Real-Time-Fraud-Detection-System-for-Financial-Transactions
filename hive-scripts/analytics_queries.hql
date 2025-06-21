-- Daily fraud rate
SELECT dt, COUNT(*) as total_transactions,
       SUM(CASE WHEN is_fraud THEN 1 ELSE 0 END) as fraud_count
FROM fraud_transactions
GROUP BY dt;

-- High-risk users
SELECT user_id, COUNT(*) as fraud_attempts
FROM fraud_transactions
WHERE is_fraud = TRUE
GROUP BY user_id
HAVING COUNT(*) > 3;
