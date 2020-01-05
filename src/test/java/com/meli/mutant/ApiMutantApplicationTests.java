package com.meli.mutant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApiMutantApplicationTests {

    @Autowired
    protected WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testIsMutantOk() throws Exception {
        String jsonBody = "{\n\"dna\": [\"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\"]\n}";
        mockMvc.perform(post("/mutant").contentType(MediaType.APPLICATION_JSON).content(jsonBody))
                .andExpect(status().isOk());
    }

    @Test
    public void testIsMutantError() throws Exception {
        String jsonBody = "{\n\"dna\": [\"ATGCGA\",\"CAGTGC\",\"TTATTT\",\"AGACGG\",\"GCGTCA\",\"TCACTG\"]\n}";
        mockMvc.perform(post("/mutant").contentType(MediaType.APPLICATION_JSON).content(jsonBody))
                .andExpect(status().isForbidden());
    }

    @Test
    public void getStats() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/stats").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
