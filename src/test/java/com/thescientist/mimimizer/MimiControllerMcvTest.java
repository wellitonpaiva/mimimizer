package com.thescientist.mimimizer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
@AutoConfigureMockMvc
class MimiControllerMcvTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void addMimimi() throws Exception {
        Mimimi mimimi = Mimimi.builder().message("test").user("user").build();
        this.mvc.perform(MockMvcRequestBuilders.post("/mimimi").contentType(MediaType.APPLICATION_JSON).content(mimimi.toJson()))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void getMimimi() throws Exception {
        Mimimi mimimi = Mimimi.builder().message("test").user("user").build();

        String location = this.mvc.perform(MockMvcRequestBuilders.post("/mimimi").contentType(MediaType.APPLICATION_JSON).content(mimimi.toJson()))
                .andExpect(MockMvcResultMatchers.status().isCreated()).andReturn().getResponse().getRedirectedUrl();

        this.mvc.perform(MockMvcRequestBuilders.get(location))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}