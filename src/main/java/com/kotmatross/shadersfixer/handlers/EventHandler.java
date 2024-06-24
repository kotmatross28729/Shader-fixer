package com.kotmatross.shadersfixer.handlers;

import com.kotmatross.shadersfixer.WIP.EntityLightingFix;
import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class EventHandler {
// pretend thus
    //TODO:
    //  1) Server-side config option - 40%
    //  2) Accurate player tracking - ?%
    //  -3) Accurate Fix-Entity Positioning - 100%
    //  4) In-game GUI menu with explanation about this fix - 0%
    //  5) - 0%
    //  if( TodoList("4)").checkStatus() == StatusEnum.DONE)
    //  {
    //      Kotmatross.getOffAssAndDo(Action a = new Action(Const.read("Pictures (that explain and show difference between fix) in the GUI")));
    //  }
    // ,
    //                     █
    //                    ███
    //                   █████
    //                  ███████
    //                 █████████
    //                ███████████
    //               █████████████
    //              ███████████████
    //             █████████████████
    //            ███████████████████
    //           █████████████████████
    //          ███████████████████████
    //         █████████████████████████
    //        ███████████████████████████
    //       █████████████████████████████
    //      ███████████████████████████████
    //                  ██████
    //                  ██████
    //                  ██████
    //                  ██████
    //                  ██████
    //                  ██████
    //                  ██████
    //                  ██████
    //                  ██████
    //                  ██████
    //                  ██████
    //                  ██████
    //                  ██████
    //                  Cringe.

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if(ShaderFixerConfig.LightingFix) {
            if (event.player.worldObj != null) {
                if (!event.player.worldObj.isRemote) {
                    if (event.player.worldObj.getWorldTime() % ShaderFixerConfig.tickRatePlayerLoop == 0) {
                        for (int i = 0; i < event.player.worldObj.playerEntities.size(); i++) {
                            EntityPlayer player = (EntityPlayer) event.player.worldObj.playerEntities.get(i);
                            if (player != null) {
                                playerTick(player);
                            }
                        }
                    }
                }

            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(ShaderFixerConfig.LightingFix) {
            if (event.player.worldObj != null) {
                if (!event.player.worldObj.isRemote) {
                    if (event.player.worldObj.getWorldTime() % ShaderFixerConfig.tickRatePlayerLoop == 0) {
                        for (int i = 0; i < event.player.worldObj.playerEntities.size(); i++) {
                            EntityPlayer player = (EntityPlayer) event.player.worldObj.playerEntities.get(i);
                            if (player != null) {
                                playerTick(player);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void spawnLightingFix(Entity player) {
        double height = player.posY - (double)player.yOffset + 0.0D;
        EntityLightingFix var1 = new EntityLightingFix(player.worldObj);
        var1.setPosition(player.posX, height, player.posZ);
        player.worldObj.spawnEntityInWorld(var1);
    }

    public static void playerTick(EntityPlayer player) {
        if(player.ticksExisted % ShaderFixerConfig.tickRateLightingFix == 0) {
                spawnLightingFix(player);
        }

    }
}
