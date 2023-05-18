//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.*;
import net.minecraft.*;

public class ParticleEvent extends Cancellable
{
    protected class_703 particle;
    protected class_2394 effect;
    
    public class_703 getParticle() {
        return this.particle;
    }
    
    public class_2394 getEffect() {
        return this.effect;
    }
    
    public static class Emitter extends ParticleEvent
    {
        public Emitter(final class_2394 effect) {
            this.effect = effect;
        }
    }
    
    public static class Normal extends ParticleEvent
    {
        public Normal(final class_703 particle) {
            this.particle = particle;
        }
    }
}
