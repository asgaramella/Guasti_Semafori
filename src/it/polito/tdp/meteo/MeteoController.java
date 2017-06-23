package it.polito.tdp.meteo;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.meteo.bean.Model;
import it.polito.tdp.meteo.bean.Stat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class MeteoController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> boxCitta;

    @FXML
    private Button btnElencoDate;

    @FXML
    private Button btnSimula;

    @FXML
    private TextField txtProbabilita;

    @FXML
    private TextArea txtResult;

    @FXML
    private TextField txtTecnici;
    
    @FXML
    void doElencoDate(ActionEvent event) {
    	txtResult.clear();
    	String l=this.boxCitta.getValue();
    	if(l ==null){
    		txtResult.appendText("ERRORE: Scegliere Localita !");
    		return;
    	}
    	
    	for(Stat stemp:model.getDateforCitta(l)){
    		txtResult.appendText(stemp.getOggi().toString()+" T max "+stemp.getTempMax()+"° giorno prec "+stemp.getIeri().toString()+" "+stemp.getTempIeri()+"° \n");
    	}

    }

    @FXML
    void doSimula(ActionEvent event) {
    	
    	String tecnici=this.txtTecnici.getText();
    	if(tecnici==null){
    		txtResult.appendText("ERRORE: Inserire num tecnici");
    		return;
    	}
    	int T;
    	try{
    	T=Integer.parseInt(tecnici);
    	}catch(NumberFormatException e){
    		txtResult.appendText("ERRORE:Inserire un numero !");
    		return;
    	}
    	
    	String prob=this.txtProbabilita.getText();
    	if(prob==null){
    		txtResult.appendText("ERRORE: Inserire una probabilità");
    		return;
    	}
    	 double p;
    	 try{
    	    	p=Double.parseDouble(prob);
    	    	}catch(NumberFormatException e){
    	    		txtResult.appendText("ERRORE:Inserire un numero !");
    	    		return;
    	    	}
    	 
    	 txtResult.appendText("Costo complessivo manutenzione: "+model.getCostoTot(T,p)+" €\n");
    	

    }


    @FXML
    void initialize() {
        assert boxCitta != null : "fx:id=\"boxCitta\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert btnElencoDate != null : "fx:id=\"btnElencoDate\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert txtProbabilita != null : "fx:id=\"txtProbabilita\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Meteo.fxml'.";
        assert txtTecnici != null : "fx:id=\"txtTecnici\" was not injected: check your FXML file 'Meteo.fxml'.";


    }


	public void setModel(Model model) {
		this.model=model;
		this.boxCitta.getItems().clear();
		this.boxCitta.getItems().addAll(model.getAllLocalita());
		
		
		
	}

}
