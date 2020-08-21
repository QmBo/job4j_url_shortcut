package ru.job4j.shortcut.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.store.SiteRepository;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWithIgnoringCase;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SiteRegistrationControlTest {
    @MockBean
    private SiteRepository repository;

    @Autowired
    private MockMvc mockMvc;

	@Test
	void returnSiteRegistrationAnswerWhenPresentOrNot() throws Exception {
	    when(repository.findByName("test.ru")).thenReturn(Optional.empty());
        String response = this.mockMvc.perform(post("/registration")
                .contentType("application/json")
                .content("{\"site\": \"test.ru\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertThat(response, startsWithIgnoringCase("{\"registration\":true,\"login\":"));
        when(repository.findByName("test.ru")).thenReturn(Optional.of(new Site()));
        response = this.mockMvc.perform(post("/registration")
                .contentType("application/json")
                .content("{\"site\": \"test.ru\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertThat(response, is("{\"registration\":false,\"login\":\"\",\"password\":\"\"}"));
    }


}
