package com.kotmatross.shadersfixer.mixins.late.client.Techguns.utils;

import static com.kotmatross.shadersfixer.config.ShaderFixerConfig.*;

import java.util.HashMap;

import net.minecraft.client.model.*;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import techguns.Techguns;
import techguns.client.ClientProxy;
import techguns.client.DeathEffect;
import techguns.client.GoreData;
import techguns.client.models.*;
import techguns.client.particle.EntityBloodEffect;
import techguns.entities.npc.*;
import techguns.entities.projectiles.FlyingGibs;
import techguns.util.EntityDeathUtils;

@Deprecated
@Mixin(value = DeathEffect.class, priority = 999)
public class MixinDeathEffect {

    @Shadow(remap = false)
    private static GoreData genericGore;

    private static HashMap<EntityLivingBase, GoreData> goreStats = new HashMap();

    @Unique
    private static GoreData shaders_fixer$getGoreData(EntityLivingBase entityClass) {
        ModelGibs modelBiped = new ModelGibsBiped(1.0F);
        ModelGibs modelBipedPlayer = new ModelGibsBiped(1.0F, 0.0F, 64, 32);
        for (String entity : TechgunsGoreList) {
            String entityname = "";
            int numGibs = 6;
            float scale = 0.66f;
            int bloodColorRed = 255;
            int bloodColorGreen = 255;
            int bloodColorBlue = 255;

            String[] parts = entity.split(":");
            if (parts.length > 1) {
                entityname = parts[0];
                numGibs = Integer.parseInt(parts[1]);
                scale = Float.parseFloat(parts[2]);
                bloodColorRed = Integer.parseInt(parts[3]);
                bloodColorGreen = Integer.parseInt(parts[4]);
                bloodColorBlue = Integer.parseInt(parts[5]);
            }
            if (EntityList.getEntityString(entityClass)
                .equals(entityname)) {
                genericGore = new GoreData(
                    modelBiped,
                    numGibs,
                    new ResourceLocation("techguns", "textures/entity/gore.png"),
                    scale,
                    bloodColorRed,
                    bloodColorGreen,
                    bloodColorBlue);
            } else {
                genericGore = new GoreData(
                    modelBiped,
                    numGibs,
                    new ResourceLocation("techguns", "textures/entity/gore.png"),
                    1.0F,
                    255,
                    255,
                    255);
            }
        }
        if (entityClass instanceof EntityPlayer) {
            genericGore = new GoreData(
                modelBipedPlayer,
                6,
                new ResourceLocation("techguns", "textures/entity/gore.png"),
                0.66F,
                160,
                21,
                31);
        } else if (entityClass.getClass() == EntityZombie.class) {
            genericGore = new GoreData(
                modelBiped,
                6,
                new ResourceLocation("textures/entity/zombie/zombie.png"),
                0.66F,
                110,
                21,
                41);
        } else if (entityClass.getClass() == ZombieSoldier.class) {
            genericGore = new GoreData(
                modelBiped,
                6,
                new ResourceLocation("techguns", "textures/entity/zombie_soldier.png"),
                0.66F,
                110,
                21,
                41);
        } else if (entityClass.getClass() == ArmySoldier.class) {
            genericGore = new GoreData(
                modelBiped,
                6,
                new ResourceLocation("techguns", "textures/entity/army_soldier.png"),
                0.66F,
                160,
                21,
                31);
        } else if (entityClass.getClass() == EntitySkeleton.class) {
            genericGore = new GoreData(
                new ModelGibsSkeleton(1.0F),
                6,
                new ResourceLocation("textures/entity/skeleton/skeleton.png"),
                0.66F,
                255,
                255,
                255).setBlood(false);
        } else if (entityClass.getClass() == EntityVillager.class) {
            genericGore = new GoreData(
                new ModelGibsVillagerGeneric(new ModelVillager(1.0F)),
                5,
                new ResourceLocation("textures/entity/villager/villager.png"),
                0.66F,
                150,
                21,
                51);
        } else if (entityClass.getClass() == EntityCow.class) {
            genericGore = new GoreData(
                new ModelGibsGeneric(new ModelCow()),
                6,
                new ResourceLocation("textures/entity/cow/cow.png"),
                1.0F,
                170,
                26,
                37);
        } else if (entityClass.getClass() == EntitySheep.class) {
            genericGore = new GoreData(
                new ModelGibsGeneric(new ModelSheep1()),
                6,
                new ResourceLocation("textures/entity/sheep/sheep.png"),
                1.0F,
                170,
                26,
                37);
        } else if (entityClass.getClass() == EntityChicken.class) {
            genericGore = new GoreData(
                new ModelGibsGeneric(new ModelChicken()),
                6,
                new ResourceLocation("textures/entity/chicken.png"),
                1.0F,
                170,
                26,
                37);
        } else if (entityClass.getClass() == EntityCreeper.class) {
            genericGore = new GoreData(
                new ModelGibsGeneric(new ModelCreeper()),
                7,
                new ResourceLocation("textures/entity/creeper/creeper.png"),
                1.0F,
                50,
                175,
                57);
        } else if (entityClass.getClass() == EntityEnderman.class) {
            genericGore = new GoreData(
                new ModelGibsGeneric(new ModelEnderman()),
                7,
                new ResourceLocation("textures/entity/enderman/enderman.png"),
                1.0F,
                160,
                36,
                167);
        } else if (entityClass.getClass() == EntityPig.class) {
            genericGore = new GoreData(
                new ModelGibsGeneric(new ModelPig()),
                6,
                new ResourceLocation("textures/entity/pig/pig.png"),
                0.8F,
                170,
                26,
                37);
        } else if (entityClass.getClass() == EntitySpider.class) {
            genericGore = new GoreData(
                new ModelGibsGeneric(new ModelSpider()),
                11,
                new ResourceLocation("textures/entity/spider/spider.png"),
                1.0F,
                85,
                156,
                17);
        } else if (entityClass.getClass() == EntityCaveSpider.class) {
            genericGore = new GoreData(
                new ModelGibsGeneric(new ModelSpider()),
                11,
                new ResourceLocation("textures/entity/spider/cave_spider.png"),
                0.66F,
                85,
                156,
                17);
        } else if (entityClass.getClass() == EntityPigZombie.class) {
            genericGore = new GoreData(
                modelBiped,
                6,
                new ResourceLocation("textures/entity/zombie_pigman.png"),
                0.66F,
                110,
                51,
                11);
        } else if (entityClass.getClass() == ZombiePigmanSoldier.class) {
            genericGore = new GoreData(
                modelBiped,
                6,
                new ResourceLocation("textures/entity/zombie_pigman.png"),
                0.66F,
                110,
                51,
                11);
        } else if (entityClass.getClass() == CyberDemon.class) {
            genericGore = new GoreData(
                new ModelGibsBipedGeneric(new ModelCyberDemon()),
                6,
                new ResourceLocation("techguns", "textures/entity/cyberdemon.png"),
                1.0F,
                85,
                156,
                17);
        } else if (entityClass.getClass() == EntityWitch.class) {
            genericGore = new GoreData(
                new ModelGibsVillagerGeneric(new ModelWitch(1.0F)),
                5,
                new ResourceLocation("textures/entity/witch.png"),
                0.66F,
                160,
                21,
                31);
        } else if (entityClass.getClass() == EntitySlime.class) {
            genericGore = new GoreData((ModelGibs) null, 0, (ResourceLocation) null, 1.0F, 40, 255, 40);
        } else if (entityClass.getClass() == ZombieFarmer.class) {
            genericGore = new GoreData(
                modelBiped,
                6,
                new ResourceLocation("techguns", "textures/entity/zombie_soldier.png"),
                0.66F,
                110,
                21,
                41);
        } else if (entityClass.getClass() == ZombieMiner.class) {
            genericGore = new GoreData(
                modelBiped,
                6,
                new ResourceLocation("techguns", "textures/entity/zombie_soldier.png"),
                0.66F,
                110,
                21,
                41);
        } else if (entityClass.getClass() == Bandit.class) {
            genericGore = new GoreData(
                modelBiped,
                6,
                new ResourceLocation("techguns", "textures/entity/bandit.png"),
                0.66F,
                160,
                21,
                31);
        } else if (entityClass.getClass() == SkeletonSoldier.class) {
            genericGore = new GoreData(
                new ModelGibsSkeleton(1.0F),
                6,
                new ResourceLocation("textures/entity/skeleton/skeleton.png"),
                0.66F,
                255,
                255,
                255).setBlood(false);
        } else if (entityClass.getClass() == AlienBug.class) {
            genericGore = new GoreData(
                new ModelGibsAlienBug(),
                8,
                new ResourceLocation("techguns", "textures/entity/alienbug.png"),
                0.8F,
                235,
                255,
                70);
        }
        genericGore.setRandomScale(0.5F, 0.8F);

        GoreData data = (GoreData) goreStats.get(entityClass);
        return data != null ? data : genericGore;
    }

