/*    */ package jp.HidetoOzeki.TowerDefence;
/*    */ 
/*    */ import java.awt.event.FocusEvent;
/*    */ import java.awt.event.FocusListener;
/*    */ import java.awt.event.KeyEvent;
/*    */ import java.awt.event.KeyListener;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ import java.awt.event.MouseMotionListener;
/*    */ 
/*    */ public class Key implements KeyListener, FocusListener, MouseListener, MouseMotionListener {
/* 12 */   public boolean[] keys = new boolean[65536];
/*    */ 
/*    */   
/*    */   public void mouseDragged(MouseEvent arg0) {}
/*    */ 
/*    */   
/*    */   public void mouseMoved(MouseEvent arg0) {}
/*    */ 
/*    */   
/*    */   public void mouseClicked(MouseEvent arg0) {}
/*    */ 
/*    */   
/*    */   public void mouseEntered(MouseEvent arg0) {}
/*    */ 
/*    */   
/*    */   public void mouseExited(MouseEvent arg0) {}
/*    */ 
/*    */   
/*    */   public void mousePressed(MouseEvent arg0) {}
/*    */ 
/*    */   
/*    */   public void mouseReleased(MouseEvent arg0) {}
/*    */ 
/*    */   
/*    */   public void focusGained(FocusEvent arg0) {}
/*    */   
/*    */   public void focusLost(FocusEvent arg0) {
/* 39 */     for (int i = 0; i < this.keys.length; i++) {
/* 40 */       this.keys[i] = false;
/*    */     }
/*    */   }
/*    */   
/*    */   public void keyPressed(KeyEvent e) {
/* 45 */     int code = e.getKeyCode();
/* 46 */     if (code > 0 && code < this.keys.length) {
/* 47 */       this.keys[code] = true;
/*    */     }
/*    */   }
/*    */   
/*    */   public void keyReleased(KeyEvent e) {
/* 52 */     int code = e.getKeyCode();
/* 53 */     if (code > 0 && code < this.keys.length)
/* 54 */       this.keys[code] = false; 
/*    */   }
/*    */   
/*    */   public void keyTyped(KeyEvent arg0) {}
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Key.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */