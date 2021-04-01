/*    */ package jp.HidetoOzeki.TowerDefence.gfx;
/*    */ 
/*    */ import java.awt.Color;
/*    */ 
/*    */ public class Color8Bit
/*    */ {
/*  7 */   int bpp = 8;
/*  8 */   int mult = 256 / this.bpp;
/*  9 */   int[] r = new int[this.bpp];
/* 10 */   int[] g = new int[this.bpp];
/* 11 */   int[] b = new int[this.bpp];
/*    */   public Color8Bit() {
/* 13 */     for (int i = 0; i < this.r.length; i++) {
/* 14 */       this.r[i] = i * this.mult;
/* 15 */       this.g[i] = i * this.mult;
/* 16 */       this.b[i] = i * this.mult;
/*    */     } 
/*    */   }
/*    */   public int convert(int red, int green, int blue) {
/* 20 */     int r0 = red / this.mult;
/* 21 */     int g0 = green / this.mult;
/* 22 */     int b0 = blue / this.mult;
/* 23 */     return (new Color(this.r[r0], this.g[g0], this.b[b0])).getRGB();
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\gfx\Color8Bit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */