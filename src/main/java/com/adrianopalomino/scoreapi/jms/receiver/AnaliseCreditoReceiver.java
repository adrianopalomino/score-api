package com.adrianopalomino.scoreapi.jms.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AnaliseCreditoReceiver {
	
	@JmsListener(destination = "analisecredito", containerFactory = "myFactory")
	public void receiveMessage(Long idAnalise) {
		System.out.println("Received <" + idAnalise + ">");
		
		throw new NullPointerException();
	}

}
