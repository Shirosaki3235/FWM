package Fantasy_World.mod.biome.dimensions;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import Fantasy_World.mod.generater.magic_wood_generator;

public class magic_forest_main_biome extends BiomeGenBase{
	public magic_forest_main_biome (int par1){
		super(par1);
		this.topBlock = Blocks.grass;//自作ブロックも可
		this.fillerBlock = Blocks.stone;
		//this.waterColorMultiplier = 0xBF96DE;

		this.theBiomeDecorator.treesPerChunk = 7;

		//this.setTemperatureRainfall(0.7F, 0.8F);

		this.worldGeneratorBigTree = new magic_wood_generator(true);
	}
}