/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.systems.RenderSystem
 *  net.minecraft.class_310
 *  net.minecraft.class_4184
 *  net.minecraft.class_4587
 *  net.minecraft.class_757
 *  net.minecraft.class_761
 *  net.minecraft.class_765
 *  org.joml.Matrix4f
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package com.mrzak34.thunderhack.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mrzak34.thunderhack.Main;
import com.mrzak34.thunderhack.event.events.RenderWorldEvent;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.minecraft.class_761;
import net.minecraft.class_765;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_761.class})
public class MixinWorldRenderer {
    @Inject(method={"render"}, at={@At(value="RETURN")})
    private void render_return(class_4587 matrices, float tickDelta, long limitTime, boolean renderBlockOutline, class_4184 camera, class_757 gameRenderer, class_765 lightmapTextureManager, Matrix4f positionMatrix, CallbackInfo callback) {
        RenderSystem.clear((int)256, (boolean)class_310.field_1703);
        RenderWorldEvent.Post event = new RenderWorldEvent.Post(tickDelta, matrices);
        Main.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callback.cancel();
        }
    }
}

