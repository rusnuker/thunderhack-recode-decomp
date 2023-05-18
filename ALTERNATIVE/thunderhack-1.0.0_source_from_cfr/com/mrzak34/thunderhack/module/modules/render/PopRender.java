/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1799
 *  net.minecraft.class_1802
 *  net.minecraft.class_1935
 *  net.minecraft.class_1937
 *  net.minecraft.class_243
 *  net.minecraft.class_2663
 *  net.minecraft.class_287
 *  net.minecraft.class_289
 *  net.minecraft.class_308
 *  net.minecraft.class_310
 *  net.minecraft.class_327
 *  net.minecraft.class_332
 *  net.minecraft.class_3532
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_4608
 *  net.minecraft.class_7833
 *  net.minecraft.class_811
 */
package com.mrzak34.thunderhack.module.modules.render;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.event.events.RenderWorldEvent;
import com.mrzak34.thunderhack.manager.TotemPopManager;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.settings.ParentSetting;
import com.mrzak34.thunderhack.util.RenderUtil;
import java.util.HashMap;
import java.util.Random;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1935;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_2663;
import net.minecraft.class_287;
import net.minecraft.class_289;
import net.minecraft.class_308;
import net.minecraft.class_310;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_7833;
import net.minecraft.class_811;

public class PopRender
extends Module {
    NumberSetting itemScale = new NumberSetting("item scale", 0.1f, 0.1f, 1.0f, true);
    NumberSetting counterScale = new NumberSetting("counter scale", 1.0f, 1.0f, 10.0f, true);
    ParentSetting counter = new ParentSetting("counter", true, true, this.counterScale);
    public static HashMap<class_1657, Integer> itemActivationTicks = new HashMap();
    public static HashMap<class_1657, Float> itemActivationOffX = new HashMap();
    public static HashMap<class_1657, Float> itemActivationOffY = new HashMap();
    public static HashMap<class_1657, class_1799> itemActivationItem = new HashMap();
    private Random random = new Random();

    public PopRender() {
        super("PopRender", 0, false, Category.RENDER);
        this.addSettings(this.itemScale, this.counter);
    }

    @Subscribe
    public void onPacketReceive(PacketEvent.Receive event) {
        class_2663 packet;
        if (event.getPacket() instanceof class_2663 && (packet = (class_2663)event.getPacket()).method_11470() == 35 && packet.method_11469((class_1937)PopRender.mc.field_1687) instanceof class_1657) {
            class_1657 player = (class_1657)packet.method_11469((class_1937)PopRender.mc.field_1687);
            itemActivationItem.put(player, new class_1799((class_1935)class_1802.field_8288));
            this.showFloatingItem(player);
        }
    }

    @Subscribe
    public void onRenderWorld(RenderWorldEvent event) {
        try {
            for (class_1657 player : PopRender.mc.field_1687.method_18456()) {
                if (player == null || player.equals((Object)PopRender.mc.field_1724)) continue;
                class_243 pos = RenderUtil.smoothMovement((class_1297)player).method_1031(0.0, (double)player.method_17682() - 0.8, 0.0);
                this.renderNameTag(player, pos, event.getPartialTicks());
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    private void renderNameTag(class_1657 player, class_243 pos, float partialTicks) {
        class_4184 camera = PopRender.mc.field_1773.method_19418();
        double scale = 0.1;
        class_4587 matrices = RenderUtil.matrixFrom(pos);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        matrices.method_22905(-0.025f * (float)scale, -0.025f * (float)scale, -0.025f * (float)scale);
        class_4597.class_4598 immediate = class_4597.method_22991((class_287)class_289.method_1348().method_1349());
        this.renderFloatingItem(0, 0, partialTicks, matrices, player);
        String pops = String.valueOf(TotemPopManager.getPlayerPops(player));
        class_332.method_25303((class_4587)matrices, (class_327)PopRender.mc.field_1772, (String)pops, (int)(-PopRender.mc.field_1772.method_1727(pops) / 2), (int)0, (int)-1);
    }

    @Override
    public void onTick() {
        super.onTick();
        for (class_1657 player : PopRender.mc.field_1687.method_18456()) {
            if (!itemActivationTicks.containsKey((Object)player)) continue;
            if (itemActivationTicks.get((Object)player) <= 0) continue;
            itemActivationTicks.put(player, itemActivationTicks.get((Object)player) - 1);
            if (itemActivationTicks.get((Object)player) != 0) continue;
            itemActivationItem.put(player, null);
        }
    }

    public void showFloatingItem(class_1657 player) {
        itemActivationTicks.put(player, 40);
        itemActivationOffX.put(player, Float.valueOf(this.random.nextFloat() * 2.0f - 1.0f));
        itemActivationOffY.put(player, Float.valueOf(this.random.nextFloat() * 2.0f - 1.0f));
    }

    private void renderFloatingItem(int scaledWidth, int scaledHeight, float tickDelta, class_4587 matrices, class_1657 player) {
        if (itemActivationItem.get((Object)player) != null) {
            if (itemActivationTicks.get((Object)player) > 0) {
                int i = 40 - itemActivationTicks.get((Object)player);
                float f = ((float)i + tickDelta) / 40.0f;
                float g = f * f;
                float h = f * g;
                float j = 10.25f * h * g - 24.95f * g * g + 25.5f * h - 13.8f * g + 4.0f * f;
                float k = j * (float)Math.PI;
                float l = itemActivationOffX.get((Object)player).floatValue() * (float)(scaledWidth / 4);
                float m = itemActivationOffY.get((Object)player).floatValue() * (float)(scaledHeight / 4);
                matrices.method_22903();
                matrices.method_22904((double)((float)(scaledWidth / 2) + l * class_3532.method_15379((float)class_3532.method_15374((float)(k * 2.0f)))), (double)((float)(scaledHeight / 2) + m * class_3532.method_15379((float)class_3532.method_15374((float)(k * 2.0f)))), -50.0);
                float n = 50.0f + 175.0f * class_3532.method_15374((float)k);
                matrices.method_22905(n, -n, n);
                matrices.method_22907(class_7833.field_40716.rotationDegrees(900.0f * class_3532.method_15379((float)class_3532.method_15374((float)k))));
                matrices.method_22907(class_7833.field_40714.rotationDegrees(6.0f * class_3532.method_15362((float)(f * 8.0f))));
                matrices.method_22907(class_7833.field_40718.rotationDegrees(6.0f * class_3532.method_15362((float)(f * 8.0f))));
                class_4597.class_4598 immediate = class_310.method_1551().method_22940().method_23000();
                class_308.method_24210();
                immediate.method_22993();
                mc.method_1480().method_23178(itemActivationItem.get((Object)player), class_811.field_4317, 0xF000F0, class_4608.field_21444, matrices, (class_4597)immediate, (class_1937)PopRender.mc.field_1687, 0);
                matrices.method_22909();
            }
        }
    }
}

