//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.module.modules.combat;

import com.mrzak34.thunderhack.module.*;
import com.mrzak34.thunderhack.settings.*;
import com.google.common.eventbus.*;
import com.mrzak34.thunderhack.event.events.*;
import org.apache.commons.lang3.*;
import java.util.*;
import com.mrzak34.thunderhack.util.*;
import net.minecraft.*;

public class DeadAura extends Module
{
    ModeSetting rotationMode;
    ModeSetting targetMode;
    ModeSetting targetPrio;
    ModeSetting aimMode;
    ModeSetting shieldBreakMode;
    NumberSetting distance;
    NumberSetting fov;
    NumberSetting minCPS;
    NumberSetting maxCPS;
    BooleanSetting autocrit;
    BooleanSetting rayrace;
    BooleanSetting look;
    BooleanSetting stopSprinting;
    BooleanSetting onlyWeapon;
    BooleanSetting shieldBlock;
    BooleanSetting shieldBreak;
    BooleanSetting autoDisable;
    BooleanSetting throughWalls;
    BooleanSetting teleport;
    BooleanSetting swing;
    BooleanSetting cooldown;
    public static TimerUtil D;
    public TimerUtil E;
    public TimerUtil F;
    public TimerUtil G;
    public TimerUtil H;
    public TimerUtil I;
    public static class_1657 target;
    public static float[] K;
    public float L;
    public boolean M;
    public double N;
    public int O;
    public boolean P;
    public float Q;
    public float R;
    public float serverYaw;
    public float serverPitch;
    public static double prevCircleStep;
    public static double circleStep;
    public static ArrayList<class_1657> targets;
    
    public DeadAura() {
        super("DeadAura", 0, false, Category.COMBAT);
        this.rotationMode = new ModeSetting("rotation", "default", new String[] { "default", "matrix", "aac", "sunrise" });
        this.targetMode = new ModeSetting("mode", "switch", new String[] { "switch", "single" });
        this.targetPrio = new ModeSetting("target prio", "closest", new String[] { "closest", "health", "equip" });
        this.aimMode = new ModeSetting("aim mode", "body", new String[] { "head", "body", "legs", "all" });
        this.shieldBreakMode = new ModeSetting("shield break mode", "old", new String[] { "old", "new" });
        this.distance = new NumberSetting("distance", 3.8f, 1.0f, 7.0f, true);
        this.fov = new NumberSetting("fov", 360.0f, 0.0f, 360.0f, false);
        this.minCPS = new NumberSetting("min cps", 6.0f, 1.0f, 30.0f, false);
        this.maxCPS = new NumberSetting("max cps", 12.0f, 1.0f, 30.0f, false);
        this.autocrit = new BooleanSetting("autocrit", true);
        this.rayrace = new BooleanSetting("raytrace", false);
        this.look = new BooleanSetting("look", false);
        this.stopSprinting = new BooleanSetting("stop sprint", false);
        this.onlyWeapon = new BooleanSetting("weapon only", false);
        this.shieldBlock = new BooleanSetting("shield block", false);
        this.shieldBreak = new BooleanSetting("shield breack", false);
        this.autoDisable = new BooleanSetting("auto disable", false);
        this.throughWalls = new BooleanSetting("through walls", false);
        this.teleport = new BooleanSetting("teleport", false);
        this.swing = new BooleanSetting("swing", true);
        this.cooldown = new BooleanSetting("cooldown", false);
        this.E = new TimerUtil();
        this.F = new TimerUtil();
        this.G = new TimerUtil();
        this.H = new TimerUtil();
        this.I = new TimerUtil();
        this.L = 0.1f;
        this.O = -2;
        this.serverYaw = 0.0f;
        this.serverPitch = 0.0f;
        this.addSettings(new Setting[] { this.rotationMode, this.targetMode, this.targetPrio, this.aimMode, this.shieldBreakMode, this.distance, this.fov, this.minCPS, this.maxCPS, this.autocrit, this.rayrace, this.look, this.stopSprinting, this.onlyWeapon, this.shieldBreak, this.autoDisable, this.throughWalls, this.teleport, this.swing, this.cooldown });
    }
    
    public void onEnable() {
        super.onEnable();
        DeadAura.target = null;
        DeadAura.K[0] = 0.0f;
        DeadAura.K[1] = 0.0f;
        this.Q = this.d();
        this.R = this.e();
        if (!this.look.isEnabled() && !this.rotationMode.getMode().equals("aac")) {
            efUtil.j();
        }
        efUtil.l();
        this.L = (this.rotationMode.getMode().equals("sunrise") ? 0.5f : 0.1f);
        this.N = 0.0;
    }
    
    public void onDisable() {
        super.onDisable();
        DeadAura.target = null;
        DeadAura.K[0] = 0.0f;
        DeadAura.K[1] = 0.0f;
        this.Q = this.d();
        this.R = this.e();
        if (!this.look.isEnabled() && !this.rotationMode.getMode().equals("aac")) {
            efUtil.j();
        }
        efUtil.l();
        this.L = (this.rotationMode.getMode().equals("sunrise") ? 0.5f : 0.1f);
        this.a(false);
    }
    
    public void onTick() {
        super.onTick();
        DeadAura.prevCircleStep = DeadAura.circleStep;
        DeadAura.circleStep += 0.15;
        if (!this.autoDisable.isEnabled()) {
            return;
        }
        if (DeadAura.mc.field_1724.method_6032() == 0.0f || DeadAura.mc.field_1724.method_29504()) {
            this.toggle();
        }
    }
    
    @Subscribe
    public void onMove(final MoveEvent event) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: invokestatic    invokestatic   !!! ERROR
        //     6: aload_0         /* this */
        //     7: getfield        com/mrzak34/thunderhack/module/modules/combat/DeadAura.rotationMode:Lcom/mrzak34/thunderhack/settings/ModeSetting;
        //    10: invokevirtual   com/mrzak34/thunderhack/settings/ModeSetting.getMode:()Ljava/lang/String;
        //    13: ldc             "matrix"
        //    15: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    18: ifne            52
        //    21: aload_0         /* this */
        //    22: getfield        com/mrzak34/thunderhack/module/modules/combat/DeadAura.rotationMode:Lcom/mrzak34/thunderhack/settings/ModeSetting;
        //    25: invokevirtual   com/mrzak34/thunderhack/settings/ModeSetting.getMode:()Ljava/lang/String;
        //    28: ldc             "sunrise"
        //    30: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    33: ifne            52
        //    36: aload_0         /* this */
        //    37: getfield        com/mrzak34/thunderhack/module/modules/combat/DeadAura.rotationMode:Lcom/mrzak34/thunderhack/settings/ModeSetting;
        //    40: invokevirtual   com/mrzak34/thunderhack/settings/ModeSetting.getMode:()Ljava/lang/String;
        //    43: ldc_w           "static"
        //    46: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    49: ifeq            57
        //    52: aload_0         /* this */
        //    53: aload_1         /* event */
        //    54: invokevirtual   com/mrzak34/thunderhack/module/modules/combat/DeadAura.doRotate:(Lcom/mrzak34/thunderhack/event/events/MoveEvent;)V
        //    57: aload_0         /* this */
        //    58: getfield        com/mrzak34/thunderhack/module/modules/combat/DeadAura.shieldBlock:Lcom/mrzak34/thunderhack/settings/BooleanSetting;
        //    61: invokevirtual   com/mrzak34/thunderhack/settings/BooleanSetting.isEnabled:()Z
        //    64: ifeq            71
        //    67: aload_0         /* this */
        //    68: invokevirtual   com/mrzak34/thunderhack/module/modules/combat/DeadAura.setShield:()V
        //    71: ldc_w           "z"
        //    74: invokestatic    invokestatic   !!! ERROR
        //    77: aload_0         /* this */
        //    78: invokevirtual   com/mrzak34/thunderhack/module/modules/combat/DeadAura.z:()V
        //    81: return         
        //    MethodParameters:
        //  Name   Flags  
        //  -----  -----
        //  event  
        //    StackMapTable: 00 03 34 04 0D
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Invalid BootstrapMethods attribute entry: 2 additional arguments required for method java/lang/invoke/StringConcatFactory.makeConcatWithConstants, but only 1 specified.
        //     at com.strobel.assembler.ir.Error.invalidBootstrapMethodEntry(Error.java:244)
        //     at com.strobel.assembler.ir.MetadataReader.readAttributeCore(MetadataReader.java:280)
        //     at com.strobel.assembler.metadata.ClassFileReader.readAttributeCore(ClassFileReader.java:261)
        //     at com.strobel.assembler.ir.MetadataReader.inflateAttributes(MetadataReader.java:439)
        //     at com.strobel.assembler.metadata.ClassFileReader.visitAttributes(ClassFileReader.java:1134)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:439)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:377)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveType(MetadataSystem.java:129)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveCore(MetadataSystem.java:81)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:104)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:616)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:128)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:626)
        //     at com.strobel.assembler.metadata.MethodReference.resolve(MethodReference.java:177)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2438)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:109)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Subscribe
    public void PacketReceive(final PacketEvent.Receive event) {
        if (fullNullCheck()) {
            return;
        }
        if ((event.getPacket() instanceof class_2828.class_2830 || event.getPacket() instanceof class_2828.class_2831) && (this.rotationMode.getMode().equals("matrix") || this.rotationMode.getMode().equals("sunrise")) && !this.look.isEnabled() && !this.rotationMode.getMode().equals("aac")) {
            this.z();
        }
    }
    
    public static int checkAxe() {
        int n2 = -2;
        for (int i2 = 0; i2 <= 8; ++i2) {
            if (DeadAura.mc.field_1724.method_31548().method_5438(i2).method_7909() instanceof class_1743) {
                n2 = i2;
            }
        }
        return n2;
    }
    
    public static boolean checkShield(final class_1657 entityPlayer) {
        final class_1799 itemStack = entityPlayer.method_6079();
        final class_1799 itemStack2 = entityPlayer.method_6047();
        return (!isEmpty(itemStack) && itemStack.method_7909() == class_1802.field_8255 && entityPlayer.method_6048() > 0) || (!isEmpty(itemStack2) && itemStack2.method_7909() == class_1802.field_8255 && entityPlayer.method_6048() > 0);
    }
    
    public static boolean isEmpty(final class_1799 itemStack) {
        return itemStack == null || itemStack.method_7960();
    }
    
