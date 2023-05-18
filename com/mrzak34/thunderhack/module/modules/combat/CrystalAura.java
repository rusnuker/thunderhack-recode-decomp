//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import java.util.*;
import net.minecraft.*;
import com.mrzak34.thunderhack.util.*;

public class CrystalAura extends Module
{
    BooleanSetting doPlace;
    BooleanSetting doBreak;
    NumberSetting range;
    ModeSetting placeMode;
    BooleanSetting autoSwitch;
    NumberSetting breakTick;
    NumberSetting placeTick;
    NumberSetting stopHp;
    ParentSetting antiSuicide;
    BooleanSetting offhand;
    private final ArrayList<class_1511> crystals;
    
    public CrystalAura() {
        super("CrystalAura", 0, false, Category.COMBAT);
        this.doPlace = new BooleanSetting("place", true);
        this.doBreak = new BooleanSetting("break", true);
        this.range = new NumberSetting("range", 5.0f, 1.0f, 5.0f, true);
        this.placeMode = new ModeSetting("place mode", "old", new String[] { "old", "new" });
        this.autoSwitch = new BooleanSetting("auto switch", true);
        this.breakTick = new NumberSetting("break delay", 10.0f, 1.0f, 20.0f, false);
        this.placeTick = new NumberSetting("place delay", 10.0f, 1.0f, 20.0f, false);
        this.stopHp = new NumberSetting("stop hp", 10.0f, 1.0f, 20.0f, false);
        this.antiSuicide = new ParentSetting("anti-suicide", true, true, new Setting[] { this.stopHp });
        this.offhand = new BooleanSetting("offhand", false);
        this.crystals = new ArrayList<class_1511>();
        this.addSettings(new Setting[] { this.doPlace, this.doBreak, this.range, this.placeMode, this.autoSwitch, this.breakTick, this.placeTick, this.antiSuicide, this.offhand });
    }
    
    public void onDisable() {
        super.onDisable();
        this.crystals.clear();
    }
    
    public void onTick() {
        super.onTick();
        if (this.antiSuicide.isEnabled() && CrystalAura.mc.field_1724.method_6032() <= this.stopHp.getValue()) {
            return;
        }
        if (this.offhand.isEnabled() && CrystalAura.mc.field_1724.method_6079().method_7909() != class_1802.field_8301) {
            for (int slot = 9; slot < 45; ++slot) {
                if (CrystalAura.mc.field_1724.method_31548().method_5438((slot >= 36) ? (slot - 36) : slot).method_7909() == class_1802.field_8301) {
                    CrystalAura.mc.field_1761.method_2906(CrystalAura.mc.field_1724.field_7512.field_7763, slot, 0, class_1713.field_7790, (class_1657)CrystalAura.mc.field_1724);
                    CrystalAura.mc.field_1761.method_2906(CrystalAura.mc.field_1724.field_7512.field_7763, 45, 0, class_1713.field_7790, (class_1657)CrystalAura.mc.field_1724);
                    if (!CrystalAura.mc.field_1724.method_6079().method_7960()) {
                        CrystalAura.mc.field_1761.method_2906(CrystalAura.mc.field_1724.field_7512.field_7763, slot, 0, class_1713.field_7790, (class_1657)CrystalAura.mc.field_1724);
                    }
                }
            }
        }
        final class_1657 target = TargetUtil.getTarget(this.range.getValue());
        if (target == null) {
            return;
        }
        if (this.doPlace.isEnabled()) {
            for (final class_2338 pos : this.getCrystalPositions((class_1309)target)) {
                if (this.autoSwitch.isEnabled()) {
                    InventoryUtil.switchToHotbarSlot(class_1802.field_8301, false);
                }
                if ((CrystalAura.mc.field_1724.method_31548().method_7391().method_7909() == class_1802.field_8301 || CrystalAura.mc.field_1724.method_6079().method_7909() == class_1802.field_8301) && CrystalAura.mc.field_1724.field_6012 % this.placeTick.getValue() == 0.0) {
                    CrystalUtil.placeCrystal(pos, getDirection(pos), false);
                    CrystalAura.mc.field_1724.method_6104(this.offhand.isEnabled() ? class_1268.field_5810 : class_1268.field_5808);
                    break;
                }
            }
        }
        this.findCrystals();
        for (final class_1511 crystal : this.crystals) {
            if (this.doBreak.isEnabled() && CrystalAura.mc.field_1724.field_6012 % this.breakTick.getValue() == 0.0) {
                CrystalAura.mc.field_1761.method_2918((class_1657)CrystalAura.mc.field_1724, (class_1297)crystal);
                CrystalAura.mc.field_1724.method_6104(class_1268.field_5808);
            }
        }
    }
    
    private ArrayList<class_2338> getCrystalPositions(final class_1309 target) {
        final ArrayList<class_2338> posses = new ArrayList<class_2338>();
        for (int x = (int)(target.method_23317() - 1.0); x <= target.method_23317() + 1.0; ++x) {
            for (int y = (int)(target.method_23318() - 1.0); y <= target.method_23318() + 1.0; ++y) {
                for (int z = (int)(target.method_23321() - 1.0); z <= target.method_23321() + 1.0; ++z) {
                    final class_2338 pos = new class_2338(x, y, z);
                    if (CrystalUtil.canPlaceCrystal(pos, false, this.placeMode.getMode().equals("new"))) {
                        posses.add(pos);
                    }
                }
            }
        }
        return posses;
    }
    
    private void findCrystals() {
        this.crystals.clear();
        class_1511 crystal;
        CrystalAura.mc.field_1687.method_18112().forEach(entity -> {
            if (entity instanceof class_1511) {
                crystal = entity;
                if (CrystalAura.mc.field_1724.method_5739((class_1297)entity) <= this.range.getValue()) {
                    this.crystals.add(crystal);
                }
            }
        });
    }
    
    public static class_2350 getDirection(final class_2338 block) {
        final class_243 eyeVec = CrystalAura.mc.field_1724.method_33571();
        final class_243 vec = class_243.method_26410((class_2382)block, 1.0);
        for (final class_2350 d : class_2350.values()) {
            final class_243 vd = WorldUtil.getLegitLookPos(block, d, true, 5);
            if (vd != null && eyeVec.method_1022(vd) <= eyeVec.method_1022(vec)) {
                return d;
            }
        }
        return (block.method_10264() > CrystalAura.mc.field_1724.method_23320()) ? class_2350.field_11033 : class_2350.field_11036;
    }
}
