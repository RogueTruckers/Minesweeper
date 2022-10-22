package minesweeper;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Game extends JPanel{
    private boolean playing = true;
    private Box[][] field;
    private Box box;
    private int bombs;
    private int rows;
    private int cols;
    
    public Game(int x, int y){
        field = new Box[x][y];
        rows = x;
        cols = y;
    }
    
    public void gameSetup(){
        for(int row = 0; row<field.length; row++){
            for(int col = 0; col<field[0].length; col++){
                field[row][col] = new Box(false, 0);
            }
        }
        for(int i = 0; i<10; i++){
            int row = (int)(Math. random() * rows);
            int col = (int)(Math. random() * cols);
            if(field[row][col].isBomb()){
                i--;
            }else{
               field[row][col].changer();
            }
        }
        /*int bombs = 10; 
        for(int row = 0; row<field.length; row++){
            for(int col = 0; col<field[0].length; col++){
                double random = Math.random();//(int)(Math. random() * 10 + 1);
                if(random < .5 && bombs>0){
                    field[row][col] = new Box(true, 0);
                    bombs--;
                }else{
                    field[row][col] = new Box(false, 0);
                }
            }
        }
        */
        bombChecker();
        /*
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field[0].length; j++) {
                    if(field[i][j].isBomb()){
                        System.out.print("9 -- ");
                    }else{
                        System.out.print(field[i][j].getBombs() + " -- ");
                    }
                }
                System.out.println();
            }
        */
        repaint();
    }
    
    public void bombChecker(){
        for(int r = 0; r<field.length; r++){
            for(int c = 0; c<field[0].length; c++){
                int bombs = 0;
                if(!field[r][c].isBomb()){ //Check to see if it is a bomb
                    if((r>0 && r<field.length-1) && (c>0 && c<field[0].length-1)){ //Box in middle
                        if(field[r-1][c-1].isBomb())
                            bombs++;
                        if(field[r-1][c].isBomb())
                            bombs++;
                        if(field[r-1][c+1].isBomb())
                            bombs++;
                        if(field[r][c-1].isBomb())
                            bombs++;
                        if(field[r][c+1].isBomb())
                            bombs++;
                        if(field[r+1][c-1].isBomb())
                            bombs++;
                        if(field[r+1][c].isBomb())
                            bombs++;
                        if(field[r+1][c+1].isBomb())
                            bombs++;
                    }else if(r==0){
                        if (c>0 && c<field[0].length-1) {
                            if(field[r][c-1].isBomb())
                                bombs++;
                            if(field[r][c+1].isBomb())
                                bombs++;
                            if(field[r+1][c-1].isBomb())
                                bombs++;
                            if(field[r+1][c].isBomb())
                                bombs++;
                            if(field[r+1][c+1].isBomb())
                                bombs++;
                        }else if (c==0){
                            if(field[r][c+1].isBomb())
                                bombs++;
                            if(field[r+1][c].isBomb())
                                bombs++;
                            if(field[r+1][c+1].isBomb())
                                bombs++;
                        }else{
                            if(field[r][c-1].isBomb())
                                bombs++;
                            if(field[r][c-1].isBomb())
                                bombs++;
                            if(field[r+1][c-1].isBomb())
                                bombs++;
                            if(field[r+1][c].isBomb())
                                bombs++;
                        }
                    }else if(r==field.length-1){
                        if (c>0 && c<field[0].length-1) {
                            if(field[r][c-1].isBomb())
                                bombs++;
                            if(field[r][c+1].isBomb())
                                bombs++;
                            if(field[r-1][c-1].isBomb())
                                bombs++;
                            if(field[r-1][c].isBomb())
                                bombs++;
                            if(field[r-1][c+1].isBomb())
                                bombs++;
                        }else if (c==0){
                            if(field[r][c+1].isBomb())
                                bombs++;
                            if(field[r-1][c].isBomb())
                                bombs++;
                            if(field[r-1][c+1].isBomb())
                                bombs++;
                        }else{
                            if(field[r][c-1].isBomb())
                                bombs++;
                            if(field[r-1][c].isBomb())
                                bombs++;
                            if(field[r-1][c-1].isBomb())
                                bombs++;
                        }
                    }
                }else{
                    bombs = -1;
                }
                field[r][c].nbombs(bombs);
            }
        }
    }
    
    public int getBombs(int x, int y){
        /*for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                System.out.print(field[i][j].getBombs() + " -- ");
            }
            System.out.println();
        }
        System.out.println("\n \n \n");
        */
        return field[x][y].getBombs();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        System.out.println("Sup");
    }
}

/*
Check every box around
Check that box is not on r>0 && r<field.length-1;
Check that box is not on c>0 && c<field.length[0]-1;
Check that box is top left corner;
Check that box is top right corner;
check that box is bottom left corner;
Check tht box is bottom right corner;

for(int row = 0; row<field.length; row++){
            for(int col = 0; col<field[0].length; col++){
                field[row][col].update();
            }
        }
*/
