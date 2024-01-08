package mft.model.repository;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Management;
import mft.model.tools.JdbcProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Log4j
public class ManagementRepository implements AutoCloseable {
    private PreparedStatement preparedStatement;
    private Connection connection;

    public Management save(Management management) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT member_SEQ.nextval AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        management.setId(resultSet.getInt("NEXT_ID"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO member_tbl(ID, username,password) VALUES (?,?,?)"
        );
        preparedStatement.setInt(1, management.getId());
        preparedStatement.setString(2, management.getUsername());
        preparedStatement.setString(3, management.getPassword());
        preparedStatement.execute();
        log.info("Save repository");
        return management;
    }


    public Management remove(String username,String password) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "DELETE FROM member_tbl WHERE username=? and PASSWORD=?"
        );
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.execute();
        log.info("remove");
        return null;

    }
    public Management findByUsername(String username) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM MEMBER_TBL WHERE USERNAME=?"
        );
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        Management management = null;
        while (resultSet.next()) {
            management =
                    Management
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .username(resultSet.getString("USERNAME"))
                            .password(resultSet.getString("PASSWORD"))
                            .build();
        }
        return management;
    }
    public Management findByPassword(String password) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM MEMBER_TBL WHERE password=?"
        );
        preparedStatement.setString(1, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        Management management = null;
        while (resultSet.next()) {
            management =
                Management
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .username(resultSet.getString("USERNAME"))
                            .password(resultSet.getString("PASSWORD"))
                            .build();
        }
        return management;
    }

    public Management findByUsernameAndPassword(String username,String password) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM MEMBER_TBL WHERE USERNAME=? AND PASSWORD=?"
        );
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        Management management = null;
        while (resultSet.next()) {
            management =
                    Management
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .username(resultSet.getString("USERNAME"))
                            .password(resultSet.getString("PASSWORD"))
                            .build();
        }
        return management;
    }

    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}

