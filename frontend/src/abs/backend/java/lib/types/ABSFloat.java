/** 
 * Copyright (c) 2018, the ABS language team. All rights reserved.
 * This file is licensed under the terms of the Modified BSD License.
 */
package abs.backend.java.lib.types;

import org.apfloat.Apfloat;

public class ABSFloat extends ABSBuiltInDataType {

    private final double value;

    private ABSFloat(double d) {
        super("");
        this.value = d;
    }

    public ABSFloat add(ABSFloat s) {
        return fromDouble(value + s.value);
    }

    public ABSFloat subtract(ABSFloat i) {
        return fromDouble(this.value - i.value);
    }

    public ABSFloat multiply(ABSFloat i) {
        return fromDouble(this.value * i.value);
    }

    public ABSFloat divide(ABSFloat i) {
        return fromDouble(this.value / i.value);
    }

    public ABSFloat divide(double i) {
        return fromDouble(this.value / i);
    }

    public ABSFloat mod(ABSFloat i) {
        return fromDouble(this.value % i.value);
    }

    public ABSFloat negate() {
        return fromDouble(- this.value);
    }

    @Override
    public ABSBool eq(ABSValue o) {
        if (!super.eq(o).toBoolean())
            return ABSBool.FALSE;
        ABSFloat s = (ABSFloat) o;
        return ABSBool.fromBoolean(this.value == s.value);
    }

    public ABSBool gt(ABSValue o) {
        if (o == null)
            return ABSBool.FALSE;
        if (!o.getClass().equals(ABSFloat.class))
            return ABSBool.FALSE;
        ABSFloat oi = (ABSFloat) o;
        return ABSBool.fromBoolean(this.value > oi.value);
    }

    public ABSBool lt(ABSValue o) {
        if (o == null)
            return ABSBool.FALSE;
        if (!o.getClass().equals(ABSFloat.class))
            return ABSBool.FALSE;
        ABSFloat oi = (ABSFloat) o;
        return ABSBool.fromBoolean(this.value < oi.value);
    }

    public ABSBool gtEq(ABSValue o) {
        if (o == null)
            return ABSBool.FALSE;
        if (!o.getClass().equals(ABSFloat.class))
            return ABSBool.FALSE;
        ABSFloat oi = (ABSFloat) o;
        return ABSBool.fromBoolean(this.value >= oi.value);
    }

    public ABSBool ltEq(ABSValue o) {
        if (o == null)
            return ABSBool.FALSE;
        if (!o.getClass().equals(ABSFloat.class))
            return ABSBool.FALSE;
        ABSFloat oi = (ABSFloat) o;
        return ABSBool.fromBoolean(this.value <= oi.value);
    }

    public static ABSFloat fromString(String value) {
        return new ABSFloat(Double.parseDouble(value));
    }

    public static ABSFloat fromDouble(double d) {
        return new ABSFloat(d);
    }

    public double getDouble() {
        return value;
    }

    @Override
    public String toString() {
        return new Apfloat(this.value).toString(true);
    }
}
