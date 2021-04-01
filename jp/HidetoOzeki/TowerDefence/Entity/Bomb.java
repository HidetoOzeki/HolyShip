/*    */ package jp.HidetoOzeki.TowerDefence.Entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ import jp.HidetoOzeki.TowerDefence.level.vec;
/*    */ 
/*    */ public class Bomb
/*    */   extends Entity {
/* 10 */   public int texof = 0;
/*    */   public double spin;
/* 12 */   public int life = 360;
/*    */   public Bomb(int x, int y, int z) {
/* 14 */     this.x = x;
/* 15 */     this.y = y;
/* 16 */     this.z = z;
/*    */   }
/*    */   
/*    */   public void setvec(double vx, double vy, double vz) {
/* 20 */     this.vx = vx;
/* 21 */     this.vy = vy;
/* 22 */     this.vz = vz;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Screen screen, Level level) {
/* 27 */     vec v = new vec(this.x, this.y, this.z);
/* 28 */     v.rotate(level.angle, level.w * 8 / 2, level.d * 8 / 2);
/* 29 */     v.setpos(level.xof, level.yof);
/* 30 */     screen.render((v.getpos()).x - 4, (v.getpos()).y - 4, 1 + this.texof, 1, v.getzbuffer());
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(Level level) {
/* 35 */     this.life--;
/* 36 */     this.x += this.vx;
/* 37 */     this.y += this.vy;
/* 38 */     this.z += this.vz;
/* 39 */     this.vz -= 0.02D;
/* 40 */     super.tick(level);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void remove(ArrayList<Entity> e) {
/* 46 */     super.remove(e);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\Bomb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */