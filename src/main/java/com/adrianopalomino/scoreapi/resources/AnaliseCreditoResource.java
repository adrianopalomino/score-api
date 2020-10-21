package com.adrianopalomino.scoreapi.resources;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adrianopalomino.scoreapi.dto.AnaliseCreditoDTO;
import com.adrianopalomino.scoreapi.services.AnaliseCreditoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/analises")
@Api(value = "analises")
public class AnaliseCreditoResource {
	
	@Autowired
	private AnaliseCreditoService analiseCreditoService;
		
	//over https - in production environment - all data will be encrypted
	@ApiOperation(value = "Analise de credito")
	@GetMapping(value = "/creditos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AnaliseCreditoDTO> analisar(
			@RequestParam(value="nome", required = true) String nome, 
			@RequestParam(value="idade", required = true) int idade,  
			@RequestParam(value="cpf", required = true) String cpf, 
			@RequestParam(value="dependentes") int dependentes,
			@RequestParam(value="renda", required = true) BigDecimal renda) {
		analiseCreditoService.analisar(nome, idade, cpf, dependentes, renda);
		return null;
	}
}
