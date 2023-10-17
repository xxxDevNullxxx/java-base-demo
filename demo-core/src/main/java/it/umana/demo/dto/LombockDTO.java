package it.umana.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LombockDTO {
    private String stringa;
    private Date data;
    private Integer integer;
}
