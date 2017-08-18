package Fantasy_World.mod.generater;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraftforge.common.util.ForgeDirection;
import Fantasy_World.mod.fantasy_world;

public class magic_wood_generator extends WorldGenBigTree {

	private final int minTreeHeight;
	private final boolean vinesGrow;
	private final int metaWood;
	private final int metaLeaves;

	public magic_wood_generator(boolean doBlockNotify) {
		this(doBlockNotify, 4, 0, 0, false);
	}

	public magic_wood_generator(boolean doBlockNotify, int minTreeHeight, int metaWood, int metaLeaves, boolean vinesGrow) {
		super(doBlockNotify);
		this.minTreeHeight = minTreeHeight;
		this.metaWood = metaWood;
		this.metaLeaves = metaLeaves;
		this.vinesGrow = vinesGrow;
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z) {
		int l = random.nextInt(3) + minTreeHeight;

		if (y < 1 || y + l + 1 > 256)
			return false;
		byte b0;
		int k1;
		Block block;

		for (int iy = y; iy <= y + 1 + l; ++iy) {
			b0 = 1;

			if (iy == y) {
				b0 = 0;
			}

			if (iy >= y + 1 + l - 2) {
				b0 = 2;
			}

			for (int ix = x - b0; ix <= x + b0; ++ix) {
				for (int iz = z - b0; iz <= z + b0; ++iz) {
					if (iy < 0 || iy >= 256)
						return false;
					block = world.getBlock(ix, iy, iz);
					if (!this.isReplaceable(world, ix, iy, iz))
						return false;
				}
			}
		}

		Block block2 = world.getBlock(x, y - 1, z);

		boolean isSoil = block2.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (BlockSapling) fantasy_world.blocks.magic_wood_sapling);
		if (!isSoil || y >= 256 - l - 1)
			return false;
		block2.onPlantGrow(world, x, y - 1, z, x, y, z);
		b0 = 3;
		byte b1 = 0;
		int l1;
		int i2;
		int j2;
		int i3;

		for (k1 = y - b0 + l; k1 <= y + l; ++k1) {
			i3 = k1 - (y + l);
			l1 = b1 + 1 - i3 / 2;

			for (i2 = x - l1; i2 <= x + l1; ++i2) {
				j2 = i2 - x;

				for (int k2 = z - l1; k2 <= z + l1; ++k2) {
					int l2 = k2 - z;

					if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || random.nextInt(2) != 0 && i3 != 0) {
						Block block1 = world.getBlock(i2, k1, k2);

						if (block1.isAir(world, i2, k1, k2) || block1.isLeaves(world, i2, k1, k2)) {
							this.setBlockAndNotifyAdequately(world, i2, k1, k2,fantasy_world.blocks.magic_wood_leaves, metaLeaves);
						}
					}
				}
			}
		}

		for (k1 = 0; k1 < l; ++k1) {
			block = world.getBlock(x, y + k1, z);

			if (block.isAir(world, x, y + k1, z) || block.isLeaves(world, x, y + k1, z)) {
				setBlockAndNotifyAdequately(world, x, y + k1, z, fantasy_world.blocks.magic_wood_log, metaWood);

				if (vinesGrow && k1 > 0) {
					if (random.nextInt(3) > 0 && world.isAirBlock(x - 1, y + k1, z)) {
						this.setBlockAndNotifyAdequately(world, x - 1, y + k1, z, Blocks.vine, 8);
					}

					if (random.nextInt(3) > 0 && world.isAirBlock(x + 1, y + k1, z)) {
						this.setBlockAndNotifyAdequately(world, x + 1, y + k1, z, Blocks.vine, 2);
					}

					if (random.nextInt(3) > 0 && world.isAirBlock(x, y + k1, z - 1)) {
						this.setBlockAndNotifyAdequately(world, x, y + k1, z - 1, Blocks.vine, 1);
					}

					if (random.nextInt(3) > 0 && world.isAirBlock(x, y + k1, z + 1)) {
						this.setBlockAndNotifyAdequately(world, x, y + k1, z + 1, Blocks.vine, 4);
					}
				}
			}
		}

		if (vinesGrow) {
			for (k1 = y - 3 + l; k1 <= y + l; ++k1) {
				i3 = k1 - (y + l);
				l1 = 2 - i3 / 2;

				for (i2 = x - l1; i2 <= x + l1; ++i2) {
					for (j2 = z - l1; j2 <= z + l1; ++j2) {
						if (world.getBlock(i2, k1, j2).isLeaves(world, i2, k1, j2)) {
							if (random.nextInt(4) == 0 && world.getBlock(i2 - 1, k1, j2).isAir(world, i2 - 1, k1, j2)) {
								this.growVines(world, i2 - 1, k1, j2, 8);
							}

							if (random.nextInt(4) == 0 && world.getBlock(i2 + 1, k1, j2).isAir(world, i2 + 1, k1, j2)) {
								this.growVines(world, i2 + 1, k1, j2, 2);
							}

							if (random.nextInt(4) == 0 && world.getBlock(i2, k1, j2 - 1).isAir(world, i2, k1, j2 - 1)) {
								this.growVines(world, i2, k1, j2 - 1, 1);
							}

							if (random.nextInt(4) == 0 && world.getBlock(i2, k1, j2 + 1).isAir(world, i2, k1, j2 + 1)) {
								this.growVines(world, i2, k1, j2 + 1, 4);
							}
						}
					}
				}
			}

			if (random.nextInt(5) == 0 && l > 5) {
				for (k1 = 0; k1 < 2; ++k1) {
					for (i3 = 0; i3 < 4; ++i3) {
						if (random.nextInt(4 - k1) == 0) {
							l1 = random.nextInt(3);
							this.setBlockAndNotifyAdequately(world, x + Direction.offsetX[Direction.rotateOpposite[i3]], y + l - 5 + k1, z + Direction.offsetZ[Direction.rotateOpposite[i3]], Blocks.cocoa, l1 << 2 | i3);
						}
					}
				}
			}
		}

		return true;
	}

	@Override
	protected boolean isReplaceable(World world, int x, int y, int z) {
		Block block = world.getBlock(x, y, z);
		return block.isAir(world, x, y, z) || block.isLeaves(world, x, y, z) || block.isWood(world, x, y, z) || this.func_150523_a(block);
	}

	@Override
	protected boolean func_150523_a(Block block) {
		return block.getMaterial() == Material.air || block.getMaterial() == Material.leaves || block == Blocks.grass || block == Blocks.dirt || block == Blocks.log || block == Blocks.log2 || block == Blocks.sapling || block == Blocks.vine;
	}

	private void growVines(World world, int x, int y, int z, int length) {
		this.setBlockAndNotifyAdequately(world, x, y, z, Blocks.vine, length);
		int i1 = 4;

		while (true) {
			--y;

			if (!world.getBlock(x, y, z).isAir(world, x, y, z) || i1 <= 0) {
				return;
			}

			this.setBlockAndNotifyAdequately(world, x, y, z, Blocks.vine, length);
			--i1;
		}
	}
}