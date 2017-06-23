package it.polito.tdp.meteo.bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.meteo.bean.Event.EventType;

public class Simulator {
	
	private int costoTot;
	private int T;
	private double p;
	private PriorityQueue<Event> queue;
	private List<LocalDate> date;
	
	
	public Simulator(List<Stat> modelDate, int T, double p ) {
		super();
		this.p=p;
		this.T=T;
		date=new ArrayList<>();
		for(Stat stemp:modelDate){
			date.add(stemp.getOggi());
		}
		queue=new PriorityQueue<>();
		costoTot= T*20000;
	}
	
	public void run(){
		this.popolaCoda();
		while(!queue.isEmpty()){
			Event e=queue.poll();
			
			switch(e.getType()){
			
			case IN:
				//ho ancora operai
			  if(T>0){
				  T--;
				  Random r=new Random();
				  int giorni=r.nextInt(9)+2;
				  Event enew=new Event(e.getTime().plusDays(giorni),EventType.OUT);
				  queue.add(enew);
			  }else
				  //chiamo tecnico no evento di uscita
				  costoTot+=2000;
				
				break;
				
				
			case OUT:
				
				T++;
				
				break;
			
			
			}
		}
	}

	private void popolaCoda() {
		for(LocalDate d: date){
			double rnd=Math.random();
			if(rnd<=p){
				Event e=new Event(d,EventType.IN);
				queue.add(e);
			}
		}
		
	}
	
	public int getCosto(){
		this.run();
		return costoTot;
	}
	

}
