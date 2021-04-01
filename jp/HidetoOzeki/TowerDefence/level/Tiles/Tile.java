/*    */ package jp.HidetoOzeki.TowerDefence.level.Tiles;
/*    */ 
/*    */ import jp.HidetoOzeki.TowerDefence.gfx.Screen;
/*    */ import jp.HidetoOzeki.TowerDefence.level.Level;
/*    */ 
/*    */ public class Tile {
/*    */   public int id;
/*    */   public boolean transparency;
/*    */   public String name;
/* 10 */   public int hp = 1;
/* 11 */   public static Tile[] tiles = new Tile[64];
/* 12 */   public static int[] val = new int[64];
/* 13 */   public static final Tile air = new Air(0, "Air");
/* 14 */   public static final Tile dirt = new Dirt(1, "Dirt");
/* 15 */   public static final Tile grass = new Grass(2, "grass");
/* 16 */   public static final Tile tree = new Tree(3, "tree");
/* 17 */   public static final Tile water = new Water(4, "water");
/* 18 */   public static final Tile rockwall = new RockWall(5, "rockwall");
/* 19 */   public static final Tile house = new House(6, "House");
/* 20 */   public static final Tile rock = new Rock(7, "Rock");
/* 21 */   public static final Tile cobble = new CobbleStone(8, "CobbleStone");
/* 22 */   public static final Tile farm = new Farm(9, "Farm");
/* 23 */   public static final Tile wheat = new Wheat(10, "Wheat");
/* 24 */   public static final Tile barracks = new Barracks(11, "Barracks");
/* 25 */   public static final Tile stone = new Stone(12, "Stone");
/*    */   public Tile(int id, String name) {
/* 27 */     this.id = id;
/* 28 */     this.name = name;
/* 29 */     tiles[id] = this;
/*    */   }
/*    */   
/*    */   public void render(Level level, int x, int y, Screen screen, int zb) {}
/*    */   
/*    */   public void render(int tilex, int tiley, int tilez, Level level, int x, int y, Screen screen, int zb) {}
/*    */   
/*    */   public void tick(Level level, int tx, int ty, int tz) {}
/*    */ }


/* Location:              C:\Users\Ozeki\Downloads\version_SEISHOSAI_AlphaHolyShip_scale_2\[version SEISHOSAI Alpha]HolyShip! (scale 2).jar!\jp\HidetoOzeki\TowerDefence\level\Tiles\Tile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */