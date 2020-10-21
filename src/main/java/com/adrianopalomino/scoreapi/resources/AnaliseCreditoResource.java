package com.adrianopalomino.scoreapi.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.adrianopalomino.scoreapi.dto.PessoaDTO;
import com.adrianopalomino.scoreapi.services.AnaliseCreditoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/analises")
@Api(value = "analises")
public class AnaliseCreditoResource {

	@Autowired
	private AnaliseCreditoService analiseCreditoService;

	@ApiOperation(value = "Analise de credito")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Análise em processamento"), })
	@PostMapping(value = "/creditos")
	public ResponseEntity<Void> analisar(@RequestBody PessoaDTO pessoaDTO) throws InterruptedException {
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(analiseCreditoService.analisar(pessoaDTO)).toUri();
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(HttpHeaders.LOCATION, location.toString()).build();
	}
}
