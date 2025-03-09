package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.hbm.entity.mob.siege.EntitySiegeCraft;
import com.hbm.entity.mob.siege.SiegeTier;
import com.hbm.lib.RefStrings;
import com.hbm.render.entity.mob.RenderSiegeCraft;
import com.kotmatross.shadersfixer.Utils;

@Mixin(value = RenderSiegeCraft.class, priority = 999)
public class MixinRenderSiegeCraft {

    @Shadow(remap = false)
    protected ResourceLocation getEntityTexture(EntitySiegeCraft entity) {
        SiegeTier tier = entity.getTier();
        return new ResourceLocation(RefStrings.MODID + ":textures/entity/siege_craft_" + tier.name + ".png");
    }

    @Inject(
        method = "func_76986_a",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/opengl/GL11;glColor3f(FFF)V",
            ordinal = 0,
            shift = At.Shift.BEFORE),
        remap = false)
    public void func_76986_a(Entity entity, double x, double y, double z, float f0, float f1, CallbackInfo ci) {
        Utils.Fix();
    }

    @Inject(
        method = "func_76986_a",
        at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glEnable(I)V", ordinal = 1, shift = At.Shift.AFTER),
        remap = false)
    public void func_76986_a2(Entity entity, double x, double y, double z, float f0, float f1, CallbackInfo ci) {
        Minecraft.getMinecraft().renderEngine.bindTexture(getEntityTexture((EntitySiegeCraft) entity));
    }
}
