import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
    private static boolean immune = true;
    private static int immuneTime = 300; //Immune to death for 5 seconds on game start
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
            // Create the player's car
            player = new Car(SCREEN_WIDTH/2, SCREEN_HEIGHT - 150, arena);

            // Create the initial road layout
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
            playSoundCrash();
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
            }
            /*
             * Temporary Immunity on game start
             */
            if(immuneTime >= 1)
            {
                immuneTime --;
                if(immuneTime <= 0)
                {
                    immune = false;
                }
            }

            score++;

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

            if (hasCrashed())
                stop();
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
    /*
     * Sound to be played when the player crashes
     */
    public void playSoundCrash()
    {
        try
        {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.getClass().getResource("boo.wav"));
            Clip mp3Clip = AudioSystem.getClip();
            mp3Clip.open(audioInputStream);
            mp3Clip.start();
        }
        catch(Exception e)
        {
            System.out.println("ERROR" + e);
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
    private boolean hasCrashed()
    {
        if(immune == false)
        {
            for (int i=0; i<road.length; i++)
            {
                if (player.isTouching(road[i]))
                return true;
            }

            return false;
        }
        return false;
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
