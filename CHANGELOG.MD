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
  * also improve some visuals with shaders

# 3.0

* Fix lighting bug in NEI
   - It also made it much brighter (side effect? actually using vanilla render so it looks like vanilla (and in vanilla it very bright))
   - Also optimize `renderMobSpawnOverlay` fix when using the original NEI


* Fix lighting bug in Schematica (full)


* Fixed a bug where the NEI overlay was causing some rendering to appear gray
    - For example: world tooltips, neat, schematica, etc.



* Fixed almost all bugs in HBM's NTM
  - some things related to weapons unfixed (since a rework is planned soon)
     - the hardest part is just ahead...
