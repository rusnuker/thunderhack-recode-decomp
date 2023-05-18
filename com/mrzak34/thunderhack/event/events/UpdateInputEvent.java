//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.*;

public class UpdateInputEvent extends Cancellable
{
    float sidewaysSpeed;
    float forwardSpeed;
    boolean jumping;
    boolean sneaking;
    
    public static class Pre extends UpdateInputEvent
    {
        public Pre(final float sidewaysSpeed, final float forwardSpeed, final boolean jumping, final boolean sneaking) {
            this.sidewaysSpeed = sidewaysSpeed;
            this.forwardSpeed = forwardSpeed;
            this.jumping = jumping;
            this.sneaking = sneaking;
        }
    }
    
    public static class Post extends UpdateInputEvent
    {
        public Post(final float sidewaysSpeed, final float forwardSpeed, final boolean jumping, final boolean sneaking) {
            this.sidewaysSpeed = sidewaysSpeed;
            this.forwardSpeed = forwardSpeed;
            this.jumping = jumping;
            this.sneaking = sneaking;
        }
    }
}
