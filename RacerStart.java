/*
Start of Program
*/
import javax.swing.*;
public class RacerStart extends JFrame
{
  // Declare objects
  public static void main(String[] args)
  {
      JFrame window = new JFrame();
      Racer r = new Racer();

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
      JButton stop = new JButton("STOP");
      JButton start = new JButton("START");
      JLabel scoreDisplay = new JLabel();

      scoreDisplay.setXPosition(11);
      scoreDisplay.setYPosition(11);
      scoreDisplay.setBounds(12)

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
