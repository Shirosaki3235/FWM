package Fantasy_World.mod.conf;

import net.minecraftforge.common.config.Configuration;
import Fantasy_World.mod.fantasy_world;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ModConfigCore {
	public static final String GENERAL = "General";
	private static final String DIFFICULTY = GENERAL + ".Difficulty";
	public static Configuration cfg;
	public static boolean isGeneratorEnabled = true;
	public static byte amountSmelting = 1;

	public static void loadConfig(FMLPreInitializationEvent event) {
		// net.minecraftforge.common.config.Configurationのインスタンスを生成する。
		cfg = new Configuration(event.getSuggestedConfigurationFile(), fantasy_world.instance.ver, true);
		// 初期化する。
		initConfig();
		// コンフィグファイルの内容を変数と同期させる。
		syncConfig();
	}

	/** コンフィグを初期化する。 */
	private static void initConfig() {
		// カテゴリのコメントなどを設定する。
		// General
		cfg.addCustomCategoryComment(GENERAL, "A settings of fantasy world Mod.");
		cfg.setCategoryLanguageKey(GENERAL, "config.aluminium.category.general");
		// Difficulty
		cfg.addCustomCategoryComment(DIFFICULTY, "The settings of difficulty.");
		cfg.setCategoryLanguageKey(DIFFICULTY, "config.aluminium.category.difficulty");
		cfg.setCategoryRequiresMcRestart(DIFFICULTY, true);
	}

	/** コンフィグを同期する。 */
	public static void syncConfig() {
		// 各項目の設定値を反映させる。
		// General
		isGeneratorEnabled = cfg.getBoolean("enabledGenerator", GENERAL, isGeneratorEnabled, "Aluminium Ore will be generated when this setting is true.", "config.aluminium.prop.enabledGenerator");
		// Difficulty
		amountSmelting = (byte) cfg.getInt("amountSmelting", DIFFICULTY, amountSmelting, 1, Byte.MAX_VALUE, "Smelting amount of Aluminium Ingot from Aluminium Ore.", "config.aluminium.prop.amountSmelting");
		// 設定内容をコンフィグファイルに保存する。
		cfg.save();
	}
}