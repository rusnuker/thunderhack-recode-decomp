//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.combat;

import java.util.*;
import com.mrzak34.thunderhack.util.*;
import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import com.google.common.eventbus.*;
import net.minecraft.*;

public class FastProjectile extends Module
{
    public BooleanSetting rotation;
    public ModeSetting mode;
    public NumberSetting factor;
    public ModeSetting exploit;
    public NumberSetting scale;
    public BooleanSetting minimize;
    public NumberSetting delay;
    public static BooleanSetting bows;
    public static BooleanSetting pearls;
    public static BooleanSetting xps;
    public static BooleanSetting eggs;
    public static BooleanSetting snowballs;
    public static BooleanSetting potions;
    public static BooleanSetting trident;
    public ParentSetting selection;
    private Random rnd;
    private TimerUtil delayTimer;
    
    public FastProjectile() {
        super("FastProjectile", 0, false, Category.COMBAT);
        this.rotation = new BooleanSetting("rotation", true);
        this.mode = new ModeSetting("mode", "maximum", new String[] { "normal", "maximum", "factorised" });
        this.factor = new NumberSetting("factor", 1.0f, 1.0f, 20.0f, true);
        this.exploit = new ModeSetting("exploit", "strong", new String[] { "strong", "fast", "strict", "phobos" });
        this.scale = new NumberSetting("scale", 0.01f, 0.01f, 0.4f, true);
        this.minimize = new BooleanSetting("minimize", false);
        this.delay = new NumberSetting("delay", 2.0f, 0.0f, 10.0f, true);
        this.selection = new ParentSetting("selection", false, false, new Setting[] { FastProjectile.bows, FastProjectile.pearls, FastProjectile.xps, FastProjectile.eggs, FastProjectile.snowballs, FastProjectile.potions, FastProjectile.trident });
        this.rnd = new Random();
        this.delayTimer = new TimerUtil();
        this.addSettings(new Setting[] { this.rotation, this.mode, this.factor, this.exploit, this.scale, this.minimize, this.delay, this.selection });
    }
    
    @Subscribe
    public void PacketSend(final PacketEvent.Send event) {
        if (fullNullCheck() || !this.delayTimer.passedMs((long)(this.delay.getValue() * 1000.0))) {
            return;
        }
        if (event.getPacket() instanceof class_2846) {
            if ((((class_2846)event.getPacket()).method_12363() == class_2846.class_2847.field_12974 && ((FastProjectile.mc.field_1724.method_6030().method_7909() == class_1802.field_8102 && FastProjectile.bows.isEnabled()) || (FastProjectile.mc.field_1724.method_6030().method_7909() == class_1802.field_8634 && FastProjectile.pearls.isEnabled()))) || (FastProjectile.mc.field_1724.method_6030().method_7909() == class_1802.field_8287 && FastProjectile.xps.isEnabled()) || (FastProjectile.mc.field_1724.method_6030().method_7909() == class_1802.field_8803 && FastProjectile.eggs.isEnabled()) || (FastProjectile.mc.field_1724.method_6030().method_7909() == class_1802.field_8436 && FastProjectile.potions.isEnabled()) || (FastProjectile.mc.field_1724.method_6030().method_7909() == class_1802.field_8543 && FastProjectile.snowballs.isEnabled())) {
                FastProjectile.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)FastProjectile.mc.field_1724, class_2848.class_2849.field_12981));
                final double[] strict_direction = this.getDirectionSpeed();
                if (this.exploit.getMode().equals("fast")) {
                    for (int i = 0; i < this.getRuns(); ++i) {
                        this.spoof(FastProjectile.mc.field_1724.method_23317(), this.minimize.isEnabled() ? FastProjectile.mc.field_1724.method_23318() : (FastProjectile.mc.field_1724.method_23318() - 1.0E-10), FastProjectile.mc.field_1724.method_23321(), true);
                        this.spoof(FastProjectile.mc.field_1724.method_23317(), FastProjectile.mc.field_1724.method_23318() + 1.0E-10, FastProjectile.mc.field_1724.method_23321(), false);
                    }
                }
                if (this.exploit.getMode().equals("strong")) {
                    for (int i = 0; i < this.getRuns(); ++i) {
                        this.spoof(FastProjectile.mc.field_1724.method_23317(), FastProjectile.mc.field_1724.method_23318() + 1.0E-10, FastProjectile.mc.field_1724.method_23321(), false);
                        this.spoof(FastProjectile.mc.field_1724.method_23317(), this.minimize.isEnabled() ? FastProjectile.mc.field_1724.method_23318() : (FastProjectile.mc.field_1724.method_23318() - 1.0E-10), FastProjectile.mc.field_1724.method_23321(), true);
                    }
                }
                if (this.exploit.getMode().equals("phobos")) {
                    for (int i = 0; i < this.getRuns(); ++i) {
                        this.spoof(FastProjectile.mc.field_1724.method_23317(), FastProjectile.mc.field_1724.method_23318() + 1.3E-13, FastProjectile.mc.field_1724.method_23321(), true);
                        this.spoof(FastProjectile.mc.field_1724.method_23317(), FastProjectile.mc.field_1724.method_23318() + 2.7E-13, FastProjectile.mc.field_1724.method_23321(), false);
                    }
                }
                if (this.exploit.getMode().equals("strict")) {
                    for (int i = 0; i < this.getRuns(); ++i) {
                        if (this.rnd.nextBoolean()) {
                            this.spoof(FastProjectile.mc.field_1724.method_23317() - strict_direction[0], FastProjectile.mc.field_1724.method_23318(), FastProjectile.mc.field_1724.method_23321() - strict_direction[1], false);
                        }
                        else {
                            this.spoof(FastProjectile.mc.field_1724.method_23317() + strict_direction[0], FastProjectile.mc.field_1724.method_23318(), FastProjectile.mc.field_1724.method_23321() + strict_direction[1], true);
                        }
                    }
                }
            }
            this.delayTimer.reset();
        }
    }
    
    private void spoof(final double x, final double y, final double z, final boolean ground) {
        if (this.rotation.isEnabled()) {
            FastProjectile.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2830(x, y, z, FastProjectile.mc.field_1724.method_36454(), FastProjectile.mc.field_1724.method_36455(), ground));
        }
        else {
            FastProjectile.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(x, y, z, ground));
        }
    }
    
    private double[] getDirectionSpeed() {
        return new double[] { 100.0 * -Math.sin(Math.toRadians(FastProjectile.mc.field_1724.method_36454())), 100.0 * Math.cos(Math.toRadians(FastProjectile.mc.field_1724.method_36454())) };
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
    
    static {
        FastProjectile.bows = new BooleanSetting("bows", true);
        FastProjectile.pearls = new BooleanSetting("pearls", true);
        FastProjectile.xps = new BooleanSetting("xps", true);
        FastProjectile.eggs = new BooleanSetting("eggs", true);
        FastProjectile.snowballs = new BooleanSetting("snowballs", true);
        FastProjectile.potions = new BooleanSetting("potions", true);
        FastProjectile.trident = new BooleanSetting("trident", true);
    }
}
