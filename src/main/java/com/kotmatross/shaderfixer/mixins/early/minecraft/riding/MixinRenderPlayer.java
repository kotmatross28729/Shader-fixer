package com.kotmatross.shaderfixer.mixins.early.minecraft.riding;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.config.ShaderFixerConfig;

@Mixin(value = RenderPlayer.class, priority = 1059)
public class MixinRenderPlayer {

    @Shadow
    public ModelBiped modelBipedMain;

    @Inject(method = "renderFirstPersonArm", at = @At(value = "HEAD"))
    protected void renderFirstPersonArm(EntityPlayer player, CallbackInfo ci) {
        this.modelBipedMain.isRiding = ShaderFixerConfig.V_RIDING_HAND_ROTATION_FIX && player.isRiding(); // Peak
                                                                                                          // mjoang
                                                                                                          // coding
    }

}
