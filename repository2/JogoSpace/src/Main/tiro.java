package Main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class tiro {
	int posX;
	int posY;
	int VeloY = 10;
	boolean ativo;
	BufferedImage tiroI;
	final int tamanhograde = 48;
	public boolean colisao(alien indiv) {
	    return this.posX >= indiv.posX && this.posX <= indiv.posX + tamanhograde &&
	           this.posY >= indiv.posY && this.posY <= indiv.posY + tamanhograde;
	}
	public void reset() {
	    this.posY = -1;  // Define uma posição fora da tela
	}
}