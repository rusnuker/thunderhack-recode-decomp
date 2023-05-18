//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.*;
import net.minecraft.*;

public class MoveEvent extends Cancellable
{
    private class_1313 type;
    private double x;
    private double y;
    private double z;
    
    public MoveEvent(final class_1313 type, final double x, final double y, final double z) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public class_1313 getType() {
        return this.type;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public void setType(final class_1313 type) {
        this.type = type;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
}
