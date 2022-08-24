package br.univille.apidacs2022;

import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import br.univille.apidacs2022.api.PacienteControllerAPI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.collection.IsArray;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class Apidacs2022ApplicationTests {
	@Autowired
	private PacienteControllerAPI pacienteControllerAPI;
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void contextLoads() {
		assertThat(pacienteControllerAPI).isNotNull();
	}
	@Test
	public void pacienteControllerAPIPOSTGETTest() throws Exception {
		MvcResult result = mockMvc.perform(post("/api/v1/pacientes")
				.content("{\"nome\":\"Zezinho\", \"sexo\":\"Masculino\"}")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated()).andReturn();

		
		String resultStr = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultStr);

		mockMvc.perform(get("/api/v1/pacientes/" + objJson.getString("id")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome", is("Zezinho")))
				.andExpect(jsonPath("$.sexo", is("Masculino")));
	}
	
	@Test
	public void pacienteControllerAPIPOSTPUTTest() throws Exception {
		MvcResult result = mockMvc.perform(post("/api/v1/pacientes")
				.content("{\"nome\":\"Maria\", \"sexo\":\"Feminino\"}")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andReturn();

		String resultStr = result.getResponse().getContentAsString();
		JSONObject objJson = new JSONObject(resultStr);
		
		mockMvc.perform(put("/api/v1/pacientes/" + objJson.getString("id"))
				.content("{\"nome\":\"Maria da Silva\", \"sexo\":\"Feminino\"}")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

		mockMvc.perform(get("/api/v1/pacientes/" + objJson.getString("id")))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nome", is("Maria da Silva")))
				.andExpect(jsonPath("$.sexo", is("Feminino")));
	}

	
}
