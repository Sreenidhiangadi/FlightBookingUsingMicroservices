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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

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
	  @OneToMany
	  @JsonManagedReference("departure-flight")
	    private List<Ticket> departureTickets=new ArrayList<>();

	    @OneToMany
	    @JsonManagedReference("return-flight")
	    private List<Ticket> returnTickets=new ArrayList<>();

		

		
	  
}
