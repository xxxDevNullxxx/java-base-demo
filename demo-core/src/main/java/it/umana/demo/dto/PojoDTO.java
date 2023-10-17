package it.umana.demo.dto;


import java.util.Date;
import java.util.Objects;

public class PojoDTO {
    private String stringa;
    private Date data;
    private Integer integer;

    private int baseInt;

    public String getStringa() {
        return stringa;
    }

    public void setStringa(String stringa) {
        this.stringa = stringa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    public int getBaseInt() {
        return baseInt;
    }

    public void setBaseInt(int baseInt) {
        this.baseInt = baseInt;
        var i = (Integer)baseInt;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PojoDTO pojoDTO = (PojoDTO) o;
        return baseInt == pojoDTO.baseInt && Objects.equals(stringa, pojoDTO.stringa) && Objects.equals(data, pojoDTO.data) && Objects.equals(integer, pojoDTO.integer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringa, data, integer, baseInt);
    }
}
