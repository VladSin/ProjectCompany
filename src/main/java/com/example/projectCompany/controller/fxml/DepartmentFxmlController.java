package com.example.projectCompany.controller.fxml;

import com.example.projectCompany.controller.CompanyUtilApi;
import com.example.projectCompany.controller.DepartmentUtilApi;
import com.example.projectCompany.controller.EmployeeUtilApi;
import com.example.projectCompany.controller.config.CompanyApiConfig;
import com.example.projectCompany.controller.config.DepartmentApiConfig;
import com.example.projectCompany.controller.config.EmployeeApiConfig;
import com.example.projectCompany.dto.request.DepartmentRequestDto;
import com.example.projectCompany.dto.response.CompanyResponseDto;
import com.example.projectCompany.dto.response.DepartmentResponseDto;
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
@FxmlView("/fxml/department.fxml")
public class DepartmentFxmlController implements Initializable {

    private final DepartmentUtilApi departmentUtilApi = DepartmentApiConfig.getApi();
    private final EmployeeUtilApi employeeUtilApi = EmployeeApiConfig.getApi();
    private final CompanyUtilApi companyUtilApi = CompanyApiConfig.getApi();

    public DepartmentFxmlController() {
    }

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ObservableList<EmployeeResponseDto> employeeList = FXCollections.observableArrayList(getEmployeesList());
        ObservableList<CompanyResponseDto> companyList = FXCollections.observableArrayList(getCompaniesList());

        List<String> employeeData = new ArrayList<>();
        for (EmployeeResponseDto e: employeeList) {
            employeeData.add(e.getId() + ", " + e.getUsername());
        }

        List<String> companyData = new ArrayList<>();
        for (CompanyResponseDto c: companyList) {
            companyData.add(c.getId() + ", " + c.getName());
        }

        cbCompany.setItems(FXCollections.observableArrayList(companyData));
        cbHead.setItems(FXCollections.observableArrayList(employeeData));

        showDepartments();
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
    private TextField tfName;
    @FXML
    private TextField tfLocation;
    @FXML
    private TextField tfWebSite;
    @FXML
    private ComboBox<String> cbCompany;
    @FXML
    private ComboBox<String> cbHead;

    @FXML
    private TableView<DepartmentResponseDto> tvDepartment;
    @FXML
    private TableColumn<DepartmentResponseDto, Long> colId;
    @FXML
    private TableColumn<DepartmentResponseDto, String> colName;
    @FXML
    private TableColumn<DepartmentResponseDto, String> colLocation;
    @FXML
    private TableColumn<DepartmentResponseDto, String> colWebSite;
    @FXML
    private TableColumn<DepartmentResponseDto, String> colCompany;
    @FXML
    private TableColumn<DepartmentResponseDto, String> colHead;

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
            openWebpage("http://localhost:8080/export/departments/excel");
        } else if (event.getSource() == miMore) {
            redirectToAnotherWindow(event, "/fxml/infoDepartment.fxml");
        }

    }

    @FXML
    public static void openWebpage(String urlString) throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("rundll32 url.dll,FileProtocolHandler " + urlString);
    }



    public List<DepartmentResponseDto> getDepartmentsList() throws IOException {
        Call<List<DepartmentResponseDto>> response = departmentUtilApi.getAllDepartment();
        return response.execute().body();
    }

    public List<EmployeeResponseDto> getEmployeesList() throws IOException {
        Call<List<EmployeeResponseDto>> response = employeeUtilApi.getAllEmployee();
        return response.execute().body();
    }

    public List<CompanyResponseDto> getCompaniesList() throws IOException {
        Call<List<CompanyResponseDto>> response = companyUtilApi.getAllCompany();
        return response.execute().body();
    }


    public void showDepartments() throws IOException {
        ObservableList<DepartmentResponseDto> list =
                FXCollections.observableArrayList(getDepartmentsList());
        colId.setCellValueFactory(new PropertyValueFactory<DepartmentResponseDto, Long>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<DepartmentResponseDto, String>("name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<DepartmentResponseDto, String>("location"));
        colWebSite.setCellValueFactory(new PropertyValueFactory<DepartmentResponseDto, String>("website"));
        colHead.setCellValueFactory(new PropertyValueFactory<DepartmentResponseDto, String>("head"));
        colCompany.setCellValueFactory(new PropertyValueFactory<DepartmentResponseDto, String>("company"));

        tvDepartment.setItems(list);
    }

    private void insertRecord() throws IOException {
        String comma = ", ";
        String[] companyData = cbCompany.getValue().split(comma);
        String[] employeeData = cbHead.getValue().split(comma);

        DepartmentRequestDto request = new DepartmentRequestDto();
        request.setName(tfName.getText());
        request.setLocation(tfLocation.getText());
        request.setWebsite(tfWebSite.getText());
        request.setCompany(companyData[1]);
        request.setHead(Long.valueOf(employeeData[0]));

        Call<DepartmentResponseDto> response = departmentUtilApi.saveDepartment(request);
        System.out.println(response.request());
        System.out.println(response.execute().body());
        showDepartments();
    }

    private void updateRecord() throws IOException {
        String comma = ", ";
        String[] companyData = cbCompany.getValue().split(comma);
        String[] employeeData = cbHead.getValue().split(comma);

        DepartmentRequestDto request = new DepartmentRequestDto();
        request.setId(Long.valueOf(tfId.getText()));
        request.setName(tfName.getText());
        request.setLocation(tfLocation.getText());
        request.setWebsite(tfWebSite.getText());
        request.setCompany(companyData[1]);
        request.setHead(Long.valueOf(employeeData[0]));

        Call<DepartmentResponseDto> response = departmentUtilApi.updateDepartmentData(request.getId(), request);
        System.out.println(response.request());
        System.out.println(response.execute().body());
        showDepartments();
    }

    private void deleteButton() throws IOException {
        Call<String> response = departmentUtilApi.deleteDepartment(Long.valueOf(tfId.getText()));
        System.out.println(response.request());
        System.out.println(response.execute().body());
        showDepartments();
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
