/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2960
 *  net.minecraft.class_329
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package com.mrzak34.thunderhack.mixin;

import net.minecraft.class_2960;
import net.minecraft.class_329;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_329.class})
public interface IInGameHud {
    @Accessor(value="VIGNETTE_TEXTURE")
    public class_2960 getVignette();

    @Accessor(value="scaledWidth")
    public int getWidth();

    @Accessor(value="scaledHeight")
    public int getHeight();
}

