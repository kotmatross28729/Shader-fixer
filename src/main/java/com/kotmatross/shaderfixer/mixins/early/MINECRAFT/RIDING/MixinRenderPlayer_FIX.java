package com.kotmatross.shaderfixer.mixins.early.MINECRAFT.RIDING;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderPlayer.class, priority = 1059)
public abstract class MixinRenderPlayer_FIX extends RendererLivingEntity {

    public MixinRenderPlayer_FIX(ModelBase p_i1261_1_, float p_i1261_2_) {
        super(p_i1261_1_, p_i1261_2_);
    }

    @Shadow
    public ModelBiped modelBipedMain;

    @Inject(method = "renderFirstPersonArm", at = @At(value = "HEAD"))
    protected void renderFirstPersonArm(EntityPlayer p_82441_1_, CallbackInfo ci) {
        this.modelBipedMain.isRiding = p_82441_1_.isRiding(); // Peak mjoang coding
    }

}
