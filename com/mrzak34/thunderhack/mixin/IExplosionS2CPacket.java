//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import net.minecraft.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ class_2664.class })
public interface IExplosionS2CPacket
{
    @Mutable
    @Accessor("playerVelocityX")
    void setVelocityX(final float p0);
    
    @Mutable
    @Accessor("playerVelocityY")
    void setVelocityY(final float p0);
    
    @Mutable
    @Accessor("playerVelocityZ")
    void setVelocityZ(final float p0);
}
