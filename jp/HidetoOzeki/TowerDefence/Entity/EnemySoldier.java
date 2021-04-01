/*     */ package jp.HidetoOzeki.TowerDefence.Entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*     */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*     */ import jp.HidetoOzeki.TowerDefence.level.Tiles.Tile;
/*     */ import jp.HidetoOzeki.TowerDefence.level.vec;
/*     */ 
/*     */ public class EnemySoldier
/*     */   extends Enemy {
/*  11 */   double angle = 0.0D;
/*  12 */   double rota = 0.0D;
/*  13 */   int msec = 0;
/*  14 */   public Mob targetMob = null;
/*  15 */   int attacktime = 0;
/*     */   
/*     */   public EnemySoldier(double x, double y) {
/*  18 */     super(x, y);
/*  19 */     this.z = 16.0D;
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
/*  36 */     int bodcolor = 10031377;
/*  37 */     screen.put(px, py, bodcolor, v.getzbuffer());
/*  38 */     screen.put(px + 1, py + 1, bodcolor, v.getzbuffer());
/*  39 */     screen.put(px + 1, py, bodcolor, v.getzbuffer());
/*  40 */     screen.put(px, py + 1, bodcolor, v.getzbuffer());
/*  41 */     screen.put(px, py + 2, bodcolor, v.getzbuffer());
/*  42 */     screen.put(px + 1, py + 2, bodcolor, v.getzbuffer());
/*     */     
/*  44 */     super.render(screen, level);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(Level level) {
/*  49 */     this.targetMob = null;
/*  50 */     this.attacktime--;
/*  51 */     if (this.attacktime < 0)
/*  52 */       this.attacktime = 0; 
/*  53 */     this.msec++;
/*  54 */     if (this.msec > 60) {
/*  55 */       this.rota = Math.random() * 0.1D - 0.05D;
/*  56 */       this.msec = 0;
/*     */     } 
/*  58 */     if (this.boat.land) {
/*  59 */       double len = 100.0D;
/*  60 */       for (int i = 0; i < level.mobs.size(); i++) {
/*  61 */         if (!(level.mobs.get(i) instanceof EnemySoldier)) {
/*  62 */           double dx = this.x - ((Mob)level.mobs.get(i)).x;
/*  63 */           double dy = this.y - ((Mob)level.mobs.get(i)).y;
/*  64 */           double dist = Math.sqrt(dx * dx + dy * dy);
/*  65 */           if (len > dist) {
/*  66 */             len = dist;
/*  67 */             this.targetMob = level.mobs.get(i);
/*     */           } 
/*     */         } 
/*     */       } 
/*  71 */       if (this.targetMob != null) {
/*  72 */         double dx = this.x - this.targetMob.x;
/*  73 */         double dy = this.y - this.targetMob.y;
/*  74 */         this.angle = Math.atan2(dx, dy);
/*  75 */         double dist = Math.sqrt(dx * dx + dy * dy);
/*  76 */         this.vx = Math.sin(this.angle) * -this.walkspeed;
/*  77 */         this.vy = Math.cos(this.angle) * -this.walkspeed;
/*  78 */         if (dist < 1.0D && this.attacktime == 0) {
/*  79 */           this.targetMob.vx = Math.sin(this.angle) * -0.2D;
/*  80 */           this.targetMob.vy = Math.cos(this.angle) * -0.2D;
/*  81 */           this.targetMob.life--;
/*  82 */           this.attacktime = 10;
/*  83 */           for (int j = 0; j < 4; j++) {
/*  84 */             level.particles.add(new Bleeding(this.targetMob.x, this.targetMob.y, this.targetMob.z));
/*     */           }
/*     */         } 
/*     */       } 
/*  88 */       if (this.targetMob == null) {
/*  89 */         int bx = 16;
/*  90 */         int bz = 8;
/*  91 */         double dx = this.x - bx;
/*  92 */         double dy = this.y - bz;
/*  93 */         double rr = Math.atan2(dx, dy);
/*  94 */         double dist = Math.sqrt(dx * dx + dy * dy);
/*  95 */         this.vx = Math.sin(rr) * -this.walkspeed;
/*  96 */         this.vy = Math.cos(rr) * -this.walkspeed;
/*  97 */         int tile = level.getTile(16, 8, 16);
/*  98 */         if (dist < 1.0D && this.attacktime == 0 && tile == Tile.house.id) {
/*  99 */           level.setValue(16, 8, 16, level.getValue(16, 8, 16) - 1.0D);
/* 100 */           level.particles.add(new BaseBreaking(16.0D, 8.0D, 16.0D));
/* 101 */           this.attacktime = 60;
/*     */         } 
/*     */       } 
/* 104 */       if (this.x < 0.0D)
/* 105 */         this.x = 0.0D; 
/* 106 */       if (this.x > 32.0D)
/* 107 */         this.x = 32.0D; 
/* 108 */       if (this.y > 16.0D)
/* 109 */         this.y = 16.0D; 
/* 110 */       this.z = 16.0D;
/*     */     } 
/* 112 */     this.angle += this.rota;
/*     */     
/* 114 */     double tx = 0.0D;
/* 115 */     double ty = 0.0D;
/* 116 */     tx = this.x + this.vx;
/* 117 */     ty = this.y + this.vy;
/* 118 */     if (tx > 0.0D && tx < 32.0D && 
/* 119 */       ty > 0.0D && ty < 32.0D && 
/* 120 */       level.collideMap[(int)tx + (int)ty * 32]) {
/* 121 */       this.vx = 0.0D;
/* 122 */       this.vy = 0.0D;
/*     */     } 
/*     */ 
/*     */     
/* 126 */     super.tick(level);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void collide(Mob mob) {
/* 132 */     super.collide(mob);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(ArrayList<Entity> e) {
/* 138 */     super.remove(e);
/*     */   }
/*     */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\EnemySoldier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */