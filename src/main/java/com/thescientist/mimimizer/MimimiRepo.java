package com.thescientist.mimimizer;

import java.util.List;
import java.util.Optional;

public interface MimimiRepo {

    Mimimi add(String message, String user);

    Optional<Mimimi> get(int id);

    List<Mimimi> getAll();
}
