package com.adrianopalomino.scoreapi.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.adrianopalomino.scoreapi.dto.AnaliseCreditoDTO;
import com.adrianopalomino.scoreapi.dto.PessoaDTO;
import com.adrianopalomino.scoreapi.services.AnaliseCreditoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/score-api/v1/analises")
@Api(value = "score-api/v1/analises")
public class AnaliseCreditoResource {

	@Autowired
	private AnaliseCreditoService analiseCreditoService;

	@ApiOperation(value = "Recurso para processar analise de crédito pessoa")
	@ApiResponses(value = { @ApiResponse(code = 202, message = "Análise em processamento"),
							@ApiResponse(code = 400, message = "Requisição inválida"), })
	@PostMapping
	public ResponseEntity<Void> analisar(@Valid @RequestBody PessoaDTO pessoaDTO) throws InterruptedException {
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(analiseCreditoService.enviarParaAnalise(pessoaDTO)).toUri();
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(HttpHeaders.LOCATION, location.toString()).build();
	}

	@ApiOperation(value = "Recurso para recuperar análise de crédito pessoa")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Requisição inválida"), })
	@GetMapping(value = "/{id}")
	public ResponseEntity<AnaliseCreditoDTO> recuperarAnalise(@PathVariable Long id) throws InterruptedException {
		return ResponseEntity.ok().body(analiseCreditoService.recuperarAnalise(id));
	}
}
