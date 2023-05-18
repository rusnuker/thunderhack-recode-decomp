//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.ui;

import net.minecraft.*;

public class HudOverlay extends class_437
{
    private class_310 mc;
    class_327 tr;
    
    protected HudOverlay(final class_2561 title) {
        super(title);
        this.mc = class_310.method_1551();
        this.tr = this.mc.field_1772;
    }
    
    public void method_25394(final class_4587 matrices, final int x, final int y, final float partialTicks) {
        this.tr.method_1720(matrices, "ThunderHack", 1.0f, 1.0f, -1);
    }
}
