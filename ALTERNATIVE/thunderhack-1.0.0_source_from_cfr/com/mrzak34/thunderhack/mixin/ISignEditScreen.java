/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_498
 *  net.minecraft.class_837$class_4702
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package com.mrzak34.thunderhack.mixin;

import net.minecraft.class_498;
import net.minecraft.class_837;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_498.class})
public interface ISignEditScreen {
    @Accessor(value="model")
    public class_837.class_4702 getModel();
}

