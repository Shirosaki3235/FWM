package Fantasy_World.mod.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import Fantasy_World.mod.fantasy_world;
import Fantasy_World.mod.item.base.ItemMagicRod;

public class FwmItem{
	public static final int HELMET   = 0;
	public static final int PLATE    = 1;
	public static final int LEGGINGS = 2;
	public static final int BOOTS    = 3;

	public FwmItem(){
		// 杖
		fantasy_world.weapons.magic_rod = new ItemMagicRod().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("fantasy_world.FwmItem.magic_rod")/*.setTextureName("fantasy_world:magic_rod")*/;

		// 剣
		fantasy_world.weapons.crystal_sword = new ItemSword(fantasy_world.FwmToolMaterial.crystal_sword_mat).setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("fantasy_world.FwmItem.crystal_sword").setTextureName("fantasy_world:crystal_sword");
		fantasy_world.weapons.short_sword = new ItemSword(fantasy_world.FwmToolMaterial.short_sword_mat).setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("short_sword").setTextureName("fantasy_world:short_sword");


		// アイテム
		fantasy_world.items.crystal = new Item().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("crystal").setTextureName("fantasy_world:crystal");
		fantasy_world.items.crystal_stone = new Item/*c_stone*/().setCreativeTab(fantasy_world.Tabs.ItemTabs).setUnlocalizedName("crystal_stone").setTextureName("fantasy_world:crystal_stone");

		// 食料


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