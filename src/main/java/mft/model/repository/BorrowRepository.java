package mft.model.repository;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Borrow;
import mft.model.entity.Management;
import mft.model.tools.JdbcProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class BorrowRepository implements AutoCloseable  {
    private PreparedStatement preparedStatement;
    private Connection connection;
    public Borrow save(Borrow borrow) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT BORROW_SEQ.nextval AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        borrow.setId(resultSet.getInt("NEXT_ID"));
        preparedStatement = connection.prepareStatement(
"INSERT INTO BORROW_TBL(ID, USERNAME,nameBook,authorBook) VALUES (?,?,?,?)" );
        preparedStatement.setInt(1, borrow.getId());
        preparedStatement.setString(2, borrow.getUsername());
        preparedStatement.setString(3, borrow.getNameBook());
        preparedStatement.setString(4, borrow.getAuthorBook());
        preparedStatement.execute();
        log.info("Save repository");
        return borrow;
    }
    public Borrow remove(String username,String nameBook,String authorBook) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM BORROW_TBL WHERE username=? And  NAMEBOOK=? and AUTHORBOOK=?"
        );
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, nameBook);
        preparedStatement.setString(3, authorBook);
        preparedStatement.execute();
        log.info("remove");
        return null;
    }
    public Borrow findByUsernameNameBookAndAuthorBook(String username,String nameBook, String authorBook) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM BORROW_TBL WHERE username=?And NAMEBOOK=? and AUTHORBOOK=?"
        );
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, nameBook);
        preparedStatement.setString(3, authorBook);
        ResultSet resultSet = preparedStatement.executeQuery();

        Borrow borrow = null;
        while (resultSet.next()) {
            borrow =
                    Borrow
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .username(resultSet.getString("username"))
                            .nameBook(resultSet.getString("nameBook"))
                            .authorBook(resultSet.getString("authorBook"))
                            .build();
        }
        return borrow;
    }
    public Borrow edit(Borrow borrow) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
"UPDATE BORROW_TBL SET USERNAME=?, NAMEBOOK=?, AUTHORBOOK=? WHERE ID=?"
        );
        preparedStatement.setString(1, borrow.getUsername());
        preparedStatement.setString(2, borrow.getNameBook());
        preparedStatement.setString(3, borrow.getAuthorBook());
        preparedStatement.setInt(4, borrow.getId());
        preparedStatement.execute();
        log.info("edit repository");
        return borrow;
    }
    public Borrow findById(int id) throws SQLException {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM BORROW_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Borrow borrow = null;
        while (resultSet.next()) {
            borrow =
                    Borrow
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .username(resultSet.getString("username"))
                            .nameBook(resultSet.getString("Name Book"))
                            .authorBook(resultSet.getString("authorBook"))
                            .build();
        }
        log.info("findById");
        return borrow;
    }
    public List<Borrow> findAll() throws Exception   {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM BORROW_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Borrow> borrowList = new ArrayList<>();

        while (resultSet.next()) {
            Borrow borrow =
                    Borrow
                            .builder()
                            .id(resultSet.getInt("ID"))
.username(resultSet.getString("username"))
.nameBook(resultSet.getString("nameBook"))
.authorBook(resultSet.getString("AuthorBook"))
                            .build();
            borrowList.add(borrow);
        }
        return borrowList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
