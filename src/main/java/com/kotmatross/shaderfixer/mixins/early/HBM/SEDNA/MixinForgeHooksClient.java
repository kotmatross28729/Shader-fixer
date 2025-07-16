package com.kotmatross.shaderfixer.mixins.early.HBM.SEDNA;

import static net.minecraftforge.client.IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON;

import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.kotmatross.shaderfixer.shrimp.Vibe;

/**
 * Undo unnecessary forge item rotations (for NTM gun fix)
 *
 * @author kotmatross
 */

@Mixin(value = ForgeHooksClient.class, priority = 1003)
public class MixinForgeHooksClient {

    @Inject(method = "renderEquippedItem", at = @At("HEAD"), cancellable = true, remap = false)
    private static void onRenderEquippedItem(IItemRenderer.ItemRenderType type, IItemRenderer customRenderer,
        RenderBlocks renderBlocks, EntityLivingBase entity, ItemStack item, CallbackInfo ci) {
        if (customRenderer instanceof Vibe) {
            if (type.equals(EQUIPPED_FIRST_PERSON)) {
                GL11.glPushMatrix();
                customRenderer.renderItem(type, item, renderBlocks, entity);
                GL11.glPopMatrix();
                ci.cancel();
            } else {
                GL11.glPushMatrix();
                GL11.glEnable(GL12.GL_RESCALE_NORMAL);
                GL11.glTranslatef(0.0F, -0.3F, 0.0F);
                GL11.glScalef(1.5F, 1.5F, 1.5F);
                GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
                customRenderer.renderItem(type, item, renderBlocks, entity);
                GL11.glDisable(GL12.GL_RESCALE_NORMAL);
                GL11.glPopMatrix();
                ci.cancel();
            }
        }
    }
}
