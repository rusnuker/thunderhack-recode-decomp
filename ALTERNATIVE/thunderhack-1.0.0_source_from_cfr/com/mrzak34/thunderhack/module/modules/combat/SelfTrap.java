/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1511
 *  net.minecraft.class_1657
 *  net.minecraft.class_1802
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_2596
 *  net.minecraft.class_2824
 *  net.minecraft.class_2879
 */
package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.Setting;
import com.mrzak34.thunderhack.util.BlockUtil;
import com.mrzak34.thunderhack.util.InventoryUtil;
import java.util.Iterator;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_2596;
import net.minecraft.class_2824;
import net.minecraft.class_2879;

public class SelfTrap
extends Module {
    private class_2338 startPos;

    public SelfTrap() {
        super("SelfTrap", 0, false, Category.COMBAT);
        this.addSettings(new Setting[0]);
    }

    @Override
    public void onTick() {
        super.onTick();
        int startSlot = SelfTrap.mc.field_1724.method_31548().field_7545;
        this.startPos = new class_2338((class_2382)SelfTrap.mc.field_1724.method_24515());
        if (SelfTrap.mc.field_1687.method_8320(this.startPos.method_10086(2)).method_26215()) {
            if (!(this.getEntitiesToPos(this.startPos.method_10086(2)) instanceof class_1511) && !(this.getEntitiesToPos(this.startPos.method_10086(2)) instanceof class_1657) || this.getEntitiesToPos(this.startPos.method_10086(2)) == null) {
                int obiSlot = InventoryUtil.findHotbarItem(class_1802.field_8281);
                if (obiSlot != -1) {
                    InventoryUtil.switchToHotbarSlot(obiSlot, false);
                    BlockUtil.placeBlock(this.startPos.method_10086(2));
                    InventoryUtil.switchToHotbarSlot(startSlot, false);
                }
            } else if (this.getEntitiesToPos(this.startPos.method_10086(2)) instanceof class_1511) {
                mc.method_1562().method_2883((class_2596)class_2824.method_34206((class_1297)this.getEntitiesToPos(this.startPos.method_10086(2)), (boolean)SelfTrap.mc.field_1724.method_5715()));
                SelfTrap.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
            } else if (this.getEntitiesToPos(this.startPos.method_10086(2)) instanceof class_1657) {
                this.setToggled(false);
            }
        } else {
            this.setToggled(false);
        }
    }

    private class_1297 getEntitiesToPos(class_2338 pos) {
        Iterator iterator = SelfTrap.mc.field_1687.method_8335(null, new class_238((double)pos.method_10263(), (double)(pos.method_10264() + 1), (double)pos.method_10260(), (double)(pos.method_10263() + 1), (double)(pos.method_10264() + 2), (double)(pos.method_10260() + 1))).iterator();
        if (iterator.hasNext()) {
            class_1297 entity = (class_1297)iterator.next();
            return entity;
        }
        return null;
    }
}

