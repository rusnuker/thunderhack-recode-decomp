/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_310
 *  net.minecraft.class_320
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package com.mrzak34.thunderhack.mixin;

import net.minecraft.class_310;
import net.minecraft.class_320;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_310.class})
public interface IMinecraftClient {
    @Accessor(value="currentFps")
    public static int getCurrentFps() {
        return 0;
    }

    @Mutable
    @Accessor(value="session")
    public void setSession(class_320 var1);

    @Accessor(value="itemUseCooldown")
    public void setItemUseCooldown(int var1);
}

