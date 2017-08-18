package Fantasy_World.mod.biome;

import net.minecraftforge.common.BiomeManager;
import Fantasy_World.mod.fantasy_world;
import Fantasy_World.mod.biome.dimensions.magic_forest_main_biome;

public class biome_base{
	public biome_base(){
		// ディメンション
		fantasy_world.biome.magic_forest = new magic_forest_main_biome(fantasy_world.biome.magic_forest_ID).setColor(0xBF96DE).setBiomeName("magic_forest");
	    BiomeManager.desertBiomes.add(new BiomeManager.BiomeEntry(fantasy_world.biome.magic_forest, 10));
	}
}