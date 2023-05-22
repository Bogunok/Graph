import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Project: Graph.java
 * File: Design.java
 * Головний клас
 * Програма малює графік функцій R1(φ) = A sin (B φ) та R2(φ) = A (1+cos(φ))
 * φ є [0; 360]
 * A задається; B задається; кут φ задається
 */
public class Design extends JFrame implements ActionListener {
    private GraphPanel graphPanel;
    private JPanel userInput;
    private JLabel graph1 = new JLabel("Графік 1: R1(φ) = A sin (B φ)");
    private JLabel graph2 = new JLabel("Графік 2: R2(φ) = A (1+cos(φ))");
    private JLabel angle = new JLabel("Кут: φ є [0; 360]");
    private JButton setDimensions = new JButton("Намалювати графік");
    private JButton saveImage = new JButton("Зберегти графік");
    private JLabel enterA = new JLabel("Уведіть А:");
    private JLabel enterB = new JLabel("Уведіть B:");
    private JLabel enterInitialAngle = new JLabel("Уведіть початковий кут φ (в градусах):");
    private JLabel enterEndingAngle = new JLabel("Уведіть кінцевий кут φ (в градусах):");
    private JLabel enterStep = new JLabel("Уведіть крок:");
    private JTextField inputInitialAngle = new JTextField();
    private JTextField inputEndingAngle = new JTextField();
    private JTextField inputA = new JTextField();
    private JTextField inputB = new JTextField();
    private JTextField inputStep = new JTextField();
    private double A;
    private double B;
    private double alpha;
    private double beta;
    private double step;
    private int counter = 1;

    /**
     * Конструктор класу Design
     * Будова рамки з двома панелями, а саме: панель, де користувач має змогу задати дані, необхідні для побудови графіку
     * та власне панель, на якій малюється графік
     */
    public Design(){
        super("Графік №24");
        this.setSize(1200,630);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1,2));
        graphPanel = new GraphPanel(0,0,0,0,1);
        userInput = new JPanel();
        userInput.setLayout(new GridLayout(9,2));
        userInput.add(graph1);
        userInput.add(new JLabel());
        userInput.add(graph2);
        userInput.add(new JLabel());
        userInput.add(angle);
        userInput.add(new JLabel());
        userInput.add(enterA);
        userInput.add(inputA);
        userInput.add(enterB);
        userInput.add(inputB);
        userInput.add(enterInitialAngle);
        userInput.add(inputInitialAngle);
        userInput.add(enterEndingAngle);
        userInput.add(inputEndingAngle);
        userInput.add(enterStep);
        userInput.add(inputStep);
        userInput.add(saveImage);
        userInput.add(setDimensions);
        saveImage.addActionListener(this);
        setDimensions.addActionListener(this);
        getContentPane().add(graphPanel);
        getContentPane().add(userInput);
        this.setVisible(true);
    }

    /**
     * Метод, який визначає, які дії відбуваються при натисканні на ту чи іншу кнопку
     * Також передбачає інформування користувача у разі введення некоректних даних
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Намалювати графік")) {
            try {
                A = Double.valueOf(inputA.getText());
                B = Double.valueOf(inputB.getText());
                alpha = Double.valueOf(inputInitialAngle.getText());
                beta = Double.valueOf(inputEndingAngle.getText());
                step = Double.valueOf(inputStep.getText());
                if (beta < alpha) {
                    JOptionPane.showMessageDialog(this, "Увага! Кінцевий кут менший за початковий!", "ПОМИЛКА", JOptionPane.ERROR_MESSAGE);
                }else{
                    if(step<=0){
                        JOptionPane.showMessageDialog(this,"Крок не може бути від'ємним","ПОМИЛКА", JOptionPane.ERROR_MESSAGE);
                    }else {
                        graphPanel = new GraphPanel(A, B, alpha, beta, step);
                        getContentPane().remove(0);
                        getContentPane().add(graphPanel, 0);
                        this.revalidate();
                        this.repaint();
                    }
                }
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(this, "Одне з введених значень некоректне або є незаповнене поле", "ПОМИЛКА", JOptionPane.ERROR_MESSAGE);
            }
        }else if(e.getActionCommand().equals("Зберегти графік")){
            BufferedImage bi = new BufferedImage(graphPanel.getSize().width, graphPanel.getSize().height, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            graphPanel.paint(g);
            g.dispose();
            try{
                ImageIO.write(bi,"png",new File("graph"+counter+".png"));
                counter++;
                JOptionPane.showMessageDialog(this,"Графік успішно збережено!");
            }catch (Exception ex) {
                System.out.println("ПОМИЛКА");
            }
        }
    }

    /**
     * Метод для запуску програми
     * @param args
     */
    public static void main(String[]args){
        Design design = new Design();
    }
}