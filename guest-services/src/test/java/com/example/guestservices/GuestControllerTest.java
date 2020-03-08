package com.example.guestservices;
import org.apache.commons.math.stat.descriptive.summary.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.platform.engine.TestExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.CoreMatchers.*;

@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(GuestController.class)
@Import({GuestsModelAssembler.class})
class GuestControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  GuestRepository repository;

  @BeforeEach
  void setUp() {
    when(repository.findAll()).thenReturn(List.of(new Guest( 1L, "Leonardo", "DiCaprio"),
                                                  new Guest( 2L,"Bryan","Cranston")));
    when(repository.findById(1L)).thenReturn(Optional.of(new Guest(1L,"Leonardo","DiCaprio")));
    when(repository.existsById(3L)).thenReturn(true);
    when(repository.save(any(Guest.class))).thenAnswer(invocationOnMock -> {
      Object[] args = invocationOnMock.getArguments();
      var g = (Guest) args[0];
      return new Guest(1L, g.getFirstName(),g.getLastName());
    });


  }

  @Test
  @DisplayName("GET AllGuests method with url localhost:8800/api/guests")
  void getAllGuests() throws Exception {
    mockMvc.perform(
            get("http://localhost:8800/api/guests").contentType("application/hal+json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("_embedded.guestList[0]._links.self.href", is("http://localhost:8800/api/guests/1")))
            .andExpect(jsonPath("_embedded.guestList[0].firstName", is("Leonardo")))
            .andExpect(jsonPath("_embedded.guestList[0].lastName", is("DiCaprio")))
            .andExpect(jsonPath("_embedded.guestList[1]._links.self.href", is("http://localhost:8800/api/guests/2")))
            .andExpect(jsonPath("_embedded.guestList[1].firstName", is("Bryan")))
            .andExpect(jsonPath("_embedded.guestList[1].lastName", is("Cranston")));
    //Build json paths with: https://jsonpath.com/
  }

  @Test
  @DisplayName("GET OneGuest method with valid id=1")
  void getOneGuest() throws Exception {
    mockMvc.perform(
            get("http://localhost:8800/api/guests/1").accept("application/hal+json"))
            .andExpect(status().isOk())
            //.andExpect(jsonPath("content[0].links[2].rel", is("self")))
            .andExpect(jsonPath("_links.self.href", is("http://localhost:8800/api/guests/1")));
  }

  @Test
  @DisplayName("GET OneGuest method with invalid id=5")
  void getOneGuestWithInValidId() throws Exception {
    mockMvc.perform(
            get("http://localhost:8800/api/guests/5").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
  }

  @Test
  @DisplayName("POST a new guest")
  void addNewGuestWithPostRetursCreated() throws Exception {
    mockMvc.perform(
            post("http://localhost:8800/api/guests")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"id\":3,\"firstName\":\"Donald\", \"lastName\":\"Trump\" }"))
            .andExpect(status().isCreated());
  }


  @Test
  @DisplayName("Delete guest")
  void deleteGuestReturnsOk() throws Exception {
    mockMvc.perform(
            delete("http://localhost:8800/api/guests/3"))
            .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Patch Guest modify only firstName")
  public void patchGuestFirstName() throws Exception {
    mockMvc.perform(patch("http://localhost:8800/api/guests/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"firstName\":\"Joe\"}"))
            .andExpect(jsonPath("firstName", is("Joe")))
            .andExpect(jsonPath("lastName", is("DiCaprio")))
            .andExpect(status().isOk());
  }

  @Test
  @DisplayName("Patch Guest modify all values except id")
  public void patchGuestAllValues() throws Exception {
    mockMvc.perform(patch("http://localhost:8800/api/guests/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"firstName\":\"Joe\",\"lastName\":\"Biden\"}"))
            .andExpect(jsonPath("firstName", is("Joe")))
            .andExpect(jsonPath("lastName", is("Biden")))
            .andExpect(status().isOk());
  }

  @Test
  void replaceGuest() throws Exception {
    mockMvc.perform(
            put("http://localhost:8800/api/guests/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"firstName\":\"Vladimir\",\"lastName\":\"Putin\"}"))
            .andExpect(jsonPath("firstName", is("Vladimir")))
            .andExpect(jsonPath("lastName", is("Putin")))
            .andExpect(status().isOk());
  }
  }



