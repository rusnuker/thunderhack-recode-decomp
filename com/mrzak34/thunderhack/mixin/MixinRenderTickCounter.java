//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import net.minecraft.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.mrzak34.thunderhack.module.modules.misc.*;
import com.mrzak34.thunderhack.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ class_317.class })
public class MixinRenderTickCounter
{
    private float multiplier;
    @Shadow
    public float field_1969;
    @Shadow
    @Final
    private float field_1968;
    
    public MixinRenderTickCounter() {
        this.multiplier = 1.0f;
    }
    
    public float getMultiplier() {
        return this.multiplier;
    }
    
    public void setMultiplier(final float multiplier) {
        this.multiplier = multiplier;
    }
    
    @Inject(method = { "beginRenderTick" }, at = { @At(value = "FIELD", target = "Lnet/minecraft/client/render/RenderTickCounter;tickDelta:F", ordinal = 0) })
    private void onBeingRenderTick(final long a, final CallbackInfoReturnable<Integer> info) {
        if (Timer.getInstance().isToggled()) {
            this.field_1969 *= Main.TICK_TIMER;
        }
    }
}
