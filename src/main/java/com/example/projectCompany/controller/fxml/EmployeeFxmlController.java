package com.example.projectCompany.controller.fxml;

import com.example.projectCompany.controller.EmployeeUtilApi;
import com.example.projectCompany.controller.config.EmployeeApiConfig;
import com.example.projectCompany.dto.request.EmployeeRequestDto;
import com.example.projectCompany.dto.response.EmployeeResponseDto;
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
import retrofit2.Call;

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
    private TableView<EmployeeResponseDto> tvEmployees;
    @FXML
    private TableColumn<EmployeeResponseDto, Long> colId;
    @FXML
    private TableColumn<EmployeeResponseDto, String> colUsername;
    @FXML
    private TableColumn<EmployeeResponseDto, String> colEmail;
    @FXML
    private TableColumn<EmployeeResponseDto, Boolean> colMarried;
    @FXML
    private TableColumn<EmployeeResponseDto, Double> colSalary;
    @FXML
    private TableColumn<EmployeeResponseDto, String> colDepartment;

    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == btnInsert) {
            insertRecord();
        } else if (event.getSource() == btnUpdate) {
            updateRecord();
        } else if (event.getSource() == btnDelete) {
            deleteButton();
        }

    }

    @FXML
    public void initialize() throws IOException {
        showEmployees();
    }

    public void showEmployees() throws IOException {
        ObservableList<EmployeeResponseDto> list = (ObservableList<EmployeeResponseDto>) getEmployeesList();

        colId.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, Long>("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, String>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, String>("email"));
        colMarried.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, Boolean>("married"));
        colSalary.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, Double>("salary"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, String>("department"));

        tvEmployees.setItems(list);
    }

    public List<EmployeeResponseDto> getEmployeesList() throws IOException {
        Call<List<EmployeeResponseDto>> response = api.getAllEmployee();
        return response.execute().body();
    }

    private void insertRecord() throws IOException {

        EmployeeRequestDto request = new EmployeeRequestDto();
        request.setUsername(tfUsername.getText());
        request.setEmail(tfEmail.getText());
        request.setSalary(Double.parseDouble(tfSalary.getText()));
        request.setMarried(Boolean.parseBoolean(tfMarried.getText()));
        request.setDepartment(tfDepartment.getText());

        api.saveEmployee(request);
        showEmployees();
    }

    private void updateRecord() throws IOException {

        EmployeeRequestDto request = new EmployeeRequestDto();
        request.setId(Long.parseLong(tfId.getText()));
        request.setUsername(tfUsername.getText());
        request.setEmail(tfEmail.getText());
        request.setSalary(Double.parseDouble(tfSalary.getText()));
        request.setMarried(Boolean.parseBoolean(tfMarried.getText()));
        request.setDepartment(tfDepartment.getText());

        api.updateEmployeeData(request.getId(), request);
        showEmployees();
    }

    private void deleteButton() throws IOException {
        api.deleteEmployee(Long.parseLong(tfId.getText()));
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
