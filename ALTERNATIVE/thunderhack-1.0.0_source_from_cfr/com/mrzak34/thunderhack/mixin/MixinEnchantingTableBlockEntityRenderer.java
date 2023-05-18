/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_828
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.mrzak34.thunderhack.mixin;

import com.mrzak34.thunderhack.module.modules.render.NoRender;
import net.minecraft.class_828;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_828.class})
class MixinEnchantingTableBlockEntityRenderer {
    MixinEnchantingTableBlockEntityRenderer() {
    }

    @Inject(method={"render*"}, at={@At(value="HEAD")}, cancellable=true)
    public void render(CallbackInfo ci) {
        if (NoRender.getInstance().isToggled() && NoRender.enchantingTable.isEnabled()) {
            ci.cancel();
        }
    }
}

