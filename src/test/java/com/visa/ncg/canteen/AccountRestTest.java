package com.visa.ncg.canteen;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AccountRestTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getReturnsJsonContainingBalance() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/accounts/0"))
        .andExpect(status().isOk())
        .andReturn();
    String body = result.getResponse().getContentAsString();
    assertThat(body)
        .contains("\"balance\"");
  }

  @Test
  public void getWithAccountIdReturnsAccountAndBalanceForThatId() throws Exception {
    mockMvc.perform(get("/api/accounts/0"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(0))
        .andExpect(jsonPath("$.balance").value("10"));
  }

}