package Fantasy_World.mod.entity;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public abstract class EntityMob_FWM extends EntityMob
{
    private static final String __OBFID = "CL_00001692";

    public EntityMob_FWM(World p_i1738_1_)
    {
        super(p_i1738_1_);
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(3, new EntityAILookIdle(this));
        this.experienceValue = 5;
    }

    @Override
    public boolean isAIEnabled() { return true; }

    @Override
    public String getLivingSound() { return null /* MOBが生きている時の音のファイルパスを返す。 */ ; }

    @Override
    public String getHurtSound() { return null /* MOBがダメージを受けた時の音のファイルパスを返す。 */ ; }

    @Override
    public String getDeathSound() { return null /* MOBが死亡した時の音のファイルパスを返す。*/ ; }

    /*
    * このMobが動いているときの音のファイルパスを返す.
    * 引数のblockはMobの下にあるBlock.
    */
    @Override
    protected void func_145780_a(int x, int y, int z, Block block)
    {
	this.playSound(null, 0.15F, 1.0F);
    }

    protected String getJumpSound()
    {
        return null;
    }

   @Override
    public EnumCreatureAttribute getCreatureAttribute() { return EnumCreatureAttribute.UNDEFINED; }

    @Override
    public Item getDropItem() { return null /* ドロップするアイテム */ ; }

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
    public ItemStack ItemCheck(ItemStack item){
    	item.getItem();

    	return item;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        int i = MathHelper.floor_double(this.posX);
        int j = MathHelper.floor_double(this.boundingBox.minY);
        int k = MathHelper.floor_double(this.posZ);
        //return

        return this.worldObj.checkNoEntityCollision(this.boundingBox) &&
          	   this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() &&
          	   !this.worldObj.isAnyLiquid(this.boundingBox) &&
          	   this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL;
    }
}