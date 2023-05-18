//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import net.minecraft.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ class_2824.class })
public interface IPlayerInteractEntityC2SPacket
{
    @Mutable
    @Accessor("entityId")
    void setEntityId(final int p0);
    
    @Mutable
    @Accessor("playerSneaking")
    void setPlayerSneaking(final boolean p0);
    
    @Mutable
    @Accessor("type")
    void setType(final class_2824.class_5906 p0);
    
    @Mutable
    @Accessor("type")
    class_2824.class_5906 getType();
}
