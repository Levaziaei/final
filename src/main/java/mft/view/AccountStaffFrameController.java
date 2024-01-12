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
import mft.Controller.AdminController;
import mft.Controller.BookController;
import mft.Controller.BorrowController;
import mft.Controller.ManagementController;
import mft.model.entity.Admin;
import mft.model.entity.Book;
import mft.model.entity.Borrow;
import mft.model.entity.Management;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AccountStaffFrameController  implements Initializable {
    @FXML
    private Button removeBorrowBtn,removeBookBtn,saveBookBtn,editBookBtn,removeSuggestionBtn;
    @FXML
    private TextField idBorrowTxt, usernameBorrowTxt,nameBookBorrowTxt,authorBookBorrowTxt,idBookTxt,nameBookTxt,authorBookTxt,idSuggestionTxt,suggestionTxt;
    @FXML
    private TableView<Borrow> borrowTbl;
    @FXML
    private TableView<Book> bookTbl;
    @FXML
    private TableView<Admin> suggestionTbl;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resetForm1();
        resetForm2();
        resetForm3();
        saveBookBtn.setOnAction((event) -> {
            try {
                Book book = BookController.getController().save(
                        nameBookTxt.getText(),
                        authorBookTxt.getText()
                );
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Saved");
                alert.show();
                resetForm2();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Save Error " + e.getMessage());
                alert.show();
            }
        });
        editBookBtn.setOnAction((event) -> {
            try {
                Book book = BookController.getController().edit(
                        Integer.parseInt(idBookTxt.getText()),
                        nameBookTxt.getText(),
                        authorBookTxt.getText()
                );
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Edited");
                alert.show();
                resetForm2();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Save Error " + e.getMessage());
                alert.show();
            }
        });

        removeBookBtn.setOnAction((event) -> {
            try {
                Book book = BookController.getController().remove2(Integer.parseInt(idBookTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Removed");
                alert.show();
                resetForm2();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Save Error " + e.getMessage());
                alert.show();
            }
        });

        removeBorrowBtn.setOnAction((event) -> {
                try {
                    Borrow borrow = BorrowController.getController().remove(Integer.parseInt(idBorrowTxt.getText()));
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Removed");
                    alert.show();
                    resetForm1();
                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Save Error " + e.getMessage());
                    alert.show();
                }
        });
        removeSuggestionBtn.setOnAction((event) -> {
            try {
                Admin admin = AdminController.getController().remove(Integer.parseInt(idSuggestionTxt.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "User Removed");
                alert.show();
                resetForm3();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Save Error " + e.getMessage());
                alert.show();
            }
        });
        borrowTbl.setOnMouseClicked((event) -> {
            Borrow borrow = borrowTbl.getSelectionModel().getSelectedItem();

            idBorrowTxt.setText(String.valueOf(borrow.getId()));
            usernameBorrowTxt.setText(borrow.getNameBook());
            nameBookBorrowTxt.setText(borrow.getNameBook());
            authorBookBorrowTxt.setText(borrow.getAuthorBook());
        });
        bookTbl.setOnMouseClicked((event) -> {
            Book book = bookTbl.getSelectionModel().getSelectedItem();

            idBookTxt.setText(String.valueOf(book.getId()));
            nameBookTxt.setText(book.getNameBook());
            authorBookTxt.setText(book.getAuthorBook());
        });
        suggestionTbl.setOnMouseClicked((event) -> {
            Admin admin = suggestionTbl.getSelectionModel().getSelectedItem();

            idSuggestionTxt.setText(String.valueOf(admin.getId()));
            suggestionTxt.setText(admin.getSuggestion());
        });
    }

    private void showDataOnTable1(List<Borrow> borrowList) {
ObservableList<Borrow> borrows = FXCollections.observableList(borrowList);
        borrowTbl.getColumns().clear();

        TableColumn<Borrow, Integer> idBorrowCol = new TableColumn<>("IdBorrow");
        idBorrowCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Borrow, String> usernameBorrowCol = new TableColumn<>("UsernameBorrow");
        usernameBorrowCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Borrow, String> nameBookBorrowCol = new TableColumn<>("BookBorrow");
        nameBookBorrowCol.setCellValueFactory(new PropertyValueFactory<>("nameBook"));

        TableColumn<Borrow, String> authorBookBorrowCol = new TableColumn<>("AuthorBorrow");
        authorBookBorrowCol.setCellValueFactory(new PropertyValueFactory<>("authorBook"));

        borrowTbl.getColumns().addAll(idBorrowCol, usernameBorrowCol, nameBookBorrowCol,authorBookBorrowCol);

        borrowTbl.setItems(borrows);
    }
    private void showDataOnTable2(List<Book> bookList) {
        ObservableList<Book> books = FXCollections.observableList(bookList);

        bookTbl.getColumns().clear();

        TableColumn<Book, Integer> idBookCol = new TableColumn<>("Id");
        idBookCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> nameBookCol = new TableColumn<>("Book");
        nameBookCol.setCellValueFactory(new PropertyValueFactory<>("nameBook"));

        TableColumn<Book, String> authorBookCol = new TableColumn<>("Author");
        authorBookCol.setCellValueFactory(new PropertyValueFactory<>("authorBook"));

        bookTbl.getColumns().addAll(idBookCol, nameBookCol, authorBookCol);

        bookTbl.setItems(books);
    }
    private void showDataOnTable3(List<Admin> suggestionList) {
ObservableList<Admin> suggestions = FXCollections.observableList(suggestionList);
        suggestionTbl.getColumns().clear();

        TableColumn<Admin, Integer> idSuggestionCol = new TableColumn<>("Id");
        idSuggestionCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Admin, String> suggestionCol = new TableColumn<>("Suggestion");
        suggestionCol.setCellValueFactory(new PropertyValueFactory<>("suggestion"));


        suggestionTbl.getColumns().addAll(idSuggestionCol, suggestionCol);

        suggestionTbl.setItems(suggestions);
    }
 public void resetForm1() {
     try {
         idBorrowTxt.clear();
         nameBookBorrowTxt.clear();
         authorBookBorrowTxt.clear();
         showDataOnTable1(BorrowController.getController().findAll());
     } catch (Exception e) {
         Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
         alert.show();
     }}
     public void resetForm2() {
         try {
             idBookTxt.clear();
             nameBookTxt.clear();
             authorBookTxt.clear();
             showDataOnTable2(BookController.getController().findAll());
         } catch (Exception e) {
             Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
             alert.show();
         }
     }
    public void resetForm3() {
        try {
            idSuggestionTxt.clear();
           suggestionTxt.clear();
            showDataOnTable3(AdminController.getController().findAll());
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Data Load Error" + e.getMessage());
            alert.show();
        }}
 }