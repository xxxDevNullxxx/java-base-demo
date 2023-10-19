package it.umana.demo.mapper;

import it.umana.demo.bo.CarBO;
import it.umana.demo.core.CarType;
import it.umana.demo.dto.CarDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CarMapperTest {

    @Test
    public void shouldMapCarToDto() {
        //given
        CarBO car = new CarBO( "Morris", 5, CarType.SEDAN );

        //when
        CarDTO carDto = CarMapper.INSTANCE.carToCarDto( car );

        //then
        Assertions.assertEquals("Brooom....",car.Start(), """ 
         Il BO deve avere un metodo funzionante. Se accendo il motore deve fare Brooom....""");

        Assertions.assertNotNull( carDto , "Il risultato del mapping non deve essere nullo");

        Assertions.assertEquals( "Morris" , carDto.getMake(),"""
        Due campi con lo stesso nome devono essere mappati automaticamente""");

        Assertions.assertEquals( 5,carDto.getSeatCount(),"""
        Devo mappare correttamente il campo numberOfSeats sul campo seatCount""");

        Assertions.assertEquals( "SEDAN", carDto.getType(),"""
        Il campo type di tipo enumeration deve essere correttamente mappato sul campo omonimo, ma di tipo stringa.""");
    }


}