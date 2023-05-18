//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.render;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.util.*;
import java.awt.*;
import java.util.*;
import net.minecraft.*;
import com.google.common.eventbus.*;

public class Tracers extends Module
{
    public Tracers() {
        super("Tracers", 0, false, Category.RENDER);
        this.addSettings(new Setting[0]);
    }
    
    @Subscribe
    private void onRenderWorld(final RenderWorldEvent event) {
        for (final class_1657 progress : Tracers.mc.field_1687.method_18456()) {
            if (progress == Tracers.mc.field_1724) {
                continue;
            }
            final class_4184 camera = Tracers.mc.field_1773.method_19418();
            final class_243 startPos = new class_243(0.0, 0.0, 1.0).method_1037(-(float)Math.toRadians(camera.method_19329())).method_1024(-(float)Math.toRadians(camera.method_19330()));
            final class_243 endPos = RenderUtil.smoothMovement((class_1297)progress).method_1031(0.0, (double)progress.method_5751(), 0.0);
            RenderUtil.drawLine(event.getMatrices(), startPos, endPos, new Color(255, 0, 0), 4.0);
        }
    }
}
