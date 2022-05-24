import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class Dynamic extends JPanel implements ActionListener, KeyListener{
    //Field----------------------------------------------------------------------------------------
    final int WIDTH = 700;
    final int HEIGHT = 700;
    int cLeftSideLine = 650;
    int cRightSideLine = 350;
    int cLeftCarLine = -90;
    int cRightCarLine = -340;
    long score = 0;
    Image backgroundImage;
    Timer timer;
    int countL = 0 , countR = 0;
    Random random = new Random();

    private SpeedBoost leftBoost = new SpeedBoost();
    private SpeedBoost rightBoost = new SpeedBoost();
    private int roadMove = 0;
    private int tree1PosX = 100, tree2PosX = 520, tree3PosX = 520, stonePosX = 52, cloud1PosX = -200, cloud2PosX = 0;
    private int tree1PosY = 300, tree2PosY = 200, tree3PosY = 200, stonePosY = 290;
    private int mainCarPosX = 168, mainCarPosY = 600;
    private int speedBoost1PosX = 241, speedBoost1PosY = 335, speedBoost2PosX = 409, speedBoost2PosY = 335; 
    private ImageIcon tree1, tree2, tree3, stone, cloud1, cloud2, car, speedWindow;
    private int sideVelocityY = 20;
    private int cloudVelocity1 = 5;
    private int cloudVelocity2 = 5;
    private boolean gameover = false;
    private int displaySpeed = 10;
    private int displaySpeedPosX = 200;
    //Constructor----------------------------------------------------------------------------------
    public Dynamic() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        backgroundImage = new ImageIcon("assets\\background.png").getImage();
        timer = new Timer(100 , this); //Timer to call action performed.
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }
    //Method----------------------------------------------------------------------------------------
    public void paint(Graphics g) { //Call every time when action perform end.
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(backgroundImage,0,0,null);
        // Move center line-----------------------------------------------
        if(roadMove==0)
        {
            for(int i=302; i<=700; i+=100)
            {
                g.setColor(new Color(255,239,0));
                g.fillRect(345, i,10, 70);
        
            }
        }
        else if(roadMove==1)
        {
            for(int i=302 + sideVelocityY; i<=700; i+=100)
            {
                g.setColor(new Color(255,239,0));
                g.fillRect(345, i,10, 70);
            }
        }
        // Paralax sky----------------------------------------------------
        cloud1 = new ImageIcon("assets\\cloud1.png");
        cloud1.paintIcon(this, g2d, cloud1PosX, 180);
        cloud2 = new ImageIcon("assets\\cloud2.png");
        cloud2.paintIcon(this, g2d, cloud2PosX, 180);
        // Side Move------------------------------------------------------
        tree1 = new ImageIcon("assets\\tree1.png");
        tree1.paintIcon(this, g2d, tree1PosX, tree1PosY);
        tree2 = new ImageIcon("assets\\tree2.png");
        tree2.paintIcon(this, g2d, tree2PosX, tree2PosY);
        tree3 = new ImageIcon("assets\\tree3.png");
        tree3.paintIcon(this, g2d, tree3PosX, tree3PosY);
        stone = new ImageIcon("assets\\stone.png");
        stone.paintIcon(this, g2d, stonePosX, stonePosY);
        // Speed boost------------------------------------------------------
		g.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,35));
		g.setColor(Color.black);
        g2d.drawString(leftBoost.Display(), speedBoost1PosX ,speedBoost1PosY); //display
        g.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,35));
		g.setColor(Color.black);
        g2d.drawString(rightBoost.Display(), speedBoost2PosX ,speedBoost2PosY); //display
        // Main Car---------------------------------------------------------
        car = new ImageIcon("assets\\car.png");
        car.paintIcon(this, g2d, mainCarPosX, mainCarPosY);
        //Speed counter-----------------------------------------------------
        speedWindow = new ImageIcon("assets\\display.png");
        speedWindow.paintIcon(this, g2d, displaySpeedPosX, 550);
        g.setFont(new Font("Serif",Font.BOLD,20));
		g.setColor(Color.black);
        g2d.drawString(String.valueOf(displaySpeed) + " MPH" , displaySpeedPosX + 4, 582);
        // Score count-------------------------------------------------------
        g.setFont(new Font("Arial Rounded MT Bold",Font.BOLD,30));
		g.setColor(Color.black);
        g2d.drawString("Score " + String.valueOf(score), 15, 50);
        // If game over------------------------------------------------------
        if (gameover) {
            g2d.setColor(new Color(0,0,0));
            g2d.fillRect(100,100,500,500);
            g2d.setColor(new Color(255,255,255));
            g2d.fillRect(105,105,490,490);
            g.setFont(new Font("Serif",Font.BOLD,60));
            g.setColor(Color.black);
            g2d.drawString("GAME OVER !!" , 150,300); //
            g.setFont(new Font("Serif",Font.BOLD,50));
            g.setColor(Color.black);
            g2d.drawString("Press Esc to exit" , 170,380); // 
            g.setFont(new Font("Serif",Font.BOLD,30));
            g.setColor(Color.red);
            g2d.drawString("Yes there is no restart button" , 160,430); //
            timer.stop();
        }
    }
    //Keybind Method--------------------------------------------------------------------------------
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_LEFT) {
            mainCarPosX -= 200;
            displaySpeedPosX -= 200;
            if (mainCarPosX < 168) mainCarPosX = 168;
            if (displaySpeedPosX < 200) displaySpeedPosX = 200;
        }
        if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
            mainCarPosX += 200;
            displaySpeedPosX += 200;
            if (mainCarPosX > 368) mainCarPosX = 368;
            if (displaySpeedPosX > 400) displaySpeedPosX = 400;
        }
        if (e.getKeyCode()==KeyEvent.VK_ESCAPE) {
            System.exit(1);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {}
    //Game Dynamic Method----------------------------------------------------------------------------
    @Override
    public void actionPerformed(ActionEvent e) { //Perfromed an action then call repaint to call paint.
        // Move center line------------------------------------
        if (roadMove == 0) {
            roadMove = 1;
        }
        else if (roadMove == 1) {
            roadMove = 0;
        }
        // Paralax sky-----------------------------------------
        cloud1PosX += cloudVelocity1;
        if (cloud1PosX >= 0) cloudVelocity1 = cloudVelocity1 * (-1);
        if (cloud1PosX < -200) cloudVelocity1 = cloudVelocity1 * (-1);
        cloud2PosX -= cloudVelocity2;
        if (cloud2PosX >= 0) cloudVelocity2 = cloudVelocity2 * (-1);
        if (cloud2PosX < -200) cloudVelocity2 = cloudVelocity2 * (-1);
        // Side move-------------------------------------------
        int rand = random.nextInt(10) + 1;
        tree1PosY += sideVelocityY;
        tree1PosX = ProjectionLine.LeftSideLine(tree1PosY += sideVelocityY, cLeftSideLine);
        if (tree1PosY > 700 && rand == 1) {
            tree1PosY = 300;
            tree1PosX = 100;
        }
        tree2PosY += sideVelocityY;
        tree2PosX = ProjectionLine.RightSideLine(tree2PosY += sideVelocityY, cRightSideLine);
        if (tree2PosY > 700 && rand == 5) {
            tree2PosY = 200;
            tree2PosX = 520;
        }
        tree3PosY += sideVelocityY;
        tree3PosX = ProjectionLine.RightSideLine(tree3PosY += sideVelocityY, cRightSideLine);
        if (tree3PosY > 700 && rand == 7) {
            tree3PosY = 200;
            tree3PosX = 520;
        }
        stonePosY += sideVelocityY;
        stonePosX = ProjectionLine.LeftSideLine(stonePosY += sideVelocityY, 900);
        if (stonePosY > 700 && rand == 3) {
            stonePosY = 290;
            stonePosX = 52;
        }
        // Speed boost-----------------------------------------------
        speedBoost1PosY += sideVelocityY;
        speedBoost1PosX = ProjectionLine.LeftSideLine(speedBoost1PosY += sideVelocityY, cLeftCarLine);
        if (speedBoost1PosY > 1000) {
            leftBoost = new SpeedBoost();
            countL = 0;
            speedBoost1PosY = 335;
            speedBoost1PosX = 241;
        }
        speedBoost2PosY += sideVelocityY;
        speedBoost2PosX = ProjectionLine.RightSideLine(speedBoost2PosY += sideVelocityY, cRightCarLine);
        if (speedBoost2PosY > 1000) {
            rightBoost = new SpeedBoost();
            countR = 0;
            speedBoost2PosY = 335;
            speedBoost2PosX = 409;
        }
        //Speed counter-----------------------------------------------
        if (mainCarPosX == 168 && speedBoost1PosY >= mainCarPosY && speedBoost1PosY <= 700 && countL == 0 && !gameover) {
            displaySpeed = leftBoost.CalculateSpeed(displaySpeed);
            countL = 1;
            sideVelocityY = 20 + (displaySpeed/50) ; 
            if (sideVelocityY >= 50) sideVelocityY = 50;
        }
        if (mainCarPosX == 368 && speedBoost2PosY >= mainCarPosY && speedBoost2PosY <= 700 && countR == 0 && !gameover) {
            displaySpeed = rightBoost.CalculateSpeed(displaySpeed);
            countR = 1;
            sideVelocityY = 20 + (displaySpeed/50) ;
            if (sideVelocityY >= 50) sideVelocityY = 50;
        }
        //Game over-----------------------------------------------------
        if (displaySpeed <= 0) {
            gameover = true;
        }
        score++;
        repaint();
    }
    
}
