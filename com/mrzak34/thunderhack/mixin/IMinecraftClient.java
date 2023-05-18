//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.gen.*;
import net.minecraft.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ class_310.class })
public interface IMinecraftClient
{
    @Accessor("currentFps")
    default int getCurrentFps() {
        return 0;
    }
    
    @Mutable
    @Accessor("session")
    void setSession(final class_320 p0);
    
    @Accessor("itemUseCooldown")
    void setItemUseCooldown(final int p0);
}
