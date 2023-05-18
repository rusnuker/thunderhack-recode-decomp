/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2708
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package com.mrzak34.thunderhack.mixin;

import net.minecraft.class_2708;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_2708.class})
public interface IPlayerPositionLookS2CPacket {
    @Mutable
    @Accessor(value="x")
    public void setX(double var1);

    @Mutable
    @Accessor(value="y")
    public void setY(double var1);

    @Mutable
    @Accessor(value="z")
    public void setZ(double var1);

    @Mutable
    @Accessor(value="yaw")
    public void setYaw(float var1);

    @Mutable
    @Accessor(value="pitch")
    public void setPitch(float var1);
}

