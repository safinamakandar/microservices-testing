package com.example.demo.integration;

import com.example.demo.models.Station;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest // Let's the tests know the Spring application context needs to be available
@AutoConfigureMockMvc // Mock Mvc is a library we're using for making our mock requests.
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // lets us reuse state between tests
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // runs application properties each
// time, which has to consequence of dropping the database each time.
public class StationIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void helloWorld_ReturnsString_Success() throws Exception {
        // build the request
        RequestBuilder request = MockMvcRequestBuilders.get("/hello");
        // perform the request
        ResultActions resultsActions = mockMvc.perform(request);
        // look at the results
        MvcResult result = resultsActions
                .andExpect(status().isOk()) // checks status code
                .andReturn();

        // tests the content of the response
        String actual = result.getResponse().getContentAsString();
        String expected = "Hello world";
        assertEquals(expected, actual);
    }

    @Test
    public void getStations_ReturnsStations_Success() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/stations");

        ResultActions resultActions = mockMvc.perform(request);

        MvcResult result = resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Baker Street"))
                .andReturn();
    }

    @Test
    public void postStation_ReturnsStation_Success() throws Exception {
        Station station = new Station();
        station.setName("Canary Wharf");
        station.setDateOpened(LocalDate.parse("1950-10-02"));
        station.setPlatformCount(4);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/station")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(station));

        ResultActions resultActions = mockMvc.perform(request);

        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(6));
    }


}
