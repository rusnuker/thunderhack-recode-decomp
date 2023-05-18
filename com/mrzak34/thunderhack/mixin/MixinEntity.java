//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import net.minecraft.*;
import com.mrzak34.thunderhack.module.modules.combat.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ class_1297.class })
public class MixinEntity
{
    @Redirect(method = { "pushAwayFrom" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;addVelocity(DDD)V"))
    public void onPushAwayFrom(final class_1297 entity, final double deltaX, final double deltaY, final double deltaZ) {
        if (!Velocity.noPush.isEnabled()) {
            return;
        }
        entity.method_5750(entity.method_18798().field_1352 + deltaX, entity.method_18798().field_1351 + deltaY, entity.method_18798().field_1350 + deltaZ);
    }
}
