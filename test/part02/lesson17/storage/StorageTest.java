package part02.lesson17.storage;

import org.junit.jupiter.api.*;
import part02.lesson17.util.ConnectorDB;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static part02.lesson17.storage.Storage.*;

class StorageTest {

    private static Connection connection;

    @BeforeAll
    public static void initConnection() throws SQLException, ClassNotFoundException {
        connection = ConnectorDB.getConnection();
    }

    /**
     * Тест на соединение с БД
     *
     * @throws SQLException
     */
    @Test
    public void testConnection() throws SQLException {
        Assertions.assertNotNull(connection);
        assertTrue(connection.isValid(0));
    }

    /**
     * Тест на пакетное добавление записей.
     */
    @Test
    public void testAddBatch() throws SQLException {
        int expected = 10;
        int actual = addBatch(connection);
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Тест на параметризированную вставку записей в БД
     */
    @Test
    public void testAddStatement() {
        boolean actual = addStatement(connection);
        Assertions.assertEquals(false, actual);
    }

    /**
     * Тест на параметризированную выборку записей из БД
     */
    @Test
    public void testGetQuery() {
        String SQL_SELECT_FROM_USER = "SELECT * FROM user WHERE login_id=? AND name=?";
        int actual = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_FROM_USER)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "Sergey");
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.last()) {
                actual = rs.getRow();
            }
            int expected = getQuery(connection);
            Assertions.assertEquals(actual, expected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Тест на проверку метода для добавления транзакции с точкой сохранения. Пытаемся добавить 2 записи,
     * после первой транзакции создаем точку сохранения. Во второй транзации намеренно вводим неверные параметры.
     * Записываем только первую транзакцию
     *
     * @throws SQLException
     */
    @Test
    public void testAddManualTransaction() throws SQLException {
        String SQL_SELECT_FROM_USER = "SELECT * FROM user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SQL_SELECT_FROM_USER);
        resultSet.last();
        int rowCountBeforeTransaction = resultSet.getRow();
        addManualTransaction(connection);
        ResultSet resultSet2 = statement.executeQuery(SQL_SELECT_FROM_USER);
        resultSet2.last();
        int rowCountAfterTransaction = resultSet2.getRow();
        int result = rowCountAfterTransaction - rowCountBeforeTransaction;
        Assertions.assertEquals(1, result);
    }

    @AfterAll
    public static void testCloseConnection() throws SQLException {
        connection.close();
        assertTrue(connection.isClosed());
        Assertions.assertFalse(connection.isValid(0));
    }
}