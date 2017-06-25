package ueb17;

 
import java.io.*;
    
/**
 *    Die Klasse:  Dvd.java
 *    Realisiert eine Dvd-Klasse
 *
 * @version	2.0 Beta 13.01.2017
 * @author	Wolfgang Pauly
 *
 */

public class Dvd
       extends Artikel      
{
//------------------Konstanten----------------------------------

  public static final int MIN_ERSCHEINUNGSJAHR = 1999;
  public static final int MAX_ERSCHEINUNGSJAHR = 2014;

  private static final String STD_TITEL = "kein Titel";
  private static final float  STD_SPIELDAUER = 0;
  private static final int    STD_ERSCHEINUNGSJAHR = 0;

  private static final String  TITEL_MELDUNG = 
          "Uebergebener Titel ist ungueltig, da ein LEER-String !";
  private static final String  SPIELDAUER_MELDUNG = 
          "Uebergebene Spieldauer ist ungueltig, da kleiner 0 !";
  private static final String  ERSCHEINUNGSJAHR_MELDUNG = 
          "Uebergebenes Erscheinungsjahr liegt nicht zwischen " +
           MIN_ERSCHEINUNGSJAHR + " und " + MAX_ERSCHEINUNGSJAHR +
          " !!!";
  
//------------------Attribute----------------------------------
  
  private  String  titel;
  private  float   spieldauer;
  private  int     erscheinungsJahr;

//------------------Konstruktoren-------------------------------


  /**
   *    Der Konstruktor zur Voll-Initialisierung
   *
   *    @param artikelNr initial Dvd-Artikelnummer
   *    @param bezeichnung initial Dvd-Bezeichnung
   *    @param bestand initial Dvd-Bestand
   *    @param preis initial Dvd-Preis
   *    @param titel initial Dvd-Titel
   *    @param spieldauer initial Dvd-Spieldauer
   *    @param erscheinungsJahr initial Dvd-Erscheinungsjahr
   */
  public Dvd ( int artikelNr, String bezeichnung,
               int bestand, double preis, String titel,
               float spieldauer, int erscheinungsJahr
             )
  {
   super( artikelNr, bezeichnung, bestand, preis 
        );
   setDvdAttributes( titel, spieldauer, erscheinungsJahr );
  }  


  /**
   *    die Hilfs-Methode zu Konstruktion von Dvd-Objekten
   *
   *    @param titel initial Dvd-Titel
   *    @param spieldauer initial Dvd-Spieldauer
   *    @param erscheinungsJahr initial Dvd-Erscheinungsjahr
   */
  private void setDvdAttributes ( String titel, float spieldauer,
                                  int erscheinungsJahr 
                                )
  {
   setTitel( titel );
   setSpieldauer( spieldauer );
   setErscheinungsJahr( erscheinungsJahr );
  }  
  

//------------------titel-------------------------------

  /**
   *    gibt Dvd-Attribut : titel zurueck
   *    
   *    @return    Dvd-Titel
   */
  public String getTitel ( )
  {
    return titel;
  }


  /**
   *    setzt Dvd-Attribut :  titel
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


//------------------spieldauer-------------------------------

  /**
   *    gibt Dvd-Attribut : spieldauer zurueck
   *    
   *    @return    Dvd-Spieldauer
   */
  public float getSpieldauer ( )
  {
    return spieldauer;
  }


  /**
   *    setzt Dvd-Attribut :  spieldauer
   *    
   *    @param spieldauer     neue Spieldauer
   */
  public void setSpieldauer ( float spieldauer )
  {
   if ( spieldauer <= 0.0 )
     {
       throw new RuntimeException( SPIELDAUER_MELDUNG );
     };
   this.spieldauer = spieldauer;
  }


//------------------erscheinungsJahr-------------------------------

  /**
   *    gibt Dvd-Attribut : erscheinungsJahr zurueck
   *    
   *    @return    das Erscheinungsjahr des Dvds
   */
  public int getErscheinungsJahr ( )
  {
    return erscheinungsJahr;
  }


  /**
   *    setzt Dvd-Attribut :  erscheinungsJahr
   *    
   *    @param erscheinungsJahr     das neue Erscheinungsjahr
   */
  public void setErscheinungsJahr ( int erscheinungsJahr )
  {
   if ( (erscheinungsJahr < MIN_ERSCHEINUNGSJAHR) || 
        (erscheinungsJahr > MAX_ERSCHEINUNGSJAHR) 
      )
     {
       throw new RuntimeException( ERSCHEINUNGSJAHR_MELDUNG );
     };
   this.erscheinungsJahr = erscheinungsJahr;
  }
  
  
 

//------------------sonstiges-------------------------------

  /**
   *    erzeugt einen Beschreibungs-String, der eine Kurzbeschreibung
   *    der Dvd zurueckliefert
   *    
   *    @return eine Kurzbeschreibung der Dvd
   */
  public String getBeschreibung ()
  {
   return String.format( " %20s", titel );
  }


  /**
   *    erzeugt einen String, der alle, fuer den Klassenbenutzer
   *    wichtigen, Dvd-Merkmale enthaelt
   *    
   *    @return die String-Repraesentation der Dvd
   */
  public String toString ()
  {
   return String.format( "%s ---> %20s %10.2f %5d ",
                              super.toString(), titel, spieldauer, erscheinungsJahr
                            );

  }

}
