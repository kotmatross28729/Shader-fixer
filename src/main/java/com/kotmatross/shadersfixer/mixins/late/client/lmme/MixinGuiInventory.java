package com.kotmatross.shadersfixer.mixins.late.client.lmme;

import java.util.Random;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.EXTRescaleNormal;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.kotmatross.shadersfixer.shrimp.nonsense.FuckingCursed;

import littleMaidMobX.entity.EntityLittleMaid;
import littleMaidMobX.gui.GuiInventory;
import littleMaidMobX.inventory.ContainerInventory;

@FuckingCursed
@Mixin(value = GuiInventory.class, priority = 998)
public abstract class MixinGuiInventory extends GuiContainer {

    @Shadow(remap = false)
    private Random rand;
    @Shadow(remap = false)
    private IInventory upperChestInventory;
    @Shadow(remap = false)
    private IInventory lowerChestInventory;
    @Shadow(remap = false)
    private int ySizebk;
    @Shadow(remap = false)
    private int updateCounter;
    @Shadow(remap = false)
    public EntityLittleMaid entitylittlemaid;
    @Shadow(remap = false)
    public boolean isChangeTexture;

    public MixinGuiInventory(EntityPlayer pPlayer, EntityLittleMaid elmaid) {
        super(new ContainerInventory(pPlayer.inventory, elmaid));
        rand = new Random();
        upperChestInventory = pPlayer.inventory;
        lowerChestInventory = elmaid.maidInventory;
        allowUserInput = false;
        updateCounter = 0;
        ySizebk = ySize;
        ySize = 207;
        isChangeTexture = true;

        entitylittlemaid = elmaid;
        // entitylittlemaid.setOpenInventory(true);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite(remap = false)
    protected void func_146979_b(int par1, int par2) {
        String ls;
        mc.fontRenderer
            .drawString(StatCollector.translateToLocal(lowerChestInventory.getInventoryName()), 8, 64, 0x404040);
        mc.fontRenderer
            .drawString(StatCollector.translateToLocal(upperChestInventory.getInventoryName()), 8, 114, 0x404040);
        mc.fontRenderer.drawString(StatCollector.translateToLocal("littleMaidMob.text.STATUS"), 86, 8, 0x404040);
        mc.fontRenderer.drawString(
            StatCollector.translateToLocal("littleMaidMob.mode.".concat(entitylittlemaid.getMaidModeString())),
            86,
            61,
            0x404040);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        int lj = 0;
        int lk = 0;
        GL11.glEnable(EXTRescaleNormal.GL_RESCALE_NORMAL_EXT);
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glTranslatef(lj + 51, lk + 57, 50F);
        float f1 = 30F;
        GL11.glScalef(-f1, f1, f1);
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
        float f2 = entitylittlemaid.renderYawOffset;
        float f3 = entitylittlemaid.rotationYaw;
        float f4 = entitylittlemaid.rotationYawHead;
        float f5 = entitylittlemaid.rotationPitch;
        float f8 = (float) (guiLeft + 51 - par1);
        float f9 = (float) (guiTop + 22 - par2);
        GL11.glRotatef(135F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-(float) Math.atan(f9 / 40F) * 20F, 1.0F, 0.0F, 0.0F);
        entitylittlemaid.renderYawOffset = (float) Math.atan(f8 / 40F) * 20F;
        entitylittlemaid.rotationYawHead = entitylittlemaid.rotationYaw = (float) Math.atan(f8 / 40F) * 40F;
        entitylittlemaid.rotationPitch = -(float) Math.atan(f9 / 40F) * 20F;
        GL11.glTranslatef(0.0F, entitylittlemaid.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180F;
        // RenderManager.instance.renderEntityWithPosYaw(entitylittlemaid, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        entitylittlemaid.renderYawOffset = f2;
        entitylittlemaid.rotationYaw = f3;
        entitylittlemaid.rotationYawHead = f4;
        entitylittlemaid.rotationPitch = f5;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(EXTRescaleNormal.GL_RESCALE_NORMAL_EXT);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F / 1.0F, 240F / 1.0F);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
