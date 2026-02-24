# Write your MySQL query statement below
WITH ConsecutiveGroups AS (
    SELECT 
        id, 
        visit_date, 
        people,
        -- Subtract row number from id to create a constant value for consecutive rows
        id - ROW_NUMBER() OVER(ORDER BY id) AS grp
    FROM Stadium
    WHERE people >= 100
),
GroupCounts AS (
    SELECT 
        *,
        -- Count how many rows are in each group
        COUNT(*) OVER(PARTITION BY grp) AS count_in_grp
    FROM ConsecutiveGroups
)
SELECT id, visit_date, people
FROM GroupCounts
WHERE count_in_grp >= 3
ORDER BY visit_date;