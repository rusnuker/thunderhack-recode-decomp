//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.settings;

import java.text.*;

public class NumberSetting extends Setting
{
    public float value;
    public float minimum;
    public float maximum;
    public boolean mouseClicked;
    public boolean fraction;
    public boolean editing;
    public String shadowValue;
    
    public NumberSetting(final String name, final float value, final float minimum, final float maximum, final boolean fraction) {
        this.mouseClicked = false;
        this.editing = false;
        this.name = name;
        this.fraction = fraction;
        if (this.fraction) {
            this.value = value;
        }
        else {
            this.value = (float)(int)Double.parseDouble(new DecimalFormat("#0").format(value));
        }
        this.minimum = minimum;
        this.maximum = maximum;
    }
    
    public double getValue() {
        return this.value;
    }
    
    public double getMinimum() {
        return this.minimum;
    }
    
    public void setMinimum(final float minimum) {
        this.minimum = minimum;
    }
    
    public double getMaximum() {
        return this.maximum;
    }
    
    public void setMaximum(final float maximum) {
        this.maximum = maximum;
    }
}
