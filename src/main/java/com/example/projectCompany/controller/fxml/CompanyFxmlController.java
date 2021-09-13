package com.example.projectCompany.controller.fxml;

import com.example.projectCompany.controller.CompanyUtilApi;
import com.example.projectCompany.controller.config.CompanyApiConfig;
import com.example.projectCompany.entity.Company;
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

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("mainCompany.fxml")
public class CompanyFxmlController {

    private final CompanyUtilApi api = CompanyApiConfig.getApi();

    public CompanyFxmlController() {
    }

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
    private TableView<Company> tvCompany;
    @FXML
    private TableColumn<Company, Long> colId;
    @FXML
    private TableColumn<Company, String> colName;
    @FXML
    private TableColumn<Company, String> colLocation;
    @FXML
    private TableColumn<Company, String> colWebSite;
    @FXML
    private TableColumn<Company, Double> colBudget;

    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if(event.getSource() == btnInsert){
            insertRecord();
        }else if (event.getSource() == btnUpdate){
            updateRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
        }

    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        showCompanies();
    }

    public ObservableList<Company> getCompaniesList() {
        return null;
    }

    public void showCompanies() {
        ObservableList<Company> list = getCompaniesList();

        colId.setCellValueFactory(new PropertyValueFactory<Company, Long>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Company, String>("name"));
        colLocation.setCellValueFactory(new PropertyValueFactory<Company, String>("location"));
        colWebSite.setCellValueFactory(new PropertyValueFactory<Company, String>("website"));
        colBudget.setCellValueFactory(new PropertyValueFactory<Company, Double>("budget"));

        tvCompany.setItems(list);
    }

    private void insertRecord() {
        showCompanies();
    }

    private void updateRecord() {
        showCompanies();
    }

    private void deleteButton() {
        showCompanies();
    }
}
