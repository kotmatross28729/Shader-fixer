package com.kotmatross.shadersfixer;

import com.kotmatross.shadersfixer.WIP.EntityLightingFix;
import com.kotmatross.shadersfixer.config.ShaderFixerConfig;
import com.kotmatross.shadersfixer.proxy.CommonProxy;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import techguns.client.GoreData;
import techguns.client.models.ModelGibs;
import techguns.client.models.ModelGibsBiped;

import static techguns.client.DeathEffect.goreStats;

@Mod(modid = Tags.MODID,
    version = Tags.VERSION,
    name = Tags.MODNAME,
    acceptedMinecraftVersions = Tags.MCVERSION,
    dependencies =
        "required-after:gtnhmixins@[2.0.0,);")
public class ShadersFixer {

    @Mod.Instance(Tags.MODID)
    public static ShadersFixer instance;
    @SidedProxy(clientSide = Tags.CLIENTPROXY, serverSide = Tags.SERVERPROXY)
    public static CommonProxy proxy;

    public static Configuration config;
    public static final Logger logger = LogManager.getLogger();


    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerEvents();
        if(ShaderFixerConfig.FixRivalRebelsShaders) {
            if (Loader.isModLoaded("rivalrebels")) {
                if (!Loader.instance().getIndexedModList().get("rivalrebels").getVersion().contains(" fixed")) {
                    throw new RuntimeException("You are using a version of Rival Rebels that is not compatible with ShadersFixer, please update to: https://github.com/kotmatross28729/Rival-Rebels-Mod/releases");
                }
            }
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {}

    //TODO:
    //  1) Server-side config option
    //  2) Accurate player tracking
    //  3) Accurate Fix-Entity Positioning
    //  4) In-game GUI menu with explanation about this fix
    //  5)
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
    public static void onWorldJoinEvent(EntityJoinWorldEvent event) {
        if ((event.entity instanceof EntityPlayer)) {
            ((EntityPlayer) event.entity).addChatComponentMessage(new ChatComponentText("Joined :  " + event.entity)); //TODO: remove when complete

            double height = event.entity.lastTickPosY - (double)event.entity.yOffset + 0.0D; //TODO: check event.entity.yOffset

            EntityLightingFix E_L_F = new EntityLightingFix(event.entity.worldObj); //TODO: check worldObj != null?

            //E_L_F.setPosition(event.entity.posX, height, event.entity.posZ);
            E_L_F.setPosition(event.entity.lastTickPosX, height, event.entity.lastTickPosZ); //TODO: check lastTick

            event.entity.worldObj.spawnEntityInWorld(E_L_F); //TODO: null checks maybe?
        }
    }

    @SubscribeEvent
    public void tickServer(TickEvent.ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            instance.onTick(MinecraftServer.getServer()); //TODO: check
        }
    }
    public void onTick(MinecraftServer var1) {
        onTickInGame(var1); //TODO: Useless? Direct `instance.onTickInGame(MinecraftServer.getServer());` ???
    }
    public static MinecraftServer mc;
    public static World world;
    public void onTickInGame(MinecraftServer var1) {
        mc = var1;
        World worldT = world; //TODO: check
        if (worldT != null) {
            worldT = mc.worldServerForDimension(0); //TODO: other dimensions? Maybe Shaders.shaderPackDimensions based? If so, then force other mods detection based system (Psychedelicraft or smt)?
            if (worldT != null) {
                worldTick(worldT); //TODO
            }
        }
    }

    /**
     * TODO: main thing
     * @author Kotmatross
     */
    public void worldTick(World world) {} //TODO: teleport (setPosition) to player (and track)

}
