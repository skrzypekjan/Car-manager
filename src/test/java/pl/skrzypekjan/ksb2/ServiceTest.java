package pl.skrzypekjan.ksb2;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.skrzypekjan.ksb2.model.Car;
import pl.skrzypekjan.ksb2.model.Color;
import pl.skrzypekjan.ksb2.service.CarService;
import pl.skrzypekjan.ksb2.service.CarServiceImpl;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.*;


public class ServiceTest {

    //BDD
    @Test
    public void should_has_two_element() {
        //given
        CarService carService = mock(CarServiceImpl.class);
        given(carService.findAllCars()).willReturn(prepareMockData());
        //when
        List<Car> cars = carService.findAllCars();
        //then
        Assert.assertThat(cars, Matchers.hasSize(2));
    }

    private List<Car> prepareMockData(){
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("Mercedes", "W123", Color.BLACK));
        carList.add(new Car("Volkswagen", "Golf Plus", Color.RED));
        return carList;
    }


}




