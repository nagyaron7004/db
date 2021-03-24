package com.codecool.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RadioCharts {
    private final String ULR;
    private final String USER;
    private final String PASSWORD;


    public RadioCharts(String URL, String USER, String PASSWORD) {
        this.ULR = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
    }
    public String getMostPlayedSong() {
        return getResult("SELECT song FROM music_broadcast GROUP BY artist ORDER BY SUM(times_aired) DESC LIMIT 1");
    }
    public String getMostActiveArtist() {
        return getResult("SELECT artist FROM music_broadcast GROUP BY artist ORDER BY COUNT(DISTINCT song) DESC LIMIT 1");
    }
    public String getResult(String SQL) {
        try (Connection connection = DriverManager.getConnection(ULR, USER, PASSWORD)){
            ResultSet resultSet = connection.createStatement().executeQuery(SQL);

            if(resultSet.next()) return resultSet.getString(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}