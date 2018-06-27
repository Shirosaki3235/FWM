package Fantasy_World.mod.blocks.base;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import Fantasy_World.mod.fantasy_world;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class magic_grass_block extends Block implements IGrowable {

    @SideOnly(Side.CLIENT)
	private static IIcon[] iicon = new IIcon[4];
    //private static final String __OBFID = "CL_00000251";

	public magic_grass_block(Material grass) {
		super(grass);
		//硬さの設定
		this.setHardness(2.0F);
		//爆破耐性の設定
		this.setResistance(4.0F);
		//ブロックの上を歩いた時の音を登録する。
		this.setStepSound(Block.soundTypeGrass);
		//回収するのに必要なツールを設定する。
		this.setHarvestLevel("shovel", 2);
		//明るさの設定
		this.setLightLevel(1.0F);

	}

	@Override
	public int getRenderType() {
		return 31;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		//int k = meta & 12;
		//int l = meta & 3;
		//int n = meta & 4;
		//return k == 0 && (side == 1 || side == 0) ? this.getTopIcon(l) : (k == 4 && (side == 5 || side == 4) ? this.getTopIcon(l) : (k == 8 && (side == 2 || side == 3) ? this.getTopIcon(l) : this.getSideIcon(l)));
		//return side == 0 ? this.getSideIcon(k) : side == 1 ? this.getTopIcon(l) : this.getSideIcon(k);
		return side == 1 ? this.iicon[0] : (side == 0 ? fantasy_world.blocks.magic_dirt.getBlockTextureFromSide(side) : this.iicon[1]);
	}

	/**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
    {
        if (!p_149674_1_.isRemote)
        {
            if (p_149674_1_.getBlockLightValue(p_149674_2_, p_149674_3_ + 1, p_149674_4_) < 4 && p_149674_1_.getBlockLightOpacity(p_149674_2_, p_149674_3_ + 1, p_149674_4_) > 2)
            {
                p_149674_1_.setBlock(p_149674_2_, p_149674_3_, p_149674_4_, fantasy_world.blocks.magic_dirt);
            }
            else if (p_149674_1_.getBlockLightValue(p_149674_2_, p_149674_3_ + 1, p_149674_4_) >= 9)
            {
                for (int l = 0; l < 4; ++l)
                {
                    int i1 = p_149674_2_ + p_149674_5_.nextInt(3) - 1;
                    int j1 = p_149674_3_ + p_149674_5_.nextInt(5) - 3;
                    int k1 = p_149674_4_ + p_149674_5_.nextInt(3) - 1;
                    Block block1 = p_149674_1_.getBlock(i1, j1 + 1, k1);
                    int block2 = p_149674_1_.getBlockMetadata(i1, j1, k1);

                    if (p_149674_1_.isAirBlock(p_149674_2_, p_149674_3_ + 1, p_149674_4_) && p_149674_1_.getBlock(i1, j1, k1) == fantasy_world.blocks.magic_dirt && p_149674_1_.getBlockMetadata(i1, j1, k1) == 0 && p_149674_1_.getBlockLightValue(i1, j1 + 1, k1) >= 4 && p_149674_1_.getBlockLightOpacity(i1, j1 + 1, k1) <= 2)
                    {
                        p_149674_1_.setBlock(i1, j1, k1, fantasy_world.blocks.magic_grass);
                    }
                }
            }
        }
    }

	@Override
	public int damageDropped(int meta) {
		return meta & 3;
	}
/*
	@SideOnly(Side.CLIENT)
	protected IIcon getTopIcon(int meta) {
		return this.iicon[1];
	}
*/
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return fantasy_world.blocks.magic_dirt.getItemDropped(0, p_149650_2_, p_149650_3_);
    }

    public boolean func_149851_a(World p_149851_1_, int p_149851_2_, int p_149851_3_, int p_149851_4_, boolean p_149851_5_)
    {
        return true;
    }

    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_)
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_)
    {
        if (p_149673_5_ == 1)
        {
            return this.iicon[0];
        }
        else if (p_149673_5_ == 0)
        {
            return fantasy_world.blocks.magic_dirt.getBlockTextureFromSide(p_149673_5_);
        }
        else
        {
            Material material = p_149673_1_.getBlock(p_149673_2_, p_149673_3_ + 1, p_149673_4_).getMaterial();
            return material != Material.snow && material != Material.craftedSnow ? this.iicon[1] : this.iicon[2];
        }
    }

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iicon) {
		this.iicon[0] = iicon.registerIcon(this.getTextureName() + "_top");
		this.iicon[1] = iicon.registerIcon(this.getTextureName() + "_side");
		this.iicon[2] = iicon.registerIcon(this.getTextureName() + "_side_snow");
		this.iicon[3] = iicon.registerIcon(this.getTextureName() + "_side_overlay");
		//this.iicon[2] = iicon.registerIcon(this.getTextureName() + "_bottom");
	}

	 @SideOnly(Side.CLIENT)
    public static IIcon getIconSideOverlay()
    {
        return iicon[3];
    }

	 public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
    {
    	return true;
    }

	@Override
	public void func_149853_b(World p_149853_1_, Random p_149853_2_, int p_149853_3_, int p_149853_4_, int p_149853_5_) {
		// TODO 自動生成されたメソッド・スタブ
		int l = 0;

        while (l < 128)
        {
            int i1 = p_149853_3_;
            int j1 = p_149853_4_ + 1;
            int k1 = p_149853_5_;
            int l1 = 0;

            while (true)
            {
                if (l1 < l / 16)
                {
                    i1 += p_149853_2_.nextInt(3) - 1;
                    j1 += (p_149853_2_.nextInt(3) - 1) * p_149853_2_.nextInt(3) / 2;
                    k1 += p_149853_2_.nextInt(3) - 1;

                    if (p_149853_1_.getBlock(i1, j1 - 1, k1) == Blocks.grass && !p_149853_1_.getBlock(i1, j1, k1).isNormalCube())
                    {
                        ++l1;
                        continue;
                    }
                }
                else if (p_149853_1_.getBlock(i1, j1, k1).getMaterial() == Material.air)
                {
                    if (p_149853_2_.nextInt(8) != 0)
                    {
                        if (Blocks.tallgrass.canBlockStay(p_149853_1_, i1, j1, k1))
                        {
                            p_149853_1_.setBlock(i1, j1, k1, Blocks.tallgrass, 1, 3);
                        }
                    }
                    else
                    {
                        p_149853_1_.getBiomeGenForCoords(i1, k1).plantFlower(p_149853_1_, p_149853_2_, i1, j1, k1);
                    }
                }

                ++l;
                break;
            }
        }
	}
}