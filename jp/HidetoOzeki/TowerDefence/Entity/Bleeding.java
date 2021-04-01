/*    */ package jp.HidetoOzeki.TowerDefence.Entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ import jp.HidetoOzeki.TowerDefence.level.vec;
/*    */ 
/*    */ public class Bleeding
/*    */   extends Particle {
/*    */   public Bleeding(double x, double y, double z) {
/* 11 */     this.x = x;
/* 12 */     this.y = y;
/* 13 */     this.z = z;
/* 14 */     this.vz = 0.2D + Math.random() * 0.2D;
/* 15 */     this.vx = Math.random() * 0.2D - 0.1D;
/* 16 */     this.vy = Math.random() * 0.2D - 0.1D;
/* 17 */     this.life = (int)(Math.random() * 100.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Screen screen, Level level) {
/* 22 */     vec v = new vec(this.x * 8.0D, this.y * 8.0D, this.z * 8.0D);
/* 23 */     v.rotate(level.angle, level.w * 8 / 2, level.d * 8 / 2);
/* 24 */     v.setpos(level.xof, level.yof);
/* 25 */     screen.put((v.getpos()).x, (v.getpos()).y, 16724787, v.getzbuffer());
/* 26 */     screen.put((v.getpos()).x + 1, (v.getpos()).y, 16724787, v.getzbuffer());
/* 27 */     screen.put((v.getpos()).x, (v.getpos()).y + 1, 16711731, v.getzbuffer());
/* 28 */     screen.put((v.getpos()).x + 1, (v.getpos()).y + 1, 16724736, v.getzbuffer());
/* 29 */     super.render(screen, level);
/*    */   }
/*    */   
/*    */   public void tick(Level level) {
/* 33 */     this.x += this.vx;
/* 34 */     this.y += this.vy;
/* 35 */     this.z += this.vz;
/* 36 */     this.vz -= 0.02D;
/* 37 */     if (this.z < 16.0D) this.vz = -this.vz * 0.9D; 
/* 38 */     this.life--;
/* 39 */     super.tick(level);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void remove(ArrayList<Entity> e) {
/* 45 */     super.remove(e);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\Bleeding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */