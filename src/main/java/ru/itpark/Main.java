package ru.itpark;

import ru.itpark.model.House;
import ru.itpark.repository.HouseRepositoryInMemoryImpl;
import ru.itpark.util.JdbcTemplate;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        String[] metro = {"Harthof", "Riem"};
//        List<House> list = JdbcTemplate.executeQuery(
//                "jdbc:sqlite:C:/Users/amail/IdeaProjects/Avito JdbcTemplate/db.sqlite",
//                "SELECT * FROM houses WHERE metro in" + parameterBuilder,
//                pstmt -> {
//                    int index = 1;
//                    for (String s : metro) {
//                        pstmt.setString(index++, s);
//                    }
//                },
//                rs -> new House(
//                        rs.getInt("id"),
//                        rs.getInt("price"),
//                        rs.getInt("rooms"),
//                        rs.getString("district"),
//                        rs.getString("metro")
//                )
//        );
//        for (House house : list) {
//            System.out.println(house.getMetro());
//        }
//        HouseRepositoryInMemoryImpl memory = new HouseRepositoryInMemoryImpl();
//        memory.houses.add(new House(1, 12000, 3, "novaya", "asf"));
//        memory.houses.add(new House(2, 12000, 3, "staraya", "asfas"));
//        memory.houses.add(new House(3, 12000, 3, "test", "fasf"));
//        List<House> test = memory.searchByDistrict("novaya", "test");
//        for (House house : test) {
//            System.out.println(house.getMetro());
//        }
    }
}
