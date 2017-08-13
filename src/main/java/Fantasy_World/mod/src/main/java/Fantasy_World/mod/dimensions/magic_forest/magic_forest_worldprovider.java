package Fantasy_World.mod.dimensions.magic_forest;

import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.storage.DerivedWorldInfo;
import net.minecraft.world.storage.WorldInfo;
import Fantasy_World.mod.fantasy_world;
import cpw.mods.fml.common.ObfuscationReflectionHelper;


public class magic_forest_worldprovider extends WorldProvider{
	@Override
    public String getDimensionName() {
        return "Sample";
    }

    // 独自のワールドタイプやワールドチャンクマネージャーを設定
    @Override
    protected void registerWorldChunkManager() {
        worldObj.getWorldInfo().setTerrainType(magic_forest_worldtype.worldTypeSample);
        worldChunkMgr = new magic_forest_chunkmanager(worldObj);
        setDimension(fantasy_world.dimensions.dimensionID);
        hasNoSky = false;
    }

    // チャンク生成は独自のチャンクプロバイダが担当する
    @Override
    public IChunkProvider createChunkGenerator() {
        return new magic_forest_chunkprovider(worldObj, worldObj.getSeed(), true);
    }

    @Override
	public boolean canRespawnHere() {
		return true;
	}

	@Override
	public void resetRainAndThunder() {
		super.resetRainAndThunder();

		if(this.worldObj.getGameRules().getGameRuleBooleanValue("doDaylightCycle")) {

			WorldInfo worldInfo = ObfuscationReflectionHelper.getPrivateValue(DerivedWorldInfo.class, (DerivedWorldInfo)worldObj.getWorldInfo(), "theWorldInfo", "field_76115_a");
			long i = worldInfo.getWorldTime() + 24000L;
			worldInfo.setWorldTime(i - i % 24000L);
		}
	}
}