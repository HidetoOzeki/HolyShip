/*    */ package jp.HidetoOzeki.TowerDefence.level;
/*    */ 
/*    */ 
/*    */ public class vec
/*    */ {
/*    */   public double x;
/*    */   public double y;
/*    */   public double z;
/*    */   
/*    */   public vec(double x, double y, double z) {
/* 11 */     this.x = x;
/* 12 */     this.y = y;
/* 13 */     this.z = z;
/*    */   } double xof; double yof;
/*    */   public vec() {}
/*    */   public void rotate(double rot, int centx, int centy) {
/* 17 */     double x0 = this.x - centx;
/* 18 */     double y0 = this.y - centy;
/* 19 */     this.x = Math.sin(rot) * x0 + Math.cos(rot) * y0;
/* 20 */     this.y = Math.cos(rot) * x0 - Math.sin(rot) * y0;
/*    */   }
/*    */   public void setpos(int xo, int yo) {
/* 23 */     this.xof = xo;
/* 24 */     this.yof = yo;
/* 25 */     this.x += xo;
/* 26 */     this.y += yo;
/*    */   }
/*    */   public vec2 getpos() {
/* 29 */     vec2 pos = new vec2();
/* 30 */     pos.x = (int)this.x;
/* 31 */     pos.y = (int)(this.y * 0.5D - this.z * 0.7D) + 32;
/* 32 */     return pos;
/*    */   }
/*    */   public int getzbuffer() {
/* 35 */     double y0 = this.y * 0.5D - this.z * 0.5D;
/* 36 */     double zbuf = this.z + y0;
/* 37 */     return (int)-zbuf;
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\level\vec.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */