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
      int insertAt = -1;
       
      for(int i = 0; i < 10; i++) {
          if(Integer.parseInt(scoreLabel[i].getText()) < newScore) {
              insertAt = i;
              break;
          }
      }
     
      // we now know where the new score goes.
      if(insertAt != -1) {
          String tempSc = Integer.toString(newScore);
          String tempNm = tempN;

          for(int i = insertAt; i < 10; i++) {
              String tempSc2 = scoreLabel[i].getText();
              String tempNm2 = nameLabel[i].getText();

              scoreLabel[i].setText(tempSc);
              nameLabel[i].setText(tempNm);

              tempSc = tempSc2;
              tempNm = tempNm2;
          }
      }

    /*
      if(scoreAddIt == 0)
      {
          scoreLabel[9].setText(tempS);
          nameLabel[9].setText(tempN);
      {
      else
      {
          
      }
      
      scoreLabel[i]
      nameLabel[i]

            

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

            JLabel lblNameDisplay1 = new JLabel(highName[0]);
            JLabel lblNameDisplay2 = new JLabel(highName[1]);
            JLabel lblNameDisplay3 = new JLabel(highName[2]);
            JLabel lblNameDisplay4 = new JLabel(highName[3]);
            JLabel lblNameDisplay5 = new JLabel(highName[4]);
            JLabel lblNameDisplay6 = new JLabel(highName[5]);
            JLabel lblNameDisplay7 = new JLabel(highName[6]);
            JLabel lblNameDisplay8 = new JLabel(highName[7]);
            JLabel lblNameDisplay9 = new JLabel(highName[8]);
            JLabel lblNameDisplay10 = new JLabel(highName[9]);

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
      
            butSubmit.setBounds(200, 370, 80, 30);

            txtEnterScore.setBounds(12, 370, 120, 30);
      
            boardPanel.add(lblNameDisplay1);
            boardPanel.add(lblScoreDisplay1);
            boardPanel.add(lblNameDisplay2);
            boardPanel.add(lblScoreDisplay2);
            boardPanel.add(lblNameDisplay3);
            boardPanel.add(lblScoreDisplay3);
            boardPanel.add(lblNameDisplay4);
            boardPanel.add(lblScoreDisplay4);
            boardPanel.add(lblNameDisplay5);
            boardPanel.add(lblScoreDisplay5);
            boardPanel.add(lblNameDisplay6);
            boardPanel.add(lblScoreDisplay6);
            boardPanel.add(lblNameDisplay7);
            boardPanel.add(lblScoreDisplay7);
            boardPanel.add(lblNameDisplay8);
            boardPanel.add(lblScoreDisplay8);
            boardPanel.add(lblNameDisplay9);
            boardPanel.add(lblScoreDisplay9);
            boardPanel.add(lblNameDisplay10);
            boardPanel.add(lblScoreDisplay10); 
            boardPanel.add(txtEnterScore);
            boardPanel.add(butSubmit);

            leaderBoard.setTitle("LeaderBoard");
            leaderBoard.setSize(300, 450);
            leaderBoard.setContentPane(boardPanel);
            leaderBoard.setVisible(true);
            leaderBoard.setLayout(mainGrid);
            leaderBoard.setResizable(false);

          }
        }
      }
      */
  }
}
