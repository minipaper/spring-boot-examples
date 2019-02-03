package sample.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class HelloControllerTest {

  @InjectMocks
  private HelloController helloController;
  private MockMvc mockMvc;

  @Before
  public void setup() {
    mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
  }

  @Test
  public void hello() throws Exception {
    mockMvc.perform(get("/"))
        .andExpect(status().isOk());
  }
}