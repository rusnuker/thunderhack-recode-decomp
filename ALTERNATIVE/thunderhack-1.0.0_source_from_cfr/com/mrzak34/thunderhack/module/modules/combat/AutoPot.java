/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_1268
 *  net.minecraft.class_1799
 *  net.minecraft.class_1937
 *  net.minecraft.class_2561
 *  net.minecraft.class_2596
 *  net.minecraft.class_2868
 *  net.minecraft.class_2879
 *  net.minecraft.class_2886
 *  net.minecraft.class_310
 *  net.minecraft.class_3417
 *  net.minecraft.class_3419
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_4597
 *  net.minecraft.class_4597$class_4598
 *  net.minecraft.class_4608
 *  net.minecraft.class_7833
 *  net.minecraft.class_811
 */
package com.mrzak34.thunderhack.module.modules.combat;

import com.google.common.eventbus.Subscribe;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mrzak34.thunderhack.event.events.RenderOverlayEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.util.InventoryUtil;
import com.mrzak34.thunderhack.util.TimerUtil;
import java.util.Random;
import net.minecraft.class_1268;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_2561;
import net.minecraft.class_2596;
import net.minecraft.class_2868;
import net.minecraft.class_2879;
import net.minecraft.class_2886;
import net.minecraft.class_310;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4608;
import net.minecraft.class_7833;
import net.minecraft.class_811;

public class AutoPot
extends Module {
    public NumberSetting triggerhealth = new NumberSetting("triggerhealth", 10.0f, 1.0f, 36.0f, false);
    public NumberSetting delay = new NumberSetting("delay", 200.0f, 1.0f, 2000.0f, false);
    public BooleanSetting animation = new BooleanSetting("animation", true);
    public static int neededCap = 0;
    public TimerUtil timer = new TimerUtil();
    public TimerUtil alerttimer = new TimerUtil();
    private int itemActivationTicks;
    private float itemActivationOffX;
    private float itemActivationOffY;
    private class_1799 itemActivationItem;
    private Random random = new Random();

    public AutoPot() {
        super("AutoPot", 0, false, Category.COMBAT);
        this.addSettings(this.triggerhealth, this.delay, this.animation);
    }

    @Subscribe
    public void RenderOverlay(RenderOverlayEvent event) {
        if (this.animation.isEnabled()) {
            this.renderFloatingItem(960, 540, event.getPartialTicks(), event.getMatrices());
        }
    }

    @Override
    public void onTick() {
        super.onTick();
        if ((double)AutoPot.mc.field_1724.method_6032() < this.triggerhealth.getValue() && this.timer.passedMs((long)this.delay.getValue()) && InventoryUtil.getCappuchinoAtHotbar() != -1) {
            this.itemActivationItem = InventoryUtil.getPotionItemStack();
            int hotbarslot = AutoPot.mc.field_1724.method_31548().field_7545;
            AutoPot.mc.field_1687.method_8486(AutoPot.mc.field_1724.method_23317(), AutoPot.mc.field_1724.method_23318(), AutoPot.mc.field_1724.method_23318(), class_3417.field_14978, class_3419.field_15256, 150.0f, 1.0f, true);
            AutoPot.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(InventoryUtil.getCappuchinoAtHotbar()));
            AutoPot.mc.field_1724.field_3944.method_2883((class_2596)new class_2886(class_1268.field_5808, 0));
            AutoPot.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
            AutoPot.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(hotbarslot));
            ++neededCap;
            this.showFloatingItem();
            this.timer.reset();
        }
        if (InventoryUtil.getCappuchinoAtHotbar() == -1 && this.alerttimer.passedMs(1000L)) {
            AutoPot.mc.field_1724.method_43496(class_2561.method_30163((String)"\u041d\u0435\u043c\u0430 \u0437\u0435\u043b\u0435\u043a!!!!"));
            this.alerttimer.reset();
        }
        if (this.itemActivationTicks > 0) {
            --this.itemActivationTicks;
            if (this.itemActivationTicks == 0) {
                this.itemActivationItem = null;
            }
        }
    }

    public void showFloatingItem() {
        this.itemActivationTicks = 40;
        this.itemActivationOffX = this.random.nextFloat() * 2.0f - 1.0f;
        this.itemActivationOffY = this.random.nextFloat() * 2.0f - 1.0f;
    }

    private void renderFloatingItem(int scaledWidth, int scaledHeight, float tickDelta, class_4587 matrices) {
        if (this.itemActivationItem != null && this.itemActivationTicks > 0) {
            int i = 40 - this.itemActivationTicks;
            float f = ((float)i + tickDelta) / 40.0f;
            float g = f * f;
            float h = f * g;
            float j = 10.25f * h * g - 24.95f * g * g + 25.5f * h - 13.8f * g + 4.0f * f;
            float k = j * (float)Math.PI;
            float l = this.itemActivationOffX * (float)(scaledWidth / 4);
            float m = this.itemActivationOffY * (float)(scaledHeight / 4);
            RenderSystem.enableDepthTest();
            RenderSystem.disableCull();
            matrices.method_22903();
            matrices.method_22904((double)((float)(scaledWidth / 2) + l * class_3532.method_15379((float)class_3532.method_15374((float)(k * 2.0f)))), (double)((float)(scaledHeight / 2) + m * class_3532.method_15379((float)class_3532.method_15374((float)(k * 2.0f)))), -50.0);
            float n = 50.0f + 175.0f * class_3532.method_15374((float)k);
            matrices.method_22905(n, -n, n);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(900.0f * class_3532.method_15379((float)class_3532.method_15374((float)k))));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(6.0f * class_3532.method_15362((float)(f * 8.0f))));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(6.0f * class_3532.method_15362((float)(f * 8.0f))));
            class_4597.class_4598 immediate = class_310.method_1551().method_22940().method_23000();
            mc.method_1480().method_23178(this.itemActivationItem, class_811.field_4319, 0xF000F0, class_4608.field_21444, matrices, (class_4597)immediate, (class_1937)AutoPot.mc.field_1687, 0);
            matrices.method_22909();
            immediate.method_22993();
            RenderSystem.enableCull();
            RenderSystem.disableDepthTest();
        }
    }
}

