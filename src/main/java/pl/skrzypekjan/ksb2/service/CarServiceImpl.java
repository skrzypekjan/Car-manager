package pl.skrzypekjan.ksb2.service;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.skrzypekjan.ksb2.model.Car;
import pl.skrzypekjan.ksb2.model.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    public static long count = 1;
    private List<Car> carList;


    public CarServiceImpl() {
        carList = new ArrayList<>();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addCars(){
        carList.add(new Car("Mercedes", "W123", Color.BLACK));
        carList.add(new Car("Volkswagen", "Golf Plus", Color.RED));
        carList.add(new Car("Honda", "CR-V", Color.RED));
        carList.add(new Car("Fiat", "Tipo", Color.RED));
        carList.add(new Car("Ford", "Mondeo", Color.WHITE));
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
    public boolean addCar(Car newCar) {
        newCar.setId(count);
        count++;
        return carList.add(newCar);
    }

    @Override
    public boolean updateCar(long id, Car car) {
        Optional<Car> newCar = findCarById(id);
        if (newCar.isPresent()){
            Car modCar = newCar.get();
            if (car.getColor() != null) {
                modCar.setColor(car.getColor());
            }
            if (car.getMark() != null) {
                modCar.setMark(car.getMark());
            }
            if (car.getModel() != null) {
                modCar.setModel(car.getModel());
            }
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
