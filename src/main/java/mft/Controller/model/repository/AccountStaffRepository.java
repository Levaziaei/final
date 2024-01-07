package mft.Controller.model.repository;

import lombok.extern.log4j.Log4j;
import mft.model.entity.AccountStaff;
import mft.model.tools.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Log4j
public class AccountStaffRepository implements AutoCloseable {
    private static PreparedStatement preparedStatement;
    private static Connection connection;

    public AccountStaff save(AccountStaff accountStaff) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT AccountStaff_seq .nextval AS NEXT_ID FROM DUAL"
        );;
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        accountStaff.setId(resultSet.getInt("NEXT_ID"));
  preparedStatement = connection.prepareStatement(
"INSERT INTO AccountStaff_Tbl(ID, nameBook,nameAndFamily,yourSuggestion,addSuggestion) VALUES (?,?,?,?,?)" );
        preparedStatement.setString(1, String.valueOf(accountStaff.getNameBook()));
        preparedStatement.setString(2, String.valueOf(accountStaff.getNameAndFamily()));
        preparedStatement.setString(3, String.valueOf(accountStaff.getYourSuggestion()));
        preparedStatement.setString(4, String.valueOf(accountStaff.getAddSuggestion()));
        preparedStatement.setInt(5, accountStaff.getId());
        preparedStatement.execute();
        log.info("save");
        return accountStaff;
    }
    public AccountStaff remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM accountStaff_tbl WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("remove");
        return null;
    }
    public AccountStaff edit(AccountStaff accountStaff) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE accountStaff_tbl SET nameBook=?, nameandfamily=?,yourSuggestion=?,addSuggestion=?, WHERE ID=?"
        );

        preparedStatement.setString(2, String.valueOf(accountStaff.getNameBook()));
        preparedStatement.setString(1, String.valueOf(accountStaff.getNameAndFamily()));
        preparedStatement.setString(3, String.valueOf(accountStaff.getYourSuggestion()));
        preparedStatement.setString(4, String.valueOf(accountStaff.getAddSuggestion()));
        preparedStatement.setInt(5, accountStaff.getId());
        preparedStatement.execute();
        log.info("edit");
        return accountStaff;
    }
    public List<AccountStaff> findAll() throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM Accountstaff_tbl"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        List<AccountStaff> accountStaffList = new ArrayList<>();
        while (resultSet.next()) {
            AccountStaff accountStaff =
                    AccountStaff
.builder()
.id(resultSet.getInt("ID"))
.addSuggestion(resultSet.getString("suggestion add"))
.yourSuggestion(resultSet.getString("for suggestion students "))
.nameAndFamily(resultSet.getString("name and family "))
.nameBook(resultSet.getString("name book"))
                            .build();
            accountStaffList.add(accountStaff);
            log.info("findAll");
        }
        return accountStaffList;

    }

    public AccountStaff findById(int id) throws SQLException {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM AccountStaff WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        AccountStaff accountStaff = null;
        while (resultSet.next()) {
            accountStaff  =
                    AccountStaff
                    .builder()
                    .id(resultSet.getInt("ID"))
                    .nameBook(resultSet.getString("Name Book"))
                    .nameAndFamily(resultSet.getString("Family Book"))
.addSuggestion(resultSet.getString("suggestion add"))
.yourSuggestion(resultSet.getString("for suggestion students "))
                    .build();
     log.info("findById");
        }
        return accountStaff;
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }}