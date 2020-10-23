package com.adrianopalomino.scoreapi.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;

public class PessoaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Nome da pessoa")
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 2 e 80 caracteres")
	private String nome;
	
	@ApiModelProperty(value = "Idade da pessoa")
	private int idade;
	
	@ApiModelProperty(value = "CPF da pessoa")
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=11, max=11, message="O tamanho deve ser de 11 caracteres")
	private String cpf;
	
	@ApiModelProperty(value = "Quantidade de dependentes da pessoa")
	private int dependentes;
	
	@ApiModelProperty(value = "Renda da pessoa")
	private BigDecimal renda;

	public PessoaDTO() {
		super();
	}

	public PessoaDTO(String nome, int idade, String cpf, int dependentes, BigDecimal renda) {
		super();
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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

	@Override
	public String toString() {
		return "PessoaDTO [nome=" + nome + ", idade=" + idade + ", cpf=" + cpf + ", dependentes=" + dependentes
				+ ", renda=" + renda + "]";
	}

}