    public void a(final class_1657 entityPlayer) {
        final int n2;
        if ((n2 = checkAxe()) != -2) {
            this.O = DeadAura.mc.field_1724.method_31548().field_7545;
            DeadAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(n2));
        }
    }
    
    public void b(final class_1657 entityPlayer) {
    }
    
    public static boolean etf() {
        return !isEmpty(DeadAura.mc.field_1724.method_6079()) && DeadAura.mc.field_1724.method_6079().method_7909() == class_1802.field_8255;
    }
    
    public void setShield() {
        if (!etf()) {
            return;
        }
        final class_1799 itemStack = DeadAura.mc.field_1724.method_6047();
        final class_1839 enumAction;
        if (!isEmpty(itemStack) && !DeadAura.mc.field_1690.field_1849.method_1434() && ((enumAction = itemStack.method_7909().method_7853(itemStack)) != class_1839.field_8952 || itemStack.method_7909() instanceof class_1747)) {
            this.a(false);
            this.M = false;
            return;
        }
        if (DeadAura.target == null) {
            if (this.M) {
                this.a(this.M = false);
            }
            return;
        }
        if (DeadAura.mc.field_1724.method_7261(0.0f) >= 0.75f) {
            this.M = false;
        }
        if (DeadAura.mc.field_1724.method_7261(0.0f) <= 0.1f) {
            this.M = true;
        }
        if (this.G.passedMs(RandomUtils.nextInt(0, 55))) {
            this.a(this.M);
            this.G.reset();
        }
    }
    
    public void a(final boolean bl) {
        if (DeadAura.mc.field_1724.method_6079().method_7909() instanceof class_1819) {
            DeadAura.mc.field_1690.field_1904.method_23481(bl);
        }
    }
    
    public float d() {
        return (this.look.isEnabled() || this.rotationMode.getMode().equals("aac")) ? DeadAura.mc.field_1724.method_36455() : efUtil.e();
    }
    
    public float e() {
        return (this.look.isEnabled() || this.rotationMode.getMode().equals("aac")) ? DeadAura.mc.field_1724.method_36454() : efUtil.d();
    }
    
    public void doRotate(final MoveEvent event) {
        if (DeadAura.target == null) {
            return;
        }
        if (this.rotationMode.getMode().equals("default")) {
            if (this.look.isEnabled() || this.rotationMode.getMode().equals("aac")) {
                DeadAura.K = DeadMathUtil.a((class_1297)DeadAura.target, this.getHitbox());
                DeadAura.mc.field_1724.method_36456(DeadAura.K[0]);
                DeadAura.mc.field_1724.method_36457(DeadAura.K[1]);
            }
        }
        else {
            if (!this.rotationMode.getMode().equals("static")) {
                float f2 = 2.2f - RandomUtils.nextFloat();
                if (this.rotationMode.getMode().equals("sunrise")) {
                    f2 = 5.0f - RandomUtils.nextFloat();
                    f2 += RandomUtils.nextFloat();
                }
                DeadAura.K = DeadMathUtil.a((class_1297)DeadAura.target, this.getHitbox(), f2);
                if (this.rotationMode.getMode().equals("sunrise")) {
                    this.R = DeadMathUtil.c(this.e(), DeadAura.K[0], 75.0f + RandomUtils.nextFloat(0.1f, 1.0f));
                    this.Q = DeadMathUtil.c(this.d(), DeadAura.K[1], 2.0f + RandomUtils.nextFloat(0.1f, 1.0f));
                    DeadAura.K[0] = this.R;
                    DeadAura.K[1] = this.Q;
                }
            }
            else {
                DeadAura.K = DeadMathUtil.a2((class_1297)DeadAura.target, this.look.isEnabled() || this.rotationMode.getMode().equals("aac"));
            }
            efUtil.a(DeadAura.K);
            DeadAura.mc.field_1724.method_36457(DeadAura.K[1]);
            DeadAura.mc.field_1724.method_36456((float)(DeadAura.K[0] + 1.0E-4));
            if (this.E.passedS(1.0)) {
                DeadAura.mc.field_1724.method_36456((float)(DeadAura.K[0] + 1.0E-4));
            }
            if (this.E.passedS(2.0)) {
                DeadAura.mc.field_1724.method_36456((float)(DeadAura.K[0] - 2.0E-4));
                this.E.reset();
            }
        }
        this.serverYaw = DeadAura.K[0];
        this.serverPitch = DeadAura.K[1];
    }
    
    public static boolean ete() {
        final class_1799 itemStack = DeadAura.mc.field_1724.method_6047();
        return !isEmpty(itemStack) && (itemStack.method_7909() instanceof class_1829 || itemStack.method_7909() instanceof class_1743);
    }
    
    private void getTarget() {
        if (this.targetPrio.getMode().equals("closest")) {
            DeadAura.target = this.getClosest(this.distance.getValue());
        }
        if (this.targetPrio.getMode().equals("equip")) {
            DeadAura.target = this.getArmorLess();
        }
        if (this.targetPrio.getMode().equals("health")) {
            DeadAura.target = this.getHealthDown();
        }
    }
    
    private void getTarget2() {
        DeadAura.target = TargetUtil.getTarget(this.distance.getValue());
    }
    
    private class_1657 getArmorLess() {
        DeadAura.targets.clear();
        double armor = 1815.0;
        class_1657 target = null;
        for (final class_1297 object : DeadAura.mc.field_1687.method_18112()) {
            if (object instanceof class_1657) {
                final class_1657 player = (class_1657)object;
                if (!this.canAttack(player)) {
                    continue;
                }
                final double currentarm = ThunderDamageUtil.ChekTotalarmorDamage(player);
                if (currentarm > armor) {
                    continue;
                }
                armor = currentarm;
                target = player;
                DeadAura.targets.add(player);
            }
        }
        return target;
    }
    
    private class_1657 getClosest(final double range) {
        DeadAura.targets.clear();
        double dist = range;
        class_1657 target = null;
        for (final class_1297 object : DeadAura.mc.field_1687.method_18112()) {
            if (object instanceof class_1657) {
                final class_1657 player = (class_1657)object;
                if (!this.canAttack(player)) {
                    continue;
                }
                final double currentDist = DeadAura.mc.field_1724.method_5739((class_1297)player);
                if (currentDist > dist) {
                    continue;
                }
                dist = currentDist;
                target = player;
                DeadAura.targets.add(player);
            }
        }
        return target;
    }
    
    public boolean canAttack(final class_1657 nigger) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getstatic       com/mrzak34/thunderhack/module/modules/combat/DeadAura.mc:Lnet/minecraft/class_310;
        //     4: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //     7: if_acmpne       12
        //    10: iconst_0       
        //    11: ireturn        
        //    12: aload_1         /* nigger */
        //    13: invokevirtual   net/minecraft/class_1657.method_5477:()Lnet/minecraft/class_2561;
        //    16: invokeinterface net/minecraft/class_2561.getString:()Ljava/lang/String;
        //    21: invokestatic    invokestatic   !!! ERROR
        //    24: ifeq            29
        //    27: iconst_0       
        //    28: ireturn        
        //    29: getstatic       com/mrzak34/thunderhack/module/modules/combat/DeadAura.mc:Lnet/minecraft/class_310;
        //    32: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //    35: aload_1         /* nigger */
        //    36: invokevirtual   net/minecraft/class_746.method_5739:(Lnet/minecraft/class_1297;)F
        //    39: f2d            
        //    40: aload_0         /* this */
        //    41: getfield        com/mrzak34/thunderhack/module/modules/combat/DeadAura.distance:Lcom/mrzak34/thunderhack/settings/NumberSetting;
        //    44: invokevirtual   com/mrzak34/thunderhack/settings/NumberSetting.getValue:()D
        //    47: dcmpl          
        //    48: ifgt            55
        //    51: iconst_1       
        //    52: goto            56
        //    55: iconst_0       
        //    56: ireturn        
        //    MethodParameters:
        //  Name    Flags  
        //  ------  -----
        //  nigger  
        //    StackMapTable: 00 04 0C 10 19 40 01
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Invalid BootstrapMethods attribute entry: 2 additional arguments required for method java/lang/invoke/StringConcatFactory.makeConcatWithConstants, but only 1 specified.
        //     at com.strobel.assembler.ir.Error.invalidBootstrapMethodEntry(Error.java:244)
        //     at com.strobel.assembler.ir.MetadataReader.readAttributeCore(MetadataReader.java:280)
        //     at com.strobel.assembler.metadata.ClassFileReader.readAttributeCore(ClassFileReader.java:261)
        //     at com.strobel.assembler.ir.MetadataReader.inflateAttributes(MetadataReader.java:439)
        //     at com.strobel.assembler.metadata.ClassFileReader.visitAttributes(ClassFileReader.java:1134)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:439)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:377)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveType(MetadataSystem.java:129)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveCore(MetadataSystem.java:81)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:104)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:616)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:128)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:626)
        //     at com.strobel.assembler.metadata.MethodReference.resolve(MethodReference.java:177)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2438)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferBinaryExpression(TypeAnalysis.java:2104)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1531)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1551)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:109)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private class_1657 getHealthDown() {
        DeadAura.targets.clear();
        double health = 36.0;
        class_1657 target = null;
        for (final class_1297 object : DeadAura.mc.field_1687.method_18112()) {
            if (object instanceof class_1657) {
                final class_1657 player = (class_1657)object;
                if (!this.canAttack(player)) {
                    continue;
                }
                final double currenhealth = player.method_6032();
                if (currenhealth > health) {
                    continue;
                }
                health = currenhealth;
                target = player;
                DeadAura.targets.add(player);
            }
        }
        return target;
    }
    
    public class_1297 h() {
        class_1297 entity = null;
        if (this.rotationMode.getMode().equals("matrix") || this.rotationMode.getMode().equals("sunrise") || this.rotationMode.getMode().equals("aac") || this.rotationMode.getMode().equals("static")) {
            float f2 = this.serverYaw;
            float f3 = this.serverPitch;
            if (!this.look.isEnabled() && !this.rotationMode.getMode().equals("aac")) {
                f2 = efUtil.d();
                f3 = efUtil.e();
            }
            entity = RaytraceUtil.b((float)(DeadAura.mc.field_1724.method_5624() ? (this.distance.getValue() - 0.1) : this.distance.getValue()), f2, f3);
        }
        return entity;
    }
    
    public void z() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getstatic       com/mrzak34/thunderhack/module/modules/combat/DeadAura.target:Lnet/minecraft/class_1657;
        //     4: invokevirtual   com/mrzak34/thunderhack/module/modules/combat/DeadAura.g:(Lnet/minecraft/class_1657;)Z
        //     7: ifne            14
        //    10: aload_0         /* this */
        //    11: invokevirtual   com/mrzak34/thunderhack/module/modules/combat/DeadAura.getTarget:()V
        //    14: getstatic       com/mrzak34/thunderhack/module/modules/combat/DeadAura.target:Lnet/minecraft/class_1657;
        //    17: ifnonnull       27
        //    20: ldc_w           "target is null"
        //    23: invokestatic    invokestatic   !!! ERROR
        //    26: return         
        //    27: aload_0         /* this */
        //    28: getfield        com/mrzak34/thunderhack/module/modules/combat/DeadAura.onlyWeapon:Lcom/mrzak34/thunderhack/settings/BooleanSetting;
        //    31: invokevirtual   com/mrzak34/thunderhack/settings/BooleanSetting.isEnabled:()Z
        //    34: ifeq            44
        //    37: invokestatic    com/mrzak34/thunderhack/module/modules/combat/DeadAura.ete:()Z
        //    40: ifne            44
        //    43: return         
        //    44: getstatic       com/mrzak34/thunderhack/module/modules/combat/DeadAura.mc:Lnet/minecraft/class_310;
        //    47: getfield        net/minecraft/class_310.field_1724:Lnet/minecraft/class_746;
        //    50: astore_1        /* entityPlayerSP */
        //    51: aload_0         /* this */
        //    52: getstatic       com/mrzak34/thunderhack/module/modules/combat/DeadAura.target:Lnet/minecraft/class_1657;
        //    55: invokevirtual   com/mrzak34/thunderhack/module/modules/combat/DeadAura.e:(Lnet/minecraft/class_1657;)Z
        //    58: ifne            68
        //    61: ldc_w           "target not e"
        //    64: invokestatic    invokestatic   !!! ERROR
        //    67: return         
        //    68: aload_0         /* this */
        //    69: getfield        com/mrzak34/thunderhack/module/modules/combat/DeadAura.cooldown:Lcom/mrzak34/thunderhack/settings/BooleanSetting;
        //    72: invokevirtual   com/mrzak34/thunderhack/settings/BooleanSetting.isEnabled:()Z
        //    75: ifeq            176
        //    78: iconst_0       
        //    79: istore_3        /* crit */
        //    80: aload_0         /* this */
        //    81: getfield        com/mrzak34/thunderhack/module/modules/combat/DeadAura.autocrit:Lcom/mrzak34/thunderhack/settings/BooleanSetting;
        //    84: invokevirtual   com/mrzak34/thunderhack/settings/BooleanSetting.isEnabled:()Z
        //    87: ifeq            98
        //    90: iload_3         /* crit */
        //    91: ifeq            98
        //    94: fconst_1       
        //    95: goto            101
        //    98: ldc_w           0.92
        //   101: dup            
        //   102: fstore_2        /* f2 */
        //   103: fstore          f3
        //   105: aload_1         /* entityPlayerSP */
        //   106: fconst_0       
        //   107: invokevirtual   net/minecraft/class_1657.method_7261:(F)F
        //   110: fload_2         /* f2 */
        //   111: fcmpl          
        //   112: iflt            126
        //   115: aload_0         /* this */
        //   116: getstatic       com/mrzak34/thunderhack/module/modules/combat/DeadAura.target:Lnet/minecraft/class_1657;
        //   119: invokevirtual   com/mrzak34/thunderhack/module/modules/combat/DeadAura.c:(Lnet/minecraft/class_1657;)V
        //   122: aload_1         /* entityPlayerSP */
        //   123: invokevirtual   net/minecraft/class_1657.method_7350:()V
        //   126: aload_0         /* this */
        //   127: getfield        com/mrzak34/thunderhack/module/modules/combat/DeadAura.P:Z
        //   130: ifeq            173
        //   133: aload_0         /* this */
        //   134: getfield        com/mrzak34/thunderhack/module/modules/combat/DeadAura.H:Lcom/mrzak34/thunderhack/util/TimerUtil;
        //   137: ldc2_w          800
        //   140: invokevirtual   com/mrzak34/thunderhack/util/TimerUtil.passedMs:(J)Z
        //   143: ifeq            173
        //   146: aload_0         /* this */
        //   147: getstatic       com/mrzak34/thunderhack/module/modules/combat/DeadAura.target:Lnet/minecraft/class_1657;
        //   150: invokevirtual   com/mrzak34/thunderhack/module/modules/combat/DeadAura.c:(Lnet/minecraft/class_1657;)V
        //   153: aload_1         /* entityPlayerSP */
        //   154: invokevirtual   net/minecraft/class_1657.method_7350:()V
        //   157: invokestatic    com/mrzak34/thunderhack/util/efUtil.l:()V
        //   160: aload_0         /* this */
        //   161: iconst_0       
        //   162: putfield        com/mrzak34/thunderhack/module/modules/combat/DeadAura.P:Z
        //   165: aload_0         /* this */
        //   166: getfield        com/mrzak34/thunderhack/module/modules/combat/DeadAura.H:Lcom/mrzak34/thunderhack/util/TimerUtil;
        //   169: invokevirtual   com/mrzak34/thunderhack/util/TimerUtil.reset:()Lcom/mrzak34/thunderhack/util/TimerUtil;
        //   172: pop            
        //   173: goto            241
        //   176: aload_0         /* this */
        //   177: getfield        com/mrzak34/thunderhack/module/modules/combat/DeadAura.minCPS:Lcom/mrzak34/thunderhack/settings/NumberSetting;
        //   180: invokevirtual   com/mrzak34/thunderhack/settings/NumberSetting.getValue:()D
        //   183: d2i            
        //   184: aload_0         /* this */
        //   185: aload_0         /* this */
        //   186: getfield        com/mrzak34/thunderhack/module/modules/combat/DeadAura.minCPS:Lcom/mrzak34/thunderhack/settings/NumberSetting;
        //   189: invokevirtual   com/mrzak34/thunderhack/settings/NumberSetting.getValue:()D
        //   192: d2i            
        //   193: aload_0         /* this */
        //   194: getfield        com/mrzak34/thunderhack/module/modules/combat/DeadAura.maxCPS:Lcom/mrzak34/thunderhack/settings/NumberSetting;
        //   197: invokevirtual   com/mrzak34/thunderhack/settings/NumberSetting.getValue:()D
        //   200: d2i            
        //   201: invokevirtual   com/mrzak34/thunderhack/module/modules/combat/DeadAura.costyl:(II)I
        //   204: invokestatic    org/apache/commons/lang3/RandomUtils.nextInt:(II)I
        //   207: istore_2        /* n2 */
        //   208: aload_0         /* this */
        //   209: pop            
        //   210: getstatic       com/mrzak34/thunderhack/module/modules/combat/DeadAura.D:Lcom/mrzak34/thunderhack/util/TimerUtil;
        //   213: sipush          1000
        //   216: iload_2         /* n2 */
        //   217: idiv           
        //   218: i2l            
        //   219: invokevirtual   com/mrzak34/thunderhack/util/TimerUtil.passedMs:(J)Z
        //   222: ifeq            241
        //   225: aload_0         /* this */
        //   226: getstatic       com/mrzak34/thunderhack/module/modules/combat/DeadAura.target:Lnet/minecraft/class_1657;
        //   229: invokevirtual   com/mrzak34/thunderhack/module/modules/combat/DeadAura.c:(Lnet/minecraft/class_1657;)V
        //   232: aload_0         /* this */
        //   233: pop            
        //   234: getstatic       com/mrzak34/thunderhack/module/modules/combat/DeadAura.D:Lcom/mrzak34/thunderhack/util/TimerUtil;
        //   237: invokevirtual   com/mrzak34/thunderhack/util/TimerUtil.reset:()Lcom/mrzak34/thunderhack/util/TimerUtil;
        //   240: pop            
        //   241: aload_0         /* this */
        //   242: getstatic       com/mrzak34/thunderhack/module/modules/combat/DeadAura.target:Lnet/minecraft/class_1657;
        //   245: invokevirtual   com/mrzak34/thunderhack/module/modules/combat/DeadAura.g:(Lnet/minecraft/class_1657;)Z
        //   248: ifne            270
        //   251: aload_0         /* this */
        //   252: getfield        com/mrzak34/thunderhack/module/modules/combat/DeadAura.targetMode:Lcom/mrzak34/thunderhack/settings/ModeSetting;
        //   255: invokevirtual   com/mrzak34/thunderhack/settings/ModeSetting.getMode:()Ljava/lang/String;
        //   258: ldc             "switch"
        //   260: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   263: ifeq            270
        //   266: aload_0         /* this */
        //   267: invokevirtual   com/mrzak34/thunderhack/module/modules/combat/DeadAura.getTarget:()V
        //   270: return         
        //    StackMapTable: 00 0B 0E 0C 10 FC 00 17 07 01 86 FD 00 1D 00 01 42 02 FF 00 18 00 05 07 00 02 07 01 86 02 01 02 00 00 F8 00 2E 02 FB 00 40 1C
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Invalid BootstrapMethods attribute entry: 2 additional arguments required for method java/lang/invoke/StringConcatFactory.makeConcatWithConstants, but only 1 specified.
        //     at com.strobel.assembler.ir.Error.invalidBootstrapMethodEntry(Error.java:244)
        //     at com.strobel.assembler.ir.MetadataReader.readAttributeCore(MetadataReader.java:280)
        //     at com.strobel.assembler.metadata.ClassFileReader.readAttributeCore(ClassFileReader.java:261)
        //     at com.strobel.assembler.ir.MetadataReader.inflateAttributes(MetadataReader.java:439)
        //     at com.strobel.assembler.metadata.ClassFileReader.visitAttributes(ClassFileReader.java:1134)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:439)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:377)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveType(MetadataSystem.java:129)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveCore(MetadataSystem.java:81)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:104)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:616)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:128)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:626)
        //     at com.strobel.assembler.metadata.MethodReference.resolve(MethodReference.java:177)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2438)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:109)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public int costyl(final int a, final int b) {
        if (a >= b) {
            return b - 2;
        }
        return a;
    }
    
    public void c(final class_1657 entityLivingBase) {
        final class_1657 entityPlayerSP = (class_1657)DeadAura.mc.field_1724;
        if (this.teleport.isEnabled()) {
            entityPlayerSP.method_5814(entityLivingBase.method_23317(), entityPlayerSP.method_23318(), entityLivingBase.method_23321());
        }
        if (this.shieldBlock.isEnabled() && etf() && this.M) {
            return;
        }
        if (this.stopSprinting.isEnabled()) {
            DeadAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2848((class_1297)DeadAura.mc.field_1724, class_2848.class_2849.field_12985));
        }
        if (this.rotationMode.getMode().equals("aac") && DeadAura.target != null) {
            final float f2 = 2.2f - RandomUtils.nextFloat();
            DeadAura.K = DeadMathUtil.a((class_1297)DeadAura.target, this.getHitbox(), f2);
            DeadAura.mc.field_1724.method_36456(DeadAura.K[0]);
            DeadAura.mc.field_1724.method_36457(DeadAura.K[1]);
        }
        class_1657 entityLivingBase2 = entityLivingBase;
        if (this.rotationMode.getMode().equals("matrix") || this.rotationMode.getMode().equals("sunrise") || this.rotationMode.getMode().equals("aac") || (this.rotationMode.getMode().equals("static") && (this.rayrace.isEnabled() || this.rotationMode.getMode().equals("sunrise")))) {
            final class_1297 entity = this.h();
            if (entity == null) {
                return;
            }
            entityLivingBase2 = (class_1657)entity;
        }
        if (this.shieldBreak.isEnabled() && this.shieldBreakMode.getMode().equals("old")) {
            this.a(entityLivingBase2);
        }
        final boolean bl2;
        final boolean bl = bl2 = (entityPlayerSP.field_6017 > 0.0f && !entityPlayerSP.method_24828() && !entityPlayerSP.method_21754() && !entityPlayerSP.method_5799() && entityPlayerSP.method_5854() == null);
        if (bl) {}
        DeadAura.mc.method_1562().method_2883((class_2596)class_2824.method_34206((class_1297)entityLivingBase2, DeadAura.mc.field_1724.method_5715()));
        if (this.swing.isEnabled()) {
            DeadAura.mc.field_1724.method_6104(class_1268.field_5808);
        }
        else {
            DeadAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2879(class_1268.field_5808));
        }
        if (this.shieldBreak.isEnabled() && this.shieldBreakMode.getMode().equals("new")) {
            this.b(entityLivingBase2);
        }
        if (this.O != -2) {
            DeadAura.mc.field_1724.field_3944.method_2883((class_2596)new class_2868(this.O));
            this.O = -2;
        }
        this.M = true;
    }
    
    public Hitbox getHitbox() {
        if (this.aimMode.getMode().equals("head")) {
            return Hitbox.HEAD;
        }
        if (this.aimMode.getMode().equals("body")) {
            return Hitbox.CHEST;
        }
        if (this.aimMode.getMode().equals("legs")) {
            return Hitbox.TIGHS;
        }
        if (this.aimMode.getMode().equals("all")) {
            final int n2 = RandomUtils.nextInt(0, 3);
            return Hitbox.values()[n2];
        }
        return Hitbox.CHEST;
    }
    
    public boolean d(final class_1657 entityLivingBase) {
        return (this.targetPrio.getMode().equals("closest") && eQc(entityLivingBase, DeadAura.target)) || (this.targetPrio.getMode().equals("health") && eQa(entityLivingBase, DeadAura.target)) || (this.targetPrio.getMode().equals("equip") && eQb(entityLivingBase, DeadAura.target));
    }
    
    public static boolean eQa(final class_1657 entityLivingBase, final class_1657 entityLivingBase2) {
        return entityLivingBase2 == null || entityLivingBase.method_6032() < entityLivingBase2.method_6032();
    }
    
    public static boolean eQb(final class_1657 entityLivingBase, final class_1657 entityLivingBase2) {
        return entityLivingBase2 == null || !(entityLivingBase instanceof class_1657) || !(entityLivingBase2 instanceof class_1657) || etd(entityLivingBase) > etd(entityLivingBase2);
    }
    
    public static int etd(final class_1657 entityPlayer) {
        final ArrayList<class_1799> arrayList = new ArrayList<class_1799>();
        final class_1799 itemStack = entityPlayer.method_6047();
        final class_1799 itemStack2 = (class_1799)entityPlayer.method_31548().field_7548.get(0);
        final class_1799 itemStack3 = (class_1799)entityPlayer.method_31548().field_7548.get(1);
        final class_1799 itemStack4 = (class_1799)entityPlayer.method_31548().field_7548.get(2);
        final class_1799 itemStack5 = (class_1799)entityPlayer.method_31548().field_7548.get(3);
        final class_1799[] array;
        final class_1799[] itemStackArray = array = new class_1799[] { itemStack, itemStack5, itemStack4, itemStack3, itemStack2 };
        for (final class_1799 itemStack6 : array) {
            if (!isEmpty(itemStack6)) {
                arrayList.add(itemStack6);
            }
        }
        return arrayList.size();
    }
    
    public static boolean eQc(final class_1657 entityLivingBase, final class_1657 entityLivingBase2) {
        return entityLivingBase2 == null || DeadAura.mc.field_1724.method_5739((class_1297)entityLivingBase) < DeadAura.mc.field_1724.method_5739((class_1297)entityLivingBase2);
    }
    
    public boolean e(final class_1657 entityLivingBase) {
        return entityLivingBase.method_5739((class_1297)DeadAura.mc.field_1724) <= (float)(DeadAura.mc.field_1724.method_5624() ? (this.distance.getValue() - 0.1) : this.distance.getValue()) - this.L;
    }
    
    public boolean f(final class_1657 entityLivingBase) {
        return entityLivingBase.method_5739((class_1297)DeadAura.mc.field_1724) <= (float)(DeadAura.mc.field_1724.method_5624() ? (this.distance.getValue() - 0.1) : this.distance.getValue()) + this.L;
    }
    
    public boolean a(final class_1657 entityLivingBase, final float f2) {
        return entityLivingBase.method_5739((class_1297)DeadAura.mc.field_1724) <= (float)(DeadAura.mc.field_1724.method_5624() ? (this.distance.getValue() - 0.1) : this.distance.getValue()) + f2;
    }
    
    public boolean g(final class_1657 entityLivingBase) {
        return entityLivingBase != null && !entityLivingBase.method_29504() && entityLivingBase.field_6213 <= 0 && (this.f(entityLivingBase) || !this.targetMode.getMode().equals("switch")) && (this.a(entityLivingBase, 2.0f) || !this.targetMode.getMode().equals("single"));
    }
    
    public boolean h(final class_1657 entityLivingBase) {
        return !(entityLivingBase instanceof class_1657) && !checkent(entityLivingBase) && entityLivingBase != DeadAura.mc.field_1724 && !entityLivingBase.method_29504() && entityLivingBase.field_6213 <= 0 && checkent(entityLivingBase) && checkent(entityLivingBase) && this.f(entityLivingBase) && checkent(entityLivingBase) && DeadMathUtil.a((class_1297)entityLivingBase, this.fov.getValue()) && (this.throughWalls.isEnabled() || DeadAura.mc.field_1724.method_6057((class_1297)entityLivingBase)) && this.d(entityLivingBase);
    }
    
    public static boolean checkent(final class_1657 entityLivingBase) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: instanceof      Lnet/minecraft/class_1657;
        //     4: ifeq            22
        //     7: aload_0         /* entityLivingBase */
        //     8: invokevirtual   net/minecraft/class_1657.method_5477:()Lnet/minecraft/class_2561;
        //    11: invokeinterface net/minecraft/class_2561.getString:()Ljava/lang/String;
        //    16: invokestatic    invokestatic   !!! ERROR
        //    19: ifne            26
        //    22: iconst_1       
        //    23: goto            27
        //    26: iconst_0       
        //    27: ireturn        
        //    MethodParameters:
        //  Name              Flags  
        //  ----------------  -----
        //  entityLivingBase  
        //    StackMapTable: 00 03 16 03 40 01
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Invalid BootstrapMethods attribute entry: 2 additional arguments required for method java/lang/invoke/StringConcatFactory.makeConcatWithConstants, but only 1 specified.
        //     at com.strobel.assembler.ir.Error.invalidBootstrapMethodEntry(Error.java:244)
        //     at com.strobel.assembler.ir.MetadataReader.readAttributeCore(MetadataReader.java:280)
        //     at com.strobel.assembler.metadata.ClassFileReader.readAttributeCore(ClassFileReader.java:261)
        //     at com.strobel.assembler.ir.MetadataReader.inflateAttributes(MetadataReader.java:439)
        //     at com.strobel.assembler.metadata.ClassFileReader.visitAttributes(ClassFileReader.java:1134)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:439)
        //     at com.strobel.assembler.metadata.ClassFileReader.readClass(ClassFileReader.java:377)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveType(MetadataSystem.java:129)
        //     at com.strobel.assembler.metadata.MetadataSystem.resolveCore(MetadataSystem.java:81)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:104)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:616)
        //     at com.strobel.assembler.metadata.MetadataResolver.resolve(MetadataResolver.java:128)
        //     at com.strobel.assembler.metadata.CoreMetadataFactory$UnresolvedType.resolve(CoreMetadataFactory.java:626)
        //     at com.strobel.assembler.metadata.MethodReference.resolve(MethodReference.java:177)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferCall(TypeAnalysis.java:2438)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1029)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferBinaryExpression(TypeAnalysis.java:2104)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1531)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:778)
        //     at com.strobel.decompiler.ast.TypeAnalysis.doInferTypeForExpression(TypeAnalysis.java:1551)
        //     at com.strobel.decompiler.ast.TypeAnalysis.inferTypeForExpression(TypeAnalysis.java:803)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:672)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:655)
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:365)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:109)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    static {
        DeadAura.D = new TimerUtil();
        DeadAura.K = new float[2];
        DeadAura.targets = new ArrayList<class_1657>();
    }
    
    public enum Hitbox
    {
        HEAD, 
        CHEST, 
        TIGHS;
        
        private static /* synthetic */ Hitbox[] $values() {
            return new Hitbox[] { Hitbox.HEAD, Hitbox.CHEST, Hitbox.TIGHS };
        }
        
        static {
            $VALUES = $values();
        }
    }
}