    /**
     * @author kotmatross
     * @reason config
     */
    @Overwrite(remap = false)
    public static void createDeathEffect(final EntityLivingBase entity, final EntityDeathUtils.DeathType deathtype,
        final float motionX, final float motionY, final float motionZ) {
        final double x = entity.posX;
        final double y = entity.posY + entity.height / 2.0f;
        final double z = entity.posZ;
        if (deathtype == EntityDeathUtils.DeathType.GORE) {
            final GoreData data = shaders_fixer$getGoreData(entity);
            ClientProxy.spawnParticle(
                (EntityFX) new EntityBloodEffect(
                    entity.worldObj,
                    x,
                    y,
                    z,
                    motionX,
                    motionY,
                    motionZ,
                    entity.width,
                    entity.height,
                    data));
            for (int count = data.numGibs, i = 0; i < count; ++i) {
                final double vx = (0.5 - entity.worldObj.rand.nextDouble()) * 0.35;
                double vy;
                if (entity.onGround) {
                    vy = entity.worldObj.rand.nextDouble() * 0.35;
                } else {
                    vy = (0.5 - entity.worldObj.rand.nextDouble()) * 0.35;
                }
                final double vz = (0.5 - entity.worldObj.rand.nextDouble()) * 0.35;
                final FlyingGibs ent = new FlyingGibs(
                    entity.worldObj,
                    entity,
                    data,
                    x,
                    y,
                    z,
                    motionX * 0.35 + vx,
                    motionY * 0.15 + vy,
                    motionZ * 0.35 + vz,
                    (entity.width + entity.height) / 2.0f,
                    i);
                entity.worldObj.spawnEntityInWorld((Entity) ent);
            }
        } else if (deathtype == EntityDeathUtils.DeathType.BIO) {
            Techguns.proxy.createFX("biodeath", entity.worldObj, x, y, z, motionX, motionY, motionZ);
        } else if (deathtype == EntityDeathUtils.DeathType.LASER) {
            Techguns.proxy.createFX("laserdeathFire", entity.worldObj, x, y, z, motionX, 0.0, motionZ);
            Techguns.proxy.createFX("laserdeathAsh", entity.worldObj, x, y, z, motionX, 0.0, motionZ);
        }
    }

}
