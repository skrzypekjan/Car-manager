package pl.skrzypekjan.ksb2.service;

import pl.skrzypekjan.ksb2.model.Car;
import pl.skrzypekjan.ksb2.model.Color;

import java.util.List;
import java.util.Optional;


public interface CarService {
    List<Car> findAllCars();

    Optional<Car> findCarById(long id);

    List<Car> findCarByColor(Color color);

    boolean addCar(Car car);

    boolean updateCar(long id, Car car);

    boolean modifyColorCarById(Car car);

    Optional<Car> removeCarById(long id);
}

