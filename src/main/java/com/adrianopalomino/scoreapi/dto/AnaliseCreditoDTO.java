package com.adrianopalomino.scoreapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.adrianopalomino.scoreapi.domain.Pessoa;

public class AnaliseCreditoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private PessoaDTO pessoa;
	private int score;
	private BigDecimal resultado;

	public AnaliseCreditoDTO(Pessoa pessoa, int score, BigDecimal resultado) {
		this.pessoa = new PessoaDTO(pessoa.getNome(), pessoa.getIdade(), pessoa.getDependentes(), pessoa.getRenda());
		this.score = score;
		this.resultado = resultado;
	}

	public PessoaDTO getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaDTO pessoa) {
		this.pessoa = pessoa;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public BigDecimal getResultado() {
		return resultado;
	}

	public void setResultado(BigDecimal resultado) {
		this.resultado = resultado;
	}

}
