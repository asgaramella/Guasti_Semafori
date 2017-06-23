package it.polito.tdp.meteo.bean;

import java.time.LocalDate;

public class Event implements Comparable<Event> {
	
	public enum EventType{IN,OUT};
	
	private LocalDate time;
	private EventType type;
	
	public Event(LocalDate time, EventType type) {
		super();
		this.time = time;
		this.type = type;
	}









	public LocalDate getTime() {
		return time;
	}









	public void setTime(LocalDate time) {
		this.time = time;
	}









	public EventType getType() {
		return type;
	}









	public void setType(EventType type) {
		this.type = type;
	}









	@Override
	public int compareTo(Event other) {
	
		return this.time.compareTo(other.getTime());
	}
	
	

}
