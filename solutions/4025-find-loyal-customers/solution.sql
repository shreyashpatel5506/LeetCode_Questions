# Write your MySQL query statement below
select customer_id
from customer_transactions
group by customer_id 
having count(transaction_id) >= 3 and
datediff(max(transaction_date) , min(transaction_date)) >= 30 and
(count(case when transaction_type= 'refund' then transaction_id end)*1.0 / count(transaction_id)) <0.20;
