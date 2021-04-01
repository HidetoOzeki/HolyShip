/*    */ package jp.HidetoOzeki.TowerDefence.Entity;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ 
/*    */ public class Enemy
/*    */   extends Mob {
/*  9 */   double walkspeed = 0.06D;
/* 10 */   Boat boat = null;
/*    */   public Enemy(double x, double y) {
/* 12 */     super(x, y);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void render(Screen screen, Level level) {
/* 19 */     super.render(screen, level);
/*    */   }
/*    */ 
/*    */   
/*    */   public void tick(Level level) {
/* 24 */     this.x += this.vx;
/* 25 */     this.y += this.vy;
/* 26 */     this.z += this.vz;
/* 27 */     this.vx *= 0.9D;
/* 28 */     this.vy *= 0.9D;
/* 29 */     if (!this.boat.land && this.boat != null) {
/* 30 */       this.boat.put(this);
/* 31 */       this.z = 16.7D;
/*    */     } 
/* 33 */     super.tick(level);
/*    */   }
/*    */ 
/*    */   
/*    */   public void collide(Mob mob) {
/* 38 */     if (mob != this) {
/*    */       
/* 40 */       double dx = mob.x - this.x;
/* 41 */       double dy = mob.y - this.y;
/* 42 */       double distance = Math.sqrt(dx * dx + dy * dy);
/* 43 */       if (distance < 0.5D && distance > 0.01D) {
/* 44 */         double refangle = Math.atan2(dx, dy);
/* 45 */         this.vx -= Math.sin(refangle) * this.walkspeed;
/* 46 */         this.vy -= Math.cos(refangle) * this.walkspeed;
/*    */       } 
/*    */     } 
/*    */     
/* 50 */     super.collide(mob);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void remove(ArrayList<Entity> e) {
/* 56 */     super.remove(e);
/*    */   }
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\Entity\Enemy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */