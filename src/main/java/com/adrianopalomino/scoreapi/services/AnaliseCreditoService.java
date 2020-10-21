package com.adrianopalomino.scoreapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.adrianopalomino.scoreapi.domain.AnaliseCredito;
import com.adrianopalomino.scoreapi.domain.Pessoa;
import com.adrianopalomino.scoreapi.domain.enums.StatusAnalise;
import com.adrianopalomino.scoreapi.dto.PessoaDTO;
import com.adrianopalomino.scoreapi.repositories.AnaliseCreditoRepository;

@Service
public class AnaliseCreditoService {

	private static final Logger LOG = LoggerFactory.getLogger(AnaliseCreditoService.class);

	@Autowired
	private AnaliseCreditoRepository analiseCreditoRepository;

	@Autowired
	private JmsTemplate jmsTemplate;

	public Long analisar(final PessoaDTO pessoaDTO) {
		LOG.info(" ###  Inicio da análise " + pessoaDTO.getCpf());

		final AnaliseCredito analiseCredito = analiseCreditoRepository.save(
				new AnaliseCredito(null, new Pessoa(null, pessoaDTO.getNome(), pessoaDTO.getIdade(), pessoaDTO.getCpf(),
						pessoaDTO.getDependentes(), pessoaDTO.getRenda()), 0, null, StatusAnalise.PENDENTE));

		// apenas o identificador será enviado para a fila para evitar o anti pattern
		// fat message
		jmsTemplate.convertAndSend("analisecredito", analiseCredito.getId());

		return analiseCredito.getId();
	}

}
