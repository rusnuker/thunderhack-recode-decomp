//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import net.minecraft.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.mrzak34.thunderhack.module.modules.render.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ class_828.class })
class MixinEnchantingTableBlockEntityRenderer
{
    @Inject(method = { "render*" }, at = { @At("HEAD") }, cancellable = true)
    public void render(final CallbackInfo ci) {
        if (NoRender.getInstance().isToggled() && NoRender.enchantingTable.isEnabled()) {
            ci.cancel();
        }
    }
}
