//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.mixin.*;
import com.mrzak34.thunderhack.util.*;
import net.minecraft.*;

public class HoleBreaker extends Module
{
    NumberSetting breakRange;
    NumberSetting progress;
    public static class_1657 target;
    
    public HoleBreaker() {
        super("HoleBreaker", 0, false, Category.COMBAT);
        this.breakRange = new NumberSetting("break range", 5.0f, 0.0f, 10.0f, true);
        this.progress = new NumberSetting("progress", 95.0f, 0.0f, 100.0f, false);
        this.addSettings(new Setting[] { this.breakRange, this.progress });
    }
    
    public void onTick() {
        super.onTick();
        HoleBreaker.target = TargetUtil.getTarget(8.0);
        if (HoleBreaker.target == null) {
            return;
        }
        if (this.getBreakBlock(HoleBreaker.target) != null) {
            final int preSwapSlot = HoleBreaker.mc.field_1724.method_31548().field_7545;
            InventoryUtil.selectSlot(InventoryUtil.findHotbarItem(class_1802.field_22024));
            if (((IClientPlayerInteractionManager)HoleBreaker.mc.field_1761).getBreakingProgress() >= this.progress.getValue()) {
                HoleBreaker.mc.field_1761.method_2896(HoleBreaker.mc.field_1724, class_1268.field_5808, new class_3965(BlockUtil.closestVec3d(this.getBreakBlock(HoleBreaker.target)), class_2350.field_11036, this.getBreakBlock(HoleBreaker.target), true));
                HoleBreaker.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
            }
            InventoryUtil.selectSlot(InventoryUtil.findHotbarItem(class_1802.field_22024));
            HoleBreaker.mc.field_1761.method_2902(this.getBreakBlock(HoleBreaker.target), class_2350.field_11036);
            HoleBreaker.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
            InventoryUtil.selectSlot(preSwapSlot);
        }
    }
    
    public class_2338 getBreakBlock(final class_1657 target) {
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
