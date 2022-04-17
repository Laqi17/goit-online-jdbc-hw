package ua.goit.dl.repository;

import java.util.List;
import java.util.Optional;

public interface Repository <T> {

    Optional<T> find (Integer id);

    List<T> findAll();

    boolean save(T t);

    void remove(T t);

    int update(T t);

}
