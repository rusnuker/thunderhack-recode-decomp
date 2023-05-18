/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1799
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2382
 *  net.minecraft.class_239
 *  net.minecraft.class_239$class_240
 *  net.minecraft.class_2480
 *  net.minecraft.class_2649
 *  net.minecraft.class_2680
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_7833
 */
package com.mrzak34.thunderhack.module.modules.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.event.events.RenderWorldEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.util.RenderUtil;
import java.util.ArrayList;
import java.util.HashMap;
import net.minecraft.class_1799;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2382;
import net.minecraft.class_239;
import net.minecraft.class_2480;
import net.minecraft.class_2649;
import net.minecraft.class_2680;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_7833;

public class ContainerPrieview
extends Module {
    public static HashMap<class_2338, ArrayList<class_1799>> PosItems = new HashMap();

    public ContainerPrieview() {
        super("ContainerPrieview", 0, false, Category.RENDER);
    }

    @Subscribe
    public void onRenderWorld(RenderWorldEvent event) {
        class_239 ray = ContainerPrieview.mc.field_1765;
        if (ray == null) {
            return;
        }
        if (ray.method_17783() != class_239.class_240.field_1332) {
            return;
        }
        if (!PosItems.containsKey((Object)new class_2338((int)ray.method_17784().field_1352, (int)ray.method_17784().field_1351, (int)ray.method_17784().field_1350))) {
            return;
        }
        ContainerPrieview.renderNametag(new class_2338((int)ray.method_17784().field_1352, (int)ray.method_17784().field_1351, (int)ray.method_17784().field_1350));
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2649) {
            class_239 ray = ContainerPrieview.mc.field_1765;
            if (ray == null) {
                return;
            }
            if (ray.method_17783() != class_239.class_240.field_1332) {
                return;
            }
            class_2680 l_State = ContainerPrieview.mc.field_1687.method_8320(new class_2338((int)ray.method_17784().field_1352, (int)ray.method_17784().field_1351, (int)ray.method_17784().field_1350));
            if (l_State == null) {
                return;
            }
            if (l_State.method_26204() != class_2246.field_10034 && !(l_State.method_26204() instanceof class_2480)) {
                return;
            }
            class_2649 packet = (class_2649)event.getPacket();
            class_2338 blockpos = new class_2338((class_2382)new class_2338((int)ray.method_17784().field_1352, (int)ray.method_17784().field_1351, (int)ray.method_17784().field_1350));
            if (PosItems.containsKey((Object)blockpos)) {
                PosItems.remove((Object)blockpos);
            }
            ArrayList<class_1799> l_List = new ArrayList<class_1799>();
            for (int i = 0; i < packet.method_11441().size() - 36; ++i) {
                class_1799 itemStack = (class_1799)packet.method_11441().get(i);
                if (itemStack == null) continue;
                l_List.add(itemStack);
            }
            PosItems.put(blockpos, l_List);
        }
    }

    public static void renderNametag(class_2338 pos) {
        ArrayList<class_1799> itemList = PosItems.get((Object)pos);
        class_4587 matrices = RenderUtil.matrixFrom(pos.method_10069(0, 1, 0).method_46558());
        class_4184 camera = ContainerPrieview.mc.field_1773.method_19418();
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
        for (class_1799 stack : itemList) {
            if (stack != null) {
                RenderUtil.renderItemStack(matrices, stack, x, l_Y, null);
                x += 16;
            }
            if (++l_I % 9 != 0) continue;
            x = -72;
            l_Y += 15;
        }
    }
}

