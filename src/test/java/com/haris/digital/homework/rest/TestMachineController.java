package com.haris.digital.homework.rest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.ResourceUtils;

import com.haris.digital.homework.TestWithSpringCtx;
import com.haris.digital.homework.dto.MachineDTO;

import lombok.RequiredArgsConstructor;

@AutoConfigureMockMvc
@RequiredArgsConstructor
public class TestMachineController extends TestWithSpringCtx {

	@Autowired
	private MockMvc mockMvc;
	private HttpMessageConverter mappingJackson2HttpMessageConverter;


	@Autowired
	private void setConverters(HttpMessageConverter<?>[] converters) {
		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
			.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
		assertNotNull(this.mappingJackson2HttpMessageConverter);
	}

	private String getJsonFromFile(String path) {
		try {
			var file = ResourceUtils.getFile("classpath:" + path);
			var string = FileUtils.readFileToString(file, "UTF-8");
			return string;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	protected String toJson(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}


	@Test
	public void testFetchAllWorksNormally() throws Exception {
		mockMvc.perform(get("/api/machines")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json(getJsonFromFile("expected_json_responses/get_all.json")))
			.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testFetchSingleWorksNormally() throws Exception {
		mockMvc.perform(get("/api/machines/" + ID5)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json(getJsonFromFile("expected_json_responses/get_single.json")))
			.andDo(MockMvcResultHandlers.print());
	}


	@Test
	public void testCreateWorksNormally() throws Exception {
		var machineDTO = new MachineDTO();
		machineDTO.setName("New Test Machine");

		var response = mockMvc.perform(post("/api/machines")
			.contentType(MediaType.APPLICATION_JSON)
			.content(toJson(machineDTO)))
			.andExpect(status().isCreated())
			.andDo(MockMvcResultHandlers.print())
			.andReturn().getResponse().getContentAsString();
		assertTrue(response.contains("id"));
		assertTrue(response.contains("New Test Machine"));
	}

	@Test
	public void testUpdateWorksNormally() throws Exception {
		var machineDTO = new MachineDTO();
		machineDTO.setId(ID5);
		machineDTO.setName("New Test Machine");

		mockMvc.perform(put("/api/machines")
			.contentType(MediaType.APPLICATION_JSON)
			.content(toJson(machineDTO)))
			.andExpect(status().isOk())
			.andExpect(content().json(getJsonFromFile("expected_json_responses/update.json")))
			.andDo(MockMvcResultHandlers.print());
	}


	@Test
	public void testDeleteWorksNormally() throws Exception {
		mockMvc.perform(delete("/api/machines/" + ID5)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testNotFoundHandler() throws Exception {
		mockMvc.perform(delete("/api/machines/notanid")
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNotFound())
			.andExpect(content().string("No machine found by the ID: |notanid|"))
			.andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void testInvalidArgumentHandler() throws Exception {
		var machineDTO = new MachineDTO();
		machineDTO.setId(ID5);
		machineDTO.setName(" ");

		mockMvc.perform(put("/api/machines")
			.contentType(MediaType.APPLICATION_JSON)
			.content(toJson(machineDTO)))
			.andExpect(status().isBadRequest())
			.andExpect(content().string("Missing name"))
			.andDo(MockMvcResultHandlers.print());
	}

}
