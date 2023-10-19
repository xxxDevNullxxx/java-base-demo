package it.umana.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    private String make;
    private int seatCount;
    private String type;

    //constructor, getters, setters etc

}
