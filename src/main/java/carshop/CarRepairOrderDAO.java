package carshop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static carshop.CarRepairOrderQueries.*;


public class CarRepairOrderDAO {
    private MysqlConnection mysqlConnection;

    public CarRepairOrderDAO(MysqlConnection mysqlConnection) throws SQLException {
        this.mysqlConnection = mysqlConnection;
        createTableIfNotExist();
    }


    private void createTableIfNotExist() throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(CREATE_TABLE_QUERY)) {
                statement.execute();
            }
        }
    }

    public void insertCarRepairOrder(String orderText, Long idCar) throws SQLException {
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, orderText);
                statement.setLong(2, idCar);
                int affectedRecords = statement.executeUpdate();
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    Long generatesId = resultSet.getLong(1);
                    System.out.println("Został utworzony rekord o identyfikatorze: " + generatesId);
                }
            }
        }
    }


    public List<CarRepairOrder> listAllOrdersById() throws SQLException {
        List<CarRepairOrder> carRepairOrders = new ArrayList<>();
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(SELECT_CAR_REPAIRS_ORDER_BY_CAR_ID)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    CarRepairOrder carRepairOrderar = loadOrderFromResultSet(resultSet);
                    carRepairOrders.add(carRepairOrderar);
                }
            }
        }
        return carRepairOrders;
    }

    private CarRepairOrder loadOrderFromResultSet(ResultSet resultSet) throws SQLException {
        CarRepairOrder carRepairOrder = new CarRepairOrder();
        /*carRepairOrder.setId(resultSet.getLong(1));
        car.setRegistrationNumber(resultSet.getString(2));
        car.setMilage(resultSet.getInt(3));
        car.setCarBrandAndModel(resultSet.getString(4));
        car.setYearOfProduction(resultSet.getInt(5));
        car.setCarType(resultSet.getString(6));
        car.setOwnerName(resultSet.getString(7));
        */
        return carRepairOrder;
    }

}
