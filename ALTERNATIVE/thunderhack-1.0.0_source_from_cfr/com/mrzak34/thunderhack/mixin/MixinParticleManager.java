/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_2394
 *  net.minecraft.class_702
 *  net.minecraft.class_703
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.event.events.ParticleEvent;
import net.minecraft.class_1297;
import net.minecraft.class_2394;
import net.minecraft.class_702;
import net.minecraft.class_703;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_702.class})
public class MixinParticleManager {
    @Inject(method={"addParticle(Lnet/minecraft/client/particle/Particle;)V"}, cancellable=true, at={@At(value="HEAD")})
    public void addParticle(class_703 particle, CallbackInfo callback) {
        ParticleEvent.Normal event = new ParticleEvent.Normal(particle);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }

    @Inject(method={"addEmitter(Lnet/minecraft/entity/Entity;Lnet/minecraft/particle/ParticleEffect;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private void addEmitter(class_1297 entity, class_2394 particleEffect, CallbackInfo callback) {
        ParticleEvent.Emitter event = new ParticleEvent.Emitter(particleEffect);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
}

