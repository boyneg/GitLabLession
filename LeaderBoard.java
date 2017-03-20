/*
Start of Program
*/
import javax.swing.*;
import java.awt.event.*;
import javafx.embed.swing.JFXPanel;
public class LeaderBoard extends JFrame
{

  public static void boardSetup()
  {
      JFrame leaderBoard = new JFrame();
      LeaderBoard l = new LeaderBoard();

      JPanel boardPanel = new JPanel();

      leaderBoard.setTitle("LeaderBoard");
      leaderBoard.setSize(300, 450);
      leaderBoard.setContentPane(boardPanel);
      leaderBoard.setVisible(true);
      leaderBoard.setLayout(null);
      leaderBoard.setResizable(false);
      leaderBoard.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

      JButton butSubmit = new JButton("Submit");

      JTextField txtEnterScore = new JTextField("");

      JLabel lblScoreDisplay1 = new JLabel("0");
      JLabel lblScoreDisplay2 = new JLabel("0");
      JLabel lblScoreDisplay3 = new JLabel("0");
      JLabel lblScoreDisplay4 = new JLabel("0");
      JLabel lblScoreDisplay5 = new JLabel("0");
      JLabel lblScoreDisplay6 = new JLabel("0");
      JLabel lblScoreDisplay7 = new JLabel("0");
      JLabel lblScoreDisplay8 = new JLabel("0");
      JLabel lblScoreDisplay9 = new JLabel("0");
      JLabel lblScoreDisplay10 = new JLabel("0");

      lblScoreDisplay1.setBounds(170, 12, 90, 20);
      lblScoreDisplay2.setBounds(170, 44, 90, 20);
      lblScoreDisplay3.setBounds(170, 76, 90, 20);
      lblScoreDisplay4.setBounds(170, 108, 90, 20);
      lblScoreDisplay5.setBounds(170, 140, 90, 20);
      lblScoreDisplay6.setBounds(170, 172, 90, 20);
      lblScoreDisplay7.setBounds(170, 204, 90, 20);
      lblScoreDisplay8.setBounds(170, 236, 90, 20);
      lblScoreDisplay9.setBounds(170, 268, 90, 20);
      lblScoreDisplay10.setBounds(170, 300, 90, 20);

      JLabel lblNameDisplay1 = new JLabel("Player: 1");
      JLabel lblNameDisplay2 = new JLabel("Player: 2");
      JLabel lblNameDisplay3 = new JLabel("Player: 3");
      JLabel lblNameDisplay4 = new JLabel("Player: 4");
      JLabel lblNameDisplay5 = new JLabel("Player: 5");
      JLabel lblNameDisplay6 = new JLabel("Player: 6");
      JLabel lblNameDisplay7 = new JLabel("Player: 7");
      JLabel lblNameDisplay8 = new JLabel("Player: 8");
      JLabel lblNameDisplay9 = new JLabel("Player: 9");
      JLabel lblNameDisplay10 = new JLabel("Player: 10");

      lblNameDisplay1.setBounds(12, 12, 90, 20);
      lblNameDisplay2.setBounds(12, 44, 90, 20);
      lblNameDisplay3.setBounds(12, 76, 90, 20);
      lblNameDisplay4.setBounds(12, 108, 90, 20);
      lblNameDisplay5.setBounds(12, 140, 90, 20);
      lblNameDisplay6.setBounds(12, 172, 90, 20);
      lblNameDisplay7.setBounds(12, 204, 90, 20);
      lblNameDisplay8.setBounds(12, 236, 90, 20);
      lblNameDisplay9.setBounds(12, 268, 90, 20);
      lblNameDisplay10.setBounds(12, 300, 90, 20);
      
      butSubmit.setBounds(153, 887, 30, 10);

      txtEnterScore.setBounds(12, 880, 60, 20);
      

      leaderBoard.add(lblNameDisplay1);
      leaderBoard.add(lblNameDisplay2);
      leaderBoard.add(lblNameDisplay3);
      leaderBoard.add(lblNameDisplay4);
      leaderBoard.add(lblNameDisplay5);
      leaderBoard.add(lblNameDisplay6);
      leaderBoard.add(lblNameDisplay7);
      leaderBoard.add(lblNameDisplay8);
      leaderBoard.add(lblNameDisplay9);
      leaderBoard.add(lblNameDisplay10);
      leaderBoard.add(lblScoreDisplay1);
      leaderBoard.add(lblScoreDisplay2);
      leaderBoard.add(lblScoreDisplay3);
      leaderBoard.add(lblScoreDisplay4);
      leaderBoard.add(lblScoreDisplay5);
      leaderBoard.add(lblScoreDisplay6);
      leaderBoard.add(lblScoreDisplay7);
      leaderBoard.add(lblScoreDisplay8);
      leaderBoard.add(lblScoreDisplay9);
      leaderBoard.add(lblScoreDisplay10); 
      leaderBoard.add(butSubmit);
      leaderBoard.add(txtEnterScore);  

      butSubmit.addActionListener(e -> {
          // Sorting algorithm goes here
      });
  }
}
