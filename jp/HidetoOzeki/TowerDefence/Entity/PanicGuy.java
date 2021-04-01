/*    */ package jp.HidetoOzeki.TowerDefence.Entity;
/*    */ 
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ import jp.HidetoOzeki.TowerDefence.level.vec;
/*    */ 
/*    */ public class PanicGuy extends Mob {
/*  8 */   double rot = 0.0D;
/*  9 */   int tick = 0;
/* 10 */   int col = (int)(Math.random() * 1.6777215E7D);
/* 11 */   double forward = 0.0D;
/*    */   int targetx;
/*    */   int targety;
/* 14 */   double rotacc = 0.0D;
/*    */   public PanicGuy(double x, double y) {
/* 16 */     super(x, y);
/* 17 */     this.vx = 0.01D;
/* 18 */     this.vy = 0.01D;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Screen screen, Level level) {
/* 23 */     vec v = new vec(this.x * 8.0D, this.y * 8.0D, this.z * 8.0D);
/* 24 */     v.rotate(level.angle, level.w * 8 / 2, level.d * 8 / 2);
/* 25 */     v.setpos(level.xof, level.yof);
/* 26 */     screen.put((v.getpos()).x - 4, (v.getpos()).y - 1, 11171703, v.getzbuffer());
/* 27 */     screen.put((v.getpos()).x - 4, (v.getpos()).y, 0x774444 & this.col, v.getzbuffer());
/* 28 */     screen.put((v.getpos()).x - 4, (v.getpos()).y + 1, 0x774444 & this.col, v.getzbuffer());
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(Level level) {
/* 33 */     this.tick++;
/* 34 */     if (this.tick > 10) {
/* 35 */       this.rotacc = Math.random() * 0.4D - 0.2D;
/* 36 */       this.tick = 0;
/*    */     } 
/* 38 */     this.rot += this.rotacc;
/* 39 */     this.forward = 0.05D;
/* 40 */     this.vx = Math.sin(this.rot) * this.forward;
/* 41 */     this.vy = Math.cos(this.rot) * this.forward;
/* 42 */     double tx = this.x + this.vx;
/* 43 */     double ty = this.y + this.vy;
/* 44 */     double tz = this.z + this.vz;
/* 45 */     int tile = level.getTile((int)tx, (int)ty, (int)tz);
/* 46 */     if (tile == 0) {
/* 47 */       this.z += this.vz;
/* 48 */       this.x += this.vx;
/* 49 */       this.y += this.vy;
/*    */     } 
/* 51 */     if (this.y < 1.0D) this.y = 1.0D; 
/* 52 */     if (this.y > 13.0D) this.y = 13.0D; 
/* 53 */     if (this.x < 1.0D) this.x = 1.0D; 
/* 54 */     if (this.x > 31.0D) this.x = 31.0D; 
/* 55 */     this.vx *= 0.9D;
/* 56 */     this.vy *= 0.9D;
/* 57 */     super.tick(level);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\PanicGuy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */