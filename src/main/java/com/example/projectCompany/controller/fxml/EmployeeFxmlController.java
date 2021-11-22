package com.example.projectCompany.controller.fxml;

import com.example.projectCompany.controller.EmployeeUtilApi;
import com.example.projectCompany.controller.config.EmployeeApiConfig;
import com.example.projectCompany.dto.request.EmployeeRequestDto;
import com.example.projectCompany.dto.response.EmployeeResponseDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import retrofit2.Call;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
@FxmlView("/fxml/employee.fxml")
public class EmployeeFxmlController implements Initializable {

    private final EmployeeUtilApi api = EmployeeApiConfig.getApi();

    public EmployeeFxmlController() {
    }

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showEmployees();
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
    private TextField tfFirstName;
    @FXML
    private TextField tfSecondName;
    @FXML
    private TextField tfBirthday;
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
    private TableColumn<EmployeeResponseDto, String> colBirthday;
    @FXML
    private TableColumn<EmployeeResponseDto, String> colAge;
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
    public void menuHandleButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == miHome) {
            redirectToAnotherWindow(event, "/fxml/index.fxml");
        } else if (event.getSource() == miReport) {
            openWebpage("http://localhost:8080/export/employees/excel");
        } else if (event.getSource() == miMore) {
            redirectToAnotherWindow(event, "/fxml/infoEmployee.fxml");
        }

    }

    @FXML
    public static void openWebpage(String urlString) throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("rundll32 url.dll,FileProtocolHandler " + urlString);
    }



    public List<EmployeeResponseDto> getEmployeesList() throws IOException {
        Call<List<EmployeeResponseDto>> response = api.getAllEmployee();
        return response.execute().body();
    }

    public void showEmployees() throws IOException {
        ObservableList<EmployeeResponseDto> list =
                FXCollections.observableArrayList(getEmployeesList());

        colId.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, Long>("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, String>("username"));
        colBirthday.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, String>("birthday"));
        colAge.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, String>("age"));
        colEmail.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, String>("email"));
        colMarried.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, Boolean>("married"));
        colSalary.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, Double>("salary"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, String>("department"));

        tvEmployees.setItems(list);
    }

    private void insertRecord() throws IOException {
        EmployeeRequestDto request = new EmployeeRequestDto();
        request.setFirstName(tfFirstName.getText());
        request.setLastName(tfSecondName.getText());
        request.setBirthday(tfBirthday.getText());
        request.setEmail(tfEmail.getText());
        request.setSalary(Double.parseDouble(tfSalary.getText()));
        request.setMarried(Boolean.parseBoolean(tfMarried.getText()));
        request.setDepartment(tfDepartment.getText());

        Call<EmployeeResponseDto> response = api.saveEmployee(request);
        System.out.println(response.request());
        System.out.println(response.execute().body());
        showEmployees();
    }

    private void updateRecord() throws IOException {
        EmployeeRequestDto request = new EmployeeRequestDto();
        request.setId(Long.parseLong(tfId.getText()));
        request.setFirstName(tfFirstName.getText());
        request.setLastName(tfSecondName.getText());
        request.setBirthday(tfBirthday.getText());
        request.setEmail(tfEmail.getText());
        request.setSalary(Double.parseDouble(tfSalary.getText()));
        request.setMarried(Boolean.parseBoolean(tfMarried.getText()));
        request.setDepartment(tfDepartment.getText());

        Call<EmployeeResponseDto> response = api.updateEmployeeData(Long.valueOf(tfId.getText()), request);
        System.out.println(response.request());
        System.out.println(response.execute().body());
        showEmployees();
    }

    private void deleteButton() throws IOException {
        Call<String> response = api.deleteEmployee(Long.valueOf(tfId.getText()));
        System.out.println(response.request());
        System.out.println(response.execute().body());
        showEmployees();
    }

    public void redirectToAnotherWindow(ActionEvent event, String url) throws IOException {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream(url));

        InputStream iconStream = getClass().getResourceAsStream("/icon/bird.png");
        Image image = new Image(iconStream);
        stage.getIcons().add(image);

        stage.setTitle("VladSin Application");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
