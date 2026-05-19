package org.greencodeinitiative.creedengo.java.checks.GCI75;

public class AvoidSQLRequestWithoutLimit {

    // === NONCOMPLIANT: SELECT without LIMIT ===

    public void selectWithoutLimit() {
        String sql1 = "SELECT user FROM myTable"; // Noncompliant {{Limit your Database Queries. You should use the "LIMIT" SQL clause.}}
        String sql2 = "SELECT id, name FROM users WHERE active = 1"; // Noncompliant {{Limit your Database Queries. You should use the "LIMIT" SQL clause.}}
        String sql3 = "select name from employees"; // Noncompliant {{Limit your Database Queries. You should use the "LIMIT" SQL clause.}}
        String sql4 = "SELECT * FROM orders WHERE status = 'pending'"; // Noncompliant {{Limit your Database Queries. You should use the "LIMIT" SQL clause.}}
        String sql5 = "SELECT u.name, u.email FROM users u JOIN orders o ON u.id = o.user_id"; // Noncompliant {{Limit your Database Queries. You should use the "LIMIT" SQL clause.}}
    }

    // === COMPLIANT: SELECT with LIMIT ===

    public void selectWithLimit() {
        String sql1 = "SELECT user FROM myTable LIMIT 50"; // Compliant
        String sql2 = "SELECT id, name FROM users WHERE active = 1 LIMIT 100"; // Compliant
        String sql3 = "select name from employees limit 10"; // Compliant
        String sql4 = "SELECT * FROM orders WHERE status = 'pending' LIMIT 20"; // Compliant
    }

    // === COMPLIANT: Not a SELECT query ===

    public void notSelectQueries() {
        String sql1 = "INSERT INTO users (name) VALUES ('test')"; // Compliant
        String sql2 = "UPDATE users SET name = 'test' WHERE id = 1"; // Compliant
        String sql3 = "DELETE FROM users WHERE id = 1"; // Compliant
        String sql4 = "CREATE TABLE test (id INT)"; // Compliant
    }

    // === COMPLIANT: Not SQL at all ===

    public void notSql() {
        String text1 = "Hello world"; // Compliant
        String text2 = "This is a regular string"; // Compliant
        String text3 = "from here to there"; // Compliant
    }
}

