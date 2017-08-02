package com.ebanx.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.ebanx.domain.Payment;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;

@Component
@Path("/api")
public class PaymentController {
	
	@GET
	@Produces("application/json")
	public String test(){
		return "Test ok";
	}

	@POST
	@Path("/topup")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Charge makeTopUp(Payment payment){
		Stripe.apiKey = "sk_test_0lfcCHXhvgbdNk3EXMjJuwnU";
		Charge charge = new Charge();
		Map<String, Object> chargeParams = new HashMap<String, Object>();
		chargeParams.put("amount", payment.getAmount());
		chargeParams.put("currency", payment.getCurrency());
		chargeParams.put("source", payment.getToken()); 
		chargeParams.put("description", "diogenes@ebanx.com");

		RequestOptions options = RequestOptions
		  .builder()
		  .setIdempotencyKey(UUID.randomUUID().toString())
		  .build();

		
		try {
			charge = Charge.create(chargeParams, options);
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | CardException | APIException e) {
			e.printStackTrace();
		}
		
		return charge;
	}
}
