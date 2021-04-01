/*    */ package jp.HidetoOzeki.TowerDefence.level.Tiles;
/*    */ 
/*    */ import jp.HidetoOzeki.TowerDefence.Entity.WoodItem;
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ 
/*    */ public class Tree
/*    */   extends Tile {
/*    */   public Tree(int id, String name) {
/* 10 */     super(id, name);
/* 11 */     this.transparency = true;
/* 12 */     val[id] = 5;
/* 13 */     this.hp = 240;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Level level, int x, int y, Screen screen, int zb) {
/* 19 */     screen.render(x - 4, y - 4, 2, 0, zb);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(int tilex, int tiley, int tilez, Level level, int x, int y, Screen screen, int zb) {
/* 25 */     super.render(tilex, tiley, tilez, level, x, y, screen, zb);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(Level level, int tx, int ty, int tz) {
/* 30 */     double val = level.getValue(tx, ty, tz);
/* 31 */     if (val < 1.0D) {
/* 32 */       int n = 0;
/* 33 */       for (int i = 0; i < n; i++) {
/* 34 */         level.items.add(new WoodItem(tx, ty, tz));
/*    */       }
/* 36 */       level.put(tx, ty, tz, 0);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\level\Tiles\Tree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */