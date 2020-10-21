package com.adrianopalomino.scoreapi.domain.converters;

import com.adrianopalomino.scoreapi.domain.AnaliseCredito;
import com.adrianopalomino.scoreapi.dto.AnaliseCreditoDTO;
import com.adrianopalomino.scoreapi.dto.PessoaDTO;

public class AnaliseCreditoConverter extends ConverterBase<AnaliseCreditoDTO, AnaliseCredito> {

	public AnaliseCreditoConverter() {
		super(AnaliseCreditoConverter::convertToEntity, AnaliseCreditoConverter::convertToDto);
	}

	private static AnaliseCreditoDTO convertToDto(AnaliseCredito analiseCredito) {
		return new AnaliseCreditoDTO(
				new PessoaDTO(analiseCredito.getPessoa().getNome(), analiseCredito.getPessoa().getIdade(),
						analiseCredito.getPessoa().getCpf(), analiseCredito.getPessoa().getDependentes(),
						analiseCredito.getPessoa().getRenda()),
				analiseCredito.getScore(), analiseCredito.getResultado(), analiseCredito.getStatusAnalise().getCod());
	}

	// TODO: IMPLEMENT IF NECESSARY
	private static AnaliseCredito convertToEntity(AnaliseCreditoDTO dto) {
		return null;
	}

}
