package rikei.academy.service;

import rikei.academy.model.manU.FootballPlayer;

import java.util.List;

public interface IGenericService <T>{
    List<T> findAll();
    void save(T t);
    T findById(int id);
    void deleteById(int id);


}
