/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2824
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.ducks.IPlayerInteractEntityC2SPacket;
import net.minecraft.class_2824;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value={class_2824.class})
public abstract class MixinPlayerInteractEntityC2SPacket
implements IPlayerInteractEntityC2SPacket {
    @Unique
    private boolean natural;

    @Override
    public boolean isNatural() {
        return this.natural;
    }

    @Override
    public void setNatural(boolean natural) {
        this.natural = natural;
    }
}

