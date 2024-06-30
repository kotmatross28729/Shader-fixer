package com.kotmatross.shadersfixer.LightingFix;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.HashMap;

public class EventHandler {
public static final HashMap<String, EntityLightingFix> Entities = new HashMap<>();

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.entityLiving.worldObj.isRemote) {
            if (event.entityLiving instanceof EntityPlayer player) {
                String currentPlayerName = Minecraft.getMinecraft().thePlayer.getCommandSenderName();

                if (player.getCommandSenderName().equals(currentPlayerName)) {

                    if (!Entities.containsKey(currentPlayerName)) {
                        EntityLightingFix newEntityLightingFix = new EntityLightingFix(player.worldObj);
                        newEntityLightingFix.setPosition(player.posX, player.posY, player.posZ);
                        player.worldObj.spawnEntityInWorld(newEntityLightingFix);
                        Entities.put(currentPlayerName, newEntityLightingFix);
                    } else {
                        EntityLightingFix existingEntity = Entities.get(currentPlayerName);
                        if (existingEntity != null && player.worldObj.loadedEntityList.contains(existingEntity)) {
                            existingEntity.setPosition(player.posX, player.posY, player.posZ);
                        } else {
                            EntityLightingFix respawnedEntity = new EntityLightingFix(player.worldObj);
                            respawnedEntity.setPosition(player.posX, player.posY, player.posZ);
                            player.worldObj.spawnEntityInWorld(respawnedEntity);
                            Entities.put(currentPlayerName, respawnedEntity);
                        }
                    }
                }
            }
        }
    }
}
