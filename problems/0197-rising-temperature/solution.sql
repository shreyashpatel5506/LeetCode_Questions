# Write your MySQL query statement below
# Write your MySQL query statement below
select  w1.id from weather w1 left join weather w2 on datediff(w1.recordDate,w2.recordDate)=1 where w2.temperature is not null and w1.temperature > w2.temperature