//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.combat;

import java.util.*;
import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import com.google.common.eventbus.*;
import com.mrzak34.thunderhack.util.*;
import com.mojang.blaze3d.systems.*;
import net.minecraft.*;

public class AutoPot extends Module
{
    public NumberSetting triggerhealth;
    public NumberSetting delay;
    public BooleanSetting animation;
    public static int neededCap;
    public TimerUtil timer;
    public TimerUtil alerttimer;
    private int itemActivationTicks;
    private float itemActivationOffX;
    private float itemActivationOffY;
    private class_1799 itemActivationItem;
    private Random random;
    
    public AutoPot() {
        super("AutoPot", 0, false, Category.COMBAT);
        this.triggerhealth = new NumberSetting("triggerhealth", 10.0f, 1.0f, 36.0f, false);
        this.delay = new NumberSetting("delay", 200.0f, 1.0f, 2000.0f, false);
        this.animation = new BooleanSetting("animation", true);
        this.timer = new TimerUtil();
        this.alerttimer = new TimerUtil();
        this.random = new Random();
        this.addSettings(new Setting[] { this.triggerhealth, this.delay, this.animation });
    }
    
    @Subscribe
    public void RenderOverlay(final RenderOverlayEvent event) {
        if (this.animation.isEnabled()) {
            this.renderFloatingItem(960, 540, event.getPartialTicks(), event.getMatrices());
        }
    }
    
    public void onTick() {
        super.onTick();
        if (AutoPot.mc.field_1724.method_6032() < this.triggerhealth.getValue() && this.timer.passedMs((long)this.delay.getValue()) && InventoryUtil.getCappuchinoAtHotbar() != -1) {
            this.itemActivationItem = InventoryUtil.getPotionItemStack();
            final int hotbarslot = AutoPot.mc.field_1724.method_31548().field_7545;
            AutoPot.mc.field_1687.method_8486(AutoPot.mc.field_1724.method_23317(), AutoPot.mc.field_1724.method_23318(), AutoPot.mc.field_1724.method_23318(), class_3417.field_14978, class_3419.field_15256, 150.0f, 1.0f, true);
            AutoPot.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(InventoryUtil.getCappuchinoAtHotbar()));
            AutoPot.mc.field_1724.field_3944.method_2883((class_2596)new class_2886(class_1268.field_5808, 0));
            AutoPot.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
            AutoPot.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(hotbarslot));
            ++AutoPot.neededCap;
            this.showFloatingItem();
            this.timer.reset();
        }
        if (InventoryUtil.getCappuchinoAtHotbar() == -1 && this.alerttimer.passedMs(1000L)) {
            AutoPot.mc.field_1724.method_43496(class_2561.method_30163("\u041d\u0435\u043c\u0430 \u0437\u0435\u043b\u0435\u043a!!!!"));
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
    
    private void renderFloatingItem(final int scaledWidth, final int scaledHeight, final float tickDelta, final class_4587 matrices) {
        if (this.itemActivationItem != null && this.itemActivationTicks > 0) {
            final int i = 40 - this.itemActivationTicks;
            final float f = (i + tickDelta) / 40.0f;
            final float g = f * f;
            final float h = f * g;
            final float j = 10.25f * h * g - 24.95f * g * g + 25.5f * h - 13.8f * g + 4.0f * f;
            final float k = j * 3.1415927f;
            final float l = this.itemActivationOffX * (scaledWidth / 4);
            final float m = this.itemActivationOffY * (scaledHeight / 4);
            RenderSystem.enableDepthTest();
            RenderSystem.disableCull();
            matrices.method_22903();
            matrices.method_22904((double)(scaledWidth / 2 + l * class_3532.method_15379(class_3532.method_15374(k * 2.0f))), (double)(scaledHeight / 2 + m * class_3532.method_15379(class_3532.method_15374(k * 2.0f))), -50.0);
            final float n = 50.0f + 175.0f * class_3532.method_15374(k);
            matrices.method_22905(n, -n, n);
            matrices.method_22907(class_7833.field_40716.rotationDegrees(900.0f * class_3532.method_15379(class_3532.method_15374(k))));
            matrices.method_22907(class_7833.field_40714.rotationDegrees(6.0f * class_3532.method_15362(f * 8.0f)));
            matrices.method_22907(class_7833.field_40718.rotationDegrees(6.0f * class_3532.method_15362(f * 8.0f)));
            final class_4597.class_4598 immediate = class_310.method_1551().method_22940().method_23000();
            AutoPot.mc.method_1480().method_23178(this.itemActivationItem, class_811.field_4319, 15728880, class_4608.field_21444, matrices, (class_4597)immediate, (class_1937)AutoPot.mc.field_1687, 0);
            matrices.method_22909();
            immediate.method_22993();
            RenderSystem.enableCull();
            RenderSystem.disableDepthTest();
        }
    }
    
    static {
        AutoPot.neededCap = 0;
    }
}
