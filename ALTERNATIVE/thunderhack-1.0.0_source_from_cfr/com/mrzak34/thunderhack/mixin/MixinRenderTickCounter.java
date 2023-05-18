/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_317
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.module.modules.misc.Timer;
import net.minecraft.class_317;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={class_317.class})
public class MixinRenderTickCounter {
    private float multiplier = 1.0f;
    @Shadow
    public float field_1969;
    @Shadow
    @Final
    private float field_1968;

    public float getMultiplier() {
        return this.multiplier;
    }

    public void setMultiplier(float multiplier) {
        this.multiplier = multiplier;
    }

    @Inject(method={"beginRenderTick"}, at={@At(value="FIELD", target="Lnet/minecraft/client/render/RenderTickCounter;tickDelta:F", ordinal=0)})
    private void onBeingRenderTick(long a, CallbackInfoReturnable<Integer> info) {
        if (Timer.getInstance().isToggled()) {
            this.field_1969 *= Main.TICK_TIMER;
        }
    }
}

