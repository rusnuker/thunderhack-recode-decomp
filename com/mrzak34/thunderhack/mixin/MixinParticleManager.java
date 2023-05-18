//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.*;

@Mixin({ class_702.class })
public class MixinParticleManager
{
    @Inject(method = { "addParticle(Lnet/minecraft/client/particle/Particle;)V" }, cancellable = true, at = { @At("HEAD") })
    public void addParticle(final class_703 particle, final CallbackInfo callback) {
        final ParticleEvent.Normal event = new ParticleEvent.Normal(particle);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
    
    @Inject(method = { "addEmitter(Lnet/minecraft/entity/Entity;Lnet/minecraft/particle/ParticleEffect;)V" }, at = { @At("HEAD") }, cancellable = true)
    private void addEmitter(final class_1297 entity, final class_2394 particleEffect, final CallbackInfo callback) {
        final ParticleEvent.Emitter event = new ParticleEvent.Emitter(particleEffect);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
}
