# 4.8
* **[Fisk's Superheroes]** Fixed some beams not rendering with Complementary Shaders 5.x
  - No side effects this time!

# 4.7

* **[Hbm's NTM]** Fixed Tesla Crab/Meltdown Elemental turning completely white/green respectively when attacking
* **[Hbm's NTM:Space]** Fixed lasers (Tesla Crab/Meltdown Elemental/Mining Laser) having alpha greater than 1.0 (in `glColor4f`)
  - A.K.A. something that will burn your eyes if the 'Bloom' option in the shader is enabled
* **[Main]** Fixed numerous light leaks (entities are glowing) caused by `Utils.EnableFullBrightness()`

# 4.6-patch-3

* **[Hbm's NTM]** (`EXTENDED_HAZARD_DESCRIPTIONS`) Changed "Blinding for:" to "Blinds for:" / "Danger:" to "Coal dust/s:" and "Asbestos/s:" respectively
* **[Hbm's NTM]** (`EXTENDED_HAZARD_DESCRIPTIONS`) Fixed a time rounding bug where some items had 0 seconds of effect
* **[Hbm's NTM]** `MUZZLE_FLASH_ENABLE_DEPTH` config option is now disabled by default (causes z-fighting)
  
# 4.6-patch-2

#### NTM require version [X5439+](https://github.com/HbmMods/Hbm-s-Nuclear-Tech-GIT/releases)!
#### NTM:Space require version [X5439_H261+](https://github.com/JameH2/Hbm-s-Nuclear-Tech-GIT/releases)!

* **[Hbm's NTM]** Fixed NI4NI coins display with shaders

# 4.6-patch-1
* **[Main]** Fixed incorrect limbs rotation when `VANILLA_MODERN_RIDING_LEG_POS` is enabled

# 4.6

* **[Customizable Player Models]** Added experimental fix for models that have untextured parts
  - Config - `minecraft\config\shaderfixer\mixinsLate_GENERAL.cfg -> CPM_EXP_FIX`
* **[Hbm's NTM:Space]** Fixed missing sunspike with shaders

# 4.5

#### NTM:Space require version [X5420_H261+](https://github.com/JameH2/Hbm-s-Nuclear-Tech-GIT/releases)!

* **[Hbm's NTM:Space]** Support fixes for X5420_H261 update

# 4.4

#### NTM require version [insert next release](insert next release)!
#### NTM:Space require version [insert next release](insert next release)!

* **[Hbm's NTM]** Fixed incorrect STG77 aim
* **[Hbm's NTM]** Fixed rebar placer outline rendering with shaders
* **[NotEnoughItems]** Fixed bug due to which fixes were not applied
* **[Main]** Fixed crash with MCPatcherForge (Angelica's module)

# 4.3

* **[Main]** [NTM texture patch for shaders](https://modrinth.com/resourcepack/ntm-texture-patch-for-shaders) now included in Shader Fixer
  - Config - `minecraft\config\shaderfixer\mixinsLate_GENERAL.cfg -> NTM_TEXTURE_FIX`

* **[Fisk's Superheroes]** Fixed animations breaking when `VANILLA_MODERN_RIDING_LEG_POS` is enabled
* **[Hbm's NTM:Space]** Fixed orrery rendering with shaders
* **[Hbm's NTM / NTM:Space]** Fixed digamma and ~~[͊͆͒l̢o̴̺͎̿̈́͜͝d̸͕̼̾͌̐e̠]~~ stars not rendered with shaders


# 4.2
## Requires [UniMixins 0.1.22+](https://github.com/LegacyModdingMC/UniMixins/releases)!

* Fixed crash with Fisk's Superheroes
* Added `VANILLA_DISABLE_HORIZON` config option 
  * Disables horizon rendering
* Added `NTM_SPACE_DISABLE_PLANET_RENDER` config option
  * Disables rendering of the planet under the player, at high altitude


# 4.1
## Requires [UniMixins 0.1.22+](https://github.com/LegacyModdingMC/UniMixins/releases)!

* Fixed crash with MapleTree
* Fixed crash if Hbm's NTM is missing

# 4.0
## Requires [UniMixins 0.1.22+](https://github.com/LegacyModdingMC/UniMixins/releases)!

* Updated mixins to the new MixinsBuilder system
* General code cleaning

# 3.9
* Some configuration options are now disabled by default (due to their non-functionality)
  * They are marked with `[UNSTABLE]`
* **[NotEnoughItems]** Fixed crash with original version
* **[Fisk's Superheroes]** ATTEMPT to fix "that one" bug (some things just don't render) with Complementary Shaders 5.x
  * Disabled by default, config - `FixFisksuperheroesEXPProgramFix` (`minecraft\config\shadersfixer\mixins.cfg`)
  * It hurts me to say this, but it seems to work with optifine, side effects appear with angelica
  * If using angelica, only enable if using Complementary Shaders 5.x, not required for other shaders

# 3.8
#### NTM require version [insert next release](insert next release)!
#### NTM:Space require version [insert next release](insert next release)!

---
* **[Hbm's NTM:Space]** Removed fixes that are now included in the fork
* **[Hbm's NTM:Space]** City Lights now work with shaders (thanks to Mellow!)
* **[Hbm's NTM]** Fixed white squares (instead of missile symbols) on the radar screen
* **[Hbm's NTM]** Added `HbmMuzzleFlashEnableDepth` config option 
  * Disabled by default, enable only if the resource pack ["NTM texture patch for shaders"](https://modrinth.com/resourcepack/ntm-texture-patch-for-shaders) is enabled
  * Enables writing to the depth buffer back, fixes all sorts of errors with shader effects (clouds/water, etc.)
* **[Hbm's NTM:Space]** Fixed fuselage Z-fighting with shaders

# 3.7
#### NTM:Space fixes require version [X5356_H261](https://github.com/JameH2/Hbm-s-Nuclear-Tech-GIT/releases/tag/xspace33b)!

---
* **[Hbm's NTM:Space]** Fixed orbit (space station) rendering (again)
* **[Hbm's NTM:Space]** Fixed sun Z-fighting with angelica shaders (again)
* **[Hbm's NTM]** Fixed black hole discs/swirls brightness with angelica shaders
* **[Hbm's NTM]** Fixed flickering of fast tools/weapons (chainsaw/minigun) when used
* **[Hbm's NTM:Space]** Fixed impact effects from tom (gerald) on other planets are rendered "forever" (freeze)

# 3.6-patch-2
* **[Backhand]** Disabled offhand rendering if NTM gun is in main hand (render conflict)
  * Config option - [`DisableBackhandRenderWithGunFix`] (Not recommended to turn it off)

# 3.6-patch-1
* **[Hbm's NTM]** Fixed potential crash related to I18n in future versions (X5336+)

# 3.6
* **[Hbm's NTM]** Fixed charge thrower grappling hook's line brightness with angelica shaders
* **[Vanilla]** Changed the position of the legs so that they no longer extend beyond Et Futurum Requiem boat 
  * Config option - [`ModernRidingLegsPos`]

# 3.5
* **[Customizable Player Models]** Fixed nametag rendering
* **[Hbm's NTM]** Fixed rendering of akimbo weapons for non-player mobs
* **[FindIt]** Fixed highlighter rendering
* **[Fisk's Superheroes]** Fixed armor -> Customizable Player Models compat (by Darek505)
* **[Fisk's Superheroes]** Fixed tentacles z-buffer with shaders
* **[MapleTree]** Fixed rendering with shaders

# 3.4-patch-2
* **[Hbm's NTM]** Fixed some b-side guns using the texture of the original gun
  * !!! Need to wait until the full release of both the main and space fork, otherwise crash
* **[3DManeuverGear]** Fixed rendering with shaders
* **[Balkon's WeaponMod]** Fixed rendering with shaders

# 3.4-patch-1
* **Added crash if Optifine and Hbm's NTM:Space are installed**
  * Due to horrible flickering bugs
  * **Can still be disabled in config**, but not recommended
* **[JourneyMap]** Fixed crash with new versions (5.2.3+)
* **[Hbm's NTM]** Fixed wires (pylons) brightness with angelica shaders
* **[Hbm's NTM:Space]** Fixed sun Z-fighting with angelica shaders
* **[Hbm's NTM:Space]** Bring back Laythe sunset, should now be fixed without disabling glBlendFunc
* **[SignPicture]** Fixed rendering with shaders
* **[SignPicture]** Fixed brightness when rendering vanilla signs with angelica shaders

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
