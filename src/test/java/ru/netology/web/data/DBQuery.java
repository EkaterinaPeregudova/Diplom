package ru.netology.web.data;

import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBQuery {
    private final static QueryRunner runner = new QueryRunner();
    private final static Connection conn = getConnect();


    @SneakyThrows
    private static Connection getConnect() {

        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/app",
                "app",
                "pass"
        );
    }

    @SneakyThrows
    public static String getLastPaymentStatus() {
        val query = "SELECT status FROM payment_entity ORDER BY created DESC";
        return runner.query(conn, query, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getLastPaymentId() {
        val query = "SELECT transaction_id FROM payment_entity ORDER BY created DESC";
        return runner.query(conn, query, new ScalarHandler<>());
    }

    @SneakyThrows
    public static int getLastPaymentAmount() {
        val query = "SELECT amount FROM payment_entity ORDER BY created DESC";
        int amount = runner.query(conn, query, new ScalarHandler<>());
        return (amount / 100);
    }

    @SneakyThrows
    public static String getLastOrderPaymentId() {
        val query = "SELECT payment_id FROM order_entity ORDER BY created DESC";
        return runner.query(conn, query, new ScalarHandler<>());
    }

}
