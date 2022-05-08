/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c19project;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pojo.Covid;
import static pojo.Covid.listByLocation;
import static pojo.Covid.listCovidsFiltree;

/**
 *
 * @author Leopard
 */
public class FXMLDocumentController implements Initializable {
    
     //table properties
   @FXML
    private TableView<Covid> tableAffichage;


    @FXML
    private TableColumn<Covid, String> continent;

    @FXML
    private TableColumn<Covid, String> location;

    @FXML
    private TableColumn<Covid, String> date;

    @FXML
    private TableColumn<Covid, String> total_cases;

    @FXML
    private TableColumn<Covid, String> total_deaths;

    @FXML
    private TableColumn<Covid, String> total_tests;

    @FXML
    private TableColumn<Covid, String> total_vaccinations;
    
    
    //checkboxes
    @FXML
    private ComboBox continentsBox;

    @FXML
    private ComboBox locationsBox;
    
    @FXML
    private RadioButton historique;
    
    
    
    
    
    
    
    
    
    //----------------
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listContinents= FXCollections.observableArrayList(Covid.listContinents());
        ObservableList<String> listLocations= FXCollections.observableArrayList(Covid.listLocations("ALL"));
        showALL(Covid.listCovids());
        continentsBox.setItems(listContinents);
       continentsBox.getItems().add("ALL");
        locationsBox.setItems(listLocations);
        locationsBox.getItems().add("ALL");
        continentsBox.getSelectionModel().selectLast();
        locationsBox.getSelectionModel().selectLast();
        
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        continent.setCellValueFactory(new PropertyValueFactory<>("continent"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        total_cases.setCellValueFactory(new PropertyValueFactory<>("totalCases"));
        total_deaths.setCellValueFactory(new PropertyValueFactory<>("totalDeaths"));
        total_tests.setCellValueFactory(new PropertyValueFactory<>("totalTests"));
        total_vaccinations.setCellValueFactory(new PropertyValueFactory<>("totalVaccinations"));
        
       
    }    
    
     private void showALL(List list) {
        ObservableList<Covid> l= FXCollections.observableArrayList(list);
        tableAffichage.setItems(l);
    }
     
     public void continentBox(ActionEvent event){
         String c = continentsBox.getValue().toString();
         ObservableList<String> listLocations= FXCollections.observableArrayList(Covid.listLocations(c));
         locationsBox.setItems(listLocations);
         locationsBox.getItems().add("ALL");
         locationsBox.getSelectionModel().selectLast();
        showALL(Covid.listByContinents(c));
     }
     
     public void locationBox(ActionEvent event){
         if(locationsBox.isShowing()){
         String l = locationsBox.getValue().toString();
         showALL(Covid.listByLocation(l));
         }
         else {
         String l = "ALL";
         showALL(Covid.listByLocation(l));
         }

     }
     
     public void historique(ActionEvent event){
          System.out.println(continentsBox.getValue().toString());
         System.out.println(locationsBox.getValue().toString());
         System.out.println(historique.isSelected());
         showALL(listCovidsFiltree(continentsBox.getValue().toString(),locationsBox.getValue().toString(),historique.isSelected()));
        

     }
     
     
     
     private void showALLAfterFiltering() {
        String c = continentsBox.getValue().toString();
        String l = locationsBox.getValue().toString();
        boolean isSelected = historique.isSelected();
         
         
        // ObservableList<Covid> l= FXCollections.observableArrayList(Covid.listCovids());
       // tableAffichage.setItems(l);
    }
     
}
