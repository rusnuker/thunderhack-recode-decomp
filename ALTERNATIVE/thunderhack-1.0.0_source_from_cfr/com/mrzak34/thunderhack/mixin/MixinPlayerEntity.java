/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1299
 *  net.minecraft.class_1309
 *  net.minecraft.class_1657
 *  net.minecraft.class_1937
 *  net.minecraft.class_243
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.event.events.ClipAtLedgeEvent;
import com.mrzak34.thunderhack.event.events.TravelEvent;
import net.minecraft.class_1299;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_1657.class})
public abstract class MixinPlayerEntity
extends class_1309 {
    public MixinPlayerEntity(class_1937 worldIn) {
        super(class_1299.field_6097, worldIn);
    }

    @Inject(method={"travel"}, at={@At(value="HEAD")}, cancellable=true)
    private void travel(class_243 movement, CallbackInfo callback) {
        TravelEvent event = new TravelEvent(movement.method_10216(), movement.method_10214(), movement.method_10215());
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }

    @Inject(method={"clipAtLedge"}, at={@At(value="HEAD")}, cancellable=true)
    protected void clipAtLedge(CallbackInfoReturnable<Boolean> info) {
        ClipAtLedgeEvent event = new ClipAtLedgeEvent();
        Main.EVENT_BUS.post((Object)event);
        if (event.isSet()) {
            info.setReturnValue((Object)event.isClip());
        }
    }
}

