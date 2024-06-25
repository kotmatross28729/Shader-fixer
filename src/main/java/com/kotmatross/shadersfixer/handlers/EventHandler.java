package com.kotmatross.shadersfixer.handlers;

import com.kotmatross.shadersfixer.ShadersFixer;
import com.kotmatross.shadersfixer.WIP.EntityLightingFix;
import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.world.WorldEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import java.util.*;

public class EventHandler {
    //TODO:
    //  -1) Server-side config option - 100% DONE
    //  -2) Accurate player tracking - 100% DONE
    //  -3) Accurate Fix-Entity Positioning - ?%
    //  4) In-game GUI menu with explanation about this fix - 0% (Replace with github wiki?)

    public static EntityLightingFix var1;


    public static double height;


/**
    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if(ShaderFixerConfig.LightingFix) {
            if (event.player.worldObj != null) {
                if (!event.player.worldObj.isRemote) {
                    if (event.player.worldObj.getWorldTime() % ShaderFixerConfig.tickRatePlayerLoop == 0) {
                        for (int i = 0; i < event.player.worldObj.playerEntities.size(); i++) {
                            EntityPlayer player = (EntityPlayer) event.player.worldObj.playerEntities.get(i);
                            if (player != null) {
                                if(var1 != null){
                                    var1.setDead(); //5) AFTER LOGOUT, NULL AGAIN
                                    ShadersFixer.logger.fatal("Entity removed: " + var1);
                                }
                                //playerTick(player, false);
                                height = player.posY - (double) player.yOffset + 0.0D;
                                var1 = new EntityLightingFix(player.worldObj);
                                var1.setPositionAndUpdate(player.posX, height, player.posZ);
                                ShadersFixer.logger.fatal("SPAWNED : " + var1);
                                player.worldObj.spawnEntityInWorld(var1); //2) NOT NULL , 6)NOT NULL AGAIN
                            }
                        }
                    }
                }
            }
        }
    }
*/


//public static EntityZombie var2 = null; // null

public static Logger logger = LogManager.getLogger();

public void mylogger (String s){
    logger.fatal(s);
}

    public static EntityZombie Zombie;

    private static final HashMap<UUID, EntityZombie> zombies = new HashMap<>();

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.player.worldObj.isRemote) {
            EntityPlayer player = event.player;
            UUID playerID = player.getUniqueID();
            
            if (zombies.containsKey(playerID)) {
                EntityZombie oldZombie = zombies.get(playerID);
                if (oldZombie != null) {
                    oldZombie.setDead();
                    event.player.worldObj.removeEntity(oldZombie);
                }
                zombies.remove(playerID);
            }

            EntityZombie newZombie = new EntityZombie(player.worldObj);
            newZombie.setPosition(player.posX, player.posY, player.posZ);
            player.worldObj.spawnEntityInWorld(newZombie);
            zombies.put(playerID, newZombie);

        }
    }

    @SubscribeEvent
    public void onWorldSave(WorldEvent.Save event) {
        if (!event.world.isRemote) {

            for (Map.Entry<UUID, EntityZombie> entry : zombies.entrySet()) {
                EntityZombie zombie = entry.getValue();
                if (zombie != null) {
                    zombie.setDead();
                    event.world.removeEntity(zombie);

                    // Get the chunk and mark it as modified
                    Chunk chunk = event.world.getChunkFromBlockCoords((int)zombie.posX, (int)zombie.posZ);
                    chunk.isModified = true;
                }
            }
            zombies.clear();
        }
    }

/*
    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Save event) {
        if(event.world != null){
            if (!event.world.isRemote) {
                if (event.world.getWorldTime() % ShaderFixerConfig.tickRatePlayerLoop == 0) {
                    for (int i = 0; i < event.world.playerEntities.size(); i++) {
                        EntityPlayer player = (EntityPlayer) event.world.playerEntities.get(i);
                        if (player != null) {
                            mylogger(" Unload WorldEvent... ");

                            if(!Zombie.isDead){
                                mylogger(" Zombie is not dead, killing it");
                                Zombie.setDead();

                                mylogger(" Just to be sure, removing it");
                                event.world.removeEntity(Zombie);

                            }
                            if(Zombie.isDead){
                                mylogger(" Zombie is dead, oh no... removing it from the hash");
                                zombies.remove(Zombie);
                            }

                            mylogger(player.getCommandSenderName() + " hashmap is... (below) ");
                            mylogger(zombies.toString());

                        }
                    }
                }
            }
        }
    }
*/


    @SubscribeEvent
    public void playerLoggedOutEvent (PlayerEvent.PlayerLoggedOutEvent event)
    {
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER)
        {
        }
    }










/**
    public static void spawnLighting(Entity player) {
        height = player.posY - (double) player.yOffset + 0.0D;
        var2 = new EntityZombie(player.worldObj);
        var2.setPositionAndUpdate(player.posX, height, player.posZ);
        ShadersFixer.logger.fatal("SPAWNED : " + var2);
        player.worldObj.spawnEntityInWorld(var2);
    }

    public static void removeEntity(EntityZombie entity, Entity player) {
        if (entity != null) {
            entity.setDead();
            player.worldObj.removeEntity(entity);
            ShadersFixer.logger.fatal("Entity " + entity +  " removed for player: " + player);
        }
    }*/

    /**
    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(ShaderFixerConfig.LightingFix) {
            if (event.player.worldObj != null) {
                if (!event.player.worldObj.isRemote) {
                    if (event.player.worldObj.getWorldTime() % ShaderFixerConfig.tickRatePlayerLoop == 0) {
                        for (int i = 0; i < event.player.worldObj.playerEntities.size(); i++) {
                            EntityPlayer player = (EntityPlayer) event.player.worldObj.playerEntities.get(i);
                            if (player != null) {
                                //playerTick(player, true);
                                if(var1 != null) { //3) SHOULD NOT BE NULL
                                    if(Keyboard.isKeyDown(Keyboard.KEY_G)){
                                        ShadersFixer.logger.warn("TRYING TO DELETE OLD! ");
                                        try {
                                            var1.setDead(); //4) DELETE NOT NULL?
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                    //ShadersFixer.logger.warn("X : " + var1.posX + " Y : " + var1.posY + " Z : " + var1.posZ);
                                    //height = player.posY - (double) player.yOffset + 0.0D;
                                    //var1.setPositionAndUpdate(player.posX, height, player.posZ);
                                } else {
                                    ShadersFixer.logger.warn("var1 = null?");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
*/

    /**
    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if(ShaderFixerConfig.LightingFix) {
            if (event.player.worldObj != null) {
                if (!event.player.worldObj.isRemote) {
                    if (event.player.worldObj.getWorldTime() % ShaderFixerConfig.tickRatePlayerLoop == 0) {
                        for (int i = 0; i < event.player.worldObj.playerEntities.size(); i++) {
                            EntityPlayer player = (EntityPlayer) event.player.worldObj.playerEntities.get(i);
                            if (player != null) {
                                if(var1 != null){
                                    var1.setDead();
                                    ShadersFixer.logger.fatal("Entity removed: " + var1);
                                }
                                //playerTick(player, false);
                                height = player.posY - (double) player.yOffset + 0.0D;
                                var1 = new EntityLightingFix(player.worldObj);
                                var1.setPositionAndUpdate(player.posX, height, player.posZ);
                                ShadersFixer.logger.fatal("SPAWNED : " + var1);
                                player.worldObj.spawnEntityInWorld(var1);
                            }
                        }
                    }
                }
            }
        }
    }
*/
}
