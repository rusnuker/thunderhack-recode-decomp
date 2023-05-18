//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import net.minecraft.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.mrzak34.thunderhack.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ class_442.class })
public class ThunderHackMixin
{
    @Inject(at = { @At("HEAD") }, method = { "init()V" })
    private void init(final CallbackInfo info) {
        Main.LOGGER.info("ThunderHack loaded");
    }
}
