package com.adrianopalomino.scoreapi.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrianopalomino.scoreapi.domain.Pessoa;
import com.adrianopalomino.scoreapi.repositories.PessoaRepository;


@Service
public class DBService {
	
	private static final Logger LOG = LoggerFactory.getLogger(DBService.class);
	
	@Autowired
	private PessoaRepository pessoaRepository;

	public void instantiateDatabase() throws ParseException {
		LOG.info(" ### Database init !");
		
		List<Pessoa> pessoas = new ArrayList<>();
		
		pessoas.add(new Pessoa(null, "Pedro", 20, "60699876087", 0, new BigDecimal(5000)));
		pessoas.add(new Pessoa(null, "João", 18, "05286440096", 0, new BigDecimal(1045)));
		pessoas.add(new Pessoa(null, "José", 25, "69528075002", 1, new BigDecimal(3000)));
		pessoas.add(new Pessoa(null, "Matheus", 53, "36746490041", 2, new BigDecimal(10000)));
		pessoas.add(new Pessoa(null, "Maria", 65, "60030752019", 0, new BigDecimal(2000)));
		pessoas.add(new Pessoa(null, "Fernanda", 32, "60030752019", 1, new BigDecimal(6000)));

		pessoaRepository.saveAll(pessoas);
	}
}
