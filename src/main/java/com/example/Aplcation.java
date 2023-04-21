package com.example;

import com.zaxxer.hikari.*;
import java.sql.*;

public class Aplcation {
    private static HikariDataSource dataSource;

    public static void main(String[] args) throws SQLException {
        try {
            initDatabaseConnectionPool();
            // DELETE ALL TABLES
            deleteAllDataFromTable("bikes");
            deleteAllDataFromTable("bookings");
            deleteAllDataFromTable("extra");
            deleteAllDataFromTable("places");
            deleteAllDataFromTable("bikes_extra");

            // CREATE THE TABLES

            createDataPlace(1,"Palma",07014,"Jaime III");
            createDataPlace(2, "Alcúdia", 07400, "Calle Sant Jaume");
            createDataPlace(3, "Calvià", 07032, "Carrer de sa Porrassa");
            createDataPlace(4, "Andratx", 07157, "Carrer Isaac Peral");
            createDataPlace(5, "Sóller", 07100, "Carrer de sa Lluna");
            createDataPlace(6, "Manacor", 07500, "Carrer Nou");


            createDataBikes(1,"Orix",1,1);
            createDataBikes(2, "Trek", 2, 2);
            createDataBikes(3, "Specialized", 4, 3);
            createDataBikes(4, "Giant", 2, 4);
            createDataBikes(5, "Cannondale", 6, 5);
            createDataBikes(6, "Scott", 5, 6);
            createDataBikes(7, "Pinarello", 5, 7);
            createDataBikes(8, "BMC", 3, 8);
            createDataBikes(9, "Santa Cruz", 2, 9);
            createDataBikes(10, "Colnago", 1, 10);


            createDataBookings(1,"Paco","Lopez", 23, "pagolopez@gmail.com", "Palma", 1);
            createDataBookings(2, "Ana", "García", 30, "anagar@gmail.com", "Alcúdia", 2);
            createDataBookings(3, "Juan", "Martínez", 25, "juanma@gmail.com", "Calvià", 3);
            createDataBookings(4, "María", "Fernández", 27, "mariafdez@gmail.com", "Andratx", 4);
            createDataBookings(5, "Pedro", "Hernández", 28, "pedrohernandez@gmail.com", "Sóller", 5);
            createDataBookings(6, "Luisa", "Sánchez", 24, "lsanchez@gmail.com", "Manacor", 6);
            createDataBookings(7, "Carlos", "González", 29, "cgonzalez@gmail.com", "Inca", 7);
            createDataBookings(8, "Sofía", "Ramírez", 26, "sofiaramirez@gmail.com", "Pollença", 8);
            createDataBookings(9, "Alberto", "Jiménez", 32, "albertojimenez@gmail.com", "Felanitx", 9);
            createDataBookings(10, "Laura", "Gómez", 31, "lauragomez@gmail.com", "Llucmajor", 10);

            createDataExtra(1, "Retovisor");
            createDataExtra(2, "Portabultos");
            createDataExtra(3, "Timbre");
            createDataExtra(4, "Candado");
            createDataExtra(5, "Luz trasera");
            createDataExtra(6, "Luz delantera");
            createDataExtra(7, "Casco");
            createDataExtra(8, "Guantes");
            createDataExtra(9, "Chaqueta");
            createDataExtra(10, "Portabotellas");



            createDataBikes_Extra(1, 1);
            createDataBikes_Extra(1, 2);
            createDataBikes_Extra(2, 3);
            createDataBikes_Extra(3, 4);
            createDataBikes_Extra(3, 5);
            createDataBikes_Extra(4, 6);
            createDataBikes_Extra(5, 7);
            createDataBikes_Extra(6, 8);
            createDataBikes_Extra(7, 9);
            createDataBikes_Extra(8, 10);

            // SHOW THE TABLES
            readDataExtra();
            readDataPlaces();
            readDataBikes();
            readDataBookings();


            // UPDATES

        } finally {
            closeDatabaseConnectionPool();
        }
    }
    // Conectarse y desconectarse
    private static void initDatabaseConnectionPool() {
        dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mariadb://localhost:3306/jdbc_demo");
        dataSource.setUsername("viktoriob");
        dataSource.setPassword("password");
    }

    private static void closeDatabaseConnectionPool() {
        dataSource.close();
    }

