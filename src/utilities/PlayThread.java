package utilities;
import board.GameBoard;


public class PlayThread extends Thread{
	private GameBoard board;
	public PlayThread( GameBoard gb){
		board = gb;
	}

	@Override
	public void run() {
		while(!Thread.currentThread().interrupted()){
			
			try{
				if(board.isPlaying()){
					board.eachFrame();					
					//board.checkWinner(); //do this more frequently (in setSelected(false)
				}
				
				//Thread.sleep( (int)(1000.0/board.FPS) );
				Thread.sleep(10);
			}
			catch( InterruptedException ie){
				
			}
			catch(NullPointerException np){
				np.printStackTrace();				
			}
			catch(Exception ex){ex.printStackTrace();}
		}
	}

}
