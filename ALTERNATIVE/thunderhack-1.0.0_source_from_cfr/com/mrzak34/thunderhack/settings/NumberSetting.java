/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.settings;

import com.mrzak34.thunderhack.settings.Setting;
import java.text.DecimalFormat;

public class NumberSetting
extends Setting {
    public float value;
    public float minimum;
    public float maximum;
    public boolean mouseClicked = false;
    public boolean fraction;
    public boolean editing = false;
    public String shadowValue;

    public NumberSetting(String name, float value, float minimum, float maximum, boolean fraction) {
        this.name = name;
        this.fraction = fraction;
        this.value = this.fraction ? value : (float)((int)Double.parseDouble(new DecimalFormat("#0").format(value)));
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public double getValue() {
        return this.value;
    }

    public double getMinimum() {
        return this.minimum;
    }

    public void setMinimum(float minimum) {
        this.minimum = minimum;
    }

    public double getMaximum() {
        return this.maximum;
    }

    public void setMaximum(float maximum) {
        this.maximum = maximum;
    }
}

