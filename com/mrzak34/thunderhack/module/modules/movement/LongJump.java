//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.movement;

import com.mrzak34.thunderhack.util.*;
import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import com.google.common.eventbus.*;
import net.minecraft.*;

public class LongJump extends Module
{
    public static NumberSetting spoofs;
    public static BooleanSetting bypass;
    TimerUtil jumpTimer;
    
    public LongJump() {
        super("LongJump", 0, false, Category.MOVEMENT);
        this.jumpTimer = new TimerUtil();
        this.addSettings(new Setting[] { LongJump.spoofs, LongJump.bypass });
    }
    
    @Subscribe
    public void onMovePre(final MoveEvent event) {
        if (LongJump.mc.field_1724.method_24828() && this.jumpTimer.passedMs(50L)) {
            float f = LongJump.mc.field_1724.field_3913.field_3905;
            float f2 = LongJump.mc.field_1724.field_3913.field_3907;
            float f3 = LongJump.mc.field_1724.field_6241;
            if (f == 0.0f && f2 == 0.0f) {
                event.setX(0.0);
                event.setZ(0.0);
            }
            else if (f != 0.0f) {
                if (f2 >= 1.0f) {
                    f3 += ((f > 0.0f) ? -45 : 45);
                    f2 = 0.0f;
                }
                else if (f2 <= -1.0f) {
                    f3 += ((f > 0.0f) ? 45 : -45);
                    f2 = 0.0f;
                }
                if (f > 0.0f) {
                    f = 1.0f;
                }
                else if (f < 0.0f) {
                    f = -1.0f;
                }
            }
            final double d = Math.cos(Math.toRadians(f3 + 90.0f));
            final double d2 = Math.sin(Math.toRadians(f3 + 90.0f));
            LongJump.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)LongJump.mc.field_1724, class_2848.class_2849.field_12981));
            LongJump.mc.field_1724.method_18800(f * 0.2873 * d + f2 * 0.2873 * d2, 0.4, f * 0.2873 * d2 - f2 * 0.2873 * d);
            doSpoofs();
            this.setToggled(false);
        }
    }
    
    private static void doSpoofs() {
        LongJump.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)LongJump.mc.field_1724, class_2848.class_2849.field_12981));
        for (int index = 0; index < LongJump.spoofs.getValue(); ++index) {
            if (LongJump.bypass.isEnabled()) {
                LongJump.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(LongJump.mc.field_1724.method_23317(), LongJump.mc.field_1724.method_23318() + 1.0E-10, LongJump.mc.field_1724.method_23321(), false));
                LongJump.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(LongJump.mc.field_1724.method_23317(), LongJump.mc.field_1724.method_23318() - 1.0E-10, LongJump.mc.field_1724.method_23321(), true));
            }
            else {
                LongJump.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(LongJump.mc.field_1724.method_23317(), LongJump.mc.field_1724.method_23318() - 1.0E-10, LongJump.mc.field_1724.method_23321(), true));
                LongJump.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(LongJump.mc.field_1724.method_23317(), LongJump.mc.field_1724.method_23318() + 1.0E-10, LongJump.mc.field_1724.method_23321(), false));
            }
        }
    }
    
    static {
        LongJump.spoofs = new NumberSetting("spoofs", 10.0f, 1.0f, 300.0f, false);
        LongJump.bypass = new BooleanSetting("bypass", false);
    }
}
