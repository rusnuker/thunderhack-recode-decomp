//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.event.events;

import com.mrzak34.thunderhack.event.*;
import net.minecraft.*;

public class RenderSignEvent extends Cancellable
{
    class_2586 signBlockEntity;
    float f;
    class_4587 matrices;
    class_4597 vertexConsumerProvider;
    int i;
    int j;
    
    public RenderSignEvent(final class_2586 signBlockEntity, final float f, final class_4587 matrices, final class_4597 vertexConsumerProvider, final int i, final int j) {
        this.signBlockEntity = signBlockEntity;
        this.f = f;
        this.matrices = matrices;
        this.vertexConsumerProvider = vertexConsumerProvider;
        this.i = i;
        this.j = j;
    }
    
    public class_4587 getMatrices() {
        return this.matrices;
    }
    
    public int getI() {
        return this.i;
    }
    
    public int getJ() {
        return this.j;
    }
    
    public class_2586 getSignBlockEntity() {
        return this.signBlockEntity;
    }
}
