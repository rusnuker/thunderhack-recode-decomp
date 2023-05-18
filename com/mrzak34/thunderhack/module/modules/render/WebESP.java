//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.render;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.google.common.eventbus.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.util.*;
import java.awt.*;
import java.util.*;
import net.minecraft.*;

public class WebESP extends Module
{
    public static ArrayList<class_2338> webs;
    
    public WebESP() {
        super("WebESP", 0, false, Category.RENDER);
        this.addSettings(new Setting[0]);
    }
    
    public void onDisable() {
        super.onDisable();
        WebESP.webs.clear();
    }
    
    @Subscribe
    public void PacketReceive(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2626) {
            final class_2626 packet = (class_2626)event.getPacket();
            if (BlockUtil.getBlock(packet.method_11309()) == class_2246.field_10343 && !WebESP.webs.contains(packet.method_11309())) {
                WebESP.webs.add(packet.method_11309());
            }
            if (BlockUtil.getBlock(packet.method_11309()) != class_2246.field_10343 && WebESP.webs.contains(packet.method_11309())) {
                WebESP.webs.remove(packet.method_11309());
            }
        }
    }
    
    @Subscribe
    public void onRenderWorld(final RenderWorldEvent event) {
        for (final class_2338 pos : WebESP.webs) {
            final class_4587 matrices = RenderUtil.matrixFrom(new class_243((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260()));
            RenderUtil.drawLine(matrices, new class_243(0.0, 0.0, 0.0), new class_243((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260()).method_1031(2.0, 2.0, 2.0), new Color(255, 0, 0), 2.0);
            RenderUtil.drawLine(matrices, new class_243(0.0, 1.0, 0.0), new class_243((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260()).method_1031(2.0, 0.0, 2.0), new Color(255, 0, 0), 2.0);
            RenderUtil.drawLine(matrices, new class_243(1.0, 0.0, 0.0), new class_243((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260()).method_1031(0.0, 2.0, 2.0), new Color(255, 0, 0), 2.0);
            RenderUtil.drawLine(matrices, new class_243(1.0, 1.0, 0.0), new class_243((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260()).method_1031(0.0, 0.0, 2.0), new Color(255, 0, 0), 2.0);
            RenderUtil.drawLine(matrices, new class_243(0.5, 1.0, 0.5), new class_243((double)pos.method_10263(), (double)pos.method_10264(), (double)pos.method_10260()).method_1031(1.0, 0.0, 1.0), new Color(255, 0, 0), 2.0);
        }
    }
    
    static {
        WebESP.webs = new ArrayList<class_2338>();
    }
}
