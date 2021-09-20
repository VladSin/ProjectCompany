package com.example.projectCompany.controller.fxml;

import com.example.projectCompany.controller.CompanyUtilApi;
import com.example.projectCompany.controller.config.CompanyApiConfig;
import com.example.projectCompany.dto.request.CompanyRequestDto;
import com.example.projectCompany.dto.response.CompanyResponseDto;
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
@FxmlView("/fxml/mainCompany.fxml")
public class CompanyFxmlController implements Initializable {

    private final CompanyUtilApi api = CompanyApiConfig.getApi();

    public CompanyFxmlController() {
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
    private TextField tfBudget;

    @FXML
    private TableView<CompanyResponseDto> tvCompany;
    @FXML
    private TableColumn<CompanyResponseDto, Long> colId;
    @FXML
    private TableColumn<CompanyResponseDto, String> colName;
    @FXML
    private TableColumn<CompanyResponseDto, String> colLocation;
    @FXML
    private TableColumn<CompanyResponseDto, String> colWebSite;
    @FXML
    private TableColumn<CompanyResponseDto, Double> colBudget;

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

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showCompanies();
    }

    public void showCompanies() throws IOException {
        ObservableList<CompanyResponseDto> list =
                FXCollections.observableArrayList(getCompaniesList());

        colId.setCellValueFactory(new PropertyValueFactory<CompanyResponseDto, Long>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<CompanyResponseDto, String>("name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<CompanyResponseDto, String>("location"));
        colWebSite.setCellValueFactory(new PropertyValueFactory<CompanyResponseDto, String>("website"));
        colBudget.setCellValueFactory(new PropertyValueFactory<CompanyResponseDto, Double>("budget"));

        tvCompany.setItems(list);
    }

    public List<CompanyResponseDto> getCompaniesList() throws IOException {
        Call<List<CompanyResponseDto>> response = api.getAllCompany();
        return response.execute().body();
    }

    private void insertRecord() throws IOException {
        CompanyRequestDto request = new CompanyRequestDto();
        request.setName(tfName.getText());
        request.setLocation(tfLocation.getText());
        request.setWebsite(tfWebSite.getText());
        request.setBudget(Double.parseDouble(tfBudget.getText()));

        Call<CompanyResponseDto> response = api.saveCompany(request);
        System.out.println(response.request());
        System.out.println(response.execute().body());
        showCompanies();
    }

    private void updateRecord() throws IOException {
        CompanyRequestDto request = new CompanyRequestDto();
        request.setId(Long.parseLong(tfId.getText()));
        request.setName(tfName.getText());
        request.setLocation(tfLocation.getText());
        request.setWebsite(tfWebSite.getText());
        request.setBudget(Double.parseDouble(tfBudget.getText()));

        Call<CompanyResponseDto> response = api.updateCompanyData(request.getId(), request);
        System.out.println(response.request());
        System.out.println(response.execute().body());
        showCompanies();
    }

    private void deleteButton() throws IOException {
        Call<String> response = api.deleteCompany(Long.parseLong(tfId.getText()));
        System.out.println(response.request());
        System.out.println(response.execute().body());
        showCompanies();
    }

    @FXML
    public void menuHandleButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == miHome) {
            redirectToAnotherWindow(event, "/fxml/index.fxml");
        } else if (event.getSource() == miReport) {
            redirectToAnotherWindow(event, "/fxml/index.fxml");
        } else if (event.getSource() == miMore) {
            redirectToAnotherWindow(event, "/fxml/infoCompany.fxml");
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
