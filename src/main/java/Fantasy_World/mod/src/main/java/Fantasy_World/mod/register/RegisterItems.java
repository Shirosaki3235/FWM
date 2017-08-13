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

		// 防具
		GameRegistry.registerItem(fantasy_world.armors.magic_armor_helmet, "magic_armor_helmet");
		GameRegistry.registerItem(fantasy_world.armors.magic_armor_chest, "magic_armor_chest");
		GameRegistry.registerItem(fantasy_world.armors.magic_armor_leggins, "magic_armor_leggins");
		GameRegistry.registerItem(fantasy_world.armors.magic_armor_boots, "magic_armor_boots");

		// アイテム
		GameRegistry.registerItem(fantasy_world.items.crystal, "crystal");
		GameRegistry.registerItem(fantasy_world.items.crystal_stone, "crystal_stone");
		GameRegistry.registerItem(fantasy_world.items.crystal_dust, "crystal dust");
		GameRegistry.registerItem(fantasy_world.items.rod_stick, "rod stick");

		GameRegistry.registerItem(fantasy_world.items.magic_leaf, "magic leaf");

		// 六大元素
		GameRegistry.registerItem(fantasy_world.items.fire_element, "fire element");
		GameRegistry.registerItem(fantasy_world.items.water_element, "water element");
		GameRegistry.registerItem(fantasy_world.items.ice_element, "ice_element");
		GameRegistry.registerItem(fantasy_world.items.wood_element, "wood_element");
		GameRegistry.registerItem(fantasy_world.items.thunder_element, "thunder_element");
		GameRegistry.registerItem(fantasy_world.items.earth_element, "earth_element");

		// 三大元素
		GameRegistry.registerItem(fantasy_world.items.normal_element, "normal_element");
		GameRegistry.registerItem(fantasy_world.items.light_element, "light_element");
		GameRegistry.registerItem(fantasy_world.items.dark_element, "dark_element");

		// 食料
		GameRegistry.registerItem(fantasy_world.foods.fast_drag, "fast_drag");
		GameRegistry.registerItem(fantasy_world.foods.herb_seed, "herb_seed");
		GameRegistry.registerItem(fantasy_world.foods.herb_laef, "herb_laef");

	}
}