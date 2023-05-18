//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import net.minecraft.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ class_2828.class })
public interface IPlayerMoveC2SPacket
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
    
    @Accessor("onGround")
    void setOnGround(final boolean p0);
    
    @Accessor("x")
    double getX();
    
    @Accessor("y")
    double getY();
    
    @Accessor("z")
    double getZ();
    
    @Accessor("yaw")
    float getYaw();
    
    @Accessor("pitch")
    float getPitch();
    
    @Accessor("onGround")
    boolean getOnGround();
}
