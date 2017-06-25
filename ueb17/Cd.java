package ueb17;

 
import java.io.*;
    
/**
 *    Die Klasse:  Cd.java
 *    Realisiert eine Cd-Klasse
 *
 * @version	2.0 Beta 13.01.2017
 * @author	Wolfgang Pauly
 *
 */

public class Cd
       extends Artikel      
{
//------------------Konstanten----------------------------------

  private static final String STD_INTERPRET  = "kein Interpret";
  private static final String STD_TITEL      = "kein Titel";
  private static final int    STD_MUSIKTITELANZAHL = 0;

  private static final String  INTERPRET_MELDUNG = 
          "Uebergebener Interpret ist ungueltig, da ein LEER-String !";
  private static final String  TITEL_MELDUNG = 
          "Uebergebener Titel ist ungueltig, da ein LEER-String !";
  private static final String  ANZAHLMUSIKTITEL_MELDUNG = 
          "Uebergebene Musiktitel-Anzahl ist ungueltig, da kleiner  0 !!!";
  
//------------------Attribute----------------------------------
  
  private  String  interpret;
  private  String  titel;
  private  int     anzahlMusikTitel;



//------------------Konstruktoren-------------------------------


  /**
   *    Der Konstruktor zur Voll-Initialisierung
   *
   *    @param artikelNr initial CD-Artikelnummer
   *    @param bezeichnung initial CD-Bezeichnung
   *    @param bestand initial CD-Bestandd
   *    @param preis initial CD-Preis
   *    @param interpret initial CD-Interpret
   *    @param titel initial CD-Titel
   *    @param anzahlMusiktitel initial CD-Musiktitel-Anzahl
   */
  public Cd ( int artikelNr, String bezeichnung,
              int bestand, double preis, String interpret,
              String titel, int anzahlMusiktitel
            )
  {
   super( artikelNr, bezeichnung, bestand, preis 
        );
   setCdAttributes( interpret, titel, anzahlMusiktitel );
  }  



  /**
   *    die Hilfs-Methode zu Konstruktion von Cd-Objekten
   *
   *    @param interpret initial CD-Interpret
   *    @param titel initial CD-Titel
   *    @param anzahlMusiktitel initial CD-Musiktitel-Anzahl
   */
  private void setCdAttributes ( String interpret, String titel, 
                                 int anzahlMusiktitel 
                               )
  {
   setInterpret( interpret );
   setTitel( titel );
   setAnzahlMusikTitel( anzahlMusiktitel );
  }  
  

//------------------interpret-------------------------------

  /**
   *    gibt Cd-Attribut : interpret zurueck
   *    
   *    @return    Cd-Interpret
   */
  public String getInterpret ( )
  {
    return interpret;
  }


  /**
   *    setzt Cd-Attribut :  interpret
   *    
   *    @param interpret     neuer Interpret
   */
  public void setInterpret ( String interpret )
  {
   if ( (interpret == null) || (interpret.trim().length() <= 0) )
     {
       throw new RuntimeException( INTERPRET_MELDUNG );
     };
   this.interpret = interpret.trim();
  }



//------------------titel-------------------------------

  /**
   *    gibt Cd-Attribut : titel zurueck
   *    
   *    @return    Cd-Titel
   */
  public String getTitel ( )
  {
    return titel;
  }


  /**
   *    setzt Cd-Attribut :  titel
   *    
   *    @param titel     neuer Titel
   */
  public void setTitel ( String titel )
  {
   if ( (titel == null) || (titel.trim().length() <= 0) )
     {
       throw new RuntimeException( TITEL_MELDUNG );
     };
   this.titel = titel.trim();
  }


//------------------anzahlMusikTitel-------------------------------

  /**
   *    gibt Cd-Attribut : anzahlMusikTitel zurueck
   *    
   *    @return    Anzahl der Musiktitel auf der CD
   */
  public int getAnzahlMusikTitel ( )
  {
    return anzahlMusikTitel;
  }


  /**
   *    setzt Cd-Attribut :  anzahlMusikTitel
   *    
   *    @param anzahlMusikTitel     die neue Anzahl der Musiktitel
   */
  public void setAnzahlMusikTitel ( int anzahlMusikTitel )
  {
   if ( anzahlMusikTitel <= 0 )
     {
       throw new RuntimeException( ANZAHLMUSIKTITEL_MELDUNG );
     };
   this.anzahlMusikTitel = anzahlMusikTitel;
  }
  
  



 

//------------------sonstiges-------------------------------

  /**
   *    erzeugt einen Beschreibungs-String, der eine Kurzbeschreibung
   *    der CD zurueckliefert
   *    
   *    @return eine Kurzbeschreibung der CD
   */
  public String getBeschreibung ()
  {
   return String.format( "%20s : %20s", interpret, titel );
  }


  /**
   *    erzeugt einen String, der alle, fuer den Klassenbenutzer
   *    wichtigen, Cd-Merkmale enthaelt
   *    
   *    @return die String-Repraesentation der CD
   */
  public String toString ()
  {
   return String.format( "%s ---> %12s %20s %5s ",
                              super.toString(), interpret, titel, anzahlMusikTitel
                       );

  }

}
