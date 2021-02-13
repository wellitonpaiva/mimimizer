package com.thescientist.mimimizer;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class MimiControllerTest {

    @Test
    void addMimimi() throws URISyntaxException {
        Mimimi expected = Mimimi.builder().id(1).message("test").user("user").build();
        MimiController sut = new MimiController(new MimimiRepoMemory());
        ResponseEntity<Object> mimimi = sut.createMimimi(expected);
        assertThat(mimimi.getBody(), is(expected));
    }

    @Test
    void getNotExistentMimimiById() {
        MimiController sut = new MimiController(new MimimiRepoMemory());
        Optional<Mimimi> mimimi = sut.getMimimi(1);
        assertThat(mimimi, is(Optional.empty()));
    }

    @Test
    void getExistentMimimiById() {
        Mimimi expected = Mimimi.builder().id(1).message("test").timestamp(LocalDateTime.now()).user("user").build();
        MimimiRepoMemory repo = new MimimiRepoMemory();
        repo.add(expected.getMessage(), expected.getUser());
        MimiController sut = new MimiController(repo);

        Optional<Mimimi> mimimi = sut.getMimimi(1);
        assertThat(mimimi, is(of(expected)));
    }

    @Test
    void getAllMimimi() {
        MimimiRepoMemory repo = new MimimiRepoMemory();
        repo.add("", "");
        repo.add("", "");
        repo.add("", "");
        MimiController sut = new MimiController(repo);

        List<Mimimi> mimimis = sut.getAllMimimi();
        assertThat(mimimis.size(), is(3));
    }
}