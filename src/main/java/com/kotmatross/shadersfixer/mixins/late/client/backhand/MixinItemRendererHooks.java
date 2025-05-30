package com.kotmatross.shadersfixer.mixins.late.client.backhand;

import static org.spongepowered.asm.mixin.injection.At.Shift.BEFORE;

import net.minecraft.client.renderer.ItemRenderer;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shadersfixer.shrimp.ShitUtils;
import com.kotmatross.shadersfixer.shrimp.nonsense.DoubleFuckingCursedAward;
import com.kotmatross.shadersfixer.shrimp.nonsense.FuckingCursed;

import xonin.backhand.client.hooks.ItemRendererHooks;
import xonin.backhand.client.utils.BackhandRenderHelper;

@FuckingCursed // I'm as dumb as a fucking brick
@DoubleFuckingCursedAward
@Mixin(value = ItemRendererHooks.class, priority = 999)
public class MixinItemRendererHooks {

    // TODO: 3rd person (BackhandRenderHelper)

    /**
     * Adds (should), compatibility with NTM weapon renderer in the main hand (gun in the right, something else in the
     * left)
     * NTM skips some GL calls, and also adds its own.
     * Because the backhand uses vanilla renderItemInFirstPerson, while we render the weapon,
     * shaders_fixer$checkVibe will be true when rendering, which will trigger all our "special" transformations on a
     * regular item....
     * All this shit does is "flip" special transformations before offhand rendering
     * (for example, a 180 Y rotation here will be a -180 Y rotation, or skipped NTM scale call here will be added,
     * etc., etc.)
     * <p>
     * Dirty as fuck hack, but I don't give a fuck WHAT else can be done
     * <p>
     * Oh, fuck, there's also a FUCKING GL LEAK in the 3rd person... nice
     * (8 f words, congratulations)
     */

    @Inject(
        method = "renderOffhandReturn",
        at = @At(
            value = "INVOKE",
            target = "xonin/backhand/api/core/BackhandUtils.useOffhandItem (Lnet/minecraft/entity/player/EntityPlayer;ZLjava/lang/Runnable;)V",
            shift = BEFORE),
        remap = false)
    private static void renderOffhandReturnANTITRANSFORMER(float frame, CallbackInfo ci) {
        if (ShitUtils.shaders_fixer$checkVibe()) {
            ItemRenderer itemRenderer = BackhandRenderHelper.itemRenderer;
            float f1 = itemRenderer.prevEquippedProgress
                + (itemRenderer.equippedProgress - itemRenderer.prevEquippedProgress) * frame;
            float f13 = 0.8F;
            GL11.glTranslatef(0.7F * f13, -0.65F * f13 - (1.0F - f1) * 0.6F, -0.9F * f13);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(0.4F, 0.4F, 0.4F);
            GL11.glRotated(-180, 0, 1, 0);
        }
    }
}
