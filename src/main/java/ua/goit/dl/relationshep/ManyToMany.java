package ua.goit.dl.relationshep;

public interface ManyToMany<T, V> {

    void addRelation(T first, V second);
}
