package ueb17;

 

import java.io.*;
   
/**
 *    Die Klasse:  Artikel.java
 *    Realisiert eine allgemeine Artikel-Klasse 
 *
 * @version	2.0 Beta 13.01.2017
 * @author	Wolfgang Pauly
 *
 */

public class Artikel
{
  //----------- Konstanten ----------------------
  private static final int       MIN_BESTAND     = 0;
  private static final int       MIN_ZUGANG      = 0;
  private static final int       MIN_ABGANG      = 0;
  private static final double    MIN_PREIS    = 0.0;
  private static final double    MIN_PROZENT  = -100.0;

  private static final int    KLEINSTE_GUELTIGE_ARTIKELNUMMER  = 1000;
  private static final int    GROESSTE_GUELTIGE_ARTIKELNUMMER  = 9999;
  
  
  //----------- MELDUNGS-Konstanten ----------------------
  private static final String  ARTIKEL_NUMMER_MELDUNG =
          "Uebergebene Artikelnummer ist ungueltig, " + 
          "da kleiner als " + KLEINSTE_GUELTIGE_ARTIKELNUMMER +
          " oder groesser als " + GROESSTE_GUELTIGE_ARTIKELNUMMER +
          " !!!";

  private static final String  BESTANDS_MELDUNG =
          "Uebergebener Bestand ist ungueltig, da kleiner " + MIN_BESTAND + " !!!";

  private static final String  BEZEICHNUNGS_MELDUNG =
          "Uebergebene Bezeichnung ist ungueltig, da ein" + 
          " LEER-String o. null-Referenz!";

  private static final String  ZUGANG_NEGATIV_MELDUNG =
          "Uebergebener Zugang ist ungueltig, da negativ (kleiner 0) !!!";

  private static final String  ABGANG_NEGATIV_MELDUNG =
          "Uebergebener Abgang ist ungueltig, da negativ (kleiner 0) !!!";

  private static final String  ABGANG_ZUGROSS_MELDUNG =
          "Uebergebener Abgang uebersteigt Anzahl der vorraetigen Artikel !!!"; 

  private static final String  PROZENT_MELDUNG =
          "Die uebergebene Prozentzahl muss > " + MIN_PROZENT + " sein !!!"; 

  private static final String  PREIS_MELDUNG =
          "Der angegebene Artikelpreis muss >= " + MIN_PREIS + " sein !!!"; 
              

  //----------- Attribute---------------------
  private  int     artikelNr;
  private  String  bezeichnung;
  private  int     bestand;
  private  double  preis;

  

  /**
   *    Der (Voll-)Konstruktor mit folgenden Parametern
   *    
   *    @param artikelNr    die Artikelnummer des neue Artikels -->  es gilt : 999 &lt Nummer &lt= 9999
   *    @param bezeichnung  die Bezeichnung des neuen Artikels --> darf nicht LEER sein    
   *    @param bestand      der Lagerbestand des neuen Artikels --> muss &gt= 0 sein   
   *    @param preis        der Preis des neuen Artikels --> muss &gt= 0 sein   
   */
  public Artikel (int artikelNr, String bezeichnung, 
                  int bestand, double preis
                 )
  {
   if ( (artikelNr < KLEINSTE_GUELTIGE_ARTIKELNUMMER) || 
        (artikelNr > GROESSTE_GUELTIGE_ARTIKELNUMMER) 
      )
     {
      throw new RuntimeException( ARTIKEL_NUMMER_MELDUNG );
     }
               
   if ( (bezeichnung == null) || (bezeichnung.trim().length() == 0) )
     {
      throw new RuntimeException( BEZEICHNUNGS_MELDUNG );
     }
               
   if ( bestand < MIN_BESTAND )
     {
      throw new RuntimeException( BESTANDS_MELDUNG );
     }

   if ( preis   < MIN_PREIS )
     {
      throw new RuntimeException( PREIS_MELDUNG );
     }
 
      
   this.artikelNr   = artikelNr;
   this.bezeichnung = new String( bezeichnung );
   this.bestand     = bestand;
   this.preis = preis;
  }


 /**
   *    Der (Teil-)Konstruktor mit folgenden Parametern
   *    Der Artikelbestand wird auf den Standardwert 0 gesetzt.
   *    
   *    @param artikelNr    die Artikelnummer des neue Artikels -->  es gilt : 999 &lt Nummer &lt= 9999
   *    @param bezeichnung  die Bezeichnung des neue Artikels  --> darf nicht LEER sein
   *    @param bestand      der Lagerbestand des neuen Artikels --> muss &gt= 0 sein
   *  
   */
 public Artikel (int artikelNr, String bezeichnung, int bestand )
  {
    this( artikelNr, bezeichnung, bestand, MIN_PREIS );
  }


  
 /**
   *    Der (Teil-)Konstruktor mit folgenden Parametern
   *    Der Artikelbestand wird auf den Standardwert 0 gesetzt.
   *    
   *    @param artikelNr    die Artikelnummer des neue Artikels -->  es gilt : 999 &lt Nummer &lt= 9999
   *    @param bezeichnung  die Bezeichnung des neue Artikels  --> darf nicht LEER sein      
   *  
   */
 public Artikel (int artikelNr, String bezeichnung )
  {
    this( artikelNr, bezeichnung, MIN_BESTAND, MIN_PREIS );
  }


//------------------artikelNr-------------------------------
  /**
   *    gibt Artikel-Attribut : artikelNr zurueck
   *    
   *    @return    Artikel-Nummer
   */
  public int getArtikelNr ( )
  {
    return artikelNr;
  }

