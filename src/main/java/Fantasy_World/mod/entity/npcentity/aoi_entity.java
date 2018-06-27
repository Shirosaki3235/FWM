package Fantasy_World.mod.entity.npcentity;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import Fantasy_World.mod.fantasy_world;
import Fantasy_World.mod.AI.EntityAIFollowOwner_FWM;
import Fantasy_World.mod.entity.EntityMob_FWM;

 // EntityLiving
public class aoi_entity extends EntityMob_FWM {
    private static final String __OBFID = "CL_00001654";
    private float speed = 1.0F;
    private boolean flag = false;
    //private ItemStack itemStack;
    // このAIを搭載したentity
 	EntityCreature owner;
 	// 突進する対象となるプレイヤー
 	EntityPlayer target;

 	World world_;

    public aoi_entity(World world) {
        super(world);
        //this.getNavigator().setAvoidsWater(true);
        this.tasks.addTask(1, new EntityAISwimming(this));
		// 見回すAIの追加
		this.tasks.addTask(2, new EntityAILookIdle(this));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));

		world_ = world;

    }

	/**MOBの速度やHPを変更するメソッド*/
	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
		this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(128D);
		this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(100D);
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.5D);
	}

    @Override
    public boolean isAIEnabled() { return true; }

    @Override
    public String getLivingSound() { return "fantasy_world:entity.npc.aoi_liv" /* MOBが生きている時の音のファイルパスを返す。 */ ; }

    @Override
    public String getHurtSound() { return "fantasy_world:entity.npc.aoi_dam" /* MOBがダメージを受けた時の音のファイルパスを返す。 */ ; }

    @Override
    public String getDeathSound() { return "fantasy_world:entity.npc.aoi_death" /* MOBが死亡した時の音のファイルパスを返す。*/ ; }

    /*
    * このMobが動いているときの音のファイルパスを返す.
    * 引数のblockはMobの下にあるBlock.
*/
    @Override
    protected void func_145780_a(int x, int y, int z, Block block)
    {
	this.playSound("mob.skeleton.step", 0.15F, 1.0F);
    }

   @Override
    public EnumCreatureAttribute getCreatureAttribute() { return EnumCreatureAttribute.UNDEFINED; }

    @Override
    public Item getDropItem() { return fantasy_world.items.aoi_spawn_stone /* ドロップするアイテム */ ; }

    /**MOB死亡時に呼ばれるメソッド*/
    public void onDeath(DamageSource source)
    {
    	super.onDeath(source);
    	if(source.getSourceOfDamage() != null && source.getSourceOfDamage() instanceof EntityPlayer)
    	{
    		EntityPlayer player = (EntityPlayer) source.getSourceOfDamage();
    		 if(!this.worldObj.isRemote)
    		 {
				   player.addChatMessage(new ChatComponentText("葵「ぐへ」"));
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
    		return false;
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

    public void clearTasks()
    {
    	for(int cnt = 0; cnt < this.tasks.taskEntries.size(); cnt++)
    	{
            EntityAITasks.EntityAITaskEntry entityaitaskentry = (EntityAITasks.EntityAITaskEntry)this.tasks.taskEntries.get(cnt);
            EntityAIBase entityaibase1 = entityaitaskentry.action;
    		this.tasks.removeTask(entityaibase1);
    	}
    }

    public void setCombatTask()
    {
        this.tasks.addTask(1, new EntityAISwimming(this));
		// 見回すAIの追加
		this.tasks.addTask(2, new EntityAILookIdle(this));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
		if(flag){
			this.tasks.addTask(4, new EntityAIFollowOwner_FWM(this, speed));
		}
    }

	@Override
    public boolean interact(EntityPlayer entityplayer){
    	if(!this.worldObj.isRemote)
    	{
    		ItemStack itemStack = entityplayer.inventory.getCurrentItem();
    		if(entityplayer.isSneaking()){
    			entityplayer.openGui(fantasy_world.instance, 2, world_, (int)entityplayer.posX, (int)entityplayer.posY, (int)entityplayer.posZ);
    			return true;
    		}


    		if(itemStack != null && !entityplayer.isSneaking()){
    			if (itemStack.getItem() instanceof ItemFood)
                {
                    ItemFood itemfood = (ItemFood)itemStack.getItem();

                    if (this.getHealth() < 20.0F)
                    {
                        if (!entityplayer.capabilities.isCreativeMode)
                        {
                            --itemStack.stackSize;
                        }

                        this.heal((float)itemfood.func_150905_g(itemStack));

                        if (itemStack.stackSize <= 0)
                        {
                        	entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, (ItemStack)null);
                        }

                        return true;
                    }
                }

    			if(itemStack.stackSize > 0 && itemStack.getItem() == Items.redstone){
    				entityplayer.addChatMessage(new ChatComponentText("うちの体力は" + this.getHealth() + "だよ"));
    				itemStack.stackSize--;
    				return true;
    			}
    		}

    		else if(flag == false && !entityplayer.isSneaking())
    		{
    			this.playSound("fantasy_world:entity.npc.aoi_death", 1.0F,1.0F);
    			entityplayer.addChatMessage(new ChatComponentText("葵「ついていくよ」"));
    			flag = true;
    		}
    		else if(flag == true && !entityplayer.isSneaking())
    		{
    			this.playSound("fantasy_world:entity.npc.aoi_death", 1.0F,1.0F);
    			entityplayer.addChatMessage(new ChatComponentText("葵「待ってるね」"));
    			flag = false;
    		}
    		this.clearTasks();
    		this.setCombatTask();
    		return true;
    		//this.isClearTasks = true;
    	}
    	return true;
    }

}