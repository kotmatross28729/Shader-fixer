package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.main.ResourceManager;
import com.hbm.render.tileentity.RenderRadarScreen;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderRadarScreen.class, priority = 999)
public class MixinRenderRadarScreen {
    @Inject(method = "func_147500_a",
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDepthMask(Z)V", ordinal = 0, shift = At.Shift.AFTER), remap = false)
    public void func_147500_a(TileEntity tileEntity, double x, double y, double z, float f, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(method = "func_147500_a",
        at = @At(value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glDepthMask(Z)V", ordinal = 1, shift = At.Shift.AFTER), remap = false)
    public void func_147500_a2(TileEntity tileEntity, double x, double y, double z, float f, CallbackInfo ci) {
        Minecraft.getMinecraft().renderEngine.bindTexture(ResourceManager.radar_screen_tex);
    }
}
