/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1297
 *  net.minecraft.class_1802
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2829
 *  net.minecraft.class_2828$class_2830
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 */
package com.mrzak34.thunderhack.module.modules.combat;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.settings.ParentSetting;
import com.mrzak34.thunderhack.util.TimerUtil;
import java.util.Random;
import net.minecraft.class_1297;
import net.minecraft.class_1802;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_2846;
import net.minecraft.class_2848;

public class FastProjectile
extends Module {
    public BooleanSetting rotation = new BooleanSetting("rotation", true);
    public ModeSetting mode = new ModeSetting("mode", "maximum", "normal", "maximum", "factorised");
    public NumberSetting factor = new NumberSetting("factor", 1.0f, 1.0f, 20.0f, true);
    public ModeSetting exploit = new ModeSetting("exploit", "strong", "strong", "fast", "strict", "phobos");
    public NumberSetting scale = new NumberSetting("scale", 0.01f, 0.01f, 0.4f, true);
    public BooleanSetting minimize = new BooleanSetting("minimize", false);
    public NumberSetting delay = new NumberSetting("delay", 2.0f, 0.0f, 10.0f, true);
    public static BooleanSetting bows = new BooleanSetting("bows", true);
    public static BooleanSetting pearls = new BooleanSetting("pearls", true);
    public static BooleanSetting xps = new BooleanSetting("xps", true);
    public static BooleanSetting eggs = new BooleanSetting("eggs", true);
    public static BooleanSetting snowballs = new BooleanSetting("snowballs", true);
    public static BooleanSetting potions = new BooleanSetting("potions", true);
    public static BooleanSetting trident = new BooleanSetting("trident", true);
    public ParentSetting selection = new ParentSetting("selection", false, false, bows, pearls, xps, eggs, snowballs, potions, trident);
    private Random rnd = new Random();
    private TimerUtil delayTimer = new TimerUtil();

    public FastProjectile() {
        super("FastProjectile", 0, false, Category.COMBAT);
        this.addSettings(this.rotation, this.mode, this.factor, this.exploit, this.scale, this.minimize, this.delay, this.selection);
    }

    @Subscribe
    public void PacketSend(PacketEvent.Send event) {
        if (FastProjectile.fullNullCheck() || !this.delayTimer.passedMs((long)(this.delay.getValue() * 1000.0))) {
            return;
        }
        if (event.getPacket() instanceof class_2846) {
            if (((class_2846)event.getPacket()).method_12363() == class_2846.class_2847.field_12974 && (FastProjectile.mc.field_1724.method_6030().method_7909() == class_1802.field_8102 && bows.isEnabled() || FastProjectile.mc.field_1724.method_6030().method_7909() == class_1802.field_8634 && pearls.isEnabled()) || FastProjectile.mc.field_1724.method_6030().method_7909() == class_1802.field_8287 && xps.isEnabled() || FastProjectile.mc.field_1724.method_6030().method_7909() == class_1802.field_8803 && eggs.isEnabled() || FastProjectile.mc.field_1724.method_6030().method_7909() == class_1802.field_8436 && potions.isEnabled() || FastProjectile.mc.field_1724.method_6030().method_7909() == class_1802.field_8543 && snowballs.isEnabled()) {
                int i;
                FastProjectile.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)FastProjectile.mc.field_1724, class_2848.class_2849.field_12981));
                double[] strict_direction = this.getDirectionSpeed();
                if (this.exploit.getMode().equals("fast")) {
                    for (i = 0; i < this.getRuns(); ++i) {
                        this.spoof(FastProjectile.mc.field_1724.method_23317(), this.minimize.isEnabled() ? FastProjectile.mc.field_1724.method_23318() : FastProjectile.mc.field_1724.method_23318() - 1.0E-10, FastProjectile.mc.field_1724.method_23321(), true);
                        this.spoof(FastProjectile.mc.field_1724.method_23317(), FastProjectile.mc.field_1724.method_23318() + 1.0E-10, FastProjectile.mc.field_1724.method_23321(), false);
                    }
                }
                if (this.exploit.getMode().equals("strong")) {
                    for (i = 0; i < this.getRuns(); ++i) {
                        this.spoof(FastProjectile.mc.field_1724.method_23317(), FastProjectile.mc.field_1724.method_23318() + 1.0E-10, FastProjectile.mc.field_1724.method_23321(), false);
                        this.spoof(FastProjectile.mc.field_1724.method_23317(), this.minimize.isEnabled() ? FastProjectile.mc.field_1724.method_23318() : FastProjectile.mc.field_1724.method_23318() - 1.0E-10, FastProjectile.mc.field_1724.method_23321(), true);
                    }
                }
                if (this.exploit.getMode().equals("phobos")) {
                    for (i = 0; i < this.getRuns(); ++i) {
                        this.spoof(FastProjectile.mc.field_1724.method_23317(), FastProjectile.mc.field_1724.method_23318() + 1.3E-13, FastProjectile.mc.field_1724.method_23321(), true);
                        this.spoof(FastProjectile.mc.field_1724.method_23317(), FastProjectile.mc.field_1724.method_23318() + 2.7E-13, FastProjectile.mc.field_1724.method_23321(), false);
                    }
                }
                if (this.exploit.getMode().equals("strict")) {
                    for (i = 0; i < this.getRuns(); ++i) {
                        if (this.rnd.nextBoolean()) {
                            this.spoof(FastProjectile.mc.field_1724.method_23317() - strict_direction[0], FastProjectile.mc.field_1724.method_23318(), FastProjectile.mc.field_1724.method_23321() - strict_direction[1], false);
                            continue;
                        }
                        this.spoof(FastProjectile.mc.field_1724.method_23317() + strict_direction[0], FastProjectile.mc.field_1724.method_23318(), FastProjectile.mc.field_1724.method_23321() + strict_direction[1], true);
                    }
                }
            }
            this.delayTimer.reset();
        }
    }

    private void spoof(double x, double y, double z, boolean ground) {
        if (this.rotation.isEnabled()) {
            FastProjectile.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2830(x, y, z, FastProjectile.mc.field_1724.method_36454(), FastProjectile.mc.field_1724.method_36455(), ground));
        } else {
            FastProjectile.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(x, y, z, ground));
        }
    }

    private double[] getDirectionSpeed() {
        return new double[]{100.0 * -Math.sin(Math.toRadians(FastProjectile.mc.field_1724.method_36454())), 100.0 * Math.cos(Math.toRadians(FastProjectile.mc.field_1724.method_36454()))};
    }

    int getRuns() {
        if (this.mode.getMode().equals("factorised")) {
            return 10 + (int)(this.factor.getValue() - 1.0);
        }
        if (this.mode.getMode().equals("normal")) {
            return (int)Math.floor(this.factor.getValue());
        }
        if (this.mode.getMode().equals("maximum")) {
            return (int)(30.0 * this.factor.getValue());
        }
        return 1;
    }
}

