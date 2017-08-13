package Fantasy_World.mod.dimensions.magic_forest;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;

public class magic_forest_chunkprovider extends ChunkProviderGenerate implements IChunkProvider{
	public magic_forest_chunkprovider(World par1World, long par2, boolean par4) {
        super(par1World, par2, par4);
    }
}