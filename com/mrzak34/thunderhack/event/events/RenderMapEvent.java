//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.*;
import net.minecraft.*;

public class RenderMapEvent extends Cancellable
{
    class_4587 matrices;
    class_4597 vertexConsumers;
    int id;
    class_22 state;
    boolean hidePlayerIcons;
    int light;
    
    public RenderMapEvent(final class_4587 matrices, final class_4597 vertexConsumers, final int id, final class_22 state, final boolean hidePlayerIcons, final int light) {
        this.matrices = matrices;
        this.vertexConsumers = vertexConsumers;
        this.id = id;
        this.state = state;
        this.hidePlayerIcons = hidePlayerIcons;
        this.light = light;
    }
    
    public class_4587 getMatrices() {
        return this.matrices;
    }
    
    public int getId() {
        return this.id;
    }
}
