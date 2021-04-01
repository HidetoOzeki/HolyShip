/*     */ package jp.HidetoOzeki.TowerDefence.gfx;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import jp.HidetoOzeki.TowerDefence.History;
/*     */ import jp.HidetoOzeki.TowerDefence.level.vec;
/*     */ 
/*     */ public class Screen {
/*     */   int w;
/*     */   int h;
/*     */   public int[] pixels;
/*     */   int[] zbuffer;
/*     */   Bitmap sprite;
/*  13 */   int mcolor = 16777215;
/*     */   int xoffset;
/*     */   int yoffset;
/*     */   
/*     */   public Screen(int width, int height) {
/*  18 */     this.w = width;
/*  19 */     this.h = height;
/*  20 */     this.pixels = new int[this.w * this.h];
/*  21 */     this.zbuffer = new int[this.w * this.h];
/*  22 */     this.sprite = new Bitmap("/res/spritesheet8bit2.png");
/*     */   }
/*     */   
/*     */   public void clear() {
/*  26 */     clear(0);
/*     */   }
/*     */   
/*     */   public void clear(int col) {
/*  30 */     for (int i = 0; i < this.w * this.h; i++) {
/*  31 */       this.pixels[i] = col;
/*     */     }
/*     */   }
/*     */   
/*     */   public void clearbuffer() {
/*  36 */     for (int i = 0; i < this.w * this.h; i++) {
/*  37 */       this.zbuffer[i] = 240;
/*     */     }
/*     */   }
/*     */   
/*     */   public void render(int x, int y, int xo, int yo, int zb) {
/*  42 */     for (int y0 = 0; y0 < 8; y0++) {
/*  43 */       int py = y + y0;
/*  44 */       if (py >= 0 && py < this.h)
/*     */       {
/*  46 */         for (int x0 = 0; x0 < 8; x0++) {
/*  47 */           int px = x + x0;
/*  48 */           if (px >= 0 && px < this.w) {
/*     */             
/*  50 */             int col = this.sprite.pixels[xo * 8 + x0 + (yo * 8 + y0) * this.sprite.w];
/*  51 */             if (col != Color.black.getRGB())
/*  52 */               put(px, py, col, zb); 
/*     */           } 
/*     */         }  } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void mountColor(int mcolor) {
/*  59 */     this.mcolor = mcolor;
/*     */   }
/*     */   
/*     */   public void render(int x, int y, int xo, int yo) {
/*  63 */     x -= this.xoffset;
/*  64 */     y -= this.yoffset;
/*  65 */     for (int y0 = 0; y0 < 8; y0++) {
/*  66 */       int py = y + y0;
/*  67 */       if (py >= 0 && py < this.h)
/*     */       {
/*  69 */         for (int x0 = 0; x0 < 8; x0++) {
/*  70 */           int px = x + x0;
/*  71 */           if (px >= 0 && px < this.w) {
/*     */             
/*  73 */             int col = this.sprite.pixels[xo * 8 + x0 + (yo * 8 + y0) * this.sprite.w];
/*  74 */             if (col != Color.black.getRGB())
/*  75 */               put(px, py, col + this.mcolor); 
/*     */           } 
/*     */         }  } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setOffset(int xo, int yo) {
/*  82 */     this.xoffset = xo;
/*  83 */     this.yoffset = yo;
/*     */   }
/*     */   
/*     */   public void draw(vec v) {
/*  87 */     draw(v, 16777215);
/*     */   }
/*     */   
/*     */   public void draw(vec v, int col) {
/*  91 */     int x = (int)v.x;
/*  92 */     int y = (int)(v.y / 2.0D - v.z);
/*  93 */     x += this.w / 2;
/*  94 */     y += this.h / 2;
/*  95 */     put(x, y, col);
/*     */   }
/*     */   
/*     */   public void put(int x, int y, int col) {
/*  99 */     if (x > 0 && x < this.w && 
/* 100 */       y > 0 && y < this.h) {
/* 101 */       this.pixels[x + y * this.w] = col;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int get(int x, int y) {
/* 107 */     if (x > 0 && x < this.w && 
/* 108 */       y > 0 && y < this.h) {
/* 109 */       return this.pixels[x + y * this.w];
/*     */     }
/*     */     
/* 112 */     return 0;
/*     */   }
/*     */   
/*     */   public void put(int x, int y, int col, int zb) {
/* 116 */     if (x > 0 && x < this.w && 
/* 117 */       y > 0 && y < this.h && 
/* 118 */       this.zbuffer[x + y * this.w] > zb) {
/* 119 */       this.pixels[x + y * this.w] = col;
/* 120 */       this.zbuffer[x + y * this.w] = zb;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw(int[] p) {
/* 127 */     for (int i = 0; i < this.w * this.h; i++)
/*     */     {
/* 129 */       p[i] = this.pixels[i];
/*     */     }
/*     */   }
/*     */   
/*     */   public void ShowHistory(History hist, int x, int y) {
/* 134 */     for (int i = 0; i < hist.texts.length; i++) {
/* 135 */       String s = hist.get()[i];
/* 136 */       if (s != null) {
/* 137 */         Font.drawFont(this, s, x, i * 8 + y, 16777215);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public int[] getRed() {
/* 143 */     return this.pixels;
/*     */   }
/*     */   
/*     */   public int[] getGreen() {
/* 147 */     return null;
/*     */   }
/*     */   
/*     */   public int[] getBlue() {
/* 151 */     return null;
/*     */   }
/*     */   
/*     */   public int[] getRaster() {
/* 155 */     return this.zbuffer;
/*     */   }
/*     */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\gfx\Screen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */