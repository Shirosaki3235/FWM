package Fantasy_World.mod.entity.mobentity;

import java.util.Calendar;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;
import Fantasy_World.mod.AI.MagicAIFire_FWM;
import Fantasy_World.mod.entity.EntityMob_FWM;
import Fantasy_World.mod.entity.magicentity.normal_entity;

 // EntityLiving
public class seyana_entity extends EntityMob_FWM implements IRangedAttackMob{
	private MagicAIFire_FWM aiArrowAttack = new MagicAIFire_FWM(this, 1.0D, 20, 60, 15.0F);
	private EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.2D, false);

    public seyana_entity(World world) {
        super(world);
		this.tasks.addTask(1, new EntityAISwimming(this));
		//this.tasks.addTask(2, new EntityAIAvoidEntity_FWM(this, EntityPlayer.class, 16.0F, 0.8D, 1.33D));
        	// うろうろ移動するAIの追加
		this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
		// 見回すAIの追加
		this.tasks.addTask(4, new EntityAILookIdle(this));
		//this.tasks.addTask(4, new MagicAIFire_FWM(this, world));

		// あたり判定の設定1.0F＝1ブロック	MagicAIFire_FWM
		this.setSize(this.width * 0.75F, this.height * 0.5F);

		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

	/**MOBの速度やHPを変更するメソッド*/
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(128D);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.5D);
	}

    @Override
    public boolean isAIEnabled() { return true; }

    @Override
    public String getLivingSound() { return "fantasy_world:entity.mob.seyana_liv" /* MOBが生きている時の音のファイルパスを返す。 */ ; }

    @Override
    public String getHurtSound() { return "fantasy_world:entity.mob.seyana_dam" /* MOBがダメージを受けた時の音のファイルパスを返す。 */ ; }

    @Override
    public String getDeathSound() { return "fantasy_world:entity.mob.seyana_death" /* MOBが死亡した時の音のファイルパスを返す。*/ ; }

    /*
    * このMobが動いているときの音のファイルパスを返す.
    * 引数のblockはMobの下にあるBlock.
    */
    @Override
    protected void func_145780_a(int x, int y, int z, Block block)
    {
	this.playSound("mob.slime.step", 0.15F, 1.0F);
    }

    protected String getJumpSound()
    {
        return "mob.slime." + "small";
    }

   @Override
    public EnumCreatureAttribute getCreatureAttribute() { return EnumCreatureAttribute.UNDEFINED; }

    @Override
    public Item getDropItem() { return Items.slime_ball /* ドロップするアイテム */ ; }

    /**MOB死亡時に呼ばれるメソッド*/
    public void onDeath(DamageSource source)
    {
    	super.onDeath(source);
    	if(source.getSourceOfDamage() != null && source.getSourceOfDamage() instanceof EntityPlayer)
    	{
    		EntityPlayer player = (EntityPlayer) source.getSourceOfDamage();
    		 if(!this.worldObj.isRemote)
    		 {
				   //player.addChatMessage(new ChatComponentText("Aoi is dead."));
				   //player.triggerAchievement(AchievementList.mineWood);
    		 }
    	}
    }

    /**ダメージを食らうか否かを判定するメソッド*/
    @Override
    public boolean attackEntityFrom(DamageSource source, float damage)
    {
    	if(source.isExplosion())
    	{
    		return true;
    	}
    	else if(source.isFireDamage())
    	{
    		return super.attackEntityFrom(source, damage * 2);
    	}
    	else
    	{
    		return super.attackEntityFrom(source, damage);
    	}
    }
