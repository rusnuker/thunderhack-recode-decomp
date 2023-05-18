//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.manager;

import net.minecraft.*;
import com.mrzak34.thunderhack.util.*;
import com.mrzak34.thunderhack.event.events.*;
import com.google.common.eventbus.*;
import com.mrzak34.thunderhack.module.*;
import java.util.*;

public class TotemPopManager implements Util
{
    static HashMap<class_1657, Integer> popcount;
    static HashMap<class_1657, TimerUtil> popDelay;
    
    @Subscribe
    public void onPacketReceive(final PacketEvent.Receive event) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   com/mrzak34/thunderhack/event/events/PacketEvent$Receive.getPacket:()Lnet/minecraft/class_2596;
        //     4: instanceof      Lnet/minecraft/class_2663;
        //     7: ifeq            207
        //    10: aload_1         /* event */
        //    11: invokevirtual   com/mrzak34/thunderhack/event/events/PacketEvent$Receive.getPacket:()Lnet/minecraft/class_2596;
        //    14: checkcast       Lnet/minecraft/class_2663;
        //    17: astore_2        /* packet */
        //    18: aload_2         /* packet */
        //    19: invokevirtual   net/minecraft/class_2663.method_11470:()B
        //    22: bipush          35
        //    24: if_icmpne       207
        //    27: aload_2         /* packet */
        //    28: getstatic       com/mrzak34/thunderhack/manager/TotemPopManager.mc:Lnet/minecraft/class_310;
        //    31: getfield        net/minecraft/class_310.field_1687:Lnet/minecraft/class_638;
        //    34: invokevirtual   net/minecraft/class_2663.method_11469:(Lnet/minecraft/class_1937;)Lnet/minecraft/class_1297;
        //    37: instanceof      Lnet/minecraft/class_1657;
        //    40: ifeq            207
        //    43: aload_2         /* packet */
        //    44: getstatic       com/mrzak34/thunderhack/manager/TotemPopManager.mc:Lnet/minecraft/class_310;
        //    47: getfield        net/minecraft/class_310.field_1687:Lnet/minecraft/class_638;
        //    50: invokevirtual   net/minecraft/class_2663.method_11469:(Lnet/minecraft/class_1937;)Lnet/minecraft/class_1297;
        //    53: checkcast       Lnet/minecraft/class_1657;
        //    56: astore_3        /* player */
        //    57: getstatic       com/mrzak34/thunderhack/manager/TotemPopManager.popcount:Ljava/util/HashMap;
        //    60: aload_3         /* player */
        //    61: invokevirtual   java/util/HashMap.containsKey:(Ljava/lang/Object;)Z
        //    64: ifeq            141
        //    67: getstatic       com/mrzak34/thunderhack/manager/TotemPopManager.popDelay:Ljava/util/HashMap;
        //    70: aload_3         /* player */
        //    71: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    74: checkcast       Lcom/mrzak34/thunderhack/util/TimerUtil;
        //    77: ldc2_w          50
        //    80: invokevirtual   com/mrzak34/thunderhack/util/TimerUtil.passedMs:(J)Z
        //    83: ifne            87
        //    86: return         
        //    87: getstatic       com/mrzak34/thunderhack/manager/TotemPopManager.popcount:Ljava/util/HashMap;
        //    90: aload_3         /* player */
        //    91: getstatic       com/mrzak34/thunderhack/manager/TotemPopManager.popcount:Ljava/util/HashMap;
        //    94: aload_3         /* player */
        //    95: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    98: checkcast       Ljava/lang/Integer;
        //   101: invokevirtual   java/lang/Integer.intValue:()I
        //   104: iconst_1       
        //   105: iadd           
        //   106: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   109: invokevirtual   java/util/HashMap.replace:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   112: pop            
        //   113: invokestatic    com/mrzak34/thunderhack/module/modules/client/Notifications.getInstance:()Lcom/mrzak34/thunderhack/module/modules/client/Notifications;
        //   116: invokevirtual   com/mrzak34/thunderhack/module/modules/client/Notifications.isToggled:()Z
        //   119: ifeq            207
        //   122: invokestatic    com/mrzak34/thunderhack/module/modules/client/Notifications.getInstance:()Lcom/mrzak34/thunderhack/module/modules/client/Notifications;
        //   125: getfield        com/mrzak34/thunderhack/module/modules/client/Notifications.pops:Lcom/mrzak34/thunderhack/settings/BooleanSetting;
        //   128: invokevirtual   com/mrzak34/thunderhack/settings/BooleanSetting.isEnabled:()Z
        //   131: ifeq            207
        //   134: aload_3         /* player */
        //   135: invokestatic    invokestatic   !!! ERROR
        //   138: goto            207
        //   141: getstatic       com/mrzak34/thunderhack/manager/TotemPopManager.popcount:Ljava/util/HashMap;
        //   144: aload_3         /* player */
        //   145: iconst_1       
        //   146: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   149: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   152: pop            
        //   153: getstatic       com/mrzak34/thunderhack/manager/TotemPopManager.popDelay:Ljava/util/HashMap;
        //   156: aload_3         /* player */
        //   157: new             Lcom/mrzak34/thunderhack/util/TimerUtil;
        //   160: dup            
        //   161: invokespecial   com/mrzak34/thunderhack/util/TimerUtil.<init>:()V
        //   164: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   167: pop            
        //   168: getstatic       com/mrzak34/thunderhack/manager/TotemPopManager.popDelay:Ljava/util/HashMap;
        //   171: aload_3         /* player */
        //   172: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   175: checkcast       Lcom/mrzak34/thunderhack/util/TimerUtil;
        //   178: invokevirtual   com/mrzak34/thunderhack/util/TimerUtil.reset:()Lcom/mrzak34/thunderhack/util/TimerUtil;
        //   181: pop            
        //   182: invokestatic    com/mrzak34/thunderhack/module/modules/client/Notifications.getInstance:()Lcom/mrzak34/thunderhack/module/modules/client/Notifications;
        //   185: invokevirtual   com/mrzak34/thunderhack/module/modules/client/Notifications.isToggled:()Z
        //   188: ifeq            207
        //   191: invokestatic    com/mrzak34/thunderhack/module/modules/client/Notifications.getInstance:()Lcom/mrzak34/thunderhack/module/modules/client/Notifications;
        //   194: getfield        com/mrzak34/thunderhack/module/modules/client/Notifications.pops:Lcom/mrzak34/thunderhack/settings/BooleanSetting;
        //   197: invokevirtual   com/mrzak34/thunderhack/settings/BooleanSetting.isEnabled:()Z
        //   200: ifeq            207
        //   203: aload_3         /* player */
        //   204: invokestatic    invokestatic   !!! ERROR
        //   207: return         
        //    MethodParameters:
        //  Name   Flags  
        //  -----  -----
        //  event  
        //    StackMapTable: 00 03 FD 00 57 07 00 21 07 00 35 35 F9 00 41
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
    
    public static int getPlayerPops(final class_1657 player) {
        if (TotemPopManager.popcount.containsKey(player)) {
            return TotemPopManager.popcount.get(player);
        }
        return 0;
    }
    
    public static void onTick() {
        if (Module.fullNullCheck()) {
            return;
        }
        for (final class_1657 player : TotemPopManager.mc.field_1687.method_18456()) {
            if (player.method_29504()) {
                TotemPopManager.popcount.replace(player, 0);
            }
        }
    }
    
    static {
        TotemPopManager.popcount = new HashMap<class_1657, Integer>();
        TotemPopManager.popDelay = new HashMap<class_1657, TimerUtil>();
    }
}
