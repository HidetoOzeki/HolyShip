/*    */ package jp.HidetoOzeki.TowerDefence.Entity;
/*    */ 
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ import jp.HidetoOzeki.TowerDefence.level.vec;
/*    */ 
/*    */ public class Boat extends Entity {
/*  8 */   double dir = 0.0D;
/*  9 */   int pop = 0;
/*    */   
/*    */   public Boat(Ship ship) {
/* 12 */     this.life = 10;
/* 13 */     this.x = ship.x + Math.random() * 64.0D - 32.0D;
/* 14 */     this.y = ship.y + Math.random() * 16.0D - 8.0D;
/* 15 */     this.z = ship.z - 4.0D;
/*    */   }
/*    */   boolean land = false;
/*    */   
/*    */   public void render(Screen screen, Level level) {
/* 20 */     vec v = new vec(this.x, this.y, this.z);
/* 21 */     v.rotate(level.angle, level.w * 8 / 2, level.d * 8 / 2);
/* 22 */     v.setpos(level.xof, level.yof);
/* 23 */     double xx = this.x + Math.sin(this.dir) * 1.0D;
/* 24 */     double zz = this.z + Math.cos(this.dir) * 1.0D;
/* 25 */     vec v2 = new vec(xx, 16.0D, zz);
/* 26 */     v2.rotate(level.angle, level.w * 8 / 2, level.d * 8 / 2);
/* 27 */     v2.setpos(level.xof, level.yof);
/* 28 */     double dx = ((v.getpos()).x - (v2.getpos()).x);
/* 29 */     double dy = ((v.getpos()).y - (v2.getpos()).y);
/* 30 */     double rot = Math.atan2(dx, dy);
/* 31 */     for (int i = 0; i < 14; i++) {
/* 32 */       for (int j = -4; j < 4; j++) {
/* 33 */         double px = (v.getpos()).x + Math.sin(rot) * i + Math.cos(rot) * j;
/* 34 */         double py = (v.getpos()).y + (Math.cos(rot) * i - Math.sin(rot) * j) * 0.7D;
/* 35 */         screen.put((int)px, (int)py, 6697779, v.getzbuffer());
/* 36 */         if (i == 0 || j == -4 || i == 13 || j == 3) {
/* 37 */           for (int y0 = 0; y0 < 4.0D - Math.sin(i * 0.3D) * 1.5D; y0++) {
/* 38 */             screen.put((int)px, (int)py - y0, 8939076 - y0 * 255, v.getzbuffer() - 3);
/*    */           }
/*    */         }
/*    */       } 
/*    */     } 
/* 43 */     super.render(screen, level);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(Level level) {
/* 48 */     if (this.y > 128.0D) {
/* 49 */       this.vy = -0.18D + Math.random() * 0.4D - 0.2D;
/*    */     } else {
/* 51 */       this.land = true;
/*    */     } 
/* 53 */     this.vy *= 0.9D;
/* 54 */     this.x += this.vx;
/* 55 */     this.y += this.vy;
/* 56 */     this.pop = 0;
/* 57 */     super.tick(level);
/*    */   }
/*    */   public void put(Enemy e) {
/* 60 */     this.pop++;
/* 61 */     e.x = this.x / 8.0D;
/* 62 */     e.y = this.y / 8.0D + this.pop * 0.5D;
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\Boat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */