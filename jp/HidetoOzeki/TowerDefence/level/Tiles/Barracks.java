/*    */ package jp.HidetoOzeki.TowerDefence.level.Tiles;
/*    */ 
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ 
/*    */ public class Barracks
/*    */   extends Tile {
/*    */   public Barracks(int id, String name) {
/*  9 */     super(id, name);
/* 10 */     this.transparency = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Level level, int x, int y, Screen screen, int zb) {
/* 15 */     int xx = 3;
/* 16 */     int yy = 16;
/* 17 */     int py = y;
/* 18 */     screen.render(x - 8, py - 8, xx, yy, zb - 4);
/* 19 */     screen.render(x, py, xx + 1, yy + 1, zb - 4);
/* 20 */     screen.render(x - 8, py, xx, yy + 1, zb - 4);
/* 21 */     screen.render(x, py - 8, xx + 1, yy, zb - 4);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\level\Tiles\Barracks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */