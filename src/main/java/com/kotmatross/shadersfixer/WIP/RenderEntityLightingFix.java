package com.kotmatross.shadersfixer.WIP;

import com.kotmatross.shadersfixer.Tags;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class RenderEntityLightingFix extends RenderLiving {

    public RenderEntityLightingFix()
    {
        super(new ModelCreeper(), 0.0F); //dirty hack
    }

    protected int shouldRenderPass(EntityLightingFix p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return -1;
    }
    protected int inheritRenderPass(EntityLightingFix p_77035_1_, int p_77035_2_, float p_77035_3_)
    {
        return -1;
    }

    protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_)
    {
        return this.shouldRenderPass((EntityLightingFix)p_77032_1_, p_77032_2_, p_77032_3_);
    }

    protected int inheritRenderPass(EntityLivingBase p_77035_1_, int p_77035_2_, float p_77035_3_)
    {
        return this.inheritRenderPass((EntityLightingFix)p_77035_1_, p_77035_2_, p_77035_3_);
    }

    /**
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return new ResourceLocation(Tags.MODID, "textures/LightingFix.png"); // invisible texture, dirty hack 2
    }
    */

    @Override
    protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
        return new ResourceLocation("textures/entity/creeper/creeper.png");
    }

}
