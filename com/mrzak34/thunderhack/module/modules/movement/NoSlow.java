//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.movement;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.mrzak34.thunderhack.event.events.*;
import com.google.common.eventbus.*;
import net.minecraft.*;
import com.mrzak34.thunderhack.util.*;

public class NoSlow extends Module
{
    public BooleanSetting slowness;
    public BooleanSetting soulsand;
    public BooleanSetting slimeblock;
    public BooleanSetting strict;
    public BooleanSetting web;
    public BooleanSetting item;
    private class_243 addVelocity;
    boolean lastTickOG;
    private static NoSlow INSTANCE;
    
    public NoSlow() {
        super("NoSlow", 0, false, Category.MOVEMENT);
        this.slowness = new BooleanSetting("slowness", true);
        this.soulsand = new BooleanSetting("soulsand", true);
        this.slimeblock = new BooleanSetting("slimeblock", true);
        this.strict = new BooleanSetting("strict", true);
        this.web = new BooleanSetting("web", true);
        this.item = new BooleanSetting("item", true);
        this.addVelocity = class_243.field_1353;
        this.addSettings(new Setting[] { this.slowness, this.soulsand, this.slimeblock, this.web, this.item, this.strict });
        this.setInstance();
    }
    
    @Subscribe
    public void onMovePre(final MoveEvent event) {
        if (this.strict.isEnabled() && NoSlow.mc.field_1724.method_6115()) {
            NoSlow.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(NoSlow.mc.field_1724.method_31548().field_7545));
        }
    }
    
    @Subscribe
    public void onMovePost(final MoveEvent event) {
        if (this.slowness.isEnabled() && (NoSlow.mc.field_1724.method_6112(class_1294.field_5909) != null || NoSlow.mc.field_1724.method_6112(class_1294.field_5919) != null)) {
            if (NoSlow.mc.field_1690.field_1894.method_1434() && NoSlow.mc.field_1724.method_18798().field_1352 > -0.15 && NoSlow.mc.field_1724.method_18798().field_1352 < 0.15 && NoSlow.mc.field_1724.method_18798().field_1350 > -0.15 && NoSlow.mc.field_1724.method_18798().field_1350 < 0.15) {
                NoSlow.mc.field_1724.method_18799(NoSlow.mc.field_1724.method_18798().method_1019(this.addVelocity));
                this.addVelocity = this.addVelocity.method_1019(new class_243(0.0, 0.0, 0.05).method_1024(-(float)Math.toRadians(NoSlow.mc.field_1724.method_36454())));
            }
            else {
                this.addVelocity = this.addVelocity.method_18805(0.75, 0.75, 0.75);
            }
        }
        if (this.soulsand.isEnabled() && NoSlow.mc.field_1687.method_8320(NoSlow.mc.field_1724.method_24515()).method_26204() == class_2246.field_10114) {
            NoSlow.mc.field_1724.method_18799(NoSlow.mc.field_1724.method_18798().method_18805(2.5, 1.0, 2.5));
        }
        if (this.slimeblock.isEnabled() && NoSlow.mc.field_1687.method_8320(new class_2338((class_2382)NoSlow.mc.field_1724.method_24515().method_10069(0, -1, 0))).method_26204() == class_2246.field_10030 && NoSlow.mc.field_1724.method_24828()) {
            final double d = Math.abs(NoSlow.mc.field_1724.method_18798().field_1351);
            if (d < 0.1 && !NoSlow.mc.field_1724.method_21749()) {
                final double e = 1.0 / (0.4 + d * 0.2);
                NoSlow.mc.field_1724.method_18799(NoSlow.mc.field_1724.method_18798().method_18805(e, 1.0, e));
            }
        }
        if (this.web.isEnabled() && WorldUtil.doesBoxTouchBlock(NoSlow.mc.field_1724.method_5829(), class_2246.field_10343)) {
            NoSlow.mc.field_1724.method_5844(NoSlow.mc.field_1687.method_8320(NoSlow.mc.field_1724.method_24515()), new class_243(1.75, 1.75, 1.75));
        }
    }
    
    public static NoSlow getInstance() {
        if (NoSlow.INSTANCE == null) {
            NoSlow.INSTANCE = new NoSlow();
        }
        return NoSlow.INSTANCE;
    }
    
    private void setInstance() {
        NoSlow.INSTANCE = this;
    }
    
    static {
        NoSlow.INSTANCE = new NoSlow();
    }
}
