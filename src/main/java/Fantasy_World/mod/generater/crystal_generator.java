package Fantasy_World.mod.generater;

import java.util.Random;

import Fantasy_World.mod.fantasy_world;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class crystal_generator implements IWorldGenerator{

	// world.providerはそのワールド（ディメンション）のプロバイダ。
	// これを使って判定することで鉱石を生成するディメンションを制御できる。
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if (world.provider instanceof WorldProviderSurface) {
			generateOre(world, random, chunkX << 4, chunkZ << 4);
		}
	}

	// 鉱石生成
	private void generateOre(World world, Random random, int x, int z) {
		for(int i = 0; i < 10; i++) {
			int genX = x + random.nextInt(16);
			int genY = 1 + random.nextInt(15);
			int genZ = z + random.nextInt(16);
			// ブロックを生成させるクラスの一つ。
			// コンストラクタで指定したパラメータをもとにgenerateで鉱脈を生成する。
			// 引数は、生成するブロック、生成するブロックのメタデータ、生成する鉱脈の大きさ、置き換えるブロック。
			// 第二引数はなくてもよい。
			new WorldGenMinable(fantasy_world.blocks.crystal_ore, 0, 6, Blocks.stone).generate(world, random, genX, genY, genZ);
		}
	}

}