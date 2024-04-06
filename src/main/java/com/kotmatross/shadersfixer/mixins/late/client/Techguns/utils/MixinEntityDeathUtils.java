package com.kotmatross.shadersfixer.mixins.late.client.Techguns.utils;

import com.kotmatross.shadersfixer.ShadersFixer;
import cpw.mods.fml.common.Loader;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import techguns.entities.npc.*;
import techguns.util.EntityDeathUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.LogManager;

import static com.kotmatross.shadersfixer.config.ShaderFixerConfig.*;

@Mixin(value = EntityDeathUtils.class, priority = 999)
public class MixinEntityDeathUtils {
    @Shadow
    public static HashMap<EntityDeathUtils.DeathType, List<Class<? extends EntityLivingBase>>> entityDeathTypes = new HashMap<>();

/**
 * @author kotmatross
 * @reason fix bug
 */
@Overwrite(remap = false)
public static boolean hasSpecialDeathAnim(EntityLivingBase entityLiving, EntityDeathUtils.DeathType deathtype) {
        ArrayList<Class<? extends EntityLivingBase>> listGore = new ArrayList();
        listGore.add(EntityPlayer.class);
        listGore.add(EntityZombie.class);
        listGore.add(EntitySkeleton.class);
        listGore.add(EntityEnderman.class);
        listGore.add(EntityCreeper.class);
        listGore.add(EntityCow.class);
        listGore.add(EntitySheep.class);
        listGore.add(EntityPig.class);
        listGore.add(EntityChicken.class);
        listGore.add(EntityPigZombie.class);
        listGore.add(ZombieSoldier.class);
        listGore.add(ArmySoldier.class);
        listGore.add(CyberDemon.class);
        listGore.add(ZombiePigmanSoldier.class);
        listGore.add(EntitySpider.class);
        listGore.add(EntityCaveSpider.class);
        listGore.add(EntityWitch.class);
        listGore.add(EntitySlime.class);
        listGore.add(ZombieFarmer.class);
        listGore.add(ZombieMiner.class);
        listGore.add(Bandit.class);
        listGore.add(ZombieSoldier.class);
        listGore.add(EntityHorse.class);
        listGore.add(EntityMooshroom.class);
        listGore.add(EntityWolf.class);
        listGore.add(EntitySquid.class);
        listGore.add(EntityGhast.class);
        listGore.add(EntityVillager.class);
        listGore.add(PsychoSteve.class);
        listGore.add(DictatorDave.class);
        listGore.add(SkeletonSoldier.class);
        listGore.add(AlienBug.class);
        entityDeathTypes.put(EntityDeathUtils.DeathType.GORE, listGore);
/*
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

        ShadersFixer.logger.fatal("getEntityString? " + EntityList.getEntityString(entityLiving)); //TODO debug
        ShadersFixer.logger.fatal("entityname? " + entityname); //TODO debug

        if (EntityList.getEntityString(entityLiving).equals(entityname)) {
            ShadersFixer.logger.fatal("bloodColorRed? " + bloodColorRed); //TODO debug
            ShadersFixer.logger.fatal("bloodColorGreen? " + bloodColorGreen); //TODO debug
            ShadersFixer.logger.fatal("bloodColorBlue? " + bloodColorBlue); //TODO debug
            return true;
        }
    }
    */
    if(TechgunsGoreLogger){
        ShadersFixer.logger.info("Name of the killed mob: " + EntityList.getEntityString(entityLiving));
    }
        if(Loader.isModLoaded("witchery")) {
            if(entityLiving instanceof EntityVillager){
                return false;
            }
        }
        if (entityLiving instanceof IBossDisplayData) {
            if(entityLiving instanceof GenericNPC){
                return true;
            }
        } else if (deathtype != EntityDeathUtils.DeathType.BIO && deathtype != EntityDeathUtils.DeathType.LASER) {
                    if ( (((List) entityDeathTypes.get(EntityDeathUtils.DeathType.GORE))) != null) {
                        if (((List) entityDeathTypes.get(EntityDeathUtils.DeathType.GORE)).contains(entityLiving.getClass())) {
                            return true;
                        }
                    } else {
                    }
        } else {
            return true;
        }
        return false;
    }
}
