package Fantasy_World.mod.AI;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAIFollowOwner_FWM extends EntityAIBase
{
	// このAIを搭載したentity
		EntityCreature owner;

		public static boolean flag;

		// 突進する対象となるプレイヤー
		EntityPlayer target;

		// 突進するスピード
		float speed;

		public EntityAIFollowOwner_FWM(EntityCreature entity, float f) {
			owner = entity;
			speed = f;
			flag = true;
			setMutexBits(3);
		}

		/*
		 * このAIによる行動を開始すべきかどうかの判定を行うメソッド
		 * EntityAITasksのonUpdateTasksメソッドから呼び出され、戻り値によりこのAIによる行動を実行するかどうかの判定が行われる
		 * trueを返却すると、EntityAITasksはstartExecuting、updateTask、continueExecutingを呼び出す
		 */
		@Override
		public boolean shouldExecute() {
			// 一番近くのプレイヤーを探す
			target = owner.worldObj.getClosestPlayerToEntity(owner, 128.0D);

			if(target != null) {
				if(owner.getDistanceSqToEntity(target) < 2.0D) {
					// 既に接近してたら何もしない
					return false;
				}
				//else if (128.0D < owner.getDistanceSqToEntity(target) && owner.getDistanceSqToEntity(target) > 2.0D) {return true;}
				else{return true;}
				// プレイヤーが持っているアイテム
				/*
				ItemStack itemStack = target.getCurrentEquippedItem();
				if(itemStack != null) {
					if(itemStack.getItem() == items.aoi_spawn_stone) {
						// レッドストーンを持ってたら突進する
						return true;
					}
					// 赤い染料やレッドストーントーチも赤いけどこのサンプルでは省略
				}*/
			}
			return false;
		}

		/*
		 * このAIによる行動を続行すべきかどうかの判定を行うメソッド
		 * shouldExecuteが開始判定を行うのに対し、こちらはすでに開始された行動を続行すべきかどうかの判定を行う
		 */
		@Override
		public boolean continueExecuting() {
			// EntityAIBaseで定義済み。オーバーライドしなければshouldExecuteをそのまま利用する
			return super.continueExecuting();
		}

		/*
		 * このAIによる行動が起動したときの初回の行動を定義する
		 * つまり、shouldExecuteがtrueの場合の行動
		 * ここで記述した行動は1回しか実行されないので注意
		 * shouldExecute→startExecuting→continueExecuting→updateTask→continueExecuting→updateTask→…
		 * のように実行される
		 */
		@Override
		public void startExecuting() {
		}

		/*
		 * このAIによる行動を終了するときの処理
		 */
		@Override
		public void resetTask() {
			target = null;
		}

		/*
		 * このAIによる行動を続行する場合の行動を定義する
		 * つまり、continueExecutingがtrueの場合の行動であり、1回の行動（startExecutingで定義された行動）で全て終わる場合は不要
		 * 例えば、飼い主にずっと追従するようなAIの場合、継続して飼い主の位置に向かって移動するように、updateTaskで移動先を更新するような実装をする
		 */
		@Override
		public void updateTask() {
			if(target != null) {
				if(flag){
				owner.getNavigator().tryMoveToEntityLiving(target, speed);
				}
			}
		}
}