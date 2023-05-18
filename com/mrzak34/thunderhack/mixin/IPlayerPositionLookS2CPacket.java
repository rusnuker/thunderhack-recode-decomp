//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import net.minecraft.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ class_2708.class })
public interface IPlayerPositionLookS2CPacket
{
    @Mutable
    @Accessor("x")
    void setX(final double p0);
    
    @Mutable
    @Accessor("y")
    void setY(final double p0);
    
    @Mutable
    @Accessor("z")
    void setZ(final double p0);
    
    @Mutable
    @Accessor("yaw")
    void setYaw(final float p0);
    
    @Mutable
    @Accessor("pitch")
    void setPitch(final float p0);
}
