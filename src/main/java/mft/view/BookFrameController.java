package mft.view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mft.Controller.SuggestionController;
import mft.Controller.BookController;
import mft.Controller.BorrowController;
import mft.model.entity.Book;
import mft.model.entity.Borrow;
import mft.model.entity.Suggestion;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookFrameController implements Initializable {

    @FXML
    private Button SaveAndRemoveBtn, saveSuggestionBtn;
    @FXML
    private TextField idTxt, searchForNameBookTxt, nameBookTxt, authorBookTxt, usernameTxt, suggestionTxt;

    @FXML
    private TableView<Book> bookTbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm();
        SaveAndRemoveBtn.setOnAction((event) -> {
                    try {
                        Borrow borrow =   BorrowController.getController().save(
                                usernameTxt.getText(),
                                nameBookTxt.getText(),
                                authorBookTxt.getText());
                      BookController.getController().remove1(
                                usernameTxt.getText(),
                                nameBookTxt.getText(),
                                authorBookTxt.getText()
                       );
                       Alert alert = new Alert(Alert.AlertType.INFORMATION, "Accept :>");
                        alert.show();
                        resetForm();

                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
                    }
                });
        saveSuggestionBtn.setOnAction((event) -> {
            try {
                Suggestion suggestion =SuggestionController.getController().save(
                        suggestionTxt.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Thanks :>");
                alert.show();
                resetForm2();

            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            }
        });

        bookTbl.setOnMouseClicked((event) -> {
            Book book = bookTbl.getSelectionModel().getSelectedItem();
            idTxt.setText(String.valueOf(book.getId()));
            nameBookTxt.setText(book.getNameBook());
            authorBookTxt.setText(book.getAuthorBook());
        });

        searchForNameBookTxt.setOnKeyReleased((event) -> {
            try {
                showDataOnTable(BookController.getController().searchForNameBook(searchForNameBookTxt.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, " Error " + e.getMessage());
                alert.show();
            }
        });
    }

    private void showDataOnTable(List<Book> bookList) {
        ObservableList<Book> books = FXCollections.observableList(bookList);

        bookTbl.getColumns().clear();

        TableColumn<Book, Integer> idCol = new TableColumn<>("Id");
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> nameBookCol = new TableColumn<>("Book");
        nameBookCol.setCellValueFactory(new PropertyValueFactory<>("nameBook"));

        TableColumn<Book, String> authorBookCol = new TableColumn<>("Author");
        authorBookCol.setCellValueFactory(new PropertyValueFactory<>("authorBook"));

        bookTbl.getColumns().addAll(idCol, nameBookCol, authorBookCol);

        bookTbl.setItems(books);
    }

    public void resetForm() {
        try {
            idTxt.clear();
            nameBookTxt.clear();
            authorBookTxt.clear();
            usernameTxt.clear();
            showDataOnTable(BookController.getController().findAll());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
            alert.show();
        }
    }

    public void resetForm2() {
        try {
            suggestionTxt.clear();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}