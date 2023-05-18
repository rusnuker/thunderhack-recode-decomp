/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1511
 *  net.minecraft.class_1657
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_238
 *  net.minecraft.class_2382
 *  net.minecraft.class_2596
 *  net.minecraft.class_2828$class_2829
 *  net.minecraft.class_2848
 *  net.minecraft.class_2848$class_2849
 */
package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.util.BlockUtil;
import com.mrzak34.thunderhack.util.InventoryUtil;
import java.util.Arrays;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_238;
import net.minecraft.class_2382;
import net.minecraft.class_2596;
import net.minecraft.class_2828;
import net.minecraft.class_2848;

public class Burrow
extends Module {
    ModeSetting mode = new ModeSetting("mode", "obi", "obi", "anvil");
    NumberSetting height = new NumberSetting("height", 3.0f, -10.0f, 10.0f, false);
    private final List<Double> offsets = Arrays.asList(0.4199999, 0.7531999, 1.0013359, 1.1661092);
    private class_2338 startPos;
    int tick;
    boolean fill;

    public Burrow() {
        super("Burrow", 0, false, Category.COMBAT);
        this.addSettings(this.mode, this.height);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.fill = true;
    }

    @Override
    public void onTick() {
        int startSlot;
        super.onTick();
        if (this.mode.getMode().equals("obi")) {
            startSlot = Burrow.mc.field_1724.method_31548().field_7545;
            this.startPos = new class_2338((class_2382)Burrow.mc.field_1724.method_24515());
            if (InventoryUtil.findHotbarBlock(class_2246.field_10443, class_2246.field_10540, class_2246.field_10034) == -1) {
                this.setToggled(false);
                this.tick = 0;
                return;
            }
            if (!this.check()) {
                return;
            }
            ++this.tick;
            if (this.fill) {
                InventoryUtil.switchToHotbarSlot(InventoryUtil.findHotbarBlock(class_2246.field_10443, class_2246.field_10540, class_2246.field_10034), false);
                this.offsets.forEach(offset -> Burrow.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(Burrow.mc.field_1724.method_23317(), Burrow.mc.field_1724.method_23318() + offset, Burrow.mc.field_1724.method_23321(), true)));
                Burrow.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)Burrow.mc.field_1724, class_2848.class_2849.field_12979));
                BlockUtil.placeBlock(this.startPos);
                Burrow.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)Burrow.mc.field_1724, class_2848.class_2849.field_12984));
                Burrow.mc.field_1724.field_3944.method_2883((class_2596)new class_2828.class_2829(Burrow.mc.field_1724.method_23317(), Burrow.mc.field_1724.method_23318() + this.height.getValue(), Burrow.mc.field_1724.method_23321(), false));
                InventoryUtil.switchToHotbarSlot(startSlot, false);
                this.fill = false;
            }
            if (this.tick >= 8) {
                this.tick = 0;
                this.setToggled(false);
            }
        }
        if (this.mode.getMode().equals("anvil")) {
            startSlot = Burrow.mc.field_1724.method_31548().field_7545;
            this.startPos = new class_2338((class_2382)Burrow.mc.field_1724.method_24515());
            if (Burrow.mc.field_1687.method_8320(this.startPos.method_10086(2)).method_26215() && this.isNoEntitiesToPos(this.startPos.method_10086(2))) {
                int anvilSlot = InventoryUtil.findHotbarItem(class_1802.field_8782);
                if (anvilSlot != -1) {
                    InventoryUtil.switchToHotbarSlot(anvilSlot, false);
                    BlockUtil.placeBlock(this.startPos.method_10086(2));
                    InventoryUtil.switchToHotbarSlot(startSlot, false);
                }
            } else if (!Burrow.mc.field_1687.method_8320(this.startPos.method_10086(2)).method_26215()) {
                this.setToggled(false);
            }
        }
    }

    private boolean isNoEntitiesToPos(class_2338 pos) {
        for (class_1297 entity : Burrow.mc.field_1687.method_8335(null, new class_238((double)pos.method_10263(), (double)(pos.method_10264() + 1), (double)pos.method_10260(), (double)(pos.method_10263() + 1), (double)(pos.method_10264() + 2), (double)(pos.method_10260() + 1)))) {
            if (!(entity instanceof class_1511) && !(entity instanceof class_1657)) continue;
            return false;
        }
        return true;
    }

    private boolean check() {
        return Burrow.mc.field_1724.method_24828() && Burrow.mc.field_1687.method_8320(this.startPos).method_26204() == class_2246.field_10124 && Burrow.mc.field_1687.method_8320(this.startPos.method_10069(0, 3, 0)).method_26204() == class_2246.field_10124;
    }
}

