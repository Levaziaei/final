package mft.model.repository;

import lombok.extern.log4j.Log4j;
import mft.model.entity.AccountStudent;
import mft.model.tools.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
@Log4j
public class AccountStudentRepository implements AutoCloseable {
    private static PreparedStatement preparedStatement;
    private static Connection connection;

    public AccountStudent save(AccountStudent accountStudent) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT ACCOUNTSTUDENT_seq.nextval AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        accountStudent.setId(resultSet.getInt("NEXT_ID"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO AccountStudent_tbl(ID, nameAndFamily,yourSuggestion) VALUES (?,?,?)"
        );
        preparedStatement.setInt(1, accountStudent.getId());
        preparedStatement.setString(2, String.valueOf(accountStudent.getNameAndFamily()));
        preparedStatement.setString(3, String.valueOf(accountStudent.getYourSuggestion()));
        preparedStatement.execute();
      log.info("save repository");
        return accountStudent;
    }

    public AccountStudent remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM accountstudent_tbl WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("remove");
        return null;
    }

    public AccountStudent edit(AccountStudent accountStudent) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE AccountStudent_tbl SET Borrow=?, nameandfamily=?,yourSuggestion=? WHERE ID=?"
        );
        preparedStatement.setString(1, String.valueOf(accountStudent.getBorrow()));
        preparedStatement.setString(2, String.valueOf(accountStudent.getNameAndFamily()));
        preparedStatement.setString(2, String.valueOf(accountStudent.getYourSuggestion()));
        preparedStatement.setInt(4, accountStudent.getId());
        preparedStatement.execute();
        log.info("edit");
        return accountStudent;
    }
    public AccountStudent findAllByNameAndFamily(String nameAndFamily) throws SQLException {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM accountStudent_tbl WHERE ID=?"
        );
        preparedStatement.setString(1, nameAndFamily);
        ResultSet resultSet = preparedStatement.executeQuery();
        AccountStudent accountStudent = null;
        while (resultSet.next()) {
            accountStudent =
                    AccountStudent
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .nameBook(resultSet.getString("Name Book"))
                            .yourSuggestion(resultSet.getString("Suggestion Book"))
                            .nameAndFamily(resultSet.getString("Family and name"))
                            .build();
        }
        log.info("findAllByNameAndFamily");
        return accountStudent;
    }


    public AccountStudent findById(int id) throws SQLException {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM ACCOUNTSTUDENT_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        AccountStudent accountStudent = null;
        while (resultSet.next()) {
            accountStudent =
                    AccountStudent
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .nameBook(resultSet.getString("Name Book"))
                            .yourSuggestion(resultSet.getString("Suggestion Book"))
                            .nameAndFamily(resultSet.getString("Family Book"))
                            .build();
        }
        log.info("findById");
        return accountStudent;
    }
@Override
public void close() throws Exception {
    preparedStatement.close();
    connection.close();
}
}