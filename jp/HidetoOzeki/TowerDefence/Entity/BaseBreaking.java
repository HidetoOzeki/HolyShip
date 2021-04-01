/*    */ package jp.HidetoOzeki.TowerDefence.Entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ import jp.HidetoOzeki.TowerDefence.level.vec;
/*    */ 
/*    */ public class BaseBreaking
/*    */   extends Particle {
/*    */   boolean pat = false;
/* 11 */   int upd = 0;
/*    */   public BaseBreaking(double x, double y, double z) {
/* 13 */     this.life = 120;
/* 14 */     this.x = x;
/* 15 */     this.y = y;
/* 16 */     this.z = z;
/* 17 */     double rot = Math.random() * 3.14D * 2.0D;
/* 18 */     this.vx = Math.sin(rot) * 0.05D;
/* 19 */     this.vy = Math.cos(rot) * 0.05D;
/* 20 */     this.vz = 0.2D + Math.random() * 0.02D - 0.01D;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Screen screen, Level level) {
/* 25 */     int yof = 13;
/* 26 */     if (this.pat) yof = 12; 
/* 27 */     vec v = new vec(this.x * 8.0D, this.y * 8.0D, this.z * 8.0D);
/* 28 */     v.rotate(level.angle, level.w * 8 / 2, level.d * 8 / 2);
/* 29 */     v.setpos(level.xof, level.yof);
/* 30 */     screen.render((v.getpos()).x, (v.getpos()).y, 3, yof, v.getzbuffer());
/* 31 */     super.render(screen, level);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(Level level) {
/* 36 */     this.upd++;
/* 37 */     if (this.upd > 15) {
/* 38 */       this.pat = !this.pat;
/* 39 */       this.upd = 0;
/*    */     } 
/* 41 */     this.x += this.vx;
/* 42 */     this.y += this.vy;
/* 43 */     this.z += this.vz;
/* 44 */     this.vz -= 0.003D;
/*    */     
/* 46 */     super.tick(level);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void remove(ArrayList<Entity> e) {
/* 52 */     super.remove(e);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\BaseBreaking.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */