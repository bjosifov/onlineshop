package com.example.boyanyosifov.myapplication.com.online.shop.repository;

import java.util.List;

public interface DataManager<T> {
    T getById(long id );
    List<T> getAll();

    T addEntity(T entity);
    boolean addEntities(List<T> entities);

    boolean removeEntity(T entity);
    boolean updateEntity(T updatedEntity);
}
