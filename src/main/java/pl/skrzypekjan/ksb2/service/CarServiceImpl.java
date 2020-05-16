package pl.skrzypekjan.ksb2.service;

import org.springframework.stereotype.Service;
import pl.skrzypekjan.ksb2.model.Car;
import pl.skrzypekjan.ksb2.model.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private List<Car> carList;

    public CarServiceImpl() {
        carList = new ArrayList<>();
        carList.add(new Car(1L, "Mercedes", "W123", Color.BLACK));
        carList.add(new Car(2L, "Volkswagen", "Golf Plus", Color.RED));
        carList.add(new Car(3L, "Honda", "CR-V", Color.RED));
        carList.add(new Car(4L, "Fiat", "Tipo", Color.RED));
        carList.add(new Car(5L, "Ford", "Mondeo", Color.WHITE));
    }

    @Override
    public List<Car> findAllCars() {
        return carList;
    }

    @Override
    public Optional<Car> findCarById(long id) {
        return carList.stream().filter(car -> car.getId() == id).findFirst();
    }

    @Override
    public List<Car> findCarByColor(Color color) {
        return carList.stream().filter(e -> e.getColor().equals(color)).collect(Collectors.toList());
    }

    @Override
    public boolean addCar(Car car) {
        return carList.add(car);
    }

    @Override
    public boolean modifyCar(Car newCar) {
        Optional<Car> modifyCar = findCarById(newCar.getId());
        if (modifyCar.isPresent()) {
            Car car = modifyCar.get();
            car.setMark(newCar.getMark());
            car.setModel(newCar.getModel());
            car.setColor(newCar.getColor());
            return true;
        }
        return false;
    }

    @Override
    public boolean modifyColorCarById(Car newCar) {
        Optional<Car> car = carList.stream().filter(Car -> Car.getId() == newCar.getId()).findFirst();
        if (car.isPresent()) {
            if (newCar.getColor() != null) {
                car.get().setColor(newCar.getColor());
            }
            return true;
        }
        return false;
    }

    @Override
    public Optional<Car> removeCarById(long id) {
        Optional<Car> removeCar = carList.stream().filter(carId -> carId.getId() == id).findFirst();
        if (removeCar.isPresent()) {
            carList.remove(removeCar.get());
        }
        return removeCar;
    }
}