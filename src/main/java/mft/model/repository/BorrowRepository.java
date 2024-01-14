package mft.model.repository;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Borrow;
import mft.model.tools.JdbcProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class BorrowRepository implements AutoCloseable {
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
                "INSERT INTO BORROW_TBL(ID, USERNAME,nameBook,authorBook) VALUES (?,?,?,?)");
        preparedStatement.setInt(1, borrow.getId());
        preparedStatement.setString(2, borrow.getUsername());
        preparedStatement.setString(3, borrow.getNameBook());
        preparedStatement.setString(4, borrow.getAuthorBook());
        preparedStatement.execute();
        log.info("save ");
        return borrow;
    }

    public Borrow remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "Delete FROM BORROW_TBL WHERE ID=?"
        );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("remove");
        return null;
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
                            .build();
        }
        log.info("findById");
        return borrow;
    }
    public List<Borrow> findAll() throws Exception {
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
        log.info("findAll");
        return borrowList;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}

