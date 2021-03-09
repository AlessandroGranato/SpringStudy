package spittr.data;

import spittr.domain.Spitter;

public interface SpitterRepository {
    public Spitter save(Spitter spitter);

    Spitter findByUsername(String username);
}
