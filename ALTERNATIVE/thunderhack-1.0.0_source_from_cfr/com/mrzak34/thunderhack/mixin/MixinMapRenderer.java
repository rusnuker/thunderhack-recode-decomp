/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_22
 *  net.minecraft.class_330
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.event.events.RenderMapEvent;
import net.minecraft.class_22;
import net.minecraft.class_330;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_330.class})
public class MixinMapRenderer {
    @Inject(method={"draw"}, at={@At(value="HEAD")}, cancellable=true)
    private void renderMapPost(class_4587 matrices, class_4597 vertexConsumers, int id, class_22 state, boolean hidePlayerIcons, int light, CallbackInfo callback) {
        RenderMapEvent event = new RenderMapEvent(matrices, vertexConsumers, id, state, hidePlayerIcons, light);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
}

