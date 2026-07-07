<h2><a href="https://leetcode.com/problems/combine-two-tables">175. Combine Two Tables</a></h2>

<p>Table: <code>Person</code></p>

<pre>+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| personId    | int     |
| lastName    | varchar |
| firstName   | varchar |
+-------------+---------+
personId is the primary key (column with unique values) for this table.
This table contains information about the ID of some persons and their first and last names.
</pre>

<p>&nbsp;</p>

<p>Table: <code>Address</code></p>

<pre>+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| addressId   | int     |
| personId    | int     |
| city        | varchar |
| state       | varchar |
+-------------+---------+
addressId is the primary key (column with unique values) for this table.
Each row of this table contains information about the city and state of one person with ID = PersonId.
</pre>

<p>&nbsp;</p>

<p>Write a solution to report the first name, last name, city, and state of each person in the <code>Person</code> table. If the address of a <code>personId</code> is not present in the <code>Address</code> table, report <code>null</code> instead.</p>

<p>Return the result table in <strong>any order</strong>.</p>

<p>The result format is in the following example.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre><strong>Input:</strong> 
Person table:
+----------+----------+-----------+
| personId | lastName | firstName |
+----------+----------+-----------+
| 1        | Wang     | Allen     |
| 2        | Alice    | Bob       |
+----------+----------+-----------+
Address table:
+-----------+----------+---------------+------------+
| addressId | personId | city          | state      |
+-----------+----------+---------------+------------+
| 1         | 2        | New York City | New York   |
| 2         | 3        | Leetcode      | California |
+-----------+----------+---------------+------------+
<strong>Output:</strong> 
+-----------+----------+---------------+----------+
| firstName | lastName | city          | state    |
+-----------+----------+---------------+----------+
| Allen     | Wang     | Null          | Null     |
| Bob       | Alice    | New York City | New York |
+-----------+----------+---------------+----------+
<strong>Explanation:</strong> 
There is no address in the address table for the personId = 1 so we return null in their city and state.
addressId = 1 contains information about the address of personId = 2.
</pre>


---

# 🛍️ Combine-Two-Tables | Explained

## Approach 1 (SQL Left Join)
### Intuition
The intuition behind this approach is to combine rows from two tables (`Person` and `Address`) based on a common column (`personId`). This is similar to how you would merge two spreadsheets in a real-world scenario, where you have one spreadsheet with customer information and another with their corresponding addresses. By joining these two tables, you can create a new table that contains all the relevant information for each customer.

### Approach
The high-level logic flow for this approach is as follows:
1. Identify the common column between the two tables (`personId`).
2. Use a SQL `LEFT JOIN` to combine rows from both tables based on the common column.
3. Select the desired columns from the combined table.

### Detailed Code Analysis
Let's break down the code block:
- `SELECT p.firstName, p.lastName, a.city, a.state`: This line specifies the columns that we want to select from the combined table. The `p` and `a` are aliases for the `Person` and `Address` tables, respectively.
- `FROM Person p`: This line specifies the first table (`Person`) and assigns it the alias `p`.
- `LEFT JOIN Address a`: This line specifies the second table (`Address`) and assigns it the alias `a`. The `LEFT JOIN` clause is used to combine rows from both tables based on the common column.
- `ON p.personId = a.personId`: This line specifies the condition for joining the two tables. In this case, we're joining the tables based on the `personId` column.

### Code
```sql
SELECT p.firstName, p.lastName, a.city, a.state
FROM Person p
LEFT JOIN Address a
ON p.personId = a.personId;
```

### Complexity
- Time: The time complexity of this approach is O(n), where n is the number of rows in the `Person` table. This is because the SQL engine needs to iterate over each row in the `Person` table to perform the join.
- Space: The space complexity of this approach is O(n), where n is the number of rows in the combined table. This is because the SQL engine needs to store the combined table in memory.

## 🕵️‍♂️ Follow-up Questions (Optional)
Some common follow-up questions for this pattern include:
- What if there are multiple addresses for a single person? How would you modify the query to handle this scenario?
  - To handle this scenario, you would need to modify the query to use a `GROUP BY` clause to group the results by person, and then use an aggregate function (such as `GROUP_CONCAT`) to concatenate the multiple addresses into a single string.
- What if the `personId` column is not present in one of the tables? How would you modify the query to handle this scenario?
  - To handle this scenario, you would need to modify the query to use a different join type (such as a `FULL OUTER JOIN`) or to use a subquery to first retrieve the relevant data from the table that does not contain the `personId` column.