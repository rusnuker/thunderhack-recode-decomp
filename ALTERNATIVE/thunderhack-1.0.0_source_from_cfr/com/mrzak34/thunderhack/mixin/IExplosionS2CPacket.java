/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2664
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package com.mrzak34.thunderhack.mixin;

import net.minecraft.class_2664;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_2664.class})
public interface IExplosionS2CPacket {
    @Mutable
    @Accessor(value="playerVelocityX")
    public void setVelocityX(float var1);

    @Mutable
    @Accessor(value="playerVelocityY")
    public void setVelocityY(float var1);

    @Mutable
    @Accessor(value="playerVelocityZ")
    public void setVelocityZ(float var1);
}

