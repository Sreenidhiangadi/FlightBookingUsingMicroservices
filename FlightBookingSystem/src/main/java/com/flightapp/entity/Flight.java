package com.flightapp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Data
public class Flight {

	  @Id
	     @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	  @NotBlank(message="Airline name is required")
	  private String airline;
	  @NotBlank(message="From place is required")
	  private String fromPlace;
	  @NotBlank(message="To place is required")
	  private String toPlace;
	  
	  @NotNull(message="Departure time  is required")
	  private LocalDateTime departureTime;
	  @NotNull(message="Arrival time  is required")
	  private LocalDateTime arrivalTime;
	  @Min(value=1,message="Price must be atleast 1")
	  private int price;
	  
	  @Min(value=1,message="Total seats must be atleast 1")
	  private int totalSeats;
	  @Min(value=0,message="Available seats cant be neagtive")
	  private int availableSeats;
	  @OneToMany(mappedBy = "departureFlight")
	  @JsonManagedReference
	    private List<Ticket> departureTickets=new ArrayList<>();

	    @OneToMany(mappedBy = "returnFlight")
	    @JsonManagedReference
	    private List<Ticket> returnTickets=new ArrayList<>();
	  
}
