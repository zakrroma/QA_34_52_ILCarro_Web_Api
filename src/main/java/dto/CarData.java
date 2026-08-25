package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CarData {
    private String location;
    private String manufacture;
    private String model;
    private String year;
    private String seats;
    private String carClass;
    private String carRegistrationNumber;
    private String price;
}
