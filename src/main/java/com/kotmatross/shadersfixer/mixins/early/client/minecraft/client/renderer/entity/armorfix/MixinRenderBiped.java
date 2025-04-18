package com.kotmatross.shadersfixer.mixins.early.client.minecraft.client.renderer.entity.armorfix;

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

import com.kotmatross.shadersfixer.asm.ShadersFixerLateMixins;

@Mixin(value = RenderBiped.class, priority = 999)
public class MixinRenderBiped {

    @Shadow
    public ModelBiped modelBipedMain;

    // Allows non-player mobs to render akimbo weapons in their hands

    @Inject(method = "renderEquippedItems", at = @At(value = "HEAD"))
    public void RenderAkimbo(EntityLiving p_77029_1_, float p_77029_2_, CallbackInfo ci) {
        ItemStack held = p_77029_1_.getHeldItem();
        if (held != null) {
            IItemRenderer customRenderer = MinecraftForgeClient
                .getItemRenderer(held, IItemRenderer.ItemRenderType.EQUIPPED);

            if (ShadersFixerLateMixins.handleVibe(customRenderer)) {
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

                ShadersFixerLateMixins.akimboSetupNRender(customRenderer, held);

                GL11.glDisable(GL12.GL_RESCALE_NORMAL);

                GL11.glPopMatrix();
            }
        }
    }

}
