//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "E:\cocaine"!

//Decompiled by Procyon!

package com.mrzak34.thunderhack.mixin;

import org.spongepowered.asm.mixin.*;
import com.mrzak34.thunderhack.module.modules.movement.*;
import net.minecraft.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = { class_743.class }, priority = Integer.MAX_VALUE)
public abstract class MixinMovementInput extends class_744
{
    @Redirect(method = { "tick" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/option/KeyBinding;isPressed()Z"))
    public boolean isKeyPressed(final class_304 keyBinding) {
        if (class_310.method_1551().field_1724 != null && InventoryMove.getInstance().isToggled() && !(class_310.method_1551().field_1755 instanceof class_408) && class_310.method_1551().field_1755 != null && class_310.method_1551().field_1690.field_1832 != keyBinding) {
            return class_3675.method_15987(class_310.method_1551().method_22683().method_4490(), keyBinding.method_1429().method_1444());
        }
        return keyBinding.method_1434();
    }
}
