package com.adrianopalomino.scoreapi.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.adrianopalomino.scoreapi.domain.Pessoa;
import com.adrianopalomino.scoreapi.exceptions.ObjectNotFoundException;

@Service
public class IntegracaoScoreService {

	private static final Logger LOG = LoggerFactory.getLogger(IntegracaoScoreService.class);

	private Map<Pessoa, Integer> scoreMap = new HashMap<>();

	public IntegracaoScoreService() {
		scoreMap.put(new Pessoa(null, "Pedro", 20, "92388006007", 0, new BigDecimal(5000.00)), 350);
		scoreMap.put(new Pessoa(null, "João", 18, "70060842024", 0, new BigDecimal(1045.00)), 600);
		scoreMap.put(new Pessoa(null, "José", 25, "10406392080", 1, new BigDecimal(3000.00)), 450);
		scoreMap.put(new Pessoa(null, "Mateus", 53, "28340240064", 2, new BigDecimal(10000.00)), 880);
		scoreMap.put(new Pessoa(null, "Maria", 65, "74384183089", 0, new BigDecimal(2000.00)), 800);
		scoreMap.put(new Pessoa(null, "Fernanda", 32, "76898015010", 1, new BigDecimal(6000.00)), 300);
	}

	public Integer recuperarScore(final Pessoa pessoa) {
		LOG.info(" ###  Inicio da recuperação do score " + pessoa.getCpf());
		// este mapa simula a integração com o recurso de score
		// a integração seria implementada neste ponto
		
		if(scoreMap.get(pessoa) == null) {
			throw new ObjectNotFoundException("Dados inválidos");
		}
		return scoreMap.get(pessoa);
	}
}
