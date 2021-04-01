/*    */ package jp.HidetoOzeki.TowerDefence;
/*    */ 
/*    */ public class History {
/*  4 */   public String[] texts = new String[10];
/*    */ 
/*    */   
/*    */   public void add(String s) {
/*  8 */     for (int i = 0; i < 10; i++) {
/*  9 */       if (i - 1 > -1) {
/* 10 */         this.texts[i - 1] = this.texts[i];
/*    */       }
/*    */     } 
/* 13 */     this.texts[9] = s;
/*    */   }
/*    */   public String[] get() {
/* 16 */     return this.texts;
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\History.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */