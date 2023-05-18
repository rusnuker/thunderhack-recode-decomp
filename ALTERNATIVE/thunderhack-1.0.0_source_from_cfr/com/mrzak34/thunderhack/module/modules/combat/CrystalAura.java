/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1268
 *  net.minecraft.class_1297
 *  net.minecraft.class_1309
 *  net.minecraft.class_1511
 *  net.minecraft.class_1657
 *  net.minecraft.class_1713
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
import net.minecraft.class_1713;
import net.minecraft.class_1802;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_2382;
import net.minecraft.class_243;

public class CrystalAura
extends Module {
    BooleanSetting doPlace = new BooleanSetting("place", true);
    BooleanSetting doBreak = new BooleanSetting("break", true);
    NumberSetting range = new NumberSetting("range", 5.0f, 1.0f, 5.0f, true);
    ModeSetting placeMode = new ModeSetting("place mode", "old", "old", "new");
    BooleanSetting autoSwitch = new BooleanSetting("auto switch", true);
    NumberSetting breakTick = new NumberSetting("break delay", 10.0f, 1.0f, 20.0f, false);
    NumberSetting placeTick = new NumberSetting("place delay", 10.0f, 1.0f, 20.0f, false);
    NumberSetting stopHp = new NumberSetting("stop hp", 10.0f, 1.0f, 20.0f, false);
    ParentSetting antiSuicide = new ParentSetting("anti-suicide", true, true, this.stopHp);
    BooleanSetting offhand = new BooleanSetting("offhand", false);
    private final ArrayList<class_1511> crystals = new ArrayList();

    public CrystalAura() {
        super("CrystalAura", 0, false, Category.COMBAT);
        this.addSettings(this.doPlace, this.doBreak, this.range, this.placeMode, this.autoSwitch, this.breakTick, this.placeTick, this.antiSuicide, this.offhand);
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.crystals.clear();
    }

    @Override
    public void onTick() {
        class_1657 target;
        super.onTick();
        if (this.antiSuicide.isEnabled() && (double)CrystalAura.mc.field_1724.method_6032() <= this.stopHp.getValue()) {
            return;
        }
        if (this.offhand.isEnabled() && CrystalAura.mc.field_1724.method_6079().method_7909() != class_1802.field_8301) {
            for (int slot = 9; slot < 45; ++slot) {
                if (CrystalAura.mc.field_1724.method_31548().method_5438(slot >= 36 ? slot - 36 : slot).method_7909() != class_1802.field_8301) continue;
                CrystalAura.mc.field_1761.method_2906(CrystalAura.mc.field_1724.field_7512.field_7763, slot, 0, class_1713.field_7790, (class_1657)CrystalAura.mc.field_1724);
                CrystalAura.mc.field_1761.method_2906(CrystalAura.mc.field_1724.field_7512.field_7763, 45, 0, class_1713.field_7790, (class_1657)CrystalAura.mc.field_1724);
                if (CrystalAura.mc.field_1724.method_6079().method_7960()) continue;
                CrystalAura.mc.field_1761.method_2906(CrystalAura.mc.field_1724.field_7512.field_7763, slot, 0, class_1713.field_7790, (class_1657)CrystalAura.mc.field_1724);
            }
        }
        if ((target = TargetUtil.getTarget(this.range.getValue())) == null) {
            return;
        }
        if (this.doPlace.isEnabled()) {
            for (class_2338 pos : this.getCrystalPositions((class_1309)target)) {
                if (this.autoSwitch.isEnabled()) {
                    InventoryUtil.switchToHotbarSlot(class_1802.field_8301, false);
                }
                if (CrystalAura.mc.field_1724.method_31548().method_7391().method_7909() != class_1802.field_8301 && CrystalAura.mc.field_1724.method_6079().method_7909() != class_1802.field_8301 || (double)CrystalAura.mc.field_1724.field_6012 % this.placeTick.getValue() != 0.0) continue;
                CrystalUtil.placeCrystal(pos, CrystalAura.getDirection(pos), false);
                CrystalAura.mc.field_1724.method_6104(this.offhand.isEnabled() ? class_1268.field_5810 : class_1268.field_5808);
                break;
            }
        }
        this.findCrystals();
        for (class_1511 crystal : this.crystals) {
            if (!this.doBreak.isEnabled() || (double)CrystalAura.mc.field_1724.field_6012 % this.breakTick.getValue() != 0.0) continue;
            CrystalAura.mc.field_1761.method_2918((class_1657)CrystalAura.mc.field_1724, (class_1297)crystal);
            CrystalAura.mc.field_1724.method_6104(class_1268.field_5808);
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

    private void findCrystals() {
        this.crystals.clear();
        CrystalAura.mc.field_1687.method_18112().forEach(entity -> {
            if (entity instanceof class_1511) {
                class_1511 crystal = (class_1511)entity;
                if ((double)CrystalAura.mc.field_1724.method_5739(entity) <= this.range.getValue()) {
                    this.crystals.add(crystal);
                }
            }
        });
    }

    public static class_2350 getDirection(class_2338 block) {
        class_243 eyeVec = CrystalAura.mc.field_1724.method_33571();
        class_243 vec = class_243.method_26410((class_2382)block, (double)1.0);
        for (class_2350 d : class_2350.values()) {
            class_243 vd = WorldUtil.getLegitLookPos(block, d, true, 5);
            if (vd == null || !(eyeVec.method_1022(vd) <= eyeVec.method_1022(vec))) continue;
            return d;
        }
        return (double)block.method_10264() > CrystalAura.mc.field_1724.method_23320() ? class_2350.field_11033 : class_2350.field_11036;
    }
}

