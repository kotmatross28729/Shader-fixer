package com.kotmatross.shadersfixer.mixins.late.client.Techguns.client.renderer.tileentity;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelChest;
import net.minecraft.client.model.ModelLargeChest;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import cpw.mods.fml.common.FMLLog;
import techguns.blocks.BlockTGChest;
import techguns.client.renderer.tileentity.RenderTGChest;
import techguns.tileentities.TGChestTileEnt;

@Mixin(value = RenderTGChest.class, priority = 999)
public class MixinRenderTGChest {

    @Shadow(remap = false)
    private ModelChest doubleChest = new ModelLargeChest();
    @Shadow(remap = false)
    private ModelChest singleChest = new ModelChest();
    @Final
    @Shadow(remap = false)
    private static final ResourceLocation[] texture_double = new ResourceLocation[] {
        new ResourceLocation("techguns:textures/blocks/reinforced_chest_double.png"),
        new ResourceLocation("techguns:textures/blocks/weapon_chest_double.png") };
    @Final
    @Shadow(remap = false)
    private static final ResourceLocation[] texture_single = new ResourceLocation[] {
        new ResourceLocation("techguns:textures/blocks/reinforced_chest.png"),
        new ResourceLocation("techguns:textures/blocks/weapon_chest.png") };

    /**
     * @author kotmatross
     * @reason Fix crash
     */
    @Overwrite(remap = false)
    public void renderTileEntityAt(final TGChestTileEnt chestTileEnt, final double p_147500_2_,
        final double p_147500_4_, final double p_147500_6_, final float p_147500_8_) {
        if (chestTileEnt.getBlockType() == Blocks.air) {
            return;
        }

        final int chestType = ((BlockTGChest) chestTileEnt.getBlockType()).type;
        int i;
        if (!chestTileEnt.hasWorldObj()) {
            i = 0;
        } else {
            final Block block = chestTileEnt.getBlockType();
            i = chestTileEnt.getBlockMetadata();
            if (block instanceof BlockTGChest && i == 0) {
                try {
                    ((BlockTGChest) block).func_149954_e(
                        chestTileEnt.getWorldObj(),
                        chestTileEnt.xCoord,
                        chestTileEnt.yCoord,
                        chestTileEnt.zCoord);
                } catch (ClassCastException e) {
                    FMLLog.severe(
                        "Attempted to render a chest at %d,  %d, %d that was not a chest",
                        new Object[] { chestTileEnt.xCoord, chestTileEnt.yCoord, chestTileEnt.zCoord });
                }
                i = chestTileEnt.getBlockMetadata();
            }
            chestTileEnt.checkForAdjacentChests();
        }
        if (chestTileEnt.adjacentChestZNeg == null && chestTileEnt.adjacentChestXNeg == null) {
            ModelChest modelchest;
            if (chestTileEnt.adjacentChestXPos == null && chestTileEnt.adjacentChestZPos == null) {
                modelchest = this.singleChest;
                Minecraft.getMinecraft().renderEngine.bindTexture(texture_single[chestType]);
            } else {
                modelchest = this.doubleChest;
                Minecraft.getMinecraft().renderEngine.bindTexture(texture_double[chestType]);
            }
            GL11.glPushMatrix();
            GL11.glEnable(32826);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glTranslatef((float) p_147500_2_, (float) p_147500_4_ + 1.0f, (float) p_147500_6_ + 1.0f);
            GL11.glScalef(1.0f, -1.0f, -1.0f);
            GL11.glTranslatef(0.5f, 0.5f, 0.5f);
            short short1 = 0;
            if (i == 2) {
                short1 = 180;
            }
            if (i == 3) {
                short1 = 0;
            }
            if (i == 4) {
                short1 = 90;
            }
            if (i == 5) {
                short1 = -90;
            }
            if (i == 2 && chestTileEnt.adjacentChestXPos != null) {
                GL11.glTranslatef(1.0f, 0.0f, 0.0f);
            }
            if (i == 5 && chestTileEnt.adjacentChestZPos != null) {
                GL11.glTranslatef(0.0f, 0.0f, -1.0f);
            }
            GL11.glRotatef((float) short1, 0.0f, 1.0f, 0.0f);
            GL11.glTranslatef(-0.5f, -0.5f, -0.5f);
            float f1 = chestTileEnt.prevLidAngle + (chestTileEnt.lidAngle - chestTileEnt.prevLidAngle) * p_147500_8_;
            if (chestTileEnt.adjacentChestZNeg != null) {
                final float f2 = chestTileEnt.adjacentChestZNeg.prevLidAngle
                    + (chestTileEnt.adjacentChestZNeg.lidAngle - chestTileEnt.adjacentChestZNeg.prevLidAngle)
                        * p_147500_8_;
                if (f2 > f1) {
                    f1 = f2;
                }
            }
            if (chestTileEnt.adjacentChestXNeg != null) {
                final float f2 = chestTileEnt.adjacentChestXNeg.prevLidAngle
                    + (chestTileEnt.adjacentChestXNeg.lidAngle - chestTileEnt.adjacentChestXNeg.prevLidAngle)
                        * p_147500_8_;
                if (f2 > f1) {
                    f1 = f2;
                }
            }
            f1 = 1.0f - f1;
            f1 = 1.0f - f1 * f1 * f1;
            modelchest.chestLid.rotateAngleX = -(f1 * 3.1415927f / 2.0f);
            modelchest.renderAll();
            GL11.glDisable(32826);
            GL11.glPopMatrix();
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
}
