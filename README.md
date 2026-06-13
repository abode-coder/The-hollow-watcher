# The Hollow Watcher - Minecraft Horror Mod

A psychological horror mod for Minecraft 1.19.2 Fabric that features a stalking creature that gradually becomes more aggressive over time.

## Features

- **The Hollow Watcher**: A tall, skinny humanoid creature with unnaturally long limbs and a face hidden in shadows
- **Progressive Stalking**: The creature begins appearing after a few nights and slowly becomes more aggressive
- **Randomized Appearances**: Never know when it will appear - it changes poses between sightings
- **Psychological Horror**: Bone cracking sounds, distant screams, footsteps, random events
- **The Hollow Realm**: A dark dimension you're sent to if caught
- **Protection System**: Craft Holy Crosses and use ritual phrases to repel the creature
- **Ritual Ending**: Perform a complex ritual to banish the creature permanently
- **World Persistence**: Your stalking progress is saved - the creature remembers you

## Installation

1. Download the mod JAR from [Releases](https://github.com/abode-coder/the-hollow-watcher/releases)
2. Install [Fabric Loader](https://fabricmc.net/use/installer/) for 1.19.2
3. Place the JAR in your `.minecraft/mods` folder
4. Launch Minecraft with the Fabric profile

## How It Works

### The Stalking Phase
- The Hollow Watcher appears after the first few nights
- It stalks from 30-80 blocks away, never approaching directly
- It changes poses randomly: tilted head, unnaturally long arms, twisted body, crawling
- Bone cracking sounds become more frequent as it becomes more aggressive
- The creature remembers encounters - it gets progressively worse

### Protection
Craft a **Holy Cross** and hold it while typing these phrases in chat:
- `I see you.`
- `Return below.`
- `The gate is shut.`

### The Hollow Realm
If caught, you're sent to a dark dimension with:
- Thick fog and dead trees
- Random quests to complete
- Must survive to escape

### Final Ritual
To permanently banish the creature, gather:
- Holy Cross
- Ritual Candles (8)
- Ancient Pages (4)
- Blessed Iron Chains (2)
- Hollow Realm Fragments (3)

Then create a ritual circle and perform the final banishment.

## Crafting Recipes

### Holy Cross
```
I O I
O G O
I O I
```
(I = Iron Ingot, O = Oak Wood, G = Gold Ingot)

### Ritual Candle
```
W S W
W W W
```
(W = White Wool, S = String)

### Ancient Pages
```
P P P
P B P
P P P
```
(P = Paper, B = Book)

## Requirements

- **Minecraft Version**: 1.19.2
- **Loader**: Fabric Loader 0.14.25+
- **API**: Fabric API 0.76.0+
- **Java**: Java 17 or higher

## Configuration

The mod includes adjustable settings (coming in future versions):
- Stalking frequency
- Aggression speed
- Event rarity
- Sound volume multiplier

## Sound Design

The mod includes custom horror sounds:
- **Bones Cracking**: Distant, unsettling bone sounds
- **Distant Scream**: Haunting screams that grow louder
- **Footsteps**: Eerie footsteps behind you
- **Whispers**: Indecipherable whispers at close range

## Multiplayer

The mod works in both singleplayer and multiplayer:
- Each player has their own stalking progress
- The creature appears independently for each player
- World data is saved per-world

## Performance

- Optimized for low-end systems
- Minimal performance impact
- Custom entity rendering
- Efficient sound system

## Technical Details

- **Mod ID**: hollow-watcher
- **Entity**: HollowWatcherEntity (custom AI and rendering)
- **Dimension**: The Hollow Realm (custom biome generation)
- **Data Storage**: NBT-based world persistence
- **Rendering**: Custom model with 6 pose variations

## Changelog

### v1.0.0
- Initial release
- Core stalking mechanics
- Entity AI and rendering
- Sound system
- Protection items
- Hollow Realm dimension

## Known Issues

None reported yet!

## Credits

- **Developer**: abode-coder
- **Inspired by**: Analog horror, folklore, and psychological horror games

## License

MIT License - See [LICENSE](LICENSE) file for details

## Support

Have issues? Report them on [GitHub Issues](https://github.com/abode-coder/the-hollow-watcher/issues)

---

**⚠️ WARNING**: This mod is designed to be unsettling and creepy. Play with sound enabled for the best experience. Not recommended for young children.