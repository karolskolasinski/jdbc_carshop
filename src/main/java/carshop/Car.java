package carshop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    private Long id;
    private String registrationNumber;
    private int milage;
    private String carBrandAndModel;
    private int yearOfProduction;
    private CarType carType;
    private String ownerName;

    public void setCarType(String carType) {
        this.carType = CarType.valueOf(carType.toUpperCase());
    }
}
