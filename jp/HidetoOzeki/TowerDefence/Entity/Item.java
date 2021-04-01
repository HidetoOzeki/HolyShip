/*    */ package jp.HidetoOzeki.TowerDefence.Entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Tiles.Tile;
/*    */ 
/*    */ public class Item
/*    */   extends Entity {
/*    */   int itemid;
/*    */   boolean grabbed = true;
/*    */   
/*    */   public Item(double x, double y, double z) {
/* 14 */     this.x = x;
/* 15 */     this.y = y;
/* 16 */     this.z = z;
/* 17 */     double rr = Math.random() * 6.28D;
/* 18 */     double sp = 0.03D + Math.random() * 0.05D;
/* 19 */     double zz = 1.0D + Math.random() * 0.2D;
/* 20 */     this.vx = Math.sin(rr) * sp;
/* 21 */     this.vy = Math.cos(rr) * sp;
/* 22 */     this.vz = zz;
/* 23 */     this.life = 120;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Screen screen, Level level) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick(Level level) {
/* 33 */     this.life--;
/* 34 */     if (this.x < 1.0D) this.x = 1.0D; 
/* 35 */     if (this.x > 32.0D) this.x = 32.0D; 
/* 36 */     if (this.y < 1.0D) this.y = 1.0D; 
/* 37 */     if (this.y > 32.0D) this.y = 32.0D; 
/* 38 */     this.x += this.vx;
/* 39 */     this.y += this.vy;
/* 40 */     this.z += this.vz;
/* 41 */     int tx = (int)this.x;
/* 42 */     int ty = (int)this.y;
/* 43 */     int tz = (int)(this.z + this.vz);
/* 44 */     int tile = level.getTile(tx, ty, tz);
/* 45 */     if ((((tile == 0) ? 1 : 0) | ((tile == Tile.water.id) ? 1 : 0)) != 0 && !this.grabbed) {
/* 46 */       this.vz -= 0.02D;
/*    */     } else {
/* 48 */       this.vz = -this.vz * 0.5D;
/* 49 */       this.vx *= 0.9D;
/* 50 */       this.vy *= 0.9D;
/* 51 */       this.grabbed = false;
/*    */     } 
/* 53 */     tz = (int)this.z;
/* 54 */     tile = level.getTile(tx, ty, tz);
/* 55 */     int tile10 = level.getTile(tx + 1, ty, tz);
/* 56 */     int tile01 = level.getTile(tx, ty + 1, tz);
/* 57 */     int tile11 = level.getTile(tx + 1, ty + 1, tz);
/* 58 */     int tile100 = level.getTile(tx - 1, ty, tz);
/* 59 */     int tile010 = level.getTile(tx, ty - 1, tz);
/* 60 */     int tile110 = level.getTile(tx - 1, ty - 1, tz);
/* 61 */     if ((((tile == Tile.house.id) ? 1 : 0) | (
/* 62 */       (tile01 == Tile.house.id) ? 1 : 0) | (
/* 63 */       (tile10 == Tile.house.id) ? 1 : 0) | (
/* 64 */       (tile11 == Tile.house.id) ? 1 : 0)) != 0) {
/* 65 */       this.life = 0;
/* 66 */       level.inventory[this.itemid] = level.inventory[this.itemid] + 1;
/*    */     } 
/*    */   }
/*    */   public void move(double vx, double vy, double vz) {
/* 70 */     this.x = vx;
/* 71 */     this.y = vy;
/* 72 */     this.z = vz;
/* 73 */     this.grabbed = true;
/*    */   }
/*    */   public void toss(double vx, double vy) {
/* 76 */     this.vx = vx;
/* 77 */     this.vy = vy;
/* 78 */     this.vz = 0.2D;
/* 79 */     this.grabbed = false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void remove(ArrayList<Entity> e) {
/* 85 */     super.remove(e);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void autoremove(ArrayList<Entity> e) {
/* 91 */     super.autoremove(e);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\Item.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */