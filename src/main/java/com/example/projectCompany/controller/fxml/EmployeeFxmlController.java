package com.example.projectCompany.controller.fxml;

import com.example.projectCompany.controller.EmployeeUtilApi;
import com.example.projectCompany.controller.config.EmployeeApiConfig;
import com.example.projectCompany.entity.Employee;
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

import java.util.List;

@Component
@FxmlView("mainEmployee.fxml")
public class EmployeeFxmlController {

    private final EmployeeUtilApi api = EmployeeApiConfig.getApi();

    public EmployeeFxmlController() {
    }

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
}
