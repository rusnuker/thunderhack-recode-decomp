/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_243
 *  net.minecraft.class_2626
 *  net.minecraft.class_4587
 */
package com.mrzak34.thunderhack.module.modules.render;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.event.events.RenderWorldEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.Setting;
import com.mrzak34.thunderhack.util.BlockUtil;
import com.mrzak34.thunderhack.util.RenderUtil;
import java.awt.Color;
import java.util.ArrayList;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_2626;
import net.minecraft.class_4587;

public class WebESP
extends Module {
    public static ArrayList<class_2338> webs = new ArrayList();

    public WebESP() {
        super("WebESP", 0, false, Category.RENDER);
        this.addSettings(new Setting[0]);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        webs.clear();
    }

    @Subscribe
    public void PacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2626) {
            class_2626 packet = (class_2626)event.getPacket();
            if (BlockUtil.getBlock(packet.method_11309()) == class_2246.field_10343 && !webs.contains((Object)packet.method_11309())) {
                webs.add(packet.method_11309());
            }
            if (BlockUtil.getBlock(packet.method_11309()) != class_2246.field_10343 && webs.contains((Object)packet.method_11309())) {
                webs.remove((Object)packet.method_11309());
            }
        }
    }

    @Subscribe
    public void onRenderWorld(RenderWorldEvent event) {
        for (class_2338 pos : webs) {
            class_4587 matrices = RenderUtil.matrixFrom(new class_243((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260()));
            RenderUtil.drawLine(matrices, new class_243(0.0, 0.0, 0.0), new class_243((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260()).method_1031(2.0, 2.0, 2.0), new Color(255, 0, 0), 2.0);
            RenderUtil.drawLine(matrices, new class_243(0.0, 1.0, 0.0), new class_243((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260()).method_1031(2.0, 0.0, 2.0), new Color(255, 0, 0), 2.0);
            RenderUtil.drawLine(matrices, new class_243(1.0, 0.0, 0.0), new class_243((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260()).method_1031(0.0, 2.0, 2.0), new Color(255, 0, 0), 2.0);
            RenderUtil.drawLine(matrices, new class_243(1.0, 1.0, 0.0), new class_243((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260()).method_1031(0.0, 0.0, 2.0), new Color(255, 0, 0), 2.0);
            RenderUtil.drawLine(matrices, new class_243(0.5, 1.0, 0.5), new class_243((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260()).method_1031(1.0, 0.0, 1.0), new Color(255, 0, 0), 2.0);
        }
    }
}

