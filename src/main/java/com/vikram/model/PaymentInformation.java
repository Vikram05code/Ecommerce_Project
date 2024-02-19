package com.vikram.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructoro
public class PaymentInformation {

	@Column(name="cardholder_name")
	private String cardholderName;
	
	@Column(name="card_number")
	private String cardNumber;
	
	@Column(name="expiration_date")
	private LocalDate expirationDate;
	
	@Column(name="cvv")
	private String cvv;
	
	
	
}
