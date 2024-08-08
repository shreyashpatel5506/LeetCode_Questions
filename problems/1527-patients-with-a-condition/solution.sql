# Write your MySQL query statement below
select * from Patients where conditions like 'DIAB1%' or conditions like '%DIAB1__%' and conditions not like 'SADIAB100';
/*select * from patients where conditions not like 'SADIAB100';*/