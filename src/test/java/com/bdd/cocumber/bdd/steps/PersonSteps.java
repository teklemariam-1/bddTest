package com.bdd.cocumber.bdd.steps;

import com.bdd.cocumber.bdd.entity.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import io.cucumber.java.Before;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.core.type.TypeReference;


import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=test")
@AutoConfigureMockMvc
public class PersonSteps {

    @Autowired
    private Environment env;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MvcResult mvcResult;

    private ObjectMapper objectMapper = new ObjectMapper();



    @Before
    public void setup() {

        System.out.println("Environment is " + Arrays.toString(env.getActiveProfiles()));
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Given("the system has been initialized")
    public void theSystemHasBeenInitialized() {
        // Assuming this is a setup step, left empty for now.
    }

    @When("I request all persons")
    public void iRequestAllPersons() throws Exception {
        mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/persons"))
                .andReturn();
    }

    @When("I add a new person named {string} with age {int}")
    public void iAddANewPersonNamedWithAge(String name, int age) throws Exception {
        Person person = new Person();
        person.setId(3L);
        person.setName(name);
        person.setAge(age);
        String jsonRequest = objectMapper.writeValueAsString(person);

        mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/persons")
                        .content(jsonRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Then("the system should have a person named {string} with age {int}")
    public void theSystemShouldHaveAPersonNamedWithAge(String name, int age) throws Exception {
        String jsonResponse = mvcResult.getResponse().getContentAsString();
        List<Person> persons = objectMapper.readValue(jsonResponse, List.class);
        boolean exists = persons.stream().anyMatch(p -> p.getName().equals(name) && p.getAge() == age);
        Assertions.assertTrue(exists);
    }

    @Then("the result should be a list of persons")
    public void the_result_should_be_a_list_of_persons() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/api/persons"))
                .andExpect(status().isOk())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Person> persons = objectMapper.readValue(content, new TypeReference<List<Person>>(){});

        // Assert list here. For example:
         Assert.assertEquals(2, persons.size()); // assuming you expect 3 persons
    }


}
