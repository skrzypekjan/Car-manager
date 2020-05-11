package pl.skrzypekjan.ksb2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.skrzypekjan.ksb2.model.Car;
import pl.skrzypekjan.ksb2.model.Color;
import pl.skrzypekjan.ksb2.service.CarService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cars", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping()
    public ResponseEntity<List<Car>> getAllCars(){
        return new ResponseEntity<>(carService.findAllCars(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@Validated @PathVariable long id){
        Optional<Car> car = carService.findCarById(id);
        if (car.isPresent()){
            return new ResponseEntity<>(car.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<List<Car>> getCarsByColor(@Validated @PathVariable String color){
        try{
            List<Car> carList = carService.findCarByColor(Color.valueOf(color.toUpperCase()));
            if (!carList.isEmpty()) {
                return new ResponseEntity<>(carList, HttpStatus.OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity addNewCar(@Validated @RequestBody Car car){
        boolean isAdded = carService.addCar(car);
        if (isAdded) {
            return new ResponseEntity<>("New Car was added", HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping()
    public ResponseEntity modifyCar(@Validated @RequestBody Car car) {
        Optional<Car> modifyCar = carService.modifyCar(car);
        if (modifyCar.isPresent()) {
            return new ResponseEntity<>("Modification Car: " + modifyCar.get().getId(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping()
    public ResponseEntity modifyColorCarById(@Validated @RequestBody Car car) {
        boolean result  = carService.modifyColorCarById(car);
        if(result) {
            return new ResponseEntity("Modify to color " + car.getColor() ,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeCar(@Validated @PathVariable long id) {
        Optional<Car> delCar = carService.removeCarById(id);
        if (delCar.isPresent()) {
            return new ResponseEntity("Car nr " + delCar.get().getId() + " was remove", HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
