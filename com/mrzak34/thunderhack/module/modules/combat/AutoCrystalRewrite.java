//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import java.util.*;
import net.minecraft.*;
import com.mrzak34.thunderhack.util.*;

public class AutoCrystalRewrite extends Module
{
    NumberSetting targetRange;
    NumberSetting placeRange;
    NumberSetting placeWallRange;
    NumberSetting placeDelay;
    ParentSetting doPlace;
    NumberSetting breakRange;
    NumberSetting breakWallRange;
    NumberSetting breakDelay;
    ParentSetting doBreak;
    ModeSetting placeMode;
    BooleanSetting eating;
    BooleanSetting rClickGap;
    ParentSetting pause;
    NumberSetting minDmg;
    NumberSetting maxSelfDmg;
    BooleanSetting autoSwitch;
    public static class_1657 target;
    private final ArrayList<class_1511> crystals;
    int preSwapSlot;
    
    public AutoCrystalRewrite() {
        super("AutoCrystalRewrite", 0, false, Category.COMBAT);
        this.targetRange = new NumberSetting("target range", 8.0f, 1.0f, 10.0f, true);
        this.placeRange = new NumberSetting("place range", 5.0f, 1.0f, 10.0f, true);
        this.placeWallRange = new NumberSetting("place wall range", 5.0f, 1.0f, 10.0f, true);
        this.placeDelay = new NumberSetting("place delay", 0.0f, 1.0f, 20.0f, false);
        this.doPlace = new ParentSetting("place", true, true, new Setting[] { this.placeRange, this.placeWallRange, this.placeDelay });
        this.breakRange = new NumberSetting("break range", 5.0f, 1.0f, 10.0f, true);
        this.breakWallRange = new NumberSetting("break wall range", 5.0f, 1.0f, 10.0f, true);
        this.breakDelay = new NumberSetting("break delay", 0.0f, 1.0f, 20.0f, false);
        this.doBreak = new ParentSetting("break", true, true, new Setting[] { this.breakRange, this.breakWallRange, this.breakDelay });
        this.placeMode = new ModeSetting("place mode", "new", new String[] { "old", "new" });
        this.eating = new BooleanSetting("eating", true);
        this.rClickGap = new BooleanSetting("right click gap", true);
        this.pause = new ParentSetting("pause", false, false, new Setting[] { this.eating, this.rClickGap });
        this.minDmg = new NumberSetting("min dmg", 5.0f, 1.0f, 36.0f, false);
        this.maxSelfDmg = new NumberSetting("max self dmg", 10.0f, 1.0f, 36.0f, false);
        this.autoSwitch = new BooleanSetting("auto switch", true);
        this.crystals = new ArrayList<class_1511>();
        this.preSwapSlot = -1;
        this.addSettings(new Setting[] { this.targetRange, this.doPlace, this.doBreak, this.placeMode, this.pause, this.minDmg, this.maxSelfDmg, this.autoSwitch });
    }
    
    public void onTick() {
        super.onTick();
        AutoCrystalRewrite.target = TargetUtil.getTarget(this.targetRange.getValue());
        if (AutoCrystalRewrite.target != null) {
            if (CrystalUtil.needCrystal(AutoCrystalRewrite.target, this.minDmg.getValue())) {
                if (this.doPlace.isEnabled()) {
                    this.placeCrystal();
                }
            }
            else if (this.doBreak.isEnabled()) {
                this.breakCrystal();
            }
        }
    }
    
    private void breakCrystal() {
        this.findCrystals();
        for (final class_1511 crystal : this.crystals) {
            if (this.doBreak.isEnabled() && AutoCrystalRewrite.mc.field_1724.field_6012 % this.breakDelay.getValue() == 0.0) {
                AutoCrystalRewrite.mc.field_1761.method_2918((class_1657)AutoCrystalRewrite.mc.field_1724, (class_1297)crystal);
                AutoCrystalRewrite.mc.field_1724.method_6104(class_1268.field_5808);
            }
        }
    }
    
    private void findCrystals() {
        this.crystals.clear();
        class_1511 crystal;
        AutoCrystalRewrite.mc.field_1687.method_18112().forEach(entity -> {
            if (entity instanceof class_1511) {
                crystal = (class_1511)entity;
                if (AutoCrystalRewrite.mc.field_1724.method_5739(entity) <= this.breakRange.getValue() || (!AutoCrystalRewrite.mc.field_1724.method_6057(entity) && AutoCrystalRewrite.mc.field_1724.method_5739(entity) <= this.breakWallRange.getValue())) {
                    this.crystals.add(crystal);
                }
            }
        });
    }
    
    private void placeCrystal() {
        for (final class_2338 pos : this.getCrystalPositions((class_1309)AutoCrystalRewrite.target)) {
            if (this.autoSwitch.isEnabled()) {
                InventoryUtil.switchToHotbarSlot(class_1802.field_8301, false);
            }
            if ((AutoCrystalRewrite.mc.field_1724.method_31548().method_7391().method_7909() == class_1802.field_8301 || AutoCrystalRewrite.mc.field_1724.method_6079().method_7909() == class_1802.field_8301) && AutoCrystalRewrite.mc.field_1724.field_6012 % this.placeDelay.getValue() == 0.0) {
                CrystalUtil.placeCrystal(pos, getDirection(pos), false);
                AutoCrystalRewrite.mc.field_1724.method_6104(class_1268.field_5808);
                break;
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
    
    public static class_2350 getDirection(final class_2338 block) {
        final class_243 eyeVec = AutoCrystalRewrite.mc.field_1724.method_33571();
        final class_243 vec = class_243.method_26410((class_2382)block, 1.0);
        for (final class_2350 d : class_2350.values()) {
            final class_243 vd = WorldUtil.getLegitLookPos(block, d, true, 5);
            if (vd != null && eyeVec.method_1022(vd) <= eyeVec.method_1022(vec)) {
                return d;
            }
        }
        return (block.method_10264() > AutoCrystalRewrite.mc.field_1724.method_23320()) ? class_2350.field_11033 : class_2350.field_11036;
    }
}
