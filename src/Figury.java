import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Figury extends JFrame {
    private JRadioButton kwadratRadioButton;
    private JRadioButton prostokątRadioButton;
    private JRadioButton trójkątRadioButton;
    private JRadioButton trapezRadioButton;
    private JRadioButton sześcianRadioButton;
    private JRadioButton prostopadłościanRadioButton;
    private JRadioButton walecRadioButton;
    private JRadioButton kulaRadioButton;
    private JTextField textBokA;
    private JTextField textBokB;
    private JTextField textWysokosc;
    private JTextField textPromien;
    private JTextField textPole;
    private JTextField textObwod;
    private JTextField textObjetosc;
    private JButton obliczButton;
    private JButton wyczyśćButton;
    private JPanel panel1;

    private double bokB, bokA, wysokosc, promien, pole, obwod, objetosc;


    public static void main(String[] args) {
        Figury figury = new Figury();
        figury.setVisible(true);
    }


    public Figury() {

        super("Figury geometryczne - kalkulator");
        this.setContentPane(this.panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        setLayout(null);

        obliczButton.addActionListener(listener);
        wyczyśćButton.addActionListener(listener);

        textBokA.addActionListener(listener);
        textBokB.addActionListener(listener);
        textPole.addActionListener(listener);
        textObwod.addActionListener(listener);
        textWysokosc.addActionListener(listener);
        textObjetosc.addActionListener(listener);
        textPromien.addActionListener(listener);

        prostokątRadioButton.addActionListener(listener);
        kwadratRadioButton.addActionListener(listener);
        trójkątRadioButton.addActionListener(listener);
        trapezRadioButton.addActionListener(listener);
        sześcianRadioButton.addActionListener(listener);
        prostopadłościanRadioButton.addActionListener(listener);
        walecRadioButton.addActionListener(listener);
        kulaRadioButton.addActionListener(listener);

        textBokA.setEditable(false);
        textBokB.setEditable(false);
        textWysokosc.setEditable(false);
        textPromien.setEditable(false);
        textPole.setEditable(false);
        textObjetosc.setEditable(false);
        textObwod.setEditable(false);
    }

    ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object ob1 = e.getSource();
                if (ob1 == obliczButton) {
                        if (kwadratRadioButton.isSelected() == true) {
                            bokA = Double.parseDouble(textBokA.getText());
                            if(bokA >= 0) {
                                pole = bokA * bokA;
                                obwod = bokA * 4;
                                textPole.setText(String.valueOf(pole));
                                textObwod.setText(String.valueOf(obwod));
                            }else{
                                textPole.setText("Błąd");
                                textObwod.setText("Błąd");
                                JOptionPane.showMessageDialog(null, "Wartość jest ujemna!");
                            }
                        } else if (prostokątRadioButton.isSelected() == true) {
                            bokA = Double.parseDouble(textBokA.getText());
                            bokB = Double.parseDouble(textBokB.getText());
                            if(bokA >= 0 && bokB >=0) {
                                pole = bokA * bokB;
                                obwod = (bokA * 2) + (bokB * 2);
                                textPole.setText(String.valueOf(pole));
                                textObwod.setText(String.valueOf(obwod));
                            }else{
                                textPole.setText("Błąd");
                                textObwod.setText("Błąd");
                                JOptionPane.showMessageDialog(null, "Wartość jest ujemna!");
                            }
                        } else if (trójkątRadioButton.isSelected() == true) {
                            bokA = Double.parseDouble(textBokA.getText());
                            bokB = Double.parseDouble(textBokB.getText());
                            wysokosc = Double.parseDouble(textWysokosc.getText());
                            if(bokA >= 0 && bokB >=0 && wysokosc >=0) {
                                pole = 0.5 * bokA * wysokosc;
                                obwod = bokA + bokB * 2;
                                textPole.setText(String.valueOf(pole));
                                textObwod.setText(String.valueOf(obwod));
                            }else{
                                textPole.setText("Błąd");
                                textObwod.setText("Błąd");
                                JOptionPane.showMessageDialog(null, "Wartość jest ujemna!");
                            }
                        } else if (trapezRadioButton.isSelected() == true) {
                            bokA = Double.parseDouble(textBokA.getText());
                            bokB = Double.parseDouble(textBokB.getText());
                            wysokosc = Double.parseDouble(textWysokosc.getText());
                            if(bokA >= 0 && bokB >=0 && wysokosc >=0) {
                                pole = (bokA + bokB) * wysokosc * 1 / 2;
                                textPole.setText(String.valueOf(pole));
                                textObwod.setText("Brak Danych");
                            }else{
                                textPole.setText("Błąd");
                                textObwod.setText("Błąd");
                                JOptionPane.showMessageDialog(null, "Wartość jest ujemna!");
                            }
                        } else if (sześcianRadioButton.isSelected() == true) {
                            bokA = Double.parseDouble(textBokA.getText());
                            if(bokA >= 0) {
                                objetosc = bokA * bokA * bokA;
                                pole = 6 * bokA * bokA;
                                textPole.setText(String.valueOf(pole));
                                textObjetosc.setText(String.valueOf(objetosc));
                                textObwod.setText("Brak");
                            }else{
                                textPole.setText("Błąd");
                                textObwod.setText("Błąd");
                                textObjetosc.setText("Błąd");
                                JOptionPane.showMessageDialog(null, "Wartość jest ujemna!");
                            }
                        } else if (prostopadłościanRadioButton.isSelected() == true) {
                            bokA = Double.parseDouble(textBokA.getText());
                            bokB = Double.parseDouble(textBokB.getText());
                            wysokosc = Double.parseDouble(textWysokosc.getText());
                            if(bokA >= 0 && bokB >=0 && wysokosc >=0) {
                                pole = (2 * bokA * bokB) + (2 * bokA * wysokosc) + (2 * wysokosc * bokB);
                                objetosc = bokA * bokB * objetosc;
                                textPole.setText(String.valueOf(pole));
                                textObjetosc.setText(String.valueOf(objetosc));
                                textObwod.setText("Brak");
                            }else{
                                textPole.setText("Błąd");
                                textObwod.setText("Błąd");
                                textObjetosc.setText("Błąd");
                                JOptionPane.showMessageDialog(null, "Wartość jest ujemna!");
                            }
                        } else if (walecRadioButton.isSelected() == true) {
                            promien = Double.parseDouble(textPromien.getText());
                            wysokosc = Double.parseDouble(textWysokosc.getText());
                            if(promien >=0 && wysokosc >=0) {
                                pole = 2 * 3.14 * promien * wysokosc + 2 * 3.14;
                                objetosc = 3.14 * promien * promien * wysokosc;
                                textPole.setText(String.valueOf(pole));
                                textObjetosc.setText(String.valueOf(objetosc));
                                textObwod.setText("Brak");
                            }else{
                                textPole.setText("Błąd");
                                textObwod.setText("Błąd");
                                textObjetosc.setText("Błąd");
                                JOptionPane.showMessageDialog(null, "Wartość jest ujemna!");
                            }
                        } else if (kulaRadioButton.isSelected() == true) {
                            promien = Double.parseDouble(textPromien.getText());
                            if(promien >=0) {
                                pole = 4 * 3.14 * promien * promien;
                                objetosc = (4 / 3) * 3.14 * promien * promien * promien;
                                textPole.setText(String.valueOf(pole));
                                textObjetosc.setText(String.valueOf(objetosc));
                                textObwod.setText("Brak");
                            }else{
                                textPole.setText("Błąd");
                                textObwod.setText("Błąd");
                                textObjetosc.setText("Błąd");
                                JOptionPane.showMessageDialog(null, "Wartość jest ujemna!");
                            }
                        }
            }
            if (ob1 == wyczyśćButton) {
                textBokA.setText("");
                textBokB.setText("");
                textWysokosc.setText("");
                textPromien.setText("");
                textPole.setText("");
                textObwod.setText("");
                textObjetosc.setText("");
                kwadratRadioButton.setSelected(false);
                prostokątRadioButton.setSelected(false);
                trójkątRadioButton.setSelected(false);
                trapezRadioButton.setSelected(false);
                sześcianRadioButton.setSelected(false);
                prostopadłościanRadioButton.setSelected(false);
                walecRadioButton.setSelected(false);
                kulaRadioButton.setSelected(false);
            }

            if (ob1 == kwadratRadioButton ){
                if (kwadratRadioButton.isSelected() == true) {
                   DisableAll(1);
                   textBokA.setEditable(true);
                }
            }
            if (ob1 == prostokątRadioButton ) {
                if (prostokątRadioButton.isSelected() == true) {
                    DisableAll(2);
                    textBokA.setEditable(true);
                    textBokB.setEditable(true);
                }
            }
            if (ob1 == trójkątRadioButton ) {
                if (trójkątRadioButton.isSelected() == true) {
                    DisableAll(3);
                    textBokA.setEditable(true);
                    textBokB.setEditable(true);
                    textWysokosc.setEditable(true);
                }
            }
            if (ob1 == trapezRadioButton ) {
                if (trapezRadioButton.isSelected() == true) {
                    DisableAll(4);
                    textBokA.setEditable(true);
                    textBokB.setEditable(true);
                    textWysokosc.setEditable(true);
                }
            }
            if (ob1 == sześcianRadioButton ) {
                if (sześcianRadioButton.isSelected() == true) {
                    DisableAll(5);
                    textBokA.setEditable(true);
                }
            }
            if (ob1 == prostopadłościanRadioButton ) {
                if (prostopadłościanRadioButton.isSelected() == true) {
                    DisableAll(6);
                    textBokA.setEditable(true);
                    textBokB.setEditable(true);
                    textWysokosc.setEditable(true);
                }
            }
            if (ob1 == walecRadioButton ) {
                if (walecRadioButton.isSelected() == true) {
                    DisableAll(7);
                    textPromien.setEditable(true);
                    textWysokosc.setEditable(true);
                }
            }
            if (ob1 == kulaRadioButton ) {
                if (kulaRadioButton.isSelected() == true) {
                    DisableAll(8);
                    textPromien.setEditable(true);
                }
            }
        }

        public void DisableAll(int i) {
            System.out.println(i);
            if(i == 1){
                prostokątRadioButton.setSelected(false);
                trójkątRadioButton.setSelected(false);
                trapezRadioButton.setSelected(false);
                prostopadłościanRadioButton.setSelected(false);
                sześcianRadioButton.setSelected(false);
                walecRadioButton.setSelected(false);
                kulaRadioButton.setSelected(false);
            }else if (i == 2) {
                trójkątRadioButton.setSelected(false);
                trapezRadioButton.setSelected(false);
                kwadratRadioButton.setSelected(false);
                prostopadłościanRadioButton.setSelected(false);
                sześcianRadioButton.setSelected(false);
                walecRadioButton.setSelected(false);
                kulaRadioButton.setSelected(false);
            }else if (i == 3){
                prostokątRadioButton.setSelected(false);
                trapezRadioButton.setSelected(false);
                kwadratRadioButton.setSelected(false);
                prostopadłościanRadioButton.setSelected(false);
                sześcianRadioButton.setSelected(false);
                walecRadioButton.setSelected(false);
                kulaRadioButton.setSelected(false);
            }else if (i == 4){
                prostokątRadioButton.setSelected(false);
                trójkątRadioButton.setSelected(false);
                kwadratRadioButton.setSelected(false);
                prostopadłościanRadioButton.setSelected(false);
                sześcianRadioButton.setSelected(false);
                walecRadioButton.setSelected(false);
                kulaRadioButton.setSelected(false);
            }else if (i == 5){
                prostokątRadioButton.setSelected(false);
                trapezRadioButton.setSelected(false);
                trójkątRadioButton.setSelected(false);
                kwadratRadioButton.setSelected(false);
                prostopadłościanRadioButton.setSelected(false);
                walecRadioButton.setSelected(false);
                kulaRadioButton.setSelected(false);
            }else if (i == 6){
                prostokątRadioButton.setSelected(false);
                trójkątRadioButton.setSelected(false);
                kwadratRadioButton.setSelected(false);
                trapezRadioButton.setSelected(false);
                sześcianRadioButton.setSelected(false);
                walecRadioButton.setSelected(false);
                kulaRadioButton.setSelected(false);
            }else if (i == 7){
                prostokątRadioButton.setSelected(false);
                trójkątRadioButton.setSelected(false);
                kwadratRadioButton.setSelected(false);
                trapezRadioButton.setSelected(false);
                prostopadłościanRadioButton.setSelected(false);
                sześcianRadioButton.setSelected(false);
                kulaRadioButton.setSelected(false);
            }else if (i == 8){
                prostokątRadioButton.setSelected(false);
                trójkątRadioButton.setSelected(false);
                kwadratRadioButton.setSelected(false);
                trapezRadioButton.setSelected(false);
                prostopadłościanRadioButton.setSelected(false);
                sześcianRadioButton.setSelected(false);
                walecRadioButton.setSelected(false);
            }

            textBokA.setEditable(false);
            textBokB.setEditable(false);
            textWysokosc.setEditable(false);
            textPromien.setEditable(false);
            textPole.setEditable(false);
            textObjetosc.setEditable(false);
            textObwod.setEditable(false);
            textBokA.setText("");
            textBokB.setText("");
            textWysokosc.setText("");
            textPromien.setText("");
            textPole.setText("");
            textObjetosc.setText("");
            textObwod.setText("");
        }

    };
}


