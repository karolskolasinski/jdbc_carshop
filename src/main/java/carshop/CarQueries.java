package carshop;

public interface CarQueries {
    String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS `cars` (\n" +
            "    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
            "    `registrationNumber` VARCHAR(255) NOT NULL UNIQUE,\n" +
            "    `milage` INT NOT NULL,\n" +
            "    `carBrandAndModel` VARCHAR(255) NOT NULL,\n" +
            "    `yearOfProduction` INT NOT NULL,\n" +
            "    `carType` VARCHAR(255) NOT NULL,\n" +
            "    `ownerName` VARCHAR(255) NOT NULL\n" +
            ")";
    String INSERT_QUERY = "INSERT INTO `cars` (`registrationNumber`, `milage`, `carBrandAndModel`, `yearOfProduction`, `carType`, `ownerName`)\n" +
            "VALUES( ? , ? , ? , ? , ? , ? );";
    String DELETE_BY_ID_QUERY = "DELETE FROM `jdbc_cars`.`cars` WHERE `id` = ?";
    String DELETE_BY_REGISTRATION_NUMBER_QUERY = "DELETE FROM `jdbc_cars`.`cars` WHERE `registrationNumber` = ?";
    String SELECT_ALL_QUERY = "SELECT * FROM `cars`;";
    String SELECT_BY_REGISTRATION_NUMBER_QUERY = "SELECT * FROM `cars` WHERE `registrationNumber` LIKE ?;";
    String SELECT_BY_OWNER_NAME_QUERY = "SELECT * FROM `cars` WHERE `ownerName` LIKE ?;";
    String SELECT_BY_CAR_BRAND_AND_MODEL_QUERY = "SELECT * FROM `cars` WHERE `carBrandAndModel` LIKE ?;";
}
