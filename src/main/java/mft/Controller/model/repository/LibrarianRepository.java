package mft.Controller.model.repository;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Librarian;
import mft.model.tools.JdbcProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
@Log4j
public class LibrarianRepository implements AutoCloseable {
    private static PreparedStatement preparedStatement;
    private static Connection connection;
    public Librarian save(Librarian librarian) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
"SELECT librarian_Seq.nextval AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet =  preparedStatement.executeQuery();
        resultSet.next();
        librarian.setId(resultSet.getInt("NEXT_ID"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO librarian_tbl(ID, nameAndFamily,nameBook,search) VALUES (?,?,?)"
        );
        preparedStatement.setInt(1, librarian.getId());
        preparedStatement.setString(3, librarian.getNameAndFamily());
        preparedStatement.setString(2, librarian.getNameBook());
        preparedStatement.execute();
        log.info("save");
        return librarian;
    }

    public Librarian findByBook(String nameBook) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM Librarian_tbl WHERE nameBook=?"
        );
        preparedStatement.setString(1, nameBook);
        ResultSet resultSet = preparedStatement.executeQuery();
Librarian librarian=null;
        while (resultSet.next()) {
            librarian =
                    Librarian
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .password(resultSet.getString("Password"))
                            .nameAndFamily(resultSet.getString("name and family"))
                            .search(resultSet.getString("Search"))
                            .build();
            log.info("findByBook");

        }
        return librarian;
    }

    public Librarian edit(Librarian librarian) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "UPDATE Librarian_tbl SET nameAndFamily=?, nameBook=?, search=?, WHERE ID=?");
        preparedStatement.setString(1, librarian.getNameBook());
        preparedStatement.setString(2, librarian.getSearch());
        preparedStatement.setString(3, String.valueOf(librarian.getNameAndFamily()));
        preparedStatement.setInt(6, librarian.getId());
        preparedStatement.execute();
        log.info("edit");
        return librarian;
    }
    public List<Librarian> findAll() throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM BORROW_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Librarian> librarianList = new ArrayList<>();

        while (resultSet.next()) {
            Librarian librarian =
                    Librarian
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .nameBook(resultSet.getString("Book is"))
                            .nameAndFamily(resultSet.getString("Name and family of borrow"))
                            .build();
            librarianList.add(librarian);
            log.info("findAll");
        }
        return librarianList;
    }


    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}


