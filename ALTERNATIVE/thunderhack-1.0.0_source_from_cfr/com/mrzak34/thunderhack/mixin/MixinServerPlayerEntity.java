/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_3222
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.event.events.UpdateInputEvent;
import net.minecraft.class_3222;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_3222.class})
public class MixinServerPlayerEntity {
    @Inject(method={"updateInput"}, cancellable=true, at={@At(value="HEAD")})
    private void updateInputPre(float sidewaysSpeed, float forwardSpeed, boolean jumping, boolean sneaking, CallbackInfo callback) {
        UpdateInputEvent.Pre event = new UpdateInputEvent.Pre(sidewaysSpeed, forwardSpeed, jumping, sneaking);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }

    @Inject(method={"updateInput"}, cancellable=true, at={@At(value="RETURN")})
    private void updateInputPost(float sidewaysSpeed, float forwardSpeed, boolean jumping, boolean sneaking, CallbackInfo callback) {
        UpdateInputEvent.Post event = new UpdateInputEvent.Post(sidewaysSpeed, forwardSpeed, jumping, sneaking);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
}

