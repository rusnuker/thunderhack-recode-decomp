/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_1802
 */
package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import net.minecraft.class_1657;
import net.minecraft.class_1802;

public class BowSpam
extends Module {
    NumberSetting charge = new NumberSetting("charge", 5.0f, 3.0f, 20.0f, false);
    BooleanSetting whenRightClick = new BooleanSetting("when right click", true);
    boolean wasHoldingRightClick;

    public BowSpam() {
        super("BowSpam", 0, false, Category.COMBAT);
        this.addSettings(this.charge, this.whenRightClick);
    }

    @Override
    public void onTick() {
        super.onTick();
        if (BowSpam.mc.field_1724.method_6047().method_7909() != class_1802.field_8102) {
            return;
        }
        if (!this.whenRightClick.isEnabled() || BowSpam.mc.field_1690.field_1904.method_1434()) {
            boolean isBow;
            boolean bl = isBow = BowSpam.mc.field_1724.method_6047().method_7909() == class_1802.field_8102;
            if (!isBow) {
                this.setPressed(false);
            }
            if (!isBow) {
                return;
            }
            if ((double)BowSpam.mc.field_1724.method_6048() >= this.charge.getValue()) {
                BowSpam.mc.field_1724.method_6075();
                BowSpam.mc.field_1761.method_2897((class_1657)BowSpam.mc.field_1724);
            } else {
                this.setPressed(true);
            }
            this.wasHoldingRightClick = BowSpam.mc.field_1690.field_1904.method_1434();
        } else if (this.wasHoldingRightClick) {
            this.setPressed(false);
            this.wasHoldingRightClick = false;
        }
    }

    private void setPressed(boolean pressed) {
        BowSpam.mc.field_1690.field_1904.method_23481(pressed);
    }
}

