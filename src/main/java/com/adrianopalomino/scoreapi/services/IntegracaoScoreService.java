package com.adrianopalomino.scoreapi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.adrianopalomino.scoreapi.domain.Pessoa;

@Service
public class IntegracaoScoreService {

	private static final Logger LOG = LoggerFactory.getLogger(IntegracaoScoreService.class);

	public Long recuperarScore(final Pessoa pessoa) {
		LOG.info(" ###  Inicio da recuperação do score " + pessoa.getCpf());
		return null;
	}
}
