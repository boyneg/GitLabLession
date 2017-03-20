/*
Start of Program
*/
import javax.swing.*;
import java.awt.event.*;
public class RacerStart extends JFrame
{
  public static boolean gameBreak = false;
  public static void main(String[] args)
  {
      interfaceSetup();     
  }

  public static void interfaceSetup()
  {
      int startCordX = 4;
      int stopCordX = startCordX;
      int stopCordY = Racer.SCREEN_HEIGHT - 81;
      int startCordY = stopCordY - 34;
      int breakCordX = Racer.SCREEN_WIDTH - 142;
      int fixCordX = breakCordX;
      int breakCordY = stopCordY;
      int fixCordY = startCordY;

      JFrame window = new JFrame();
      Racer r = new Racer();

      window.setTitle("Totally not a racing game");
      window.setSize(Racer.SCREEN_WIDTH, Racer.SCREEN_HEIGHT);
      window.setContentPane(r.getPanel());
      window.setVisible(true);
      window.setLayout(null);

      JButton butStop = new JButton("STOP");
      JButton butStart = new JButton("START");
      JButton butFix = new JButton("FIX");
      JButton butBreak = new JButton("BREAK");
      JLabel lblScoreDisplay = new JLabel("0000");

      lblScoreDisplay.setBounds(12, 12, 30, 10);
      butStop.setBounds(stopCordX, stopCordY, 120, 30);
      butStart.setBounds(startCordX, startCordY, 120, 30);
      butFix.setBounds(fixCordX, fixCordY, 120, 30);
      butBreak.setBounds(breakCordX, breakCordY, 120, 30);

      window.add(butStop);
      window.add(butStart);
      window.add(butFix);
      window.add(butBreak);
      window.add(lblScoreDisplay);
      window.setTitle("Totally not a racing game");
      window.setSize(Racer.SCREEN_WIDTH, Racer.SCREEN_HEIGHT);
      window.setContentPane(r.getPanel());
      window.setVisible(true);
      window.setLayout(null);
      window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      butFix.addActionListener(e -> {
          gameBreak = false;
      });

      butBreak.addActionListener(e -> {
          gameBreak = true;
      });

      butStart.addActionListener(e -> {
          interfaceSetup();
      });

      butStop.addActionListener(e -> {
          r.stop();
      });

      r.start();

      while(r.isPlaying())
          r.update();
  }
}
/*
import java.awt.Color;

import javax.swing.*;
public class calculator {
	public static void main(String[] args)
	{
		JFrame frame = new JFrame("hi"); // Creates new instance of JFrame
		JButton button = new JButton("Click me"); // Creates new instance of JButton
		JLabel label = new JLabel(); // Create a new instance of JLabel
		JPanel pan = new JPanel(); // Creates a new instance of JPanel

		button.setBounds(130, 100, 100, 40); // X, Y, width, height

		label.setBounds(50, 50, 390, 100); // X, Y, width, height
		pan.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    lable.set



		frame.add(label); // adds label
		frame.add(button); // adds button
		frame.setSize(400, 500); // width, height
		frame.setLayout(null); // no layer managers
		frame.setVisible(true); // makes frame visible

	}
  */
