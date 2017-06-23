package it.polito.tdp.meteo.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {
	
	private MeteoDAO dao;
	private List<Situazione> situazioni;
	private List<Stat> date;
	private Simulator sim;

	public Model() {
		super();
		dao=new MeteoDAO();
	}
	
	public List<String> getAllLocalita(){
		return dao.getAllLocalita();
	}
	
	public List<Stat> getDateforCitta(String s){
		situazioni= dao.getAllSituaForCitta(s);
		date=new ArrayList<>();
		
		for(int i=1; i<situazioni.size();i++){
			LocalDate ieri=situazioni.get(i).getData().minusDays(1);
			int tempIeri=dao.getTempMaxIeri(ieri,s);
			if(situazioni.get(i).getTMax()>=tempIeri+2)
				date.add(new Stat(situazioni.get(i).getData(),situazioni.get(i).getTMax(),ieri,tempIeri));
		}
		
		return date;
	}

	public int getCostoTot(int t, double p) {
		sim=new Simulator(date,t,p);
		return sim.getCosto();
	}
	
	
	

}
