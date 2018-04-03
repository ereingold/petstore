package com.ndgits.petstore.rs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndgits.petstore.service.model.Pet;

import static org.junit.Assert.*;
//import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PetStoreControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void getListOfPets() throws Exception {

        this.mockMvc.perform(get("/pet")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
	@Test
	public void shouldCreateEntity() throws Exception {

		mockMvc.perform(post("/pet").header("Content-Type", "application/json").content(
				"{\"name\":\"Sr.Lance\",\"description\":\"Very friendly\",\"imageURL\":null,\"categoryId\":1}")).andExpect(
						status().isCreated()).andExpect(jsonPath("$.id").exists());
	}
	@Test
	public void shouldRetrieveEntity() throws Exception {

		MvcResult mvcResult = mockMvc.perform(post("/pet").header("Content-Type", "application/json").content(
				"{\"name\":\"Sr.Lance\",\"description\":\"Very friendly\",\"imageURL\":null,\"categoryId\":1}")).andExpect(
						status().isCreated()).andReturn();

		String data = mvcResult.getResponse().getContentAsString();
		ObjectMapper mapper = new ObjectMapper();
		Pet pet = mapper.readValue(data, Pet.class);
		assertNotNull(pet);
		
		mockMvc.perform(get("/pet/" + pet.getId())).andExpect(status().isOk()).andExpect(
				jsonPath("$.id").value(pet.getId()));
	}

}
