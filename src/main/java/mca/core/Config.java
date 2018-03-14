package mca.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import mca.enums.EnumRace;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public final class Config implements Serializable {
	private transient final Configuration config;

	public int baseEntityId;
	public boolean disableWeddingRingRecipe;

	public boolean overwriteOriginalVillagers;
	public boolean allowMobAttacks;
	public boolean shiftClickForPlayerMarriage;
	public boolean giveCrystalBall;
	public boolean enableDiminishingReturns;
	public boolean enableInfection;
	public boolean enableStructureSpawning;
	public boolean serverEnableStructureSpawning;
	public boolean allowVillagerRevival;
	public Integer[] dimensionWhitelist;
	public String[] additionalGiftItems;
	public int guardSpawnRate;
	public int chanceToHaveTwins;
	public int villagerMaxHealth;
	public int villagerAttackDamage;
	public int guardMaxHealth;
	public int guardAttackDamage;
	public int orcAttackDamage;
	public int elfAttackDamage;
	public boolean storyProgression;
	public int storyProgressionThreshold;
	public int storyProgressionRate;
	public int marriageHeartsRequirement;
	public int tradeHeartsRequirement;
	public int hiringHeartsRequirement;

	public int roseGoldSpawnWeight;

	public int babyGrowUpTime;
	public int childGrowUpTime;
	public boolean isAgingEnabled;

	public int childLimit;
	public int villagerSpawnerCap;
	public int storyProgressionCap;
	public boolean allowFarmingChore;
	public boolean allowFishingChore;
	public boolean allowWoodcuttingChore;
	public boolean allowMiningChore;
	public boolean allowHuntingChore;
	public boolean allowGiftDemands;
	public boolean allowTrading;
	public boolean logVillagerDeaths;
	public boolean spawnInAllDimensions;
	public boolean replenishEmptyVillages;
	public String villagerChatPrefix;
	public String serverLanguageId;

	public boolean showMoodParticles;
	public boolean showNameTagOnHover;
	public boolean showVillagerConversations = true;
	public boolean modifyFemaleBody;
	public boolean allowBlinking;

	public boolean inTutorialMode;

	public boolean allowCrashReporting;
	public boolean allowUpdateChecking;
	public boolean showPlayerDataMigrationErrors;
	private int matingSeasonDuration;

	private List<EnumRace> seasonalBreeders = new ArrayList<EnumRace>();

	public Config(FMLPreInitializationEvent event) {
		config = new Configuration(event.getSuggestedConfigurationFile());
		addConfigValues();
	}

	private void addConfigValues() {
		seasonalBreeders.add(EnumRace.Orc);
		config.setCategoryComment("Init", "Settings that affect how MCA starts up.");
		baseEntityId =
				config.get("Init",
						"Base Entity ID",
						227,
						"The base ID to use for entities in MCA. Only change if you know what you are doing!").getInt();
		disableWeddingRingRecipe =
				config.get("Init",
						"Disable wedding ring recipe",
						false,
						"True if you want to disable the recipe for the wedding ring. It can confict with a few mods. Rose gold can be used as an alternative.")
						.getBoolean();

		config.setCategoryComment("Privacy", "Setting pertaining to your privacy while using MCA.");
		allowCrashReporting =
				config.get("Privacy",
						"Allow crash reporting",
						true,
						"True if MCA can send crash reports to the mod authors. Crash reports may include your Minecraft username, OS version, Java version, and PC username.")
						.getBoolean();
		allowUpdateChecking =
				config.get("Privacy",
						"Allow update checking",
						true,
						"True if MCA can check for updates. This setting requires a restart in order to take effect.")
						.getBoolean();

		config.setCategoryComment("General", "General mod settings.");

		giveCrystalBall =
				config.get("General",
						"Give crystal ball",
						true,
						"Toggles giving the crystal ball to new players on join. WARNING: If this is false, you must spawn the crystal ball in later manually!")
						.getBoolean();
		overwriteOriginalVillagers = config.get("General", "Overwrite original villagers", true).getBoolean();
		shiftClickForPlayerMarriage =
				config.get("General",
						"Shift-click for player marriage menu",
						false,
						"True if you must hold shift then right click a player to open the marriage menu. Useful on PvP servers.")
						.getBoolean();
		chanceToHaveTwins =
				config.get("General", "Chance to have twins", 2, "Your percent chance of having twins.").getInt();
		guardSpawnRate =
				config.get("General",
						"Guard spawn rate",
						3,
						"One guard per this many villagers. Set to zero or a negative number to disable guards.")
						.getInt();
		enableDiminishingReturns =
				config.get("General",
						"Enable diminishing returns?",
						true,
						"True if hearts increase decreases after multiple interactions.").getBoolean();
		enableInfection =
				config.get("General",
						"Enable infection?",
						true,
						"True if villagers and your children have a chance of being infected from zombies.")
						.getBoolean();
		enableStructureSpawning =
				config.get("General",
						"Enable structure spawning?",
						true,
						"True if players can have the option to spawn structures during MCA's setup. Single player only!")
						.getBoolean();
		spawnInAllDimensions =
				config.get("General",
						"Spawn villagers in all dimensions?",
						false,
						"True if you want to ignore the dimension whitelist and spawn MCA villagers in all worlds.")
						.getBoolean();
		replenishEmptyVillages =
				config.get("General",
						"Replenish empty villages?",
						true,
						"True if villagers can spawn within villages that have very few or no villagers. Controlled by vanilla village requirements.")
						.getBoolean();

		//Dimension whitelist.
		if (spawnInAllDimensions) {
			MCA.getLog()
					.info("MCA is configured to spawn villagers in all dimensions. The dimension whitelist will be ignored.");
		} else {
			String
					validDimensions =
					config.get("General",
							"Dimension whitelist",
							"0, 1, -1",
							"The dimension IDs in which MCA villagers can spawn, separated by a comma.").getString();
			List<Integer> dimensionsList = new ArrayList<Integer>();

			for (String s : validDimensions.split(",")) {
				s = s.trim();

				try {
					int intValue = Integer.parseInt(s);
					dimensionsList.add(intValue);
				} catch (NumberFormatException e) {
					MCA.getLog().error("Unable to parse dimension ID provided in config: " + s);
				}
			}

			if (dimensionsList.isEmpty()) {
				MCA.getLog().info("Detected empty dimension whitelist, adding dimension 0 as default.");
				dimensionsList.add(0);
			}

			dimensionWhitelist = dimensionsList.toArray(new Integer[dimensionsList.size()]);
		}

		villagerMaxHealth = config.get("General", "Villager max health", 20).getInt();
		villagerAttackDamage =
				config.get("General",
						"Villager attack damage",
						2,
						"How many half-hearts of damage a villager can deal without a weapon. Does not affect players.")
						.getInt();
		guardMaxHealth = config.get("General", "Guard max health", 40).getInt();
		guardAttackDamage =
				config.get("General",
						"Guard attack damage",
						8,
						"How many half-hearts of damage a guard can deal. Does not affect players.").getInt();
		orcAttackDamage = config.get("General", "Orc attack damage", 10, "How much damage orcs deal").getInt();
		elfAttackDamage = config.get("General", "Elf attack damage", 6, "How much damage elves deal").getInt();
		storyProgression = config.get("General", "Story progression", true,
				"Villagers automatically get married, have children, etc.").getBoolean();

		storyProgressionThreshold = config.get("General",
				"Story progression threshold",
				120,
				"Amount of time a villager has to be alive before story progression begins to affect them. This value is in MINUTES, default is 120. Range (1 and above)")
				.getInt();

		storyProgressionRate = config.get("General",
				"Story progression rate",
				20,
				"How often story progression tries to make changes. Changes may not always be made. This value is in MINUTES, default is 20. Range (1 and above)")
				.getInt();
		storyProgressionCap = config.get("General",
				"Story progression spawn cap",
				-1,
				"Determines whether or not story progression will occur based on this number of villagers within a 32 block radius. Set to -1 to disable. 16 is recommended.")
				.getInt();
		inTutorialMode = config.get("General", "Tutorial mode", true,
				"Displays various tips while you play. ").getBoolean();
		allowMobAttacks =
				config.get("General",
						"Allow mob attacks",
						true,
						"True if regular Minecraft mobs can attack villagers. False to prevent mobs from attacking any villager.")
						.getBoolean();
		hiringHeartsRequirement =
				config.get("General",
						"Hiring hearts requirement",
						-1,
						"Heart points (1 heart = 10, 1 gold heart = 20) required to trade with a villager").getInt();
		marriageHeartsRequirement =
				config.get("General",
						"Marriage hearts requirement",
						100,
						"Heart points (1 heart = 10, 1 gold heart = 20) required to marry a villager. -1 if no requirement")
						.getInt();
		tradeHeartsRequirement =
				config.get("General",
						"Trade hearts requirement",
						-1,
						"Heart points (1 heart = 10, 1 gold heart = 20) required to trade with a villager. -1 if no requirement")
						.getInt();

		config.setCategoryComment("World Generation", "All settings related to MCA's world generation.");
		roseGoldSpawnWeight =
				config.get("World Generation",
						"Rose gold spawn weight",
						1,
						"Sets the spawn weight for rose gold. Higher numbers = less common. Set to zero to disable.")
						.getInt();

		config.setCategoryComment("Aging", "All aging-related settings of villagers and children in-game.");
		babyGrowUpTime = config.get("Aging", "Time until babies grow up (in minutes)", 10).getInt();
		childGrowUpTime = config.get("Aging", "Time until children grow up (in minutes)", 180).getInt();
		isAgingEnabled = config.get("Aging", "Enable aging", true).getBoolean();

		config.setCategoryComment("Graphics", "All graphics-related settings are located here.");
		showMoodParticles =
				config.get("Graphics",
						"Show mood particles",
						true,
						"True if you want for particles to appear around villagers if they are in a certain mood")
						.getBoolean();
		showNameTagOnHover =
				config.get("Graphics",
						"Show name tag on hover",
						true,
						"True if you want a villager's name to appear above their head when you hover over them.")
						.getBoolean();
		//showVillagerConversations = config.get("Graphics", "Show villager conversations", true, "True if you want to see any conversations a villager may have with another villager.").getBoolean();
		modifyFemaleBody =
				config.get("Graphics",
						"Modify female body",
						true,
						"True if you want a female villager to render with breasts, curves, etc.").getBoolean();
		allowBlinking =
				config.get("Graphics",
						"Allow blinking",
						true,
						"True if you want to see villagers blink their eyes at random.").getBoolean();

		config.setCategoryComment("Server", "All settings that server administrators may want to configure.");
		serverLanguageId =
				config.get("Server",
						"Server language ID",
						"en_us",
						"The language your server should load. English by default. Ex.) To use Spanish, set to `es_es`")
						.getString();
		childLimit = config.get("Server", "Child limit", -1).getInt();
		villagerSpawnerCap =
				config.get("Server",
						"Villager spawner cap",
						16,
						"How many villagers maximum that can be within a 32 block radius of any villager spawner block.")
						.getInt();
		allowFarmingChore = config.get("Server", "Allow farming chore", true).getBoolean();
		allowFishingChore = config.get("Server", "Allow fishing chore", true).getBoolean();
		allowWoodcuttingChore = config.get("Server", "Allow woodcutting chore", true).getBoolean();
		allowMiningChore = config.get("Server", "Allow mining chore", true).getBoolean();
		allowHuntingChore = config.get("Server", "Allow hunting chore", true).getBoolean();
		allowGiftDemands = config.get("Server", "Allow gift demands", true).getBoolean();
		allowTrading = config.get("Server", "Allow trading", true).getBoolean();
		logVillagerDeaths =
				config.get("Server",
						"Log villager deaths",
						false,
						"True if you want villager deaths to be logged to the console/server logs. Shows 'RMFS' values in console, R = related, M = mother, F = father, S = spouse. Can be a bit spammy!")
						.getBoolean();
		villagerChatPrefix = config.get("Server", "Villager chat prefix", "").getDefault();
		serverEnableStructureSpawning =
				config.get("Server",
						"Enable structure spawning on server?",
						false,
						"True if players can have the option to spawn structures during MCA's setup on a server. WARNING: POTENTIAL FOR GRIEFING IS VERY HIGH - YOU HAVE BEEN WARNED")
						.getBoolean();
		allowVillagerRevival =
				config.get("Server",
						"Allow dead villagers to be revived?",
						true,
						"True if players can have the ability to revive villagers they are related to. Creates a file in [world name]/data/ that could become very large on big servers.")
						.getBoolean();
		setMatingSeasonDuration(config
				.get("mating_season_time", "How long the mating season lasts in minutes (not yet implemented).", 1)
				.getInt());
		showPlayerDataMigrationErrors =
				config.get("Server",
						"Show player data migration errors?",
						true,
						"If you're updating MCA on an existing world, some internal migrations of data must be performed. This can be error prone, but if you want to ignore these errors, set this to false - NOT RECOMMENDED! YOUR PLAYERS COULD LOSE PROGRESS!")
						.getBoolean();

		//Additional gifts.
		additionalGiftItems =
				config.get("Server",
						"Additional gifts",
						new String[]{"#<EXAMPLE> fermented_spider_eye|25", "#<EXAMPLE> poisonous_potato|12"},
						"The names of the items/blocks that can be gifted in addition to the default items. Include hearts value preceded by |. 10 hearts points equals 1 heart.")
						.getStringList();

		config.save();
	}

	public void syncConfiguration() {
		config.load();
		addConfigValues();
		config.save();
	}

	public Configuration getInstance() {
		return config;
	}

	public List<IConfigElement> getCategories() {
		List<IConfigElement> elements = new ArrayList<IConfigElement>();

		for (String s : config.getCategoryNames()) {
			if (!s.equals("server")) {
				IConfigElement element = new ConfigElement(config.getCategory(s));
				for (IConfigElement e : element.getChildElements()) {
					elements.add(e);
				}
			}
		}

		return elements;
	}

	public boolean canSpawnInDimension(int dimensionId) {
		if (spawnInAllDimensions) {
			return true;
		} else {
			for (int i : dimensionWhitelist) {
				if (i == dimensionId) {
					return true;
				}
			}
		}

		return false;
	}

	public int getMatingSeasonDuration() {
		return matingSeasonDuration;
	}

	public void setMatingSeasonDuration(int matingSeasonDuration) {
		this.matingSeasonDuration = matingSeasonDuration;
	}

	public List<EnumRace> getSeasonalBreeders() {
		return seasonalBreeders;
	}

	public void setSeasonalBreeders(List<EnumRace> seasonalBreeders) {
		this.seasonalBreeders = seasonalBreeders;
	}
}
