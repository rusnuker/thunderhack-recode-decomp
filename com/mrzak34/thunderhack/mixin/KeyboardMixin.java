//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import net.minecraft.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.mrzak34.thunderhack.*;
import com.mrzak34.thunderhack.event.events.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ class_309.class })
public class KeyboardMixin
{
    @Inject(method = { "onKey" }, at = { @At("HEAD") }, cancellable = true)
    public void onKey(final long window, final int key, final int scancode, final int action, final int modifiers, final CallbackInfo callback) {
        Main.INSTANCE.KeyPress(key, action);
        final KeyEvent event = new KeyEvent(key, action);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
}
