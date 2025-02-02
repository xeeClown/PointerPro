package PointerPro;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PointerXY extends JFrame
{
    private JTextField inputField;
    private DrawingPanel drawingPanel;

    public PointerXY()
    {
        setTitle("Pointer XY");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputField = new JTextField();
        drawingPanel = new DrawingPanel();
        drawingPanel.setPreferredSize(new Dimension(400, 400));

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String edu = inputField.getText().trim();
                int X = parsingX(edu);
                int Y = parsingY(edu);
                drawingPanel.setCoordinates(X, Y);
                drawingPanel.repaint();
            }
        });

        add(inputField, BorderLayout.NORTH);
        add(drawingPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);
    }

    public static int parsingX(String str)
    {
        try {
            int index = str.indexOf("," , 0);
            String str1 = str.substring(1 , index);
            int X = Integer.parseInt(str1);
            return X;
        } catch (Exception e) {
            return 0; // Default value or handle error appropriately
        }
    }

    public static int parsingY(String str)
    {
        try {
            int index = str.indexOf("," , 0);
            String str2 = str.substring(index + 1, str.length() - 1);
            int Y = Integer.parseInt(str2);
            return Y;
        } catch (Exception e) {
            return 0; // Default value or handle error appropriately
        }
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PointerXY().setVisible(true);
            }
        });
    }

    class DrawingPanel extends JPanel
    {
        private int x = 0;
        private int y = 0;

        public void setCoordinates(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            int width = getWidth();
            int height = getHeight();
            int centerX = width / 2;
            int centerY = height / 2;

            // Draw the axes
            g.drawLine(centerX, 0, centerX, height);
            g.drawLine(0, centerY, width, centerY);

            // Draw the point
            int pointX = centerX + x * 10; // Scale the coordinates for better visibility
            int pointY = centerY - y * 10; // Invert Y-axis for correct orientation
            g.fillOval(pointX - 2, pointY - 2, 4, 4);
        }
    }
}