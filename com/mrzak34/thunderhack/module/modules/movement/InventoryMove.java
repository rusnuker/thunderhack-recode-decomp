//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.movement;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import com.mrzak34.thunderhack.mixin.*;
import net.minecraft.*;
import com.google.common.eventbus.*;

public class InventoryMove extends Module
{
    NumberSetting rotationSpeed;
    private static InventoryMove INSTANCE;
    
    public InventoryMove() {
        super("InventoryMove", 0, false, Category.MOVEMENT);
        this.rotationSpeed = new NumberSetting("rotation speed", 5.0f, 1.0f, 50.0f, false);
        this.addSettings(new Setting[] { this.rotationSpeed });
        this.setInstance();
    }
    
    @Subscribe
    public void onRenderWorld(final RenderWorldEvent event) {
        if (InventoryMove.mc.field_1755 == null || InventoryMove.mc.field_1755 instanceof class_408) {
            return;
        }
        final float speed = (float)(this.rotationSpeed.getValue() / IMinecraftClient.getCurrentFps() * 10.0);
        final long h = InventoryMove.mc.method_22683().method_4490();
        InventoryMove.mc.field_1724.method_36456(InventoryMove.mc.field_1724.method_36454() + (class_3675.method_15987(h, 262) ? speed : (class_3675.method_15987(h, 263) ? (-speed) : 0.0f)));
        InventoryMove.mc.field_1724.method_36457(InventoryMove.mc.field_1724.method_36455() + (class_3675.method_15987(h, 265) ? ((InventoryMove.mc.field_1724.method_36455() - speed >= -90.0f) ? (-speed) : 0.0f) : (class_3675.method_15987(h, 264) ? ((InventoryMove.mc.field_1724.method_36455() + speed <= 90.0f) ? speed : 0.0f) : 0.0f)));
    }
    
    public static InventoryMove getInstance() {
        if (InventoryMove.INSTANCE == null) {
            InventoryMove.INSTANCE = new InventoryMove();
        }
        return InventoryMove.INSTANCE;
    }
    
    private void setInstance() {
        InventoryMove.INSTANCE = this;
    }
    
    static {
        InventoryMove.INSTANCE = new InventoryMove();
    }
}
