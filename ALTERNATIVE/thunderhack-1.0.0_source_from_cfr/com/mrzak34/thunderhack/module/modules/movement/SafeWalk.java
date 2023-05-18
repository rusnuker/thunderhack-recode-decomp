/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 */
package com.mrzak34.thunderhack.module.modules.movement;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.ClipAtLedgeEvent;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.Setting;

public class SafeWalk
extends Module {
    public SafeWalk() {
        super("SafeWalk", 0, false, Category.MOVEMENT);
        this.addSettings(new Setting[0]);
    }

    @Subscribe
    private void onClipAtLedge(ClipAtLedgeEvent event) {
        if (!SafeWalk.mc.field_1724.method_5715()) {
            event.setClip(true);
        }
    }
}

