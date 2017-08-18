package Fantasy_World.mod.dimensions.magic_forest;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import Fantasy_World.mod.fantasy_world;

public class magic_forest_portal extends Block{
	public magic_forest_portal(Material p_i45394_1_) {
	    super(p_i45394_1_);
	}

	@Override
	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
		// 現在地が独自ディメンション以外
		if (p_149727_1_.provider.dimensionId != fantasy_world.dimensions.dimensionID) {
			if (p_149727_5_ instanceof EntityPlayerMP) {
				// １行で書くと長過ぎるので一旦ローカル変数に格納
				EntityPlayerMP entityPlayerMP = (EntityPlayerMP) p_149727_5_;
				ServerConfigurationManager serverConfigurationManager = entityPlayerMP.mcServer.getConfigurationManager();
				WorldServer worldServer = entityPlayerMP.mcServer.worldServerForDimension(fantasy_world.dimensions.dimensionID);

				// 移動後にネザーポータルが作成されるので即座に再送還されないように
				entityPlayerMP.timeUntilPortal = 10;
				entityPlayerMP.setInPortal();

				// 独自ディメンションに移動する
				serverConfigurationManager.transferPlayerToDimension(entityPlayerMP, fantasy_world.dimensions.dimensionID, new magic_forest_teleporter(worldServer, 0, fantasy_world.dimensions.dimensionID));
			}
		}
		// 現在地が独自ディメンション
		else {
			if (p_149727_5_ instanceof EntityPlayerMP) {
				// １行で書くと長過ぎるので一旦ローカル変数に格納
				EntityPlayerMP entityPlayerMP = (EntityPlayerMP) p_149727_5_;
				ServerConfigurationManager serverConfigurationManager = entityPlayerMP.mcServer.getConfigurationManager();
				WorldServer worldServer = entityPlayerMP.mcServer.worldServerForDimension(0);

				entityPlayerMP.timeUntilPortal = 10;
				entityPlayerMP.setInPortal();


				// 独自ディメンションからはオーバーワールドに移動
				serverConfigurationManager.transferPlayerToDimension(entityPlayerMP, 0,new magic_forest_teleporter(worldServer, fantasy_world.dimensions.dimensionID, 0));
			}
		}
		return true;
	}
/*
	private int constructID;
	private int toDimID;

	@SideOnly(Side.CLIENT)
	//public Icon portalIcon;
	public Icon portalIcon;

    public magic_forest_portal(int par1, int par2, int par3)
    {
        super(par1);
        this.setTickRandomly(true);
        this.constructID = par2;
        this.toDimID = par3;

        this.setHardness(-1.0F);
        this.setStepSound(soundGlassFootstep);
        this.setLightValue(0.0F);
        //this.setCreativeTab(DqmTabNextBlock.DqmTabNBlock);
        this.setUnlocalizedName("BlockPortalDim0");
        this.setBlockUnbreakable();

    }

    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);

        if (par1World.provider.isSurfaceWorld() && par5Random.nextInt(2000) < par1World.difficultySetting)
        {
            int l;

            for (l = par3; !par1World.doesBlockHaveSolidTopSurface(par1World, par2, l, par4) && l > 0; --l)
            {
                ;
            }

            if (l > 0 && !par1World.isBlockNormalCube(par2, l + 1, par4))
            {
                Entity entity = ItemMonsterPlacer.spawnCreature(par1World, 57, (double)par2 + 0.5D, (double)l + 1.1D, (double)par4 + 0.5D);

                if (entity != null)
                {
                   // entity.timeUntilPortal = entity.getPortalCooldown();
                }
            }
        }
    }

    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {

    }

    /**
     * Checks to see if this location is valid to create a portal and will return True if it does. Args: world, x, y, z

    public boolean tryToCreatePortal(World par1World, int par2, int par3, int par4)
    {
    	return true;
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID

    public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {}

    @Override
    public void onFallenUpon(World par1World, int par2, int par3, int par4, Entity par5Entity, float par6)
    {
    	par5Entity.fallDistance = 0;
    }

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		entity.fallDistance = 0;

		if (!world.isRemote && entity.isEntityAlive() && entity.riddenByEntity == null && entity.ridingEntity == null)
		{
			//entity.posY = 128;
			//entity.prevPosY = 128;
			MinecraftServer server = DQM.proxy.getServer();
			int dimOld = entity.dimension;

			if (dimOld != 0)
			{
				int dimNew;

				dimNew = 0;

				WorldServer worldOld = server.worldServerForDimension(dimOld);
				WorldServer worldNew = server.worldServerForDimension(dimNew);
				Teleporter teleporter = new magic_forest_teleporter(worldNew, dimOld, dimNew);

	//DEBUG
	//			Teleporter teleporter = null;

				//if (entity.timeUntilPortal <= 0 && (dimOld == 0 || dimOld == -49))
				//if (entity.timeUntilPortal <= 0)
				//{
					if (entity instanceof EntityPlayerMP)
					{
						EntityPlayerMP player = (EntityPlayerMP)entity;

						//if (!player.isSneaking())
						//{

		                    if(player.dimension == 0)
		                    {
		                        player.mcServer.getConfigurationManager().transferPlayerToDimension(player, this.toDimID, new magic_forest_teleporter(player.mcServer.worldServerForDimension(this.toDimID), dimOld, dimNew));
		                        player.timeUntilPortal = 200;

		                    }
		                    else
		                    {
		                        player.mcServer.getConfigurationManager().transferPlayerToDimension(player, 0, new magic_forest_teleporter(player.mcServer.worldServerForDimension(0), dimOld, dimNew));
		                        player.timeUntilPortal = 200;
		                    }


							//server.getConfigurationManager().transferPlayerToDimension(player, dimNew, teleporter);


							player.timeUntilPortal = player.getPortalCooldown();
						//}
					}
					else
					{

						entity.dimension = dimNew;
						server.getConfigurationManager().transferEntityToWorld(entity, dimOld, worldOld, worldNew, teleporter);

						//Entity target = EntityList.createEntityByID(EntityList.getEntityID(entity), worldNew);
						Entity target = EntityList.createEntityByName(EntityList.getEntityString(entity), worldNew);

						if (target != null)
						{
							target.copyDataFrom(entity, true);
							target.isDead = false;
							target.field_98038_p = true;
							target.timeUntilPortal = target.getPortalCooldown();

							worldNew.spawnEntityInWorld(target);
							worldNew.updateEntity(target);
						}

						entity.setDead();

						worldOld.resetUpdateEntityTick();
						worldNew.resetUpdateEntityTick();

					}
				//}
				//else
				//{
				//	entity.timeUntilPortal = entity.getPortalCooldown();
				//}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		blockIcon = iconRegister.registerIcon("portal_floor");
	}


    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)

    public boolean renderAsNormalBlock()
    {
        return true;
    }

    public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        //float f = 0.0625F;
        //float f2 = 0.0F
        float f2 = 0.0F;
    	float f = 1.0F;
        this.setBlockBounds(0.0F, f2, 0.0F, 1.0F, f, 1.0F);
    }

    public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
    {
       return true;
    }

    public int getRenderBlockPass()
    {
        return 0;
    }*/
}