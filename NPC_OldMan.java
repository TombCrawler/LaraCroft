import java.util.Random;

public class NPC_OldMan extends Entity{
    // constructor
    public NPC_OldMan(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }
    public void getImage(){

        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
    }
    // make a method
    public void setDialogue(){
        dialogues[0] = "Hello, Lara";
        dialogues[1] = "You've made it here, \nfound the relic?";
        dialogues[2] = "I'm pretty old now \nbut was a great sorcerer";
        dialogues[3] = "Good luck";
    }
    public void setAction(){
        actionLockCounter ++;
        if(actionLockCounter == 120){ // this means 2 sec
            Random random = new Random();
            int i = random.nextInt(100)+1; // this means the Random chooses a number between 0 - 99, but since we added 1, it becomes 1 -100

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
    // create a method
    public void speak(){ // It works w/o this speak method but leave it here for when you want to add specific features to this character
        super.speak();

        // we called the super class from the Entity class, so we do not need lines below anymore
//       if(dialogues[dialogueIndex] == null){
//           dialogueIndex = 0; //w/o this if statement, we get a null pointer exception error
//       }
//       gp.ui.currentDialogue = dialogues[dialogueIndex];
//       dialogueIndex++;
//
//       // to make NPC turn around to talk to the player
//       switch(gp.player.direction){
//           case "up":
//               direction = "down";
//               break;
//           case "down":
//               direction = "up";
//               break;
//           case "left":
//               direction = "right";
//               break;
//           case "right":
//               direction = "left";
//               break;
//       }
    }
}
