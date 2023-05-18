//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import net.minecraft.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ class_312.class })
public class MixinMouse
{
    @Inject(method = { "onMouseButton" }, at = { @At("HEAD") }, cancellable = true)
    private void onMouseButton(final long window, final int button, final int action, final int mods, final CallbackInfo callback) {
        final MouseButtonEvent event = new MouseButtonEvent(button, action);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
    
    @Inject(method = { "onMouseScroll" }, at = { @At("HEAD") })
    private void onMouseScroll(final long window, final double horizontal, final double vertical, final CallbackInfo info) {
    }
}
