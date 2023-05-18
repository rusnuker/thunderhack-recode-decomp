//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.ducks.*;
import net.minecraft.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ class_2824.class })
public abstract class MixinPlayerInteractEntityC2SPacket implements IPlayerInteractEntityC2SPacket
{
    @Unique
    private boolean natural;
    
    public boolean isNatural() {
        return this.natural;
    }
    
    public void setNatural(final boolean natural) {
        this.natural = natural;
    }
}
