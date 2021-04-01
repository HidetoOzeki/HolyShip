/*    */ package jp.HidetoOzeki.TowerDefence.Entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ 
/*    */ 
/*    */ public class Particle
/*    */   extends Entity
/*    */ {
/*    */   public void render(Screen screen, Level level) {
/* 12 */     super.render(screen, level);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(Level level) {
/* 17 */     this.life--;
/*    */     
/* 19 */     super.tick(level);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void remove(ArrayList<Entity> e) {
/* 25 */     super.remove(e);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\Particle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */