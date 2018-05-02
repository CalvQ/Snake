import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.UIManager;

public class SnakePanel extends JPanel {
    private int[][] board = new int[30][40];
	private Snake s = new Snake(Snake.Direction.none, 15, 20);
	private Timer timer = new Timer(500,null);

	public static void main(String[] args) {//break at 47 minutes
		try {
			// Set System L&F
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		JFrame frame = new JFrame("Snake!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		SnakePanel sp = new SnakePanel();
		frame.add(sp);
		sp.setPreferredSize(new Dimension(800,600));
		sp.setBackground(Color.BLACK);
		frame.pack();
		frame.setVisible(true);
		sp.setUpKeyMappings();
		sp.startGame();
	}
	private void setUpKeyMappings() {

		this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),"left");
		this.getActionMap().put("left",new AbstractAction(){

			@Override
			public void actionPerformed(ActionEvent e) {
				s.changeDir(Snake.Direction.W);
			}
		});
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),"right");
        this.getActionMap().put("right",new AbstractAction(){

            @Override
            public void actionPerformed(ActionEvent e) {
                s.changeDir(Snake.Direction.E);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"),"up");
        this.getActionMap().put("up",new AbstractAction(){

            @Override
            public void actionPerformed(ActionEvent e) {
                s.changeDir(Snake.Direction.N);
            }
        });
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),"down");
        this.getActionMap().put("down",new AbstractAction(){

            @Override
            public void actionPerformed(ActionEvent e) {
                s.changeDir(Snake.Direction.S);
            }
        });
		this.requestFocusInWindow();
	}
	private void startGame() {
		timer.addActionListener(arg0 -> tick());
		timer.start();
		board[s.getX()][s.getY()] = s.getLength();
		genTreat();
		this.repaint();
	}
	@Override
    public void paintComponent(Graphics g){
	    super.paintComponent(g);
        drawGame(g);
    }

    private void move(){
	    s.move();
	    for(int r=0; r<board.length; r++){
	        for(int c=0; c<board[r].length; c++){
	            if(board[r][c]>0) {
                    board[r][c]--;
                }
            }
        }
        board[s.getX()][s.getY()] = s.getLength();
    }

    private void genTreat(){

    }

    private void drawGame(Graphics g){
	    g.setColor(Color.WHITE);
        for(int r=0; r<board.length; r++){
            for(int c=0; c<board[r].length; c++){
                if(board[r][c]>0){
                    g.fillOval(c*20, r*20, 20, 20);
                }
            }
        }
    }

	private void tick() {
		move();
        this.repaint();
	}
}