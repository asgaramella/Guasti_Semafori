package it.polito.tdp.meteo.bean;

import java.time.LocalDate;

public class Stat {
	
	private LocalDate oggi;
	private int tempMax;
	private LocalDate ieri;
	private int tempIeri;
	
	
	
	
	public Stat(LocalDate oggi, int tempMax, LocalDate ieri, int tempIeri) {
		super();
		this.oggi = oggi;
		this.tempMax = tempMax;
		this.ieri = ieri;
		this.tempIeri = tempIeri;
	}
	
	
	public LocalDate getOggi() {
		return oggi;
	}
	public void setOggi(LocalDate oggi) {
		this.oggi = oggi;
	}
	public int getTempMax() {
		return tempMax;
	}
	public void setTempMax(int tempMax) {
		this.tempMax = tempMax;
	}
	public LocalDate getIeri() {
		return ieri;
	}
	public void setIeri(LocalDate ieri) {
		this.ieri = ieri;
	}
	public int getTempIeri() {
		return tempIeri;
	}
	public void setTempIeri(int tempIeri) {
		this.tempIeri = tempIeri;
	}
	
	
	

}