/*
    public void setTamed(boolean p_70903_1_)
    {
        super.setTamed(p_70903_1_);

        if (p_70903_1_)
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        }
        else
        {
            this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8.0D);
        }
    }
    public boolean interact(EntityPlayer p_70085_1_)
    {
        ItemStack itemstack = p_70085_1_.inventory.getCurrentItem();
    	if (itemstack != null && itemstack.getItem() == Items.bone && !this.isAngry())
        {
            if (!p_70085_1_.capabilities.isCreativeMode)
            {
                --itemstack.stackSize;
            }

            if (itemstack.stackSize <= 0)
            {
                p_70085_1_.inventory.setInventorySlotContents(p_70085_1_.inventory.currentItem, (ItemStack)null);
            }

            if (!this.worldObj.isRemote)
            {
                if (this.rand.nextInt(3) == 0)
                {
                    this.setTamed(true);
                    this.setPathToEntity((PathEntity)null);
                    this.setAttackTarget((EntityLivingBase)null);
                    this.aiSit.setSitting(true);
                    this.setHealth(20.0F);
                    this.func_152115_b(p_70085_1_.getUniqueID().toString());
                    this.playTameEffect(true);
                    this.worldObj.setEntityState(this, (byte)7);
                }
                else
                {
                    this.playTameEffect(false);
                    this.worldObj.setEntityState(this, (byte)6);
                }
            }

            return true;
        }

        return super.interact(p_70085_1_);
    }
*/

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_,float p_82196_2_) {
		// TODO 自動生成されたメソッド・スタブ
		int cooltime = 0; // 一旦凍結。常に0を入れる
		//public magic_rod_entity(World par1World, EntityLivingBase par2EntityLivingBase, float speed, float speed2, double damage, double range, int cool)
		//EntityArrow entityarrow = new EntityArrow(this.worldObj, this, p_82196_1_, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
		//                            EntityArrow(World p_i1755_1_, EntityLivingBase p_i1755_2_, EntityLivingBase p_i1755_3_, float p_i1755_4_, float p_i1755_5_)
		//= new magic_rod_entity(par2World, par3EntityPlayer, f, 0.0F, dam, 4.0F, cooltime);
		//magic_rod_entity bullet = new magic_rod_entity(this.worldObj, p_82196_1_, 2.0F, 0.0F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4), 20.0F, cooltime);
		normal_entity bullet = new normal_entity(this.worldObj, this, p_82196_1_, 1.6F, (float)(14 - this.worldObj.difficultySetting.getDifficultyId() * 4));
		int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItem());
        int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItem());
        bullet.setDamage((double)(p_82196_2_ * 2.0F) + this.rand.nextGaussian() * 0.25D + (double)((float)this.worldObj.difficultySetting.getDifficultyId() * 0.11F));

        if (i > 0)
        {
        	bullet.setDamage(bullet.getDamage() + (double)i * 0.5D + 0.5D);
        }

        if (j > 0)
        {
        	bullet.setKnockbackStrength(j);
        }

        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItem()) > 0)
        {
        	bullet.setFire(100);
        }

        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));

        // TODO Entityを放つ
        this.worldObj.spawnEntityInWorld(bullet);
	}

	public IEntityLivingData onSpawnWithEgg(IEntityLivingData p_110161_1_)
    {
        p_110161_1_ = super.onSpawnWithEgg(p_110161_1_);

        if (this.worldObj.provider instanceof WorldProviderHell && this.getRNG().nextInt(5) > 0)
        {
            this.tasks.addTask(4, this.aiAttackOnCollide);
            this.setCurrentItemOrArmor(0, new ItemStack(Items.stone_sword));
            this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
        }
        else
        {
            this.tasks.addTask(4, this.aiArrowAttack);
            this.addRandomArmor();
            this.enchantEquipment();
        }

        this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * this.worldObj.func_147462_b(this.posX, this.posY, this.posZ));

        if (this.getEquipmentInSlot(4) == null)
        {
            Calendar calendar = this.worldObj.getCurrentDate();

            if (calendar.get(2) + 1 == 10 && calendar.get(5) == 31 && this.rand.nextFloat() < 0.25F)
            {
                this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.lit_pumpkin : Blocks.pumpkin));
                this.equipmentDropChances[4] = 0.0F;
            }
        }

        return p_110161_1_;
    }

    /**
     * sets this entity's combat AI.
     */
    public void setCombatTask()
    {
        this.tasks.removeTask(this.aiAttackOnCollide);
        this.tasks.removeTask(this.aiArrowAttack);
        ItemStack itemstack = this.getHeldItem();

        if (itemstack != null && itemstack.getItem() == Items.bow)
        {
            this.tasks.addTask(4, this.aiArrowAttack);
        }
        else
        {
            this.tasks.addTask(4, this.aiAttackOnCollide);
        }
    }

    public void setCurrentItemOrArmor(int p_70062_1_, ItemStack p_70062_2_)
    {
        super.setCurrentItemOrArmor(p_70062_1_, p_70062_2_);

        if (!this.worldObj.isRemote && p_70062_1_ == 0)
        {
            this.setCombatTask();
        }
    }

    protected void addRandomArmor()
    {
        super.addRandomArmor();
        this.setCurrentItemOrArmor(0, new ItemStack(Items.bow));
    }
}