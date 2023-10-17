package it.umana.demo.spi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageConfigurationDTO {

    public String plugin;

    public String queue;

    public Date timestamp;
}
