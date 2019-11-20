package ru.itpark.repository;

import ru.itpark.model.House;


import java.util.*;
import java.util.stream.Collectors;

public class HouseRepositoryInMemoryImpl implements HouseRepository {
    private Collection<House> houses = new ArrayList<>();
    private int nextId = 1;

    @Override
    public House save(House house) {
        if (house.getId() == 0) {
            house.setId(nextId++);
            houses.add(house);
        }

        boolean removed = houses.removeIf(o -> o.getId() == house.getId());
        if (!removed) {
            throw new IllegalArgumentException("Id not exists: " + house.getId());
        }

        houses.add(house);
        return house;
    }

    @Override
    public List<House> searchByPrice(int min, int max) {
        return houses.stream().filter(o -> o.getPrice() >= min && o.getPrice() <= max).collect(Collectors.toList());
    }



    @Override
    public List<House> searchByDistrict(String ... district) {
        return houses.stream().filter(o -> Arrays.asList(district).contains(o.getDistrict())).collect(Collectors.toList());
    }

    @Override
    public List<House> sortByAscPrice() {
        return houses.stream().sorted((o1, o2) -> o1.getPrice() - o2.getPrice()).collect(Collectors.toList());
    }

    @Override
    public List<House> sortByDescPrice() {
        return houses.stream().sorted((o1, o2) -> -(o1.getPrice() - o2.getPrice())).collect(Collectors.toList());
    }
}
