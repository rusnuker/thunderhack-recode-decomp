/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1297
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2829
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 */
package com.mrzak34.thunderhack.module.modules.movement;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.MoveEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.util.TimerUtil;
import net.minecraft.class_1297;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_2848;

public class LongJump
extends Module {
    public static NumberSetting spoofs = new NumberSetting("spoofs", 10.0f, 1.0f, 300.0f, false);
    public static BooleanSetting bypass = new BooleanSetting("bypass", false);
    TimerUtil jumpTimer = new TimerUtil();

    public LongJump() {
        super("LongJump", 0, false, Category.MOVEMENT);
        this.addSettings(spoofs, bypass);
    }

    @Subscribe
    public void onMovePre(MoveEvent event) {
        if (LongJump.mc.field_1724.method_24828() && this.jumpTimer.passedMs(50L)) {
            float f = LongJump.mc.field_1724.field_3913.field_3905;
            float f2 = LongJump.mc.field_1724.field_3913.field_3907;
            float f3 = LongJump.mc.field_1724.field_6241;
            if (f == 0.0f && f2 == 0.0f) {
                event.setX(0.0);
                event.setZ(0.0);
            } else if (f != 0.0f) {
                if (f2 >= 1.0f) {
                    f3 += (float)(f > 0.0f ? -45 : 45);
                    f2 = 0.0f;
                } else if (f2 <= -1.0f) {
                    f3 += (float)(f > 0.0f ? 45 : -45);
                    f2 = 0.0f;
                }
                if (f > 0.0f) {
                    f = 1.0f;
                } else if (f < 0.0f) {
                    f = -1.0f;
                }
            }
            double d = Math.cos(Math.toRadians(f3 + 90.0f));
            double d2 = Math.sin(Math.toRadians(f3 + 90.0f));
            LongJump.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)LongJump.mc.field_1724, class_2848.class_2849.field_12981));
            LongJump.mc.field_1724.method_18800((double)f * 0.2873 * d + (double)f2 * 0.2873 * d2, 0.4, (double)f * 0.2873 * d2 - (double)f2 * 0.2873 * d);
            LongJump.doSpoofs();
            this.setToggled(false);
        }
    }

    private static void doSpoofs() {
        LongJump.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)LongJump.mc.field_1724, class_2848.class_2849.field_12981));
        int index = 0;
        while ((double)index < spoofs.getValue()) {
            if (bypass.isEnabled()) {
                LongJump.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(LongJump.mc.field_1724.method_23317(), LongJump.mc.field_1724.method_23318() + 1.0E-10, LongJump.mc.field_1724.method_23321(), false));
                LongJump.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(LongJump.mc.field_1724.method_23317(), LongJump.mc.field_1724.method_23318() - 1.0E-10, LongJump.mc.field_1724.method_23321(), true));
            } else {
                LongJump.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(LongJump.mc.field_1724.method_23317(), LongJump.mc.field_1724.method_23318() - 1.0E-10, LongJump.mc.field_1724.method_23321(), true));
                LongJump.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(LongJump.mc.field_1724.method_23317(), LongJump.mc.field_1724.method_23318() + 1.0E-10, LongJump.mc.field_1724.method_23321(), false));
            }
            ++index;
        }
    }
}

