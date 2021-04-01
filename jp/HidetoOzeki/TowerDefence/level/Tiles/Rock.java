/*    */ package jp.HidetoOzeki.TowerDefence.level.Tiles;
/*    */ 
/*    */ import jp.HidetoOzeki.TowerDefence.Entity.RockItem;
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ 
/*    */ public class Rock
/*    */   extends Tile {
/*    */   public Rock(int id, String name) {
/* 10 */     super(id, name);
/* 11 */     val[id] = 20;
/* 12 */     this.hp = 360;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Level level, int x, int y, Screen screen, int zb) {}
/*    */ 
/*    */   
/*    */   public void render(int tilex, int tiley, int tilez, Level level, int x, int y, Screen screen, int zb) {
/* 21 */     int ty = 0;
/* 22 */     screen.render(x - 8, y - 8, 0, ty + 4, zb);
/* 23 */     screen.render(x, y, 1, ty + 4 + 1, zb);
/* 24 */     screen.render(x - 8, y, 0, ty + 4 + 1, zb);
/* 25 */     screen.render(x, y - 8, 1, ty + 4, zb);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(Level level, int tx, int ty, int tz) {
/* 30 */     double val = level.getValue(tx, ty, tz);
/* 31 */     if (val < 1.0D) {
/* 32 */       level.put(tx, ty, tz, Tile.air.id);
/* 33 */       int n = 0;
/* 34 */       for (int i = 0; i < n; i++)
/* 35 */         level.items.add(new RockItem(tx, ty, tz)); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\level\Tiles\Rock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */