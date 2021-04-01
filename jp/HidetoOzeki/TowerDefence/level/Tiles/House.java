/*    */ package jp.HidetoOzeki.TowerDefence.level.Tiles;
/*    */ 
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ 
/*    */ public class House extends Tile {
/*  7 */   int lvl = 1;
/*    */   public House(int id, String name) {
/*  9 */     super(id, name);
/* 10 */     this.transparency = true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(Level level, int x, int y, Screen screen, int zb) {
/* 15 */     this.lvl = level.BaseLevel;
/* 16 */     if (this.lvl == 0) {
/* 17 */       screen.render(x - 8, y - 8, 1, 2, zb - 4);
/* 18 */       screen.render(x, y, 2, 3, zb - 4);
/* 19 */       screen.render(x - 8, y, 1, 3, zb - 4);
/* 20 */       screen.render(x, y - 8, 2, 2, zb - 4);
/*    */     } 
/* 22 */     if (this.lvl == 1) {
/* 23 */       screen.render(x - 8, y - 24, 13, 16, zb - 8);
/* 24 */       screen.render(x - 8, y - 16, 13, 17, zb - 8);
/* 25 */       screen.render(x - 8, y - 8, 13, 18, zb - 8);
/* 26 */       screen.render(x - 8, y, 13, 19, zb - 8);
/*    */       
/* 28 */       screen.render(x, y - 24, 14, 16, zb - 8);
/* 29 */       screen.render(x, y - 16, 14, 17, zb - 8);
/* 30 */       screen.render(x, y - 8, 14, 18, zb - 8);
/* 31 */       screen.render(x, y, 14, 19, zb - 8);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\level\Tiles\House.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */