package com.example.projectCompany.controller.fxml;

import com.example.projectCompany.controller.DepartmentUtilApi;
import com.example.projectCompany.controller.config.DepartmentApiConfig;
import com.example.projectCompany.dto.response.DepartmentResponseDto;
import javafx.collections.FXCollections;
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
import java.util.ArrayList;
import java.util.List;

@Component
@FxmlView("/fxml/infoDepartment.fxml")
public class ExpansionDepartmentFxmlController {

    private final DepartmentUtilApi api = DepartmentApiConfig.getApi();

    public ExpansionDepartmentFxmlController() {
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
    private TextField tfGetByName;
    @FXML
    private TextField tfGetByLocation;

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
    private Button btnGetAll;
    @FXML
    private Button btnGetById;
    @FXML
    private Button btnGetByName;
    @FXML
    private Button btnGetByLocation;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == btnGetAll) {
            getAll();
        } else if (event.getSource() == btnGetById) {
            getById();
        } else if (event.getSource() == btnGetByName) {
            getByName();
        } else if (event.getSource() == btnGetByLocation) {
            getAllByLocation();
        }

    }

    @FXML
    public void menuHandleButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == miHome) {
            redirectToAnotherWindow(event, "/fxml/index.fxml");
        } else if (event.getSource() == miReport) {
            openWebpage("http://localhost:8080/export/departments/excel");
        } else if (event.getSource() == miBack) {
            redirectToAnotherWindow(event, "/fxml/mainDepartment.fxml");
        }

    }

    @FXML
    public static void openWebpage(String urlString) throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("rundll32 url.dll,FileProtocolHandler " + urlString);
    }

    private void getAll() throws IOException {
        ObservableList<DepartmentResponseDto> list =
                FXCollections.observableArrayList(getDepartmentsList());

        showDepartments(list);
    }

    private void getById() throws IOException {
        ObservableList<DepartmentResponseDto> list =
                FXCollections.observableArrayList(getDepartmentById());

        showDepartments(list);
    }

    private void getByName() throws IOException {
        ObservableList<DepartmentResponseDto> list =
                FXCollections.observableArrayList(getDepartmentByName());

        showDepartments(list);
    }

    private void getAllByLocation() throws IOException {
        ObservableList<DepartmentResponseDto> list =
                FXCollections.observableArrayList(getDepartmentsListByLocation());

        showDepartments(list);
    }

    public List<DepartmentResponseDto> getDepartmentsList() throws IOException {
        Call<List<DepartmentResponseDto>> response = api.getAllDepartment();
        return response.execute().body();
    }

    public List<DepartmentResponseDto> getDepartmentById() throws IOException {
        Call<DepartmentResponseDto> response = api.getDepartmentById(Long.valueOf(tfGetById.getText()));
        List<DepartmentResponseDto> list = new ArrayList<>();
        list.add(response.execute().body());
        return list;
    }

    public List<DepartmentResponseDto> getDepartmentByName() throws IOException {
        Call<DepartmentResponseDto> response = api.getDepartmentByName(tfGetByName.getText());
        List<DepartmentResponseDto> list = new ArrayList<>();
        list.add(response.execute().body());
        return list;
    }

    public List<DepartmentResponseDto> getDepartmentsListByLocation() throws IOException {
        Call<List<DepartmentResponseDto>> response = api.getAllDepartmentByLocation(tfGetByLocation.getText());
        return response.execute().body();
    }


    private void showDepartments(ObservableList<DepartmentResponseDto> list) {
        colId.setCellValueFactory(new PropertyValueFactory<DepartmentResponseDto, Long>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<DepartmentResponseDto, String>("name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<DepartmentResponseDto, String>("location"));
        colWebSite.setCellValueFactory(new PropertyValueFactory<DepartmentResponseDto, String>("website"));
        colCompany.setCellValueFactory(new PropertyValueFactory<DepartmentResponseDto, String>("company"));

        tvDepartment.setItems(list);
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
