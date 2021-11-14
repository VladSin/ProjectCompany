package com.example.projectCompany.controller.fxml;

import com.example.projectCompany.controller.CompanyUtilApi;
import com.example.projectCompany.controller.config.CompanyApiConfig;
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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
@FxmlView("/fxml/infoCompany.fxml")
public class ExpansionCompanyFxmlController implements Initializable {

    private final CompanyUtilApi api = CompanyApiConfig.getApi();

    public ExpansionCompanyFxmlController() {
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
    private TextField tfGetByName;
    @FXML
    private TextField tfGetByLocation;

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
            openWebpage("http://localhost:8080/export/companies/excel");
        } else if (event.getSource() == miBack) {
            redirectToAnotherWindow(event, "/fxml/company.fxml");
        }

    }

    @FXML
    public static void openWebpage(String urlString) throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec("rundll32 url.dll,FileProtocolHandler " + urlString);
    }

    private void getAll() throws IOException {
        ObservableList<CompanyResponseDto> list =
                FXCollections.observableArrayList(getCompaniesList());

        showCompanies(list);
    }

    private void getById() throws IOException {
        ObservableList<CompanyResponseDto> list =
                FXCollections.observableArrayList(getCompanyById());

        showCompanies(list);
    }

    private void getByName() throws IOException {
        ObservableList<CompanyResponseDto> list =
                FXCollections.observableArrayList(getCompanyByName());

        showCompanies(list);
    }

    private void getAllByLocation() throws IOException {
        ObservableList<CompanyResponseDto> list =
                FXCollections.observableArrayList(getCompaniesListByLocation());

        showCompanies(list);
    }

    public List<CompanyResponseDto> getCompaniesList() throws IOException {
        Call<List<CompanyResponseDto>> response = api.getAllCompany();
        return response.execute().body();
    }

    public List<CompanyResponseDto> getCompaniesListByLocation() throws IOException {
        Call<List<CompanyResponseDto>> response = api.getAllCompanyByLocation(tfGetByLocation.getText());
        return response.execute().body();
    }

    public List<CompanyResponseDto> getCompanyById() throws IOException {
        Call<CompanyResponseDto> response = api.getCompanyById(Long.valueOf(tfGetById.getText()));
        List<CompanyResponseDto> list = new ArrayList<>();
        list.add(response.execute().body());
        return list;
    }

    public List<CompanyResponseDto> getCompanyByName() throws IOException {
        Call<CompanyResponseDto> response = api.getCompanyByName(tfGetByName.getText());
        List<CompanyResponseDto> list = new ArrayList<>();
        list.add(response.execute().body());
        return list;
    }


    private void showCompanies(ObservableList<CompanyResponseDto> list) {
        colId.setCellValueFactory(new PropertyValueFactory<CompanyResponseDto, Long>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<CompanyResponseDto, String>("name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<CompanyResponseDto, String>("location"));
        colWebSite.setCellValueFactory(new PropertyValueFactory<CompanyResponseDto, String>("website"));
        colBudget.setCellValueFactory(new PropertyValueFactory<CompanyResponseDto, Double>("budget"));

        tvCompany.setItems(list);
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
