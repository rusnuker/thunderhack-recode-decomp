/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_3675
 *  net.minecraft.class_408
 */
package com.mrzak34.thunderhack.module.modules.movement;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.RenderWorldEvent;
import com.mrzak34.thunderhack.mixin.IMinecraftClient;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.NumberSetting;
import net.minecraft.class_3675;
import net.minecraft.class_408;

public class InventoryMove
extends Module {
    NumberSetting rotationSpeed = new NumberSetting("rotation speed", 5.0f, 1.0f, 50.0f, false);
    private static InventoryMove INSTANCE = new InventoryMove();

    public InventoryMove() {
        super("InventoryMove", 0, false, Category.MOVEMENT);
        this.addSettings(this.rotationSpeed);
        this.setInstance();
    }

    @Subscribe
    public void onRenderWorld(RenderWorldEvent event) {
        if (InventoryMove.mc.field_1755 == null || InventoryMove.mc.field_1755 instanceof class_408) {
            return;
        }
        float speed = (float)(this.rotationSpeed.getValue() / (double)IMinecraftClient.getCurrentFps() * 10.0);
        long h = mc.method_22683().method_4490();
        InventoryMove.mc.field_1724.method_36456(InventoryMove.mc.field_1724.method_36454() + (class_3675.method_15987((long)h, (int)262) ? speed : (class_3675.method_15987((long)h, (int)263) ? -speed : 0.0f)));
        InventoryMove.mc.field_1724.method_36457(InventoryMove.mc.field_1724.method_36455() + (class_3675.method_15987((long)h, (int)265) ? (InventoryMove.mc.field_1724.method_36455() - speed >= -90.0f ? -speed : 0.0f) : (class_3675.method_15987((long)h, (int)264) ? (InventoryMove.mc.field_1724.method_36455() + speed <= 90.0f ? speed : 0.0f) : 0.0f)));
    }

    public static InventoryMove getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InventoryMove();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

