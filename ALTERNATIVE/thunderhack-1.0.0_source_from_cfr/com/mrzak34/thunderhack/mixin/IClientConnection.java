/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  io.netty.channel.Channel
 *  net.minecraft.class_2535
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.gen.Accessor
 */
package com.mrzak34.thunderhack.mixin;

import io.netty.channel.Channel;
import net.minecraft.class_2535;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value={class_2535.class})
public interface IClientConnection {
    @Mutable
    @Accessor(value="channel")
    public void setChannel(Channel var1);
}

