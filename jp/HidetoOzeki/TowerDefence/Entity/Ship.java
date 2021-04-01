/*     */ package jp.HidetoOzeki.TowerDefence.Entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*     */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*     */ import jp.HidetoOzeki.TowerDefence.level.vec;
/*     */ 
/*     */ public class Ship
/*     */   extends Entity {
/*     */   double vertical;
/*     */   double horizontal;
/*  12 */   double angle = 0.0D; public double gunx; public double guny; public double gunz;
/*  13 */   int dir = 0;
/*  14 */   int xa = 0;
/*  15 */   int ya = 0;
/*  16 */   double rot = 0.0D;
/*  17 */   int sec = 0;
/*     */   boolean arrived = false;
/*     */   double arriveY;
/*  20 */   int msec = 0;
/*  21 */   double vertr = 0.0D;
/*  22 */   int boats = 2;
/*     */   public Ship(double x, double y, double z) {
/*  24 */     this.x = x;
/*  25 */     this.y = y;
/*  26 */     this.z = z;
/*  27 */     this.arriveY = 256.0D - Math.random() * 32.0D * 2.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Screen screen, Level level) {
/*  32 */     vec v = new vec(this.x, this.y, this.z);
/*  33 */     v.rotate(level.angle, level.w * 8 / 2, level.d * 8 / 2);
/*  34 */     v.setpos(level.xof, level.yof);
/*  35 */     double xx = this.x + Math.sin(this.dir) * 1.0D;
/*  36 */     double zz = this.z + Math.cos(this.dir) * 1.0D;
/*  37 */     vec v2 = new vec(xx, 16.0D, zz);
/*  38 */     v2.rotate(level.angle, level.w * 8 / 2, level.d * 8 / 2);
/*  39 */     v2.setpos(level.xof, level.yof);
/*  40 */     double dx = ((v.getpos()).x - (v2.getpos()).x);
/*  41 */     double dy = ((v.getpos()).y - (v2.getpos()).y);
/*  42 */     double rot = Math.atan2(dx, dy);
/*  43 */     for (int i = 0; i < 24; i++) {
/*  44 */       for (int j = 0; j < 20; j++) {
/*  45 */         double px = (v.getpos()).x + Math.sin(rot) * i + Math.cos(rot) * j;
/*  46 */         double py = (v.getpos()).y + (Math.cos(rot) * i - Math.sin(rot) * j) * 0.7D;
/*  47 */         screen.put((int)px, (int)py, 6697779, v.getzbuffer());
/*  48 */         if (i == 0 || j == 0 || i == 23 || j == 19) {
/*  49 */           for (int y0 = 0; y0 < 6.0D - Math.sin(i * 0.2D) * 1.5D; y0++) {
/*  50 */             screen.put((int)px, (int)py - y0, 8939076 - y0 * 255, v.getzbuffer() - 3);
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(Level level) {
/*  59 */     if (this.y < this.arriveY) this.arrived = true; 
/*  60 */     if (!this.arrived) this.y -= 0.3D; 
/*  61 */     if (this.arrived && this.boats > 0) {
/*  62 */       this.boats--;
/*  63 */       Boat b = new Boat(this);
/*  64 */       for (int i = 0; i < 3; i++) {
/*  65 */         EnemySoldier es = new EnemySoldier(16.0D, 16.0D);
/*  66 */         es.boat = b;
/*  67 */         level.mobs.add(es);
/*     */       } 
/*  69 */       level.entities.add(b);
/*     */     } 
/*  71 */     int theta = (int)Math.toDegrees(this.rot) - 135;
/*  72 */     if (theta < 0.0D) {
/*  73 */       theta = (int)(theta + 360.0D);
/*     */     }
/*  75 */     this.dir = theta / 51;
/*  76 */     if (this.dir > 7)
/*  77 */       this.dir = 0; 
/*  78 */     if (this.dir < 0)
/*  79 */       this.dir = 0; 
/*  80 */     this.vertical = 1.57D;
/*  81 */     this.horizontal = 3.14D + Math.sin(this.angle) / 3.0D;
/*  82 */     double turn = Math.random() * 10.0D;
/*  83 */     this.msec++;
/*  84 */     this.vertr = 0.0D;
/*  85 */     if (this.msec > 15) {
/*  86 */       this.vertr = Math.random() * 5.0D;
/*  87 */       this.msec = 0;
/*     */     } 
/*  89 */     this.angle += turn * 0.01D;
/*  90 */     this.vertical += this.vertr * 0.2D;
/*  91 */     if (this.arrived) {
/*  92 */       this.sec++;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 103 */     super.tick(level);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(ArrayList<Entity> e) {
/* 109 */     super.remove(e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void autoremove(ArrayList<Entity> e) {
/* 115 */     super.autoremove(e);
/*     */   }
/*     */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\Ship.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */