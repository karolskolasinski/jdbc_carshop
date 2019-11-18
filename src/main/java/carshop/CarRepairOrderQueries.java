package carshop;

public interface CarRepairOrderQueries {
    String CREATE_TABLE_QUERY = "CREATE TABLE IF NOT EXISTS `car_repair_orders` (\n" +
            "    `id_order` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n" +
            "    `addDate` DATETIME DEFAULT NOW() NOT NULL ,\n" +
            "    `realized` BOOLEAN DEFAULT FALSE NOT NULL,\n" +
            "    `realizedTime` DATETIME DEFAULT NULL,\n" +
            "    `orderText` VARCHAR(255) NOT NULL,\n" +
            "    `id_car` INT,\n" +
            "    FOREIGN KEY (`id_car`) REFERENCES `cars` (`id`)\n" +
            ")";
    String INSERT_QUERY = "INSERT INTO `car_repair_orders` (`orderText`, `id_car`)\n" +
            "VALUES( ? , ? );";
    String SELECT_CAR_REPAIRS_ORDER_BY_CAR_ID = "SELECT * FROM `car_repair_orders` JOIN `cars` ON `car_repair_orders`.`id_car` = `car`.`id` WHERE `id_car` = ?;";
    String UPDATE_CAR_REPAIR_ORDER_WHEN_ID_DONE = "UPDATE `car_repair_orders` JOIN `cars` ON `car_repair_orders`.`id_car` = `car`.`id` SET `realized` = TRUE, `realizedTime` = now() WHERE `id_order` = ?";
    String SELECT_NOT_REALIZED_ORDERS = "SELECT * FROM `car_repair_orders` WHERE `realized` = FALSE;";
    String SELECT_REALIZED_ORDERS = "SELECT * FROM `car_repair_orders` WHERE `realized` = TURE;";
    String SELECT_ORDERS_FROM_LAST_7_DAYS = "SELECT * FROM `car_repair_orders` WHERE `realizedTime` >= (NOW() - INTERVAL 7 DAY);";
}
