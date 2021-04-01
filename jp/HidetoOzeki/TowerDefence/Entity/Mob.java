/*    */ package jp.HidetoOzeki.TowerDefence.Entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ 
/*    */ public class Mob
/*    */   extends Entity {
/*    */   public int TargetID;
/* 10 */   public int itempath = 0;
/*    */   
/*    */   public Mob(double x, double y) {
/* 13 */     this.x = x;
/* 14 */     this.y = y;
/* 15 */     this.z = 16.0D;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Screen screen, Level level) {
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
/*    */   public void collide(Mob mob) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void remove(ArrayList<Entity> e) {
/* 37 */     super.remove(e);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\Mob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */