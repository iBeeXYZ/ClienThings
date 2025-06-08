## What's ClienThings ?
**ClienThings** is a project to simulate more things client-side.
For example, if you eat food, the animation only ends when the server indicates it will.
This mod fixes that and therefore compensates for the ping.
You can use ClienThings on your server to find out if a player is using **ClienThings**.

Currently this mod compensates for :
- Consumables (90%)
- Elytras (95%)
- Firework rocket (100%)
- Cooldowns (100%)

Coming Soon™ :
- Interfaces
- End Crystal Placement
- Game mode change
- Sneaking state
- Potion Effects
- Tchat
- Sounds
- Crafting
- Weapons (Bow/Crossbow, etc.)
- Note block
- Jukebox
- Swap Items In Hands
- Pick Block
- Scaffolding
- Knockback (in some case)
- Fall damage (maybe)
- Wind charge (maybe)


<details>
<summary>Configuration (default)</summary>
  
```
#consumables: Enables or disables consumable prediction
#consumables_MaxDistance: Maximum distance (in blocks) between the client and the server for the end-of-consumption sound(s) to be ignored
#consumables_MaxTime: Maximum time (in ms) the server has to play the end-of-consumption sound(s) to be ignored
#cooldowns: Enables or disables cooldowns prediction
#debugMode: Debug mode (for developers)
#elytras: Enables or disables elytras prediction
#firework: Enables or disables firework prediction
#firework_MaxDistance: Maximum distance (in blocks) between the client and the server for the firework sound to be ignored
#firework_MaxTime: Maximum time (in ms) the server has to delete the firework
#optout: Enables or disables the opt-out

consumables=true
consumables_MaxDistance=0.3
consumables_MaxTime=3200
cooldowns=true
debugMode=false
elytras=true
firework=true
firework_MaxDistance=2.0
firework_MaxTime=3200
optout=true
```

</details>

## How it works ?
Instead of waiting for an indication from the server, the client will simulate it at the right time, without delay.

## Disclaimer
This mod may be detected as a cheat by some anti-cheats. Use with caution on public servers.

## Complementary
If you use this mod, you might be interested by [No Bugs](https://modrinth.com/project/no-bugs)

## Compatibility
**ClienThings** should work on any client and server.
