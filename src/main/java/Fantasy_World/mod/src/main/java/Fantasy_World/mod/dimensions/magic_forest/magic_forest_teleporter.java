package Fantasy_World.mod.dimensions.magic_forest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;
import Fantasy_World.mod.fantasy_world;

import com.google.common.collect.Lists;



public class magic_forest_teleporter extends Teleporter{
	 private WorldServer worldServerInstance;
	    private final LongHashMap destinationCoordinateCache = new LongHashMap();
	    private final List destinationCoordinateKeys = new ArrayList();
	    private Random random;
	    private final List<Long> coordKeys = Lists.newArrayList();
	    private final LongHashMap coordCache = new LongHashMap();
	    private int dimIdx;
	    private int oldDimID;
	    private int newDimID;

	    public magic_forest_teleporter(WorldServer par1WorldServer, int oldDim, int newDim)
	    {
	        super(par1WorldServer);
	        this.worldServerInstance = par1WorldServer;
	        this.worldServerInstance.customTeleporters.add(this);
	        this.random = new Random(par1WorldServer.getSeed());
	        this.oldDimID = oldDim;
	        this.newDimID = newDim;

	    }


		@Override
		public void placeInPortal(Entity entity, double posX, double posY, double posZ, float rotationYaw)
		{
			if (!placeInExistingPortal(entity, posX, posY, posZ, rotationYaw))
			{
				makePortal(entity);
				placeInExistingPortal(entity, posX, posY, posZ, rotationYaw);

			}
		}

	    /*
	    public void placeInPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
	    {
	        if (this.worldServerInstance.provider.dimensionId != 1)
	        {
	            if (!this.placeInExistingPortal(par1Entity, par2, par4, par6, par8))
	            {
	                this.makePortal(par1Entity);
	                this.placeInExistingPortal(par1Entity, par2, par4, par6, par8);
	            }
	        }
	        else
	        {
	            int i = MathHelper.floor_double(par1Entity.posX);
	            int j = MathHelper.floor_double(par1Entity.posY) - 1;
	            int k = MathHelper.floor_double(par1Entity.posZ);
	            byte b0 = 1;
	            byte b1 = 0;
	            for (int l = -2; l <= 2; ++l)
	            {
	                for (int i1 = -2; i1 <= 2; ++i1)
	                {
	                    for (int j1 = -1; j1 < 3; ++j1)
	                    {
	                        int k1 = i + i1 * b0 + l * b1;
	                        int l1 = j + j1;
	                        int i2 = k + i1 * b1 - l * b0;
	                        boolean flag = j1 < 0;
	                        this.worldServerInstance.setBlock(k1, l1, i2, flag ? 2160 : 0);
	                    }
	                }
	            }
	            par1Entity.setLocationAndAngles((double)i, (double)j, (double)k, par1Entity.rotationYaw, 0.0F);
	            par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
	        }
	    }
	    */

