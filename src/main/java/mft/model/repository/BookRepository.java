package mft.model.repository;
import lombok.extern.log4j.Log4j;
import mft.model.entity.Book;
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
"SELECT BOOK_SEQ.nextval AS NEXT_ID FROM DUAL"
);
ResultSet resultSet = preparedStatement.executeQuery();
resultSet.next();
book.setId(resultSet.getInt("NEXT_ID"));
preparedStatement = connection.prepareStatement(
  "INSERT INTO BOOK_TBL(ID, nameBook,authorBook) VALUES (?,?,?)" );
        preparedStatement.setInt(1, book.getId());
        preparedStatement.setString(2, book.getNameBook());
        preparedStatement.setString(3, book.getAuthorBook());
        preparedStatement.execute();
        log.info("save ");
     return book;
    }
    public Book remove2(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "Delete FROM BOOK_TBL WHERE ID=?"
        );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("remove2");
        return null;
    }
    public Book remove1(String nameBook,String authorBook) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM BOOK_TBL WHERE nameBook=? And AUTHORBOOK=?"
        );
        preparedStatement.setString(1, nameBook);
        preparedStatement.setString(2, authorBook);
        preparedStatement.execute();
        log.info("remove1");
        return null;

    }
    public Book edit(Book book) throws Exception {
            connection = JdbcProvider.getJdbcProvider().getConnection();
            preparedStatement = connection.prepareStatement(
                    "UPDATE BOOK_TBL SET NAMEBOOK=?, AUTHORBOOK=? WHERE ID=?"
            );
        preparedStatement.setString(1, book.getNameBook());
        preparedStatement.setString(2, book.getAuthorBook());
        preparedStatement.setInt(3, book.getId());
            preparedStatement.execute();
            log.info("edit");
            return book;
    }
    public List<Book> findAll() throws Exception   {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM BOOK_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Book> bookList = new ArrayList<>();

        while (resultSet.next()) {
            Book book =
                    Book
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .nameBook(resultSet.getString("nameBook"))
                            .authorBook(resultSet.getString("AuthorBook"))
                            .build();
            bookList.add(book);
        }
        log.info("findAll");
        return bookList;
    }
    public Book findById(int id) throws SQLException {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM BOOK_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Book book = null;
        while (resultSet.next()) {
            book =
                    Book
  .builder()
.id(resultSet.getInt("ID"))
.build();
      }
        log.info("findById");
        return book;
    }
    public List<Book> searchForNameBook(String nameBook) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM BOOK_TBL WHERE NAMEBOOK LIKE ?"
        );
        preparedStatement.setString(1,nameBook+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Book> bookList = new ArrayList<>();

        while (resultSet.next()) {
            Book book =
                    Book
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .nameBook(resultSet.getString("nameBook"))
                            .authorBook(resultSet.getString("authorBook"))
                            .build();
            bookList.add(book);
        }
        log.info("searchForNameBook");
        return bookList;
    }

    public Book findByNameBookAndAuthorBook(String nameBook,String authorBook) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM BOOK_TBL WHERE nameBook=? And AUTHORBOOK=?"
        );
        preparedStatement.setString(1, nameBook);
        preparedStatement.setString(2, authorBook);
        ResultSet resultSet = preparedStatement.executeQuery();

        Book book = null;
        while (resultSet.next()) {
            book =
                    Book
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .nameBook(resultSet.getString("nameBook"))
                            .authorBook(resultSet.getString("authorBook"))
                            .build();
        }
        log.info("findByNameBookAndAuthorBook");
        return book;
    }
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }}


