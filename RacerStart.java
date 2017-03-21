/*
Start of Program
*/
import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class RacerStart extends JFrame
{
    public static boolean gameFreaky = false;
    public static boolean buttonState = false;

    private boolean gameInProgress;

    public static void main(String[] args)
    {
      RacerStart rs = new RacerStart();
    }

    /*
     * Sound that is played only when the user crashes their car
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
    /*
     * Creates the interface for the game
     */
    public RacerStart()
    {
        int startCordX = 4;
        int stopCordX = startCordX;
        int stopCordY = Racer.SCREEN_HEIGHT - 81;
        int startCordY = stopCordY - 34;
        int freakyCordX = Racer.SCREEN_WIDTH - 142;
        int freakyCordY = stopCordY;

        LeaderBoard l = new LeaderBoard();
        JFrame window = new JFrame();
        Racer r = new Racer();

        window.setTitle("Totally not a racing game");
        window.setSize(Racer.SCREEN_WIDTH, Racer.SCREEN_HEIGHT);
        window.setContentPane(r.getPanel());
        window.setVisible(true);
        window.setLayout(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton butStop = new JButton("STOP");
        JButton butStart = new JButton("START");
        JButton butFreaky = new JButton("FREAKY");
        JLabel lblScoreDisplay = new JLabel(Integer.toString(r.getScore()));

        lblScoreDisplay.setBounds(12, 12, 30, 10);
        butStop.setBounds(stopCordX, stopCordY, 120, 30);
        butStart.setBounds(startCordX, startCordY, 120, 30);
        butFreaky.setBounds(freakyCordX, freakyCordY, 120, 30);

        window.add(butStop);
        window.add(butStart);
        window.add(butFreaky);
        window.add(lblScoreDisplay);

        /*
         * Action listeners for buttons
         */

        butFreaky.addActionListener(e -> {
            if(buttonState == false) // button looks normal
            {
                gameFreaky = true;
                butFreaky.setBorder(BorderFactory.createLoweredBevelBorder());
                buttonState = true;
            }
            else // button looks pressed
            {
                gameFreaky = false;
                butFreaky.setBorder(null);
                buttonState = false;
            }
        });


        // Start and stop buttons
        butStart.addActionListener(e -> {
            r.start();
        });

        butStop.addActionListener(e -> {
            r.stop();
        });

        gameInProgress = true;

        r.start();

        while(true)
        {
            if (r.hasCrashed() && gameInProgress)
            {
                r.stop();
                l.showBoard();

                playSoundCrash();

                gameInProgress = false;
            }
            else if(!r.hasCrashed())
            {
                gameInProgress = true;
            }
            // Update the score as the game progresses 
            lblScoreDisplay.setText(Integer.toString(r.getScore()));
            LeaderBoard.newScore = Integer.parseInt (lblScoreDisplay.getText());
            r.update();
        } 
    }
}