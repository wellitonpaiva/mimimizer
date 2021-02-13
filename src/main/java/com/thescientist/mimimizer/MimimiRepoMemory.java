package com.thescientist.mimimizer;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MimimiRepoMemory implements MimimiRepo {

    private transient List<Mimimi> cache;

    public MimimiRepoMemory() {
        this.cache = new ArrayList<>();
    }

    public Mimimi add(String message, String user) {
        Mimimi mimimi = new Mimimi(cache.size() + 1, message, user);
        cache.add(mimimi);
        return mimimi;
    }

    public Optional<Mimimi> get(int id) {
        return cache.stream().filter(m -> m.getId().equals(id)).findFirst();
    }

    public List<Mimimi> getAll() {
        return cache;
    }
}
