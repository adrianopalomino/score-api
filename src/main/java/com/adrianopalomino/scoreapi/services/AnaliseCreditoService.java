package com.adrianopalomino.scoreapi.services;

import java.math.BigDecimal;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.adrianopalomino.scoreapi.domain.AnaliseCredito;
import com.adrianopalomino.scoreapi.domain.Pessoa;
import com.adrianopalomino.scoreapi.domain.converters.AnaliseCreditoConverter;
import com.adrianopalomino.scoreapi.domain.enums.StatusAnalise;
import com.adrianopalomino.scoreapi.dto.AnaliseCreditoDTO;
import com.adrianopalomino.scoreapi.dto.PessoaDTO;
import com.adrianopalomino.scoreapi.exceptions.ObjectNotFoundException;
import com.adrianopalomino.scoreapi.repositories.AnaliseCreditoRepository;

@Service
public class AnaliseCreditoService {

	private static final Logger LOG = LoggerFactory.getLogger(AnaliseCreditoService.class);

	@Autowired
	private AnaliseCreditoRepository analiseCreditoRepository;

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private IntegracaoScoreService integracaoScoreService;

	public Long enviarParaAnalise(final PessoaDTO pessoaDTO) {
		LOG.info(" ###  Inicio da análise " + pessoaDTO.getCpf());

		final AnaliseCredito analiseCredito = analiseCreditoRepository.save(
				new AnaliseCredito(null, new Pessoa(null, pessoaDTO.getNome(), pessoaDTO.getIdade(), pessoaDTO.getCpf(),
						pessoaDTO.getDependentes(), pessoaDTO.getRenda()), 0, null, StatusAnalise.EM_PROCESSAMENTO));

		// apenas o identificador será enviado para a fila para evitar o anti pattern
		// fat message
		jmsTemplate.convertAndSend("analisecredito", analiseCredito.getId());

		return analiseCredito.getId();
	}

	@Transactional(value = TxType.NOT_SUPPORTED)
	public AnaliseCreditoDTO recuperarAnalise(final Long id) {
		var analiseCreditoConverter = new AnaliseCreditoConverter();
		return analiseCreditoConverter
				.convertFromEntity(analiseCreditoRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
						"Objeto não encontrado! Id: " + id + ", Tipo: " + AnaliseCreditoDTO.class.getName())));
	}

	public void processarAnaliseScore(final Long id) {
		
		var analiseCredito = analiseCreditoRepository.findById(id)
		.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + AnaliseCreditoDTO.class.getName()));
		
		try {
			var score = integracaoScoreService.recuperarScore(analiseCredito.getPessoa());
			analiseCredito.setScore(score);
			analiseCredito.setResultado(analisarPerfil(score));
			analiseCredito.setStatusAnalise(StatusAnalise.FINALIZADO);
		} catch (ObjectNotFoundException e) {
			LOG.error(" ### Dados invalidos");
			analiseCredito.setStatusAnalise(StatusAnalise.SCORE_INEXISTENTE);
		}
		
		analiseCreditoRepository.save(analiseCredito);
	}
	
	//para situações mais complexas e reais, eu utilizaria um padrão chamado 'polymorphism for conditional' example https://dzone.com/articles/using-polymorphism-instead
	//alem de, também, utilizar os dados do objeto pessoa como parâmetro de perfil
	private BigDecimal analisarPerfil(final Integer score) {
		BigDecimal result = new BigDecimal(0);

		if(score >= 450 && score <= 600) {
			result = new BigDecimal(500);
		}else if(score > 600 && score <= 800) {
			result = new BigDecimal(2500);
		}else if(score > 800) {
			result = new BigDecimal(5000);
		}else {
			return result;
		}

		return result;
	}

}
