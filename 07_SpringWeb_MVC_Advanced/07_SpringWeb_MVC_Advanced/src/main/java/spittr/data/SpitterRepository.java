package spittr.data;

import spittr.Spitter;

public interface SpitterRepository {
    public Spitter save(Spitter spitter);

    Spitter findByUsername(String username);
}
