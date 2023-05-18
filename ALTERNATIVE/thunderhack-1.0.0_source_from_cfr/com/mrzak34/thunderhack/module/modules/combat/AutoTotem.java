/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1713
 *  net.minecraft.class_1802
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2596
 *  net.minecraft.class_2846
 *  net.minecraft.class_2846$class_2847
 *  net.minecraft.class_2868
 */
package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2596;
import net.minecraft.class_2846;
import net.minecraft.class_2868;

public class AutoTotem
extends Module {
    BooleanSetting override = new BooleanSetting("override", false);
    NumberSetting delayValue = new NumberSetting("delay", 0.0f, 0.0f, 10.0f, false);
    NumberSetting popDelay = new NumberSetting("pop delay", 0.0f, 0.0f, 10.0f, false);
    private int delay;
    private boolean holdingTotem;

    public AutoTotem() {
        super("AutoTotem", 0, false, Category.COMBAT);
        this.addSettings(this.override, this.delayValue, this.popDelay);
    }

    @Override
    public void onTick() {
        super.onTick();
        if (this.holdingTotem && AutoTotem.mc.field_1724.method_6079().method_7909() != class_1802.field_8288) {
            this.delay = (int)Math.max(this.popDelay.getValue(), (double)this.delay);
        }
        boolean bl = this.holdingTotem = AutoTotem.mc.field_1724.method_6079().method_7909() == class_1802.field_8288;
        if (this.delay > 0) {
            --this.delay;
            return;
        }
        if (this.holdingTotem || !AutoTotem.mc.field_1724.method_6079().method_7960() && !this.override.isEnabled()) {
            return;
        }
        if (AutoTotem.mc.field_1724.field_7498 == AutoTotem.mc.field_1724.field_7512) {
            for (int i = 9; i < 45; ++i) {
                if (AutoTotem.mc.field_1724.method_31548().method_5438(i >= 36 ? i - 36 : i).method_7909() != class_1802.field_8288) continue;
                boolean itemInOffhand = !AutoTotem.mc.field_1724.method_6079().method_7960();
                AutoTotem.mc.field_1761.method_2906(AutoTotem.mc.field_1724.field_7512.field_7763, i, 0, class_1713.field_7790, (class_1657)AutoTotem.mc.field_1724);
                AutoTotem.mc.field_1761.method_2906(AutoTotem.mc.field_1724.field_7512.field_7763, 45, 0, class_1713.field_7790, (class_1657)AutoTotem.mc.field_1724);
                if (itemInOffhand) {
                    AutoTotem.mc.field_1761.method_2906(AutoTotem.mc.field_1724.field_7512.field_7763, i, 0, class_1713.field_7790, (class_1657)AutoTotem.mc.field_1724);
                }
                this.delay = (int)this.delayValue.getValue();
                return;
            }
        } else {
            for (int i = 0; i < 9; ++i) {
                if (AutoTotem.mc.field_1724.method_31548().method_5438(i).method_7909() != class_1802.field_8288) continue;
                if (i != AutoTotem.mc.field_1724.method_31548().field_7545) {
                    AutoTotem.mc.field_1724.method_31548().field_7545 = i;
                    AutoTotem.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(i));
                }
                AutoTotem.mc.field_1724.field_3944.method_2883((class_2596)new class_2846(class_2846.class_2847.field_12969, class_2338.field_10980, class_2350.field_11033));
                this.delay = (int)this.delayValue.getValue();
                return;
            }
        }
    }
}

