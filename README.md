# Shader Fixer (1.7.10)

**[CurseForge](https://legacy.curseforge.com/minecraft/mc-mods/shader-fixer) | [Modrinth](https://modrinth.com/mod/shader-fixer)**

---

A mod (a.k.a. toxic wasteland) that attempts to improve visuals by patching various mods to fix issues with Angelica / Angelica Shaders (as well as minor QOL features :D)

**Use this mod at your own risk.** **Or not, as you wish.**

## Full list of supported mods:


### Vanilla Minecraft
  - Fixed incorrect rendering of many translucent things when rendering the entity's name tag | `VANILLA_NORMALS_DISABLE_TAG`
  - Fixed translucent particles becoming very dark with Complementary shaders enabled | `VANILLA_PARTICLE_ALPHA_WORKAROUND`
  - Removed 30 FPS lock in the main menu + added the ability to set an arbitrary value | `VANILLA_MAIN_MENU_FPS_BYPASS`
  - Fixed annoying bug due to which the effect bar in the creative menu turns black | `VANILLA_GUI_BLEND_FIX`
  - Fixed bug due to which the hand wouldn't update rotation when the player was sitting + added ability to turn hand's rotation off (similar to modern versions) | `VANILLA_RIDING_HAND_ROTATION_FIX`
  - Fixed experience bar black background if there is an item in the hotbar | `VANILLA_XP_BAR_ALPHA_FIX`

### Angelica
  - Added support for NTM_GUN_FIX (interpolation / ntmclient's GUN_MODEL_FOV) | `NTM_GUN_FIX`

### Avaritia [`AVARITIA_FIX`]
  - Fixed broken shadows when rendering tools/armor from the mod with shaders enabled
  - Fixed heaven arrow not having full brightness with shaders enabled

### Backhand
  - Added support for `NTM_GUN_FIX` (broken render otherwise)

### HBM's NTM [`NTM_MAIN_FIX`]
  - Fixed holding guns causing various (water/clouds visible through blocks, distorted shadows, other shader effects completely broken) issues with shaders enabled | `NTM_GUN_FIX`
  - Fixed various effects/particles have a black background with shaders enabled | `NTM_TEXTURE_FIX`
  - Fixed lamps on the M1TTY suit helmet not glowing with shaders enabled
  - Fixed fire diamond (NFPA 704) brightness with shaders enabled
  - Fixed meteorite sword render breaking entity lighting in 3rd person view
  - Fixed antimatter explosion not rendering with shaders enabled
  - Fixed folly/tau gun beam brightness with shaders enabled
  - Fixed black hole brightness with shaders enabled
  - Fixed antimatter beam (chemthrower) not rendering with shaders enabled
  - Fixed DFC core (running) casting shadows with shaders enabled / Fixed flare from DFC (jammed) not rendering with shaders enabled
  - Fixed orbital death ray not rendering with shaders enabled
  - Fixed demon core lamp radiation effect not rendering with shaders enabled
  - Fixed forcefield emitter field casting shadows with shaders enabled / Fixed forcefield emitter field brightness with shaders enabled
  - Fixed incorrect rendering of many translucent things when rendering the entity's name tag (armor VATS) / Fixed GL state leak when rendering some GUI elements
  - Fixed wires (electricity pylon) brightness with shaders enabled
  - Fixed Cherenkov radiation effect (RBMK column) not rendering with shaders enabled
  - Fixed UFO beam brightness with shaders enabled
  - Fixed Cherenkov radiation effect (research reactor) not rendering with shaders enabled
  - Fixed solar boiler beams not rendering with shaders enabled
  - Fixed DFC core sparks casting shadows with shaders enabled
  - Fixed digamma spear (RBMK) flash not rendering with shaders enabled
  - Fixed nuclear explosion brightness with angelica/shaders enabled 
  - Added extended tooltips for hazardous items (Explosion force: n / Ignites for: n second(s) / etc.) | `NTM_EXTENDED_HAZARD_DESCRIPTIONS`
  - Fixed holding two-handed weapons with Customizable Player Models mod | `NTM_ARMOR_FIX`

### HBM's NTM:Space [`NTM_MAIN_FIX`]
  - Fixed z-fighting between rocket thruster/fuselage with shaders enabled

### Fisk Superheroes [`FISKHEROES_FIX`]
  - Fixed multiple things (lasers/lightnings/etc.) not rendering with shaders enabled
  - Fixed suit database / suit fabricator brightness with shaders enabled

### Journeymap [`JOURNEYMAP_FIX`]
  - Fixed blinding waypoint (if shader has bloom) having brightness 255 when limit is 1

### Techguns [`TECHGUNS_FIX `]
  - Fixed crash (ClassCastException) when breaking a chest with shaders enabled
  - Fixed tesla gun projectile casting a shadow
  - Fixed explosion/rocket trail particles breaking water brightness
  - Fixed various effects/particles have a black background with shaders enabled | `TECHGUNS_TEXTURE_FIX`

### Schematica [`SCHEMATICA_FIX`]
  - Fixed highlight brightness with shaders enabled

### OpenComputers
  - Fixed screen rendering with shaders enabled | OC_DISABLE_DL

### Electrical Age
  - Fixed wires brightness with shaders enabled | ELN_DISABLE_DL

### Dynamic Surroundings [`DSURROUND_FIX`]
  - Fixed aurora rendering with shaders enabled

### SignPicture [`SIGNPIC_FIX`]
  - Fixed multiple GL leaks when rendering pictures
  - Fixed pictures casting shadows


## Requires [unimixins](https://github.com/LegacyModdingMC/UniMixins/releases)

