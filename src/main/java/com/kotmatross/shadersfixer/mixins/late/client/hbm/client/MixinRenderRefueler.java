package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.main.ResourceManager;
import com.hbm.render.tileentity.IItemRendererProvider;
import com.hbm.render.tileentity.RenderRefueler;
import com.hbm.tileentity.machine.TileEntityRefueler;
import com.kotmatross.shadersfixer.AngelicaUtils;
import com.kotmatross.shadersfixer.Utils;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.awt.*;
import java.nio.DoubleBuffer;

@Mixin(value = RenderRefueler.class, priority = 999)
public abstract class MixinRenderRefueler extends TileEntitySpecialRenderer implements IItemRendererProvider {
	@Shadow
	private static DoubleBuffer clip = null;

	/**
	 * @author kotmatross
	 * @reason fix liquid rendering (glClipPlane doesn't work with shaders)
	 */
	@Overwrite
	public void renderTileEntityAt(TileEntity tile, double x, double y, double z, float interp) {
		TileEntityRefueler refueler = (TileEntityRefueler) tile;
		
		GL11.glPushMatrix();
		{
			
			GL11.glTranslated(x + 0.5, y, z + 0.5);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_CULL_FACE);
			GL11.glRotatef(90, 0F, 1F, 0F);
			switch (tile.getBlockMetadata()) {
				case 4 -> GL11.glRotatef(90, 0F, 1F, 0F);
				case 3 -> GL11.glRotatef(180, 0F, 1F, 0F);
				case 5 -> GL11.glRotatef(270, 0F, 1F, 0F);
				case 2 -> GL11.glRotatef(0, 0F, 1F, 0F);
			}
			
			GL11.glShadeModel(GL11.GL_SMOOTH);
			
			bindTexture(ResourceManager.refueler_tex);
			ResourceManager.refueler.renderPart("Fueler");
			
			if(!AngelicaUtils.isShaderEnabled()) {
				if(clip == null) {
					clip = GLAllocation.createDirectByteBuffer(8*4).asDoubleBuffer();
					clip.put(new double[] {0, 1, 0, -0.125 });
					clip.rewind();
				}
				GL11.glEnable(GL11.GL_CLIP_PLANE0);
				GL11.glClipPlane(GL11.GL_CLIP_PLANE0, clip);
			}
			
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
			
			double fillLevel = refueler.prevFillLevel + (refueler.fillLevel - refueler.prevFillLevel) * interp;
			
			if(!AngelicaUtils.isShaderEnabled()) {
				GL11.glTranslated(0, (1 - fillLevel) * -0.625, 0);
				
				Color color = new Color(refueler.tank.getTankType().getColor());
				GL11.glColor4f(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, 0.75F);
				ResourceManager.refueler.renderPart("Fluid");
				GL11.glColor4f(1, 1, 1, 1);
			} else {
				Tessellator tess = Tessellator.instance;
				
				double height = (fillLevel * 0.70113) + 0.09; //Max Y on model + offset
				
				
				height = MathHelper.clamp_double(height, 0.0, 0.70113);
				
				double initialY = 0.12; //Min Y on model
				
				Utils.Fix();
				
				tess.startDrawingQuads();
				tess.setColorOpaque_I(refueler.tank.getTankType().getColor());

				//1
				tess.addVertex(-0.4375, initialY, -0.095);
				tess.addVertex(-0.4375, initialY + height, -0.095);
				tess.addVertex(-0.31875, initialY + height, -0.063181);
				tess.addVertex(-0.31875, initialY, -0.063181);

				//2
				tess.addVertex(-0.31875, initialY, -0.063181);
				tess.addVertex(-0.31875, initialY + height, -0.063181);
				tess.addVertex(-0.231819,initialY + height ,0.02375);
				tess.addVertex(-0.231819,initialY,0.02375);

				//3
				tess.addVertex(-0.231819,initialY,0.02375);
				tess.addVertex(-0.231819,initialY + height ,0.02375);
				tess.addVertex(-0.2, initialY + height,0.1425);
				tess.addVertex(-0.2, initialY,0.1425);

				//4
				tess.addVertex(-0.2, initialY,0.1425);
				tess.addVertex(-0.2, initialY + height,0.1425);
				tess.addVertex(-0.231819,initialY + height,0.26125);
				tess.addVertex(-0.231819,initialY,0.26125);

				//5
				tess.addVertex(-0.231819,initialY,0.26125);
				tess.addVertex(-0.231819,initialY + height,0.26125);
				tess.addVertex(-0.31875, initialY + height, 0.348181);
				tess.addVertex(-0.31875, initialY, 0.348181);

				//6
				tess.addVertex(-0.31875, initialY, 0.348181);
				tess.addVertex(-0.31875, initialY + height, 0.348181);
				tess.addVertex(-0.4375,initialY + height, 0.38);
				tess.addVertex(-0.4375,initialY , 0.38);

				tess.draw();

				
				//Top
				tess.startDrawing(GL11.GL_POLYGON);
				tess.setColorOpaque_I(refueler.tank.getTankType().getColor());
				
				//FKING GL_CCW
				tess.addVertex(-0.4375, initialY + height, 0.38);
				tess.addVertex(-0.31875, initialY + height, 0.348181);
				tess.addVertex(-0.231819, initialY + height, 0.26125);
				tess.addVertex(-0.2, initialY + height, 0.1425);
				tess.addVertex(-0.231819, initialY + height, 0.02375);
				tess.addVertex(-0.31875, initialY + height, -0.063181);
				tess.addVertex(-0.4375, initialY + height, -0.095);
				
				tess.draw();
				
			}
			
			GL11.glColor4f(1, 1, 1, 1);
			
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_BLEND);
			
			if(!AngelicaUtils.isShaderEnabled()) {
				GL11.glDisable(GL11.GL_CLIP_PLANE0);
			}
			
			GL11.glShadeModel(GL11.GL_FLAT);
			
		}
		GL11.glPopMatrix();
	}
}
