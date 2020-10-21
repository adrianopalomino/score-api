package com.adrianopalomino.scoreapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class PessoaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private int idade;
	private int dependentes;
	private BigDecimal renda;

	public PessoaDTO() {
		super();
	}

	public PessoaDTO(String nome, int idade, int dependentes, BigDecimal renda) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.dependentes = dependentes;
		this.renda = renda;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getDependentes() {
		return dependentes;
	}

	public void setDependentes(int dependentes) {
		this.dependentes = dependentes;
	}

	public BigDecimal getRenda() {
		return renda;
	}

	public void setRenda(BigDecimal renda) {
		this.renda = renda;
	}

}
