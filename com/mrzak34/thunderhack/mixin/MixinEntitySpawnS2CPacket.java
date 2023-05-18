//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import net.minecraft.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ class_2604.class })
public abstract class MixinEntitySpawnS2CPacket implements IEntitySpawnS2CPacket
{
    @Unique
    private boolean attacked;
    
    public void setAttacked(final boolean attacked) {
        this.attacked = attacked;
    }
    
    public boolean isAttacked() {
        return this.attacked;
    }
}
