package br.com.thiago.calc.visao;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BarraTituloMac extends JPanel {

    private JFrame frame;

    public BarraTituloMac(JFrame frame) {
        this.frame = frame;

        setLayout(null);
        setPreferredSize(new Dimension(0, 28));
        setBackground(new Color(30, 30, 30)); 

        criarBotoes();
        moverJanela();
    }

    private void criarBotoes() {

        BotaoMac close = new BotaoMac(new Color(255, 92, 92));
        close.setBounds(10, 8, 14, 14);
        close.addActionListener(e -> frame.dispose());
        add(close);

        BotaoMac minimize = new BotaoMac(new Color(255, 189, 68));
        minimize.setBounds(30, 8, 14, 14);
        minimize.addActionListener(e -> frame.setState(JFrame.ICONIFIED));
        add(minimize);

        BotaoMac maximize = new BotaoMac(new Color(0, 202, 78));
        maximize.setBounds(50, 8, 14, 14);
        maximize.addActionListener(e -> frame.setExtendedState(JFrame.MAXIMIZED_BOTH));
        add(maximize);
    }

    private int mouseX, mouseY;

    private void moverJanela() {

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                frame.setLocation(
                    e.getXOnScreen() - mouseX,
                    e.getYOnScreen() - mouseY
                );
            }
        });
    }

    private class BotaoMac extends JButton {

        private Color cor;

        public BotaoMac(Color cor) {
            this.cor = cor;
            setBorder(null);
            setFocusable(false);
            setContentAreaFilled(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(cor);
            g2.fillOval(0, 0, getWidth(), getHeight());
        }
    }
}
