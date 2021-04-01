/*    */ package jp.HidetoOzeki.TowerDefence.level.Tiles;
/*    */ 
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ 
/*    */ public class Water
/*    */   extends Tile {
/*    */   public Water(int id, String name) {
/*  9 */     super(id, name);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Level level, int x, int y, Screen screen, int zb) {
/* 15 */     screen.render(x - 4, y - 4, 0, 1, zb);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\level\Tiles\Water.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */