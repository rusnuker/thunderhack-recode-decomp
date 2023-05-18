//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import net.minecraft.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ class_2743.class })
public interface IEntityVelocityUpdateS2CPacket
{
    @Mutable
    @Accessor("velocityX")
    void setVelocityX(final int p0);
    
    @Mutable
    @Accessor("velocityY")
    void setVelocityY(final int p0);
    
    @Mutable
    @Accessor("velocityZ")
    void setVelocityZ(final int p0);
}
