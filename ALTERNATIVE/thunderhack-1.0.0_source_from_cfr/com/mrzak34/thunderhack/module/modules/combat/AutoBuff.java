/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.Subscribe
 *  net.minecraft.class_1268
 *  net.minecraft.class_1828
 *  net.minecraft.class_2596
 *  net.minecraft.class_2886
 */
package com.mrzak34.thunderhack.module.modules.combat;

import com.google.common.eventbus.Subscribe;
import com.mrzak34.thunderhack.event.events.PacketEvent;
import com.mrzak34.thunderhack.manager.NotificationManager;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.Setting;
import com.mrzak34.thunderhack.util.InventoryUtil;
import net.minecraft.class_1268;
import net.minecraft.class_1828;
import net.minecraft.class_2596;
import net.minecraft.class_2886;

public class AutoBuff
extends Module {
    public AutoBuff() {
        super("AutoBuff", 0, false, Category.COMBAT);
        this.addSettings(new Setting[0]);
    }

    @Subscribe
    public void PacketSend(PacketEvent.Send event) {
        if (event.getPacket() instanceof class_2886) {
            NotificationManager.notif("fall dist: " + AutoBuff.mc.field_1724.field_6017);
            NotificationManager.notif("velocity y: " + AutoBuff.mc.field_1724.method_18798().field_1351);
        }
    }

    @Override
    public void onTick() {
        super.onTick();
        if (AutoBuff.mc.field_1724.method_18798().field_1351 < 0.0 && (double)AutoBuff.mc.field_1724.field_6017 > -AutoBuff.mc.field_1724.method_18798().field_1351) {
            NotificationManager.notif("fall dist: " + AutoBuff.mc.field_1724.field_6017);
            NotificationManager.notif("velocity y: " + AutoBuff.mc.field_1724.method_18798().field_1351);
            if (AutoBuff.getPotionStack() != -1) {
                InventoryUtil.switchToHotbarSlot(AutoBuff.getPotionStack(), false);
                AutoBuff.mc.field_1724.field_3944.method_2883((class_2596)new class_2886(class_1268.field_5808, 0));
            }
        }
    }

    public static int getPotionStack() {
        for (int i = 0; i < 9; ++i) {
            if (!(AutoBuff.mc.field_1724.method_31548().method_5438(i).method_7909() instanceof class_1828)) continue;
            return i;
        }
        return -1;
    }
}

