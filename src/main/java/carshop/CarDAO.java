package carshop;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static carshop.CarQueries.*;

public class CarDAO {
    /*Metody:
        - szukanie rekordów po nazwisku właściciela
        - szukanie po marce/modelu
    */

    private MysqlConnection mysqlConnection;

    public CarDAO() throws SQLException, IOException {
        mysqlConnection = new MysqlConnection();
        createTableIfNotExist();
    }

    private void createTableIfNotExist() throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_TABLE_QUERY)) {
                statement.execute();
            }
        }
    }

    public void insertCar(Car car) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, car.getRegistrationNumber());
                statement.setInt(2, car.getMilage());
                statement.setString(3, car.getCarBrandAndModel());
                statement.setInt(4, car.getYearOfProduction());
                statement.setString(5, String.valueOf(car.getCarType()));
                statement.setString(6, car.getOwnerName());
                int affectedRecords = statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    Long generatesId = resultSet.getLong(1);
                    System.out.println("Został utworzony rekord o identyfikatorze: " + generatesId);
                }
            }
        }
    }

    public boolean deleteCarById(int idToDelete) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID_QUERY)) {
                statement.setLong(1, idToDelete);
                int affectedRecords = statement.executeUpdate();
                if (affectedRecords > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean deleteCarByRegistrationNumber(String RegistrationNumberToDelete) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_BY_REGISTRATION_NUMBER_QUERY)) {
                statement.setString(1, RegistrationNumberToDelete);
                int affectedRecords = statement.executeUpdate();
                if (affectedRecords > 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Car> listAllCars() throws SQLException {
        List<Car> carList = new ArrayList<>();
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_ALL_QUERY)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Car car = loadCarFromResultSet(resultSet);
                    carList.add(car);
                }
            }
        }
        return carList;
    }

    private Car loadCarFromResultSet(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getLong(1));
        car.setRegistrationNumber(resultSet.getString(2));
        car.setMilage(resultSet.getInt(3));
        car.setCarBrandAndModel(resultSet.getString(4));
        car.setYearOfProduction(resultSet.getInt(5));
        car.setCarType(resultSet.getString(6));
        car.setOwnerName(resultSet.getString(7));
        return car;
    }

    public Optional<Car> getByRegistrationNumber(String searchedRegistrationNumber) throws SQLException {
        return getCar(searchedRegistrationNumber, SELECT_BY_REGISTRATION_NUMBER_QUERY);
    }

    public Optional<Car> getByOwnerName(String searchedOwnerName) throws SQLException {
        return getCar(searchedOwnerName, SELECT_BY_OWNER_NAME_QUERY);
    }

    public Optional<Car> getByBrandAndModel(String searchedBrandAndModel) throws SQLException {
        return getCar(searchedBrandAndModel, SELECT_BY_CAR_BRAND_AND_MODEL_QUERY);
    }

    private Optional<Car> getCar(String searchedName, String toSearch) throws SQLException {
        Car car;
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(toSearch)) {
                statement.setString(1, "%" + searchedName + "%");
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    car = loadCarFromResultSet(resultSet);
                    return Optional.of(car);
                }
            }
        }
        return Optional.empty();
    }


}
