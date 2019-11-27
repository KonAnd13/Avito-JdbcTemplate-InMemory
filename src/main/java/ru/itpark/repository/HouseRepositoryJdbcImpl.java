package ru.itpark.repository;

import ru.itpark.model.House;
import ru.itpark.util.JdbcTemplate;

import java.util.List;

public class HouseRepositoryJdbcImpl implements HouseRepository {
    private String url;

    public HouseRepositoryJdbcImpl(String url) {
        this.url = url;
    }

    @Override
    public House save(House house) {
        return house.getId() == 0 ? insert(house) : update(house);
    }

    private House insert(House house) {
        int id = JdbcTemplate.executeUpdateAndGetID(
                url,
                "INSERT INTO houses (price, rooms, district, metro) VALUES (?, ?, ?, ?);",
                pstmt -> {
                    int index = 1;
                    pstmt.setInt(index++, house.getPrice());
                    pstmt.setInt(index++, house.getRooms());
                    pstmt.setString(index++, house.getDistrict());
                    pstmt.setString(index, house.getMetro());
                }
        );
        house.setId(id);
        return house;
    }

    private House update(House house) {
        JdbcTemplate.executeUpdate(
                url,
                "UPDATE houses SET price = ?, rooms = ?, district = ?, metro = ? WHERE id = ?;",
                pstmt -> {
                    int index = 1;
                    pstmt.setInt(index++, house.getPrice());
                    pstmt.setInt(index++, house.getRooms());
                    pstmt.setString(index++, house.getDistrict());
                    pstmt.setString(index++, house.getMetro());
                    pstmt.setInt(index, house.getId());
                }
        );
        return house;
    }

    @Override
    public List<House> searchByPrice(int min, int max) {
        return JdbcTemplate.executeQuery(
                url,
                "SELECT * FROM houses WHERE price >= ? && price <= ?;",
                pstmt -> {
                    int index = 1;
                    pstmt.setInt(index++, min);
                    pstmt.setInt(index, max);
                },
                rs -> new House(
                        rs.getInt("id"),
                        rs.getInt("price"),
                        rs.getInt("rooms"),
                        rs.getString("district"),
                        rs.getString("metro")
                )
        );
    }

    @Override
    public List<House> searchByDistrict(String ... district) {
        String[] numberOfParameters = new String[district.length];
        for (int i = 0; i < district.length; i++) {
            numberOfParameters[i] = "?";
        }
        String parameterBuilder = String.join(",", numberOfParameters);

        return JdbcTemplate.executeQuery(
                url,
                "SELECT * FROM houses WHERE district in (" + parameterBuilder + ")",
                pstmt -> {
                    int index = 1;
                    for (String s : district) {
                        pstmt.setString(index++, s);
                    }
                },
                rs -> new House(
                        rs.getInt("id"),
                        rs.getInt("price"),
                        rs.getInt("rooms"),
                        rs.getString("district"),
                        rs.getString("metro")
                )
        );
    }

    @Override
    public List<House> sortByAscPrice() {
        return JdbcTemplate.executeQuery(
                url,
                "SELECT * FROM houses ORDER BY price ASC;",
                pstmt -> {},
                rs -> new House(
                        rs.getInt("id"),
                        rs.getInt("price"),
                        rs.getInt("rooms"),
                        rs.getString("district"),
                        rs.getString("metro")
                )
        );
    }

    @Override
    public List<House> sortByDescPrice() {
        return JdbcTemplate.executeQuery(
                url,
                "SELECT * FROM houses ORDER BY price DESC;",
                pstmt -> {},
                rs -> new House(
                        rs.getInt("id"),
                        rs.getInt("price"),
                        rs.getInt("rooms"),
                        rs.getString("district"),
                        rs.getString("metro")
                )
        );
    }
}
