/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2561
 *  net.minecraft.class_2625
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package com.mrzak34.thunderhack.mixin;

import net.minecraft.class_2561;
import net.minecraft.class_2625;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_2625.class})
public interface ISignBlockEntity {
    @Mutable
    @Accessor(value="texts")
    public void setText(class_2561[] var1);
}

