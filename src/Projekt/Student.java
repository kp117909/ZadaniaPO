package Projekt;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Student {

    private String imie;
    private String nazwisko;
    private LocalDate dateOfBirth;
    private String plec;
    private String nrIndeksu;
    private String Srednia;
    private String Oceny;
    private double srednia = 0;

    public Student(String imie , String nazwisko , String plec , String dateOfBirth, String nrIndeksu, String Oceny, String Srednia)
    {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.plec = plec;
        this.nrIndeksu = nrIndeksu;
        this.Oceny = Oceny;
        this.Srednia = Srednia;
        this.setDateOfBirth(dateOfBirth);
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getAge(){
        LocalDate todey = LocalDate.now();
        Period period = Period.between(this.dateOfBirth, todey);
        return period.getYears();
    }

    public String getPlec() {
        return plec;
    }

    public String getNrIndeksu() {
        return nrIndeksu;
    }

    public String getOceny() {
        return Oceny;
    }

    public String getSrednia(){
        return this.Srednia;
    }

    public double getSredniaDouble(){
        double wynik;
        wynik = Double.parseDouble(this.Srednia);
        srednia = wynik;
        return srednia;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


}
