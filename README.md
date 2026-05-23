# ShaderFixer

[![github](images/badges/github.png)](https://github.com/kotmatross28729/Shader-fixer)
[![curseforge](images/badges/curse.png)](https://www.curseforge.com/minecraft/mc-mods/shader-fixer)
[![modrinth](images/badges/modrinth.png)](https://modrinth.com/mod/shader-fixer)
![forge](images/badges/forge.png)

---

A **set of patches** for various mods, fixes **some** issues with Angelica / Angelica Shaders. Also has QoL features and vanilla fixes

*Do not expect this mod to fix all your shader problems, it only fixes what is explicitly stated below.*

*Use this mod at your own risk. Contains experimental fixes.*

## Dependencies
  - [UniMixins](https://github.com/LegacyModdingMC/UniMixins/releases) [![curse](images/icons/curse.png)](https://www.curseforge.com/minecraft/mc-mods/unimixins) [![modrinth](images/icons/modrinth.png)](https://modrinth.com/mod/unimixins/versions) [![git](images/icons/git.png)](https://github.com/LegacyModdingMC/UniMixins/releases)

## Incompatibilities
  - [Optifine](https://optifine.net) |  Use [Angelica](https://github.com/GTNewHorizons/Angelica) [![curse](images/icons/curse.png)](https://www.curseforge.com/minecraft/mc-mods/angelica) [![modrinth](images/icons/modrinth.png)](https://modrinth.com/mod/angelica/versions) [![git](images/icons/git.png)](https://github.com/GTNewHorizons/Angelica/releases) instead

## Supported mods:

**Vanilla Minecraft**
  - Fixed incorrect translucent renderer when rendering entity's name tag | `VANILLA_NORMALS_DISABLE_TAG`
  - [Shaders] Fixed translucent particles becoming dark with Complementary | `VANILLA_PARTICLE_ALPHA_WORKAROUND`
  - Removed 30 FPS lock in main menu, added ability to set it to arbitrary value | `VANILLA_MAIN_MENU_FPS_BYPASS`
  - Fixed effect bar in creative menu turns black | `VANILLA_GUI_BLEND_FIX`
  - Fixed first person hand doesn't update rotation when player was sitting, added ability to turn hand's rotation off (modern minecraft behavior) | `VANILLA_RIDING_HAND_ROTATION_FIX`
  - Fixed experience bar black background if there is an item in hotbar | `VANILLA_XP_BAR_ALPHA_FIX`

**Angelica**
  - [Shaders] Added support for `NTM_GUN_FIX`

**Avaritia [`AVARITIA_FIX`]**
  - [Shaders] Fixed broken shadows when rendering cosmic tools/armor
  - [Shaders] Fixed heaven arrow not having full brightness

**Backhand**
  - [Shaders] Added support for `NTM_GUN_FIX`

**HBM's NTM [`NTM_MAIN_FIX`]**
  - [Shaders] Fixed holding guns causing various issues (water/clouds visible through blocks, incorrect lighting, etc.) | `NTM_GUN_FIX`
  - [Shaders] Fixed some particles have a black background | `NTM_TEXTURE_FIX`
  - [Shaders] todo (new shadow map horrible workaround here)
  - [Shaders] Fixed M1TTY suit lamps on helmet not glowing
  - [Shaders] Fixed fire diamond (NFPA 704) on barrels brightness
  - Fixed meteorite sword render breaking entity lighting in 3rd person view
  - [Shaders] Fixed antimatter explosion not rendering
  - [Shaders] Fixed folly/tau gun beam brightness
  - [Shaders] Fixed black hole brightness
  - [Shaders] Fixed antimatter beam (chemthrower) not rendering
  - [Shaders] Fixed DFC core (running) casting shadows
  - [Shaders] Fixed flare from DFC (jammed) not rendering
  - [Shaders] Fixed orbital death ray not rendering
  - [Shaders] Fixed demon core lamp radiation effect not rendering
  - [Shaders] Fixed forcefield emitter field casting shadows
  - [Shaders] Fixed forcefield emitter field brightness
  - Fixed incorrect translucent renderer when rendering entity's name tag (armor VATS)
  - [Shaders] Fixed GL state leak when rendering some GUI elements
  - [Shaders] Fixed wires (electricity pylon) brightness
  - [Shaders] Fixed Cherenkov radiation effect (RBMK column) not rendering
  - [Shaders] Fixed UFO beam brightness
  - [Shaders] Fixed Cherenkov radiation effect (research reactor) not rendering
  - [Shaders] Fixed solar boiler sun beams not rendering
  - [Shaders] Fixed DFC core sparks casting shadows
  - [Shaders] Fixed digamma spear (RBMK) flash not rendering
  - [Shaders] Fixed nuclear explosion brightness
  - Added extended tooltips for hazardous items (Explosion force: n / Ignites for: n second(s) / etc.) | `NTM_EXTENDED_HAZARD_DESCRIPTIONS`
  - Fixed holding two-handed weapons with Customizable Player Models mod | `NTM_ARMOR_FIX`

**Fisk Superheroes [`FISKHEROES_FIX`]**
  - [Shaders] Fixed lasers/lightnings/etc. not rendering
  - [Shaders] Fixed suit database / suit fabricator brightness

**Techguns [`TECHGUNS_FIX `]**
  - [Shaders] Fixed crash (ClassCastException) when breaking a chest
  - [Shaders] Fixed tesla gun projectile casting a shadow
  - Fixed explosion/rocket trail particles breaking water brightness
  - [Shaders] Fixed various effects/particles have a black background | `TECHGUNS_TEXTURE_FIX`

**Schematica [`SCHEMATICA_FIX`]**
  - [Shaders] Fixed highlight brightness

**OpenComputers**
  - [Shaders] Fixed screen rendering | `OC_DISABLE_DL`

**Electrical Age**
  - [Shaders] Fixed wires brightness | `ELN_DISABLE_DL`

**Dynamic Surroundings [`DSURROUND_FIX`]**
  - [Shaders] Fixed aurora rendering

**SignPicture [`SIGNPIC_FIX`]**
  - Fixed multiple GL leaks when rendering pictures
  - [Shaders] Fixed pictures casting shadows