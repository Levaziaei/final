package mft.model.repository;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Admin;
import mft.model.tools.JdbcProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Log4j

public class AdminRepository  implements AutoCloseable {
    private PreparedStatement preparedStatement;
    private Connection connection;
    public Admin save(Admin admin) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT SUGGESTION_SEQ.nextval AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        admin.setId(resultSet.getInt("NEXT_ID"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO SUGGESTION_TBL(ID, SUGGESTION) VALUES (?,?)" );
        preparedStatement.setInt(1, admin.getId());
        preparedStatement.setString(2, admin.getSuggestion());
        preparedStatement.execute();
        log.info("Save repository");
        return admin;
    }
    public List<Admin> findAll() throws Exception   {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM SUGGESTION_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Admin> adminList = new ArrayList<>();
        while (resultSet.next()) {
            Admin admin =
                    Admin
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .suggestion(resultSet.getString("Suggestion"))
                            .build();
            adminList.add(admin);
        }
        return adminList;
    }
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
