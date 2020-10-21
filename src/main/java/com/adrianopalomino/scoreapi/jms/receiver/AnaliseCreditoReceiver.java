package com.adrianopalomino.scoreapi.jms.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.adrianopalomino.scoreapi.services.AnaliseCreditoService;

@Component
public class AnaliseCreditoReceiver {

	private static final Logger LOG = LoggerFactory.getLogger(AnaliseCreditoReceiver.class);

	@Autowired
	private AnaliseCreditoService analiseCreditoService;

	@JmsListener(destination = "analisecredito", containerFactory = "myFactory")
	public void receiveMessage(Long id) {
		LOG.info(" ### Mensagem recebida: " + id);
		analiseCreditoService.processarAnaliseScore(id);
	}

}
