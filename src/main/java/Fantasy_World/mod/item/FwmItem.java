package Fantasy_World.mod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import Fantasy_World.mod.fantasy_world;
import Fantasy_World.mod.item.base.ItemMagicRod;

public class FwmItem{
	public FwmItem(){
		// 杖
		fantasy_world.weapons.magic_rod = new ItemMagicRod().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("fantasy_world.FwmItem.magic_rod").setTextureName("fantasy_world:magic_rod");

		// 剣
		fantasy_world.weapons.crystal_sword = new ItemSword(fantasy_world.FwmToolMaterial.crystal_sword_mat).setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("fantasy_world.FwmItem.crystal_sword").setTextureName("fantasy_world:crystal_sword");
		fantasy_world.weapons.short_sword = new ItemSword(fantasy_world.FwmToolMaterial.short_sword_mat).setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("short_sword").setTextureName("fantasy_world:short_sword");

		// アイテム
		fantasy_world.items.crystal = new Item().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("crystal").setTextureName("fantasy_world:crystal");
		fantasy_world.items.crystal_stone = new Item/*c_stone*/().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("crystal_stone").setTextureName("fantasy_world:crystal_stone");
		fantasy_world.items.rod_stick = new Item().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("rod_stick").setTextureName("fantasy_world:rod_stick");
		fantasy_world.items.crystal_dust = new Item().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("crystal_dust").setTextureName("fantasy_world:crystal_dust");
		fantasy_world.items.magic_leaf = new Item().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("magic_leaf").setTextureName("fantasy_world:magic_leaf");

		// 六大元素
		fantasy_world.items.fire_element = new Item().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("fire_element").setTextureName("fantasy_world:fire_element");
		fantasy_world.items.water_element = new Item().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("water_element").setTextureName("fantasy_world:water_element");
		fantasy_world.items.ice_element = new Item().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("ice_element").setTextureName("fantasy_world:ice_element");
		fantasy_world.items.wood_element = new Item().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("wood_element").setTextureName("fantasy_world:wood_element");
		fantasy_world.items.thunder_element = new Item().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("thunder_element").setTextureName("fantasy_world:thunder_element");
		fantasy_world.items.earth_element = new Item().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("earth_element").setTextureName("fantasy_world:earth_element");

		// 三大元素
		fantasy_world.items.normal_element = new Item().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("normal_element").setTextureName("fantasy_world:normal_element");
		fantasy_world.items.light_element = new Item().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("light_element").setTextureName("fantasy_world:light_element");
		fantasy_world.items.dark_element = new Item().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("dark_element").setTextureName("fantasy_world:dark_element");

		// 食料


		// 木
	}
}



/* クリエイティブタブ一覧
tabBlock・・・建築ブロック
tabBrewing・・・醸造
tabCombat・・・戦闘
tabDecorations・・・装飾ブロック
tabFood・・・食料
tabMaterials・・・材料
tabMisc・・・その他
tabRedStone・・・レッドストーン
tabTools・・・道具
tabTransport・・・移動
*/