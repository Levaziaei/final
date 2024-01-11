package mft.view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mft.Controller.BookController;
import mft.Controller.BorrowController;
import mft.Controller.ManagementController;
import mft.model.entity.Book;
import mft.model.entity.Borrow;
import mft.model.entity.Management;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class BookFrameController implements Initializable {

    @FXML
    private Button SaveAndRemoveBtn;
    @FXML
    private TextField searchForNameBookTxt,nameBookTxt,authorBookTxt,usernameTxt,suggestionTxt;

    @FXML
    private TableView<Book> bookTbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            showDataOnTable(BookController.getController().findAll());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
        }
        searchForNameBookTxt.setOnKeyReleased((event) -> {
            try {
                showDataOnTable(BookController.getController().findByNameBook(searchForNameBookTxt.getText()));
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Save Error " + e.getMessage());
                alert.show();
            }
        });
        SaveAndRemoveBtn.setOnAction((event) -> {
            try {
                Borrow borrow = BorrowController.getController().save(
                        usernameTxt.getText(),
                        nameBookTxt.getText(),
                        authorBookTxt.getText());



            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
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


    }


