/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1657
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2374
 *  net.minecraft.class_2596
 *  net.minecraft.class_2879
 *  net.minecraft.class_3965
 */
package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.mixin.IClientPlayerInteractionManager;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.util.BlockUtil;
import com.mrzak34.thunderhack.util.InventoryUtil;
import com.mrzak34.thunderhack.util.TargetUtil;
import net.minecraft.class_1268;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2374;
import net.minecraft.class_2596;
import net.minecraft.class_2879;
import net.minecraft.class_3965;

public class HoleBreaker
extends Module {
    NumberSetting breakRange = new NumberSetting("break range", 5.0f, 0.0f, 10.0f, true);
    NumberSetting progress = new NumberSetting("progress", 95.0f, 0.0f, 100.0f, false);
    public static class_1657 target;

    public HoleBreaker() {
        super("HoleBreaker", 0, false, Category.COMBAT);
        this.addSettings(this.breakRange, this.progress);
    }

    @Override
    public void onTick() {
        super.onTick();
        target = TargetUtil.getTarget(8.0);
        if (target == null) {
            return;
        }
        if (this.getBreakBlock(target) != null) {
            int preSwapSlot = HoleBreaker.mc.field_1724.method_31548().field_7545;
            InventoryUtil.selectSlot(InventoryUtil.findHotbarItem(class_1802.field_22024));
            if ((double)((IClientPlayerInteractionManager)HoleBreaker.mc.field_1761).getBreakingProgress() >= this.progress.getValue()) {
                HoleBreaker.mc.field_1761.method_2896(HoleBreaker.mc.field_1724, class_1268.field_5808, new class_3965(BlockUtil.closestVec3d(this.getBreakBlock(target)), class_2350.field_11036, this.getBreakBlock(target), true));
                HoleBreaker.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
            }
            InventoryUtil.selectSlot(InventoryUtil.findHotbarItem(class_1802.field_22024));
            HoleBreaker.mc.field_1761.method_2902(this.getBreakBlock(target), class_2350.field_11036);
            HoleBreaker.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
            InventoryUtil.selectSlot(preSwapSlot);
        }
    }

    public class_2338 getBreakBlock(class_1657 target) {
        if (HoleBreaker.mc.field_1687.method_8320(target.method_24515().method_10069(1, 0, 0)).method_26215()) {
            return null;
        }
        if (HoleBreaker.mc.field_1687.method_8320(target.method_24515().method_10069(-1, 0, 0)).method_26215()) {
            return null;
        }
        if (HoleBreaker.mc.field_1687.method_8320(target.method_24515().method_10069(0, 0, 1)).method_26215()) {
            return null;
        }
        if (HoleBreaker.mc.field_1687.method_8320(target.method_24515().method_10069(0, 0, -1)).method_26215()) {
            return null;
        }
        if (HoleBreaker.mc.field_1687.method_8320(target.method_24515().method_10069(1, 0, 0)).method_26204() == class_2246.field_10540 && Math.sqrt(target.method_24515().method_10069(1, 0, 0).method_19770((class_2374)HoleBreaker.mc.field_1724.method_19538())) < this.breakRange.getValue()) {
            return target.method_24515().method_10069(1, 0, 0);
        }
        if (HoleBreaker.mc.field_1687.method_8320(target.method_24515().method_10069(-1, 0, 0)).method_26204() == class_2246.field_10540 && Math.sqrt(target.method_24515().method_10069(-1, 0, 0).method_19770((class_2374)HoleBreaker.mc.field_1724.method_19538())) < this.breakRange.getValue()) {
            return target.method_24515().method_10069(-1, 0, 0);
        }
        if (HoleBreaker.mc.field_1687.method_8320(target.method_24515().method_10069(0, 0, 1)).method_26204() == class_2246.field_10540 && Math.sqrt(target.method_24515().method_10069(0, 0, 1).method_19770((class_2374)HoleBreaker.mc.field_1724.method_19538())) < this.breakRange.getValue()) {
            return target.method_24515().method_10069(0, 0, 1);
        }
        if (HoleBreaker.mc.field_1687.method_8320(target.method_24515().method_10069(0, 0, -1)).method_26204() == class_2246.field_10540 && Math.sqrt(target.method_24515().method_10069(0, 0, -1).method_19770((class_2374)HoleBreaker.mc.field_1724.method_19538())) < this.breakRange.getValue()) {
            return target.method_24515().method_10069(0, 0, -1);
        }
        return null;
    }
}

