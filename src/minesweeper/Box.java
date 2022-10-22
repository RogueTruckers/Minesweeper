package minesweeper;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

public class Box {
    private boolean bomb; //True if bomb
    private int number_of_bombs;
    private BufferedImage status;
    
    public Box(boolean b, int n){
        bomb = b;
        number_of_bombs = n;
    }
    
    public void nbombs(int n){
        if(!bomb){
            number_of_bombs = n;
        }else{
            number_of_bombs = -1;
        }
    }
    
    public int getBombs(){
        return number_of_bombs;
    }
    
    public boolean isBomb(){
        return bomb;
    }
    
    public void changer(){
        bomb = true;
    }
    
    public void update(){
        if ( bomb ) {
            try {
                status = ImageIO.read(new File("\\\\ghc-fs2\\2019\\s31011\\Documents\\NetBeansProjects\\Minesweeper\\src\\minesweeper\\mines9.png"));
            } catch (IOException e) {
                System.out.println ( e );
            }
        } else {
            try {
                status = ImageIO.read(new File("\\\\ghc-fs2\\2019\\s31011\\Documents\\NetBeansProjects\\Minesweeper\\src\\minesweeper\\mines0.png"));
            } catch (IOException e) {
                System.out.println ( e );
            }
        }
    }
}

/*
    if ( good ) {
            try {
                img = ImageIO.read(new File("M:\\Documents\\NetBeansProjects\\GUI Unit13\\src\\Program5/good.png"));
                img_width = 18;
                img_height = 18;
            } catch (IOException e) {
                System.out.println ( e );
            }
        } else {
            try {
                img = ImageIO.read(new File("M:\\Documents\\NetBeansProjects\\GUI Unit13\\src\\Program5v2/badBullet.png"));
                img_width = 30;
                img_height = 14;
            } catch (IOException e) {
                System.out.println ( e );
            }
        }
*/