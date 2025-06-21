from datetime import datetime
from airflow import DAG
from airflow.operators.bash import BashOperator

default_args = {
    'owner': 'data_engineering',
    'depends_on_past': False,
    'start_date': datetime(2023, 1, 1)
}

dag = DAG(
    'fraud_detection_pipeline',
    default_args=default_args,
    schedule_interval='@hourly',
    catchup=False
)

run_spark_job = BashOperator(
    task_id='run_spark_streaming',
    bash_command='spark-submit --class FraudDetectionEngine /app/fraud-detection.jar',
    dag=dag
)

validate_hive = BashOperator(
    task_id='validate_hive_tables',
    bash_command='hive -f /scripts/validate_queries.hql',
    dag=dag
)

run_spark_job >> validate_hive
