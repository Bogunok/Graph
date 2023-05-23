import java.awt.*;
import java.awt.geom.*;

/**
 * Клас GraphPanel.java у якому малюються вісі координат X та Y та сам графік,
 * у залежності від значень, які введе користувач
 */
public class GraphPanel extends Panel {
    private double A;
    private double B;
    private double alpha;
    private double beta;
    private double step;

    /**
     * Конструктор класу GraphPanel
     * @param A параметр А
     * @param B параметр В
     * @param alpha початковий кут
     * @param beta кінцевий кут
     */
    public GraphPanel(double A, double B, double alpha, double beta, double step){
        this.A = A;
        this.B = B;
        this.alpha = alpha;
        this.beta = beta;
        this.step = step;
        this.setLayout(null);
    }

    /**
     * Метод, у якому малюється графік
     * @param g the specified Graphics window
     */
    public void paint(Graphics g){

        Graphics2D g2 = (Graphics2D) g;

        for(int i=1; i<=14;i++){
            g2.setFont(new Font("Times New Roman",Font.PLAIN,8));
            g2.drawString((i*this.A/20)+"π",295+20*i,315);
        }

        for(int i=1; i<=14;i++){
            g2.setFont(new Font("Times New Roman",Font.PLAIN,8));
            g2.drawString(("-"+i*this.A/20)+"π",295-20*i,315);
        }

        for(int i=1; i<=14;i++){
            g2.setFont(new Font("Times New Roman",Font.PLAIN,8));
            g2.drawString(("-"+i*this.A/20)+"π",305,303+20*i);
        }

        for(int i=1; i<=14;i++){
            g2.setFont(new Font("Times New Roman",Font.PLAIN,8));
            g2.drawString((i*this.A/20)+"π",305,303-20*i);
        }

        g2.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        g2.drawString("R1(φ) = A sin (B φ)", 460, 470);
        g2.drawString("R2(φ) = A (1+cos(φ))", 460, 490);
        g2.drawString("A ="+this.A, 460, 510);
        g2.drawString("B ="+this.B, 460, 530);
        g2.drawString("Діапазон: "+this.alpha+" - "+this.beta, 460, 550);
        g2.drawString("Крок: "+this.step, 460, 570);

        g2.setFont(new Font("Times New Roman",Font.BOLD,10));
        g2.drawString("X",580,285);
        g2.drawString("Y",286,20);

        Line2D axisX = new Line2D.Float(0, 300, 600, 300);
        Line2D arrow11 = new Line2D.Float(583,295,591,300);
        Line2D arrow12 = new Line2D.Float(583,305,591,300);
        Line2D axisY = new Line2D.Float(300, 0, 300, 600);
        Line2D arrow21 = new Line2D.Float(295,10,300,2);
        Line2D arrow22 = new Line2D.Float(305,10,300,2);
        Line2D border = new Line2D.Float(592, 0, 592, 600);

        g2.draw(axisX);
        g2.draw(arrow11);
        g2.draw(arrow12);
        g2.draw(axisY);
        g2.draw(arrow21);
        g2.draw(arrow22);

        for(int i = 1; i<=30; i++){
            Line2D linesOnAxisX = new Line2D.Float(i*20,297,i*20,303);
            g2.draw(linesOnAxisX);
        }
        for(int i=1;i<=30;i++){
            Line2D linesOnAxisY = new Line2D.Float(297,i*20,303,i*20);
            g2.draw(linesOnAxisY);
        }
        g2.setStroke(new BasicStroke(4f));
        g2.draw(border);

        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(2f));

        for(double i=(Math.PI*step/180)+this.alpha/180*Math.PI; i<= this.beta/180*Math.PI; i=i+Math.PI*step/180){
            double x1 = (this.A*Math.sin(this.B*(i-Math.PI*step/180))*Math.cos(i-Math.PI*step/180))*400/(Math.PI*Math.abs(this.A))+300;
            double y1 = 300-((this.A*Math.sin(this.B*(i-Math.PI*step/180))*Math.sin(i-Math.PI*step/180))*400/(Math.PI*Math.abs(this.A)));
            double x2 = (this.A*Math.sin(this.B*i)*Math.cos(i))*400/(Math.PI*Math.abs(this.A))+300;
            double y2 = 300-((this.A*Math.sin(this.B*i)*Math.sin(i))*400/(Math.PI*Math.abs(this.A)));
            Line2D flower = new Line2D.Double(x1,y1,x2,y2);
            g2.draw(flower);
        }

        g2.setColor(Color.GREEN);
        g2.setStroke(new BasicStroke(2f));

        for(double i=(Math.PI*step/180)+this.alpha/180*Math.PI; i<= this.beta/180*Math.PI; i=i+Math.PI*step/180){
            double x1 = (this.A*(1+Math.cos(i-Math.PI*step/180))*Math.cos(i-Math.PI*step/180))*400/(Math.PI*Math.abs(this.A))+300;
            double y1 = 300-((this.A*(1+Math.cos(i-Math.PI*step/180))*Math.sin(i-Math.PI*step/180))*400/(Math.PI*Math.abs(this.A)));
            double x2 = (this.A*(1+Math.cos(i))*Math.cos(i))*400/(Math.PI*Math.abs(this.A))+300;
            double y2 = 300-((this.A*(1+Math.cos(i))*Math.sin(i))*400/(Math.PI*Math.abs(this.A)));
            Line2D cardioid = new Line2D.Double(x1,y1,x2,y2);
            g2.draw(cardioid);
        }
    }
}
