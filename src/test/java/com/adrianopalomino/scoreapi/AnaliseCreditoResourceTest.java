package com.adrianopalomino.scoreapi;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.adrianopalomino.scoreapi.dto.PessoaDTO;
import com.adrianopalomino.scoreapi.resources.AnaliseCreditoResource;
import com.adrianopalomino.scoreapi.services.AnaliseCreditoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(AnaliseCreditoResource.class)
@ContextConfiguration(classes = { JmsTemplate.class, JmsTemplate.class })
@WithMockUser(username = "scoreapi", roles = { "MANAGER" })
public class AnaliseCreditoResourceTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private AnaliseCreditoService service;

	@MockBean
	private JmsTemplate jmsTemplate;

	private ObjectMapper objectMapper = new ObjectMapper();
	
	private List<PessoaDTO> pessoas = new ArrayList<>();
	
	private Random rand = new Random();

	@BeforeEach 
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		pessoas.add(new PessoaDTO("Pedro", 20, "92388006007", 0, new BigDecimal(5000.00)));
		pessoas.add(new PessoaDTO("João", 18, "70060842024", 0, new BigDecimal(1045.00)));
		pessoas.add(new PessoaDTO("José", 25, "10406392080", 1, new BigDecimal(3000.00)));
		pessoas.add(new PessoaDTO("Mateus", 53, "28340240064", 2, new BigDecimal(10000.00)));
		pessoas.add(new PessoaDTO("Maria", 65, "74384183089", 0, new BigDecimal(2000.00)));
		pessoas.add(new PessoaDTO("Fernanda", 32, "76898015010", 1, new BigDecimal(6000.00)));
	}

	@Test
	public void analisar() throws Exception {
		this.mockMvc
				.perform(post("/score-api/v1/analises").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(pessoas.get(rand.nextInt(5)))))
				.andDo(print()).andExpect(status().is2xxSuccessful());
	}

//	@Test
	public void recuperarAnalise() throws Exception {
		this.mockMvc.perform(get("/score-api/v1/analises/1")).andDo(print()).andExpect(status().isOk());
	}
	
}
