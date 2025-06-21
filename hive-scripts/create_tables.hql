-- Fraud transactions table (partitioned by date)
CREATE EXTERNAL TABLE IF NOT EXISTS fraud_transactions (
  transaction_id STRING,
  user_id STRING,
  amount DOUBLE,
  is_fraud BOOLEAN,
  risk_score DOUBLE
)
PARTITIONED BY (dt STRING)
STORED AS PARQUET
LOCATION '/data/fraud_output';

-- Merchant risk profile
CREATE TABLE merchant_risk_profiles AS
SELECT merchant, AVG(risk_score) as avg_risk
FROM fraud_transactions
GROUP BY merchant;
