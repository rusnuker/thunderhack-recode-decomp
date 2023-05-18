//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import net.minecraft.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ class_329.class })
public interface IInGameHud
{
    @Accessor("VIGNETTE_TEXTURE")
    class_2960 getVignette();
    
    @Accessor("scaledWidth")
    int getWidth();
    
    @Accessor("scaledHeight")
    int getHeight();
}
