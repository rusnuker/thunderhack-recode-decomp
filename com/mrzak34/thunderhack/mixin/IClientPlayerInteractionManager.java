//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.*;
import net.minecraft.*;

@Mixin({ class_636.class })
public interface IClientPlayerInteractionManager
{
    @Accessor("currentBreakingProgress")
    float getBreakingProgress();
    
    @Accessor("currentBreakingPos")
    class_2338 getCurrentBreakingBlockPos();
}
