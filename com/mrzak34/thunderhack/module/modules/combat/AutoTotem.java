//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import net.minecraft.*;

public class AutoTotem extends Module
{
    BooleanSetting override;
    NumberSetting delayValue;
    NumberSetting popDelay;
    private int delay;
    private boolean holdingTotem;
    
    public AutoTotem() {
        super("AutoTotem", 0, false, Category.COMBAT);
        this.override = new BooleanSetting("override", false);
        this.delayValue = new NumberSetting("delay", 0.0f, 0.0f, 10.0f, false);
        this.popDelay = new NumberSetting("pop delay", 0.0f, 0.0f, 10.0f, false);
        this.addSettings(new Setting[] { this.override, this.delayValue, this.popDelay });
    }
    
    public void onTick() {
        super.onTick();
        if (this.holdingTotem && AutoTotem.mc.field_1724.method_6079().method_7909() != class_1802.field_8288) {
            this.delay = (int)Math.max(this.popDelay.getValue(), this.delay);
        }
        this.holdingTotem = (AutoTotem.mc.field_1724.method_6079().method_7909() == class_1802.field_8288);
        if (this.delay > 0) {
            --this.delay;
            return;
        }
        if (this.holdingTotem || (!AutoTotem.mc.field_1724.method_6079().method_7960() && !this.override.isEnabled())) {
            return;
        }
        if (AutoTotem.mc.field_1724.field_7498 == AutoTotem.mc.field_1724.field_7512) {
            for (int i = 9; i < 45; ++i) {
                if (AutoTotem.mc.field_1724.method_31548().method_5438((i >= 36) ? (i - 36) : i).method_7909() == class_1802.field_8288) {
                    final boolean itemInOffhand = !AutoTotem.mc.field_1724.method_6079().method_7960();
                    AutoTotem.mc.field_1761.method_2906(AutoTotem.mc.field_1724.field_7512.field_7763, i, 0, class_1713.field_7790, (class_1657)AutoTotem.mc.field_1724);
                    AutoTotem.mc.field_1761.method_2906(AutoTotem.mc.field_1724.field_7512.field_7763, 45, 0, class_1713.field_7790, (class_1657)AutoTotem.mc.field_1724);
                    if (itemInOffhand) {
                        AutoTotem.mc.field_1761.method_2906(AutoTotem.mc.field_1724.field_7512.field_7763, i, 0, class_1713.field_7790, (class_1657)AutoTotem.mc.field_1724);
                    }
                    this.delay = (int)this.delayValue.getValue();
                    return;
                }
            }
        }
        else {
            for (int i = 0; i < 9; ++i) {
                if (AutoTotem.mc.field_1724.method_31548().method_5438(i).method_7909() == class_1802.field_8288) {
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
}
