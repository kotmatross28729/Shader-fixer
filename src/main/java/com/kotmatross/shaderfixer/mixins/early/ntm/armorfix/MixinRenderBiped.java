package com.kotmatross.shaderfixer.mixins.early.ntm.armorfix;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.ShaderFixer;
import com.kotmatross.shaderfixer.utils.NTMUtils_WRAPPER;
import com.llamalad7.mixinextras.sugar.Local;

@Mixin(value = RenderBiped.class, priority = 999)
public class MixinRenderBiped {

    @Shadow
    public ModelBiped modelBipedMain;

    /**
     * Allows non-player mobs to render akimbo weapons in their hands
     */
    @Inject(method = "renderEquippedItems(Lnet/minecraft/entity/EntityLiving;F)V", at = @At(value = "HEAD"))
    public void renderAkimbo(CallbackInfo ci, @Local(argsOnly = true) EntityLiving entity) {
        if (ShaderFixer.IS_HBM_NTM_PRESENT) {
            ItemStack held = entity.getHeldItem();
            if (held != null) {
                IItemRenderer customRenderer = MinecraftForgeClient
                    .getItemRenderer(held, IItemRenderer.ItemRenderType.EQUIPPED);

                if (NTMUtils_WRAPPER.isAkimboRenderer(customRenderer)) {
                    GL11.glPushMatrix();

                    this.modelBipedMain.bipedLeftArm.isHidden = false;
                    this.modelBipedMain.bipedLeftArm.postRender(0.0625F);

                    // vanilla bullshit
                    GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);
                    float scale = 0.375F;
                    GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
                    GL11.glScalef(scale, scale, scale);
                    GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
                    GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
                    // forge bullshit

                    GL11.glEnable(GL12.GL_RESCALE_NORMAL);
                    GL11.glTranslatef(0.0F, -0.3F, 0.0F);
                    GL11.glScalef(1.5F, 1.5F, 1.5F);
                    GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
                    GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);

                    NTMUtils_WRAPPER.akimboSetupNRender(customRenderer, held);

                    GL11.glDisable(GL12.GL_RESCALE_NORMAL);

                    GL11.glPopMatrix();
                }
            }
        }
    }

}
