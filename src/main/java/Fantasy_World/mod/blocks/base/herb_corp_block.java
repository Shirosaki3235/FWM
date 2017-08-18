package Fantasy_World.mod.blocks.base;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import Fantasy_World.mod.fantasy_world;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class herb_corp_block extends BlockCrops implements IPlantable, IGrowable {
	private IIcon[] iIcons;

	public herb_corp_block() {
		// BlockBushのコンストラクタでMaterialはMaterial.plantsを指定されている。
		super();
		// updateTickがランダムに呼ばれるようにする。
		this.setTickRandomly(true);
		// ブロックの大きさを指定する。あたり判定やカーソルがあった時の枠の大きさに使われる。
		float f = 0.5F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
		// クリエイティブタブに表示されないようにする。
		this.setCreativeTab(null);
		// 一瞬で破壊できるようにする。ツールを持っていても耐久値は消費しない。
		this.setHardness(0.0F);
		// 設置時や歩行時の音の種類を指定する。
		this.setStepSound(soundTypeGrass);
		// 統計にカウントされないようにする。（？）
		this.disableStats();
	}

	/** その座標に設置できるか。 */
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		return world.getBlock(x, y, z).isReplaceable(world, x, y, z) && this.canBlockStay(world, x, y, z);
		// 以下はBlockBushでの実装。superを呼び出すと二重に判定されてしまうため変更した。
		//		return super.canPlaceBlockAt(world, x, y, z) && this.canBlockStay(world, x, y, z);
	}

	/** そのブロックの上に設置できるか。 */
	@Override
	protected boolean canPlaceBlockOn(Block block) {
		// 耕地の上のみ。
		return block == Blocks.farmland;
	}

	/** 隣接ブロックが更新された時の処理。 */
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		// 二重判定回避のためコメントアウト。実際、Blockクラスでは何もしていない。
		//		super.onNeighborBlockChange(world, x, y, z, block);
		this.checkAndDropBlock(world, x, y, z);
	}

	/** Tick更新時の処理。 */
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		// ランダムに呼ばれる。
		this.checkAndDropBlock(world, x, y, z);
		// 一つ上のブロックの光源レベルが9以上の時。
		if (world.getBlockLightValue(x, y + 1, z) >= 9) {
			// メタデータを取得。
			int l = world.getBlockMetadata(x, y, z);
			// 成長限界に達していない時。
			if (l < 7) {
				// 成長しやすさを取得。
				float f = this.func_149864_n(world, x, y, z);
				// 成長させるかを判定する。
				if (random.nextInt((int) (25.0F / f) + 1) == 0) {
					// 一段階成長させる。
					++l;
					world.setBlockMetadataWithNotify(x, y, z, l, 2);
				}
			}
		}
	}

	/** 設置状態を維持できるかを確認し、維持できなければドロップする。 */
	@Override
	protected void checkAndDropBlock(World world, int x, int y, int z) {
		// 維持できない時。
		if (!this.canBlockStay(world, x, y, z)) {
			// ドロップする。
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			// 空気に上書きする。
			world.setBlock(x, y, z, getBlockById(0), 0, 2);
		}
	}

	/** その座標で維持できるか。 */
	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		// 下のブロックが耕地かどうかを判定する。
		return world.getBlock(x, y - 1, z).canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, this);
	}

	/** 作物の種別を返す。 */
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		// IPlantableの実装。作物。耕地の上に設置する。
		return EnumPlantType.Crop;
	}

	/** 作物ブロックのインスタンスを返す。 */
	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		// IPlantableの実装。
		return this;
	}

	/** 作物のメタデータを返す。 */
	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		// IPlantableの実装。
		return world.getBlockMetadata(x, y, z);
	}

	/** 成長しやすさの数値を返す。 */
	private float func_149864_n(World world, int x, int y, int z) {
		// 周囲の耕地、作物の状況を判定し、成長しやすさを算出する。
		float f = 1.0F;
		Block block = world.getBlock(x, y, z - 1);
		Block block1 = world.getBlock(x, y, z + 1);
		Block block2 = world.getBlock(x - 1, y, z);
		Block block3 = world.getBlock(x + 1, y, z);
		Block block4 = world.getBlock(x - 1, y, z - 1);
		Block block5 = world.getBlock(x + 1, y, z - 1);
		Block block6 = world.getBlock(x + 1, y, z + 1);
		Block block7 = world.getBlock(x - 1, y, z + 1);
		boolean flag = block2 == this || block3 == this;
		boolean flag1 = block == this || block1 == this;
		boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;
		for (int l = x - 1; l <= x + 1; ++l) {
			for (int i1 = z - 1; i1 <= z + 1; ++i1) {
				float f1 = 0.0F;
				if (world.getBlock(l, y - 1, i1).canSustainPlant(world, l, y - 1, i1, ForgeDirection.UP, this)) {
					f1 = 1.0F;
					if (world.getBlock(l, y - 1, i1).isFertile(world, l, y - 1, i1)) {
						f1 = 3.0F;
					}
				}
				if (l != x || i1 != z) {
					f1 /= 4.0F;
				}
				f += f1;
			}
		}
		if (flag2 || flag && flag1) {
			f /= 2.0F;
		}
		return f;
	}

	/** あたり判定を返す。 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		// あたり判定をなくす。
		return null;
	}

	/** 不透明なブロックか。 */
	@Override
	public boolean isOpaqueCube() {
		// 透明なブロックなのでfalseを返す。
		return false;
	}

	/** 通常と同様に描画するか。 */
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	/** 描画の種別を返す。 */
	@Override
	public int getRenderType() {
		// 小麦などと同じ。四枚の板が上から見て「井」の形になるように配置され、そこにテクスチャが表示される。
		return 6;
	}

	/** 種のアイテムを返す。 */
	@Override
	protected Item func_149866_i() {
		return fantasy_world.foods.herb_seed;
	}

	/** 作物のアイテムを返す。 */
	@Override
	protected Item func_149865_P() {
		return fantasy_world.foods.herb_laef;
	}

	/** ブロックをドロップさせる。 */
	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int meta, float dropChance, int fortune) {
		super.dropBlockAsItemWithChance(world, x, y, z, meta, dropChance, fortune);
		// BlockCropで以下のようにオーバーライドされている。幸運レベルを0に固定。
		//		super.dropBlockAsItemWithChance(world, x, y, z, meta, dropChance, 0);
	}

	/** ドロップアイテムを返す。 */
	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		// 基本的に種を返すが、完全成長していたら作物を返す。
		return meta == 7 ? this.func_149865_P() : this.func_149866_i();
	}

	/** ドロップ数を返す。 */
	@Override
	public int quantityDropped(Random random) {
		return 1;
	}

	/** ドロップアイテムのリストを返す。 */
	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
		// 未成長なら種を、完全成長していたら作物が追加される。
		ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
		int count = quantityDropped(metadata, fortune, world.rand);
		for (int i = 0; i < count; i++) {
			Item item = getItemDropped(metadata, world.rand, fortune);
			if (item != null) {
				ret.add(new ItemStack(item, 1, damageDropped(metadata)));
			}
		}
		// 以上はBlockでの実装。以下はBlockCrops出の実装。重複処理回避のため変更した。
		//		ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);
		//
		// 完全成長の時。
		if (metadata >= 7) {
			// 幸運レベルにより判定回数が増加する。デフォルトは3回。
			for (int i = 0; i < 3 + fortune; ++i) {
				// 0~14 <= 7　より、1/2の確率。
				if (world.rand.nextInt(15) <= metadata) {
					// 種を追加する。
					ret.add(new ItemStack(this.func_149866_i(), 1, 0));
				}
			}
		}
		return ret;
	}

	/** 対応するアイテムを返す。 */
	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z) {
		// 種を返す。
		return this.func_149866_i();
	}

	/** ブロックのテクスチャを返す。 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		// メタデータの数値が異常だったら成長限界の値を使う。
		if (meta < 0 || meta > 7) {
			meta = 7;
		}
		return this.iIcons[meta];
	}

	/** ブロックのテクスチャを登録する。 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		this.iIcons = new IIcon[8];
		for (int i = 0; i < this.iIcons.length; ++i) {
			this.iIcons[i] = register.registerIcon(this.getTextureName() + "_stage_" + i);
		}
	}

	/** 骨粉を使用できるか。 */
	@Override
	public boolean func_149851_a(World world, int x, int y, int z, boolean isRemote) {
		// IGrowableの実装。完全成長していたらfalse。
		return world.getBlockMetadata(x, y, z) != 7;
	}

	/** 骨粉を適用するか。 */
	@Override
	public boolean func_149852_a(World world, Random random, int x, int y, int z) {
		// IGrowableの実装。
		return true;
	}

	/** 骨粉を適用する。 */
	@Override
	public void func_149853_b(World world, Random random, int x, int y, int z) {
		// IGrowableの実装。
		this.func_149863_m(world, x, y, z);
	}

	/** 骨粉を使用した時の成長させる処理。 */
	@Override
	public void func_149863_m(World world, int x, int y, int z) {
		// 成長段階を2以上5以下上昇させる。
		int l = world.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
		// 成長限界を超えていたら抑える。
		if (l > 7) {
			l = 7;
		}
		// メタデータを設定する。
		world.setBlockMetadataWithNotify(x, y, z, l, 2);
	}
}