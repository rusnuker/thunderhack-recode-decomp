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

public class TridentSpam
extends Module {
    NumberSetting charge = new NumberSetting("charge", 10.0f, 10.0f, 20.0f, false);
    BooleanSetting whenRightClick = new BooleanSetting("when right click", true);
    boolean wasHoldingRightClick;

    public TridentSpam() {
        super("TridentSpam", 0, false, Category.COMBAT);
        this.addSettings(this.charge, this.whenRightClick);
    }

    @Override
    public void onTick() {
        super.onTick();
        if (TridentSpam.mc.field_1724.method_6047().method_7909() != class_1802.field_8547) {
            return;
        }
        if (!this.whenRightClick.isEnabled() || TridentSpam.mc.field_1690.field_1904.method_1434()) {
            boolean isTrident;
            boolean bl = isTrident = TridentSpam.mc.field_1724.method_6047().method_7909() == class_1802.field_8547;
            if (!isTrident) {
                this.setPressed(false);
            }
            if (!isTrident) {
                return;
            }
            if ((double)TridentSpam.mc.field_1724.method_6048() >= this.charge.getValue()) {
                TridentSpam.mc.field_1724.method_6075();
                TridentSpam.mc.field_1761.method_2897((class_1657)TridentSpam.mc.field_1724);
            } else {
                this.setPressed(true);
            }
            this.wasHoldingRightClick = TridentSpam.mc.field_1690.field_1904.method_1434();
        } else if (this.wasHoldingRightClick) {
            this.setPressed(false);
            this.wasHoldingRightClick = false;
        }
    }

    private void setPressed(boolean pressed) {
        TridentSpam.mc.field_1690.field_1904.method_23481(pressed);
    }
}

