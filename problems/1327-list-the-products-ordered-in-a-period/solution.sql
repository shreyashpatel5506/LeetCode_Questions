
SELECT p.product_name, SUM(o.unit) AS unit FROM  Products p JOIN Orders o ON    p.product_id = o.product_id  and  month(order_date)=2 and year(order_date)=2020  GROUP BY p.product_name having sum(o.unit) >= 100 