	    public boolean placeInExistingPortal(Entity par1Entity, double par2, double par4, double par6, float par8)
	    {
	    	//System.out.println("ファイル名Z:" + this.dimIdx);
	        //short short1 = 128;
	    	short short1 = 128;
	        double d3 = -1.0D;
	        int i = 0;
	        int j = 0;
	        int k = 0;
	        int l = MathHelper.floor_double(par1Entity.posX);
	        int i1 = MathHelper.floor_double(par1Entity.posZ);
	        long j1 = ChunkCoordIntPair.chunkXZ2Int(l, i1);
	        boolean flag = true;
	        double d4;
	        int k1;

	        if (this.destinationCoordinateCache.containsItem(j1))
	        {
	        	//System.out.println("ファイル名R1");
	            PortalPosition portalposition = (PortalPosition)this.destinationCoordinateCache.getValueByKey(j1);
	            d3 = 0.0D;
	            i = portalposition.posX;
	            j = portalposition.posY;
	            k = portalposition.posZ;
	            portalposition.lastUpdateTime = this.worldServerInstance.getTotalWorldTime();
	            flag = false;
	        }
	        else
	        {
	        	//System.out.println("ファイル名R2");
	        	//x座標
	            for (k1 = l - short1; k1 <= l + short1; ++k1)
	            {
	                double d5 = (double)k1 + 0.5D - par1Entity.posX;
	                //z座標
	                for (int l1 = i1 - short1; l1 <= i1 + short1; ++l1)
	                {
	                    double d6 = (double)l1 + 0.5D - par1Entity.posZ;
	                    //高さ
	                    for (int i2 = this.worldServerInstance.getActualHeight() - 1; i2 >= 0; --i2)
	                    {
	                        if (this.worldServerInstance.getBlock(k1, i2, l1) == fantasy_world.dimensions.portal)
	                        {
	                            while (this.worldServerInstance.getBlock(k1, i2 - 1, l1) == fantasy_world.dimensions.portal)
	                            {
	                                --i2;
	                            }

	                            d4 = (double)i2 + 0.5D - par1Entity.posY;
	                            double d7 = d5 * d5 + d4 * d4 + d6 * d6;

	                            if (d3 < 0.0D || d7 < d3)
	                            {
	                                d3 = d7;
	                                i = k1;
	                                j = i2;
	                                k = l1;
	                            }
	                        }
	                    }
	                }
	            }
	        }
	        //System.out.println("ファイル名R3");
	        if (d3 >= 0.0D)
	        {
	        	//System.out.println("ファイル名R4");
	            if (flag)
	            {
	                this.destinationCoordinateCache.add(j1, new PortalPosition(i, j, k, this.worldServerInstance.getTotalWorldTime()));
	                this.destinationCoordinateKeys.add(Long.valueOf(j1));
	            }

	            double d8 = (double)i + 0.5D;
	            double d9 = (double)j + 0.5D;
	            d4 = (double)k + 0.5D;
	            int j2 = -1;

	            if (this.worldServerInstance.getBlock(i - 1, j, k) == fantasy_world.dimensions.portal)
	            {
	                j2 = 2;
	            }

	            if (this.worldServerInstance.getBlock(i + 1, j, k) == fantasy_world.dimensions.portal)
	            {
	                j2 = 0;
	            }

	            if (this.worldServerInstance.getBlock(i, j, k - 1) == fantasy_world.dimensions.portal)
	            {
	                j2 = 3;
	            }

	            if (this.worldServerInstance.getBlock(i, j, k + 1) == fantasy_world.dimensions.portal)
	            {
	                j2 = 1;
	            }

	            int k2 = par1Entity.getTeleportDirection();

	            if (j2 > -1)
	            {
	                int l2 = Direction.rotateLeft[j2];
	                int i3 = Direction.offsetX[j2];
	                int j3 = Direction.offsetZ[j2];
	                int k3 = Direction.offsetX[l2];
	                int l3 = Direction.offsetZ[l2];
	                boolean flag1 = !this.worldServerInstance.isAirBlock(i + i3 + k3, j, k + j3 + l3) || !this.worldServerInstance.isAirBlock(i + i3 + k3, j + 1, k + j3 + l3);
	                boolean flag2 = !this.worldServerInstance.isAirBlock(i + i3, j, k + j3) || !this.worldServerInstance.isAirBlock(i + i3, j + 1, k + j3);

	                if (flag1 && flag2)
	                {
	                    j2 = Direction.rotateOpposite[j2];
	                    l2 = Direction.rotateOpposite[l2];
	                    i3 = Direction.offsetX[j2];
	                    j3 = Direction.offsetZ[j2];
	                    k3 = Direction.offsetX[l2];
	                    l3 = Direction.offsetZ[l2];
	                    k1 = i - k3;
	                    d8 -= (double)k3;
	                    int i4 = k - l3;
	                    d4 -= (double)l3;
	                    flag1 = !this.worldServerInstance.isAirBlock(k1 + i3 + k3, j, i4 + j3 + l3) || !this.worldServerInstance.isAirBlock(k1 + i3 + k3, j + 1, i4 + j3 + l3);
	                    flag2 = !this.worldServerInstance.isAirBlock(k1 + i3, j, i4 + j3) || !this.worldServerInstance.isAirBlock(k1 + i3, j + 1, i4 + j3);
	                }

	                float f1 = 0.5F;
	                float f2 = 0.5F;

	                if (!flag1 && flag2)
	                {
	                    f1 = 1.0F;
	                }
	                else if (flag1 && !flag2)
	                {
	                    f1 = 0.0F;
	                }
	                else if (flag1 && flag2)
	                {
	                    f2 = 0.0F;
	                }

	                d8 += (double)((float)k3 * f1 + f2 * (float)i3);
	                d4 += (double)((float)l3 * f1 + f2 * (float)j3);
	                float f3 = 0.0F;
	                float f4 = 0.0F;
	                float f5 = 0.0F;
	                float f6 = 0.0F;

	                if (j2 == k2)
	                {
	                    f3 = 1.0F;
	                    f4 = 1.0F;
	                }
	                else if (j2 == Direction.rotateOpposite[k2])
	                {
	                    f3 = -1.0F;
	                    f4 = -1.0F;
	                }
	                else if (j2 == Direction.rotateRight[k2])
	                {
	                    f5 = 1.0F;
	                    f6 = -1.0F;
	                }
	                else
	                {
	                    f5 = -1.0F;
	                    f6 = 1.0F;
	                }

	                double d10 = par1Entity.motionX;
	                double d11 = par1Entity.motionZ;
	                par1Entity.motionX = d10 * (double)f3 + d11 * (double)f6;
	                par1Entity.motionZ = d10 * (double)f5 + d11 * (double)f4;
	                par1Entity.rotationYaw = par8 - (float)(k2 * 90) + (float)(j2 * 90);
	            }
	            else
	            {
	            	//System.out.println("ファイル名R5");
	                par1Entity.motionX = par1Entity.motionY = par1Entity.motionZ = 0.0D;
	            }
	            //System.out.println("ファイル名R6");
	            //System.out.println("ファイル名Ea");
	            //checkPosSafely(par1Entity, (int)d8 + 2, (int)d9 + 2, (int)d4);
	            //System.out.println("ファイル名E2")

	            par1Entity.setLocationAndAngles(d8 , d9 + 1, d4 + 1, par1Entity.rotationYaw, par1Entity.rotationPitch);
	            //System.out.println("ファイル名E3");
	            return true;
	        }
	        else
	        {
	        	//System.out.println("ファイル名R7");
	            return false;
	        }
	    }

