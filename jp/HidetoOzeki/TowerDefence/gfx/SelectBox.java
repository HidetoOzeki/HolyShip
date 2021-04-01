/*     */ package jp.HidetoOzeki.TowerDefence.gfx;
/*     */ 
/*     */ 
/*     */ public class SelectBox
/*     */ {
/*     */   int len;
/*     */   public String[] name;
/*     */   String title;
/*     */   int x;
/*     */   
/*     */   public SelectBox(String[] s, int x, int y, int xs, int ys, String title) {
/*  12 */     this.name = s;
/*  13 */     this.len = s.length;
/*  14 */     this.x = x;
/*  15 */     this.y = y;
/*  16 */     this.xs = xs;
/*  17 */     this.ys = ys;
/*  18 */     this.title = title;
/*     */   }
/*     */   int y; int xs; int ys; public int select; int popup;
/*     */   public void renderBox(Screen screen) {
/*  22 */     for (int k = 0; k < this.xs * 8 + 14; k++) {
/*  23 */       for (int n = 0; n < this.ys * 8 + 14; n++) {
/*  24 */         screen.put(this.x + k - 4, this.y + n - 4, 0);
/*     */       }
/*     */     } 
/*  27 */     screen.setOffset(this.popup, this.popup);
/*     */     
/*  29 */     screen.render(this.x - 8, this.y - 8, 2, 4);
/*  30 */     screen.render(this.xs * 8 + this.x, this.y - 8, 3, 4);
/*  31 */     screen.render(this.x - 8, this.ys * 8 + this.y, 2, 5);
/*  32 */     screen.render(this.xs * 8 + this.x, this.ys * 8 + this.y, 3, 5);
/*  33 */     for (int j = 0; j < this.ys; j++) {
/*  34 */       screen.render(this.x - 8, this.y + j * 8, 2, 7);
/*  35 */       screen.render(this.xs * 8 + this.x - 1, this.y + j * 8, 2, 8);
/*     */     } 
/*  37 */     for (int i = 0; i < this.xs; i++) {
/*  38 */       screen.render(this.x + i * 8, this.y - 8, 3, 7);
/*  39 */       screen.render(this.x + i * 8, this.ys * 8 + this.y - 1, 3, 6);
/*  40 */       for (int n = 0; n < this.ys; n++) {
/*  41 */         screen.render(this.x + i * 8, this.y + n * 8, 2, 6);
/*     */       }
/*     */     } 
/*  44 */     int xx = this.xs * 8 / 2 - this.title.length() * 4;
/*  45 */     for (int m = 0; m < this.title.length(); m++) {
/*  46 */       screen.render(this.x + xx + m * 8, this.y - 12, 2, 6);
/*     */     }
/*  48 */     Font.drawFont(screen, this.title, this.x + xx, this.y - 11, 7829265);
/*  49 */     Font.drawFont(screen, this.title, this.x + xx, this.y - 12, 16776977);
/*     */     
/*  51 */     screen.setOffset(0, 0);
/*     */   }
/*     */   
/*     */   public void render(Screen screen) {
/*  55 */     screen.setOffset(this.popup, this.popup);
/*     */     
/*  57 */     for (int i = 0; i < this.name.length; i++) {
/*  58 */       String s = this.name[i];
/*  59 */       int xx = this.xs * 8 / 2 - s.length() * 4;
/*  60 */       int yy = 1;
/*  61 */       if (i == this.select) {
/*  62 */         Font.drawFont(screen, ">", this.x + xx - 12, i * 10 + this.y + 8, 16777215);
/*  63 */         yy = 0;
/*     */       } 
/*  65 */       Font.drawFont(screen, s, this.x + xx, i * 10 + this.y + 8, 7829367);
/*  66 */       Font.drawFont(screen, s, this.x + xx - yy, i * 10 + this.y + 8 - yy, 16777215);
/*     */     } 
/*  68 */     screen.mountColor(0);
/*  69 */     screen.setOffset(0, 0);
/*  70 */     this.popup = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void renderGUI(Screen screen) {
/*  75 */     this.popup = 3;
/*  76 */     if (this.select < 0)
/*  77 */       this.select = this.len - 1; 
/*  78 */     if (this.select > this.len - 1)
/*  79 */       this.select = 0; 
/*  80 */     screen.setOffset(this.popup, this.popup);
/*  81 */     for (int i = 0; i < this.name.length; i++) {
/*  82 */       String s = this.name[i];
/*  83 */       int xx = this.xs * 8 / 2 - s.length() * 4;
/*  84 */       int yy = 1;
/*  85 */       Font.drawFont(screen, s, this.x + xx, i * 10 + this.y + 8, 7829367);
/*  86 */       Font.drawFont(screen, s, this.x + xx - yy, i * 10 + this.y + 8 - yy, 16777215);
/*     */     } 
/*  88 */     screen.setOffset(0, 0);
/*  89 */     this.popup = 0;
/*     */   }
/*     */   
/*     */   public int getNumber() {
/*  93 */     return this.select;
/*     */   }
/*     */   
/*     */   public void tick() {
/*  97 */     int l = this.name.length - 1;
/*  98 */     if (this.select < 0)
/*  99 */       this.select = l; 
/* 100 */     if (this.select > l)
/* 101 */       this.select = 0; 
/*     */   }
/*     */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\gfx\SelectBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */