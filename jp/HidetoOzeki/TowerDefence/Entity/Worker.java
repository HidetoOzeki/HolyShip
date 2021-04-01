/*     */ package jp.HidetoOzeki.TowerDefence.Entity;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*     */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*     */ import jp.HidetoOzeki.TowerDefence.level.Tiles.Tile;
/*     */ import jp.HidetoOzeki.TowerDefence.level.vec;
/*     */ 
/*     */ public class Worker extends Mob {
/*     */   double rot;
/*  11 */   int wave = 0; double forward;
/*  12 */   int msec = 0;
/*     */   boolean hasTarget = false;
/*     */   boolean reachedTarget = false;
/*     */   int targetX;
/*     */   int targetY;
/*  17 */   int animation = 0;
/*     */   boolean foot = false;
/*  19 */   double steps = 0.0D;
/*  20 */   int hurttime = 0;
/*  21 */   int canceltime = 0; boolean rethink = false;
/*     */   boolean free = false;
/*     */   boolean hasItem = false;
/*     */   int itemid;
/*     */   int tileid;
/*     */   Item item;
/*  27 */   int clothcolor = 4473975;
/*  28 */   int baseX = 16; int baseY = 8;
/*  29 */   double walkspeed = 0.005D;
/*  30 */   public int hurtdmg = 1;
/*     */   
/*     */   public Worker(double x, double y) {
/*  33 */     super(x, y);
/*  34 */     this.life = 15;
/*     */   }
/*     */ 
/*     */   
/*     */   public void render(Screen screen, Level level) {
/*  39 */     if (this.foot) {
/*  40 */       this.animation = -1;
/*     */     } else {
/*  42 */       this.animation = 0;
/*     */     } 
/*  44 */     if (this.tileid == Tile.wheat.id) {
/*  45 */       this.clothcolor = 5583667;
/*     */     }
/*  47 */     if (this.tileid == Tile.rock.id) {
/*  48 */       this.clothcolor = 2236962;
/*     */     }
/*  50 */     if (this.tileid == Tile.tree.id) {
/*  51 */       this.clothcolor = 4473975;
/*     */     }
/*  53 */     vec v = new vec(this.x * 8.0D, this.y * 8.0D, this.z * 8.0D);
/*  54 */     v.rotate(level.angle, level.w * 8 / 2, level.d * 8 / 2);
/*  55 */     v.setpos(level.xof, level.yof);
/*  56 */     screen.put((v.getpos()).x, 2 + (v.getpos()).y - 1, 11171703, v.getzbuffer());
/*  57 */     screen.put((v.getpos()).x, 2 + (v.getpos()).y, this.clothcolor, v.getzbuffer());
/*  58 */     screen.put((v.getpos()).x, 2 + (v.getpos()).y + 1, this.clothcolor, v.getzbuffer());
/*  59 */     screen.put(this.animation + (v.getpos()).x, 2 + (v.getpos()).y + 2, 7816260, v.getzbuffer());
/*     */     
/*  61 */     screen.put((v.getpos()).x + 1, 2 + (v.getpos()).y - 1, 11171703, v.getzbuffer());
/*  62 */     screen.put((v.getpos()).x + 1, 2 + (v.getpos()).y, this.clothcolor, v.getzbuffer());
/*  63 */     screen.put((v.getpos()).x + 1, 2 + (v.getpos()).y + 1, this.clothcolor, v.getzbuffer());
/*  64 */     screen.put(this.animation + (v.getpos()).x + 2, 2 + (v.getpos()).y + 2, 7816260, v.getzbuffer());
/*  65 */     if (this.hasItem) {
/*  66 */       screen.put((v.getpos()).x - 1, (v.getpos()).y + 1, this.clothcolor, v.getzbuffer() - 4);
/*  67 */       screen.put((v.getpos()).x + 2, (v.getpos()).y + 1, this.clothcolor, v.getzbuffer() - 4);
/*  68 */       if (this.tileid == Tile.wheat.id) {
/*  69 */         screen.render((v.getpos()).x - 3, (v.getpos()).y - 5, 0, 9, v.getzbuffer() - 2);
/*     */       }
/*  71 */       if (this.tileid == Tile.rock.id) {
/*  72 */         screen.render((v.getpos()).x - 3, (v.getpos()).y - 5, 0, 3, v.getzbuffer() - 2);
/*     */       }
/*  74 */       if (this.tileid == Tile.tree.id) {
/*  75 */         screen.render((v.getpos()).x - 3, (v.getpos()).y - 5, 0, 8, v.getzbuffer() - 2);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void collide(Mob mob) {
/*  82 */     if (mob != this && 
/*  83 */       mob instanceof Worker) {
/*  84 */       double dx = mob.x - this.x;
/*  85 */       double dy = mob.y - this.y;
/*  86 */       double distance = Math.sqrt(dx * dx + dy * dy);
/*  87 */       if (distance < 0.5D && distance > 0.01D) {
/*  88 */         double refangle = Math.atan2(dx, dy);
/*  89 */         this.vx -= Math.sin(refangle) * this.walkspeed;
/*  90 */         this.vy -= Math.cos(refangle) * this.walkspeed;
/*     */       } 
/*     */     } 
/*     */     
/*  94 */     super.collide(mob);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(Level level) {
/*  99 */     if (!this.hasItem) {
/* 100 */       if (this.msec > 4) {
/* 101 */         this.wave++;
/* 102 */         this.msec = 0;
/*     */       } 
/* 104 */       if (this.wave > 32)
/* 105 */         this.wave = -1; 
/* 106 */       if (this.tileid != 0) {
/* 107 */         if (!this.hasTarget) {
/* 108 */           this.msec++;
/* 109 */           for (int i = -this.wave + 1; i < this.wave; i++) {
/* 110 */             for (int j = -this.wave + 1; j < this.wave; j++) {
/* 111 */               int tile = level.getTile((int)this.x + i, (int)this.y + j, 16);
/*     */ 
/*     */               
/* 114 */               if (tile == this.tileid) {
/* 115 */                 if (tile == Tile.wheat.id) {
/* 116 */                   double vv = level.getValue((int)this.x + i, (int)this.y + j, 16);
/* 117 */                   if (vv >= 9.0D) {
/*     */ 
/*     */                     
/* 120 */                     this.hasTarget = true;
/* 121 */                     this.wave = 0;
/* 122 */                     this.targetX = (int)(this.x + i);
/* 123 */                     this.targetY = (int)(this.y + j);
/*     */                   } 
/*     */                 } else {
/* 126 */                   this.hasTarget = true;
/* 127 */                   this.wave = 0;
/* 128 */                   this.targetX = (int)(this.x + i);
/* 129 */                   this.targetY = (int)(this.y + j);
/*     */                 } 
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/* 135 */         boolean TargetStillExist = true;
/* 136 */         if (this.hasTarget && !this.reachedTarget) {
/* 137 */           int tile = level.getTile(this.targetX, this.targetY, 16);
/* 138 */           if (tile != this.tileid) {
/* 139 */             this.hasTarget = false;
/* 140 */             TargetStillExist = false;
/*     */           } 
/*     */         } 
/*     */         
/* 144 */         if (TargetStillExist) {
/* 145 */           if (this.hasTarget && !this.reachedTarget) {
/* 146 */             double dx = this.targetX - this.x;
/* 147 */             double dy = this.targetY - this.y;
/* 148 */             this.rot = Math.atan2(dx, dy);
/* 149 */             this.forward = this.walkspeed;
/* 150 */             this.steps++;
/* 151 */             this.vx += Math.sin(this.rot) * this.forward;
/* 152 */             this.vy += Math.cos(this.rot) * this.forward;
/* 153 */             double dist = Math.sqrt(dx * dx + dy * dy);
/* 154 */             if (dist < 1.0D) {
/* 155 */               this.reachedTarget = true;
/*     */             }
/*     */           } 
/* 158 */           if (this.hasTarget) {
/* 159 */             double dx = this.targetX - this.x;
/* 160 */             double dy = this.targetY - this.y;
/* 161 */             double dist = Math.sqrt(dx * dx + dy * dy);
/* 162 */             if (dist > 2.0D) {
/* 163 */               this.reachedTarget = false;
/*     */             }
/*     */           } 
/* 166 */           if (this.reachedTarget) {
/* 167 */             int t = level.getTile(this.targetX, this.targetY, 16);
/* 168 */             if (t == this.tileid) {
/* 169 */               int particlecolor = 0;
/* 170 */               if (this.tileid == Tile.tree.id) particlecolor = 4465186; 
/* 171 */               if (this.tileid == Tile.wheat.id) particlecolor = 13421585; 
/* 172 */               if (this.tileid == Tile.rock.id) particlecolor = 11184810; 
/* 173 */               if (t != Tile.wheat.id && this.hurttime % 10 == 0) level.particles.add(new Breaking(this.targetX, this.targetY, 16.0D, particlecolor)); 
/*     */             } 
/* 175 */             this.hurttime += this.hurtdmg;
/* 176 */             if (this.hurttime > (Tile.tiles[this.tileid]).hp) {
/* 177 */               int tile = level.getTile(this.targetX, this.targetY, 16);
/* 178 */               if (tile == this.tileid) {
/* 179 */                 boolean pickable = true;
/* 180 */                 if (tile == Tile.wheat.id) {
/* 181 */                   double val = level.getValue(this.targetX, this.targetY, 16);
/* 182 */                   if (val < 9.0D) {
/* 183 */                     pickable = false;
/* 184 */                     this.hasTarget = false;
/*     */                   } 
/*     */                 } 
/* 187 */                 if (pickable) {
/* 188 */                   if (tile == Tile.wheat.id) {
/* 189 */                     level.put(this.targetX, this.targetY, 16, 0);
/*     */                   }
/* 191 */                   level.setValue(this.targetX, this.targetY, 16, level.getValue(this.targetX, this.targetY, 16) - 1.0D);
/* 192 */                   this.hasItem = true;
/* 193 */                   this.reachedTarget = false;
/*     */                 } 
/*     */               } else {
/* 196 */                 this.hasTarget = false;
/*     */               } 
/* 198 */               this.hurttime = 0;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 204 */       double dx = this.baseX - this.x;
/* 205 */       double dy = this.baseY - this.y;
/* 206 */       double dist = Math.sqrt(dx * dx + dy * dy);
/* 207 */       this.rot = Math.atan2(dx, dy);
/* 208 */       this.forward = this.walkspeed;
/* 209 */       this.steps++;
/* 210 */       this.vx += Math.sin(this.rot) * this.forward;
/* 211 */       this.vy += Math.cos(this.rot) * this.forward;
/* 212 */       if (dist < 1.0D) {
/* 213 */         level.inventory[this.itemid] = level.inventory[this.itemid] + 1;
/* 214 */         this.hasItem = false;
/* 215 */         this.hasTarget = false;
/*     */       } 
/*     */     } 
/* 218 */     if (this.steps > 10.0D) {
/* 219 */       this.foot = !this.foot;
/* 220 */       this.steps = 0.0D;
/*     */     } 
/* 222 */     this.x += this.vx;
/* 223 */     this.y += this.vy;
/* 224 */     if (this.x < 0.0D) this.x = 0.0D; 
/* 225 */     if (this.z < 0.0D) this.z = 0.0D; 
/* 226 */     if (this.y < 0.0D) this.y = 0.0D; 
/* 227 */     if (this.x > 32.0D) this.x = 32.0D; 
/* 228 */     if (this.y > 16.0D) this.y = 16.0D; 
/* 229 */     this.vx *= 0.9D;
/* 230 */     this.vy *= 0.9D;
/* 231 */     if (!this.hasItem && !this.hasTarget) {
/* 232 */       this.tileid = this.TargetID;
/* 233 */       this.itemid = this.itempath;
/*     */     } 
/* 235 */     super.tick(level);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(ArrayList<Entity> e) {
/* 241 */     super.remove(e);
/*     */   }
/*     */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\Worker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */