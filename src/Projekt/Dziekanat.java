package Projekt;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.sql.*;
import java.util.Collection;
import java.util.Collections;

public class Dziekanat extends JFrame{
    private JPanel Main;
    private JList listPoeple;
    private JTextField index;
    private JTextField imie;
    private JTextField plec;
    private JTextField nazwisko;
    private JTextField wiek;
    private JTextField oceny;
    private JTextField dataur;
    private JTextField srednia;
    private JButton sredniaButton;
    private JButton wszyscyStudenciButton;
    private JButton wyczyscButton;
    private JButton ustawianieButton;
    private ArrayList<Student> people;
    private ArrayList<Student> people4_0;
    private ArrayList<Student> people_sorted;
    private ArrayList<Student> people_sorted4_0;
    private boolean lista_4_0 = false;
    private boolean sorted = false;
    private DefaultListModel listPeopleModel;
    public Dziekanat(){
        super("Dziekanat");
        this.setContentPane(this.Main);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        people = new ArrayList<Student>();
        people4_0 = new ArrayList<Student>();
        people_sorted = new ArrayList<Student>();
        people_sorted4_0 = new ArrayList<Student>();
        listPeopleModel = new DefaultListModel();
        listPoeple.setModel(listPeopleModel);


        listPoeple.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(lista_4_0 && !sorted){
                    listPoepleSelection4_0(e);
                }else if(sorted){
                    listPoepleSelectionSorted(e);
                }else{
                    listPoepleSelection(e);
                }
            }
        });

        sredniaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sorted = false;
                lista_4_0 = true;
                PeopleList4_0();
            }
        });

        wszyscyStudenciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sorted = false;
                lista_4_0 = false;
                refreshPeopleList();
            }
        });

        wyczyscButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sorted = false;
                lista_4_0 = false;
                wyczysc();
            }
        });

        ustawianieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sorted = true;
                if(lista_4_0){
                    PeopleListSorted(2);
                }else{
                    PeopleListSorted(1);
                }
            }
        });

    }

    static final String DB_URL = "jdbc:mysql://51.77.56.204/bazd_lab";
    static final String USER = "koza";
    static final String PASS = "46ddaat4HERDfaFYHG5324dfhfdERYHhujRE";
    static final String QUERY = "SELECT * FROM student";
    static final String QUERY_SRT = "SELECT * FROM student ORDER BY data";
    public static void main(String[] args)
    {
        Dziekanat dziekanat = new Dziekanat();
        dziekanat.setVisible(true);
        String oceny[] = new String[10];
        int wynik[] = new int[10];
        double srednia = 0 ;
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);) {
            while (rs.next()) {
                String Imie =  rs.getString("Imie");
                String Nazwisko =  rs.getString("Nazwisko");
                String Plec =  rs.getString("Plec");
                Date Data =  rs.getDate("data");
                String DataS = String.valueOf(Data);
                String NrInd =  rs.getString("NrInd");
                String Oceny = rs.getString("oceny");
                for(int i = 0 ; i < Oceny.length(); i++){
                    if(i%2 == 0 || i == 0)
                    oceny[i] = Oceny.charAt(i) + "";
                }
                for(int i = 0 ; i < Oceny.length(); i++){
                    if(oceny[i]!= null) {
                        wynik[i] = Integer.parseInt(oceny[i]);
                    }
                }

                for(int i = 0 ; i < Oceny.length(); i++) {
                    srednia = srednia + wynik[i];
                }
                srednia = srednia/5;
                String srednia_s = srednia + "";
                Student st = new Student(Imie, Nazwisko,Plec, DataS ,NrInd, Oceny, srednia_s);
                dziekanat.addPerson(st);
                if(srednia >=4){
                    dziekanat.addPerson4_0(st);
                }
                dziekanat.Reload();
                srednia = 0 ;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt_srt = conn.createStatement();
            ResultSet rs_srt = stmt_srt.executeQuery(QUERY_SRT);) {
            while (rs_srt.next()) {
                String Imie_srt = rs_srt.getString("Imie");
                String Nazwisko_srt = rs_srt.getString("Nazwisko");
                String Plec_srt = rs_srt.getString("Plec");
                Date Data_srt = rs_srt.getDate("data");
                String DataS_srt = String.valueOf(Data_srt);
                String NrInd_srt = rs_srt.getString("NrInd");
                String Oceny_srt = rs_srt.getString("oceny");
                for(int i = 0 ; i < Oceny_srt.length(); i++){
                    if(i%2 == 0 || i == 0)
                        oceny[i] = Oceny_srt.charAt(i) + "";
                }
                for(int i = 0 ; i < Oceny_srt.length(); i++){
                    if(oceny[i]!= null) {
                        wynik[i] = Integer.parseInt(oceny[i]);
                    }
                }
                for(int i = 0 ; i < Oceny_srt.length(); i++) {
                    srednia = srednia + wynik[i];
                }
                srednia = srednia/5;
                String srednia_s = srednia + "";
                Student sortowane_st = new Student(Imie_srt, Nazwisko_srt, Plec_srt, DataS_srt, NrInd_srt, Oceny_srt, srednia_s);
                dziekanat.addSortedPerson(sortowane_st);
                if(srednia >= 4){
                    dziekanat.addSortedPerson4_0(sortowane_st);
                }
                srednia = 0;
            }
            } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPerson4_0(Student p){
        people4_0.add(p);
    }

    public void addSortedPerson4_0(Student p){
        people_sorted4_0.add(p);
    }

    public void addSortedPerson(Student p){
        people_sorted.add(p);
    }

    public void addPerson(Student p){
        refreshPeopleList();
        people.add(p);
    }

    private void Reload() {
        lista_4_0 = false;
        wyczysc();
        refreshPeopleList();
    }


    public void refreshPeopleList(){
        listPeopleModel.removeAllElements();
        for(Student p: people){
            listPeopleModel.addElement( " | " + p.getImie()+ " " +   p.getNazwisko() + " |");
        }
    }


    public void PeopleList4_0(){
        listPeopleModel.removeAllElements();
        for(Student p : people4_0) {
            if (p.getSredniaDouble() >= 4.0) {
                listPeopleModel.addElement( " | " + p.getImie()+ " " +   p.getNazwisko() + " |");
                imie.setText(p.getImie());
                nazwisko.setText(p.getNazwisko());
                dataur.setText(p.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                plec.setText(p.getPlec());
                wiek.setText(Integer.toString(p.getAge()) + "lat/lata");
                index.setText(p.getNrIndeksu());
                oceny.setText(p.getOceny());
                srednia.setText(p.getSrednia());
            }
        }
    }

    public void PeopleListSorted(int rodzaj){
        listPeopleModel.removeAllElements();
        if(rodzaj == 1) {
            for (Student p : people_sorted) {
                listPeopleModel.addElement(" | " + p.getImie() + " " + p.getNazwisko() + " |");
                imie.setText(p.getImie());
                nazwisko.setText(p.getNazwisko());
                dataur.setText(p.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                plec.setText(p.getPlec());
                wiek.setText(Integer.toString(p.getAge()) + "lat/lata");
                index.setText(p.getNrIndeksu());
                oceny.setText(p.getOceny());
            }
        }
        if (rodzaj == 2){
            for(Student p : people_sorted4_0) {
                listPeopleModel.addElement(" | " + p.getImie() + " " + p.getNazwisko() + " |");
                imie.setText(p.getImie());
                nazwisko.setText(p.getNazwisko());
                dataur.setText(p.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                plec.setText(p.getPlec());
                wiek.setText(Integer.toString(p.getAge()) + "lat/lata");
                index.setText(p.getNrIndeksu());
                oceny.setText(p.getOceny());
                srednia.setText(p.getSrednia());
            }
        }
    }

    public void listPoepleSelection(ListSelectionEvent e){
            int personNumber = listPoeple.getSelectedIndex();
            if (personNumber >= 0) {
                Student p = people.get(personNumber);
                imie.setText(p.getImie());
                nazwisko.setText(p.getNazwisko());
                dataur.setText(p.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                plec.setText(p.getPlec());
                wiek.setText(Integer.toString(p.getAge()) + "lat/lata");
                index.setText(p.getNrIndeksu());
                oceny.setText(p.getOceny());
                srednia.setText(p.getSrednia());
            }
    }

    public void listPoepleSelectionSorted(ListSelectionEvent e){
        int personNumber = listPoeple.getSelectedIndex();
        if (personNumber >= 0) {
            if(lista_4_0) {
                Student p = people_sorted4_0.get(personNumber);
                imie.setText(p.getImie());
                nazwisko.setText(p.getNazwisko());
                dataur.setText(p.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                plec.setText(p.getPlec());
                wiek.setText(Integer.toString(p.getAge()) + "lat/lata");
                index.setText(p.getNrIndeksu());
                oceny.setText(p.getOceny());
                srednia.setText(p.getSrednia());
            }else{
                Student p = people_sorted.get(personNumber);
                imie.setText(p.getImie());
                nazwisko.setText(p.getNazwisko());
                dataur.setText(p.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                plec.setText(p.getPlec());
                wiek.setText(Integer.toString(p.getAge()) + "lat/lata");
                index.setText(p.getNrIndeksu());
                oceny.setText(p.getOceny());
                srednia.setText(p.getSrednia());
            }
        }
    }

    public void listPoepleSelection4_0(ListSelectionEvent e){
        int personNumber = listPoeple.getSelectedIndex();
        if (personNumber >= 0) {
            Student p = people4_0.get(personNumber);
            imie.setText(p.getImie());
            nazwisko.setText(p.getNazwisko());
            dataur.setText(p.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            plec.setText(p.getPlec());
            wiek.setText(Integer.toString(p.getAge()) + "lat/lata");
            index.setText(p.getNrIndeksu());
            oceny.setText(p.getOceny());
            srednia.setText(p.getSrednia());
        }
    }

    public void wyczysc(){
        refreshPeopleList();
        imie.setText("");
        nazwisko.setText("");
        dataur.setText("");
        plec.setText("");
        wiek.setText("");
        index.setText("");
        oceny.setText("");
        srednia.setText("");
    }

}
