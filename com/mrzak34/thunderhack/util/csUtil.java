//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.util;

import net.minecraft.*;

public class csUtil implements Util
{
    public static volatile csUtil a;
    
    public class_1263 a() {
        return (class_1263)csUtil.mc.field_1724.method_31548();
    }
    
    public class_636 b() {
        return csUtil.mc.field_1761;
    }
    
    public void a(final class_1297 entity) {
        csUtil.a.b().method_2918((class_1657)csUtil.mc.field_1724, entity);
    }
    
    public double d() {
        return csUtil.mc.field_1724.method_23317();
    }
    
    public double e() {
        return csUtil.mc.field_1724.method_23318();
    }
    
    public double f() {
        return csUtil.mc.field_1724.method_23321();
    }
    
    static {
        csUtil.a = new csUtil();
    }
}
