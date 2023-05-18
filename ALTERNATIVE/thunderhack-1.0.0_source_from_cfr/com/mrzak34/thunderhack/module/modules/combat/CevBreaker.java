/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_1802
 *  net.minecraft.class_2246
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2382
 *  net.minecraft.class_2596
 *  net.minecraft.class_2824
 *  net.minecraft.class_2879
 *  net.minecraft.class_2885
 *  net.minecraft.class_3965
 */
package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.mixin.IClientPlayerInteractionManager;
import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.util.BlockUtil;
import com.mrzak34.thunderhack.util.CrystalUtil;
import com.mrzak34.thunderhack.util.InventoryUtil;
import com.mrzak34.thunderhack.util.TargetUtil;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_2596;
import net.minecraft.class_2824;
import net.minecraft.class_2879;
import net.minecraft.class_2885;
import net.minecraft.class_3965;

public class CevBreaker
extends Module {
    NumberSetting breakRange = new NumberSetting("break range", 5.0f, 0.0f, 10.0f, true);
    BooleanSetting extraPositions = new BooleanSetting("extra pos", true);
    NumberSetting progress = new NumberSetting("progress", 95.0f, 0.0f, 100.0f, false);
    public static class_2338 placePos;
    public static class_1657 target;

    public CevBreaker() {
        super("CevBreaker", 0, false, Category.COMBAT);
        this.addSettings(this.breakRange, this.extraPositions, this.progress);
    }

    @Override
    public void onTick() {
        super.onTick();
        target = TargetUtil.getTarget(8.0);
        if (target == null) {
            return;
        }
        placePos = new class_2338((class_2382)target.method_24515().method_10086(2));
        if (placePos == null) {
            return;
        }
        if (CevBreaker.mc.field_1687.method_8320(placePos).method_26215() && CrystalUtil.getCrystalUnderHere(placePos) != null) {
            mc.method_1562().method_2883((class_2596)class_2824.method_34206((class_1297)CrystalUtil.getCrystalUnderHere(placePos), (boolean)CevBreaker.mc.field_1724.method_5715()));
            CevBreaker.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
        } else if (CevBreaker.mc.field_1687.method_8320(placePos).method_26204() == class_2246.field_10540 && CrystalUtil.getCrystalUnderHere(placePos) == null) {
            int preSwapSlot = CevBreaker.mc.field_1724.method_31548().field_7545;
            InventoryUtil.selectSlot(InventoryUtil.findHotbarItem(class_1802.field_8301));
            CevBreaker.mc.field_1724.field_3944.method_2883((class_2596)new class_2885(class_1268.field_5808, new class_3965(BlockUtil.closestVec3d(placePos), class_2350.field_11036, placePos, true), 0));
            InventoryUtil.selectSlot(preSwapSlot);
        } else if (CevBreaker.mc.field_1687.method_8320(placePos).method_26204() == class_2246.field_10540 && CrystalUtil.getCrystalUnderHere(placePos) != null) {
            int preSwapSlot = CevBreaker.mc.field_1724.method_31548().field_7545;
            InventoryUtil.selectSlot(InventoryUtil.findHotbarItem(class_1802.field_22024));
            if ((double)((IClientPlayerInteractionManager)CevBreaker.mc.field_1761).getBreakingProgress() >= this.progress.getValue()) {
                CevBreaker.mc.field_1761.method_2896(CevBreaker.mc.field_1724, class_1268.field_5808, new class_3965(BlockUtil.closestVec3d(placePos), class_2350.field_11036, placePos, true));
                CevBreaker.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
            }
            InventoryUtil.selectSlot(InventoryUtil.findHotbarItem(class_1802.field_22024));
            CevBreaker.mc.field_1761.method_2902(placePos, class_2350.field_11036);
            CevBreaker.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
            InventoryUtil.selectSlot(preSwapSlot);
        } else if (CevBreaker.mc.field_1687.method_8320(placePos).method_26215() && CrystalUtil.getCrystalUnderHere(placePos) == null) {
            int preSwapSlot = CevBreaker.mc.field_1724.method_31548().field_7545;
            InventoryUtil.selectSlot(InventoryUtil.findHotbarItem(class_1802.field_8281));
            BlockUtil.placeBlock(placePos);
            InventoryUtil.selectSlot(preSwapSlot);
        }
    }
}

