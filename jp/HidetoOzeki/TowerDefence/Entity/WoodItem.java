/*    */ package jp.HidetoOzeki.TowerDefence.Entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Items;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ import jp.HidetoOzeki.TowerDefence.level.vec;
/*    */ 
/*    */ public class WoodItem
/*    */   extends Item
/*    */ {
/*    */   public WoodItem(double x, double y, double z) {
/* 13 */     super(x, y, z);
/* 14 */     this.itemid = Items.wood.id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Screen screen, Level level) {
/* 19 */     vec v = new vec(this.x * 8.0D, this.y * 8.0D, this.z * 8.0D);
/* 20 */     v.rotate(level.angle, level.w * 8 / 2, level.d * 8 / 2);
/* 21 */     v.setpos(level.xof, level.yof);
/* 22 */     screen.render((v.getpos()).x - 4, (v.getpos()).y, 0, 8, v.getzbuffer() - 2);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick(Level level) {
/* 28 */     super.tick(level);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void remove(ArrayList<Entity> e) {
/* 34 */     super.remove(e);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void autoremove(ArrayList<Entity> e) {
/* 40 */     super.autoremove(e);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\WoodItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */