/*    */ package jp.HidetoOzeki.TowerDefence.Entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ import jp.HidetoOzeki.TowerDefence.level.vec;
/*    */ 
/*    */ public class Arrow
/*    */   extends Entity
/*    */ {
/*    */   double ax;
/*    */   double ay;
/*    */   double az;
/* 14 */   double vel = 2.0D; vec v1; vec v2; double angleHol; double angleVert;
/*    */   
/*    */   public Arrow(double ah, double av, double xx, double yy, double zz) {
/* 17 */     this.angleHol = ah;
/* 18 */     this.angleVert = av;
/* 19 */     this.life = 120;
/* 20 */     this.ax = xx;
/* 21 */     this.ay = yy;
/* 22 */     this.az = zz;
/* 23 */     this.vx = Math.sin(this.angleHol) * this.vel;
/* 24 */     this.vy = Math.cos(this.angleHol) * this.vel;
/* 25 */     this.vz = Math.sin(this.angleVert) * this.vel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Screen screen, Level level) {
/* 30 */     this.v1 = new vec(this.ax * 8.0D, this.ay * 8.0D, this.az * 8.0D);
/* 31 */     this.v2 = new vec(this.v1.x + this.vx * 8.0D, this.v1.y + this.vy * 8.0D, this.v1.z + this.vz * 8.0D);
/* 32 */     vec rv = this.v1;
/* 33 */     rv.rotate(level.angle, level.w * 8 / 2, level.d * 8 / 2);
/* 34 */     rv.setpos(level.xof, level.yof);
/* 35 */     vec rv1 = this.v2;
/* 36 */     rv1.rotate(level.angle, level.w * 8 / 2, level.d * 8 / 2);
/* 37 */     rv1.setpos(level.xof, level.yof);
/* 38 */     double dx = ((rv.getpos()).x - (rv1.getpos()).x);
/* 39 */     double dy = ((rv.getpos()).y - (rv1.getpos()).y);
/* 40 */     double angle = Math.atan2(dx, dy);
/* 41 */     double dist = Math.sqrt(dx * dx + dy * dy);
/* 42 */     for (int i = 0; i < dist / 3.0D; i++) {
/* 43 */       double px = (rv.getpos()).x + Math.sin(angle) * i;
/* 44 */       double py = (rv.getpos()).y + Math.cos(angle) * i;
/* 45 */       screen.put((int)px, (int)py, 6697779, rv.getzbuffer());
/*    */     } 
/* 47 */     super.render(screen, level);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(Level level) {
/* 52 */     this.ax += this.vx * 0.1D;
/* 53 */     this.ay += this.vy * 0.1D;
/* 54 */     this.az += this.vz * 0.1D;
/* 55 */     this.vz -= 0.06D;
/* 56 */     this.life--;
/* 57 */     super.tick(level);
/*    */   }
/*    */ 
/*    */   
/*    */   public void remove(ArrayList<Entity> e) {
/* 62 */     if (this.life < 1)
/* 63 */       e.remove(this); 
/* 64 */     super.remove(e);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void autoremove(ArrayList<Entity> e) {
/* 70 */     super.autoremove(e);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\Arrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */