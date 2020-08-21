package ru.job4j.shortcut.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.shortcut.model.Link;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.store.SiteRepository;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StatisticControlTest {
    @MockBean
    private SiteRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void returnStatistic() throws Exception {
        List<Link> links  = new ArrayList<>();
        links.add(new Link().setOriginUrl("test").setRequestsCount(700));
        links.add(new Link().setOriginUrl("2").setRequestsCount(2));
        when(repository.findByName("user")).thenReturn(Optional.of(new Site().setAllLinks(links)));
        String result = this.mockMvc
                .perform(get("/statistic"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        assertThat(result, is("{{\"url\":\"test\", \"total\":700},{\"url\":\"2\", \"total\":2}}"));
    }

}
