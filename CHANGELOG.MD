# 3.4
* **[Hbm's NTM]** Fixed fire diamond (NFPA 704) brightness with angelica shaders
* **[Hbm's NTM]** Fixed nuclear explosion brightness (was completely black) with angelica shaders 
* **[Vanilla]** Fixed bug due to which the hand wouldn't update rotation when the player was riding/sitting
  * Also added a config option that disables hand rotation when the player is riding/sitting (similar to newer versions) [`DisableRiddingHandRotation`]

# 3.3 hot-fix-patch-5
*Last patch (I hope)*

* **[Hbm's NTM:Space]** Completely fixed horizon/sunset with angelica shaders
* **[Hbm's NTM]** Fixed rendering of refueler liquid with angelica shaders (liquid was rendered under refueler, without clip)
* **[Hbm's NTM]** Fixed lamp on the miner's helmet with angelica shaders
* **[Hbm's NTM]** Fixed lamp on the charger with angelica shaders
* **[Hbm's NTM]** Fixed fire rendering in a steel furnace with angelica shaders

# 3.3 hot-fix-patch-4

*Yeah, I'm too lazy to change 3.3 to 3.4, that's why we now have patches (awhile)*

* **[Hbm's NTM:Space]** Fixed incorrect rendering of an orbiting planet in orbit (space station) (appearing translucent / cut in half / wrong colors, etc.)
* **[Hbm's NTM]** Fixed DFC core flare rendering (meltdown with High Energy Field Jammer) with Complementary Shaders
* **[Hbm's NTM]** Fixed Cherenkov radiation not rendered on the RBMK fuel rod with Complementary Shaders
  * For some reason, after NTM optimization update, there is no Cherenkov radiation effect (`lastFluxQuantity` broken?)
* **[Hbm's NTM]** Fixed flare (projectile) brightness with angelica shaders
* **[Hbm's NTM]** Fixed ICF/PWR Controller marker GL state leak with angelica

# 3.3 hot-fix-patch-3

* **[Hbm's NTM:Space]** Fixed sunset not rendered with angelica shaders
* **[Hbm's NTM:Space]** Fixed GL state leak on laythe (sunset) when opening the angelica shaders menu
  * Due to the use of a different blend function, the laythe sunset looks different (in any case, better than that horror without blend)

# 3.3 hot-fix-patch-2

* **[Hbm's NTM]** (Finally) fixed antimatter explosion not rendered with Complementary Shaders
* **[Hbm's NTM]** Fixed mirror rays (for solar boiler) not rendered with angelica shaders (may flicker a little - this is normal)
* **[Hbm's NTM]** Fixed Cherenkov radiation not rendered on the research reactor with angelica shaders
* **[Hbm's NTM]** Fixed Cherenkov radiation not rendered on the demon lamp with Complementary Shaders 
* **[Hbm's NTM]** Fixed DFC explosion rendering with angelica shaders

# 3.3 hot-fix-patch-1

* **[Hbm's NTM:Space]** Fixed white square near the sun with angelica shaders
* **[Hbm's NTM:Space]** Fixed blinding white bar on laythe with Complementary Shaders
* **[Hbm's NTM:Space]** """Fixed""" eye-ripping black bottom of horizon with Complementary Shaders (only works if player is below ~219 Y)

# 3.3 hot-fix

* **[Hbm's NTM:Space]** Fixed crash with new `X5244_H261` update

# 3.3

* **[Hbm's NTM]** Fixed brightness of NTM black holes with angelica shaders
* **[CustomNPC]** Fixed NPC labels with shaders
* **[Zeldaswordskills]** Fixed hookshot/clawshot/multishot with shaders
* Fixed `NumberFormatException` / `NoClassDefFoundError` in rare cases


# 3.2

* Fixed new Hbm's NTM gun rework with shaders
  - Now all bugs are DEFINITELY fixed (please)
  - The `/ntmclient set GUN_MODEL_FOV true` command is known to not work with optifine. Also, aiming with the StG 77 (AUG) weapon looks very choppy with optifine (no interpolation). These problems will NOT be fixed, due to the fact that optifine is closed source and applies changes in “patches”, which is why any modification of data changed by optifine is impossible
    - These problems can be avoided by simply using angelica instead of optifine
* Added a setting to bypass the 30 fps limit in the main menu
* Fixed Dynamic Surroundings aurora with shaders
* Fixed Hardcore Ender Expansion with shaders
* Added mixin that disables mob rendering in LittleMaidMobEnhanced interaction GUI to fix shaders
* Fixed crash with RPLE and NEI
* Optimized code (performance improvements at the measurement error level)
* Added option for extended tooltips for dangerous items (hbm's ntm)
  - Asbestos/Coal dust items show how many "units" of dust they emit per tick
  - Explosive/Hydroactive items show the force of the explosion
  - Blinding/Pyrophoric items show for how many seconds they blind/ignite
    - If player has a tungsten reaches, then tooltip for pyrophoric items will be gray and crossed out

# 3.1

* Complete HBM's NTM fix
  * Also improve some visuals with shaders

# 3.0

* Fix lighting bug in NEI
   - It also made it much brighter (side effect? actually using vanilla render, so it looks like vanilla (and in vanilla it very bright))
   - Also optimize `renderMobSpawnOverlay` fix when using the original NEI


* Fix lighting bug in Schematica (full)


* Fixed a bug where the NEI overlay was causing some rendering to appear gray
    - For example: world tooltips, neat, schematica, etc.


* Fixed almost all bugs in HBM's NTM
  - some things related to weapons unfixed (since a rework is planned soon)
     - the hardest part is just ahead...
