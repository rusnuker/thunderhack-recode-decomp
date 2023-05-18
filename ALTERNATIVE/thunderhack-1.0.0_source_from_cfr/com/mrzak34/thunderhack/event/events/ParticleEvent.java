/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_2394
 *  net.minecraft.class_703
 */
package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.Cancellable;
import net.minecraft.class_2394;
import net.minecraft.class_703;

public class ParticleEvent
extends Cancellable {
    protected class_703 particle;
    protected class_2394 effect;

    public class_703 getParticle() {
        return this.particle;
    }

    public class_2394 getEffect() {
        return this.effect;
    }

    public static class Normal
    extends ParticleEvent {
        public Normal(class_703 particle) {
            this.particle = particle;
        }
    }

    public static class Emitter
    extends ParticleEvent {
        public Emitter(class_2394 effect) {
            this.effect = effect;
        }
    }
}

