import javax.swing.*;

/**
 * uses the GameArena APIs to implement a simple top down racing game.
 *
 * The graphical output of the game is provided as a Swing component,
 * so that it can be added into any Swing application, just like a JButton etc.
 *
 * To allow users to control the game as they see fit, start(), stop() and update()
 * methods are provided. start() should be used to create a new game, stop() to terminate
 * a running game, and update() should be called in a loop to update gameplay and graphics
 *
 * Simple example of use:
 *
 * <pre>
 *
 *  JFrame window = new JFrame();
 *  Racer r = new Racer();
 *  window.setTitle("Racer");
 *  window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
 *  window.setContentPane(r.getPanel());
 *  window.setVisible(true);
 *
 *  r.start();
 *
 *  while(r.isPlaying())
 *      r.update();
 *
 * </pre>
 *
 * @author Joe Finney (joe@comp.lancs.ac.uk)
 */
public class Racer
{
    public static final double PLAYER_SPEED = 5;
    public static final int ROAD_SEGMENT_WIDTH = 160;
    public static final int ROAD_SEGMENT_HEIGHT= 10;
    public static final int ROAD_CURVE_SPEED = 7;
    public static final int SCREEN_WIDTH = 1200;
    public static final int SCREEN_HEIGHT = 900;

    private GameArena arena;
    private Car player;
    private RoadSegment[] road = new RoadSegment[SCREEN_HEIGHT / ROAD_SEGMENT_HEIGHT + 1];

    private double currentRoadX = SCREEN_WIDTH/2;
    private double speed = 5;
    private boolean playing = false;
    private int score = 0;

    public static double randomMovement;
    private static int frameCount;
    private static int moveStage;
    private static int frameFreakyCount;
    private static int freakyChance;
    public boolean tempImmunity = true;
    public int tempImmunityTimer = 180;

    
    /**
     * Creates a new instance of the Racer racing game.
     */
    public Racer()
    {
        arena = new GameArena(SCREEN_WIDTH, SCREEN_HEIGHT, false);
    }

    /**
     * Provides a Swing component in which the Racer game runs.
     * This component can be added to a Swing panel to display the game on screen.
     *
     * @return A Swing component for this game.
     */
    public JComponent getPanel()
    {
        return arena.getPanel();
    }

    /**
     * Provides the player's current score in the game.
     * @return The player's current score.
     */
    public int getScore()
    {
        return score;
    }

    /**
     * Starts a new game, if the game is not alreayd running.
     */
    public void start()
    {
        if(!playing)
        {
            tempImmunityTimer = 180;
            tempImmunity = true;
            // Create the player's car
            player = new Car(SCREEN_WIDTH/2, SCREEN_HEIGHT - 150, arena);

            // Create the initial road layout
            currentRoadX = SCREEN_WIDTH/2;

            for (int s = road.length-1; s >= 0 ; s--)
            {
                road[s] = nextRoadSegment();
                road[s].setYPosition(s*ROAD_SEGMENT_HEIGHT);
            }

            score = 0;
            playing = true;
        }
    }

    /**
     * Stops a currently running game.
     */
    public void stop()
    {
        if(playing)
        {
            playing = false;
            arena.exit();
        }
    }

    /**
     * Determines if the game is currently being played.
     *
     * @return false if the game has not been started or on game over, true if the game is actively running.
     */
    public boolean isPlaying()
    {
        return playing;
    }

