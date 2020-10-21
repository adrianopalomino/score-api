package com.adrianopalomino.scoreapi.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.adrianopalomino.scoreapi.dto.AnaliseCreditoDTO;
import com.adrianopalomino.scoreapi.exceptions.ObjectNotFoundException;

@Service
public class AnaliseCreditoService {

	public AnaliseCreditoDTO analisar(final String nome, final int idade, final String cpf, final int dependentes,
			final BigDecimal renda) {
		
		throw new ObjectNotFoundException("Usuário não encontrado!");
	}

}
