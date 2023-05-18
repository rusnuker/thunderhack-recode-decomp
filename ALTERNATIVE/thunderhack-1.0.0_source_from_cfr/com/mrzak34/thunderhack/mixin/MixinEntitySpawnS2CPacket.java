/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2604
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Unique
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.mixin.IEntitySpawnS2CPacket;
import net.minecraft.class_2604;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value={class_2604.class})
public abstract class MixinEntitySpawnS2CPacket
implements IEntitySpawnS2CPacket {
    @Unique
    private boolean attacked;

    @Override
    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    @Override
    public boolean isAttacked() {
        return this.attacked;
    }
}

