package ru.job4j.shortcut.control;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.shortcut.model.Link;
import ru.job4j.shortcut.store.LinkRepository;

import static org.hamcrest.Matchers.startsWith;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UrlRegistrationControlTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LinkRepository linkRepository;
    @Test
    @WithMockUser
    void returnConvertUrl() throws Exception {
        when(this.linkRepository.save(any(Link.class))).thenReturn(new Link());
        String result = this.mockMvc.perform(post("/convert")
                .contentType("application/json")
                .content("{\"url\":\"https://job4j.ru/privacy.html\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertThat(result, startsWith("{\"code\":\""));
    }
}