/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 */
package com.mrzak34.thunderhack.module.modules.movement;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.MoveEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;

public class Flight
extends Module {
    ModeSetting mode = new ModeSetting("mode", "vanilla", "vanilla", "static");
    NumberSetting speed = new NumberSetting("speed", 1.0f, 0.0f, 10.0f, true);
    NumberSetting glide = new NumberSetting("glide", 0.5f, 0.0f, 3.0f, true);

    public Flight() {
        super("Flight", 0, false, Category.MOVEMENT);
        this.addSettings(this.mode, this.speed, this.glide);
    }

    @Subscribe
    public void onMovePost(MoveEvent event) {
        switch (this.mode.getMode()) {
            case "static": {
                Flight.mc.field_1724.method_18800(0.0, -(this.glide.getValue() / 10.0), 0.0);
                if (Flight.mc.field_1690.field_1903.method_1434()) {
                    Flight.mc.field_1724.method_5762(0.0, this.speed.getValue() / 2.0, 0.0);
                }
                if (!Flight.mc.field_1690.field_1832.method_1434()) break;
                Flight.mc.field_1724.method_5762(0.0, -(this.speed.getValue() / 2.0), 0.0);
                break;
            }
            case "vanilla": {
                if (this.glide.getValue() == 0.0 || Flight.mc.field_1690.field_1903.method_1434() || Flight.mc.field_1690.field_1832.method_1434()) break;
                Flight.mc.field_1724.method_5762(0.0, -this.glide.getValue() / 10.0, 0.0);
            }
        }
    }

    @Subscribe
    public void onMovePre(MoveEvent event) {
        switch (this.mode.getMode()) {
            case "static": {
                Flight.mc.field_1724.method_31549().field_7479 = true;
                Flight.mc.field_1724.method_31549().method_7248((float)this.speed.getValue());
                break;
            }
            case "vanilla": {
                Flight.mc.field_1724.method_31549().field_7479 = true;
                Flight.mc.field_1724.method_31549().method_7248((float)((double)((float)this.speed.getValue()) / 11.11));
            }
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Flight.mc.field_1724.method_31549().field_7479 = false;
        Flight.mc.field_1724.method_31549().method_7248(0.05f);
    }
}

