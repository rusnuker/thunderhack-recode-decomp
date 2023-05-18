/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1309
 */
package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.manager.NotificationManager;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.module.modules.combat.HitAura;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.util.MoveUtil;
import net.minecraft.class_1309;

public class TickBase
extends Module {
    private final NumberSetting rangeValue = new NumberSetting("range", 3.0f, 1.0f, 8.0f, true);
    private int skippedTick;
    private int preTick;
    private boolean flag;

    public TickBase() {
        super("TickBase", 0, false, Category.COMBAT);
        this.addSettings(this.rangeValue);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        Main.TICK_TIMER = 1.0f;
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Main.TICK_TIMER = 1.0f;
    }

    @Override
    public void onTick() {
        super.onTick();
        if (!MoveUtil.isMoving() || HitAura.target == null) {
            Main.TICK_TIMER = 1.0f;
        }
        if (this.flag) {
            return;
        }
        if (HitAura.target == null) {
            this.sleep();
        } else if (this.shouldSkip()) {
            this.flag = true;
            for (int i = 0; i < this.preTick; ++i) {
                try {
                    mc.method_1574();
                    continue;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.flag = false;
        } else {
            this.sleep();
        }
    }

    private void sleep() {
        if (this.skippedTick > 0) {
            try {
                NotificationManager.notif("sleep:" + 2 * this.skippedTick);
                Thread.sleep(2 * this.skippedTick);
                this.skippedTick = 0;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            Main.TICK_TIMER = 0.055f + (float)this.skippedTick;
        }
    }

    public boolean shouldSkip() {
        double dz;
        class_1309 target = HitAura.target;
        if (target == null || this.skippedTick > 5 || !TickBase.mc.field_1724.method_5624()) {
            return false;
        }
        double dx = TickBase.mc.field_1724.method_23317() - target.method_23317();
        if (Math.sqrt(dx * dx + (dz = TickBase.mc.field_1724.method_23321() - target.method_23321()) * dz) > this.rangeValue.getValue()) {
            this.preTick = (int)(2.0 * (Math.sqrt(dx * dx + dz * dz) - this.rangeValue.getValue()));
            this.skippedTick += this.preTick;
            return true;
        }
        return false;
    }
}

