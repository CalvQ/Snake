import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class SnakePanel extends JPanel {
    private int[][] board = new int[30][40];
	private Snake s = new Snake(Snake.Direction.N, 15, 20);
	private Timer timer = new Timer(200,null);
	private int[] treat;
	private boolean gg;

	public static void main(String[] args) {//FINISH at 1:23:35.46
		try {
			// Set System L&F
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		JFrame frame = new JFrame("Snake!");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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
	    if(s.getX() == treat[0] && s.getY() == treat[1]){
	        s.incLength();
	        treat =null;
	        timer.setDelay(205-s.getLength());
        }else {
            for (int r = 0; r < board.length; r++) {
                for (int c = 0; c < board[r].length; c++) {
                    if (board[r][c] > 0) {
                        board[r][c]--;
                    }
                }
            }
        }
        if(gameOver()){
            timer.stop();
            gg = true;
            return;
        }
        board[s.getX()][s.getY()] = s.getLength();
        if(treat == null){
            genTreat();
        }
    }

    private void genTreat(){
	    treat = new int[2];
		int number = (int) (Math.random()*(board.length-1)*board[0].length-1);
		System.out.println(number);
		while(board[number/board[0].length][number%board[0].length]!=0){
            number = (int) (Math.random()*(board.length-1)*board[0].length-1);
        }
        treat[0] = number/board[0].length;
		treat[1] = number%board[0].length;
    }

    private void drawGame(Graphics g){
	    if(!gg) {
            g.setColor(Color.WHITE);
        }else{
	        g.setColor(Color.RED);
        }
        for(int r=0; r<board.length; r++){
            for(int c=0; c<board[r].length; c++){
                if(board[r][c]>0){
                    g.fillOval(c*20, r*20, 20, 20);
                }
            }
        }
        if(treat==null){
            genTreat();
        }
        g.setColor(Color.GREEN);
        g.fillOval(treat[1] * 20, treat[0] * 20, 20, 20);
    }

    private boolean gameOver(){
	    if(s.getX()>board.length || s.getX()<0 || s.getY()<0 || s.getY()>board[0].length){
	        return true;
        }
        return board[s.getX()][s.getY()] > 0;
    }

	private void tick() {
		move();
        this.repaint();
	}
}