//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import net.minecraft.*;

public class TridentSpam extends Module
{
    NumberSetting charge;
    BooleanSetting whenRightClick;
    boolean wasHoldingRightClick;
    
    public TridentSpam() {
        super("TridentSpam", 0, false, Category.COMBAT);
        this.charge = new NumberSetting("charge", 10.0f, 10.0f, 20.0f, false);
        this.whenRightClick = new BooleanSetting("when right click", true);
        this.addSettings(new Setting[] { this.charge, this.whenRightClick });
    }
    
    public void onTick() {
        super.onTick();
        if (TridentSpam.mc.field_1724.method_6047().method_7909() != class_1802.field_8547) {
            return;
        }
        if (!this.whenRightClick.isEnabled() || TridentSpam.mc.field_1690.field_1904.method_1434()) {
            final boolean isTrident = TridentSpam.mc.field_1724.method_6047().method_7909() == class_1802.field_8547;
            if (!isTrident) {
                this.setPressed(false);
            }
            if (!isTrident) {
                return;
            }
            if (TridentSpam.mc.field_1724.method_6048() >= this.charge.getValue()) {
                TridentSpam.mc.field_1724.method_6075();
                TridentSpam.mc.field_1761.method_2897((class_1657)TridentSpam.mc.field_1724);
            }
            else {
                this.setPressed(true);
            }
            this.wasHoldingRightClick = TridentSpam.mc.field_1690.field_1904.method_1434();
        }
        else if (this.wasHoldingRightClick) {
            this.setPressed(false);
            this.wasHoldingRightClick = false;
        }
    }
    
    private void setPressed(final boolean pressed) {
        TridentSpam.mc.field_1690.field_1904.method_23481(pressed);
    }
}
