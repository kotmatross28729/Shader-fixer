package com.kotmatross.shadersfixer.LightingFix;

import com.kotmatross.shadersfixer.shrimp.Fucked;
import com.kotmatross.shadersfixer.shrimp.FuckingCursed;
import com.kotmatross.shadersfixer.shrimp.FuckingShit;

@Deprecated @Fucked @FuckingCursed @FuckingShit
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
/*
    @EventHandler
    public void onServerStopping(FMLServerStoppingEvent event) {
        if(FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            // Do stuff only for Single Player / integrated server
            MinecraftServer mc = FMLClientHandler.instance().getServer();
            String allNames[] = mc.getAllUsernames().clone();
            for(int i = 0; i < allNames.length; i++) {
                // For 1.7.10, func_152612_a = getPlayerForUsername
                EntityPlayerMP player = MinecraftServer.getServer().getConfigurationManager().func_152612_a(allNames[i]);
                PlayerData.savePlayerData((EntityPlayerMP)player);
            }
        } else {
            // Do stuff only for dedicated server *shutdown*, for individual players logging out hook PlayerLoggedOutEvent in an EventBus subscription instead
        }
    }
    */
}
