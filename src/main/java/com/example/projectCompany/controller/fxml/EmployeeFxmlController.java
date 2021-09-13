package com.example.projectCompany.controller.fxml;

import com.example.projectCompany.controller.EmployeeUtilApi;
import com.example.projectCompany.controller.config.EmployeeApiConfig;
import com.example.projectCompany.entity.Employee;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
@FxmlView("mainEmployee.fxml")
public class EmployeeFxmlController {

    private final EmployeeUtilApi api = EmployeeApiConfig.getApi();

    public EmployeeFxmlController() {
    }

    @FXML
    private MenuItem miHome;
    @FXML
    private MenuItem miReport;
    @FXML
    private MenuItem miMore;

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfMarried;
    @FXML
    private TextField tfSalary;
    @FXML
    private TextField tfDepartment;

    @FXML
    private TableView<Employee> tvEmployees;
    @FXML
    private TableColumn<Employee, Long> colId;
    @FXML
    private TableColumn<Employee, String> colUsername;
    @FXML
    private TableColumn<Employee, String> colEmail;
    @FXML
    private TableColumn<Employee, Boolean> colMarried;
    @FXML
    private TableColumn<Employee, Double> colSalary;
    @FXML
    private TableColumn<Employee, String> colDepartment;

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
    public void initialize() {
        showEmployees();
    }

    public List<Employee> getEmployeesList() {
        //return employeeService.getAll();
        return null;
    }

    public void showEmployees() {
        List<Employee> list = getEmployeesList();

        colId.setCellValueFactory(new PropertyValueFactory<Employee, Long>("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
        colMarried.setCellValueFactory(new PropertyValueFactory<Employee, Boolean>("married"));
        colSalary.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salary"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<Employee, String>("department"));

        tvEmployees.setItems((ObservableList<Employee>) list);
    }

    private void insertRecord() {
        //Department department = departmentTransactionController.getDepartmentByName(tfDepartment.getText());
        Employee newEmployee = new Employee(null,
                tfUsername.getText(),
                tfEmail.getText(),
                Double.parseDouble(tfSalary.getText()),
                Boolean.parseBoolean(tfMarried.getText()),
                null);
        //employeeService.saveEmployee(newEmployee);
        showEmployees();
    }

    private void updateRecord() {
        //Department department = departmentTransactionController.getDepartmentByName(tfDepartment.getText());
        Employee updateEmployee = new Employee(
                Long.parseLong(tfId.getText()),
                tfUsername.getText(),
                tfEmail.getText(),
                Double.parseDouble(tfSalary.getText()),
                Boolean.parseBoolean(tfMarried.getText()),
                null);
        //employeeService.updateEmployeeData(updateEmployee);
        showEmployees();
    }

    private void deleteButton() {
        //employeeService.deleteEmployee(Long.parseLong(tfId.getText()));
        showEmployees();
    }

    @FXML
    public void menuHandleButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == miHome) {
            redirectToAnotherWindow(event, "/fxml/index.fxml");
        } else if (event.getSource() == miReport) {
            redirectToAnotherWindow(event, "/fxml/index.fxml");
        } else if (event.getSource() == miMore) {
            redirectToAnotherWindow(event, "/fxml/infoEmployee.fxml");
        }

    }

    public void redirectToAnotherWindow(ActionEvent event, String url) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(url));

        InputStream iconStream = getClass().getResourceAsStream("/icon/bird.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);

        stage.setTitle("VladSin Company");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
