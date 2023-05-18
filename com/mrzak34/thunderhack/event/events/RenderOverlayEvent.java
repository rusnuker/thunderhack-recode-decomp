//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.*;
import net.minecraft.*;

public class RenderOverlayEvent extends Cancellable
{
    protected float partialTicks;
    protected class_4587 matrices;
    
    public RenderOverlayEvent(final class_4587 matrices, final float partialTicks) {
        this.partialTicks = partialTicks;
        this.matrices = matrices;
    }
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public class_4587 getMatrices() {
        return this.matrices;
    }
}
