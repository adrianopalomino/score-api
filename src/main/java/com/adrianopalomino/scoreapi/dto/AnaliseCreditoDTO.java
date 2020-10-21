package com.adrianopalomino.scoreapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.adrianopalomino.scoreapi.domain.enums.StatusAnalise;

public class AnaliseCreditoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private PessoaDTO pessoa;
	private int score;
	private BigDecimal resultado;
	private StatusAnalise statusAnalise;

	public AnaliseCreditoDTO(PessoaDTO pessoa, int score, BigDecimal resultado, Integer statusAnalise) {
		this.pessoa = new PessoaDTO(pessoa.getNome(), pessoa.getIdade(), pessoa.getCpf(), pessoa.getDependentes(),
				pessoa.getRenda());
		this.score = score;
		this.resultado = resultado;
		this.statusAnalise = StatusAnalise.toEnum(statusAnalise);
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

	public StatusAnalise getStatusAnalise() {
		return statusAnalise;
	}

	public void setStatusAnalise(StatusAnalise statusAnalise) {
		this.statusAnalise = statusAnalise;
	}

	@Override
	public String toString() {
		return "AnaliseCreditoDTO [pessoa=" + pessoa + ", score=" + score + ", resultado=" + resultado
				+ ", statusAnalise=" + statusAnalise + "]";
	}

}
