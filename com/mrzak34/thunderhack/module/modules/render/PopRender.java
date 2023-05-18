//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.render;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.google.common.eventbus.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.util.*;
import java.util.*;
import com.mojang.blaze3d.systems.*;
import com.mrzak34.thunderhack.manager.*;
import net.minecraft.*;

public class PopRender extends Module
{
    NumberSetting itemScale;
    NumberSetting counterScale;
    ParentSetting counter;
    public static HashMap<class_1657, Integer> itemActivationTicks;
    public static HashMap<class_1657, Float> itemActivationOffX;
    public static HashMap<class_1657, Float> itemActivationOffY;
    public static HashMap<class_1657, class_1799> itemActivationItem;
    private Random random;
    
    public PopRender() {
        super("PopRender", 0, false, Category.RENDER);
        this.itemScale = new NumberSetting("item scale", 0.1f, 0.1f, 1.0f, true);
        this.counterScale = new NumberSetting("counter scale", 1.0f, 1.0f, 10.0f, true);
        this.counter = new ParentSetting("counter", true, true, new Setting[] { this.counterScale });
        this.random = new Random();
        this.addSettings(new Setting[] { this.itemScale, this.counter });
    }
    
    @Subscribe
    public void onPacketReceive(final PacketEvent.Receive event) {
        if (event.getPacket() instanceof class_2663) {
            final class_2663 packet = (class_2663)event.getPacket();
            if (packet.method_11470() == 35 && packet.method_11469((class_1937)PopRender.mc.field_1687) instanceof class_1657) {
                final class_1657 player = (class_1657)packet.method_11469((class_1937)PopRender.mc.field_1687);
                PopRender.itemActivationItem.put(player, new class_1799((class_1935)class_1802.field_8288));
                this.showFloatingItem(player);
            }
        }
    }
    
    @Subscribe
    public void onRenderWorld(final RenderWorldEvent event) {
        try {
            for (final class_1657 player : PopRender.mc.field_1687.method_18456()) {
                if (player != null && !player.equals((Object)PopRender.mc.field_1724)) {
                    final class_243 pos = RenderUtil.smoothMovement((class_1297)player).method_1031(0.0, player.method_17682() - 0.8, 0.0);
                    this.renderNameTag(player, pos, event.getPartialTicks());
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private void renderNameTag(final class_1657 player, final class_243 pos, final float partialTicks) {
        final class_4184 camera = PopRender.mc.field_1773.method_19418();
        final double scale = 0.1;
        final class_4587 matrices = RenderUtil.matrixFrom(pos);
        matrices.method_22907(class_7833.field_40716.rotationDegrees(-camera.method_19330()));
        matrices.method_22907(class_7833.field_40714.rotationDegrees(camera.method_19329()));
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        matrices.method_22905(-0.025f * (float)scale, -0.025f * (float)scale, -0.025f * (float)scale);
        final class_4597.class_4598 immediate = class_4597.method_22991(class_289.method_1348().method_1349());
        this.renderFloatingItem(0, 0, partialTicks, matrices, player);
        final String pops = String.valueOf(TotemPopManager.getPlayerPops(player));
        class_332.method_25303(matrices, PopRender.mc.field_1772, pops, -PopRender.mc.field_1772.method_1727(pops) / 2, 0, -1);
    }
    
    public void onTick() {
        super.onTick();
        for (final class_1657 player : PopRender.mc.field_1687.method_18456()) {
            if (!PopRender.itemActivationTicks.containsKey(player)) {
                continue;
            }
            if (PopRender.itemActivationTicks.get(player) <= 0) {
                continue;
            }
            PopRender.itemActivationTicks.put(player, PopRender.itemActivationTicks.get(player) - 1);
            if (PopRender.itemActivationTicks.get(player) != 0) {
                continue;
            }
            PopRender.itemActivationItem.put(player, null);
        }
    }
    
    public void showFloatingItem(final class_1657 player) {
        PopRender.itemActivationTicks.put(player, 40);
        PopRender.itemActivationOffX.put(player, this.random.nextFloat() * 2.0f - 1.0f);
        PopRender.itemActivationOffY.put(player, this.random.nextFloat() * 2.0f - 1.0f);
    }
    
    private void renderFloatingItem(final int scaledWidth, final int scaledHeight, final float tickDelta, final class_4587 matrices, final class_1657 player) {
        if (PopRender.itemActivationItem.get(player) != null && PopRender.itemActivationTicks.get(player) > 0) {
            final int i = 40 - PopRender.itemActivationTicks.get(player);
            final float f = (i + tickDelta) / 40.0f;
            final float g = f * f;
            final float h = f * g;
            final float j = 10.25f * h * g - 24.95f * g * g + 25.5f * h - 13.8f * g + 4.0f * f;
            final float k = j * 3.1415927f;
            final float l = PopRender.itemActivationOffX.get(player) * (scaledWidth / 4);
            final float m = PopRender.itemActivationOffY.get(player) * (scaledHeight / 4);
            matrices.method_22903();
            matrices.method_22904((double)(scaledWidth / 2 + l * class_3532.method_15379(class_3532.method_15374(k * 2.0f))), (double)(scaledHeight / 2 + m * class_3532.method_15379(class_3532.method_15374(k * 2.0f))), -50.0);
            final float n = 50.0f + 175.0f * class_3532.method_15374(k);
            matrices.method_22905(n, -n, n);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(900.0f * class_3532.method_15379(class_3532.method_15374(k))));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(6.0f * class_3532.method_15362(f * 8.0f)));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(6.0f * class_3532.method_15362(f * 8.0f)));
            final class_4597.class_4598 immediate = class_310.method_1551().method_22940().method_23000();
            class_308.method_24210();
            immediate.method_22993();
            PopRender.mc.method_1480().method_23178((class_1799)PopRender.itemActivationItem.get(player), class_811.field_4317, 15728880, class_4608.field_21444, matrices, (class_4597)immediate, (class_1937)PopRender.mc.field_1687, 0);
            matrices.method_22909();
        }
    }
    
    static {
        PopRender.itemActivationTicks = new HashMap<class_1657, Integer>();
        PopRender.itemActivationOffX = new HashMap<class_1657, Float>();
        PopRender.itemActivationOffY = new HashMap<class_1657, Float>();
        PopRender.itemActivationItem = new HashMap<class_1657, class_1799>();
    }
}
