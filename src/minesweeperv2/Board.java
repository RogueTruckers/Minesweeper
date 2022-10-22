package minesweeperv2;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel{
    public Mines mines;
    
    private final int num_Img = 13; //# of images
    private final int cCover = 10; //Cover for every cell
    private final int cFlag = 11; //Flag the cell
    private final int cMine = 9; //Display the mine
    private final int cEmpty = 0; //No number or mine in the cell
    private final int cCoverBomb = 19; //
    private final int cMark = 20;
    private final int cX = 12;
    
    private final JLabel progress;
    private int cell_size; //side length of every square
    private int rows; //# of cols & rows
    private int bw; //width of the board
    private int bh; //height of the board
    private int totalNumber;
    
    private int nMines;// Total number of bombs
    private int nLeft; //number of bombs left
    private int[] field;
    private boolean inGame;
    private Image[] img;
        
    public Board(int w, int r, int m, JLabel progress, Mines f){
        mines = f;
        cell_size = w;
        rows = r;
        nMines = m;
        inGame = false;
        this.progress = progress;
        bw = rows * cell_size + 10;
        bh = (rows+2) * cell_size;
        starter();
    }
    
    public void starter(){
        mines.setBounds(0,0,bw,bh);//new Dimension(bw, bh)
        img = new Image[num_Img];
        for (int i = 0; i < num_Img; i++) {
            String path = "\\\\ghc-fs2\\2019\\s31011\\Documents\\NetBeansProjects\\Minesweeper\\src\\minesweeper\\mines" + i + ".png";
            img[i] = (new ImageIcon(path)).getImage();
        }
        addMouseListener(new MinesAdapter());
        newGame(); 
    }
    
    private void newGame() {
       
        int cell;
        
        Random random = new Random();
        inGame = true;
        nLeft = nMines;

        totalNumber = rows*rows;
        field = new int[totalNumber];
        
        for (int i = 0; i < totalNumber; i++) {
            field[i] = cCover;
        }

        progress.setText(Integer.toString(nMines));

        int i = 0;
        
        while (i < nLeft) {

            int position = (int) (totalNumber * random.nextDouble());

            if ((position < totalNumber) && (field[position] != cCoverBomb)) {

                int current_col = position % rows;
                field[position] = cCoverBomb;
                i++;

                if (current_col > 0) {
                    cell = position - 1 - rows;
                    if (cell >= 0) {
                        if (field[cell] != cCoverBomb) {
                            field[cell] += 1;
                        }
                    }
                    cell = position - 1;
                    if (cell >= 0) {
                        if (field[cell] != cCoverBomb) {
                            field[cell] += 1;
                        }
                    }

                    cell = position + rows - 1;
                    if (cell < totalNumber) {
                        if (field[cell] != cCoverBomb) {
                            field[cell] += 1;
                        }
                    }
                }

                cell = position - rows;
                if (cell >= 0) {
                    if (field[cell] != cCoverBomb) {
                        field[cell] += 1;
                    }
                }
                
                cell = position + rows;
                if (cell < totalNumber) {
                    if (field[cell] != cCoverBomb) {
                        field[cell] += 1;
                    }
                }

                if (current_col < (rows - 1)) {
                    cell = position - rows + 1;
                    if (cell >= 0) {
                        if (field[cell] != cCoverBomb) {
                            field[cell] += 1;
                        }
                    }
                    cell = position + rows + 1;
                    if (cell < totalNumber) {
                        if (field[cell] != cCoverBomb) {
                            field[cell] += 1;
                        }
                    }
                    cell = position + 1;
                    if (cell < totalNumber) {
                        if (field[cell] != cCoverBomb) {
                            field[cell] += 1;
                        }
                    }
                }
            }
        }
    }
    
    public void find_empty_cells(int j) {

        int current_col = j % rows;
        int cell;

        if (current_col > 0) {
            cell = j - rows - 1;
            if (cell >= 0) {
                if (field[cell] > cMine) {
                    field[cell] -= cCover;
                    if (field[cell] == cEmpty) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j - 1;
            if (cell >= 0) {
                if (field[cell] > cMine) {
                    field[cell] -= cCover;
                    if (field[cell] == cEmpty) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j + rows - 1;
            if (cell < totalNumber) {
                if (field[cell] > cMine) {
                    field[cell] -= cCover;
                    if (field[cell] == cEmpty) {
                        find_empty_cells(cell);
                    }
                }
            }
        }

        cell = j - rows;
        if (cell >= 0) {
            if (field[cell] > cMine) {
                field[cell] -= cCover;
                if (field[cell] == cEmpty) {
                    find_empty_cells(cell);
                }
            }
        }

        cell = j + rows;
        if (cell < totalNumber) {
            if (field[cell] > cMine) {
                field[cell] -= cCover;
                if (field[cell] == cEmpty) {
                    find_empty_cells(cell);
                }
            }
        }

        if (current_col < (rows - 1)) {
            cell = j - rows + 1;
            if (cell >= 0) {
                if (field[cell] > cMine) {
                    field[cell] -= cCover;
                    if (field[cell] == cEmpty) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j + rows + 1;
            if (cell < totalNumber) {
                if (field[cell] > cMine) {
                    field[cell] -= cCover;
                    if (field[cell] == cEmpty) {
                        find_empty_cells(cell);
                    }
                }
            }

            cell = j + 1;
            if (cell < totalNumber) {
                if (field[cell] > cMine) {
                    field[cell] -= cCover;
                    if (field[cell] == cEmpty) {
                        find_empty_cells(cell);
                    }
                }
            }
        }

    }
    
    @Override
    public void paintComponent(Graphics g) {

        int uncover = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < rows; j++) {

                int cell = field[(i * rows) + j];

                if (inGame && cell == cMine) {
                    inGame = false;
                    
                }

                if (!inGame) {
                    if (cell == cCoverBomb) {
                        cell = cMine;
                    } else if (cell == cMark) {
                        cell = cFlag;
                    } else if (cell > cCoverBomb) {
                        cell = cX;
                    } else if (cell > cMine) {
                        cell = cCover;
                    }

                } else {
                    if (cell > cCoverBomb) {
                        cell = cFlag;
                    } else if (cell > cMine) {
                        cell = cCover;
                        uncover++;
                    }
                }

                g.drawImage(img[cell], (j * cell_size),
                        (i * cell_size), this);
            }
        }

        if (uncover == 0 && inGame) {
            inGame = false;
            progress.setText("Game won");
        } else if (!inGame) {
            progress.setText("Game lost");
            //mines.gameStarter();
        }
    }
    
    private class MinesAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();

            int cCol = x / cell_size;
            int cRow = y / cell_size;

            boolean doRepaint = false;

            if (!inGame) {

                newGame();
                repaint();
            }

            if ((x < rows * cell_size) && (y < rows * cell_size)) {

                if (e.getButton() == MouseEvent.BUTTON3) {

                    if (field[(cRow * rows) + cCol] > cMine) {
                        doRepaint = true;

                        if (field[(cRow * rows) + cCol] <= cCoverBomb) {
                            if (nMines > 0) {
                                field[(cRow * rows) + cCol] += cCover;
                                nMines--;
                                String msg = Integer.toString(nMines);
                                progress.setText(msg);
                            } else {
                                progress.setText("No marks left");
                            }
                        } else {

                            field[(cRow * rows) + cCol] -= cCover;
                            nMines++;
                            String msg = Integer.toString(nMines);
                            progress.setText(msg);
                        }
                    }

                } else {

                    if (field[(cRow * rows) + cCol] > cCoverBomb) {
                        return;
                    }

                    if ((field[(cRow * rows) + cCol] > cMine)
                            && (field[(cRow * rows) + cCol] < cMark)) {

                        field[(cRow * rows) + cCol] -= cCover;
                        doRepaint = true;

                        if (field[(cRow * rows) + cCol] == cMine) {
                            inGame = false;
                        }
                        
                        if (field[(cRow * rows) + cCol] == cEmpty) {
                            find_empty_cells((cRow * rows) + cCol);
                        }
                    }
                }

                if (doRepaint) {
                    repaint();
                }

            }
        }
    }
}
