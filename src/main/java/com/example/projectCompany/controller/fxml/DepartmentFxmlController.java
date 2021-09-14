package com.example.projectCompany.controller.fxml;

import com.example.projectCompany.controller.DepartmentUtilApi;
import com.example.projectCompany.controller.config.DepartmentApiConfig;
import com.example.projectCompany.dto.request.DepartmentRequestDto;
import com.example.projectCompany.dto.response.DepartmentResponseDto;
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
@FxmlView("mainDepartment.fxml")
public class DepartmentFxmlController {

    private final DepartmentUtilApi api = DepartmentApiConfig.getApi();

    public DepartmentFxmlController() {
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
    private TextField tfCompany;

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
        showDepartments();
    }

    public void showDepartments() throws IOException {
        ObservableList<DepartmentResponseDto> list = (ObservableList<DepartmentResponseDto>) getDepartmentsList();

        colId.setCellValueFactory(new PropertyValueFactory<DepartmentResponseDto, Long>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<DepartmentResponseDto, String>("name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<DepartmentResponseDto, String>("location"));
        colWebSite.setCellValueFactory(new PropertyValueFactory<DepartmentResponseDto, String>("website"));
        colCompany.setCellValueFactory(new PropertyValueFactory<DepartmentResponseDto, String>("company"));

        tvDepartment.setItems(list);
    }

    public List<DepartmentResponseDto> getDepartmentsList() throws IOException {
        Call<List<DepartmentResponseDto>> response = api.getAllDepartment();
        return response.execute().body();
    }

    private void insertRecord() throws IOException {
        DepartmentRequestDto request = new DepartmentRequestDto();
        request.setName(tfName.getText());
        request.setLocation(tfLocation.getText());
        request.setWebsite(tfWebSite.getText());
        request.setCompany(tfCompany.getText());

        api.saveDepartment(request);
        showDepartments();
    }

    private void updateRecord() throws IOException {
        DepartmentRequestDto request = new DepartmentRequestDto();
        request.setId(Long.valueOf(tfId.getText()));
        request.setName(tfName.getText());
        request.setLocation(tfLocation.getText());
        request.setWebsite(tfWebSite.getText());
        request.setCompany(tfCompany.getText());

        api.updateDepartmentData(request.getId(), request);
        showDepartments();
    }

    private void deleteButton() throws IOException {
        api.deleteDepartment(Long.valueOf(tfId.getText()));
        showDepartments();
    }

    @FXML
    public void menuHandleButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == miHome) {
            redirectToAnotherWindow(event, "/fxml/index.fxml");
        } else if (event.getSource() == miReport) {
            redirectToAnotherWindow(event, "/fxml/index.fxml");
        } else if (event.getSource() == miMore) {
            redirectToAnotherWindow(event, "/fxml/infoDepartment.fxml");
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
