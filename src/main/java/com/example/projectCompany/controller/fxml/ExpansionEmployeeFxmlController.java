package com.example.projectCompany.controller.fxml;

import com.example.projectCompany.controller.EmployeeUtilApi;
import com.example.projectCompany.controller.config.EmployeeApiConfig;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
@FxmlView("/fxml/infoEmployee.fxml")
public class ExpansionEmployeeFxmlController implements Initializable {

    private final EmployeeUtilApi api = EmployeeApiConfig.getApi();

    public ExpansionEmployeeFxmlController() {
    }

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getAll();
    }

    @FXML
    private MenuItem miHome;
    @FXML
    private MenuItem miReport;
    @FXML
    private MenuItem miBack;

    @FXML
    private TextField tfGetById;
    @FXML
    private TextField tfGetByEmail;
    @FXML
    private TextField tfGetByMarried;
    @FXML
    private TextField tfGetByDepartment;
    @FXML
    private TextField tfGetBySalaryAfter;
    @FXML
    private TextField tfGetBySalaryBefore;
    @FXML
    private TextField tfGetBySalaryBetweenMin;
    @FXML
    private TextField tfGetBySalaryBetweenMax;

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
    private Button btnGetAll;
    @FXML
    private Button btnGetById;
    @FXML
    private Button btnGetByEmail;
    @FXML
    private Button btnGetByMarried;
    @FXML
    private Button btnGetByDepartment;
    @FXML
    private Button btnGetBySalaryAfter;
    @FXML
    private Button btnGetBySalaryBefore;
    @FXML
    private Button btnGetBySalaryBetween;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == btnGetAll) {
            getAll();
        } else if (event.getSource() == btnGetById) {
            getById();
        } else if (event.getSource() == btnGetByEmail) {
            getByEmail();
        } else if (event.getSource() == btnGetByMarried) {
            GetByMarried();
        } else if (event.getSource() == btnGetByDepartment) {
            GetByDepartment();
        } else if (event.getSource() == btnGetBySalaryAfter) {
            GetBySalaryAfter();
        } else if (event.getSource() == btnGetBySalaryBefore) {
            GetBySalaryBefore();
        } else if (event.getSource() == btnGetBySalaryBetween) {
            GetBySalaryBetween();
        }

    }

    @FXML
    public void menuHandleButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == miHome) {
            redirectToAnotherWindow(event, "/fxml/index.fxml");
        } else if (event.getSource() == miReport) {
            redirectToAnotherWindow(event, "/fxml/index.fxml");
        } else if (event.getSource() == miBack) {
            redirectToAnotherWindow(event, "/fxml/mainEmployee.fxml");
        }

    }


    private void getAll() throws IOException {
        ObservableList<EmployeeResponseDto> list =
                FXCollections.observableArrayList(getEmployeesList());
        showEmployees(list);
    }

    private void getById() throws IOException {
        ObservableList<EmployeeResponseDto> list =
                FXCollections.observableArrayList(getEmployeeById());
        showEmployees(list);
    }

    private void getByEmail() throws IOException {
        ObservableList<EmployeeResponseDto> list =
                FXCollections.observableArrayList(getEmployeeByEmail());
        showEmployees(list);
    }

    private void GetByMarried() throws IOException {
        ObservableList<EmployeeResponseDto> list =
                FXCollections.observableArrayList(getEmployeesByMarried());
        showEmployees(list);
    }

    private void GetByDepartment() throws IOException {
        ObservableList<EmployeeResponseDto> list =
                FXCollections.observableArrayList(getEmployeesByDepartment());
        showEmployees(list);
    }

    private void GetBySalaryAfter() throws IOException {
        ObservableList<EmployeeResponseDto> list =
                FXCollections.observableArrayList(getEmployeeBySalaryAfter());
        showEmployees(list);
    }

    private void GetBySalaryBefore() throws IOException {
        ObservableList<EmployeeResponseDto> list =
                FXCollections.observableArrayList(getEmployeesBySalaryBefore());
        showEmployees(list);
    }

    private void GetBySalaryBetween() throws IOException {
        ObservableList<EmployeeResponseDto> list =
                FXCollections.observableArrayList(getEmployeeBySalaryBetween());
        showEmployees(list);
    }

    public List<EmployeeResponseDto> getEmployeesList() throws IOException {
        Call<List<EmployeeResponseDto>> response = api.getAllEmployee();
        return response.execute().body();
    }

    public List<EmployeeResponseDto> getEmployeeById() throws IOException {
        Call<EmployeeResponseDto> response = api.getEmployeeById(Long.valueOf(tfGetById.getText()));
        List<EmployeeResponseDto> list = new ArrayList<>();
        list.add(response.execute().body());
        return list;
    }

    public List<EmployeeResponseDto> getEmployeeByEmail() throws IOException {
        Call<EmployeeResponseDto> response = api.getEmployeeByEmail(tfGetByEmail.getText());
        List<EmployeeResponseDto> list = new ArrayList<>();
        list.add(response.execute().body());
        return list;
    }

    public List<EmployeeResponseDto> getEmployeesByMarried() throws IOException {
        Call<List<EmployeeResponseDto>> response = api.getAllByMarried(Boolean.parseBoolean(tfGetByMarried.getText()));
        return response.execute().body();
    }

    public List<EmployeeResponseDto> getEmployeesByDepartment() throws IOException {
        Call<List<EmployeeResponseDto>> response = api.getAllByDepartment(tfGetByDepartment.getText());
        return response.execute().body();
    }

    public List<EmployeeResponseDto> getEmployeeBySalaryAfter() throws IOException {
        Call<List<EmployeeResponseDto>> response = api.getAllBySalaryAfter(Double.parseDouble(tfGetBySalaryAfter.getText()));
        return response.execute().body();
    }

    public List<EmployeeResponseDto> getEmployeesBySalaryBefore() throws IOException {
        Call<List<EmployeeResponseDto>> response = api.getAllBySalaryBefore(Double.parseDouble(tfGetBySalaryBefore.getText()));
        return response.execute().body();
    }

    public List<EmployeeResponseDto> getEmployeeBySalaryBetween() throws IOException {
        Call<List<EmployeeResponseDto>> response = api.getAllBySalaryBetween(
                Double.parseDouble(tfGetBySalaryBetweenMin.getText()),
                Double.parseDouble(tfGetBySalaryBetweenMax.getText()));
        return response.execute().body();
    }


    private void showEmployees(ObservableList<EmployeeResponseDto> list) {
        colId.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, Long>("id"));
        colUsername.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, String>("username"));
        colEmail.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, String>("email"));
        colMarried.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, Boolean>("married"));
        colSalary.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, Double>("salary"));
        colDepartment.setCellValueFactory(new PropertyValueFactory<EmployeeResponseDto, String>("department"));

        tvEmployees.setItems(list);
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
