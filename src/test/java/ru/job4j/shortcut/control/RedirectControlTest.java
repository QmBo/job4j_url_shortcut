package ru.job4j.shortcut.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RedirectControlTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnRedirect() throws Exception {
        this.mockMvc.perform(get("/redirect/test"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}
