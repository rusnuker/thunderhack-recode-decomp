//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import net.minecraft.*;

public class FakeCrystalEntity extends class_1511
{
    public FakeCrystalEntity(final double x, final double y, final double z) {
        super((class_1937)Util.mc.field_1687, x, y, z);
        this.field_5982 = this.method_36454();
        this.field_6004 = this.method_36455();
        final Byte crystalModel = (Byte)new class_1511((class_1937)Util.mc.field_1687, x, y, z).method_5841().method_12789(class_1511.field_5990);
        this.field_6011.method_12778(class_1511.field_5990, (Object)crystalModel);
    }
    
    public void spawn() {
        this.method_31482();
        Util.mc.field_1687.method_2942(this.method_5628(), (class_1297)this);
    }
    
    public void despawn() {
        Util.mc.field_1687.method_2945(this.method_5628(), class_1297.class_5529.field_26999);
        this.method_31745(class_1297.class_5529.field_26999);
    }
}
