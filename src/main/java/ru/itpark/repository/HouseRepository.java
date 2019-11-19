package ru.itpark.repository;

import ru.itpark.model.House;

import java.util.List;

public interface HouseRepository {
    House save(House house);
    List<House> searchByPrice(int min, int max);
    List<House> searchByDistrict(String ... disdtrict);
    List<House> sortByAscPrice();
    List<House> sortByDescPrice();
}
