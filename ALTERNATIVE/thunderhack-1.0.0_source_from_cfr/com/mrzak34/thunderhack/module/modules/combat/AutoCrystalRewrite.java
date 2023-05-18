/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1511
 *  net.minecraft.class_1657
 *  net.minecraft.class_1802
 *  net.minecraft.class_2338
 *  net.minecraft.class_2350
 *  net.minecraft.class_2382
 *  net.minecraft.class_243
 */
package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.Category;
import com.mrzak34.thunderhack.module.Module;
import com.mrzak34.thunderhack.settings.BooleanSetting;
import com.mrzak34.thunderhack.settings.ModeSetting;
import com.mrzak34.thunderhack.settings.NumberSetting;
import com.mrzak34.thunderhack.settings.ParentSetting;
import com.mrzak34.thunderhack.util.CrystalUtil;
import com.mrzak34.thunderhack.util.InventoryUtil;
import com.mrzak34.thunderhack.util.TargetUtil;
import com.mrzak34.thunderhack.util.WorldUtil;
import java.util.ArrayList;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1511;
import net.minecraft.class_1657;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_243;

public class AutoCrystalRewrite
extends Module {
    NumberSetting targetRange = new NumberSetting("target range", 8.0f, 1.0f, 10.0f, true);
    NumberSetting placeRange = new NumberSetting("place range", 5.0f, 1.0f, 10.0f, true);
    NumberSetting placeWallRange = new NumberSetting("place wall range", 5.0f, 1.0f, 10.0f, true);
    NumberSetting placeDelay = new NumberSetting("place delay", 0.0f, 1.0f, 20.0f, false);
    ParentSetting doPlace = new ParentSetting("place", true, true, this.placeRange, this.placeWallRange, this.placeDelay);
    NumberSetting breakRange = new NumberSetting("break range", 5.0f, 1.0f, 10.0f, true);
    NumberSetting breakWallRange = new NumberSetting("break wall range", 5.0f, 1.0f, 10.0f, true);
    NumberSetting breakDelay = new NumberSetting("break delay", 0.0f, 1.0f, 20.0f, false);
    ParentSetting doBreak = new ParentSetting("break", true, true, this.breakRange, this.breakWallRange, this.breakDelay);
    ModeSetting placeMode = new ModeSetting("place mode", "new", "old", "new");
    BooleanSetting eating = new BooleanSetting("eating", true);
    BooleanSetting rClickGap = new BooleanSetting("right click gap", true);
    ParentSetting pause = new ParentSetting("pause", false, false, this.eating, this.rClickGap);
    NumberSetting minDmg = new NumberSetting("min dmg", 5.0f, 1.0f, 36.0f, false);
    NumberSetting maxSelfDmg = new NumberSetting("max self dmg", 10.0f, 1.0f, 36.0f, false);
    BooleanSetting autoSwitch = new BooleanSetting("auto switch", true);
    public static class_1657 target;
    private final ArrayList<class_1511> crystals = new ArrayList();
    int preSwapSlot = -1;

    public AutoCrystalRewrite() {
        super("AutoCrystalRewrite", 0, false, Category.COMBAT);
        this.addSettings(this.targetRange, this.doPlace, this.doBreak, this.placeMode, this.pause, this.minDmg, this.maxSelfDmg, this.autoSwitch);
    }

    @Override
    public void onTick() {
        super.onTick();
        target = TargetUtil.getTarget(this.targetRange.getValue());
        if (target != null) {
            if (CrystalUtil.needCrystal(target, this.minDmg.getValue())) {
                if (this.doPlace.isEnabled()) {
                    this.placeCrystal();
                }
            } else if (this.doBreak.isEnabled()) {
                this.breakCrystal();
            }
        }
    }

    private void breakCrystal() {
        this.findCrystals();
        for (class_1511 crystal : this.crystals) {
            if (!this.doBreak.isEnabled() || (double)AutoCrystalRewrite.mc.field_1724.field_6012 % this.breakDelay.getValue() != 0.0) continue;
            AutoCrystalRewrite.mc.field_1761.method_2918((class_1657)AutoCrystalRewrite.mc.field_1724, (class_1297)crystal);
            AutoCrystalRewrite.mc.field_1724.method_6104(class_1268.field_5808);
        }
    }

    private void findCrystals() {
        this.crystals.clear();
        AutoCrystalRewrite.mc.field_1687.method_18112().forEach(entity -> {
            if (entity instanceof class_1511) {
                class_1511 crystal = (class_1511)entity;
                if ((double)AutoCrystalRewrite.mc.field_1724.method_5739(entity) <= this.breakRange.getValue() || !AutoCrystalRewrite.mc.field_1724.method_6057(entity) && (double)AutoCrystalRewrite.mc.field_1724.method_5739(entity) <= this.breakWallRange.getValue()) {
                    this.crystals.add(crystal);
                }
            }
        });
    }

    private void placeCrystal() {
        for (class_2338 pos : this.getCrystalPositions((class_1309)target)) {
            if (this.autoSwitch.isEnabled()) {
                InventoryUtil.switchToHotbarSlot(class_1802.field_8301, false);
            }
            if (AutoCrystalRewrite.mc.field_1724.method_31548().method_7391().method_7909() != class_1802.field_8301 && AutoCrystalRewrite.mc.field_1724.method_6079().method_7909() != class_1802.field_8301 || (double)AutoCrystalRewrite.mc.field_1724.field_6012 % this.placeDelay.getValue() != 0.0) continue;
            CrystalUtil.placeCrystal(pos, AutoCrystalRewrite.getDirection(pos), false);
            AutoCrystalRewrite.mc.field_1724.method_6104(class_1268.field_5808);
            break;
        }
    }

    private ArrayList<class_2338> getCrystalPositions(class_1309 target) {
        ArrayList<class_2338> posses = new ArrayList<class_2338>();
        int x = (int)(target.method_23317() - 1.0);
        while ((double)x <= target.method_23317() + 1.0) {
            int y = (int)(target.method_23318() - 1.0);
            while ((double)y <= target.method_23318() + 1.0) {
                int z = (int)(target.method_23321() - 1.0);
                while ((double)z <= target.method_23321() + 1.0) {
                    class_2338 pos = new class_2338(x, y, z);
                    if (CrystalUtil.canPlaceCrystal(pos, false, this.placeMode.getMode().equals("new"))) {
                        posses.add(pos);
                    }
                    ++z;
                }
                ++y;
            }
            ++x;
        }
        return posses;
    }

    public static class_2350 getDirection(class_2338 block) {
        class_243 eyeVec = AutoCrystalRewrite.mc.field_1724.method_33571();
        class_243 vec = class_243.method_26410((class_2382)block, (double)1.0);
        for (class_2350 d : class_2350.values()) {
            class_243 vd = WorldUtil.getLegitLookPos(block, d, true, 5);
            if (vd == null || !(eyeVec.method_1022(vd) <= eyeVec.method_1022(vec))) continue;
            return d;
        }
        return (double)block.method_10264() > AutoCrystalRewrite.mc.field_1724.method_23320() ? class_2350.field_11033 : class_2350.field_11036;
    }
}

