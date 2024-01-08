package mft.Controller.model.repository;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Management;
import mft.model.tools.JdbcProvider;
import org.apache.log4j.BasicConfigurator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Log4j
public class ManagementRepository implements AutoCloseable {
    private static PreparedStatement preparedStatement;
    private static Connection connection;

    public Management save(Management management) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
"SELECT member_SEQ.nextval AS NEXT_ID FROM DUAL"
        );
ResultSet resultSet =  preparedStatement.executeQuery();
        resultSet.next();
        management.setId(resultSet.getInt("NEXT_ID"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO member_tbl(ID, username,password) VALUES (?,?,?)"
        );
        preparedStatement.setInt(1, management.getId());
        preparedStatement.setString(3, management.getUsername());
        preparedStatement.setString(2, management.getPassword());
        preparedStatement.execute();
        log.info("Save repository");
        return management;
    }


    public Management remove(String username) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM member_tbl WHERE username=?"
        );
        preparedStatement.setString(1,username );
        preparedStatement.execute();
        log.info("remove");
        return null;

    }

    public Management findByUsernameAndPassword(String username) throws Exception {
        preparedStatement = connection.prepareStatement(
"SELECT * FROM member_tbl WHERE username=?  AND password=?"
        );
        preparedStatement.setString(1, username);

        ResultSet resultSet = preparedStatement.executeQuery();
        Management management =  null;
        while (resultSet.next()) {

            management =
                    Management
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .username(resultSet.getString("username"))
                            .password(resultSet.getString("password"))
                            .build();
log.info("findByUsernameAndPassword");
            return management;
        }
        return management;
    }
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}

