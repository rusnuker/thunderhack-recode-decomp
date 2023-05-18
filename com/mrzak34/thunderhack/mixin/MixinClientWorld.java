//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import net.minecraft.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ class_638.class })
public abstract class MixinClientWorld
{
    @Shadow
    public abstract void method_32888(final class_5712 p0, final class_243 p1, final class_5712.class_7397 p2);
    
    @Inject(method = { "removeEntity" }, at = { @At(value = "RETURN", target = "Lnet/minecraft/client/world/ClientWorld;removeEntity(ILnet/minecraft/entity/Entity$RemovalReason;)V") }, cancellable = true)
    private void move(final int entityId, final class_1297.class_5529 removalReason, final CallbackInfo callback) {
    }
}
