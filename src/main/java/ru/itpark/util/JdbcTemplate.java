package ru.itpark.util;

import ru.itpark.exception.DataAccesException;
import ru.itpark.exception.DataIdGenerationException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class JdbcTemplate {
    private static <T> T execute(String url, String sql, PreparedStatementExecutor<T> executor) {
        try (
                Connection connection = DriverManager.getConnection(url);
                PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {
            return executor.execute(preparedStatement);
        } catch (SQLException e) {
            throw new DataAccesException(e);
        }
    }

    public static <T> List<T> executeQuery(String url, String sql, PreparedStatementSetter setter, RowMapper<T> mapper) {
        return execute(url, sql, pstmt -> {
                    setter.set(pstmt);
                    try (ResultSet resultSet = pstmt.executeQuery()) {
                        List<T> result = new LinkedList<>();
                        while (resultSet.next()) {
                            result.add(mapper.map(resultSet));
                        }
                        return result;
                    }
                }
        );
    }

    public static void executeUpdate(String url, String sql, PreparedStatementSetter setter) {
        execute(url, sql, pstmt -> {
                    setter.set(pstmt);
                    pstmt.executeUpdate();
                    return null;
                }
        );
    }

    public static int executeUpdateAndGetID(String url, String sql, PreparedStatementSetter setter) {
        return execute(url, sql, pstmt -> {
            setter.set(pstmt);
            pstmt.executeUpdate();
            try (ResultSet keys = pstmt.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
            throw new DataIdGenerationException();
        });
    }
}
