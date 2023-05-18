/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_243
 *  net.minecraft.class_4184
 */
package com.mrzak34.thunderhack.module.modules.render;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.RenderWorldEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.Setting;
import com.mrzak34.thunderhack.util.RenderUtil;
import java.awt.Color;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_4184;

public class Tracers
extends Module {
    public Tracers() {
        super("Tracers", 0, false, Category.RENDER);
        this.addSettings(new Setting[0]);
    }

    @Subscribe
    private void onRenderWorld(RenderWorldEvent event) {
        for (class_1657 progress : Tracers.mc.field_1687.method_18456()) {
            if (progress == Tracers.mc.field_1724) continue;
            class_4184 camera = Tracers.mc.field_1773.method_19418();
            class_243 startPos = new class_243(0.0, 0.0, 1.0).method_1037(-((float)Math.toRadians(camera.method_19329()))).method_1024(-((float)Math.toRadians(camera.method_19330())));
            class_243 endPos = RenderUtil.smoothMovement((class_1297)progress).method_1031(0.0, (double)progress.method_5751(), 0.0);
            RenderUtil.drawLine(event.getMatrices(), startPos, endPos, new Color(255, 0, 0), 4.0);
        }
    }
}

