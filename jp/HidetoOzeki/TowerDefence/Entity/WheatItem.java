/*    */ package jp.HidetoOzeki.TowerDefence.Entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ import jp.HidetoOzeki.TowerDefence.level.vec;
/*    */ 
/*    */ public class WheatItem
/*    */   extends Item
/*    */ {
/*    */   public WheatItem(double x, double y, double z) {
/* 12 */     super(x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Screen screen, Level level) {
/* 17 */     vec v = new vec(this.x * 8.0D, this.y * 8.0D, this.z * 8.0D);
/* 18 */     v.rotate(level.angle, level.w * 8 / 2, level.d * 8 / 2);
/* 19 */     v.setpos(level.xof, level.yof);
/* 20 */     screen.render((v.getpos()).x - 4, (v.getpos()).y, 0, 9, v.getzbuffer() - 2);
/* 21 */     super.render(screen, level);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick(Level level) {
/* 27 */     super.tick(level);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void move(double vx, double vy, double vz) {
/* 33 */     super.move(vx, vy, vz);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void toss(double vx, double vy) {
/* 39 */     super.toss(vx, vy);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void remove(ArrayList<Entity> e) {
/* 45 */     super.remove(e);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void autoremove(ArrayList<Entity> e) {
/* 51 */     super.autoremove(e);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\WheatItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */