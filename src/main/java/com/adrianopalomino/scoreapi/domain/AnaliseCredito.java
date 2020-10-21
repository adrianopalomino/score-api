package com.adrianopalomino.scoreapi.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.adrianopalomino.scoreapi.domain.enums.StatusAnalise;

@Entity
public class AnaliseCredito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Pessoa pessoa;
	private int score;
	private BigDecimal resultado;
	private Integer statusAnalise;

	public AnaliseCredito() {
		super();
	}

	public AnaliseCredito(Long id, Pessoa pessoa, int score, BigDecimal resultado, StatusAnalise statusAnalise) {
		super();
		this.id = id;
		this.pessoa = pessoa;
		this.score = score;
		this.resultado = resultado;
		this.statusAnalise = statusAnalise == null ? null : statusAnalise.getCod();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
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
		return StatusAnalise.toEnum(statusAnalise);
	}

	public void setStatusAnalise(StatusAnalise statusAnalise) {
		this.statusAnalise = statusAnalise.getCod();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnaliseCredito other = (AnaliseCredito) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
