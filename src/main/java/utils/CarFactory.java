package utils;

import dto.CarData;
import net.datafaker.Faker;
import utils.enums.Fuel;

import java.time.LocalDate;

public class CarFactory {
    static Faker faker = new Faker();

    public static CarData positiveCar() {
        CarData car = CarData.builder()
                .serialNumber(faker.vehicle().licensePlate())
                .city("Ashkelon")
                .manufacture(faker.vehicle().manufacturer())
                .model(faker.vehicle().model())
                .year(Integer.toString(faker.number()
                        .numberBetween(0, LocalDate.now().getYear())))
                .fuel(faker.options().option(Fuel.values()))
                .seats(faker.number().numberBetween(2, 20))
                .carClass(faker.vehicle().carType())
                .pricePerDay(faker.number()
                        .randomDouble(2,0,1000))
                .about(faker.text().text(0,500))
                .build();
        return car;
    }

    public static CarData negativeAllEmptyFieldsCar() {
        CarData car = CarData.builder()
                .serialNumber("")
                .city("")
                .manufacture("")
                .model("")
                .year("")
                .fuel(null)
                .seats(null)
                .carClass("")
                .pricePerDay(null)
                .about("")
                .build();
        return car;
    }

    public static CarData negativeEmptyManufactureFieldCar() {
        CarData car = CarData.builder()
                .serialNumber(faker.vehicle().licensePlate())
                .city("Ashkelon")
                .manufacture("")
                .model(faker.vehicle().model())
                .year(Integer.toString(faker.number()
                        .numberBetween(0, LocalDate.now().getYear())))
                .fuel(faker.options().option(Fuel.values()))
                .seats(faker.number().numberBetween(2, 20))
                .carClass(faker.vehicle().carType())
                .pricePerDay(faker.number()
                        .randomDouble(2,0,1000))
                .about(faker.text().text(0,500))
                .build();
        return car;
    }

    public static CarData negativeIncorrectYearFieldCar() {
        CarData car = CarData.builder()
                .serialNumber(faker.vehicle().licensePlate())
                .city("Ashkelon")
                .manufacture(faker.vehicle().manufacturer())
                .model(faker.vehicle().model())
                .year(Integer.toString(faker.options()
                        .option(faker.number().numberBetween(Integer.MIN_VALUE, -1),
                        faker.number().numberBetween(LocalDate.now().getYear()+1, Integer.MAX_VALUE))))
                .fuel(faker.options().option(Fuel.values()))
                .seats(faker.number().numberBetween(2, 20))
                .carClass(faker.vehicle().carType())
                .pricePerDay(faker.number()
                        .randomDouble(2,0,1000))
                .about(faker.text().text(0,500))
                .build();
        return car;
    }
}
