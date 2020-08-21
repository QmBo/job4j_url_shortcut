package ru.job4j.shortcut.service;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.shortcut.model.requests.LoginPassReq;
import ru.job4j.shortcut.model.requests.SiteReq;
import ru.job4j.shortcut.model.requests.UrlConvertReq;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MainLogicTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MainLogic mainLogic;

    @Test
    void returnRegistrationAnswer() throws Exception {
        this.mockMvc.perform(post("/registration")
                .contentType("application/json")
                .content("{\"site\": \"test.ru\"}"))
                .andDo(print())
                .andExpect(status().isOk());
        ArgumentCaptor<SiteReq> captor = ArgumentCaptor.forClass(SiteReq.class);
        verify(mainLogic).regSite(captor.capture());
        assertThat(captor.getValue().getSite(), is("test.ru"));
    }

    @Test
    @WithMockUser
    void returnConvertAnswer() throws Exception {
        this.mockMvc.perform(post("/convert")
                .contentType("application/json")
                .content("{\"url\":\"https://job4j.ru/privacy.html\"}"))
                .andDo(print())
                .andExpect(status().isOk());
        ArgumentCaptor<UrlConvertReq> captor = ArgumentCaptor.forClass(UrlConvertReq.class);
        verify(mainLogic).getShortCut(captor.capture());
        assertThat(captor.getValue().getUrl(), is("https://job4j.ru/privacy.html"));
        assertThat(captor.getValue().getLink(), is("privacy.html"));
    }

    @Test
    void returnAuthAnswer() throws Exception {
        this.mockMvc.perform(post("/authorization")
                .contentType("application/json")
                .content("{\"login\":\"testLogin\",\"password\":\"testPassword\"}"))
                .andDo(print())
                .andExpect(status().isOk());
        ArgumentCaptor<LoginPassReq> captor = ArgumentCaptor.forClass(LoginPassReq.class);
        verify(mainLogic).authorization(captor.capture());
        assertThat(captor.getValue().getLogin(), is("testLogin"));
        assertThat(captor.getValue().getPassword(), is("testPassword"));
    }

    @Test
    void returnRedirectAnswer() throws Exception {
        this.mockMvc.perform(get("/redirect/test"))
                .andDo(print());
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mainLogic).redirect(captor.capture());
        assertThat(captor.getValue(), is("test"));
    }
}
