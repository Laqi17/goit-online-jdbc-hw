package ua.goit.model.convecter;

public interface Converter<T, V> {

    T toDto(V dao);

    V toDao(T dto);

}
