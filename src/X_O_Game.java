import javax.swing.*;
import java.awt.*;

public class X_O_Game {
    public static void main (String[] args){
        JFrame frame = new JFrame("X vs O");
        frame.setContentPane(new xoForm().getGameField());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setMinimumSize(new Dimension(550,400));
        frame.setMaximumSize(new Dimension(550,400));
        frame.setVisible(true);
    }

}
