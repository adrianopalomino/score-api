package com.adrianopalomino.scoreapi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adrianopalomino.scoreapi.dto.AnaliseCreditoDTO;
import com.adrianopalomino.scoreapi.services.PessoaService;

@RestController
@RequestMapping(value="/pessoas")
public class CategoriaResource {
	
	@Autowired
	private PessoaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<AnaliseCreditoDTO> find(@PathVariable Integer id) {
//		Categoria obj = service.find(id);
//		return ResponseEntity.ok().body(obj);
		return null;
	}
	
	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<AnaliseCreditoDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="nome") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
//		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
//		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));  
//		return ResponseEntity.ok().body(listDto);
		return null;
	}
}
