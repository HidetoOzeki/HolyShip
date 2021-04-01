/*    */ package jp.HidetoOzeki.TowerDefence.level.Tiles;
/*    */ 
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ 
/*    */ public class Stone
/*    */   extends Tile {
/*    */   public Stone(int id, String name) {
/*  9 */     super(id, name);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Level level, int x, int y, Screen screen, int zb) {
/* 15 */     screen.render(x - 4, y - 4, 3, 10, zb);
/* 16 */     super.render(level, x, y, screen, zb);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(int tilex, int tiley, int tilez, Level level, int x, int y, Screen screen, int zb) {
/* 22 */     super.render(tilex, tiley, tilez, level, x, y, screen, zb);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick(Level level, int tx, int ty, int tz) {
/* 28 */     super.tick(level, tx, ty, tz);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\level\Tiles\Stone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */