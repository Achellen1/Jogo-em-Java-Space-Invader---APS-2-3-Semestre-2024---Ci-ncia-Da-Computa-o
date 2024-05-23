package Main;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Space Invader Type");

        Menu menu = new Menu();
        window.add(menu);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        while (!menu.shouldStartGame()) {
            try {
                Thread.sleep(100); // Espera um pouco antes de verificar novamente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Remover o menu e adicionar o painel do jogo
        window.getContentPane().removeAll();
        Painel painel = new Painel();
        window.add(painel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        // Garante que o painel do jogo receba o foco para eventos de teclado
        painel.requestFocusInWindow();

        painel.startjogoThread();
    }
}
