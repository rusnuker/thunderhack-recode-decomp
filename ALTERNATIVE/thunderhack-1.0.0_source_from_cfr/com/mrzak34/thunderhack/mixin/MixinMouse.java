/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_312
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.event.events.MouseButtonEvent;
import net.minecraft.class_312;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_312.class})
public class MixinMouse {
    @Inject(method={"onMouseButton"}, at={@At(value="HEAD")}, cancellable=true)
    private void onMouseButton(long window, int button, int action, int mods, CallbackInfo callback) {
        MouseButtonEvent event = new MouseButtonEvent(button, action);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }

    @Inject(method={"onMouseScroll"}, at={@At(value="HEAD")})
    private void onMouseScroll(long window, double horizontal, double vertical, CallbackInfo info) {
    }
}

