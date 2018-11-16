package DAO;

import java.util.List;

public interface GenerateDAO<T> {
    // Добавление кортежа в таблицу
    public T create(T object);
    // Обновление данных кортежа таблицы
    public void update(T object);
    // Удаление кортежа из таблицы
    public void delete(T object);
    // Поиск кортежа по id
    public T getById(String name);
    // Поиск всех кортежей из таблицы
    public List<T> getAll();
}
