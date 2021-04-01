/*    */ package jp.HidetoOzeki.TowerDefence.level.Tiles;
/*    */ 
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ 
/*    */ public class Wheat
/*    */   extends Tile
/*    */ {
/*    */   public Wheat(int id, String name) {
/* 10 */     super(id, name);
/* 11 */     this.transparency = true;
/* 12 */     val[id] = 1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Level level, int x, int y, Screen screen, int zb) {
/* 18 */     super.render(level, x, y, screen, zb);
/*    */   }
/*    */ 
/*    */   
/*    */   public void render(int tilex, int tiley, int tilez, Level level, int x, int y, Screen screen, int zb) {
/* 23 */     int tex = ((int)level.getValue(tilex, tiley, tilez) - 1) / 2;
/* 24 */     if (tex > 4)
/* 25 */       tex = 4; 
/* 26 */     if (tex != 0) {
/* 27 */       screen.render(x - 4, y - 3, 2, 10 + tex - 1, zb);
/*    */     }
/*    */   }
/*    */   
/*    */   public void tick(Level level, int tx, int ty, int tz) {
/* 32 */     super.tick(level, tx, ty, tz);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\level\Tiles\Wheat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */