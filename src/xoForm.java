import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class xoForm {
    private static final boolean PRINT_LOG_ENABLE = true;
//----------------------------
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JRadioButton vsCompRadioButton;
    private JRadioButton networkRadioButton;
    private JRadioButton localRadioButton;
    private JTextArea textArea1;
    private JButton newButton;
    private JButton stepBackButton;
    private JButton stepForwdButton;
    private JTextField textField1;
    private JRadioButton xRadioButton;
    private JRadioButton oRadioButton;
    private JButton connectToServerButton;
    private JButton startServerButton;
    private JPanel gameField;
    private JButton stopServerButton;
    private JRadioButton netClientRadioButton;
    private JRadioButton netServerRadioButton;
    private JButton startButton;
    //-------------------------------------
    private JButton[]  buttons = new JButton[]{button1, button2, button3, button4, button5, button6, button7, button8, button9};
    private String x_or_o_Comp;
    private String x_or_o_User;
    private int gameType = 0;
    private String endGameString;

    public xoForm() {
        localRadioButton.setSelected(true);
        netClientRadioButton.setEnabled(false);
        netServerRadioButton.setEnabled(false);
        connectToServerButton.setEnabled(false);
        startServerButton.setEnabled(false);
        stopServerButton.setEnabled(false);
        textField1.setEnabled(false);
        endGameString = "";
        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // New Game prepare
                gameType = 0;
                localRadioButton.setSelected(true);
                netClientRadioButton.setEnabled(false);
                netServerRadioButton.setEnabled(false);
                connectToServerButton.setEnabled(false);
                startServerButton.setEnabled(false);
                stopServerButton.setEnabled(false);
                networkRadioButton.setEnabled(true);
                vsCompRadioButton.setEnabled(true);
                stepBackButton.setEnabled(true);
                stepForwdButton.setEnabled(true);
                xRadioButton.setEnabled(true);
                oRadioButton.setEnabled(true);
                endGameString = "";
                for (JButton b : buttons){
                    b.setText("*");
                    b.setEnabled(true);
                }
                printLog("Подготовка к новой игре");
            }
        });
        startServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                netClientRadioButton.setEnabled(false);
                startServerButton.setEnabled(false);
                stopServerButton.setEnabled(true);
                vsCompRadioButton.setEnabled(false);
                localRadioButton.setEnabled(false);
                networkRadioButton.setEnabled(false);
            }
        });
        stopServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                netClientRadioButton.setEnabled(true);
                startServerButton.setEnabled(true);
                stopServerButton.setEnabled(false);
                vsCompRadioButton.setEnabled(true);
                localRadioButton.setEnabled(true);
                networkRadioButton.setEnabled(true);
            }
        });
        connectToServerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                netServerRadioButton.setEnabled(false);
                vsCompRadioButton.setEnabled(false);
                localRadioButton.setEnabled(false);
                networkRadioButton.setEnabled(false);
                connectToServerButton.setEnabled(false);
            }
        });
        vsCompRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                gameType = 1;
                netClientRadioButton.setEnabled(false);
                netServerRadioButton.setEnabled(false);
                connectToServerButton.setEnabled(false);
                startServerButton.setEnabled(false);
                stopServerButton.setEnabled(false);
                textField1.setEnabled(false);
                stepBackButton.setEnabled(true);
                stepForwdButton.setEnabled(true);

            }
        });
        networkRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameType = 2;
                stepBackButton.setEnabled(false);
                stepForwdButton.setEnabled(false);
                netClientRadioButton.setEnabled(true);
                netServerRadioButton.setEnabled(true);
                if (netClientRadioButton.isSelected())
                    netClientRadioButton.doClick();
                else
                    netServerRadioButton.doClick();
            }
        });
        localRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameType = 0;
                stepBackButton.setEnabled(true);
                stepForwdButton.setEnabled(true);
                netClientRadioButton.setEnabled(false);
                netServerRadioButton.setEnabled(false);
                connectToServerButton.setEnabled(false);
                startServerButton.setEnabled(false);
                stopServerButton.setEnabled(false);
                textField1.setEnabled(false);
            }
        });
        xRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                oRadioButton.setEnabled(false);
                printLog("Выбран X");
                x_or_o_User = "x";
                x_or_o_Comp = "o";
            }
        });
        oRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                xRadioButton.setEnabled(false);
                printLog("Выбран O");
                x_or_o_User = "o";
                x_or_o_Comp = "x";
            }
        });
        stepBackButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        printLog("Step back");
            }
        });
        stepForwdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printLog("Step forward");
            }
        });

        for (final JButton b : buttons){
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setTextToButton(b,x_or_o_User);

                }
            });
        }

        netServerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServerButton.setEnabled(false);
                startServerButton.setEnabled(true);
                stopServerButton.setEnabled(false);
                textField1.setEnabled(false);
            }
        });
        netClientRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connectToServerButton.setEnabled(true);
                startServerButton.setEnabled(false);
                stopServerButton.setEnabled(false);
                textField1.setText("127.0.0.1");
                textField1.setEnabled(true);

            }
        });
    }

    public JPanel getGameField() {
        return gameField;
    }

    private JButton compChoiseNextCell(){
        //123,456,789,147,258,369,159,357 = ╨▓╤Л╨╣╨│╤А╤Л╤И

        if (button1.getText().equals(x_or_o_Comp) && button2.getText().equals(x_or_o_Comp) && button3.isEnabled()) return button3;
        if (button1.getText().equals(x_or_o_Comp) && button3.getText().equals(x_or_o_Comp) && button2.isEnabled()) return button2;
        if (button2.getText().equals(x_or_o_Comp) && button3.getText().equals(x_or_o_Comp) && button1.isEnabled()) return button1;

        if (button4.getText().equals(x_or_o_Comp) && button5.getText().equals(x_or_o_Comp) && button6.isEnabled()) return button6;
        if (button4.getText().equals(x_or_o_Comp) && button6.getText().equals(x_or_o_Comp) && button5.isEnabled()) return button5;
        if (button5.getText().equals(x_or_o_Comp) && button6.getText().equals(x_or_o_Comp) && button4.isEnabled()) return button4;

        if (button7.getText().equals(x_or_o_Comp) && button8.getText().equals(x_or_o_Comp) && button9.isEnabled()) return button9;
        if (button7.getText().equals(x_or_o_Comp) && button9.getText().equals(x_or_o_Comp) && button8.isEnabled()) return button8;
        if (button9.getText().equals(x_or_o_Comp) && button8.getText().equals(x_or_o_Comp) && button7.isEnabled()) return button7;

        if (button1.getText().equals(x_or_o_Comp) && button4.getText().equals(x_or_o_Comp) && button7.isEnabled()) return button7;
        if (button1.getText().equals(x_or_o_Comp) && button7.getText().equals(x_or_o_Comp) && button4.isEnabled()) return button4;
        if (button7.getText().equals(x_or_o_Comp) && button4.getText().equals(x_or_o_Comp) && button1.isEnabled()) return button1;

        if (button2.getText().equals(x_or_o_Comp) && button5.getText().equals(x_or_o_Comp) && button8.isEnabled()) return button8;
        if (button2.getText().equals(x_or_o_Comp) && button8.getText().equals(x_or_o_Comp) && button5.isEnabled()) return button5;
        if (button8.getText().equals(x_or_o_Comp) && button5.getText().equals(x_or_o_Comp) && button2.isEnabled()) return button2;

        if (button3.getText().equals(x_or_o_Comp) && button6.getText().equals(x_or_o_Comp) && button9.isEnabled()) return button9;
        if (button3.getText().equals(x_or_o_Comp) && button9.getText().equals(x_or_o_Comp) && button6.isEnabled()) return button6;
        if (button9.getText().equals(x_or_o_Comp) && button6.getText().equals(x_or_o_Comp) && button3.isEnabled()) return button3;

        if (button1.getText().equals(x_or_o_Comp) && button5.getText().equals(x_or_o_Comp) && button9.isEnabled()) return button9;
        if (button1.getText().equals(x_or_o_Comp) && button9.getText().equals(x_or_o_Comp) && button5.isEnabled()) return button5;
        if (button9.getText().equals(x_or_o_Comp) && button5.getText().equals(x_or_o_Comp) && button1.isEnabled()) return button1;

        if (button3.getText().equals(x_or_o_Comp) && button5.getText().equals(x_or_o_Comp) && button7.isEnabled()) return button7;
        if (button3.getText().equals(x_or_o_Comp) && button7.getText().equals(x_or_o_Comp) && button5.isEnabled()) return button5;
        if (button7.getText().equals(x_or_o_Comp) && button5.getText().equals(x_or_o_Comp) && button3.isEnabled()) return button3;
        //------------
        if (button1.getText().equals(x_or_o_User) && button2.getText().equals(x_or_o_User) && button3.isEnabled()) return button3;
        if (button1.getText().equals(x_or_o_User) && button3.getText().equals(x_or_o_User) && button2.isEnabled()) return button2;
        if (button2.getText().equals(x_or_o_User) && button3.getText().equals(x_or_o_User) && button1.isEnabled()) return button1;

        if (button4.getText().equals(x_or_o_User) && button5.getText().equals(x_or_o_User) && button6.isEnabled()) return button6;
        if (button4.getText().equals(x_or_o_User) && button6.getText().equals(x_or_o_User) && button5.isEnabled()) return button5;
        if (button5.getText().equals(x_or_o_User) && button6.getText().equals(x_or_o_User) && button4.isEnabled()) return button4;

        if (button7.getText().equals(x_or_o_User) && button8.getText().equals(x_or_o_User) && button9.isEnabled()) return button9;
        if (button7.getText().equals(x_or_o_User) && button9.getText().equals(x_or_o_User) && button8.isEnabled()) return button8;
        if (button9.getText().equals(x_or_o_User) && button8.getText().equals(x_or_o_User) && button7.isEnabled()) return button7;

        if (button1.getText().equals(x_or_o_User) && button4.getText().equals(x_or_o_User) && button7.isEnabled()) return button7;
        if (button1.getText().equals(x_or_o_User) && button7.getText().equals(x_or_o_User) && button4.isEnabled()) return button4;
        if (button7.getText().equals(x_or_o_User) && button4.getText().equals(x_or_o_User) && button1.isEnabled()) return button1;

        if (button2.getText().equals(x_or_o_User) && button5.getText().equals(x_or_o_User) && button8.isEnabled()) return button8;
        if (button2.getText().equals(x_or_o_User) && button8.getText().equals(x_or_o_User) && button5.isEnabled()) return button5;
        if (button8.getText().equals(x_or_o_User) && button5.getText().equals(x_or_o_User) && button2.isEnabled()) return button2;

        if (button3.getText().equals(x_or_o_User) && button6.getText().equals(x_or_o_User) && button9.isEnabled()) return button9;
        if (button3.getText().equals(x_or_o_User) && button9.getText().equals(x_or_o_User) && button6.isEnabled()) return button6;
        if (button9.getText().equals(x_or_o_User) && button6.getText().equals(x_or_o_User) && button3.isEnabled()) return button3;

        if (button1.getText().equals(x_or_o_User) && button5.getText().equals(x_or_o_User) && button9.isEnabled()) return button9;
        if (button1.getText().equals(x_or_o_User) && button9.getText().equals(x_or_o_User) && button5.isEnabled()) return button5;
        if (button9.getText().equals(x_or_o_User) && button5.getText().equals(x_or_o_User) && button1.isEnabled()) return button1;

        if (button3.getText().equals(x_or_o_User) && button5.getText().equals(x_or_o_User) && button7.isEnabled()) return button7;
        if (button3.getText().equals(x_or_o_User) && button7.getText().equals(x_or_o_User) && button5.isEnabled()) return button5;
        if (button7.getText().equals(x_or_o_User) && button5.getText().equals(x_or_o_User) && button3.isEnabled()) return button3;

        if (button1.getText().equals(x_or_o_User) && button9.getText().equals(x_or_o_User) && button2.isEnabled()) return button2;
        if (button1.getText().equals(x_or_o_User) && button9.getText().equals(x_or_o_User) && button4.isEnabled()) return button4;

        if (button3.getText().equals(x_or_o_User) && button7.getText().equals(x_or_o_User) && button2.isEnabled()) return button2;
        if (button3.getText().equals(x_or_o_User) && button7.getText().equals(x_or_o_User) && button6.isEnabled()) return button6;

        if (button5.isEnabled()) return button5;
        if (button1.isEnabled()) return button1;
        if (button3.isEnabled()) return button3;
        if (button7.isEnabled()) return button7;
        if (button9.isEnabled()) return button9;
        if (button2.isEnabled()) return button2;
        if (button4.isEnabled()) return button4;
        if (button6.isEnabled()) return button6;
        if (button8.isEnabled()) return button8;



        return null;
    }

    public void stopGame(){
        for (JButton b : buttons){
            if ( b.isEnabled() ) b.setEnabled(false);
        }
    }

    private Boolean checkEndGame(String x_or_o){
        //123,456,789,147,258,369,159,357 = Win game
        if ( (button1.getText().equals(x_or_o) && button2.getText().equals(x_or_o) && button3.getText().equals(x_or_o)) ||
                (button4.getText().equals(x_or_o) && button5.getText().equals(x_or_o) && button6.getText().equals(x_or_o)) ||
                (button7.getText().equals(x_or_o) && button8.getText().equals(x_or_o) && button9.getText().equals(x_or_o)) ||
                (button1.getText().equals(x_or_o) && button4.getText().equals(x_or_o) && button7.getText().equals(x_or_o)) ||
                (button2.getText().equals(x_or_o) && button5.getText().equals(x_or_o) && button8.getText().equals(x_or_o)) ||
                (button3.getText().equals(x_or_o) && button6.getText().equals(x_or_o) && button9.getText().equals(x_or_o)) ||
                (button1.getText().equals(x_or_o) && button5.getText().equals(x_or_o) && button9.getText().equals(x_or_o)) ||
                (button3.getText().equals(x_or_o) && button5.getText().equals(x_or_o) && button7.getText().equals(x_or_o))
                ) {
            endGameString = x_or_o + " win game!";
            return true;
        }
        else
        if  (!button1.isEnabled() && !button2.isEnabled() && !button3.isEnabled() && !button4.isEnabled() &&
                !button5.isEnabled() && !button6.isEnabled() && !button7.isEnabled() && !button8.isEnabled() &&
                !button9.isEnabled() && endGameString.equals("")){
            endGameString = "Ничья ! ";
            return true;
        }
        else
            return false;
    }

    public void printLog(String s){
        if (PRINT_LOG_ENABLE){
            textArea1.setText(textArea1.getText() + "\n" + s);
            //System.out.println(s);
        }
    }

    private void setTextToButton(JButton button, String x_or_o){
        if (button.isEnabled()) button.setText(x_or_o);
            button.setEnabled(false);

        if (gameType == 0 ) {
            if ( xRadioButton.isSelected() ) {
                oRadioButton.setSelected(true);
                x_or_o_User = "o";
            }
            else
            {
                xRadioButton.setSelected(true);
                x_or_o_User = "x";
            }
        }
        if (gameType == 2 ) {
            printLog("----- Network game ----------");
        }
        if (gameType == 1 ) {
            compMove();
        }

        if (checkEndGame(x_or_o)) {
            printLog(endGameString);
            stopGame();
        }
    }

    private void compMove() {
        if (endGameString.equals("")){
            JButton b;
            if ((b = compChoiseNextCell()) != null){
                b.setText(x_or_o_Comp);
                b.setEnabled(false);
                printLog("Comp move done");
            }
            if (checkEndGame(x_or_o_Comp)) {
                printLog(endGameString);
                stopGame();
            }
        }
    }


}
