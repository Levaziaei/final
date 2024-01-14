package mft.model.repository;

import lombok.extern.log4j.Log4j;
import mft.model.entity.Suggestion;
import mft.model.tools.JdbcProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Log4j

public class SuggestionRepository implements AutoCloseable {
    private PreparedStatement preparedStatement;
    private Connection connection;
    public Suggestion save(Suggestion suggestion) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT SUGGESTION_SEQ.nextval AS NEXT_ID FROM DUAL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        suggestion.setId(resultSet.getInt("NEXT_ID"));
        preparedStatement = connection.prepareStatement(
                "INSERT INTO SUGGESTION_TBL(ID, SUGGESTION) VALUES (?,?)" );
        preparedStatement.setInt(1, suggestion.getId());
        preparedStatement.setString(2, suggestion.getSuggest());
        preparedStatement.execute();
        log.info("save ");
        return suggestion;
    }

    public Suggestion remove(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "Delete FROM SUGGESTION_TBL WHERE ID=?"
        );

        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        log.info("remove");
        return null;
    }
    public Suggestion findById(int id) throws Exception {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM SUGGESTION_TBL WHERE ID=?"
        );
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        Suggestion suggestion = null;
        while (resultSet.next()) {
            suggestion =
                    Suggestion
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .suggest(resultSet.getString("Suggestion"))
                            .build();
        }
                      log.info("findById");
                       return suggestion;
    }
    public List<Suggestion> findAll() throws Exception   {
        connection = JdbcProvider.getJdbcProvider().getConnection();
        preparedStatement = connection.prepareStatement(
                "SELECT * FROM SUGGESTION_TBL"
        );
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Suggestion> suggestionList = new ArrayList<>();
        while (resultSet.next()) {
            Suggestion suggestion =
                    Suggestion
                            .builder()
                            .id(resultSet.getInt("ID"))
                            .suggest(resultSet.getString("Suggestion"))
                            .build();
            suggestionList.add(suggestion);
        }
        log.info("findAll");
        return suggestionList;
    }
    @Override
    public void close() throws Exception {
        preparedStatement.close();
        connection.close();
    }
}
