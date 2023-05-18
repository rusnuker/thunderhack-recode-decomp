/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_309
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.event.events.KeyEvent;
import net.minecraft.class_309;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_309.class})
public class KeyboardMixin {
    @Inject(method={"onKey"}, at={@At(value="HEAD")}, cancellable=true)
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo callback) {
        Main.INSTANCE.KeyPress(key, action);
        KeyEvent event = new KeyEvent(key, action);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
}

