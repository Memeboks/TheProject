package com.bot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Point> points = new ArrayList<Point>();
    public static void createGUI() {
        final JFrame frame = new JFrame("Best program ever");
	    frame.setPreferredSize(new Dimension(700,700));
	    JPanel panel = new JPanel(new BorderLayout());
        final Panel butPanel = new Panel();
        butPanel.setLayout(null);
        butPanel.setPreferredSize(new Dimension(250,700));
        final Panel pointpane   = new Panel();
        pointpane.setLayout(null);
        //pointpane.setPreferredSize(new Dimension(350,700));

	    JLabel addPointwithCoords = new JLabel("Добавить точку по координатам");
	    addPointwithCoords.setBounds(2,2,300,25);
	    butPanel.add(addPointwithCoords);
	    JLabel addRandomPoints = new JLabel("Добавить рандомное количество точек");
	    addRandomPoints.setBounds(2,50,300,25);
	    butPanel.add(addRandomPoints);
        JLabel X = new JLabel("X:");
        X.setBounds(2,25,15,25);
        butPanel.add(X);
        JLabel Y = new JLabel("Y:");
        Y.setBounds(45,25,15,25);
        butPanel.add(Y);
        JLabel N = new JLabel("NUM:");
        N.setBounds(2,70,30,25);
        butPanel.add(N);
        final JTextField x = new JTextField();
        x.setBounds(17,25, 25,25);
        butPanel.add(x);
        final JTextField y = new JTextField();
        y.setBounds(60,25, 25,25);
        butPanel.add(y);
        final JTextField n = new JTextField();
        n.setBounds(35,70,25,25);
        butPanel.add(n);


        JLabel addMainPoint = new JLabel("Добавить основную точку по координатам");
        addMainPoint.setBounds(2,95,300,25);
        butPanel.add(addMainPoint);
        JLabel XMain = new JLabel("X:");
        XMain.setBounds(2,117,15,25);
        butPanel.add(XMain);
        JLabel YMain = new JLabel("Y:");
        YMain.setBounds(45,117,15,25);
        butPanel.add(YMain);
        final JTextField xMain = new JTextField();
        xMain.setBounds(17,115, 25,25);
        butPanel.add(xMain);
        final JTextField yMain = new JTextField();
        yMain.setBounds(60,115, 25,25);
        butPanel.add(yMain);



        JButton button1 = new JButton("Добавить точку");
        button1.setBounds(2,200,160,40);
        butPanel.add(button1);
        button1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int X = (!x.getText().equals("")?Integer.parseInt(x.getText()):0);
                int Y= (!y.getText().equals("")?Integer.parseInt(y.getText()):0);
                int N = (!n.getText().equals("")?Integer.parseInt(n.getText()):0);
                if ((X>0)&&(Y>0)) {
                    Point b = new Point(X, Y);
                    points.add(b);
                    b.setBounds(b.x,b.y,b.x+3,b.y+3);
                    pointpane.add(b);
                    pointpane.revalidate();
                    pointpane.repaint();
                }
                else {
                    if (N>0){
                        for (int i=0;i<N;i++){
                            Point b = new Point((int)(Math.random()*(frame.getWidth()-250)), (int)(Math.random()*frame.getHeight()));
                            points.add(b);
                            b.setBounds(b.x,b.y,b.x+3,b.y+3);
                            pointpane.add(b);
                            pointpane.revalidate();
                            pointpane.repaint();
                        }
                    }
                }

            }
        });
        JButton button2 = new JButton("очистить");
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i=0;i<points.size();i++){
                    while(points.size() > 0) {
                        int index = points.size() - 1;
                        Point point = points.remove(index);
                        pointpane.remove(point);
                        pointpane.repaint();
                        pointpane.revalidate();
                    }
                }
            }
        });
        button2.setBounds(2,250,160,40);
        butPanel.add(button2);
        panel.add(pointpane,BorderLayout.CENTER);
        panel.add(butPanel,BorderLayout.EAST);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JButton button3 = new JButton("Выполнить");
        button3.setBounds(2,300,160,40);
        butPanel.add(button3);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Scanner sc = new Scanner(System.in);
                int cnt = 0; // определяет количество троек;
                double  d [] = new double[2]; //Здесь хранятся координаты точки d: d_x = d[0]; d_y = d[1];
                double ad;//Коэфициент наклона AD;
                double bc;//Коэфициент наклона BC;
                double ads;//смещение прямой на которой лежит отрезок AD по оси х от начала координат;
                double bcs;//смещение прямой на которой лежит отрезок BC по оси х от начала координат;
                int A = points.size();
                double x[] = new double[A];// Координаты_x каждой точки из множества A
                double y[] = new double[A];//Координаты_y каждой точки из множества A
                for (int i = 0; i < A; i++) {
                    x[i] = points.get(i).x;
                    y[i] = points.get(i).y;
                } //Вводим координаты точек из множества A
                d[0] = 100;//sc.nextDouble();// вводим координаты d
                d[1] = 100;//sc.nextDouble();// Ввели все данные, приступим к основному алгоритму:
                for (int i = 0; i <= A - 3; i++) // A-3 т.к. ожидаем еще точки b и с. То есть всего А-3 шага;
                {
                    for (int j = i+1; j <= A - 1; j++) // Начинаем с i+1 до A-1, то есть всего A-2 шага
                    {
                        for (int k = j+1; k <= A - 1; k++)//А-3 шага
                        {
                            //B этом цикле перебираются значения (всевозможные) для точек a,b,c. Также, заметим ,что у нас не могут эти точки совпадать.;
                            int m=0;
                            //a[0]=x[i] a[1]=y[i] - то есть координата точки а_х = x[i]; а_y = y[i];
                            //b[0]=x[j] b[1]=y[j] аналогично
                            //c[0]=x[k] c[1]=y[k]
                            //d[0] и  d[1] смотри в var
                            if ((Math.abs(x[i]-d[0])==Math.abs(x[j]-x[k])) && (Math.abs(y[i]-d[1])==Math.abs(y[j]-y[k])))
                            {
                                // Здесь была проверка на равенство проекций AD_x и BC_x, также равенство проекций на _y; Необходимое условие;
                                ad=(y[i]-d[1])/(x[i]-d[0]); // Вычисояем коэфициент наклона отрезка AD;
                                bc=(y[j]-y[k])/(x[j]-x[k]);// аналогично
                                ads=(x[i]*d[1]-y[i]*d[0])/(x[i]-d[0]);// вычисляем смещение прямой на которой лежит отрезок AD по оси х от начала координат;
                                bcs=(x[j]*y[k]-y[j]*x[k])/(x[j]-x[k]);// аналогично
                                if ((ad==bc) && (ads!= bcs)) // сначала проверяем их на параллельность по угловым коэффициентам, а потом на то не лежат ли отрезки на одной прямой
                                    m++; // вспомагательный счетчик
                            }
                            if (m==0)
                            {
                                //a[0]=x[j] a[1]=y[j]
                                //b[0]=x[i] b[1]=y[i]
                                //c[0]=x[k] c[1]=y[k]
                                //d[0] и d[1] смотри в var
                                if ((Math.abs(x[j]-d[0])==Math.abs(x[i]-x[k])) && (Math.abs(y[j]-d[1])==Math.abs(y[i]-y[k]))) //для тех же точек из А меняем местами координаты a и b (Так как
                                {
                                    ad=(y[j]-d[1])/(x[j]-d[0]);
                                    bc=(y[i]-y[k])/(x[i]-x[k]);
                                    ads=(x[j]*d[1]-y[j]*d[0])/(x[j]-d[0]);
                                    bcs=(x[i]*y[k]-y[i]*x[k])/(x[i]-x[k]);
                                    if ((ad==bc) && (ads!= bcs))
                                        cnt++;
                                }
                            }
                            else cnt++;
                        }
                    }
                }//вызываем нашу проверку всего 2 раза, вместо C из 3 по 2 ,так как только 2 точки из 3 образуют сторону, а последняя диагональ. А значит, если AD_1 и AD_2 не подходят, то это в любои случае не параллелограмм.
                if (cnt != 0)
                    System.out.print("Количество троек:" +cnt+"");
                else
                    System.out.print("Троек нет");
                JLabel addPointwithCoords = new JLabel("Ответ: " + (cnt == 0 ? "Троек нет" : cnt));
                addPointwithCoords.setBounds(2,350,300,25);
                butPanel.add(addPointwithCoords);
                butPanel.repaint();
                butPanel.revalidate();
            }
        });
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                createGUI();
            }
        });
    }
}