	    public boolean makePortal(Entity par1Entity)
	    {
	        int i = MathHelper.floor_double(par1Entity.posX);
	        int j = MathHelper.floor_double(par1Entity.posY);
	        int k = MathHelper.floor_double(par1Entity.posZ);

	        Block blc = newDimID == 0 ? Blocks.netherrack : fantasy_world.dimensions.portal;


	        for(int y = -1; y <= 3; y++)
	        {
	        	for(int x = -2; x <= 3; x++)
	        	{
	            	for(int z = -2; z <= 2; z++)
	            	{
	            		this.worldServerInstance.setBlock(i + x, j + y, k + z, Blocks.air, 0, 2);
	            	}
	        	}
	        }


	        int y = -1;
	        for(int x = -2 ; x <= 3; x++)
	        {
	            for(int z = -2 ; z <= 3; z++)
	            {
	            	if(z == 0 && (x >= -1 && x <= 2))
	            	{
	            		this.worldServerInstance.setBlock(i + x, j + y, k + z, Blocks.netherrack, 0, 2);
	            	}else
	            	{
	            		this.worldServerInstance.setBlock(i + x, j + y, k + z, blc, 0, 2);
	            	}
	            }
	        }
	        y = 0;
	        this.worldServerInstance.setBlock(i - 1, j + y, k, fantasy_world.dimensions.portal, 0, 2);
	        this.worldServerInstance.setBlock(i + 2, j + y, k, fantasy_world.dimensions.portal, 0, 2);

	        y = 1;
	        this.worldServerInstance.setBlock(i - 1, j + y, k, Blocks.air, 0, 2);
	        this.worldServerInstance.setBlock(i + 2, j + y, k, Blocks.air, 0, 2);

	        y = 2;
	        this.worldServerInstance.setBlock(i - 1, j + y, k, Blocks.air, 0, 2);
	        this.worldServerInstance.setBlock(i + 2, j + y, k, Blocks.air, 0, 2);

	        y = 3;
	        this.worldServerInstance.setBlock(i - 1, j + y, k, Blocks.air, 7, 2);
	        this.worldServerInstance.setBlock(i, j + y, k, Blocks.air, 7, 2);
	        this.worldServerInstance.setBlock(i + 1, j + y, k, Blocks.air, 7, 2);
	        this.worldServerInstance.setBlock(i + 2, j + y, k, Blocks.air, 7, 2);

	        for(y = 0; y <= 2; y++)
	        {
	        	for(int x = 0; x <= 1; x++)
	        	{
	        		this.worldServerInstance.setBlock(i + x, j + y, k, fantasy_world.dimensions.portal, 1, 2);
	        	}
	        }
	/*
	        for(int y = -1; y <= 0; y++)
	        {
	        	for(int x = -2; x <= 3; x++)
	        	{
	            	for(int z = -2; z <= 3; z++)
	            	{
	            		if((x >= 0 && x <= 1) && (z >= 0 && z <= 1) && y == 0)
	            		{
	            			this.worldServerInstance.setBlock(i + x, j + y, k + z, BlockList.DqmDimGate[dimIdx].blockID, 0, 2);
	            		}else if(((x == -1 || x == 2) && (z ==0 || z == 1)) || ((z == -1 || z == 2) && (x ==0 || x == 1)))
	            		{
	            			this.worldServerInstance.setBlock(i + x, j + y, k + z, BlockList.DqmDimBlocks_dirt[dimIdx].blockID, 0, 2);
	            		}else
	            		{
	            			this.worldServerInstance.setBlock(i + x, j + y, k + z, Block.dirt.blockID, 0, 2);
	            		}
	            	}
	        	}
	        }
	*/
	        return true;
	    }



		@Override
		public void removeStalePortalLocations(long time)
		{
			if (time % 100L == 0L)
			{
				Iterator<Long> iterator = coordKeys.iterator();
				long var1 = time - 600L;

				while (iterator.hasNext())
				{
					Long chunkSeed = iterator.next();
					PortalPosition portal = (PortalPosition)coordCache.getValueByKey(chunkSeed);

					if (portal == null || portal.lastUpdateTime < var1)
					{
						iterator.remove();
						coordCache.remove(chunkSeed);
					}
				}
			}
		}

		public boolean checkPosSafely(Entity par1Entity, int x, int y, int z)
		{
			int xA = x;
			int yA = y;
			int zA = z;
			for (int yP = 0; yP <= 1; yP++)
			{
				if(this.worldServerInstance.getBlockMetadata(xA, yA + yP, zA) != 0)
				{
					this.worldServerInstance.setBlock(xA, yA + yP, zA, fantasy_world.dimensions.portal, 0, 2);
				}
			}
			return true;

		}
}