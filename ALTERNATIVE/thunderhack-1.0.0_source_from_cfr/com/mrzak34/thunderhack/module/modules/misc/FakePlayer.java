/*
 * Decompiled with CFR 0.150.
 */
package com.mrzak34.thunderhack.module.modules.misc;

import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.util.FakePlayerUtil;

public class FakePlayer
extends Module {
    NumberSetting health = new NumberSetting("health", 20.0f, 1.0f, 36.0f, true);
    BooleanSetting copyInv = new BooleanSetting("copy inv", true);

    public FakePlayer() {
        super("FakePlayer", 0, false, Category.MISC);
        this.addSettings(this.health, this.copyInv);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        FakePlayerUtil.add("Putin", (float)this.health.getValue(), this.copyInv.isEnabled());
    }

    @Override
    public void onDisable() {
        super.onDisable();
        FakePlayerUtil.clear();
    }
}

