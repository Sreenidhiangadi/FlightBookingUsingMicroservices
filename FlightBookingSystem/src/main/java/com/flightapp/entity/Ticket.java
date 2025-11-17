package com.flightapp.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Data
public class Ticket {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	@NotBlank(message = "PNR cannot be blank")
	@Column(unique=true)
	private String pnr;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	@JsonBackReference
    private User user;
	
	@NotNull(message = "Departure flight is required")
    @ManyToOne
    @JoinColumn(name = "departure_flight_id")
	@JsonBackReference
    private Flight departureFlight;
	
    @ManyToOne
    @JoinColumn(name = "return_flight_id")
    @JsonBackReference
    private Flight returnFlight;
    
    @Enumerated(EnumType.STRING)
    private FlightType tripType;
	private LocalDateTime bookingTime;
	private int numberOfSeats;
	private String mealType;
    private Double totalPrice;
    private boolean canceled = false;
    
    @OneToMany(mappedBy = "ticket", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Passenger> passengers = new ArrayList<>();

	 
}