    /*TODOS LOS METODOS INTERT DE CADA TABLA*/
    private static void createDataPlace(int place_id, String village, int cp, String location) throws SQLException{
        System.out.println("Creating data...");
        int rowsInserted;
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                           INSERT INTO places(place_id, village, cp, location)
                           VALUES (?, ?, ?, ?)
                    """)) {
                statement.setInt(1, place_id);
                statement.setString(2, village);
                statement.setInt(3, cp);
                statement.setString(4, location);


                rowsInserted = statement.executeUpdate();
            }
        }
        System.out.println("Rows inserted "+ rowsInserted);
    }

    private static void createDataBikes(int bike_id, String bike_model, int place_id, int assigned_id) throws SQLException{
        System.out.println("Creating data...");
        int rowsInserted;
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                           INSERT INTO bikes(bike_id, bike_model, place_id, assigned_id)
                           VALUES (?, ?, ?, ?)
                    """)) {
                statement.setInt(1, bike_id);
                statement.setString(2, bike_model);
                statement.setInt(3, place_id);
                statement.setInt(4, assigned_id);


                rowsInserted = statement.executeUpdate();
            }
        }
        System.out.println("Rows inserted "+ rowsInserted);
    }

    private static void createDataBookings(int bookings_id, String nombre, String surname, int age, String email, String village, int bike_id) throws SQLException{
        System.out.println("Creating data...");
        int rowsInserted;
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                           INSERT INTO bookings(bookings_id, nombre, surname, age, email, village, bike_id)
                           VALUES (?, ?, ?, ?, ?, ?, ?)
                    """)) {
                statement.setInt(1, bookings_id);
                statement.setString(2, nombre);
                statement.setString(3, surname);
                statement.setInt(4, age);
                statement.setString(5, email);
                statement.setString(6, village);
                statement.setInt(7, bike_id);


                rowsInserted = statement.executeUpdate();
            }
        }
        System.out.println("Rows inserted "+ rowsInserted);
    }

    private static void createDataExtra(int extra_id, String add) throws SQLException{
        System.out.println("Creating data...");
        int rowsInserted;
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                           INSERT INTO extra()
                           VALUES (?, ?)
                    """)) {
                statement.setInt(1, extra_id);
                statement.setString(2, add);

                rowsInserted = statement.executeUpdate();
            }
        }
        System.out.println("Rows inserted "+ rowsInserted);
    }

    private static void createDataBikes_Extra(int bike_id,int extra_id) throws SQLException{
        System.out.println("Creating data...");
        int rowsInserted;
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                           INSERT INTO bikes_extra()
                           VALUES (?, ?)
                    """)) {
                statement.setInt(1, bike_id);
                statement.setInt(2, extra_id);

                rowsInserted = statement.executeUpdate();
            }
        }
        System.out.println("Rows inserted "+ rowsInserted);
    }

    /* UPADTES DE TODAS LAS CLASES */

    private static void updateDataPlace(int place_id, String location) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                        UPDATE places
                        SET location = ?
                        WHERE place_id = ?
                    """)) {
                statement.setString(1, location);
                statement.setInt(2, place_id);
                int rowsUpdated = statement.executeUpdate();
                System.out.println("Rows updated: " + rowsUpdated);
            }
        }
    }

    /* DELETES DE LAS CLASES*/

    private static void deleteAllDataFromTable(String tableName) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM " + tableName
            )) {
                int rowsDeleted = statement.executeUpdate();
                System.out.println("Rows deleted: " + rowsDeleted);
            }
        }
    }

    private static void deleteDataPlaces(int cp) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                        DELETE FROM places
                        WHERE cp LIKE ?
                    """)) {
                statement.setInt(1, cp);
                int rowsDeleted = statement.executeUpdate();
                System.out.println("Rows deleted: " + rowsDeleted);
            }
        }
    }

    private static void deleteDataBikes(int bike_id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM bikes
                    WHERE bike_id = ?
                """)) {
                statement.setInt(1, bike_id);
                int rowsDeleted = statement.executeUpdate();
                System.out.println("Rows deleted: " + rowsDeleted);
            }
        }
    }

    private static void deleteDataBookings(int bookings_id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM bookings
                    WHERE bookings_id = ?
                """)) {
                statement.setInt(1, bookings_id);
                int rowsDeleted = statement.executeUpdate();
                System.out.println("Rows deleted: " + rowsDeleted);
            }
        }
    }

    private static void deleteDataExtra(int extraId) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM extra
                    WHERE extra_id = ?
                """)) {
                statement.setInt(1, extraId);
                int rowsDeleted = statement.executeUpdate();
                System.out.println("Rows deleted: " + rowsDeleted);
            }
        }
    }

    private static void deleteDataBikes_Extra(int bike_id, int extra_id) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                    DELETE FROM bikes_extra
                    WHERE bike_id = ? AND extra_id = ?
                """)) {
                statement.setInt(1, bike_id);
                statement.setInt(2, extra_id);
                int rowsDeleted = statement.executeUpdate();
                System.out.println("Rows deleted: " + rowsDeleted);
            }
        }
    }



    /* READ DATA FROM DE CLASES*/
    private static void readDataPlaces() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                        SELECT place_id, location
                        FROM places
                        ORDER BY cp ASC
                    """)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    boolean empty = true;
                    System.out.println("PLACES TABLE");
                    System.out.println("+---------+---------------------------+");
                    System.out.printf("| %7s | %25s |\n", "PLACE_ID", "LOCATION");
                    System.out.println("+---------+---------------------------+");
                    while (resultSet.next()) {
                        empty = false;
                        int place_id = resultSet.getInt("place_id");
                        String location = resultSet.getString("location");
                        System.out.printf("| %7d | %25s |\n", place_id, location);
                    }
                    if (empty) {
                        System.out.println("\t (no data)");
                    }
                    System.out.println("+---------+---------------------------+");
                    System.out.println("");
                }
            }
        }
    }

    private static void readDataBikes() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                        SELECT bike_id, bike_model, place_id, assigned_id
                        FROM bikes
                        ORDER BY bike_id ASC 
                    """)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    boolean empty = true;
                    System.out.println("BIKES TABLE");
                    System.out.println("+---------+--------------+----------+-------------+");
                    System.out.printf("| %7s | %12s | %8s | %11s |\n", "BIKE_ID", "BIKE_MODEL", "PLACE_ID", "ASSIGNED_ID");
                    System.out.println("+---------+--------------+----------+-------------+");
                    while (resultSet.next()) {
                        empty = false;
                        int bike_id = resultSet.getInt("bike_id");
                        String bike_model = resultSet.getString("bike_model");
                        int place_id = resultSet.getInt("place_id");
                        int assigned_id = resultSet.getInt("assigned_id");

                        System.out.printf("| %7d | %12s | %8d | %11d |\n", bike_id, bike_model, place_id, assigned_id);
                    }
                    if (empty) {
                        System.out.println("\t (no data)");
                    }
                    System.out.println("+---------+--------------+----------+-------------+");
                    System.out.println("");

                }
            }
        }
    }

    private static void readDataBookings() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                SELECT bookings_id, nombre, surname, age, email, village, bike_id
                FROM bookings
                ORDER BY bookings_id ASC 
            """)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    boolean empty = true;
                    System.out.println("BOOKINGS TABLE");
                    System.out.println("+-------------+------------+------------+-----+--------------------------+----------------------+----------+");
                    System.out.printf("| %11s | %10s | %10s | %3s | %25s | %20s | %8s |\n", "BOOKINGS_ID", "NOMBRE", "SURNAME", "AGE", "EMAIL", "VILLAGE", "BIKE_ID");
                    System.out.println("+-------------+------------+------------+-----+--------------------------+----------------------+----------+");
                    while (resultSet.next()) {
                        empty = false;
                        int bookings_id = resultSet.getInt("bookings_id");
                        String nombre = resultSet.getString("nombre");
                        String surname = resultSet.getString("surname");
                        int age = resultSet.getInt("age");
                        String email = resultSet.getString("email");
                        String village = resultSet.getString("village");
                        int bike_id = resultSet.getInt("bike_id");

                        System.out.printf("| %11d | %10s | %10s | %3d | %25s | %20s | %8d |\n", bookings_id, nombre, surname, age, email, village, bike_id);
                    }
                    if (empty) {
                        System.out.println("\t (no data)");
                    }
                    System.out.println("+-------------+------------+------------+-----+--------------------------+----------------------+----------+");
                    System.out.println("");
                }
            }
        }
    }

    private static void readDataExtra() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("""
                        SELECT extra_id, adds
                        FROM extra
                        ORDER BY extra_id DESC
                    """)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    boolean empty = true;
                    System.out.println("EXTRA TABLE");
                    System.out.println("+---------+------------------------------+");
                    System.out.printf("| %7s | %28s| \n", "EXTRA_ID", "ADDS");
                    System.out.println("+---------+------------------------------+");
                    while (resultSet.next()) {
                        empty = false;
                        int extra_id = resultSet.getInt("extra_id");
                        String adds = resultSet.getString("adds");
                        System.out.printf("| %7d | %28s |\n", extra_id, adds);
                    }
                    if (empty) {
                        System.out.println("\t (no data)");
                    }
                    System.out.println("+---------+------------------------------+");
                    System.out.println("");

                }
            }
        }
    }

}
