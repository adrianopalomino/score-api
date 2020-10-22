package com.adrianopalomino.scoreapi.domain.enums;

public enum StatusAnalise {

	EM_PROCESSAMENTO(1, "EM PROCESSAMENTO"), FINALIZADO(2, "FINALIZADO"), SCORE_INEXISTENTE(3, "SCORE INEXISTENTE");

	private int cod;
	private String descricao;

	private StatusAnalise(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusAnalise toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (StatusAnalise x : StatusAnalise.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Id inválido: " + cod);
	}

}
