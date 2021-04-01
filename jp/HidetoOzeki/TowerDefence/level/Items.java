/*    */ package jp.HidetoOzeki.TowerDefence.level;
/*    */ 
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ 
/*    */ public class Items {
/*  6 */   public static Items[] items = new Items[32];
/*  7 */   public static final Items wheat = new Items(0, "Wheat", 0, 14);
/*  8 */   public static final Items wood = new Items(1, "Wood", 0, 12); public int id; public String name;
/*  9 */   public static final Items rock = new Items(2, "Rock", 0, 10);
/*    */   int tx;
/*    */   int ty;
/*    */   
/*    */   public Items(int id, String n, int x, int y) {
/* 14 */     this.tx = x;
/* 15 */     this.ty = y;
/* 16 */     this.name = n;
/* 17 */     this.id = id;
/* 18 */     items[id] = this;
/*    */   }
/*    */   public void render(Screen screen, int x, int y) {
/* 21 */     screen.mountColor(0);
/* 22 */     screen.render(x, y, this.tx, this.ty);
/* 23 */     screen.render(x + 8, y, this.tx + 1, this.ty);
/* 24 */     screen.render(x, y + 8, this.tx, this.ty + 1);
/* 25 */     screen.render(x + 8, y + 8, this.tx + 1, this.ty + 1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\level\Items.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */