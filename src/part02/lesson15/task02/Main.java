/**
 * ДЗ_13
 * 1)    Спроектировать базу
 * -      Таблица USER содержит поля id, name, birthday, login_ID, city, email, description
 * -      Таблица ROLE содержит поля id, name (принимает значения Administration, Clients, Billing), description
 * -      Таблица USER_ROLE содержит поля id, user_id, role_id
 * Типы полей на ваше усмотрению, возможно использование VARCHAR(255)
 * 2)      Через jdbc интерфейс сделать запись данных(INSERT) в таблицу
 * a)      Используя параметризированный запрос
 * b)      Используя batch процесс
 * 3)      Сделать параметризированную выборку по login_ID и name одновременно
 * 4)      Перевести connection в ручное управление транзакциями
 * a)      Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE) между sql операциями установить логическую точку сохранения(SAVEPOINT)
 * б)   Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE) между sql операциями установить точку сохранения (SAVEPOINT A), намеренно ввести некорректные данные на последней операции, что бы транзакция откатилась к логической точке SAVEPOINT A
 */
package part02.lesson15.task02;

import part02.lesson15.task02.util.ConnectorDB;
import java.sql.Connection;
import java.sql.SQLException;
import static part02.lesson15.task02.dao.UserDAO.*;

public class Main {

    public static void main(String[] args) {

        try (Connection connection = ConnectorDB.getConnection()) {
            addStatement(connection);
            addBatch(connection);
            System.out.println("===Результирующий набор по ID и name");
            getQuery(connection);
            addManualTransaction(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
