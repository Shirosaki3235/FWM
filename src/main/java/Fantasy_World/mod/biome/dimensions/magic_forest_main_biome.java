package Fantasy_World.mod.biome.dimensions;

import net.minecraft.world.biome.BiomeGenBase;
import Fantasy_World.mod.fantasy_world;
import Fantasy_World.mod.entity.mobentity.seyana_entity;
import Fantasy_World.mod.generater.magic_wood_generator;

public class magic_forest_main_biome extends BiomeGenBase{
	public magic_forest_main_biome (int par1){
		super(par1);
		this.topBlock = fantasy_world.blocks.magic_grass;//自作ブロックも可fantasy_world.blocks.magic_dirt
		this.fillerBlock = fantasy_world.blocks.magic_dirt;//Blocks.dirt;
		this.waterColorMultiplier = 0xBF96DE;

		this.theBiomeDecorator.treesPerChunk = 10;


		//this.setTemperatureRainfall(0.7F, 0.8F);

		this.theBiomeDecorator.grassPerChunk = 10;
		//this.theBiomeDecorator.dirtGen =
		this.theBiomeDecorator.generateLakes = true;
		this.worldGeneratorTrees = new magic_wood_generator(true);

		this.spawnableMonsterList.add(new SpawnListEntry(seyana_entity.class, 10, 0, 1));
	}
}