    /**
   *    Testet Artikel-Attribut : artikelNr auf gueltig
   *    
   *    wirft bei Ungueltiger Artikelnummer eine Runtimeexception
   */
  public static void testeArtikelNr ( int artikelNr)
  {
   if ( (artikelNr < KLEINSTE_GUELTIGE_ARTIKELNUMMER) || 
        (artikelNr > GROESSTE_GUELTIGE_ARTIKELNUMMER) 
      )
     {
      throw new RuntimeException( ARTIKEL_NUMMER_MELDUNG );
     }
  }

  
//------------------preis-----------------------------------

  /**
   *    gibt Artikel-Attribut preis  zurueck
   *    
   *    @return    Artikel-Preis
   */
  public double getPreis ( )
  {
    return preis;
  }


  /**
   *    setzt Artikel-Attribut :  preis
   *    
   *    @param neuerPreis    neuer Preis   --> muss >= 0.0 sein !!!
   */
  public void setPreis ( double neuerPreis )
  {
   if ( neuerPreis < MIN_PREIS )
     {
      throw new RuntimeException( PREIS_MELDUNG );
     }

   preis = neuerPreis;
  }
  
  
//------------------bezeichnung-------------------------------

  /**
   *    gibt Artikel-Attribut : bezeichnung zurueck
   *    
   *    @return    Artikel-Bezeichnung
   */
  public String getBezeichnung ( )
  {
    return bezeichnung;
  }


  /**
   *    setzt Artikel-Attribut :  bezeichnung
   *    
   *    @param bezeichnung    neue Bezeichnung   --> darf nicht LEER sein 
   */
  public void setBezeichnung ( String bezeichnung )
  {
   if ( (bezeichnung == null) || (bezeichnung.trim().length() == 0) )
     {
      throw new RuntimeException( BEZEICHNUNGS_MELDUNG );
     }
            
   this.bezeichnung = bezeichnung.trim();
  }
  
  
//------------------bestand-------------------------------

  /**
   *    gibt Artikel-Attribut : Artikelbestand im Lager zurueck
   *    
   *    @return    Artikel-Bestand
   */
  public int getBestand ( )
  {
    return bestand;
  }

//------------------sonstige Methoden-------------------------------

  /**
   *    bucht Artikel-Zugaenge zum Lagerbestand
   *    
   *    @param  zugang  Artikel-Zugang --> darf nicht negativ sein 
   */
  public void bucheZugang ( int zugang )
  {
   if ( zugang < MIN_ZUGANG )
     {
      throw new RuntimeException( ZUGANG_NEGATIV_MELDUNG);
     }

   bestand += zugang;
  }


  /**
   *    bucht Artikel-Abgaenge vom Lagerbestand
   *    
   *    @param  abgang  Artikel-Abgang  --> darf nicht negativ sein
   *                                    --> muss  &lt aktuellem Bestand sein
   */
  public void bucheAbgang ( int abgang )
  {
   if ( abgang < MIN_ABGANG )
     {
      throw new RuntimeException( ABGANG_NEGATIV_MELDUNG);
     }
   if ( bestand - abgang < MIN_BESTAND )
     {
      throw new RuntimeException( ABGANG_ZUGROSS_MELDUNG);
     }

   bestand -= abgang;
  }


  /**
   *    aenderePreis aendert Artikel-Preis
   *    
   *    @param  prozent - positive Prozentzahl == Preiserhoehung, negative = Preisverminderung
   */
  public void aenderePreis ( double prozent )
  {
   if ( prozent < MIN_PROZENT )
     {
      throw new RuntimeException( PROZENT_MELDUNG);
     }
  
   preis += preis * prozent/100.0;
  }
 
  


 /**
   *    erzeugt einen Beschreibungs-String, der eine Kurzbeschreibung
   *    des Artikels beinhaltet
   *
   *    @return eine Kurzbeschreibung des Artikels
   *    
   */
  public String getBeschreibung ()
  {
   return String.format( "  %20s", bezeichnung );
  }

  
  /**
   *    erzeugt einen Artikel-String
   *    
   */
  public String toString ()
  {
   return  String.format( " %4d  %20s %6d %8.2f",
                         artikelNr, bezeichnung, bestand, preis
           );
  }

}
