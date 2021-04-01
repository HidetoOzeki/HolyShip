/*    */ package jp.HidetoOzeki.TowerDefence.level.Tiles;
/*    */ 
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ 
/*    */ public class Farm
/*    */   extends Tile {
/*    */   public Farm(int id, String name) {
/*  9 */     super(id, name);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Level level, int x, int y, Screen screen, int zb) {
/* 15 */     screen.render(x - 8, y - 8, 1, 2, zb - 4);
/* 16 */     screen.render(x, y, 2, 3, zb - 4);
/* 17 */     screen.render(x - 8, y, 1, 3, zb - 4);
/* 18 */     screen.render(x, y - 8, 2, 2, zb - 4);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(int tilex, int tiley, int tilez, Level level, int x, int y, Screen screen, int zb) {
/* 24 */     super.render(tilex, tiley, tilez, level, x, y, screen, zb);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(Level level, int tx, int ty, int tz) {
/* 29 */     super.tick(level, tx, ty, tz);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\level\Tiles\Farm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */