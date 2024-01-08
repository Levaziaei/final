package mft.model.repository;
import lombok.extern.log4j.Log4j;
import mft.model.entity.Book;
import mft.model.entity.Management;
import mft.model.tools.JdbcProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Log4j
public class BookRepository implements AutoCloseable {
private  PreparedStatement preparedStatement;
private  Connection connection;

    public Book save(Book book) throws Exception {
connection = JdbcProvider.getJdbcProvider().getConnection();
preparedStatement = connection.prepareStatement(
"SELECT BORROW_SEQ.nextval AS NEXT_ID FROM DUAL"
);
ResultSet resultSet = preparedStatement.executeQuery();
resultSet.next();
book.setId(resultSet.getInt("NEXT_ID"));
preparedStatement = connection.prepareStatement(
  "INSERT INTO Borrow_Tbl(ID, nameBook,authorBook,nameAndFamily,yourSuggestion) VALUES (?,?,?,?,?)" );
preparedStatement.setString(2, book.getNameBook());
preparedStatement.setString(4, book.getYourSuggestion());
preparedStatement.setInt(5, book.getId());
preparedStatement.execute();
log.info("save");
return book;
    }
    public Book remove(int id) throws Exception {
            connection = JdbcProvider.getJdbcProvider().getConnection();
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM BORROW_TBL WHERE ID=?"
            );
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            log.info("remove");
            return null;
        }
    public Book edit(Book book) throws Exception {
            connection = JdbcProvider.getJdbcProvider().getConnection();
            preparedStatement = connection.prepareStatement(
                    "UPDATE BORROW_TBL SET NAMEBOOK=?, NAMEANDFAMILY=? WHERE ID=?"
            );
            preparedStatement.setString(1, book.getNameBook());
            preparedStatement.setString(2, book.getYourSuggestion());
            preparedStatement.setInt(3, book.getId());
            preparedStatement.execute();
            log.info("edit");
            return book;
    }
    public List<Book> findAll() throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
 "SELECT * FROM BORROW_TBL"
        );
 ResultSet resultSet = preparedStatement.executeQuery();

 List<Book> bookList = new ArrayList<>();

 while (resultSet.next()) {
 Book book =
 Book
.builder()
.id(resultSet.getInt("ID"))
.nameBook(resultSet.getString("Book is"))
.yourSuggestion(resultSet.getString("your Suggestion"))
.build();
 bookList.add(book);
log.info("findAll");
        }
log.info("findById");
        return bookList;
    }

    public Book findById(int id) throws SQLException {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM BORROW_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Book book = null;
        while (resultSet.next()) {
            book =
                    Book
  .builder()
.id(resultSet.getInt("ID"))
.nameBook(resultSet.getString("Name Book"))
.build();
    log.info("findById");    }
        log.info("findById");
        return book;
    }
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }}


