package com.kotmatross.shadersfixer.WIP;

import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class EntityLightingFix extends EntityCreature {

    public EntityLightingFix(World var1) {
        super(var1);
        this.isImmuneToFire = true;
        this.ignoreFrustumCheck = true;
        this.setSize(0.0F, 0.0F);
        this.noClip = true;
        this.boundingBox.setBounds(0D, 0D, 0D, 0D, 0D, 0D);
    }

    @Override
    public void setPosition(double x, double y, double z) {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
    }

    @Override
    public AxisAlignedBB getBoundingBox()
    {
        return null;
    }
    @Override
    public AxisAlignedBB getCollisionBox(Entity entityIn)
    {
        return null;
    }
    @Override
    public float getCollisionBorderSize()
    {
        return 0.0F;
    }

    @Override
    public boolean isEntityAlive()
    {
        return false;
    }

    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }

//    @Override
//    protected boolean isMovementBlocked()
//    {
//        return true;
//    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0D);
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer var1)
    {}

    @Override
    public boolean canBeCollidedWith() {
        return false;
    }

    @Override
    public void setDead() {
        super.setDead();
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public boolean isInRangeToRenderDist(double var1) {
        return true;
    }

    @Override
    public boolean isInRangeToRender3d(double p_145770_1_, double p_145770_3_,
                                       double p_145770_5_) {
        return true;
    }

    public void entityInit() {
        super.entityInit();
    }

    @Override
    public void onUpdate() {
        //super.onUpdate();

        if(this.ticksExisted >= ShaderFixerConfig.tickLightingFixDespawn){
            this.setDead();
        }

    }
}