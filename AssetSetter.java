public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
         this.gp = gp;
    }

    public void setObject(){
        // set the key in front of the lake
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 23 * gp.tileSize; // zero index
        gp.obj[0].worldY = 7 * gp.tileSize; // zero index

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 23 * gp.tileSize; // zero index
        gp.obj[1].worldY = 40 * gp.tileSize; // zero index

        gp.obj[2] = new OBJ_Key(gp);
        gp.obj[2].worldX = 38 * gp.tileSize; // zero index
        gp.obj[2].worldY = 8 * gp.tileSize; // zero index

        gp.obj[3] = new OBJ_Door(gp);
        gp.obj[3].worldX = 12 * gp.tileSize; // zero index
        gp.obj[3].worldY = 22 * gp.tileSize; // zero index

        gp.obj[4] = new OBJ_Door(gp);
        gp.obj[4].worldX = 8 * gp.tileSize; // zero index
        gp.obj[4].worldY = 28 * gp.tileSize; // zero index

        gp.obj[5] = new OBJ_Door(gp);
        gp.obj[5].worldX = 10 * gp.tileSize; // zero index
        gp.obj[5].worldY = 11 * gp.tileSize; // zero index

        gp.obj[6] = new OBJ_Doll(gp);
        gp.obj[6].worldX = 10 * gp.tileSize; // zero index
        gp.obj[6].worldY = 7 * gp.tileSize; // zero index

        gp.obj[7] = new OBJ_Gun(gp);
        gp.obj[7].worldX = 37 * gp.tileSize; // zero index
        gp.obj[7].worldY = 42   * gp.tileSize; // zero index

    }
}
