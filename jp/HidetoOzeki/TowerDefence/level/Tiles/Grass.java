/*    */ package jp.HidetoOzeki.TowerDefence.level.Tiles;
/*    */ 
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ 
/*    */ public class Grass extends Tile {
/*    */   public Grass(int id, String name) {
/*  8 */     super(id, name);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Level levle, int x, int y, Screen screen, int zb) {
/* 13 */     screen.render(x - 4, y - 4, 0, 0, zb);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\level\Tiles\Grass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */