package com.example.projectCompany.controller.fxml;

import com.example.projectCompany.controller.DepartmentUtilApi;
import com.example.projectCompany.controller.config.DepartmentApiConfig;
import com.example.projectCompany.entity.Department;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("mainDepartment.fxml")
public class DepartmentFxmlController {

    private final DepartmentUtilApi api = DepartmentApiConfig.getApi();

    public DepartmentFxmlController() {
    }

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfLocation;
    @FXML
    private TextField tfWebSite;
    @FXML
    private TextField tfCompany;

    @FXML
    private TableView<Department> tvDepartment;
    @FXML
    private TableColumn<Department, Long> colId;
    @FXML
    private TableColumn<Department, String> colName;
    @FXML
    private TableColumn<Department, String> colLocation;
    @FXML
    private TableColumn<Department, String> colWebSite;
    @FXML
    private TableColumn<Department, String> colCompany;

    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (event.getSource() == btnInsert) {
            insertRecord();
        } else if (event.getSource() == btnUpdate) {
            updateRecord();
        } else if (event.getSource() == btnDelete) {
            deleteButton();
        }

    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        showDepartments();
    }

    public ObservableList<Department> getDepartmentsList() {
        return null;
    }

    public void showDepartments() {
        ObservableList<Department> list = getDepartmentsList();

        colId.setCellValueFactory(new PropertyValueFactory<Department, Long>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Department, String>("name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<Department, String>("location"));
        colWebSite.setCellValueFactory(new PropertyValueFactory<Department, String>("website"));
        colCompany.setCellValueFactory(new PropertyValueFactory<Department, String>("company"));

        tvDepartment.setItems(list);
    }

    private void insertRecord() {
        showDepartments();
    }

    private void updateRecord() {
        showDepartments();
    }

    private void deleteButton() {
        showDepartments();
    }
}
