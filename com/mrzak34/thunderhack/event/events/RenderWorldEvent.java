//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.*;
import net.minecraft.*;

public class RenderWorldEvent extends Cancellable
{
    protected float partialTicks;
    protected class_4587 matrices;
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public class_4587 getMatrices() {
        return this.matrices;
    }
    
    public static class Pre extends RenderWorldEvent
    {
        public Pre(final float partialTicks, final class_4587 matrices) {
            this.partialTicks = partialTicks;
            this.matrices = matrices;
        }
    }
    
    public static class Post extends RenderWorldEvent
    {
        public Post(final float partialTicks, final class_4587 matrices) {
            this.partialTicks = partialTicks;
            this.matrices = matrices;
        }
    }
}
