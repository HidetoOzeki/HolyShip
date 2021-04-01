/*    */ package jp.HidetoOzeki.TowerDefence.gfx;
/*    */ 
/*    */ public class Font {
/*  4 */   static String fonts = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789<>()?!:/ ";
/*    */ 
/*    */ 
/*    */   
/*    */   public static void drawFont(Screen screen, String s, int x, int y, int col) {
/*  9 */     s = s.toUpperCase();
/* 10 */     for (int i = 0; i < s.length(); i++) {
/* 11 */       int n = fonts.indexOf(s.charAt(i));
/* 12 */       screen.mountColor(col);
/* 13 */       screen.render(x + i * 8, y, n, 36);
/* 14 */       screen.mountColor(0);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\gfx\Font.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */