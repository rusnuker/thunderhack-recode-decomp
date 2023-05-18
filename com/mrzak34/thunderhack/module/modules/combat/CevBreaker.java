//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.util.*;
import net.minecraft.*;
import com.mrzak34.thunderhack.mixin.*;

public class CevBreaker extends Module
{
    NumberSetting breakRange;
    BooleanSetting extraPositions;
    NumberSetting progress;
    public static class_2338 placePos;
    public static class_1657 target;
    
    public CevBreaker() {
        super("CevBreaker", 0, false, Category.COMBAT);
        this.breakRange = new NumberSetting("break range", 5.0f, 0.0f, 10.0f, true);
        this.extraPositions = new BooleanSetting("extra pos", true);
        this.progress = new NumberSetting("progress", 95.0f, 0.0f, 100.0f, false);
        this.addSettings(new Setting[] { this.breakRange, this.extraPositions, this.progress });
    }
    
    public void onTick() {
        super.onTick();
        CevBreaker.target = TargetUtil.getTarget(8.0);
        if (CevBreaker.target == null) {
            return;
        }
        CevBreaker.placePos = new class_2338((class_2382)CevBreaker.target.method_24515().method_10086(2));
        if (CevBreaker.placePos == null) {
            return;
        }
        if (CevBreaker.mc.field_1687.method_8320(CevBreaker.placePos).method_26215() && CrystalUtil.getCrystalUnderHere(CevBreaker.placePos) != null) {
            CevBreaker.mc.method_1562().method_2883((class_2596)class_2824.method_34206((class_1297)CrystalUtil.getCrystalUnderHere(CevBreaker.placePos), CevBreaker.mc.field_1724.method_5715()));
            CevBreaker.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
        }
        else if (CevBreaker.mc.field_1687.method_8320(CevBreaker.placePos).method_26204() == class_2246.field_10540 && CrystalUtil.getCrystalUnderHere(CevBreaker.placePos) == null) {
            final int preSwapSlot = CevBreaker.mc.field_1724.method_31548().field_7545;
            InventoryUtil.selectSlot(InventoryUtil.findHotbarItem(class_1802.field_8301));
            CevBreaker.mc.field_1724.field_3944.method_2883((class_2596)new class_2885(class_1268.field_5808, new class_3965(BlockUtil.closestVec3d(CevBreaker.placePos), class_2350.field_11036, CevBreaker.placePos, true), 0));
            InventoryUtil.selectSlot(preSwapSlot);
        }
        else if (CevBreaker.mc.field_1687.method_8320(CevBreaker.placePos).method_26204() == class_2246.field_10540 && CrystalUtil.getCrystalUnderHere(CevBreaker.placePos) != null) {
            final int preSwapSlot = CevBreaker.mc.field_1724.method_31548().field_7545;
            InventoryUtil.selectSlot(InventoryUtil.findHotbarItem(class_1802.field_22024));
            if (((IClientPlayerInteractionManager)CevBreaker.mc.field_1761).getBreakingProgress() >= this.progress.getValue()) {
                CevBreaker.mc.field_1761.method_2896(CevBreaker.mc.field_1724, class_1268.field_5808, new class_3965(BlockUtil.closestVec3d(CevBreaker.placePos), class_2350.field_11036, CevBreaker.placePos, true));
                CevBreaker.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
            }
            InventoryUtil.selectSlot(InventoryUtil.findHotbarItem(class_1802.field_22024));
            CevBreaker.mc.field_1761.method_2902(CevBreaker.placePos, class_2350.field_11036);
            CevBreaker.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
            InventoryUtil.selectSlot(preSwapSlot);
        }
        else if (CevBreaker.mc.field_1687.method_8320(CevBreaker.placePos).method_26215() && CrystalUtil.getCrystalUnderHere(CevBreaker.placePos) == null) {
            final int preSwapSlot = CevBreaker.mc.field_1724.method_31548().field_7545;
            InventoryUtil.selectSlot(InventoryUtil.findHotbarItem(class_1802.field_8281));
            BlockUtil.placeBlock(CevBreaker.placePos);
            InventoryUtil.selectSlot(preSwapSlot);
        }
    }
}
