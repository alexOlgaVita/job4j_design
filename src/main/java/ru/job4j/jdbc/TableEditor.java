package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        connection = DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("login"),
                properties.getProperty("password"));
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s();",
                tableName
        );
        doExecute(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "DROP TABLE %s;",
                tableName
        );
        doExecute(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN %s %s;",
                tableName, columnName, type
        );
        doExecute(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s;",
                tableName, columnName
        );
        doExecute(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName, columnName, newColumnName
        );
        doExecute(sql);
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select exists (select * from information_schema.tables where table_name = '%s' and table_schema = 'public')::int as \"column\";",
                    tableName));
            boolean tableExist = false;
            while (selection.next()) {
                tableExist = selection.getInt("column") != 0;
            }
            if (!tableExist) {
                buffer = new StringJoiner("");
                buffer.add("Таблицы не существует");
            } else {
                selection = statement.executeQuery(String.format(
                        "SELECT * FROM %s LIMIT 1", tableName
                ));
                var metaData = selection.getMetaData();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    buffer.add(String.format("%-15s|%-15s%n",
                            metaData.getColumnName(i), metaData.getColumnTypeName(i))
                    );
                }
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    private void doExecute(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (TableEditor te = new TableEditor(properties)) {
            te.initConnection();
            DatabaseMetaData metaData = te.connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
            String tableName = "test_db";
            String columnName = "column1";
            String newColumnName = "column2";
            String type = "boolean";
            te.createTable(tableName);
            System.out.println(te.getTableScheme(tableName));
            te.addColumn(tableName, columnName, type);
            System.out.println(te.getTableScheme(tableName));
            te.renameColumn(tableName, columnName, newColumnName);
            System.out.println(te.getTableScheme(tableName));
            te.dropColumn(tableName, newColumnName);
            System.out.println(te.getTableScheme(tableName));
            te.dropTable(tableName);
            System.out.println(te.getTableScheme(tableName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
