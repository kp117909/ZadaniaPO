package Operator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Operator extends JFrame {
    private JButton obliczButton;
    private JButton wyjscieButton;
    private JTextField textLiczba1;
    private JTextField textLiczba2;
    private JTextField textOperator;
    private JTextField textWynik;
    private JPanel Main;

    private double liczba1, liczba2 , wynik;
    private String znak;

    public static void main(String[] args) {
        Operator operator = new Operator();
        operator.setVisible(true);
    }

    public Operator(){
        super("Zadanie");
        this.setContentPane(this.Main);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        setLayout(null);

        obliczButton.addActionListener(listener);
        wyjscieButton.addActionListener(listener);

        textLiczba1.addActionListener(listener);
        textLiczba2.addActionListener(listener);
        textOperator.addActionListener(listener);
        textWynik.addActionListener(listener);
        textWynik.setEditable(false);

    }

    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object ob1 = e.getSource();
            if(ob1 == obliczButton){
                if(textLiczba1.getText().isEmpty() || textLiczba2.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Wprowadź wartości!");
                }else{
                    liczba1 = Double.parseDouble(textLiczba1.getText());
                    liczba2 = Double.parseDouble(textLiczba2.getText());
                    znak = String.valueOf(textOperator.getText());
                    if (znak.equals("*")) wynik = liczba1 * liczba2;
                    else if (znak.equals("/")) {
                        if (liczba2 != 0)
                            wynik = liczba1 / liczba2;
                        else  JOptionPane.showMessageDialog(null, "Nie wolno dzielić przez zero!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Znak nieprawidłowy wybierz między\n * - Mnożenie \n '/' - Dzielenie");
                    }
                    textWynik.setText(String.valueOf(wynik));
                }
            }else if(ob1 == wyjscieButton){
                int input = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wyjść", "Pytanie",
                        JOptionPane.YES_NO_OPTION);
                if(input == 0){
                    System.exit(0);
                }
            }
        }
    };


}