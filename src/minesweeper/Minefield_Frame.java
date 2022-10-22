package minesweeper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

public class Minefield_Frame extends JFrame{
    private Game g;
    private int row, col;
    
    public Minefield_Frame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE); //To close the panel
        setTitle("Minesweeper"); //The title of the window
        setResizable(false); //Prevent the user from resizing the window
        
        g = new Game(8,8);
        g.gameSetup();
        row = 0;
        col = 0;
        
        JPanel jp = new JPanel();
        jp.setPreferredSize(new Dimension(520, 520));
        jp.setLayout(new GridLayout(8,8)); //Creates the grid. (Row, Column)
        
        ActionListener clicked = new ButtonListener(); //add an button listener to each button
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                //System.out.println(g.getBombs(row, col));
                JButton temp = new JButton(Integer.toString(g.getBombs(i, j)));
                temp.addActionListener(clicked);
                jp.add(temp);
            }
        }
        /*
        for(int i = 1; i<65; i++){
            if(col>8){
                col = 0;
                row++;
            }
            
        }
        */
        getContentPane().add( jp );
        pack();
    }
    private class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            JButton temp = (JButton)e.getSource();
            System.out.println(temp.getText());
        }
    }
    
    public static void main(String[] args){
        Minefield_Frame mf = new Minefield_Frame();
        mf.display();
    }
    
    public void display() {
        EventQueue.invokeLater(new Runnable() {
                public void run() {
                    setVisible(true);
                }
            });
    }
}
