package it.umana.demo.bo;


import it.umana.demo.core.CarType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarBO {
        private String make;
        private int numberOfSeats;
        private CarType type;
        public String Start(){
            return "Brooom....";
        }
}
