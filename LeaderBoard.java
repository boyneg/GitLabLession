/*
Start of Program
*/
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javafx.embed.swing.JFXPanel;

public class LeaderBoard extends JFrame
{
  public static int tempS;
  public static String tempN;
  public static int newScore;
  public int scoreAddIt = 0;
  JFrame leaderBoard;
  JLabel[] scoreLabel = new JLabel[10];
  JLabel[] nameLabel = new JLabel[10];

  /*
   * Creates the initial leaderboard 
   * with all its componenets
   */
  public LeaderBoard()
  {
      leaderBoard = new JFrame();
      JPanel boardPanel = new JPanel();
      JButton butSubmit = new JButton("Submit");
      JTextField txtEnterScore = new JTextField("");
      GridLayout mainGrid = new GridLayout(11,2);


      for(int i = 0; i < scoreLabel.length; i++)
      {
          scoreLabel[i] = new JLabel("0");
      }
      for(int i = 0; i < nameLabel.length; i++)
      {
          nameLabel[i] = new JLabel("Player " + i + ":");
      }

      for(int i = 0; i < scoreLabel.length; i++)
      {
        boardPanel.add(nameLabel[i]);
        boardPanel.add(scoreLabel[i]);
      }

      boardPanel.add(txtEnterScore);
      boardPanel.add(butSubmit);

      leaderBoard.setTitle("LeaderBoard");
      leaderBoard.setSize(300, 450);
      leaderBoard.setContentPane(boardPanel);
      leaderBoard.setLayout(mainGrid);
      leaderBoard.setResizable(false);
      leaderBoard.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

      butSubmit.addActionListener(e -> {
        tempN = txtEnterScore.getText();
        tempS = newScore;
        sortScore();
    });
  }

  public void showBoard()
  {
    leaderBoard.setVisible(true);
  }

  public void sortScore()
  {
      int insertAt = -1; // be default assuming there is no where to place score until a place has been found
       
      // Go through each score to check if the new score is bigger than any of them
      // if a place is found get the insertion point
      for(int i = 0; i < 10; i++) {
          if(Integer.parseInt(scoreLabel[i].getText()) < newScore) {
              insertAt = i;
              break;
          }
      }
     
      // Check is a psace has been found, if so shuffle the scores and place in the new score
      if(insertAt != -1) {
          String tempSc = Integer.toString(newScore);
          String tempNm = tempN;
          // Shuffle the other scores down
          for(int i = insertAt; i < 10; i++) {
              String tempSc2 = scoreLabel[i].getText();
              String tempNm2 = nameLabel[i].getText();

              scoreLabel[i].setText(tempSc);
              nameLabel[i].setText(tempNm);

              tempSc = tempSc2;
              tempNm = tempNm2;
          }
      }
  }
}
