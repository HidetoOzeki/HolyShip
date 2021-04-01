/*     */ package jp.HidetoOzeki.TowerDefence;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.image.BufferStrategy;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.event.MouseInputAdapter;
/*     */ import jp.HidetoOzeki.TowerDefence.Entity.Archer;
/*     */ import jp.HidetoOzeki.TowerDefence.Entity.Mob;
/*     */ import jp.HidetoOzeki.TowerDefence.Entity.Soldier;
/*     */ import jp.HidetoOzeki.TowerDefence.Entity.Worker;
/*     */ import jp.HidetoOzeki.TowerDefence.gfx.Font;
/*     */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*     */ import jp.HidetoOzeki.TowerDefence.gfx.SelectBox;
/*     */ import jp.HidetoOzeki.TowerDefence.level.Items;
/*     */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*     */ import jp.HidetoOzeki.TowerDefence.level.Recipes;
/*     */ import jp.HidetoOzeki.TowerDefence.level.Tiles.Tile;
/*     */ import jp.HidetoOzeki.TowerDefence.level.vec;
/*     */ 
/*     */ public class Game
/*     */   implements Runnable
/*     */ {
/*     */   public static final int width = 320;
/*     */   public static final int height = 240;
/*     */   public static final int scale = 2;
/*     */   JFrame window;
/*  32 */   BufferedImage image = new BufferedImage(320, 240, 1);
/*  33 */   int[] pixels = ((DataBufferInt)this.image.getRaster().getDataBuffer()).getData();
/*     */   BufferStrategy str;
/*     */   Screen screen;
/*     */   double angle;
/*     */   public static Key key;
/*     */   boolean zview = false;
/*  39 */   Level level = new Level(32, 32, 32);
/*     */   
/*     */   int xoffset;
/*     */   int yoffset;
/*  43 */   Mouse m = new Mouse(); boolean started = false; boolean mpressed = false;
/*     */   boolean swap = false;
/*  45 */   int msec = 0;
/*  46 */   SelectBox[] sb = new SelectBox[10];
/*  47 */   SelectBox[] gui = new SelectBox[10];
/*  48 */   Recipes[] BuildCost = new Recipes[10];
/*  49 */   Recipes[] TrainCost = new Recipes[10];
/*  50 */   Recipes[] WorkerUpgradeCost = new Recipes[10];
/*  51 */   int[] targetIDs = new int[3];
/*  52 */   int[] ItemIDs = new int[3];
/*  53 */   int[] RecipeTiles = new int[10];
/*     */   boolean showmenu = false;
/*  55 */   int tileid = 0;
/*     */   boolean placing = false;
/*     */   boolean choosing = false;
/*     */   boolean setting = false;
/*  59 */   double placeX = 5.0D;
/*  60 */   double placeY = 8.0D;
/*  61 */   int select = 0;
/*     */   
/*  63 */   int titleanim = 0;
/*  64 */   int mult = 0;
/*     */   int noise;
/*  66 */   int noisescale = 130;
/*     */   boolean tail = false;
/*     */   boolean decreasenoise = false;
/*  69 */   int introtime = 2;
/*  70 */   public static History history = new History();
/*  71 */   int histTime = 0;
/*     */   int buildId;
/*  73 */   String[] tms = new String[] { "start", "tutorial", "about", "quit" };
/*  74 */   SelectBox titlemenu = new SelectBox(this.tms, 128, 168, 10, 6, "title menu");
/*  75 */   int TITLESTATE = 0;
/*  76 */   final int TITLEMENU = 0;
/*  77 */   final int TUTMENU = 1;
/*  78 */   final int ABOUTMENU = 2;
/*     */   boolean showtitlemenu = false;
/*  80 */   int currentMenu = 0;
/*     */   boolean gameover = false;
/*     */   boolean gameclear = false;
/*     */   
/*     */   public static void main(String[] args) {
/*  85 */     Game game = new Game();
/*     */   }
/*     */   
/*     */   public Game() {
/*  89 */     this.window = new JFrame("Holy Ship!!");
/*  90 */     this.window.setDefaultCloseOperation(3);
/*  91 */     this.window.setSize(640, 480);
/*  92 */     this.window.setVisible(true);
/*  93 */     this.window.setResizable(false);
/*  94 */     this.window.createBufferStrategy(2);
/*  95 */     this.window.setLocationRelativeTo((Component)null);
/*  96 */     key = new Key();
/*  97 */     this.window.addKeyListener(key);
/*  98 */     this.window.addFocusListener(key);
/*  99 */     this.window.addMouseMotionListener(key);
/* 100 */     this.window.addMouseListener(key);
/* 101 */     this.str = this.window.getBufferStrategy();
/* 102 */     this.window.addMouseMotionListener(this.m);
/* 103 */     this.window.addMouseListener(this.m);
/* 104 */     this.angle = 0.0D;
/* 105 */     this.xoffset = 160;
/* 106 */     this.yoffset = 360;
/* 107 */     Recipes farmrecipe = new Recipes();
/* 108 */     farmrecipe.setCost(Items.rock.id, 5);
/* 109 */     farmrecipe.setCost(Items.wood.id, 5);
/* 110 */     Recipes barracks = new Recipes();
/* 111 */     barracks.setCost(Items.rock.id, 10);
/* 112 */     barracks.setCost(Items.wood.id, 10);
/* 113 */     barracks.setCost(Items.wheat.id, 5);
/* 114 */     Recipes archerTower = new Recipes();
/* 115 */     archerTower.setCost(Items.rock.id, 25);
/* 116 */     archerTower.setCost(Items.wood.id, 10);
/* 117 */     Recipes UpgradeSpeedCost = new Recipes();
/* 118 */     UpgradeSpeedCost.setCost(Items.wheat.id, 2);
/* 119 */     this.WorkerUpgradeCost[0] = UpgradeSpeedCost;
/* 120 */     this.BuildCost[0] = farmrecipe;
/* 121 */     this.BuildCost[1] = barracks;
/* 122 */     this.BuildCost[2] = archerTower;
/* 123 */     this.RecipeTiles[0] = Tile.farm.id;
/* 124 */     this.RecipeTiles[1] = Tile.barracks.id;
/* 125 */     this.RecipeTiles[2] = Tile.rockwall.id;
/* 126 */     this.targetIDs[0] = Tile.rock.id;
/* 127 */     this.targetIDs[1] = Tile.tree.id;
/* 128 */     this.targetIDs[2] = Tile.wheat.id;
/* 129 */     this.ItemIDs[0] = Items.rock.id;
/* 130 */     this.ItemIDs[1] = Items.wood.id;
/* 131 */     this.ItemIDs[2] = Items.wheat.id;
/* 132 */     Recipes trainWorker = new Recipes();
/* 133 */     trainWorker.setCost(Items.wheat.id, 5);
/* 134 */     Recipes trainSoldier = new Recipes();
/* 135 */     trainSoldier.setCost(Items.wheat.id, 15);
/* 136 */     trainSoldier.setCost(Items.rock.id, 5);
/* 137 */     trainSoldier.setCost(Items.wood.id, 5);
/* 138 */     Recipes trainArcher = new Recipes();
/* 139 */     trainArcher.setCost(Items.wheat.id, 10);
/* 140 */     trainArcher.setCost(Items.rock.id, 10);
/* 141 */     trainArcher.setCost(Items.wood.id, 10);
/* 142 */     this.TrainCost[0] = trainWorker;
/* 143 */     this.TrainCost[1] = trainSoldier;
/* 144 */     this.TrainCost[2] = trainArcher;
/* 145 */     run();
/*     */   }
/*     */   
/*     */   public void render() {
/* 149 */     Graphics g = this.str.getDrawGraphics();
/* 150 */     this.level.render(this.screen, this.angle, this.xoffset, this.yoffset);
/* 151 */     if (!this.started) {
/* 152 */       int y0 = -32 + this.mult;
/* 153 */       int y1 = y0 + this.titleanim * 1 + 10;
/* 154 */       if (y0 > 0)
/* 155 */         y0 = 0; 
/* 156 */       if (y1 > 10)
/* 157 */         y1 = 10; 
/* 158 */       if (y1 == 10) {
/* 159 */         this.tail = true;
/* 160 */         this.noise = 0;
/* 161 */         if (y0 == 0 && 
/* 162 */           this.titleanim < 60 * this.introtime) {
/* 163 */           this.noise = (int)(Math.random() * this.noisescale);
/* 164 */           this.decreasenoise = true;
/*     */         } 
/*     */       } 
/*     */       
/* 168 */       for (int yy = y0; yy < y1; yy++) {
/* 169 */         int cc = 256 + yy * 7;
/* 170 */         if (this.titleanim > 60 * this.introtime)
/* 171 */           cc = 256 + (yy - y1) * 20; 
/* 172 */         if (cc > 255)
/* 173 */           cc = 255; 
/* 174 */         if (cc < 0)
/* 175 */           cc = 0; 
/* 176 */         int tcolor = (new Color(cc, cc, cc)).getRGB();
/* 177 */         for (int i = 0; i < 16; i++) {
/* 178 */           for (int j = 0; j < 13; j++) {
/* 179 */             this.screen.mountColor(tcolor);
/* 180 */             this.screen.render(-yy + 106 + i * 8, yy + 24 + j * 8, 4 + i, j);
/*     */           } 
/*     */         } 
/*     */       } 
/* 184 */       this.screen.mountColor(0);
/* 185 */       if (this.titleanim > 60 * this.introtime) {
/* 186 */         int i; for (i = 0; i < 16; i++) {
/* 187 */           for (int j = 13; j < 16; j++) {
/* 188 */             this.screen.render(98 + i * 8, 24 + j * 8, 4 + i, j);
/*     */           }
/*     */         } 
/* 191 */         this.showtitlemenu = true;
/* 192 */         this.titlemenu.renderBox(this.screen);
/* 193 */         this.titlemenu.render(this.screen);
/* 194 */         for (i = 0; i < 10; i++) {
/* 195 */           for (int j = 0; j < 14; j++) {
/* 196 */             this.screen.render(224 + i * 8, 64 + j * 8, 13 + i, 20 + j, -1000);
/*     */           }
/*     */         } 
/*     */       } 
/* 200 */       if (this.titleanim < 60 * this.introtime) {
/* 201 */         for (int i = 0; i < 10; i++) {
/* 202 */           for (int j = 0; j < 14; j++) {
/* 203 */             this.screen.render(116 + i * 8, 128 + j * 8, 13 + i, 20 + j, -1000);
/*     */           }
/*     */         } 
/*     */       }
/*     */     } else {
/* 208 */       this.showtitlemenu = false;
/* 209 */       if (this.showmenu) {
/* 210 */         for (int j = 0; j < this.sb.length; j++) {
/* 211 */           if (this.sb[j] != null) {
/* 212 */             this.sb[j].renderBox(this.screen);
/* 213 */             this.sb[j].render(this.screen);
/*     */           } 
/*     */         } 
/*     */       }
/* 217 */       for (int i = 0; i < this.gui.length; i++) {
/* 218 */         if (this.gui[i] != null) {
/* 219 */           this.gui[i].renderBox(this.screen);
/* 220 */           this.gui[i].renderGUI(this.screen);
/*     */         } 
/*     */       } 
/* 223 */       this.showmenu = true;
/* 224 */       if (this.placing) {
/* 225 */         this.angle = 1.57D;
/* 226 */         vec v = new vec(((int)this.placeX * 8), ((int)this.placeY * 8), 136.0D);
/* 227 */         v.rotate(this.level.angle, this.level.w * 8 / 2, this.level.d * 8 / 2);
/* 228 */         v.setpos(this.level.xof, this.level.yof);
/* 229 */         this.screen.mountColor(16711680);
/* 230 */         this.screen.render((v.getpos()).x - 4, (v.getpos()).y - 4, 2, 9);
/* 231 */         this.screen.mountColor(0);
/* 232 */         String placeOrder = "PRESS ENTER TO PLACE";
/* 233 */         Font.drawFont(this.screen, placeOrder, 151, 17, 7829367);
/* 234 */         Font.drawFont(this.screen, placeOrder, 150, 16, 16777215);
/* 235 */         String putOrder = "PRESS SHIFT TO SELECT";
/* 236 */         Font.drawFont(this.screen, putOrder, 151, 25, 7829367);
/* 237 */         Font.drawFont(this.screen, putOrder, 150, 24, 16777215);
/* 238 */         String howtodone = "PRESS ESC TO DONE";
/* 239 */         Font.drawFont(this.screen, howtodone, 151, 33, 7829367);
/* 240 */         Font.drawFont(this.screen, howtodone, 150, 32, 16777215);
/* 241 */         int icx = 26;
/* 242 */         int icy = 192;
/* 243 */         this.screen.render(icx, icy, 5, 16, -10000);
/* 244 */         this.screen.render(icx + 8, icy, 6, 16, -10000);
/* 245 */         this.screen.render(icx + 8, icy + 8, 6, 17, -10000);
/* 246 */         this.screen.render(icx, icy + 8, 5, 17, -10000);
/* 247 */         icx += 16;
/* 248 */         icy += 16;
/* 249 */         this.screen.render(icx, icy, 7, 16, -10000);
/* 250 */         this.screen.render(icx + 8, icy, 8, 16, -10000);
/* 251 */         this.screen.render(icx + 8, icy + 8, 8, 17, -10000);
/* 252 */         this.screen.render(icx, icy + 8, 7, 17, -10000);
/* 253 */         icx -= 16;
/* 254 */         this.screen.render(icx, icy, 9, 16, -10000);
/* 255 */         this.screen.render(icx + 8, icy, 10, 16, -10000);
/* 256 */         this.screen.render(icx + 8, icy + 8, 10, 17, -10000);
/* 257 */         this.screen.render(icx, icy + 8, 9, 17, -10000);
/* 258 */         icx -= 16;
/* 259 */         this.screen.render(icx, icy, 11, 16, -10000);
/* 260 */         this.screen.render(icx + 8, icy, 12, 16, -10000);
/* 261 */         this.screen.render(icx + 8, icy + 8, 12, 17, -10000);
/* 262 */         this.screen.render(icx, icy + 8, 11, 17, -10000);
/*     */       } 
/* 264 */       if (this.sb[0] == null && !this.placing && !this.choosing) {
/* 265 */         int icx = 26;
/* 266 */         int icy = 192;
/* 267 */         this.screen.render(icx, icy, 5, 18, -10000);
/* 268 */         this.screen.render(icx + 8, icy, 6, 18, -10000);
/* 269 */         this.screen.render(icx + 8, icy + 8, 6, 19, -10000);
/* 270 */         this.screen.render(icx, icy + 8, 5, 19, -10000);
/* 271 */         icx += 48;
/* 272 */         this.screen.render(icx, icy, 7, 18, -10000);
/* 273 */         this.screen.render(icx + 8, icy, 8, 18, -10000);
/* 274 */         this.screen.render(icx + 8, icy + 8, 8, 19, -10000);
/* 275 */         this.screen.render(icx, icy + 8, 7, 19, -10000);
/* 276 */         this.screen.render(144, 11, 2, 14, -1000000);
/* 277 */         this.screen.render(144, 19, 2, 15, -1000000);
/* 278 */         this.screen.render(152, 11, 3, 14, -1000000);
/* 279 */         this.screen.render(152, 19, 3, 15, -1000000);
/* 280 */         Font.drawFont(this.screen, "OPEN/CLOSE MENU", 161, 17, 7829367);
/* 281 */         Font.drawFont(this.screen, "OPEN/CLOSE MENU", 160, 16, 16777215);
/*     */       } 
/* 283 */       int xx = 72;
/* 284 */       int yy = 0;
/* 285 */       this.screen.ShowHistory(history, xx, yy);
/*     */     } 
/* 287 */     if ((((this.level.getValue(16, 8, 16) < 1.0D) ? 1 : 0) | ((this.level.data[16][8][16] != Tile.house.id) ? 1 : 0)) != 0) {
/* 288 */       this.gameover = true;
/*     */     }
/* 290 */     if (this.gameover) {
/* 291 */       DrawGO();
/*     */     }
/* 293 */     if (this.gameclear) {
/* 294 */       DrawGC();
/*     */     }
/* 296 */     this.screen.draw(this.pixels);
/* 297 */     g.drawImage(this.image, 0, 0, 640, 480, null);
/* 298 */     g.dispose();
/* 299 */     this.str.show();
/*     */   }
/*     */   
/*     */   public void DrawGO() {
/* 303 */     for (int i = 0; i < 25; i++) {
/* 304 */       for (int j = 0; j < 16; j++)
/* 305 */         this.screen.render(86 + i * 8, 64 + j * 8, 24 + i, 1 + j, -1000); 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void DrawGC() {
/* 310 */     for (int i = 0; i < 25; i++) {
/* 311 */       for (int j = 0; j < 16; j++) {
/* 312 */         this.screen.render(70 + i * 8, 64 + j * 8, 24 + i, 18 + j, -1000);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public void tick() {
/* 318 */     if (this.gameover && (
/* 319 */       key.keys[10] | key.keys[27]) != 0) {
/* 320 */       this.showtitlemenu = true;
/* 321 */       this.started = false;
/* 322 */       this.gameover = false;
/* 323 */       this.level = new Level(32, 32, 32);
/* 324 */       key.keys[10] = false;
/* 325 */       key.keys[27] = false;
/*     */     } 
/*     */     
/* 328 */     if (this.gameclear && (
/* 329 */       key.keys[10] | key.keys[27]) != 0) {
/* 330 */       this.showtitlemenu = true;
/* 331 */       this.started = false;
/* 332 */       this.gameover = false;
/* 333 */       this.gameclear = false;
/* 334 */       this.level = new Level(32, 32, 32);
/* 335 */       key.keys[10] = false;
/* 336 */       key.keys[27] = false;
/*     */     } 
/*     */     
/* 339 */     if (this.showtitlemenu) {
/* 340 */       this.level.gametime = 0;
/* 341 */       this.titlemenu.tick();
/* 342 */       if (key.keys[40])
/* 343 */         this.titlemenu.select++; 
/* 344 */       if (key.keys[38])
/* 345 */         this.titlemenu.select--; 
/* 346 */       key.keys[38] = false;
/* 347 */       key.keys[40] = false;
/*     */     } 
/* 349 */     this.histTime++;
/* 350 */     if (this.histTime > 60) {
/* 351 */       history.add("");
/* 352 */       this.histTime = 0;
/*     */     } 
/* 354 */     this.titleanim++;
/* 355 */     if (this.tail)
/* 356 */       this.mult++; 
/* 357 */     if (this.decreasenoise)
/* 358 */       this.noisescale = (int)(this.noisescale * 0.99D); 
/* 359 */     for (int i = 0; i < this.gui.length; i++) {
/* 360 */       if (this.gui[i] != null) {
/* 361 */         this.gui[i].tick();
/*     */       }
/*     */     } 
/* 364 */     if (this.started) {
/* 365 */       this.gameclear = this.level.won;
/* 366 */       if (this.level.worker_pick_speed == 2) {
/* 367 */         (this.WorkerUpgradeCost[0]).cost[Items.wheat.id] = 4;
/*     */       }
/* 369 */       if (this.level.worker_pick_speed == 3) {
/* 370 */         (this.WorkerUpgradeCost[0]).cost[Items.wheat.id] = 4;
/* 371 */         (this.WorkerUpgradeCost[0]).cost[Items.rock.id] = 1;
/* 372 */         (this.WorkerUpgradeCost[0]).cost[Items.wood.id] = 1;
/*     */       } 
/* 374 */       int activemenu = 0;
/* 375 */       for (int j = 0; j < this.sb.length; j++) {
/* 376 */         if (this.sb[j] != null && 
/* 377 */           activemenu < j) {
/* 378 */           activemenu = j;
/*     */         }
/*     */       } 
/* 381 */       this.currentMenu = activemenu;
/* 382 */       if (this.placing) {
/* 383 */         double speed = 1.0D;
/* 384 */         if (key.keys[38])
/* 385 */           this.placeY += speed; 
/* 386 */         if (key.keys[40])
/* 387 */           this.placeY -= speed; 
/* 388 */         if (key.keys[39])
/* 389 */           this.placeX += speed; 
/* 390 */         if (key.keys[37])
/* 391 */           this.placeX -= speed; 
/* 392 */         key.keys[38] = false;
/* 393 */         key.keys[40] = false;
/* 394 */         key.keys[39] = false;
/* 395 */         key.keys[37] = false;
/* 396 */         boolean rock = (this.level.inventory[Items.rock.id] >= (this.BuildCost[this.buildId]).cost[Items.rock.id]);
/* 397 */         boolean wood = (this.level.inventory[Items.wood.id] >= (this.BuildCost[this.buildId]).cost[Items.wood.id]);
/* 398 */         boolean wheat = (this.level.inventory[Items.wheat.id] >= (this.BuildCost[this.buildId]).cost[Items.wheat.id]);
/* 399 */         if (rock && wood && wheat) {
/* 400 */           if (key.keys[10] && 
/* 401 */             this.level.getTile((int)this.placeX, (int)this.placeY, 16) == 0) {
/* 402 */             this.placing = false;
/* 403 */             this.level.put((int)this.placeX, (int)this.placeY, 16, this.tileid);
/* 404 */             history.add("you built a " + (Tile.tiles[this.tileid]).name);
/* 405 */             this.level.inventory[Items.rock.id] = this.level.inventory[Items.rock.id] - (this.BuildCost[this.buildId]).cost[Items.rock.id];
/* 406 */             this.level.inventory[Items.wood.id] = this.level.inventory[Items.wood.id] - (this.BuildCost[this.buildId]).cost[Items.wood.id];
/* 407 */             this.level.inventory[Items.wheat.id] = this.level.inventory[Items.wheat.id] - (this.BuildCost[this.buildId]).cost[Items.wheat.id];
/* 408 */             openMenu();
/*     */           } 
/*     */           
/* 411 */           if (key.keys[16] && 
/* 412 */             this.level.getTile((int)this.placeX, (int)this.placeY, 16) == 0) {
/* 413 */             this.level.put((int)this.placeX, (int)this.placeY, 16, this.tileid);
/* 414 */             history.add("you put a " + (Tile.tiles[this.tileid]).name);
/* 415 */             this.level.inventory[Items.rock.id] = this.level.inventory[Items.rock.id] - (this.BuildCost[this.buildId]).cost[Items.rock.id];
/* 416 */             this.level.inventory[Items.wood.id] = this.level.inventory[Items.wood.id] - (this.BuildCost[this.buildId]).cost[Items.wood.id];
/* 417 */             this.level.inventory[Items.wheat.id] = this.level.inventory[Items.wheat.id] - (this.BuildCost[this.buildId]).cost[Items.wheat.id];
/*     */           } 
/*     */           
/* 420 */           if (key.keys[27]) {
/* 421 */             this.placing = false;
/* 422 */             openMenu();
/*     */           } 
/*     */         } else {
/* 425 */           history.add("You dont have enough items");
/* 426 */           this.placing = false;
/*     */         } 
/* 428 */         key.keys[10] = false;
/*     */       } 
/* 430 */       boolean shouldcloseMenu = false;
/* 431 */       if (this.choosing) {
/* 432 */         boolean done = false;
/* 433 */         boolean escaped = false;
/* 434 */         if (this.setting && 
/* 435 */           key.keys[10]) {
/* 436 */           done = true;
/*     */         }
/*     */         
/* 439 */         int max = this.level.workers.size();
/* 440 */         if (!this.setting) {
/* 441 */           if (key.keys[38])
/* 442 */             this.select++; 
/* 443 */           if (key.keys[40])
/* 444 */             this.select--; 
/* 445 */           if (key.keys[39])
/* 446 */             this.select++; 
/* 447 */           if (key.keys[37])
/* 448 */             this.select--; 
/*     */         } 
/* 450 */         if (key.keys[27]) {
/* 451 */           done = true;
/* 452 */           escaped = true;
/*     */         } 
/* 454 */         if (this.select + 1 > max)
/* 455 */           this.select = 0; 
/* 456 */         if (this.select < 0)
/* 457 */           this.select = max - 1; 
/* 458 */         Mob mob = this.level.workers.get(this.select);
/* 459 */         vec v = new vec(((int)mob.x * 8), ((int)mob.y * 8), 168.0D);
/* 460 */         v.rotate(this.level.angle, this.level.w * 8 / 2, this.level.d * 8 / 2);
/* 461 */         v.setpos(this.level.xof, this.level.yof);
/* 462 */         String s = (Tile.tiles[mob.TargetID]).name;
/* 463 */         if (s == "Air") {
/* 464 */           s = "NOTASK";
/*     */         }
/* 466 */         String[] ss = { s };
/* 467 */         this.gui[3] = new SelectBox(ss, (v.getpos()).x - 12, (v.getpos()).y, 4, 2, "Target");
/* 468 */         if (!this.setting && key.keys[10]) {
/* 469 */           String[] targets = { "Rock", "Wood", "Wheat" };
/* 470 */           this.sb[3] = new SelectBox(targets, 192, 32, 12, 7, "Select Target");
/* 471 */           this.setting = true;
/*     */         } 
/* 473 */         key.keys[10] = false;
/* 474 */         if (done && !escaped) {
/* 475 */           ((Mob)this.level.workers.get(this.select)).TargetID = this.targetIDs[this.sb[3].getNumber()];
/* 476 */           ((Mob)this.level.workers.get(this.select)).itempath = this.ItemIDs[this.sb[3].getNumber()];
/* 477 */           history.add("ordered a woker to collect " + (Items.items[this.ItemIDs[this.sb[3].getNumber()]]).name);
/* 478 */           this.choosing = false;
/* 479 */           this.setting = false;
/* 480 */           closeAll();
/* 481 */           openMenu();
/* 482 */           openOrderMenu();
/*     */         } 
/* 484 */         if (escaped) {
/* 485 */           this.choosing = false;
/* 486 */           this.setting = false;
/* 487 */           closeAll();
/* 488 */           closeAllGUI();
/* 489 */           openMenu();
/* 490 */           openOrderMenu();
/*     */         } 
/* 492 */         if (!this.setting) {
/* 493 */           key.keys[38] = false;
/* 494 */           key.keys[40] = false;
/* 495 */           key.keys[39] = false;
/* 496 */           key.keys[37] = false;
/* 497 */           key.keys[27] = false;
/*     */         } 
/*     */       } 
/* 500 */       if (this.sb[activemenu] != null) {
/* 501 */         this.sb[activemenu].tick();
/* 502 */         if (key.keys[38])
/* 503 */           (this.sb[activemenu]).select--; 
/* 504 */         if (key.keys[40])
/* 505 */           (this.sb[activemenu]).select++; 
/* 506 */         key.keys[38] = false;
/* 507 */         key.keys[40] = false;
/* 508 */         if (activemenu == 1) {
/*     */           
/* 510 */           int num = (this.sb[1]).select;
/* 511 */           if (num == 3) {
/* 512 */             (this.gui[0]).name[0] = "";
/* 513 */             (this.gui[0]).name[1] = "";
/* 514 */             (this.gui[0]).name[2] = "";
/*     */           } 
/* 516 */           if (num < 3 && num > -1) {
/* 517 */             Recipes recipe = this.BuildCost[num];
/* 518 */             for (int k = 0; k < 3; k++) {
/* 519 */               int yy = k;
/* 520 */               (this.gui[0]).name[yy] = String.valueOf((Items.items[yy]).name) + ":" + recipe.cost[yy];
/*     */             } 
/*     */           } 
/*     */         } 
/* 524 */         if (activemenu == 6) {
/* 525 */           int num = (this.sb[6]).select;
/* 526 */           if (num < 0)
/* 527 */             num = 0; 
/* 528 */           if (num > (this.sb[6]).name.length - 1)
/* 529 */             num = (this.sb[6]).name.length - 1; 
/* 530 */           String[] ss = new String[3];
/* 531 */           ss[0] = "wood x" + (this.WorkerUpgradeCost[num]).cost[Items.wood.id];
/* 532 */           ss[1] = "rock x" + (this.WorkerUpgradeCost[num]).cost[Items.rock.id];
/* 533 */           ss[2] = "wheat x" + (this.WorkerUpgradeCost[num]).cost[Items.wheat.id];
/* 534 */           String[] status = new String[3];
/* 535 */           status[0] = "pickup speed x" + this.level.worker_pick_speed;
/* 536 */           status[1] = "no abilities";
/* 537 */           status[2] = "no abilities";
/* 538 */           this.gui[6] = new SelectBox(ss, 152, 88, 14, 6, "COST");
/* 539 */           this.gui[7] = new SelectBox(status, 152, 160, 14, 6, "Worker Status");
/*     */         } 
/* 541 */         if (activemenu == 4) {
/*     */           
/* 543 */           int num = (this.sb[4]).select;
/* 544 */           if (num < 3 && num > -1) {
/* 545 */             Recipes recipe = this.TrainCost[num];
/* 546 */             for (int k = 0; k < (this.gui[4]).name.length; k++) {
/* 547 */               int yy = k;
/* 548 */               (this.gui[4]).name[yy] = String.valueOf((Items.items[yy]).name) + ":" + recipe.cost[yy];
/*     */             } 
/* 550 */             if (key.keys[10]) {
/* 551 */               boolean have = true;
/* 552 */               int CheckCostItem = Items.wheat.id;
/* 553 */               if (recipe.cost[CheckCostItem] > this.level.inventory[CheckCostItem])
/* 554 */                 have = false; 
/* 555 */               CheckCostItem = Items.rock.id;
/* 556 */               if (recipe.cost[CheckCostItem] > this.level.inventory[CheckCostItem])
/* 557 */                 have = false; 
/* 558 */               CheckCostItem = Items.wood.id;
/* 559 */               if (recipe.cost[CheckCostItem] > this.level.inventory[CheckCostItem])
/* 560 */                 have = false; 
/* 561 */               if (have) {
/* 562 */                 Archer archer; int CostItem = Items.wheat.id;
/* 563 */                 this.level.inventory[CostItem] = this.level.inventory[CostItem] - recipe.cost[CostItem];
/* 564 */                 CostItem = Items.rock.id;
/* 565 */                 this.level.inventory[CostItem] = this.level.inventory[CostItem] - recipe.cost[CostItem];
/* 566 */                 CostItem = Items.wood.id;
/* 567 */                 this.level.inventory[CostItem] = this.level.inventory[CostItem] - recipe.cost[CostItem];
/* 568 */                 double rx = Math.random() * 0.04D - 0.02D;
/* 569 */                 double ry = Math.random() * 0.04D - 0.02D;
/* 570 */                 Mob mob = null;
/* 571 */                 rx += 16.0D;
/* 572 */                 ry += 12.0D;
/* 573 */                 if (num == 0)
/* 574 */                   Worker worker = new Worker(rx, ry); 
/* 575 */                 if (num == 1)
/* 576 */                   Soldier soldier = new Soldier(rx, ry); 
/* 577 */                 if (num == 2)
/* 578 */                   archer = new Archer(rx, ry); 
/* 579 */                 this.level.mobs.add(archer);
/* 580 */                 String who = "";
/* 581 */                 if (num == 0)
/* 582 */                   who = "Worker"; 
/* 583 */                 if (num == 1)
/* 584 */                   who = "Soldier"; 
/* 585 */                 if (num == 2)
/* 586 */                   who = "Archer"; 
/* 587 */                 history.add("you trained A " + who);
/*     */               } 
/*     */             } 
/* 590 */             key.keys[10] = false;
/*     */           } 
/*     */         } 
/*     */         
/* 594 */         if (key.keys[10]) {
/* 595 */           int ans = this.sb[activemenu].getNumber();
/*     */ 
/*     */           
/* 598 */           if (ans == 2 && 
/* 599 */             activemenu == 0) {
/* 600 */             this.gui[4] = new SelectBox(new String[] { "", "", "" }, 24, 184, 12, 4, "TRAIN COST");
/* 601 */             String[] tm = { "Train Worker", "Train Soldier", "Train Archer" };
/* 602 */             this.sb[4] = new SelectBox(tm, 24, 112, 12, 7, "TRAIN MENU");
/*     */           } 
/*     */           
/* 605 */           if (activemenu == 1) {
/* 606 */             int num = ans;
/* 607 */             int scale = (this.sb[1]).name.length;
/* 608 */             if ((((num >= 0) ? 1 : 0) & ((num < scale) ? 1 : 0)) != 0) {
/* 609 */               boolean rock = (this.level.inventory[Items.rock.id] >= (this.BuildCost[num]).cost[Items.rock.id]);
/* 610 */               boolean wood = (this.level.inventory[Items.wood.id] >= (this.BuildCost[num]).cost[Items.wood.id]);
/* 611 */               boolean wheat = (this.level.inventory[Items.wheat.id] >= (this.BuildCost[num]).cost[Items.wheat.id]);
/* 612 */               if (rock && wood && wheat) {
/* 613 */                 this.placing = true;
/* 614 */                 this.buildId = num;
/* 615 */                 this.tileid = this.RecipeTiles[num];
/* 616 */                 history.add("you are Building a " + (Tile.tiles[this.RecipeTiles[num]]).name);
/* 617 */                 closeAll();
/*     */               } 
/*     */             } 
/*     */           } 
/* 621 */           if (ans == 3 && 
/* 622 */             activemenu == 0) {
/* 623 */             boolean isAvailable = (this.level.workerscount > 0);
/* 624 */             if (isAvailable) {
/* 625 */               String[] upgradeMenu = { "WORKER", "BASE", "TEST", "TEST", "TEST" };
/* 626 */               this.sb[5] = new SelectBox(upgradeMenu, 24, 112, 12, 7, "UPGRADE MENU");
/*     */             } else {
/* 628 */               history.add("there is no upgradable object");
/*     */             } 
/*     */           } 
/*     */           
/* 632 */           if (activemenu == 5 && 
/* 633 */             ans == 0) {
/* 634 */             String[] workerupgrade = { "pick speed" };
/* 635 */             this.sb[6] = new SelectBox(workerupgrade, 24, 128, 12, 7, "Worker Upgrade");
/* 636 */             key.keys[10] = false;
/*     */           } 
/*     */           
/* 639 */           if (activemenu == 5 && 
/* 640 */             ans == 1) {
/* 641 */             String[] baseupgrade = { "Upgrade Base" };
/* 642 */             this.sb[7] = new SelectBox(baseupgrade, 24, 128, 12, 7, "Base Upgrade");
/* 643 */             key.keys[10] = false;
/*     */           } 
/*     */           
/* 646 */           if (activemenu == 7 && 
/* 647 */             ans == 0) {
/* 648 */             history.add("upgraded base");
/* 649 */             this.level.BaseLevel = 1;
/* 650 */             this.level.setValue(16, 8, 16, this.level.BaseLevelHealth[this.level.BaseLevel]);
/* 651 */             key.keys[10] = false;
/*     */           } 
/*     */           
/* 654 */           if (activemenu == 6) {
/* 655 */             int num = ans;
/* 656 */             if (num < 0)
/* 657 */               num = 0; 
/* 658 */             if (num > (this.sb[activemenu]).name.length - 1)
/* 659 */               num = (this.sb[activemenu]).name.length - 1; 
/* 660 */             boolean upgradable = false;
/* 661 */             int wheatcost = (this.WorkerUpgradeCost[num]).cost[Items.wheat.id];
/* 662 */             int rockcost = (this.WorkerUpgradeCost[num]).cost[Items.rock.id];
/* 663 */             int woodcost = (this.WorkerUpgradeCost[num]).cost[Items.wood.id];
/* 664 */             int wheat = this.level.inventory[Items.wheat.id] - wheatcost;
/* 665 */             int rock = this.level.inventory[Items.rock.id] - rockcost;
/* 666 */             int wood = this.level.inventory[Items.wood.id] - woodcost;
/* 667 */             if (wheat >= 0 && rock >= 0 && wood >= 0) {
/* 668 */               upgradable = true;
/*     */             }
/* 670 */             if (upgradable) {
/* 671 */               this.level.inventory[Items.wheat.id] = this.level.inventory[Items.wheat.id] - wheatcost;
/* 672 */               this.level.inventory[Items.rock.id] = this.level.inventory[Items.rock.id] - rockcost;
/* 673 */               this.level.inventory[Items.wood.id] = this.level.inventory[Items.wood.id] - woodcost;
/* 674 */               this.level.worker_pick_speed++;
/* 675 */               history.add("upgraded pick speed");
/*     */             } else {
/* 677 */               history.add("you dont have enough items");
/*     */             } 
/* 679 */             key.keys[10] = false;
/*     */           } 
/* 681 */           if (ans == 1 && 
/* 682 */             activemenu == 0) {
/* 683 */             boolean isAvailable = (this.level.workerscount > 0);
/* 684 */             if (isAvailable)
/* 685 */               openOrderMenu(); 
/* 686 */             if (!isAvailable) {
/* 687 */               history.add("there is no worker");
/*     */             }
/*     */           } 
/* 690 */           if (ans == 11 && 
/* 691 */             activemenu == 0) {
/* 692 */             this.showtitlemenu = true;
/* 693 */             this.started = false;
/*     */           } 
/*     */           
/* 696 */           if (ans == 0) {
/* 697 */             if (activemenu == 2) {
/* 698 */               this.choosing = true;
/* 699 */               for (int k = 0; k < this.level.workers.size(); k++) {
/* 700 */                 if (((Mob)this.level.workers.get(k)).TargetID == 0) {
/* 701 */                   this.select = k;
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 705 */               closeAll();
/*     */             } 
/* 707 */             if (activemenu == 0) {
/* 708 */               boolean hasAmob = (this.level.workerscount > 0);
/* 709 */               if (hasAmob) {
/* 710 */                 String[] ss = { "Farm", "Barracks", "RockWall" };
/* 711 */                 this.sb[1] = new SelectBox(ss, 24, 104, 12, 6, "BuildMenu");
/* 712 */                 String[] costs = { "Test", "Cost", "OWSLA" };
/* 713 */                 this.gui[0] = new SelectBox(costs, 24, 168, 12, 5, "costs");
/*     */               } else {
/* 715 */                 history.add("There is no worker");
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 720 */         key.keys[10] = false;
/* 721 */         if (key.keys[27] && !this.choosing) {
/* 722 */           shouldcloseMenu = true;
/*     */         }
/* 724 */         key.keys[27] = false;
/*     */       } 
/* 726 */       if (!this.placing && !this.choosing) {
/* 727 */         if (key.keys[27] && this.sb[0] == null && !shouldcloseMenu) {
/* 728 */           openMenu();
/*     */         }
/* 730 */         key.keys[27] = false;
/*     */       } 
/* 732 */       if (shouldcloseMenu) {
/* 733 */         close(activemenu);
/* 734 */         closeAllGUI();
/*     */       } 
/*     */     } 
/* 737 */     this.level.tick();
/* 738 */     if (this.started) {
/* 739 */       this.level.gametime++;
/* 740 */       double s = 0.052333333333333336D;
/* 741 */       if (key.keys[90])
/* 742 */         this.angle += s; 
/* 743 */       if (key.keys[88])
/* 744 */         this.angle -= s; 
/* 745 */       if (key.keys[82]) {
/* 746 */         this.zview = !this.zview;
/*     */       }
/* 748 */       key.keys[82] = false;
/*     */     } else {
/* 750 */       this.msec++;
/* 751 */       if (this.msec > 30) {
/* 752 */         this.swap = !this.swap;
/* 753 */         this.msec = 0;
/*     */       } 
/* 755 */       this.angle += 0.005D;
/* 756 */       if (key.keys[27] && this.showtitlemenu) {
/* 757 */         String[] a = { "start", "tutorial", "about", "quit" };
/* 758 */         this.titlemenu = new SelectBox(a, 128, 168, 10, 6, "title menu");
/* 759 */         this.TITLESTATE = 0;
/* 760 */         key.keys[27] = false;
/*     */       } 
/* 762 */       if (key.keys[10] && this.showtitlemenu) {
/* 763 */         int ans = this.titlemenu.select;
/* 764 */         if (ans == 0 && this.TITLESTATE == 0) {
/* 765 */           this.started = true;
/* 766 */           this.level = new Level(32, 32, 32);
/* 767 */           this.sb = new SelectBox[10];
/* 768 */           this.gui = new SelectBox[10];
/* 769 */           key.keys[10] = false;
/*     */         } 
/* 771 */         if (ans == 3 && this.TITLESTATE == 0) {
/* 772 */           System.exit(0);
/*     */         }
/* 774 */         if (ans == 2 && this.TITLESTATE == 0) {
/* 775 */           this.TITLESTATE = 2;
/* 776 */           String[] ss = { "a game made by hideto ozeki", "hakata seishou 2017" };
/* 777 */           this.titlemenu = new SelectBox(ss, 96, 136, 17, 9, "about this game");
/* 778 */           key.keys[10] = false;
/*     */         } 
/* 780 */         if (ans == 1 && this.TITLESTATE == 0) {
/* 781 */           String[] ss = { "tips", "how to", "tricks" };
/* 782 */           this.titlemenu = new SelectBox(ss, 120, 168, 11, 6, "tutorial");
/* 783 */           this.TITLESTATE = 1;
/* 784 */           key.keys[10] = false;
/*     */         } 
/* 786 */         if (ans == 0 && this.TITLESTATE == 1) {
/* 787 */           System.out.println("test");
/* 788 */           key.keys[10] = false;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void openMenu() {
/* 795 */     String[] mm = { "Build", "Order", "train", "upgrade", "", "", "", "", "", "", "", 
/* 796 */         "Return To Title" };
/* 797 */     this.sb[0] = new SelectBox(mm, 16, 88, 14, 17, "menu");
/*     */   }
/*     */   
/*     */   public void openOrderMenu() {
/* 801 */     String[] ss = { "Worker" };
/* 802 */     this.sb[2] = new SelectBox(ss, 24, 120, 12, 6, "OrderMenu");
/*     */   }
/*     */   public void closeAll() {
/*     */     int i;
/* 806 */     for (i = 0; i < this.sb.length; i++) {
/* 807 */       this.sb[i] = null;
/*     */     }
/* 809 */     for (i = 0; i < this.gui.length; i++) {
/* 810 */       this.gui[i] = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public void close(int num) {
/* 815 */     this.sb[num] = null;
/*     */   }
/*     */   
/*     */   public void closeAllGUI() {
/* 819 */     for (int i = 0; i < this.gui.length; i++) {
/* 820 */       this.gui[i] = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public void closeGUI(int num) {
/* 825 */     this.gui[num] = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 830 */     this.screen = new Screen(320, 240);
/* 831 */     long last = System.nanoTime();
/* 832 */     double ns = 1.6666666666666666E7D;
/* 833 */     double delta = 0.0D;
/* 834 */     int updates = 0;
/* 835 */     int frames = 0;
/* 836 */     long timer = System.currentTimeMillis();
/*     */     while (true) {
/* 838 */       long now = System.nanoTime();
/* 839 */       delta += (now - last) / ns;
/* 840 */       last = now;
/* 841 */       if (delta >= 1.0D) {
/* 842 */         delta--;
/* 843 */         tick();
/* 844 */         updates++;
/*     */       } else {
/* 846 */         frames++;
/* 847 */         render();
/*     */       } 
/* 849 */       if (System.currentTimeMillis() - timer >= 1000L) {
/* 850 */         timer += 1000L;
/* 851 */         System.out.println(
/* 852 */             "(" + frames + " : fps " + updates + " : tps )" + " currentmenu : " + (this.currentMenu + 1));
/* 853 */         frames = 0;
/* 854 */         updates = 0;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public class Mouse
/*     */     extends MouseInputAdapter
/*     */   {
/*     */     public void mousePressed(MouseEvent m) {
/* 863 */       Game.this.mpressed = true;
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseReleased(MouseEvent m) {
/* 868 */       Game.this.mpressed = false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Game.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */