# Write your MySQL query statement b
select  max(salary) as SecondHighestSalary from Employee  where salary < (select max(salary) from Employee)