package com.thescientist.mimimizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class MimiController {

    private final MimimiRepo repo;

    @Autowired
    public MimiController(MimimiRepo repo) {
        this.repo = repo;
    }

    @PostMapping(value = "/mimimi", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createMimimi(@RequestBody Mimimi mimimi) throws URISyntaxException {
        Mimimi add = repo.add(mimimi.getMessage(), mimimi.getUser());
        return ResponseEntity.created(new URI("/mimimi/" + add.getId())).body(add);
    }

    @GetMapping("/mimimi/{id}")
    public Optional<Mimimi> getMimimi(@PathVariable int id) {
        return repo.get(id);
    }

    @GetMapping("/mimimi")
    public List<Mimimi> getAllMimimi() {
        return repo.getAll();
    }
}
