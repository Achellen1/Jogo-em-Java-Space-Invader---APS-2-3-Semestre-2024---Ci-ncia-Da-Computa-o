package Main;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Menu extends JPanel {

    private ImageIcon imgMenu;
    private boolean startGame;

    public Menu() {
        setPreferredSize(new Dimension(960, 720)); // Tamanho do painel do menu


        imgMenu = new ImageIcon(getClass().getResource("/menujogo.png"));

     
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    startGame = true;
                }
            }
        });

        setFocusable(true); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Desenha a imagem do botão de iniciar
        g.drawImage(imgMenu.getImage(), 0, 0, 960, 720, null);
    }

    public boolean shouldStartGame() {
        return startGame;
    }
}
