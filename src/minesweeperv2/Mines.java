package minesweeperv2;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Mines extends JFrame{
    JPanel jp = new JPanel();
    private JLabel progress;
    
    public Mines() { 
        setTitle("Minesweeper");
        setLocationRelativeTo(null);
        jp.setPreferredSize(new Dimension(120, 100));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        ActionListener clicked = new ButtonListener(); //add an button listener to each button
        
        JButton easy = new JButton("Easy");
        setBounds(10, 10, 80, 30);
        easy.addActionListener(clicked);
        jp.add(easy);
        JButton med = new JButton("Intermediate");
        setBounds(10, 50, 80, 30);
        med.addActionListener(clicked);
        jp.add(med);
        JButton hard = new JButton("Hard");
        setBounds(10, 90, 80, 30);
        hard.addActionListener(clicked);
        jp.add(hard);
        
        gameStarter();
        
        getContentPane().add(jp);
        setResizable(true);
        pack();
    }
    
    public void boardCreator(int rows, int mines){
        progress = new JLabel("");
        add(new Board(25, rows, mines, progress, this)); //Cell size (don't touch), Rows & Cols, # of mines
        add(progress, BorderLayout.SOUTH);
    }
    
    public void gameStarter(){
        jp.setVisible(true);
    }
    
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            JButton temp = (JButton)e.getSource();
            String level = temp.getText();
            if(level == "Easy"){
                boardCreator(8, 10);
                //jp.setVisible(false);
            }else if(level == "Intermediate"){
                boardCreator(16, 40);
                //jp.setVisible(false);
            }else{
                boardCreator(22, 99);
                //jp.setVisible(false);
            }
            jp.setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Mines runner = new Mines();
            runner.setVisible(true);
        });
    }
}
