<h2><a href="https://leetcode.com/problems/duplicate-emails">182. Duplicate Emails</a></h2>

<p>Table: <code>Person</code></p>

<pre>+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| email       | varchar |
+-------------+---------+
id is the primary key (column with unique values) for this table.
Each row of this table contains an email. The emails will not contain uppercase letters.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report all the duplicate emails. Note that it's guaranteed that the email&nbsp;field is not NULL.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The&nbsp;result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre><strong>Input:</strong> 
Person table:
+----+---------+
| id | email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
<strong>Output:</strong> 
+---------+
| Email   |
+---------+
| a@b.com |
+---------+
<strong>Explanation:</strong> a@b.com is repeated two times.
</pre>


---

# 🛍️ Duplicate-Emails | Explained

## Approach 1: Using SQL Group By
### Intuition
The core idea behind this solution is to group all rows in the `Person` table by their `email` and then identify those groups that have more than one member. This is akin to organizing people into groups based on their email addresses and then looking for groups that have more than one person, indicating duplicate emails.

### Approach
1. **Select**: Choose the `email` column from the `Person` table.
2. **From**: Specify the `Person` table as the source of the data.
3. **Group By**: Organize the rows by their `email` values. This creates groups of rows that share the same email address.
4. **Having**: Filter these groups to only include those with more than one row (i.e., `Count(email) > 1`). This effectively identifies the email addresses that appear more than once in the table.

### Code
```sql
Select email as Email
From Person
Group by email
Having Count(email) > 1;
```

### Complexity
- Time: The time complexity of this SQL query depends on the database system's implementation, but it generally involves scanning the table and grouping the rows, which can be considered as O(n), where n is the number of rows in the `Person` table.
- Space: The space complexity is also dependent on the database system but can be considered as O(n) in the worst case, where all rows are stored in memory for the grouping operation.

## 🕵️‍♂️ Follow-up Questions (Optional)
1. **Q: How would you optimize this query if the `Person` table is extremely large?**
   A: To optimize the query for a very large table, consider creating an index on the `email` column. This can significantly speed up the grouping operation.
2. **Q: Can you write a similar query to find duplicate emails in a table where the email address can be in different cases (e.g., 'example@example.com' and 'Example@example.com' are considered the same)?**
   A: Yes, to treat emails in a case-insensitive manner, you could use a function like `LOWER()` or `UPPER()` on the `email` column in both the `GROUP BY` and `SELECT` clauses, like this: `Select LOWER(email) as Email From Person Group by LOWER(email) Having Count(LOWER(email)) > 1;`