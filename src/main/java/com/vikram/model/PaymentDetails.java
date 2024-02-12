package com.vikram.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDetails {

	private String paymentMethod;
	
	private String status;
	
	private String paymentId;
	
	private String razorpayPaymentLinkId;
	
	private String razorpayPaymentLinkRefrenceId;
	
	private String razorpayPaymentLinkStatus;
	
	private String razorpayPaymentId;
	
	
	
}
