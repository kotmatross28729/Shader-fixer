package com.kotmatross.shadersfixer.WIP;

import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.world.WorldEvent;

public class TEMP {

/*
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

/*
    @SubscribeEvent
    public void playerLoggedOutEvent (PlayerEvent.PlayerLoggedOutEvent event)
    {
        //Called only when running dedicated server and player logs out. Never called on client-side.
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.SERVER)
        {
            //I am not sure but seems like in my situation this if statement above is unnecesary since the event itself is alredy called only on dedicated server. Added it just "in case".
        }
    }
*/


/*
    @SubscribeEvent
    public void unloadingWorld (WorldEvent.Unload event)
    {
        //Called on both client and/or dedicated server.
        Side side = FMLCommonHandler.instance().getEffectiveSide();
        if (side == Side.CLIENT)
        {
            //Called only if you go into SP world, then quit, world is unloaded.
        }
    }
 */
}
