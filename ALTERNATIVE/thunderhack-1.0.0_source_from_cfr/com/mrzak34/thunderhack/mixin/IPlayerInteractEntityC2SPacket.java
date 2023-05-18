/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2824
 *  net.minecraft.class_2824$class_5906
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package com.mrzak34.thunderhack.mixin;

import net.minecraft.class_2824;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_2824.class})
public interface IPlayerInteractEntityC2SPacket {
    @Mutable
    @Accessor(value="entityId")
    public void setEntityId(int var1);

    @Mutable
    @Accessor(value="playerSneaking")
    public void setPlayerSneaking(boolean var1);

    @Mutable
    @Accessor(value="type")
    public void setType(class_2824.class_5906 var1);

    @Mutable
    @Accessor(value="type")
    public class_2824.class_5906 getType();
}

