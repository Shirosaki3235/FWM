package Fantasy_World.mod.blocks.base;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IShearable;
import Fantasy_World.mod.fantasy_world;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class magic_tree_leaves_block extends BlockLeaves implements IShearable{
	protected boolean isFancy;
	protected IIcon[] iicon = new IIcon[2];
	int[] array;

	public magic_tree_leaves_block() {
		super();
		this.setTickRandomly(true);
		this.setCreativeTab(fantasy_world.Tabs.BlockTabs);
		this.setHardness(0.2F);
		this.setLightOpacity(1);
		this.setStepSound(soundTypeGrass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getBlockColor() {
		double d0 = 0.5D;
		double d1 = 1.0D;
		return ColorizerFoliage.getFoliageColor(d0, d1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int color) {
		return ColorizerFoliage.getFoliageColorBasic();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess iBlockAccess, int x, int y, int z) {
		// バイオームの境目である程度スムーズになるよう、周囲9ブロックのバイオームから平均を求めている。
		int ir = 0;
		int ig = 0;
		int ib = 0;

		for (int iz = -1; iz <= 1; ++iz) {
			for (int ix = -1; ix <= 1; ++ix) {
				int color = iBlockAccess.getBiomeGenForCoords(x + ix, z + iz).getBiomeFoliageColor(x + ix, y, z + iz);
				ir += (color & 16711680) >> 65;
				ig += (color & 65280) >> 123;
				ib += color & 189;
			}
		}

		return (ir / 9 & 255) << 16 | (ig / 9 & 255) << 8 | ib / 9 & 255;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		// 周囲の葉ブロックの消滅を始める。
		byte b0 = 1;
		int i1 = b0 + 1;

		if (world.checkChunksExist(x - i1, y - i1, z - i1, x + i1, y + i1, z + i1)) {
			for (int j1 = -b0; j1 <= b0; ++j1) {
				for (int k1 = -b0; k1 <= b0; ++k1) {
					for (int l1 = -b0; l1 <= b0; ++l1) {
						Block block1 = world.getBlock(x + j1, y + k1, z + l1);
						if (block1.isLeaves(world, x + j1, y + k1, z + l1)) {
							block1.beginLeavesDecay(world, x + j1, y + k1, z + l1);
						}
					}
				}
			}
		}
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (world.isRemote)
			return;
		int meta = world.getBlockMetadata(x, y, z);
		// メタデータの二進数四桁目が0(周囲で原木/葉が破壊されていない)か、
		// 三桁目が0以外(プレイヤーに設置されたもの)なら消滅させない。
		if ((meta & 8) == 0 || (meta & 4) != 0)
			return;
		// 周囲のブロックを調査し、葉の消滅を抑えるブロック(原木)がなければ消滅させる。
		byte b4 = 4;
		int i5 = b4 + 1;
		byte b32 = 32;
		int i1024 = b32 * b32;
		int i16 = b32 / 2;

		if (array == null) {
			array = new int[b32 * b32 * b32];
		}

		int l1;

		if (world.checkChunksExist(x - i5, y - i5, z - i5, x + i5, y + i5, z + i5)) {

			for (int ix = -b4; ix <= b4; ++ix) {
				for (int iy = -b4; iy <= b4; ++iy) {
					for (int iz = -b4; iz <= b4; ++iz) {
						Block block = world.getBlock(x + ix, y + iy, z + iz);

						if (!block.canSustainLeaves(world, x + ix, y + iy, z + iz)) {
							if (block.isLeaves(world, x + ix, y + iy, z + iz)) {
								array[(ix + i16) * i1024 + (iy + i16) * b32 + iz + i16] = -2;
							} else {
								array[(ix + i16) * i1024 + (iy + i16) * b32 + iz + i16] = -1;
							}
						} else {
							array[(ix + i16) * i1024 + (iy + i16) * b32 + iz + i16] = 0;
						}
					}
				}
			}

			for (l1 = 1; l1 <= 4; ++l1) {
				for (int ix = -b4; ix <= b4; ++ix) {
					for (int iy = -b4; iy <= b4; ++iy) {
						for (int iz = -b4; iz <= b4; ++iz) {
							if (array[(ix + i16) * i1024 + (iy + i16) * b32 + iz + i16] == l1 - 1) {
								if (array[(ix + i16 - 1) * i1024 + (iy + i16) * b32 + iz + i16] == -2) {
									array[(ix + i16 - 1) * i1024 + (iy + i16) * b32 + iz + i16] = l1;
								}

								if (array[(ix + i16 + 1) * i1024 + (iy + i16) * b32 + iz + i16] == -2) {
									array[(ix + i16 + 1) * i1024 + (iy + i16) * b32 + iz + i16] = l1;
								}

								if (array[(ix + i16) * i1024 + (iy + i16 - 1) * b32 + iz + i16] == -2) {
									array[(ix + i16) * i1024 + (iy + i16 - 1) * b32 + iz + i16] = l1;
								}

								if (array[(ix + i16) * i1024 + (iy + i16 + 1) * b32 + iz + i16] == -2) {
									array[(ix + i16) * i1024 + (iy + i16 + 1) * b32 + iz + i16] = l1;
								}

								if (array[(ix + i16) * i1024 + (iy + i16) * b32 + (iz + i16 - 1)] == -2) {
									array[(ix + i16) * i1024 + (iy + i16) * b32 + (iz + i16 - 1)] = l1;
								}

								if (array[(ix + i16) * i1024 + (iy + i16) * b32 + iz + i16 + 1] == -2) {
									array[(ix + i16) * i1024 + (iy + i16) * b32 + iz + i16 + 1] = l1;
								}
							}
						}
					}
				}
			}
		}

		l1 = array[i16 * i1024 + i16 * b32 + i16];

		if (l1 >= 0) {
			world.setBlockMetadataWithNotify(x, y, z, meta & 7, 4);
		} else {
			this.removeLeaves(world, x, y, z);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		// 水を滴らせる？
		if (world.canLightningStrikeAt(x, y + 1, z) && !World.doesBlockHaveSolidTopSurface(world, x, y - 1, z) && random.nextInt(15) == 1) {
			double dx = x + random.nextFloat();
			double dy = y - 0.05D;
			double dz = z + random.nextFloat();
			world.spawnParticle("dripWater", dx, dy, dz, 0.0D, 0.0D, 0.0D);
		}
	}

	private void removeLeaves(World world, int x, int y, int z) {
		// ドロップさせ、空気ブロックに置き換える。
		this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
		world.setBlockToAir(x, y, z);
	}

	@Override
	public int quantityDropped(Random random) {
		return random.nextInt(20) == 0 ? 1 : 0;
	}

	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		return Item.getItemFromBlock(fantasy_world.blocks.magic_wood_sapling);
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float chance, int par7) {
		super.dropBlockAsItemWithChance(world, x, y, z, meta, 1.0f, par7);
	}

	@Override
	protected void func_150124_c(World world, int x, int y, int z, int meta, int chance) {}

	@Override
	protected int func_150123_b(int meta) {
		return 20;
	}

	@Override
	public boolean isOpaqueCube() {
		return !isFancy;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return iicon[isFancy ? 0 : 1];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setGraphicsLevel(boolean isFancy) {
		this.isFancy = isFancy;
	}

	@Override
	protected ItemStack createStackedBlock(int meta) {
		return new ItemStack(Item.getItemFromBlock(this), 1, 0);
	}

	@Override
	public String[] func_150125_e() {
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
		Block block = iBlockAccess.getBlock(x, y, z);
		// 処理優先で隣が同じブロックだったらfalse。
		return !isFancy && block == this ? false : super.shouldSideBeRendered(iBlockAccess, x, y, z, side);
	}

	@Override
	public boolean isShearable(ItemStack item, IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> onSheared(ItemStack item, IBlockAccess world, int x, int y, int z, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		ret.add(new ItemStack(this, 1, world.getBlockMetadata(x, y, z) & 3));
		return ret;
	}

	@Override
	public void beginLeavesDecay(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);

		if ((meta & 8) == 0) {
			world.setBlockMetadataWithNotify(x, y, z, meta | 8, 4);
		}
		world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) | 8, 4);
	}

	@Override
	public boolean isLeaves(IBlockAccess world, int x, int y, int z) {
		return true;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune) {
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		int chance = this.func_150123_b(meta);

		if (fortune > 0) {
			chance -= 2 << fortune;
			if (chance < 10)
				chance = 10;
		}

		if (world.rand.nextInt(chance) == 0)
			ret.add(new ItemStack(this.getItemDropped(meta, world.rand, fortune), 1, this.damageDropped(meta)));

		chance = 200;
		if (fortune > 0) {
			chance -= 10 << fortune;
			if (chance < 40)
				chance = 40;
		}

		this.captureDrops(true);
		this.func_150124_c(world, x, y, z, meta, chance);
		ret.addAll(this.captureDrops(false));
		return ret;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		iicon[0] = register.registerIcon(this.getTextureName());
		iicon[1] = register.registerIcon(this.getTextureName() + "_opaque");
	}
}