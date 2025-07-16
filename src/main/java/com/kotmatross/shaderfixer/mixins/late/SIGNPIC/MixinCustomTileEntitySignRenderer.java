package com.kotmatross.shaderfixer.mixins.late.SIGNPIC;

import javax.annotation.Nonnull;
import javax.vecmath.Quat4f;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntitySignRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntitySign;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import com.kamesuta.mc.bnnwidget.render.OpenGL;
import com.kamesuta.mc.bnnwidget.render.RenderOption;
import com.kamesuta.mc.bnnwidget.render.WRenderer;
import com.kamesuta.mc.signpic.Config;
import com.kamesuta.mc.signpic.attr.prop.RotationData;
import com.kamesuta.mc.signpic.entry.Entry;
import com.kamesuta.mc.signpic.entry.EntryId;
import com.kamesuta.mc.signpic.gui.GuiImage;
import com.kamesuta.mc.signpic.mode.CurrentMode;
import com.kamesuta.mc.signpic.render.CustomTileEntitySignRenderer;

@Mixin(value = CustomTileEntitySignRenderer.class, priority = 999)
public class MixinCustomTileEntitySignRenderer extends TileEntitySignRenderer {

    /**
     * @author kotmatross
     * @reason fix brightness with angelica shaders
     */
    @Overwrite(remap = false)
    public void renderSignPictureBase(@Nonnull TileEntitySign tile, double x, double y, double z, float partialTicks,
        float opacity) {
        OpenGL.glPushAttrib(GL11.GL_LIGHTING_BIT | GL11.GL_ENABLE_BIT | GL11.GL_MAP2_INDEX); // [0] Saves: GL_LIGHTING |
                                                                                             // GL_BLEND | GL_TEXTURE_2D
        Entry entry = EntryId.SignEntryId.fromTile(tile)
            .entry();
        if (entry.isOutdated() && CurrentMode.instance.isState(CurrentMode.State.SEE)) {
            OpenGL.glDisable(GL11.GL_DEPTH_TEST);
        }

        if (entry.isValid()) {
            if (CurrentMode.instance.isState(CurrentMode.State.SEE)) {
                OpenGL.glPushAttrib(GL11.GL_LIGHTING_BIT | GL11.GL_ENABLE_BIT | GL11.GL_MAP2_INDEX); // [1] Saves:
                                                                                                     // GL_LIGHTING |
                                                                                                     // GL_BLEND |
                                                                                                     // GL_TEXTURE_2D
                WRenderer.startTexture();
                OpenGL.glColor4f(
                    1.0F,
                    1.0F,
                    1.0F,
                    opacity * ((Double) Config.getConfig().renderSeeOpacity.get()).floatValue());
                super.renderTileEntityAt(tile, x, y, z, partialTicks);
                OpenGL.glPopAttrib(); // [1] Restores: GL_LIGHTING | GL_BLEND | GL_TEXTURE_2D
            }

            OpenGL.glPushMatrix();
            this.translateBase(tile, x, y, z);
            OpenGL.glDisable(2884);
            GuiImage gui = entry.getGui();
            gui.applyLight((float) tile.xCoord, (float) tile.yCoord, (float) tile.zCoord, this.getSignRotate(tile));
            gui.renderSignPicture(opacity, 1.0F, new RenderOption());
            OpenGL.glEnable(2884);
            OpenGL.glPopMatrix();
        } else {
            OpenGL.glPushAttrib(GL11.GL_LIGHTING_BIT | GL11.GL_ENABLE_BIT | GL11.GL_MAP2_INDEX); // [2] Saves:
                                                                                                 // GL_LIGHTING |
                                                                                                 // GL_BLEND |
                                                                                                 // GL_TEXTURE_2D
            // !↓↓↓, The only reason I used Overwrite
            // IDFK how to Inject before if
            if (opacity < 1.0F) {
                WRenderer.startTexture();
                OpenGL.glColor4f(1.0F, 1.0F, 1.0F, opacity); // [2] Restores: GL_LIGHTING | GL_BLEND | GL_TEXTURE_2D
            }

            super.renderTileEntityAt(tile, x, y, z, partialTicks);
            OpenGL.glPopAttrib();
        }

        OpenGL.glEnable(GL11.GL_DEPTH_TEST);
        WRenderer.startTexture();
        OpenGL.glPopAttrib(); // [0] Restores: GL_LIGHTING | GL_BLEND | GL_TEXTURE_2D
    }

    @Shadow(remap = false)
    public void translateBase(@Nonnull TileEntitySign tile, double x, double y, double z) {
        Block block = tile.getBlockType();
        float f1 = 0.6666667F;
        if (block == Blocks.standing_sign) {
            OpenGL.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
            RotationData.RotationGL.glRotate(this.getSignRotate(tile));
        } else {
            OpenGL.glTranslatef((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
            RotationData.RotationGL.glRotate(this.getSignRotate(tile));
            OpenGL.glTranslatef(0.0F, 0.0F, -0.4375F);
        }
    }

    @Shadow(remap = false)
    @Nonnull
    public Quat4f getSignRotate(@Nonnull TileEntitySign tile) {
        Block block = tile.getBlockType();
        if (block == Blocks.standing_sign) {
            float f2 = (float) tile.getBlockMetadata() * 360.0F / 16.0F;
            return RotationData.RotationMath.quatDeg(-f2, 0.0F, 1.0F, 0.0F);
        } else {
            int j = tile.getBlockMetadata();
            float f3;
            switch (j) {
                case 2:
                    f3 = 180.0F;
                    break;
                case 3:
                default:
                    f3 = 0.0F;
                    break;
                case 4:
                    f3 = 90.0F;
                    break;
                case 5:
                    f3 = -90.0F;
            }

            return RotationData.RotationMath.quatDeg(-f3, 0.0F, 1.0F, 0.0F);
        }
    }
}
