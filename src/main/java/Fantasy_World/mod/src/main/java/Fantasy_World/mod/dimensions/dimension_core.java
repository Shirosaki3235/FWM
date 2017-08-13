package Fantasy_World.mod.dimensions;

import net.minecraft.block.material.Material;
import net.minecraftforge.common.DimensionManager;
import Fantasy_World.mod.fantasy_world;
import Fantasy_World.mod.dimensions.magic_forest.magic_forest_portal;
import Fantasy_World.mod.dimensions.magic_forest.magic_forest_worldprovider;
import cpw.mods.fml.common.registry.GameRegistry;

public class dimension_core{
	public dimension_core(){
		// 独自ディメンションへのポータルブロックを追加
		fantasy_world.dimensions.portal = new magic_forest_portal(Material.portal).setBlockName("SampleDimension:portal").setCreativeTab(fantasy_world.Tabs.BlockTabs);
		GameRegistry.registerBlock(fantasy_world.dimensions.portal, "portal");

		// 独自ディメンション用のワールドプロバイダを登録
		DimensionManager.registerProviderType(fantasy_world.dimensions.providerType, magic_forest_worldprovider.class, false);

		// 独自ディメンションを登録
		DimensionManager.registerDimension(fantasy_world.dimensions.dimensionID, fantasy_world.dimensions.providerType);
	}
}