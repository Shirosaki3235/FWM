package Fantasy_World.mod.dimensions.magic_forest;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;

public class magic_forest_worldtype extends WorldType{
	public static WorldType worldTypeSample = new magic_forest_worldtype("Sample");

    private magic_forest_worldtype(String name) {
        super(name);
    }

    @Override
    public WorldChunkManager getChunkManager(World world) {
        return new magic_forest_chunkmanager(world);
    }

    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions) {
        return new magic_forest_chunkprovider(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }
}