    /**
     * Updates the game state to allow the road and player character to move on the screen.
     *
     * This method should be called in a loop (once per frame) to advance gameplay in response to time
     * and user input. The method uses the GameArena pause() method to ensure the game runs at a constant speed.
     */
    public void update()
    {
        if(playing)
        {

            /*
             * Special feature activates
             */
            if(RacerStart.gameFreaky == true)
            {
                randomMovement();
                needMoreColour();
            }

            if(tempImmunity == false)
            {
                score++;
            }

            if(tempImmunityTimer > 0)
            {
                tempImmunityTimer --;
                if(tempImmunityTimer <= 0)
                {
                    tempImmunity = false;
                }
            }

            double speed = 0;
            if (arena.leftPressed())
                speed -= PLAYER_SPEED;

            if (arena.rightPressed())
                speed += PLAYER_SPEED;

            player.setXSpeed(speed);

            player.move();
            for (int i=0; i<road.length; i++)
            {
                if (road[i] != null)
                    road[i].move();
            }

            // Recycle any segments that have crolled off screen...
            recycleRoadSegments();
        }

        arena.pause();
    }
    /*
     * Controls the movement of the rectangles
     */
    public void randomMovement()
    {
        frameCount += 1;
        if(frameCount > 30 && moveStage == 0) // Increase random movement
        {
            frameCount = 0;
            randomMovement += 0.1;
            if(randomMovement > 1.3)
            {
                moveStage = 1;
            }
        }
        else if(frameCount > 30 && moveStage == 1) // Decrease random movement
        {
            frameCount = 0;
            randomMovement -= 0.1;
            if(randomMovement < 0.1)
            {
                moveStage = 0;
            }
        }
    }

    /**
     * Provides a randomly generated, thin slice of road.
     * This method is used periodically to create new road on the screen in front of the player's car.
     *
     * @return A new randomly generated RoadSegment
     */
    private RoadSegment nextRoadSegment()
    {
        currentRoadX += Math.random() * 2 * ROAD_CURVE_SPEED - ROAD_CURVE_SPEED;
        RoadSegment s = new RoadSegment(currentRoadX, -ROAD_SEGMENT_HEIGHT, ROAD_SEGMENT_WIDTH, ROAD_SEGMENT_HEIGHT, arena);
        s.setYSpeed(speed);
        return s;
    }

    /**
     * Removes any parts of road that have scrolled off the bottom of the screen.
     */
    private void recycleRoadSegments()
    {
        for (int i=0; i<road.length; i++)
        {
            if (road[i].getYPosition() > SCREEN_HEIGHT)
            {
                double y = road[i].getYPosition();
                road[i].remove();
                road[i] = nextRoadSegment();
                road[i].setYPosition(y - SCREEN_HEIGHT - ROAD_SEGMENT_HEIGHT);
            }
        }
    }

    /**
     * Determines if the player has crased (driven off road)
     *
     * @return true is the player is touching the kerb/grass, false otherwise.
     */
    public boolean hasCrashed()
    {
        for (int i=0; i<road.length; i++)
        {
            if(tempImmunity == false)
            {
                if (player.isTouching(road[i]))
                return true;
            } 
        }

        return false;
    }

    /**
     * Randomises colour like an LSD effect
     */
    public static void needMoreColour()
    {
        String[] colour = new String[25];

        colour[0] = "#ff0000";
        colour[1] = "#ff2e00";
        colour[2] = "#ff5400";
        colour[3] = "#ffc300";
        colour[4] = "#ffee00";
        colour[5] = "#99ff00";
        colour[6] = "#4cff00";
        colour[7] = "#00ff37";
        colour[8] = "#00ffa1";
        colour[9] = "#00f2ff";
        colour[10] = "#00c7ff";
        colour[11] = "#00a1ff";
        colour[12] = "#006eff";
        colour[13] = "#0055ff";
        colour[14] = "#0015ff";
        colour[15] = "#1d00ff";
        colour[16] = "#5d00ff";
        colour[17] = "#cb00ff";
        colour[18] = "#ff00fa";
        colour[19] = "#ff00c7";
        colour[20] = "#ff009d";
        colour[21] = "#ff004c";
        colour[22] = "#00ff55";
        colour[23] = "#00ff83";
        colour[24] = "#00ff19";

        int roughColour = (int)Math.floor(Math.random() * 25);
        int kerbColour = (int)Math.floor(Math.random() * 25);

        RoadSegment.ROUGH_COLOUR = colour[roughColour];
        RoadSegment.KERB_COLOUR = colour[kerbColour];
  }

    /**
     * A simple example of usage
     *
     * @param args unused.
     */
    public static void main(String[] args)
    {
        JFrame window = new JFrame();
        Racer r = new Racer();

        window.setTitle("Racer");
        window.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        window.setContentPane(r.getPanel());
        window.setVisible(true);

        r.start();

        while(r.isPlaying())
            r.update();
    }
}
