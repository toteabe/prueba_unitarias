package org.iesvdm.tddjava.asserts;

import java.util.Objects;

public class PruebaEquals {


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PruebaEquals that = (PruebaEquals) o;
        return Objects.equals(atributo, that.atributo);
    }

    String atributo;



    @Override
    public int hashCode() {
        return Objects.hash(atributo);
    }
}
