//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.render;

import com.mrzak34.thunderhack.module.*;
import com.google.common.eventbus.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.util.*;
import com.mojang.blaze3d.systems.*;
import net.minecraft.*;
import java.util.*;

public class ContainerPrieview extends Module
{
    public static HashMap<class_2338, ArrayList<class_1799>> PosItems;
    
    public ContainerPrieview() {
        super("ContainerPrieview", 0, false, Category.RENDER);
    }
    
    @Subscribe
    public void onRenderWorld(final RenderWorldEvent event) {
        final class_239 ray = ContainerPrieview.mc.field_1765;
        if (ray == null) {
            return;
        }
        if (ray.method_17783() != class_239.class_240.field_1332) {
            return;
        }
        if (!ContainerPrieview.PosItems.containsKey(new class_2338((int)ray.method_17784().field_1352, (int)ray.method_17784().field_1351, (int)ray.method_17784().field_1350))) {
            return;
        }
        renderNametag(new class_2338((int)ray.method_17784().field_1352, (int)ray.method_17784().field_1351, (int)ray.method_17784().field_1350));
    }
    
    @Subscribe
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2649) {
            final class_239 ray = ContainerPrieview.mc.field_1765;
            if (ray == null) {
                return;
            }
            if (ray.method_17783() != class_239.class_240.field_1332) {
                return;
            }
            final class_2680 l_State = ContainerPrieview.mc.field_1687.method_8320(new class_2338((int)ray.method_17784().field_1352, (int)ray.method_17784().field_1351, (int)ray.method_17784().field_1350));
            if (l_State == null) {
                return;
            }
            if (l_State.method_26204() != class_2246.field_10034 && !(l_State.method_26204() instanceof class_2480)) {
                return;
            }
            final class_2649 packet = (class_2649)event.getPacket();
            final class_2338 blockpos = new class_2338((class_2382)new class_2338((int)ray.method_17784().field_1352, (int)ray.method_17784().field_1351, (int)ray.method_17784().field_1350));
            if (ContainerPrieview.PosItems.containsKey(blockpos)) {
                ContainerPrieview.PosItems.remove(blockpos);
            }
            final ArrayList<class_1799> l_List = new ArrayList<class_1799>();
            for (int i = 0; i < packet.method_11441().size() - 36; ++i) {
                final class_1799 itemStack = packet.method_11441().get(i);
                if (itemStack != null) {
                    l_List.add(itemStack);
                }
            }
            ContainerPrieview.PosItems.put(blockpos, l_List);
        }
    }
    
    public static void renderNametag(final class_2338 pos) {
        final ArrayList<class_1799> itemList = ContainerPrieview.PosItems.get(pos);
        final class_4587 matrices = RenderUtil.matrixFrom(pos.method_10069(0, 1, 0).method_46558());
        final class_4184 camera = ContainerPrieview.mc.field_1773.method_19418();
        matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        matrices.method_22904(0.0, 0.0, 0.0);
        matrices.method_22905(-0.01f, -0.01f, -0.01f);
        if (itemList == null) {
            return;
        }
        int l_I = 0;
        int l_Y = -20;
        int x = -72;
        for (final class_1799 stack : itemList) {
            if (stack != null) {
                RenderUtil.renderItemStack(matrices, stack, x, l_Y, null);
                x += 16;
            }
            if (++l_I % 9 == 0) {
                x = -72;
                l_Y += 15;
            }
        }
    }
    
    static {
        ContainerPrieview.PosItems = new HashMap<class_2338, ArrayList<class_1799>>();
    }
}
