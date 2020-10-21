package com.adrianopalomino.scoreapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class AnaliseCreditoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private PessoaDTO pessoa;
	private int score;
	private BigDecimal resultado;

	public AnaliseCreditoDTO(PessoaDTO pessoa, int score, BigDecimal resultado) {
		this.pessoa = new PessoaDTO(pessoa.getNome(), pessoa.getIdade(), pessoa.getCpf(), pessoa.getDependentes(),
				pessoa.getRenda());
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

	@Override
	public String toString() {
		return "AnaliseCreditoDTO [pessoa=" + pessoa + ", score=" + score + ", resultado=" + resultado + "]";
	}

}
