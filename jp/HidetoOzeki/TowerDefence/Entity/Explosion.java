/*    */ package jp.HidetoOzeki.TowerDefence.Entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ import jp.HidetoOzeki.TowerDefence.level.vec;
/*    */ 
/*    */ public class Explosion
/*    */   extends Particle {
/*    */   public Explosion(double x, double y, double z) {
/* 11 */     this.x = x;
/* 12 */     this.y = y;
/* 13 */     this.z = z;
/* 14 */     this.life = 60;
/*    */   }
/*    */   
/*    */   public void render(Screen screen, Level level) {
/* 18 */     vec v = new vec(this.x * 8.0D, this.y * 8.0D, this.z * 8.0D);
/* 19 */     v.rotate(level.angle, level.w * 8 / 2, level.d * 8 / 2);
/* 20 */     v.setpos(level.xof, level.yof);
/* 21 */     screen.mountColor(5592405);
/* 22 */     screen.render((v.getpos()).x, (v.getpos()).y, 0, 0, v.getzbuffer());
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(Level level) {
/* 27 */     this.z += 0.02D;
/*    */     
/* 29 */     super.tick(level);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void remove(ArrayList<Entity> e) {
/* 35 */     super.remove(e);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\Explosion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */