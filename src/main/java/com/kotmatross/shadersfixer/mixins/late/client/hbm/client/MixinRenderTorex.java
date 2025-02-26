package com.kotmatross.shadersfixer.mixins.late.client.hbm.client;

import com.hbm.render.entity.effect.RenderTorex;
import net.minecraft.client.renderer.entity.Render;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = RenderTorex.class, priority = 999)
public abstract class MixinRenderTorex extends Render {
	
	//TODO: TODO: TODO: TODO: TODO
	
//	@Shadow
//	private static final ResourceLocation cloudlet = new ResourceLocation(RefStrings.MODID + ":textures/particle/particle_base.png");
//	@Shadow
//	private static final ResourceLocation flash = new ResourceLocation(RefStrings.MODID + ":textures/particle/flare.png");
//	
//	@Shadow
//	private Comparator cloudSorter = new Comparator() {
//		
//		@Override
//		public int compare(Object arg0, Object arg1) {
//			EntityNukeTorex.Cloudlet first = (EntityNukeTorex.Cloudlet) arg0;
//			EntityNukeTorex.Cloudlet second = (EntityNukeTorex.Cloudlet) arg1;
//			EntityPlayer player = MainRegistry.proxy.me();
//			double dist1 = player.getDistanceSq(first.posX, first.posY, first.posZ);
//			double dist2 = player.getDistanceSq(second.posX, second.posY, second.posZ);
//			
//			return dist1 > dist2 ? -1 : dist1 == dist2 ? 0 : 1;
//		}
//	};
//	
//	/**
//	 * @author
//	 * @reason
//	 */
//	@Overwrite(remap = false)
//	private void cloudletWrapper(EntityNukeTorex cloud, float interp) {
//		GL11.glPushMatrix();
//
//		GL11.glEnable(GL11.GL_BLEND);
//		
//		// !!! PATCH !!!
//		//OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0); 
//
//		// To prevent particles cutting off before fully fading out
//		GL11.glAlphaFunc(GL11.GL_GREATER, 0);
//		GL11.glDisable(GL11.GL_ALPHA_TEST);
//		GL11.glDepthMask(false);
//		RenderHelper.disableStandardItemLighting();
//		
//		bindTexture(cloudlet);
//		
//		Tessellator tess = Tessellator.instance;
//		
//
//		
//		tess.startDrawingQuads();
//		tess.setBrightness(240); //!!! PATCH !!!
//		
//		ArrayList<EntityNukeTorex.Cloudlet> cloudlets = new ArrayList(cloud.cloudlets);
//		cloudlets.sort(cloudSorter);
//		
//		for(EntityNukeTorex.Cloudlet cloudlet : cloudlets) {
//			Vec3 vec = cloudlet.getInterpPos(interp);
//			double x = vec.xCoord - cloud.posX;
//			double y = vec.yCoord - cloud.posY;
//			double z = vec.zCoord - cloud.posZ;
//			tessellateCloudlet(tess, x, y, z, cloudlet, interp);
//		}
//		
//		tess.draw();
//		
//		GL11.glDepthMask(true);
//		GL11.glEnable(GL11.GL_ALPHA_TEST);
//		RenderHelper.enableStandardItemLighting();
//		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
//		GL11.glDisable(GL11.GL_BLEND);
//
//		GL11.glPopMatrix();
//	}

//	/**
//	 * @author
//	 * @reason
//	 */
//	@Overwrite(remap = false)
//	private void tessellateCloudlet(Tessellator tess, double posX, double posY, double posZ, EntityNukeTorex.Cloudlet cloud, float interp) {
//		float alpha = cloud.getAlpha();
//		float scale = cloud.getScale();
//
//		float f1 = ActiveRenderInfo.rotationX;
//		float f2 = ActiveRenderInfo.rotationZ;
//		float f3 = ActiveRenderInfo.rotationYZ;
//		float f4 = ActiveRenderInfo.rotationXY;
//		float f5 = ActiveRenderInfo.rotationXZ;
//
//		float brightness = cloud.type == cloud.type.CONDENSATION ? 0.9F : 0.75F * cloud.colorMod;
//		Vec3 color = cloud.getInterpColor(interp);
//		tess.setColorRGBA_F((float)color.xCoord * brightness, (float)color.yCoord * brightness, (float)color.zCoord * brightness, alpha);
//
//		tess.addVertexWithUV((double) (posX - f1 * scale - f3 * scale), (double) (posY - f5 * scale), (double) (posZ - f2 * scale - f4 * scale), 1, 1);
//		tess.addVertexWithUV((double) (posX - f1 * scale + f3 * scale), (double) (posY + f5 * scale), (double) (posZ - f2 * scale + f4 * scale), 1, 0);
//		tess.addVertexWithUV((double) (posX + f1 * scale + f3 * scale), (double) (posY + f5 * scale), (double) (posZ + f2 * scale + f4 * scale), 0, 0);
//		tess.addVertexWithUV((double) (posX + f1 * scale - f3 * scale), (double) (posY - f5 * scale), (double) (posZ + f2 * scale - f4 * scale), 0, 1);
//	}
	
}
