package minesweeper;
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.util.ArrayList;

public class Minefield_Panel extends JPanel{ 
    private Box[][] field = new Box[8][8]; //Beginner: 8x8 with 10 mines
    
    public Minefield_Panel(){
        
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        
    }
}
