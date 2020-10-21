package com.adrianopalomino.scoreapi.jms.receiver;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.adrianopalomino.scoreapi.domain.AnaliseCredito;
import com.adrianopalomino.scoreapi.repositories.AnaliseCreditoRepository;

@Component
public class AnaliseCreditoReceiver {

	private static final Logger LOG = LoggerFactory.getLogger(AnaliseCreditoReceiver.class);

	@Autowired
	private AnaliseCreditoRepository analiseCreditoRepository;

	@JmsListener(destination = "analisecredito", containerFactory = "myFactory")
	public void receiveMessage(Long idAnalise) {
		LOG.info(" ### Processando a análise" + idAnalise);

		try {
			Optional<AnaliseCredito> analiseCredito = analiseCreditoRepository.findById(idAnalise);

			LOG.info(" ### Received" + analiseCredito);

		} catch (Exception e) {
			LOG.error(" ### Erro no processamento  da análise" + idAnalise);
			throw e;
		}

	}

}
