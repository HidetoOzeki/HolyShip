/*    */ package jp.HidetoOzeki.TowerDefence.Entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ 
/*    */ public class Entity {
/*    */   public double x;
/*    */   public double y;
/*    */   public double z;
/* 11 */   public int life = 1;
/*    */   
/*    */   public double vx;
/*    */   public double vy;
/*    */   public double vz;
/*    */   
/*    */   public void render(Screen screen, Level level) {}
/*    */   
/*    */   public void tick(Level level) {}
/*    */   
/*    */   public void remove(ArrayList<Entity> e) {
/* 22 */     e.remove(this);
/*    */   }
/*    */   public void autoremove(ArrayList<Entity> e) {
/* 25 */     if (this.life < 1) e.remove(this); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\Entity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */