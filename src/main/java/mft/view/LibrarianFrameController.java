package mft.view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import mft.model.entity.Borrow;

import java.net.URL;
import java.util.ResourceBundle;

public class LibrarianFrameController implements Initializable {
    @FXML
    private Button saveBtn, editBtn;

    @FXML
    private TextField searchTxt, chooseTxt;

    @FXML
    private TableView<Borrow> booksTbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
