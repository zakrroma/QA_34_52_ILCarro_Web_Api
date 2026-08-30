package dto;

import lombok.*;
import utils.enums.Fuel;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CarData {
    private String city;
    private String manufacture;
    private String model;
    private String year;
    private Fuel fuel;
    private Integer seats;
    private String carClass;
    private Double pricePerDay;
    private String serialNumber;
    private String about;
}
