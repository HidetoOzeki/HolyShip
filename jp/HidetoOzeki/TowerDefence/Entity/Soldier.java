/*     */ package jp.HidetoOzeki.TowerDefence.Entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*     */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*     */ import jp.HidetoOzeki.TowerDefence.level.vec;
/*     */ 
/*     */ public class Soldier
/*     */   extends Mob {
/*  10 */   double walkspeed = 0.03D;
/*  11 */   double dir = 0.0D;
/*  12 */   double acc = 0.0D;
/*  13 */   int msec = 0;
/*  14 */   Mob targetEnemy = null;
/*  15 */   int attacktime = 0;
/*     */   
/*     */   public Soldier(double x, double y) {
/*  18 */     super(x, y);
/*  19 */     this.life = 30;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Screen screen, Level level) {
/*  25 */     vec v = new vec(this.x * 8.0D, this.y * 8.0D, this.z * 8.0D);
/*  26 */     v.rotate(level.angle, level.w * 8 / 2, level.d * 8 / 2);
/*  27 */     v.setpos(level.xof, level.yof);
/*  28 */     int px = (v.getpos()).x;
/*  29 */     int py = (v.getpos()).y;
/*  30 */     int skincolor = 11171703;
/*  31 */     screen.put(px, py, skincolor, v.getzbuffer());
/*  32 */     screen.put(px + 1, py + 1, skincolor, v.getzbuffer());
/*  33 */     screen.put(px + 1, py, skincolor, v.getzbuffer());
/*  34 */     screen.put(px, py + 1, skincolor, v.getzbuffer());
/*  35 */     py += 2;
/*  36 */     int bodcolor = 10066346;
/*  37 */     screen.put(px, py, bodcolor, v.getzbuffer());
/*  38 */     screen.put(px + 1, py + 1, bodcolor, v.getzbuffer());
/*  39 */     screen.put(px + 1, py, bodcolor, v.getzbuffer());
/*  40 */     screen.put(px, py + 1, bodcolor, v.getzbuffer());
/*  41 */     screen.put(px, py + 2, bodcolor, v.getzbuffer());
/*  42 */     screen.put(px + 1, py + 2, bodcolor, v.getzbuffer());
/*  43 */     super.render(screen, level);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(Level level) {
/*  48 */     this.attacktime--;
/*  49 */     if (this.attacktime < 0) this.attacktime = 0; 
/*  50 */     this.targetEnemy = null;
/*  51 */     double len = 100.0D;
/*  52 */     if (this.targetEnemy == null) {
/*  53 */       for (int i = 0; i < level.mobs.size(); i++) {
/*  54 */         if (level.mobs.get(i) instanceof Enemy) {
/*  55 */           Enemy e = level.mobs.get(i);
/*  56 */           if (e.y < 16.0D) {
/*  57 */             double dx = this.x - e.x;
/*  58 */             double dy = this.y - e.y;
/*  59 */             double distance = Math.sqrt(dx * dx + dy * dy);
/*  60 */             if (distance < len) {
/*  61 */               len = distance;
/*  62 */               this.targetEnemy = e;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*  68 */     if (this.targetEnemy != null) {
/*  69 */       double dx = this.x - this.targetEnemy.x;
/*  70 */       double dy = this.y - this.targetEnemy.y;
/*  71 */       double rot = Math.atan2(dx, dy);
/*  72 */       double dist = Math.sqrt(dx * dx + dy * dy);
/*  73 */       this.vx = Math.sin(rot) * -this.walkspeed;
/*  74 */       this.vy = Math.cos(rot) * -this.walkspeed;
/*  75 */       if (dist < 1.0D && this.attacktime == 0) {
/*  76 */         this.targetEnemy.vx = Math.sin(rot) * 0.1D;
/*  77 */         this.targetEnemy.vy = Math.cos(rot) * 0.1D;
/*  78 */         this.targetEnemy.life--;
/*  79 */         this.attacktime = 15;
/*  80 */         for (int i = 0; i < 4; i++) {
/*  81 */           level.particles.add(new Bleeding(this.targetEnemy.x, this.targetEnemy.y, this.targetEnemy.z));
/*     */         }
/*     */       } 
/*     */     } 
/*  85 */     this.x += this.vx;
/*  86 */     this.y += this.vy;
/*  87 */     this.vx *= 0.9D;
/*  88 */     this.vy *= 0.9D;
/*  89 */     if (this.x < 0.0D)
/*  90 */       this.x = 0.0D; 
/*  91 */     if (this.x > 32.0D)
/*  92 */       this.x = 32.0D; 
/*  93 */     if (this.y < 0.0D)
/*  94 */       this.y = 0.0D; 
/*  95 */     if (this.y > 16.0D)
/*  96 */       this.y = 16.0D; 
/*  97 */     super.tick(level);
/*     */   }
/*     */ 
/*     */   
/*     */   public void collide(Mob mob) {
/* 102 */     if (mob != this && 
/* 103 */       mob instanceof Soldier) {
/* 104 */       double dx = mob.x - this.x;
/* 105 */       double dy = mob.y - this.y;
/* 106 */       double distance = Math.sqrt(dx * dx + dy * dy);
/* 107 */       if (distance < 0.5D && distance > 0.01D) {
/* 108 */         double refangle = Math.atan2(dx, dy);
/* 109 */         this.vx -= Math.sin(refangle) * this.walkspeed;
/* 110 */         this.vy -= Math.cos(refangle) * this.walkspeed;
/*     */       } 
/*     */     } 
/*     */     
/* 114 */     super.collide(mob);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(ArrayList<Entity> e) {
/* 120 */     super.remove(e);
/*     */   }
/*     */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\Soldier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */