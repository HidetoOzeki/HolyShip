/*    */ package jp.HidetoOzeki.TowerDefence.Entity;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.ArrayList;
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ import jp.HidetoOzeki.TowerDefence.level.vec;
/*    */ 
/*    */ public class Breaking
/*    */   extends Particle {
/*    */   int col;
/*    */   double floor;
/* 13 */   int mc = (int)(Math.random() * 32.0D);
/* 14 */   Color c = new Color(this.mc, this.mc, this.mc);
/*    */   public Breaking(double x, double y, double z, int col) {
/* 16 */     this.x = x + Math.random() * 0.6D - 0.3D;
/* 17 */     this.y = y + Math.random() * 0.6D - 0.3D;
/* 18 */     this.z = z + Math.random() * 0.4D - 0.2D;
/* 19 */     this.col = col;
/* 20 */     this.floor = z;
/* 21 */     double rr = Math.random() * 6.28D;
/* 22 */     double sp = Math.random() * 0.03D;
/* 23 */     double zz = 0.05D + Math.random() * 0.15D;
/* 24 */     this.vx = Math.sin(rr) * sp;
/* 25 */     this.vy = Math.cos(rr) * sp;
/* 26 */     this.vz = zz;
/* 27 */     this.life = 60 + (int)(Math.random() * 60.0D);
/*    */   }
/*    */   
/*    */   public void render(Screen screen, Level level) {
/* 31 */     vec v = new vec(this.x * 8.0D, this.y * 8.0D, this.z * 8.0D);
/* 32 */     v.rotate(level.angle, level.w * 8 / 2, level.d * 8 / 2);
/* 33 */     v.setpos(level.xof, level.yof);
/* 34 */     screen.put((v.getpos()).x, (v.getpos()).y, this.col | this.c.getRGB(), v.getzbuffer());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick(Level level) {
/* 40 */     this.x += this.vx;
/* 41 */     this.y += this.vy;
/* 42 */     this.z += this.vz;
/* 43 */     if (this.floor < this.z) this.vz -= 0.01D; 
/* 44 */     if (this.floor > this.z) this.vz = -this.vz * 0.7D; 
/* 45 */     super.tick(level);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void remove(ArrayList<Entity> e) {
/* 51 */     super.remove(e);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\Breaking.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */