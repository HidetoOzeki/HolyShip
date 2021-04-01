/*     */ package jp.HidetoOzeki.TowerDefence.Entity;
/*     */ 
/*     */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*     */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*     */ import jp.HidetoOzeki.TowerDefence.level.vec;
/*     */ 
/*     */ public class Archer extends Mob {
/*   8 */   double walkspeed = 0.05D;
/*   9 */   int msec = 0;
/*  10 */   double dir = 0.0D;
/*  11 */   double acc = 0.0D;
/*  12 */   int msec0 = 0;
/*  13 */   double targetX = 0.0D;
/*  14 */   double targetY = 0.0D;
/*  15 */   double targetZ = 0.0D;
/*     */   boolean hasTarget = true;
/*     */   
/*     */   public Archer(double x, double y) {
/*  19 */     super(x, y);
/*  20 */     this.targetX = 16.0D;
/*  21 */     this.targetY = 32.0D;
/*     */   }
/*     */   
/*     */   boolean onground = true;
/*     */   
/*     */   public void render(Screen screen, Level level) {
/*  27 */     vec v = new vec(this.x * 8.0D, this.y * 8.0D - 5.0D, this.z * 8.0D);
/*  28 */     v.rotate(level.angle, level.w * 8 / 2, level.d * 8 / 2);
/*  29 */     v.setpos(level.xof, level.yof);
/*  30 */     int px = (v.getpos()).x;
/*  31 */     int py = (v.getpos()).y;
/*  32 */     int skincolor = 11171703;
/*  33 */     screen.put(px, py, skincolor, v.getzbuffer());
/*  34 */     screen.put(px + 1, py + 1, skincolor, v.getzbuffer());
/*  35 */     screen.put(px + 1, py, skincolor, v.getzbuffer());
/*  36 */     screen.put(px, py + 1, skincolor, v.getzbuffer());
/*  37 */     py += 2;
/*  38 */     int bodcolor = 13421772;
/*  39 */     screen.put(px, py, bodcolor, v.getzbuffer());
/*  40 */     screen.put(px + 1, py + 1, bodcolor, v.getzbuffer());
/*  41 */     screen.put(px + 1, py, bodcolor, v.getzbuffer());
/*  42 */     screen.put(px, py + 1, bodcolor, v.getzbuffer());
/*  43 */     screen.put(px, py + 2, bodcolor, v.getzbuffer());
/*  44 */     screen.put(px + 1, py + 2, bodcolor, v.getzbuffer());
/*  45 */     super.render(screen, level);
/*     */   }
/*     */ 
/*     */   
/*     */   public void collide(Mob mob) {
/*  50 */     if (mob != this && this.onground && 
/*  51 */       mob instanceof Archer) {
/*  52 */       double dx = mob.x - this.x;
/*  53 */       double dy = mob.y - this.y;
/*  54 */       double distance = Math.sqrt(dx * dx + dy * dy);
/*  55 */       if (distance < 0.5D && distance > 0.01D) {
/*  56 */         double refangle = Math.atan2(dx, dy);
/*  57 */         this.vx -= Math.sin(refangle) * this.walkspeed;
/*  58 */         this.vy -= Math.cos(refangle) * this.walkspeed;
/*     */       } 
/*     */     } 
/*     */     
/*  62 */     super.collide(mob);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(Level level) {
/*  67 */     if (this.hasTarget) this.msec++; 
/*  68 */     this.msec0++;
/*  69 */     if (this.msec > 60) {
/*  70 */       double dx = this.targetX - this.x;
/*  71 */       double dy = this.targetY - this.y;
/*  72 */       double anglehol = Math.atan2(dx, dy);
/*  73 */       double anglevert = 1.0D;
/*  74 */       Arrow arrow = new Arrow(anglehol, anglevert, this.x, this.y, this.z);
/*  75 */       level.entities.add(arrow);
/*  76 */       this.msec = 0;
/*     */     } 
/*  78 */     if (this.msec0 > 20) {
/*  79 */       this.acc = Math.random() * 0.4D - 0.2D;
/*  80 */       this.msec0 = 0;
/*     */     } 
/*  82 */     int tx = (int)this.x;
/*  83 */     int ty = (int)this.y;
/*  84 */     this.dir += this.acc;
/*  85 */     if (this.onground) {
/*  86 */       this.vx = Math.sin(this.dir) * 0.1D;
/*  87 */       this.vy = Math.cos(this.dir) * 0.1D;
/*     */     } 
/*  89 */     if (level.collideMap[tx + ty * 32] && this.z < 24.0D) {
/*  90 */       this.vz = 0.03D;
/*  91 */       this.onground = false;
/*     */     } 
/*  93 */     if (this.z + this.vz < 17.0D)
/*  94 */       this.onground = true; 
/*  95 */     if (!this.onground && !level.collideMap[tx + ty * 32]) {
/*  96 */       this.vz -= 0.02D;
/*     */     }
/*  98 */     this.x += this.vx;
/*  99 */     this.y += this.vy;
/* 100 */     this.z += this.vz;
/* 101 */     this.vx *= 0.9D;
/* 102 */     this.vz *= 0.9D;
/* 103 */     this.vy *= 0.9D;
/* 104 */     if (this.x < 0.0D)
/* 105 */       this.x = 0.0D; 
/* 106 */     if (this.x > 32.0D)
/* 107 */       this.x = 32.0D; 
/* 108 */     if (this.y < 0.0D)
/* 109 */       this.y = 0.0D; 
/* 110 */     if (this.y > 16.0D)
/* 111 */       this.y = 16.0D; 
/* 112 */     super.tick(level);
/*     */   }
/*     */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\Archer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */