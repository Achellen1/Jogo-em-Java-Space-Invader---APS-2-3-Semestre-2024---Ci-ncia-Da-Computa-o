package Main;

import java.awt.Color;	
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Painel extends JPanel implements Runnable {
	
	//configurações da tela
	final int tamanhogradeOrig = 16; // 16pix x 16pix, tamanho inimigos/player/cenário
	final int escala = 3; //escalona o tamanhograde pra acomapnhar resoluções atuais
	
	final int tamanhograde = tamanhogradeOrig * escala;// 16*3 = 48 x 48
	
	final int coluna = 20;
	final int linha = 15; //4:3 escala
	
	final int larguraTela = coluna * tamanhograde; // 960 pix
	final int alturaTela = linha * tamanhograde; // 720 pix
	int counter = 0;
	int Fps = 60;
	int countGanhou = 50;
	int counterAtir = 0;
	boolean atirando;
	int score = 0;
	String scoreS;
	boolean jogoStart = true;
	BufferedImage background;
	String jogoEst = "jogando"; //jogando, perdeu, ganhou
	boolean atingiuBorda = false;
	//instâncias
	alien[][] listaAliens = new alien[5][10];
	tiro t1 = new tiro();
	player p1 = new player();
	Controles controle1 = new Controles();
	Thread jogoThread; 
	
	
	public Painel() {
		 
		try {
	        background = ImageIO.read(getClass().getResourceAsStream("/background.png"));
	        
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		inicializarAliens();
		this.setPreferredSize(new Dimension(larguraTela, alturaTela));
		this.setDoubleBuffered(true);
		
		this.addKeyListener(controle1);
		this.setFocusable(true);
	}
	
	private void inicializarAliens() {
        int X = 240;
        int Y = 50;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                alien alieni = new alien();
                alieni.posX = X;
                alieni.posY = Y;
                listaAliens[i][j] = alieni;
                X += 50;
            }
            X = 240;
            Y += 50;
        }
    }
		
		
	public void startjogoThread() {
		jogoThread = new Thread(this); //instância da thread
		jogoThread.start();//chama o método run
	}
	public void run() {
		double drawinterval = 1000000000/Fps;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		//GameLoop: Basicamente, é o que faz os updates e reprints na tela. Cada ação do jogador ou mudanças do cenário/monstros
		while (jogoThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawinterval;
			lastTime = currentTime;
			if (delta >= 1) {
				//update
				update();
				//repaint
				repaint();
				delta--;
			}
			
			
			
		}
	}
	
	public void colisao() {
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 10; j++) {
				alien indiv = listaAliens[i][j];
				if (indiv.posX <= p1.playerX + tamanhograde && indiv.posY <= p1.playerY + tamanhograde && indiv.posX >= p1.playerX && indiv.posY >= p1.playerY) {
					jogoEst = "perdeu";
					
					
				}
				if (indiv.ativo == false) {
					continue;
				}
				if (t1.posX <= indiv.posX + indiv.largura && t1.posY <= indiv.posY + indiv.altura && t1.posX >= indiv.posX && t1.posY >= indiv.posY ) {
					indiv.ativo = false;
					indiv.aliens = null;
					indiv.posY = 1000;
					indiv.posX = 480;
					indiv.veloX = 0;
					atirando = false;
					t1.tiroI = null;
					t1.posX = 0;
					t1.posY = 0;
					countGanhou -= 1;
					score += 100;
					
				}
				
				
			}
		}
		
	    for (int i = 0; i < 5; i++) {
	        for (int j = 0; j < 10; j++) {
	            
	            
	            if (listaAliens[i][j].posX <= 0 || listaAliens[i][j].posX + tamanhograde >= larguraTela) {
	                atingiuBorda = true;
	            }
	        }
	    }

	    
	    if (atingiuBorda) {
	        for (int i = 0; i < 5; i++) {
	            for (int j = 0; j < 10; j++) {
	            	listaAliens[i][j].veloX *= -1.1;
	                listaAliens[i][j].posY += tamanhograde; 
	                atingiuBorda = false;
	            }
	        }
	    }
	        
	}
	
	
	public void update() {
		if (jogoEst == "jogando") {
			if (controle1.P0 == true) {
				jogoStart = true;
				System.out.println(jogoStart);
			}
			if (controle1.P1 == true) {
				jogoStart = false;
				System.out.println(jogoStart);
			}
			}
				        
			
			if(jogoStart == true) {
				 if (controle1.EsquerdaP == true && p1.playerX >= 0 ) {
						p1.playerX -= p1.playerVelo;
					}
					
					
					 if (controle1.DireitaP == true && p1.playerX <= larguraTela - 48) {
						p1.playerX += p1.playerVelo;
					}
					 if (controle1.EspaçoP == true && atirando == false) {
						 try {
						 t1.tiroI = ImageIO.read(getClass().getResourceAsStream("/tiro.jpg"));
					        
					    } catch (IOException e) {
					        e.printStackTrace();
					    }
						atirando = true;
						t1.posX = p1.playerX + 24;
						t1.posY = p1.playerY;
						
					  }
					 

					    // Atualiza a posição dos tiros dos aliens e dos aliens
					    for (int i = 0; i < 5; i++) {
					        for (int j = 0; j < 10; j++) {
					            listaAliens[i][j].posX += listaAliens[i][j].veloX;
					        }
					    }
	
				       
					 if (t1.posY <= 0) {
						 atirando = false;
					 }
					 
					 
					
				 }
		
					
					 counterAtir++;
					 if (jogoStart == true) {
						 counter++;
					 }
					
					 
					 if (counterAtir == 100) {
					     counterAtir = 0;
					 }
					 if (counter == 60) {
						 
						 counter = 0;
					 }
					 
					 if (countGanhou == 0) {
						 jogoEst = "ganhou";
					 }
					 colisao();
				
			}
		
	
	
	
			
		
	
	//desenha na tela
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(background, 0, 0, larguraTela, alturaTela, null);
		
		
			
		if (jogoEst == "jogando") {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 10; j++) {
					
					if (i < 3 && counter < 30){
						g2.drawImage(listaAliens[i][j].aliens, listaAliens[i][j].posX, listaAliens[i][j].posY,tamanhograde, tamanhograde, null);
						
					}
					if (i >= 3 && counter < 30) {
						g2.drawImage(listaAliens[i][j].aliens2, listaAliens[i][j].posX, listaAliens[i][j].posY,tamanhograde, tamanhograde, null);
						
					}
					if (i < 3 && counter >= 30){
						g2.drawImage(listaAliens[i][j].animAlien1, listaAliens[i][j].posX, listaAliens[i][j].posY,tamanhograde, tamanhograde, null);
						
						
					}
					if (i >= 3 && counter >= 30) {
						g2.drawImage(listaAliens[i][j].animAlien2, listaAliens[i][j].posX, listaAliens[i][j].posY,tamanhograde, tamanhograde, null);
						
					
					}
							
				}
				
			}
			
			
			if (atirando) {
	            g2.drawImage(t1.tiroI, t1.posX, t1.posY, 10, 10, null);
	            if(jogoStart == true) {
	            	t1.posY -= t1.VeloY;
	            }
	            
	        }
			
			
			g2.drawImage(p1.nave, p1.playerX, p1.playerY, tamanhograde, tamanhograde, null);
			scoreS = "score =" + String.valueOf(score);
			g2.setColor(java.awt.Color.white);
			g2.setFont(new Font("Arial", Font.BOLD, 20));
			g2.drawString(scoreS, larguraTela - 150, 20);
			if (jogoStart == true) {
				g2.setFont(new Font("Arial", Font.BOLD, 20));
				g2.drawString("APERTE F PARA PAUSAR", 48, 20);
			}
			else {
				g2.setFont(new Font("Arial", Font.BOLD, 20));
				g2.drawString("APERTE R PARA DESPAUSAR", 48, 20);
			}
			
			
			}
		
		else if(jogoEst == "ganhou") {
				g2.setColor(Color.white);
				g2.setFont(new Font("Arial", Font.BOLD, 50));
		        g2.drawString("PARABÉNS, VOCÊ GANHOU!", 150, alturaTela / 2);
		        g2.setFont(new Font("Arial", Font.BOLD, 30));
		        String ScoreF = "seu score é: " + String.valueOf(score);
		        g2.drawString(ScoreF, 360, (alturaTela/2) + 48);
			
		}
		else if(jogoEst == "perdeu") {
			g2.setColor(Color.white);
			g2.setFont(new Font("Arial", Font.BOLD, 50));
	        g2.drawString("GAME OVER", 325, alturaTela / 2);
	        g2.setFont(new Font("Arial", Font.BOLD, 30));
	        g2.drawString("OS ALIENS INVADIRAM A TERRA!!", 225, (alturaTela / 2) - 50);
	        String ScoreF = "seu score é: " + String.valueOf(score);
	        g2.setFont(new Font("Arial", Font.BOLD, 30));
	        g2.drawString(ScoreF, 360, (alturaTela/2) + 48);
		}
	}
	}