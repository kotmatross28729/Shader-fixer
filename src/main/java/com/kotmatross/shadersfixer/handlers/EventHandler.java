package com.kotmatross.shadersfixer.handlers;

import com.kotmatross.shadersfixer.ShadersFixer;
import com.kotmatross.shadersfixer.WIP.EntityLightingFix;
import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.server.FMLServerHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.WorldEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;

import javax.management.relation.RelationSupport;
import java.util.*;

import static cpw.mods.fml.common.eventhandler.Event.Result.ALLOW;
import static cpw.mods.fml.common.eventhandler.Event.Result.DENY;

public class EventHandler {
    //TODO:
    //  -1) Server-side config option - 100% DONE
    //  -2) Accurate player tracking - 100% DONE
    //  -3) Accurate Fix-Entity Positioning - ?%
    //  4) In-game GUI menu with explanation about this fix - 0% (Replace with github wiki?)


public static Logger logger = LogManager.getLogger();

public static void mylogger (String s){
    logger.fatal(s);
}

    public static final HashMap<UUID, EntityLightingFix> Entities = new HashMap<>();

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

//            if (!event.player.worldObj.isRemote) {
//                for (Map.Entry<UUID, EntityLightingFix> entry : Entities.entrySet()) {
//                    EntityLightingFix acac = entry.getValue();
//                    if (acac != null) {
//                        acac.setDead();
//                        event.player.worldObj.removeEntity(acac);
//
//                        //Chunk chunk = event.player.worldObj.getChunkFromBlockCoords((int) acac.posX, (int) acac.posZ);
//                        //chunk.isModified = true;
//                    }
//                }
//                Entities.clear();
//            }
//        }
    }


//    @SubscribeEvent
//    public void LivingSpawnEvent(LivingSpawnEvent.AllowDespawn event) {
//        event.setResult(ALLOW);
//    }

//    public boolean alpha = false;

//    @SubscribeEvent
//    public void ButtonClick(GuiScreenEvent.ActionPerformedEvent.Pre event) {
//        mylogger("BUTTON PRESSED");
//        if(event.button.id == 1){
//            alpha = true;
//            mylogger("YE");
//        } else {
//            alpha = false;
//            mylogger("NO");
//        }
//    }

/**
    @SubscribeEvent
    public void onWorldSave(WorldEvent.Save event) {
        if (!event.world.isRemote) {
  //          mylogger("alpha - " + alpha);
  //          if (alpha){
                for (Map.Entry<UUID, EntityLightingFix> entry : Entities.entrySet()) {
                    EntityLightingFix acac = entry.getValue();
                    if (acac != null) {
                        acac.setDead();
                        event.world.removeEntity(acac);

                        Chunk chunk = event.world.getChunkFromBlockCoords((int) acac.posX, (int) acac.posZ);
                        chunk.isModified = true;
                    }
                }
                Entities.clear();
            }
  //      }
    }
*/


/*
    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        if (!event.world.isRemote) {
            for ( EntityPlayer player : event.world.loadedEntityList) {
                UUID playerID = player.getUniqueID();
                EntityLightingFix ent = Entities.remove(playerID);
                if (ent != null) {
                    ent.setDead();
                    event.world.removeEntity(ent);

                    Chunk chunk = event.world.getChunkFromBlockCoords((int) ent.posX, (int) ent.posZ);
                    chunk.isModified = true;
                }
            }
        }
    }*/

    /**
    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
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
    */

/**
    @SubscribeEvent
    public void onWorldSave(WorldEvent.Save event) {
        if (!event.world.isRemote) {
                for (Map.Entry<UUID, EntityLightingFix> entry : aab.entrySet()) {
                    EntityLightingFix acac = entry.getValue();
                    if (acac != null) {
                        acac.setDead();
                        event.world.removeEntity(acac);

                        // Get the chunk and mark it as modified
                        Chunk chunk = event.world.getChunkFromBlockCoords((int) acac.posX, (int) acac.posZ);
                        chunk.isModified = true;
                    }
                }
                aab.clear();
        }
    }
*/


/**
    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        mylogger("a");
        if (!event.world.isRemote) {
            mylogger("b");
            for (Map.Entry<UUID, EntityLightingFix> entry : aab.entrySet()) {
                mylogger("c");
                EntityLightingFix acac = entry.getValue();
                mylogger("d");
                if (acac != null) {
                    mylogger("e");
                    acac.setDead();
                    mylogger("f");
                    event.world.removeEntity(acac);
                    mylogger("g");
                    // Get the chunk and mark it as modified
                    Chunk chunk = event.world.getChunkFromBlockCoords((int) acac.posX, (int) acac.posZ);
                    mylogger("h");
                    chunk.isModified = true;
                    mylogger("i");
                }
                mylogger("u");
            }
            mylogger("y");
            aab.clear();
        }
    }

*/

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

/**
    @SubscribeEvent
    public void playerLoggedOutEvent (PlayerEvent.PlayerLoggedOutEvent event)
    {
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER)
        {

        }
    }
*/









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
