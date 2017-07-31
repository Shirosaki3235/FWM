package Fantasy_World.mod.item.base;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import Fantasy_World.mod.entity.magicentity.magic_rod_entity;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public class ItemMagicRod extends Item{
	private IIcon itemIcon;

	public ItemMagicRod(){
		this.maxStackSize = 1;
		this.setMaxDamage(4);
		this.setNoRepair();
	}
	/*
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		this.itemIcon = par1IconRegister.registerIcon("fantasy_world:magic_rod");
	}*/

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}

	/* ここから先が追加した部分 */



	// 耐久値を減らそうとする処理が呼ばれた場合に握りつぶす。アイテムロストなどの事故防止。
	@Override
	public void setDamage(ItemStack stack, int damage) {
		return;
	}

	// このメソッドは、プレイヤーがブロックをターゲットしていないときに呼ばれる方のRightClickメソッド。
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		boolean inf = EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;
		int power = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);
		int fire = EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack);
		int punch = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);
		//int poison = EnchantmentHelper.getEnchantmentLevel(fantasy_world., par1ItemStack);
		int unb = EnchantmentHelper.getEnchantmentLevel(Enchantment.unbreaking.effectId, par1ItemStack);

		// クリエイティブモードか
		boolean creative = par3EntityPlayer.capabilities.isCreativeMode;

		// マガジンが空でないかどうか
		boolean hasCharge = false;
		if (par1ItemStack.getItem() == this) {
			// 弾薬減少処理。第三引数のbooleanをfalseにすると、実際には減らさないシミュレーション処理になる
			int c2 = this.discharge(par1ItemStack, 1, false);
			if (c2 > 0) {
				hasCharge = true;
				this.discharge(par1ItemStack, 1, true);
			}
		}

		// 消費できる弾があったら実行
		if (creative || hasCharge) {
			// ダメージ量
			float fireDam = 2.0F * fire;
			float powerDam = 0.4F * power;
			//float poisonDam = 0.5F * poison;
			float dam = 2.0F + fireDam + powerDam;
			// 0.0F~1.0Fの乱数
			float ram = par2World.rand.nextFloat();
			//float ram = par2World.rand.nextFloat();
			int cooltime = 0; // 一旦凍結。常に0を入れる
			// 弾速
			float speed = 1.0F + (punch * 1.0F);

			// ランダムに速度を変えながら、同時に4発ずつ発射する。
			for (int i = 0; i < 4; i++) {
				float f = speed * (i * 0.35F) + ram;
				// 生成
				magic_rod_entity bullet = new magic_rod_entity(par2World, par3EntityPlayer, f, 0.0F, dam, 4.0F, cooltime);
				if (fire > 0) {
					bullet.setAdvFire(true);
				}
				// Entityの着火機能を利用
				bullet.setFire(100);
				// Entityのスポーン処理はisRemote判定を利用してサーバー側のみで行うこと!
				if (!par2World.isRemote) {
					par2World.spawnEntityInWorld(bullet);
				}
				par2World.playSoundAtEntity(par3EntityPlayer, "fantasy_world:magic_rod", 0.6F, 1.6F);
			}
		} else {
			// この処理に飛んでいるということは弾切れなので、弾薬を補充する処理をする
			// 今はまだ弾薬アイテムを追加前なので、仮のアイテムを消費させておく
			if (par3EntityPlayer.inventory.hasItem(Items.gunpowder) || inf) {
				// 弾薬補充処理。マガジンはNBTタグに入っている
				NBTTagCompound nbt = par1ItemStack.getTagCompound();
				if (nbt != null) {
					nbt.setInteger("magazine", 100 + unb * 50);
					par1ItemStack.setTagCompound(nbt);
				}
				else {
					NBTTagCompound nbt2 = new NBTTagCompound();
					nbt2.setInteger("magazine", 100 + unb * 50);
					par1ItemStack.setTagCompound(nbt2);
				}
				if (!inf)
					par3EntityPlayer.inventory.consumeInventoryItem(Items.gunpowder);

				// playerのインベントリの弾薬アイテムを減らす。この処理はInventoryPlayerの既存処理を利用している。
				par3EntityPlayer.inventory.consumeInventoryItem(Items.gunpowder);

				// バニラのドアガチャSEのピッチを弄って、それっぽくしている
				par2World.playSoundAtEntity(par3EntityPlayer, "random.door_close", 1.0F,
						1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + 1.3F);
			}
		}
		return par1ItemStack;
	}


	// onEatenのほうの右クリック処理ではそのままItemStackを返す(つまりなにもしない)
	@Override
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		return par1ItemStack;
	}

	// onItemRightClickで呼んでいる弾の消費処理
	// 今は使っていないが、弾薬を2以上減らす場合にも応用できるようにしている
	public int discharge(ItemStack item, int amount, boolean flag) {

		if (item == null)
			return 0;

		// NBTに入れている弾の数が足りているかの判定と、第三引数がtrueなら減少させている
		NBTTagCompound nbt = item.getTagCompound();
		int charge = 0;
		int reduce = 0;
		if (nbt != null && nbt.hasKey("magazine")) {
			charge = nbt.getInteger("magazine");
		}

		reduce = Math.min(amount, charge);

		if (flag) {
			if (nbt != null) {
				nbt.setInteger("magazine", (charge - reduce));
				item.setTagCompound(nbt);
			} else {
				NBTTagCompound nbt2 = new NBTTagCompound();
				nbt2.setInteger("magazine", (charge - reduce));
				item.setTagCompound(nbt2);
			}
		}

		return reduce;
	}

	// マウスオーバー時の表示情報。マガジンの残量/MAXを表示している。
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
		super.addInformation(par1ItemStack, par2EntityPlayer, (java.util.List) par3List, par4);
		NBTTagCompound nbt = par1ItemStack.getTagCompound();
		int charge = 0;
		int max = 100;
		if (nbt != null && nbt.hasKey("magazine")) {
			charge = nbt.getInteger("magazine");
		}

		String s = new String("magazine : " + charge + "/" + max);
		((java.util.List) par3List).add(s);
	}


	// 耐久値ゲージを表示するかどうかのフラグ
	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return true;
	}

	// このメソッドで耐久値の代わりに弾の残量をゲージ表示できるようにしている。
	// 0.0Dを返したときがMAXで、1.0Dを返すとゲージがなくなったように見える。よって、MAX - 残量 を計算して返している。
	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		NBTTagCompound nbt = stack.getTagCompound();
		int charge = 0;
		int max = 100;
		if (nbt != null && nbt.hasKey("magazine")) {
			charge = nbt.getInteger("magazine");
			charge = MathHelper.clamp_int(charge, 0, max);
		}

		int i = max - charge;
		return (double) i / (double) max;
	}
}