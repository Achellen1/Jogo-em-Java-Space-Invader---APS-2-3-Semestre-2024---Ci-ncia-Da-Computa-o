package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controles implements KeyListener{

	public boolean CimaP, BaixoP, DireitaP, EsquerdaP, EspaçoP, P0, P1, Enter; //teclas apertadas
	
	public void keyTyped(KeyEvent e) {
		
	}

	
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		
		if (code == KeyEvent.VK_W) {
			
			CimaP = true;
			
		}
	
		
		if (code == KeyEvent.VK_S) {
			
			BaixoP = true;
		}
		
		if (code == KeyEvent.VK_D) {
			
			DireitaP = true;
		}
		
		if (code == KeyEvent.VK_A) {
			
			EsquerdaP = true;
		}
		if (code == KeyEvent.VK_SPACE) {
			
			EspaçoP = true;
			
		}
		if (code == KeyEvent.VK_R) {
			P0 = true;
		}
		if (code == KeyEvent.VK_F) {
			P1 = true;
		}
		if (code == KeyEvent.VK_ENTER) {
			P1 = true;
		}
		
		
	}

	
	public void keyReleased(KeyEvent e) {
int code = e.getKeyCode();
		
		
		if (code == KeyEvent.VK_W) {
			
			CimaP = false;
		}
		
		if (code == KeyEvent.VK_S) {
			
			BaixoP = false;
		}
		
		if (code == KeyEvent.VK_D) {
			
			DireitaP = false;
		}
		
		if (code == KeyEvent.VK_A) {
			
			EsquerdaP = false;
		}
		if (code == KeyEvent.VK_SPACE) {
			
			EspaçoP = false;
		}
		if (code == KeyEvent.VK_R) {
			P0 = false;
		}
		if (code == KeyEvent.VK_F) {
			P1 = false;
		}
		if (code == KeyEvent.VK_ENTER) {
			P1 = false;
		}
	}
}
