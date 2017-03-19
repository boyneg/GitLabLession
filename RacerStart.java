/*
Start of Program
*/
import javax.swing.*;
public class RacerStart extends JFrame
{
  // Declare objects
  public static void main(String[] args)
  {
      interfaceSetup();     
  }

  public static void interfaceSetup()
  {
      int startCordX = ((Racer.SCREEN_WIDTH / 2) / 2);
      int stopCordX = startCordX + (Racer.SCREEN_WIDTH / 2);
      int startCordY = Racer.SCREEN_HEIGHT - 82;
      int stopCordY = Racer.SCREEN_HEIGHT - 82;

      System.out.println(startCordX);
      System.out.println(startCordY);
      System.out.println(stopCordX);
      System.out.println(stopCordY);

      JFrame window = new JFrame();
      Racer r = new Racer();

      window.setTitle("Totally not a racing game");
      window.setSize(Racer.SCREEN_WIDTH, Racer.SCREEN_HEIGHT);
      window.setContentPane(r.getPanel());
      window.setVisible(true);
      window.setLayout(null);

      JButton stop = new JButton("STOP");
      JButton start = new JButton("START");
      JLabel scoreDisplay = new JLabel("0000");

      scoreDisplay.setBounds(12, 12, 30, 10);
      stop.setBounds(stopCordX, stopCordY, 120, 30);
      start.setBounds(startCordX, startCordY, 120, 30);

      window.add(stop);
      window.add(start);
      window.add(scoreDisplay);
      window.setTitle("Totally not a racing game");
      window.setSize(Racer.SCREEN_WIDTH, Racer.SCREEN_HEIGHT);
      window.setContentPane(r.getPanel());
      window.setVisible(true);
      window.setLayout(null);

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
