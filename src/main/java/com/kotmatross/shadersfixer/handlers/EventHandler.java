package com.kotmatross.shadersfixer.handlers;

import com.kotmatross.shadersfixer.WIP.EntityLightingFix;
import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.chunk.Chunk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class EventHandler {

    //TODO:
    //  -1) Server-side config option - 100% DONE
    //  -2) Accurate player tracking - 100% DONE
    //  -3) Accurate Fix-Entity Positioning - 100% DONE
    //  4) In-game github wiki link - 0%


    public static Logger logger = LogManager.getLogger();

    public static final HashMap<UUID, EntityLightingFix> Entities = new HashMap<>();

    //100% - APT
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.player.worldObj != null) {
            if (!event.player.worldObj.isRemote) {
                if (event.player.worldObj.getWorldTime() % ShaderFixerConfig.tickRatePlayerLoop == 0) {
                            EntityPlayer player = event.player;
                            UUID playerID = player.getUniqueID();
                            EntityLightingFix Entity = new EntityLightingFix(player.worldObj);
                            Entity.setPosition(player.posX, player.posY, player.posZ);
                            player.worldObj.spawnEntityInWorld(Entity);
                            Entities.put(playerID, Entity);
                        }
            }
        }
    }

    //100% - APT
    @SubscribeEvent
    public void playerLoggedOutEvent(PlayerEvent.PlayerLoggedOutEvent event) {
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER) { //For dedicated servers
            if (!event.player.worldObj.isRemote) {
                if (event.player.worldObj.getWorldTime() % ShaderFixerConfig.tickRatePlayerLoop == 0) {
                    EntityPlayer player = event.player;
                    UUID playerID = player.getUniqueID();
                    Iterator<Map.Entry<UUID, EntityLightingFix>> iterator = Entities.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<UUID, EntityLightingFix> entry = iterator.next();
                        if (entry.getKey().equals(playerID)) {
                            EntityLightingFix entity = entry.getValue();
                            if (entity != null) {
                                entity.setDead();
                                event.player.worldObj.removeEntity(entity);
                                iterator.remove();
                            }
                        }
                    }
                }
            }
        }
    }

    //100% - APT
    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.player.worldObj != null) {
            if (!event.player.worldObj.isRemote) {
                if (event.player.worldObj.getWorldTime() % ShaderFixerConfig.tickRatePlayerLoop == 0) {
                    boolean alpha = false;
                    EntityPlayer player = event.player;
                    UUID playerID = player.getUniqueID();
                    Iterator<Map.Entry<UUID, EntityLightingFix>> iterator = Entities.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<UUID, EntityLightingFix> entry = iterator.next();
                        if (entry.getKey().equals(playerID)) {
                            EntityLightingFix entity = entry.getValue();
                            if (entity != null) {
                                entity.setDead();
                                event.player.worldObj.removeEntity(entity);
                                iterator.remove();
                                alpha = true;
                            }
                        }
                    }
                    if(alpha) {
                        EntityLightingFix Entity = new EntityLightingFix(player.worldObj);
                        Entity.setPosition(player.posX, player.posY, player.posZ);
                        player.worldObj.spawnEntityInWorld(Entity);
                        Entities.put(playerID, Entity);
                        alpha = false;
                    }
                }
            }
        }
    }

    //100% - AEP
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(ShaderFixerConfig.LightingFix) {
            if (event.player.worldObj != null) {
                if (!event.player.worldObj.isRemote) {
                    if (event.player.worldObj.getWorldTime() % ShaderFixerConfig.tickRatePlayerLoop == 0) {
                        for (int i = 0; i < event.player.worldObj.playerEntities.size(); i++) {
                            EntityPlayer player = (EntityPlayer) event.player.worldObj.playerEntities.get(i);
                            if (player != null) {
                                UUID playerID = player.getUniqueID();
                                Iterator<Map.Entry<UUID, EntityLightingFix>> iterator = Entities.entrySet().iterator();
                                while (iterator.hasNext()) {
                                    Map.Entry<UUID, EntityLightingFix> entry = iterator.next();
                                    if (entry.getKey().equals(playerID)) {
                                        EntityLightingFix entity = entry.getValue();
                                        if (entity != null) {
                                            entity.setLocationAndAngles(player.posX,player.posY,player.posZ, entity.rotationYaw, entity.rotationPitch);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}










