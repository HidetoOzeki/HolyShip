/*     */ package jp.HidetoOzeki.TowerDefence.level;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import jp.HidetoOzeki.TowerDefence.Entity.EnemySoldier;
/*     */ import jp.HidetoOzeki.TowerDefence.Entity.Entity;
/*     */ import jp.HidetoOzeki.TowerDefence.Entity.Item;
/*     */ import jp.HidetoOzeki.TowerDefence.Entity.Mob;
/*     */ import jp.HidetoOzeki.TowerDefence.Entity.Particle;
/*     */ import jp.HidetoOzeki.TowerDefence.Entity.Ship;
/*     */ import jp.HidetoOzeki.TowerDefence.Entity.Worker;
/*     */ import jp.HidetoOzeki.TowerDefence.gfx.Font;
/*     */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*     */ import jp.HidetoOzeki.TowerDefence.level.Tiles.Tile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Level
/*     */ {
/*     */   public int w;
/*     */   public int d;
/*     */   public int h;
/*     */   public int[][][] data;
/*     */   double[][][] val;
/*  25 */   public ArrayList<Entity> entities = new ArrayList<>(); int tick; public double angle; public int xof; public int yof; int sec;
/*  26 */   public ArrayList<Particle> particles = new ArrayList<>();
/*  27 */   public ArrayList<Mob> mobs = new ArrayList<>();
/*  28 */   public ArrayList<Item> items = new ArrayList<>();
/*  29 */   public int gametime = 0;
/*  30 */   int phase = 0;
/*     */   boolean inphase = false;
/*  32 */   public int[] inventory = new int[32];
/*  33 */   int msec = 0;
/*  34 */   int shiptimer = 300;
/*  35 */   public int workerscount = 0;
/*  36 */   public ArrayList<Mob> workers = new ArrayList<>();
/*  37 */   public boolean[] collideMap = new boolean[1024];
/*  38 */   public int worker_pick_speed = 1;
/*  39 */   public int BaseLevel = 0;
/*  40 */   public int[] BaseLevelHealth = new int[] { 100, 500 };
/*     */   public boolean won = false;
/*     */   public boolean enemyApeared = false;
/*     */   
/*     */   public Level(int width, int depth, int height) {
/*  45 */     this.w = width;
/*  46 */     this.d = depth;
/*  47 */     this.h = height;
/*  48 */     this.data = new int[this.w][this.d][this.h];
/*  49 */     this.val = new double[this.w][this.d][this.h]; int i;
/*  50 */     for (i = 0; i < this.w; i++) {
/*  51 */       for (int j = 0; j < this.d; j++) {
/*  52 */         for (int k = this.h / 2; k < this.h; k++) {
/*  53 */           put(i, j, k / 2, Tile.water.id);
/*  54 */           int jj = j - k / 2;
/*  55 */           if (jj < 0)
/*  56 */             jj = 0; 
/*  57 */           if (jj > this.d)
/*  58 */             jj = this.d; 
/*  59 */           int kk = k / 2;
/*  60 */           if (kk > 13) {
/*  61 */             put(i, jj, kk, Tile.dirt.id);
/*  62 */             int ayy = (int)(Math.random() * 10.0D);
/*  63 */             if (ayy < 3) put(i, jj, kk - 1, Tile.dirt.id); 
/*  64 */             if (ayy == 1) put(i, jj, kk - 2, Tile.dirt.id); 
/*  65 */           } else if (kk > 3) {
/*  66 */             put(i, jj, kk, Tile.stone.id);
/*     */           } 
/*     */         } 
/*  69 */         int r = (int)(Math.random() * j);
/*  70 */         if (r == 0)
/*  71 */           if (j / 2 < 10) {
/*  72 */             put(i, j / 2, this.h / 2, Tile.tree.id);
/*     */           } else {
/*  74 */             double sr = Math.random() * 3.0D;
/*  75 */             if ((int)sr == 1) put(i, j / 2, this.h / 2, Tile.tree.id);
/*     */           
/*     */           }  
/*     */       } 
/*     */     } 
/*  80 */     for (i = 0; i < this.w; i++) {
/*  81 */       for (int j = 0; j < this.d; j++) {
/*  82 */         put(i, j / 2, this.h / 2 - 1, Tile.grass.id);
/*     */       }
/*     */     } 
/*  85 */     for (i = 300; i > 0; i--) {
/*  86 */       int xx = (int)(Math.random() * this.w);
/*  87 */       int yy = (int)(Math.random() * this.d);
/*  88 */       int r = (int)(Math.random() * yy);
/*  89 */       if (getTile(xx, yy, 15) == Tile.grass.id && 
/*  90 */         r == 0) {
/*  91 */         if (yy < 10) {
/*  92 */           put(xx, yy, 16, Tile.rock.id);
/*     */         } else {
/*  94 */           double sr = Math.random() * 5.0D;
/*  95 */           if ((int)sr == 1) put(xx, yy, 16, Tile.rock.id);
/*     */         
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 101 */     put(16, 8, 16, Tile.house.id);
/* 102 */     setValue(16, 8, 16, 100.0D);
/* 103 */     for (i = 0; i < 0; i++) {
/* 104 */       double xr = Math.random() * 8.0D - 4.0D;
/* 105 */       double yr = Math.random() * 8.0D - 4.0D;
/* 106 */       Worker w = new Worker(16.0D + xr, 8.0D + yr);
/* 107 */       w.itempath = 1;
/* 108 */       w.TargetID = Tile.tree.id;
/* 109 */       this.mobs.add(w);
/*     */     } 
/* 111 */     for (i = 0; i < 0; i++) {
/* 112 */       double xr = Math.random() * 8.0D - 4.0D;
/* 113 */       double yr = Math.random() * 8.0D - 4.0D;
/* 114 */       Worker w = new Worker(16.0D + xr, 8.0D + yr);
/* 115 */       w.itempath = 2;
/* 116 */       w.TargetID = Tile.rock.id;
/* 117 */       this.mobs.add(w);
/*     */     } 
/* 119 */     for (i = 0; i < 0; i++) {
/* 120 */       double xr = Math.random() * 8.0D - 4.0D;
/* 121 */       double yr = Math.random() * 8.0D - 4.0D;
/* 122 */       Worker w = new Worker(16.0D + xr, 8.0D + yr);
/* 123 */       w.itempath = 0;
/* 124 */       w.TargetID = Tile.wheat.id;
/* 125 */       this.mobs.add(w);
/*     */     } 
/* 127 */     for (i = 0; i < 0; i++) {
/* 128 */       double xr = Math.random() * 8.0D - 4.0D;
/* 129 */       double yr = Math.random() * 8.0D - 4.0D;
/* 130 */       Worker w = new Worker(16.0D + xr, 8.0D + yr);
/* 131 */       this.mobs.add(w);
/*     */     } 
/* 133 */     for (i = 0; i < 20; i++)
/*     */     {
/* 135 */       this.inventory[Items.wheat.id] = this.inventory[Items.wheat.id] + 1;
/*     */     }
/*     */     
/* 138 */     for (i = 0; i < 1024; i++) {
/* 139 */       this.collideMap[i] = true;
/*     */     }
/*     */   }
/*     */   
/*     */   public void put(int x, int y, int z, int id) {
/* 144 */     if (x > 0 && x < this.w && 
/* 145 */       y > 0 && y < this.d && 
/* 146 */       z > 0 && z < this.h) {
/* 147 */       this.data[x][y][z] = id;
/* 148 */       this.val[x][y][z] = Tile.val[id];
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void render(Screen screen, double angle, int xoffset, int yoffset) {
/* 155 */     screen.clear(4473992);
/* 156 */     screen.clearbuffer();
/* 157 */     this.angle = angle;
/* 158 */     this.xof = xoffset;
/* 159 */     this.yof = yoffset; int i;
/* 160 */     for (i = 0; i < this.w; i++) {
/* 161 */       for (int j = 0; j < this.d; j++) {
/* 162 */         for (int k = 0; k < this.h; k++) {
/* 163 */           boolean istr = (Tile.tiles[getTile(i, j, k + 1)]).transparency;
/* 164 */           int m = (istr ? 0 : 1) & ((getTile(i, j, k + 1) != 0) ? 1 : 0);
/* 165 */           boolean front = (getTile(i, j + 1, k) == 0);
/* 166 */           boolean back = (getTile(i, j - 1, k) == 0);
/* 167 */           boolean left = (getTile(i + 1, j, k) == 0);
/* 168 */           boolean right = (getTile(i - 1, j, k) == 0);
/* 169 */           boolean bottom = (getTile(i, j, k - 1) == 0);
/* 170 */           int n = ((m != 0) ? 0 : 1) | front | back | left | right | bottom;
/* 171 */           if (n != 0) {
/* 172 */             vec v = new vec((i * 8), (j * 8), (k * 8));
/* 173 */             v.rotate(angle, this.w * 8 / 2, this.d * 8 / 2);
/* 174 */             v.setpos(xoffset, yoffset);
/* 175 */             int tile = this.data[i][j][k];
/* 176 */             double value = this.val[i][j][k];
/* 177 */             int xtile = (v.getpos()).x;
/* 178 */             int ytile = (v.getpos()).y;
/* 179 */             if (tile == Tile.water.id) {
/* 180 */               ytile = (v.getpos()).y + (int)value;
/*     */             }
/*     */             
/* 183 */             if (tile != Tile.rock.id)
/* 184 */               Tile.tiles[tile].render(this, xtile, ytile, screen, v.getzbuffer()); 
/* 185 */             if (tile == Tile.rock.id)
/* 186 */               Tile.tiles[tile].render(i, j, k, this, xtile, ytile, screen, v.getzbuffer()); 
/* 187 */             if (tile == Tile.wheat.id)
/* 188 */               Tile.tiles[tile].render(i, j, k, this, xtile, ytile, screen, v.getzbuffer()); 
/* 189 */             if (tile == Tile.house.id) {
/*     */               
/* 191 */               int length = (int)getValue(16, 8, 16) / 10;
/* 192 */               int health = this.BaseLevelHealth[this.BaseLevel];
/*     */               
/* 194 */               for (int l = 0; l < length; l++) {
/* 195 */                 int px = xtile;
/* 196 */                 int py = ytile;
/* 197 */                 int yy = 18;
/* 198 */                 if (this.BaseLevel == 1) yy = 32; 
/* 199 */                 screen.put(-(health / 10) / 2 + px + l, py - yy, 16711680, -10000000);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 206 */     for (i = 0; i < this.entities.size(); i++) {
/* 207 */       ((Entity)this.entities.get(i)).render(screen, this);
/*     */     }
/* 209 */     for (i = 0; i < this.particles.size(); i++) {
/* 210 */       ((Particle)this.particles.get(i)).render(screen, this);
/*     */     }
/* 212 */     for (i = 0; i < this.mobs.size(); i++) {
/* 213 */       ((Mob)this.mobs.get(i)).render(screen, this);
/*     */     }
/* 215 */     for (i = 0; i < this.items.size(); i++) {
/* 216 */       ((Item)this.items.get(i)).render(screen, this);
/*     */     }
/* 218 */     renderGUI(screen);
/* 219 */     int fontY = 200;
/* 220 */     int fontX = 160;
/* 221 */     if (!this.inphase && this.gametime != 0) {
/* 222 */       int sec = (this.shiptimer * 60 - this.gametime) / 60;
/* 223 */       Font.drawFont(screen, "ship arrives in ", fontX, fontY, 16777215);
/* 224 */       Font.drawFont(screen, String.valueOf(sec / 60) + " minutes", fontX, fontY + 9, 16777215);
/* 225 */       Font.drawFont(screen, "and ", fontX, fontY + 17, 16777215);
/* 226 */       Font.drawFont(screen, String.valueOf(sec - 60 * sec / 60) + " seconds", fontX, fontY + 25, 16777215);
/* 227 */     } else if (this.gametime != 0) {
/* 228 */       Font.drawFont(screen, "ship arrived!", fontX, fontY, 16777215);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void renderGUI(Screen screen) {
/* 233 */     if (this.gametime != 0) {
/* 234 */       int i; for (i = 0; i < 64; i++) {
/* 235 */         for (int j = 0; j < 64; j++) {
/* 236 */           screen.put(280 + i / 2, 220 - j / 2 - 32, 4474026);
/* 237 */           screen.put(281 + i / 2, 220 - j / 2, 16777215);
/* 238 */           screen.put(280 + i / 2, 219 - j / 2, 4473924);
/*     */         } 
/*     */       } 
/* 241 */       for (i = 0; i < this.entities.size(); i++) {
/* 242 */         if (this.entities.get(i) instanceof Ship) {
/* 243 */           Ship s = (Ship)this.entities.get(i);
/* 244 */           screen.put(280 + (int)(s.x / 8.0D), 220 - (int)s.y / 8, 16711680);
/* 245 */           screen.put(280 + (int)(s.x / 8.0D), 220 - (int)s.y / 8, 16711680);
/*     */         } 
/*     */       } 
/* 248 */       for (i = 0; i < 3; i++) {
/* 249 */         Items.items[i].render(screen, 16, 16 + i * 17);
/* 250 */         Font.drawFont(screen, "x", 34, 20 + i * 17, 16777215);
/* 251 */         Font.drawFont(screen, this.inventory[i], 44, 20 + i * 17, 16777215);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   public void tick() {
/*     */     int i;
/* 257 */     for (i = 0; i < this.w; i++) {
/* 258 */       for (int j = 0; j < this.d; j++) {
/* 259 */         for (int k = 0; k < this.h; k++) {
/* 260 */           int tile = this.data[i][j][k];
/* 261 */           Tile.tiles[tile].tick(this, i, j, k);
/* 262 */           if (tile == Tile.farm.id) {
/* 263 */             for (int x = -2; x <= 2; x++) {
/* 264 */               for (int y = -2; y <= 2; y++) {
/* 265 */                 if ((((x != 0) ? 1 : 0) | ((y != 0) ? 1 : 0)) != 0) {
/* 266 */                   if (getTile(i + x, j + y, k - 1) != Tile.dirt.id)
/* 267 */                     put(i + x, j + y, k - 1, Tile.dirt.id); 
/* 268 */                   if (getTile(i + x, j + y, k) != Tile.wheat.id) {
/* 269 */                     put(i + x, j + y, k, Tile.wheat.id);
/* 270 */                     setValue(i + x, j + y, k, 3.0D);
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 279 */     for (i = 0; i < this.items.size(); i++) {
/* 280 */       ((Item)this.items.get(i)).tick(this);
/* 281 */       if (((Item)this.items.get(i)).life < 1)
/* 282 */         this.items.remove(i); 
/*     */     } 
/* 284 */     if (!this.inphase && this.gametime > 60 * this.shiptimer) {
/* 285 */       for (i = 0; i < 10; i++) {
/* 286 */         int rs = (int)(Math.random() * 2.0D);
/* 287 */         if (rs == 0) {
/* 288 */           int xr = 96 + (int)(Math.random() * 12.0D * 8.0D);
/* 289 */           int yr = (int)(Math.random() * 2.0D * 8.0D);
/* 290 */           this.entities.add(new Ship(xr, (this.d * 7 + yr + 60), (this.h / 2 * 8 + 3)));
/*     */         } 
/*     */       } 
/* 293 */       this.inphase = true;
/* 294 */       this.phase++;
/*     */     } 
/* 296 */     if (this.inphase && this.enemyApeared) {
/* 297 */       int count = 0;
/* 298 */       for (int j = 0; j < this.mobs.size(); j++) {
/* 299 */         if (this.mobs.get(j) instanceof EnemySoldier) {
/* 300 */           count++;
/*     */         }
/*     */       } 
/* 303 */       if (count < 1) {
/* 304 */         this.won = true;
/*     */       }
/*     */     } 
/* 307 */     for (i = 0; i < this.particles.size(); i++) {
/* 308 */       ((Particle)this.particles.get(i)).tick(this);
/* 309 */       if (((Particle)this.particles.get(i)).life < 1) {
/* 310 */         this.particles.remove(i);
/*     */       }
/*     */     } 
/* 313 */     this.workerscount = 0;
/* 314 */     for (i = 0; i < this.mobs.size(); i++) {
/* 315 */       for (int j = 0; j < this.mobs.size(); j++) {
/* 316 */         ((Mob)this.mobs.get(j)).collide(this.mobs.get(i));
/*     */       }
/* 318 */       if (this.mobs.get(i) instanceof Worker) {
/* 319 */         this.workerscount++;
/* 320 */         ((Worker)this.mobs.get(i)).hurtdmg = this.worker_pick_speed;
/*     */       } 
/* 322 */       if (this.mobs.get(i) instanceof EnemySoldier) {
/* 323 */         EnemySoldier e = (EnemySoldier)this.mobs.get(i);
/* 324 */         if (e.targetMob != null) {
/* 325 */           this.enemyApeared = true;
/*     */         }
/*     */       } 
/* 328 */       ((Mob)this.mobs.get(i)).tick(this);
/* 329 */       if (((Mob)this.mobs.get(i)).life < 1) {
/* 330 */         this.mobs.remove(i);
/*     */       }
/*     */     } 
/* 333 */     for (i = 0; i < this.entities.size(); i++) {
/* 334 */       ((Entity)this.entities.get(i)).tick(this);
/* 335 */       if (((Entity)this.entities.get(i)).life < 1) {
/* 336 */         this.entities.remove(i);
/*     */       }
/*     */     } 
/* 339 */     this.tick++;
/* 340 */     for (i = 0; i < this.w; i++) {
/* 341 */       for (int j = 0; j < this.d; j++) {
/* 342 */         int tt = this.data[i][j][16];
/* 343 */         this.collideMap[i + j * 32] = false;
/* 344 */         if (tt == Tile.rockwall.id) {
/* 345 */           this.collideMap[i + j * 32] = true;
/*     */         }
/* 347 */         for (int k = 0; k < this.h; k++) {
/* 348 */           double scale = 0.15D;
/* 349 */           int tile = this.data[i][j][k];
/* 350 */           if (tile == Tile.water.id) {
/* 351 */             this.val[i][j][k] = this.val[i][j][k] + Math.sin(this.tick * 0.1D + j) * scale;
/*     */           }
/* 353 */           if (tile == Tile.rockwall.id) {
/* 354 */             for (int a = 0; a < 8; a++) {
/* 355 */               this.data[i][j][16 + a] = Tile.rockwall.id;
/*     */             }
/*     */           }
/* 358 */           if (tile == Tile.house.id) {
/* 359 */             int dt = (int)getValue(i, j, k);
/* 360 */             if (dt < 1) {
/* 361 */               this.data[i][j][k] = Tile.air.id;
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 368 */     for (i = 0; i < this.w; i++) {
/* 369 */       for (int j = 0; j < this.d; j++) {
/* 370 */         int tt = getTile(i, j, 16);
/* 371 */         if (tt == Tile.wheat.id) {
/* 372 */           int rr = (int)(Math.random() * 600.0D);
/* 373 */           if (rr == 0)
/* 374 */             setValue(i, j, 16, getValue(i, j, 16) + 1.0D); 
/*     */         } 
/*     */       } 
/*     */     } 
/* 378 */     for (i = 0; i < this.workers.size(); i++) {
/* 379 */       this.workers.remove(i);
/*     */     }
/* 381 */     for (i = 0; i < this.mobs.size(); i++) {
/* 382 */       if (this.mobs.get(i) instanceof Worker) {
/* 383 */         this.workers.add(this.mobs.get(i));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public int getTile(int x, int y, int z) {
/* 389 */     if (x > 0 && x < this.w && 
/* 390 */       y > 0 && y < this.d && 
/* 391 */       z > 0 && z < this.h) {
/* 392 */       return this.data[x][y][z];
/*     */     }
/*     */ 
/*     */     
/* 396 */     return 0;
/*     */   }
/*     */   
/*     */   public double getValue(int x, int y, int z) {
/* 400 */     if (x > 0 && x < this.w && 
/* 401 */       y > 0 && y < this.d && 
/* 402 */       z > 0 && z < this.h) {
/* 403 */       return this.val[x][y][z];
/*     */     }
/*     */ 
/*     */     
/* 407 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public void setValue(int x, int y, int z, double v) {
/* 411 */     if (x > 0 && x < this.w && 
/* 412 */       y > 0 && y < this.d && 
/* 413 */       z > 0 && z < this.h)
/* 414 */       this.val[x][y][z] = v; 
/*     */   }
/*     */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\level\Level.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */