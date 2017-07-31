package Fantasy_World.mod.register;

import Fantasy_World.mod.fantasy_world;
import cpw.mods.fml.common.registry.GameRegistry;



public class RegisterItems{
	public RegisterItems(){
		// 杖
		GameRegistry.registerItem(fantasy_world.weapons.magic_rod, "fantasy_world.FwmItem.magic_rod");

		// 剣
		GameRegistry.registerItem(fantasy_world.weapons.short_sword, "short_sword");
		GameRegistry.registerItem(fantasy_world.weapons.crystal_sword, "fantasy_world.FwmItem.crystal_sword");

		// アイテム
		GameRegistry.registerItem(fantasy_world.items.crystal, "crystal");
		GameRegistry.registerItem(fantasy_world.items.crystal_stone, "crystal_stone");

		// 食料
		GameRegistry.registerItem(fantasy_world.foods, "fast_drag");

	}
}