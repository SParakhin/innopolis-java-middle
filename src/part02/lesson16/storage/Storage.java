package part02.lesson16.storage;

import org.apache.logging.log4j.Logger;

import java.sql.*;

import static org.apache.logging.log4j.LogManager.getLogger;

public class Storage {

    private static final String SQL_INSERT_INTO_USER = "INSERT INTO user (name,birthday,login_id,city,email) VALUES (?,?,?,?,?) ";
    private static final String SQL_SELECT_FROM_USER = "SELECT * FROM user WHERE login_id=? AND name=?";
    private static final String SQL_SELECT_FROM_USER_ROLE = "INSERT INTO user_role (user_id,role_id) VALUES (?,?)";
    private final static Logger loggerDB = getLogger("JDBCAppender");
    private final static Logger loggerConsole = getLogger("STDOUT");

    /**
     * Метод групповой записи данных(INSERT) в таблицу использованием batch процесс
     *
     * @param connection
     * @throws SQLException
     */

    public static void addBatch(Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_USER)) {
            for (int i = 0; i < 10; i++) {
                preparedStatement.setString(1, "Sergey" + i);
                preparedStatement.setString(2, "09.01.1975" + i);
                preparedStatement.setInt(3, 1);
                preparedStatement.setString(4, "Orel" + i);
                preparedStatement.setString(5, "ormts@mail.ru" + i);
                preparedStatement.addBatch();
            }
            int[] i = preparedStatement.executeBatch();
            loggerConsole.debug("Добавлено " + i.length + " записей в таблицу user");
        } catch (SQLException e) {
            loggerDB.error("Ошибка addButch", e);
        }
    }

    /**
     * Метод записи данных в таблице с использованием параметризированного запроса
     *
     * @param connection
     * @throws SQLException
     */

    public static void addStatement(Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_USER)) {
            preparedStatement.setString(1, "Sergey");
            preparedStatement.setString(2, "09.01.1975");
            preparedStatement.setInt(3, 1);
            preparedStatement.setString(4, "Orel");
            preparedStatement.setString(5, "ormts@mail.ru");
            preparedStatement.execute();
            loggerConsole.debug("Успешная транзакция");
        } catch (SQLException e) {
            loggerDB.error("Ошибка транзакции", e);
        }
    }

    /**
     * Метод  параметризированной выборки по login_ID и name одновременно
     *
     * @param connection
     * @throws SQLException
     */
    public static int getQuery(Connection connection) {
        int numberOfRecordsSelect = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_FROM_USER)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "Sergey");
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.last()) {
                numberOfRecordsSelect = rs.getRow();
            }
            loggerConsole.debug("Результат запроса: " + numberOfRecordsSelect);
            loggerDB.info("Запрос выполнен успешно - лог в базу");
            return numberOfRecordsSelect;
        } catch (SQLException e) {
            loggerDB.error("Ошибка транзакции", e);
        }
        return 0;
    }

    /**
     * Метод ручного управления транзакциями
     * Между sql операциями установить точку сохранения (SAVEPOINT A),
     * намеренно ввести некорректные данные на последней операции, что бы транзакция
     * откатилась к логической точке SAVEPOINT A
     *
     * @param connection
     * @throws SQLException
     */
    public static void addManualTransaction(Connection connection) throws SQLException {
        Savepoint savepoint = null;
        connection.setAutoCommit(false);
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_USER)) {
            preparedStatement.setString(1, "Ivan");
            preparedStatement.setString(2, "09.01.1990");
            preparedStatement.setInt(3, 1);
            preparedStatement.setString(4, "Orel");
            preparedStatement.setString(5, "xxx@mail.ru");
            preparedStatement.execute();
            savepoint = connection.setSavepoint("А");
            preparedStatement.setString(1, "Dima");
            preparedStatement.setString(2, "09.12.1989");
            preparedStatement.setString(3, "234132421341");//неверные параметры
            preparedStatement.setString(4, "Moscow");
            preparedStatement.setString(5, "yyy@mail.ru");
            preparedStatement.execute();
            connection.commit();
            loggerConsole.debug("Успешная транзакция");
        } catch (SQLException e) {
            loggerDB.error("Откат транзакции к точке сохранения - лог запись в базу исключения", e);
            connection.rollback(savepoint);
            connection.commit();
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_FROM_USER_ROLE)) {
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, 2);
            preparedStatement.addBatch();
            int[] j = preparedStatement.executeBatch();
            connection.commit();
            loggerConsole.info("Успешно");
        } catch (SQLException e) {
            connection.rollback();
            loggerDB.error("Отмена транзакции - запись в базу");
        }
        connection.setAutoCommit(true);
    }
}