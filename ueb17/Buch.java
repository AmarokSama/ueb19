package ueb17;

 
import java.io.*;

/**
* Die Klasse:  Buch.java
* Realisiert eine Buch-Klasse
*
* @version	2.0 Beta 13.01.2017
* @author	Wolfgang Pauly
*
*/

public class Buch extends Artikel
{
    //------------------Konstanten----------------------------------

    private static final String STD_AUTOR = "kein Autor";
    private static final String STD_TITEL = "kein Titel";
    private static final String STD_VERLAG = "kein Verlag";

    private static final String AUTOR_MELDUNG = "Uebergebener Autor ist ungueltig, da ein LEER-String !";
    private static final String TITEL_MELDUNG = "Uebergebener Titel ist ungueltig, da ein LEER-String !";
    private static final String VERLAG_MELDUNG = "Uebergebener Verlag ist ungueltig, da ein LEER-String !";

    //------------------Attribute----------------------------------

    private String autor;
    private String titel;
    private String verlag;

    //------------------Konstruktoren-------------------------------

    /**
    * Der Konstruktor zur Voll-Initialisierung
    *
    * @param artikelNr initial Buch-Artikelnummer
    * @param bezeichnung initial Buch-Bezeichnung
    * @param bestand initial Buch-Bestand
    * @param preis initial Buch-Preis
    * @param autor initial Buch-Autor
    * @param titel initial Buch-Titel
    * @param verlag initial Buch-Verlag
    */
    public Buch( int artikelNr, String bezeichnung, int bestand, 
                 double preis, String autor, String titel, String verlag
               ) 
    {
        super(artikelNr, bezeichnung, bestand, preis);
        setBuchAttributes(autor, titel, verlag);
    }

    /**
     * die Hilfs-Methode zu Konstruktion von Buch-Objekten
     *
     * @param autor initial Buch-Autor
     * @param titel initial Buch-Titel
     * @param verlag initial Buch-Verlag
     */
    private void setBuchAttributes(String autor, String titel, String verlag) 
    {
        setAutor(autor);
        setTitel(titel);
        setVerlag(verlag);
    }

    //------------------autor-------------------------------

    /**
    * gibt Buch-Attribut : autor zurueck
    *
    * @return    Buch-Autor
    */
    public String getAutor()
    {
        return autor;
    }

    /**
     * setzt Buch-Attribut :  autor
     *
     * @param autor     neuer Autor
     */
    public void setAutor(String autor) 
    {
        if((autor == null) ||(autor.trim().length() <= 0))
        {
            throw new RuntimeException(AUTOR_MELDUNG);
        };
        this.autor = autor.trim();
    }

    //------------------titel-------------------------------

    /**
    * gibt Buch-Attribut : titel zurueck
    *
    * @return    Buch-Titel
    */
    public String getTitel()
    {
        return titel;
    }

    /**
     * setzt Buch-Attribut :  titel
     *
     * @param titel     neuer Titel
     */
    public void setTitel(String titel) 
    {
        if((titel == null) ||(titel.trim().length() <= 0))
        {
            throw new RuntimeException(TITEL_MELDUNG);
        };
        this.titel = titel.trim();
    }

    //------------------verlag-------------------------------

    /**
    * gibt Buch-Attribut : verlag zurueck
    *
    * @return    der Verlag des Buchs
    */
    public String getVerlag()
    {
        return verlag;
    }

    /**
     * setzt Buch-Attribut :  verlag
     *
     * @param verlag     der das Buch herrausbringende Verlag
     */
    public void setVerlag(String verlag) 
    {
        if((verlag == null) ||(verlag.trim().length() <= 0))
        {
            throw new RuntimeException(VERLAG_MELDUNG);
        };
        this.verlag = verlag;
    }



    //------------------sonstiges-------------------------------

    /**
    * erzeugt einen Beschreibungs-String, der eine Kurzbeschreibung
    * des Buches zurueckgibt
    *
    * @return eine Kurzbeschreibung der Buch
    */
    public String getBeschreibung()
    {
        return String.format(" %20s : %20s", autor, titel);
    }

    /**
     * erzeugt einen String, der alle, fuer den Klassenbenutzer
     * wichtigen, Buch-Merkmale enthaelt
     *
     * @return die String-Repraesentation der Buch
     */
    public String toString()
    {
        return String.format( "%s ---> %10s %10s %15s ",
                              super.toString(), autor, titel, verlag
                            );
    }

}
