/*    */ package jp.HidetoOzeki.TowerDefence.gfx;
/*    */ 
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.IOException;
/*    */ import java.net.URL;
/*    */ import javax.imageio.ImageIO;
/*    */ import jp.HidetoOzeki.TowerDefence.Game;
/*    */ 
/*    */ public class Bitmap
/*    */ {
/*    */   public int[] pixels;
/*    */   BufferedImage img;
/*    */   public int w;
/*    */   public int h;
/*    */   
/*    */   public Bitmap(String s) {
/*    */     try {
/* 18 */       URL url = Game.class.getResource(s);
/* 19 */       this.img = ImageIO.read(url);
/* 20 */     } catch (IOException e) {
/*    */       
/* 22 */       e.printStackTrace();
/*    */     } 
/* 24 */     this.w = this.img.getWidth();
/* 25 */     this.h = this.img.getHeight();
/* 26 */     this.pixels = this.img.getRGB(0, 0, this.w, this.h, null, 0, this.w);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\gfx\Bitmap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */