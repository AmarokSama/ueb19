package ueb17;

 
import java.io.*;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

    
/**
 *    Die Klasse:  Lager.java
 *    Realisiert eine allgemeine Lager-Klasse fuer
 *    Artikel.
 *
 * @version	2.61 Beta 13.01.2017
 * @author	Wolfgang Pauly
 *
 */

public class Lager
{
//------------------Konstanten----------------------------------
  
  public  static final int     STANDARD_DIMENSION = 5;
  public  static final int     STANDARD_OFFSET = 5;

  public  static final String  STANDARD_LAGER_ORT = 
                               " (Ort noch nicht bestimmt) ";
  
  private static final String  DIMENSION_UNGUELTIG =
          "Ein zu konstruierendes Lager muss mindestens 1 Artikel aufnehmen koennen !";
  private static final String  LAGERORT_UNGUELTIG =
          "Der Lagerort-String muss initialisiert und nicht Leer sein !";

  private static final String  LAGER_KOMPLETT_BESETZT =
          "Im Lager sind schon alle Artikel-Lager-Plaetze besetzt !";
  private static final String  ARTIKEL_NICHT_IN_LAGER =
          "Verlangter Buchungs-Artikel nicht in Lager !!!!";
  private static final String  ARTIKEL_SCHON_IN_LAGER =
          "Anzulegender Artikel schon in Lager !!!!";
  
//------------------Attribute----------------------------------
  
  private  Artikel[] lager;
  private  int       letzterBesetzterIndex;
  private  String    lagerOrt;

//------------------Konstruktoren-------------------------------


  /**
   *    Der Konstruktor zur Standard-Initialisierung 
   *    des Lagers
   *        
   */
  public Lager ()
  {
   this( STANDARD_DIMENSION, STANDARD_LAGER_ORT );
  }



  /**
   *    Der Konstruktor zur Initialisierung 
   *    eines Lagers mit einer maximalen Anzahl von Artikel-Lagerplaetzen
   *        
   *    @param dimension  == Anzahl der maximal im Lager fuehrbaren Artikel
   */
  public Lager (int dimension)
  {
   this( dimension, STANDARD_LAGER_ORT );
  }


  /**
   *    Der Konstruktor zur Initialisierung 
   *    eines Lagers mit einem Lagerort und Standard-Artikel--Lagerplaetzen
   *        
   *    @param lagerOrt == Ort an dem das Lager existiert
   */
  public Lager (String lagerOrt)
  {
   this( STANDARD_DIMENSION, lagerOrt );
  }


  /**
   *    Der Konstruktor zur Initialisierung 
   *    eines Lagers mit einer maximalen Anzahl von Artikel-Lagerplaetzen
   *        
   *    @param dimension  == Anzahl der maximal im Lager fuehrbaren Artikel
   *    @param lagerOrt == Ort an dem das Lager existiert
   */
  public Lager (int dimension, String lagerOrt)
  {
   if (dimension <= 0)     
     {
      throw new RuntimeException ( DIMENSION_UNGUELTIG );
     };

   if ( (lagerOrt == null) || (lagerOrt.trim().length() == 0))
     {
      throw new RuntimeException ( LAGERORT_UNGUELTIG );
     };

   this.lagerOrt = new String(lagerOrt); 

   lager = new Artikel[ dimension ];
   letzterBesetzterIndex = -1;

   for ( int lauf = 0; lauf < dimension; lauf++)
     {
      lager[lauf] = null;
     }
  }

//------------------ set-/get-lagerOrt---------------------------------

  /**
   *    gibt Lager-Attribut : lagerOrt zurueck
   *
   *    @return    lagerOrt
   */
  public String getLagerOrt ( )
  {
    return lagerOrt;
  }


  /**
   *    setzt Lager-Attribut :  lagerOrt
   *   
   *    @param lagerOrt     neuer Lager-Ort
   */
  public void setLagerOrt ( String lagerOrt )
  {
   if ( (lagerOrt == null)   ||
        (lagerOrt.trim().length() == 0)
      )
     {
      throw new RuntimeException ( LAGERORT_UNGUELTIG );
     };

   this.lagerOrt = lagerOrt.trim();
  }


//------------------Artikel anlegen  ---------------------------------

  /**
   *    anlegen eines neuen Artikels und ins Lager einordnen
   *
   *    @param einArtikel  der anzulegende Artikel
   */
  public void anlegen ( Artikel einArtikel )
  {
    if ( sucheArtikel( einArtikel.getArtikelNr() ) != -1 )
     {
      throw new RuntimeException ( ARTIKEL_SCHON_IN_LAGER );
     }

    if ( letzterBesetzterIndex >=  lager.length  - 1 )
      {
       erweitere_lager();
      }

    lager[++letzterBesetzterIndex] = einArtikel;
  }



  /**
   *    erweitert die Lagerkapazitaet um
   *    STANDARD_OFFSET Lagerplaetze
   *
   */
  private void erweitere_lager ()
  {
    Artikel[] neuLager = new Artikel[ lager.length + STANDARD_OFFSET ];

    System.arraycopy( lager, 0, neuLager, 0, lager.length );

    for ( int lauf = lager.length; lauf < neuLager.length; lauf++ )
       {
        neuLager[lauf] = null;
       }
    lager = neuLager;

    // ???? neuLager = lager.clone();
  }



//------------------Artikel entfernen ----------------------------------

  /**
   *    entfernt den Artikel anhand seiner Artikel-Nummer aus dem Lager
   *   
   *    @param   loeschArtikelNr Nummer des zu loeschenden Artikels
   *
   */
  public void entfernen ( int loeschArtikelNr )
  {
    int fundstelle, schieber;

    //suche Artikel
    fundstelle = sucheArtikel( loeschArtikelNr );

    if ( fundstelle == -1 ) 
     {
      throw new RuntimeException ( ARTIKEL_NICHT_IN_LAGER );
     }

    //loesche Artikel
    lager[fundstelle] = null;
    letzterBesetzterIndex--;

    //schiebe Lager zusammen
    for ( schieber = fundstelle; schieber <= letzterBesetzterIndex; schieber++ )
       {
        lager[schieber] = lager[schieber + 1];
       }

    if ( schieber + 1 < lager.length )
      {
       lager[schieber + 1] = null;
      }
  }



//------------------ zugang buchen  --------------------------------

  /**
   *    bucht einen Zugang von Artikeln zu dem durch die Artikel-Nummer 
   *    identifizierten Artikel.
   *    
   *    @param   artikelNummer Artikel-Nummer, des zu buchenden Artikels
   *    @param zugang hinzukommende Artikel-Anzahl
   */
  public void bucheZugang ( int artikelNummer, int zugang )
  {
    int artikelIndex = sucheArtikel( artikelNummer );

    if ( artikelIndex == -1 )     
     {
      throw new RuntimeException ( ARTIKEL_NICHT_IN_LAGER );
     };

    lager[artikelIndex].bucheZugang( zugang );
  }


//------------------- abgang buchen  --------------------------------

  /**
   *    bucht einen Abgang von Artikeln von dem durch die Artikel-Nummer 
   *    identifizierten Artikel.
   *    
   *    @param   artikelNummer Artikel-Nummer, des zu buchenden Artikels
   *    @param abgang weggehende Artikel-Anzahl
   */
  public void bucheAbgang ( int artikelNummer, int abgang )
  {
    int artikelIndex = sucheArtikel( artikelNummer );

    if ( artikelIndex == -1 )     
     {
      throw new RuntimeException ( ARTIKEL_NICHT_IN_LAGER );
     };

    lager[artikelIndex].bucheAbgang( abgang );
  }




  /**
   *    preisaenderung - erhoeht, vermindert die Preise
   *                     aller Lagerartikel
   *
   *    @param  prozent - positive Prozentzahl == Preiserhoehung, negative = Preisverminderung 
   */
  public void preisaenderung ( double prozent )
  {
    for ( int lauf = 0; lauf <= letzterBesetzterIndex; lauf++ )
       {
        lager[lauf].aenderePreis( prozent );
       }
  }
 

//------------------- hilfs-Methoden --------------------------------

  /**
   *    sucheArtikel - sucht im Lager Array
   *               nach dem Vorkommen eines Artikels anhand
   *               der Artikel-Nummer
   *
   *    @param suchArtikelNr - die zu suchende Artikelnummer
   *    @return Index des gefundenen Artikels oder -1, falls der Artikel nicht
   *            im Lager enthalten ist.
   */
  public int sucheArtikel ( int suchArtikelNr )
  {
   int lauf, gefunden;
  
    for ( lauf = 0, gefunden = -1;
          ( (lauf <= letzterBesetzterIndex) && (gefunden == -1) );
          lauf++
        )
       {
        if ( lager[lauf].getArtikelNr() == suchArtikelNr )
          {
            gefunden = lauf;
          }
       }
    return gefunden;
  }





//------------------ ausgebenBestandsListe --------------------------

  /**
   *    erzeugt einen String, der eine Bestandsliste repraesentiert
   *
   *    @return die String-Repraesentation der Lager-Bestandsliste
   */
  public String ausgebenBestandsListe ()
  {

    double zeilenWert, gesamtWert = 0;

    StringBuilder bestandsListe = new StringBuilder();
    Formatter formatierer = new Formatter( bestandsListe, Locale.GERMAN );

    formatierer.format( "\n-----B e s t a n d s L i s t e --------" +
                        "-----------------------------------------" +
                        "-------------------" +
                        "\n\nLagerort : %20s \n\n %6s   %-45s %10s %10s %10s" +
                        "\n--------------------------------------" +
                        "-----------------------------------------" +
                        "-------------------",
                        lagerOrt, "ArtNr", "Beschreibung", "Preis",
                        "Bestand", "Gesamt"
                       );

    for ( int lauf = 0; lauf <= letzterBesetzterIndex; lauf++ )
      {
        zeilenWert = lager[lauf].getPreis() * lager[lauf].getBestand();
        gesamtWert += zeilenWert;

        formatierer.format( "\n %6d   %45s %10.2f %10d %10.2f ",
                            lager[lauf].getArtikelNr(),
                            lager[lauf].getBeschreibung(),
                            lager[lauf].getPreis(),
                            lager[lauf].getBestand(),
                            zeilenWert
                          );
        // hier waere es evtl sinnvoll in den Klassen Artikel, Cd, Dvd und Buch 
        // eine Methode getBestandsZeile() zu programmieren, falls man auf
        // diese Klassen "quellcode-maessig" zugreifen kann.
      }

    formatierer.format( "\n--------------------------------------" +
                        "-----------------------------------------" +
                        "-------------------"
                       );
    formatierer.format( "\n Gesamtwert:                     " +
                        "                                   " +
                        "%20.2f", gesamtWert
                       );

   return bestandsListe.toString();
  }




//------------------ toString  --------------------------------------

  /**
   *    erzeugt einen String, der alle, fuer den Klassenbenutzer
   *    wichtigen, Lager-Merkmale enthaelt
   *   
   *    @return die String-Repraesentation des Lagers
   */
  public String toString ()
  {

    StringBuffer lagerString = new StringBuffer(
                               String.format( "\n\nIm Lager : %20s sind von %5d Lagerplaetzen %5d ",
                                               lagerOrt, lager.length, (letzterBesetzterIndex + 1)
                               )
                 );
                               
    lagerString.append( " belegt, mit folgenden Artikeln : \n");

    for ( int lauf = 0; lauf <= letzterBesetzterIndex; lauf++ )
      {
        lagerString.append( String.format( "\n\t %3d \t-> %s", lauf, lager[lauf]) );
      }
 

   return lagerString.toString();
  }




	//------------------ getSorted  --------------------------------------
	/**
	 * Gibt die Artikel im Lager als sortiertes Array zurück. 
	 * Das Sortierkriterium wird als Parameter an die Methode übergeben.
	 * 
	 * @param c - ein Prädikat-Objekt, welches das Sortierkriterium implementiert  
	 * @return ein sortiertes Array der Artikel im Lager 
	 */
	public Artikel[] getSorted(BiPredicate<Artikel, Artikel> c){
		Artikel[] result = lager.clone();
		sort(c, result);
		return result;
	}
	
	/**
	 * Hilfsmethode zum Sortieren eines übergebenen Arrays
	 * 
	 * @param c - ein Prädikat-Objekt, welches das Sortierkriterium implementiert
	 * @param a - das zu sortierende Array
	 */
	private void sort(BiPredicate<Artikel, Artikel> c, Artikel[] a){
		//Zum Sortieren wird der Bubblesort-Algorithmus verwendet. 
		//Dies ist nicht sehr effizient, jedoch einfach zu implementieren: 
		for (int i=a.length; i>0; i--){
			for (int j=0; j<a.length-1; j++){
				if (c.test(a[j],a[j+1])){
					swap(j, j+1, a);
				} 
			}
		}
	}
	
	//------------------ getSorted  --------------------------------------
	/**
	 * Alternative Implementierung der getSorted Methode, 
	 * unter Nutzung der sort Methode des List-Interfaces.
	 * @param c - ein Comparator-Objekt zum Vergleich zweier Artikel
	 * @return eine sortierte Liste der Artikel im Lager
	 */
	public List<Artikel> getSorted(Comparator<Artikel> c){
		List<Artikel> result = Arrays.asList(lager);
		result.sort(c);
		return result;
	}
	
	//------------------ swap  --------------------------------------
	/**
	 * Hilfsmethode zum Sortieren eines Arrays. Die Methode vertauscht 
	 * die beiden Elemente mit den Indizes i und j eines übergebenen Arrays. 
	 * 
	 * @param i - Index des ersten Elements
	 * @param j - Index des zweiten Elements
	 * @param a - Array für die Vertauschung 
	 */
	private void swap(int i, int j, Artikel[] a){
		Artikel tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;		
	}
	
	//------------------ filter  --------------------------------------
	/**
	 * Filtert die Artikel im Lager nach einem an die Methode übergebenen Filterkriterium. 
	 * 
	 * @param f - ein Prädikat-Objekt, welches das Filterkriterium implementiert
	 * @return eine Liste alle Artikel im Lager, die das Filterkriterium erfüllen. 
	 */
	public List<Artikel> filter(Predicate<Artikel> f)
	{
		List<Artikel> result = new ArrayList<Artikel>();
		for (int i=0; i<lager.length; i++)
			if(f.test(lager[i]))
				result.add(lager[i]);
		return result; 
	}
	
	//------------------ applyToArticles  --------------------------------------
	/**
	 * wendet eine übergebene Operation auf alle Artikel im Lager an 
	 * 
	 * @param c - ein Consumer der die auf die Artikel anzuwendende Operation implementiert
	 */
	public void applyToArticles(Consumer<Artikel> c)
	{
		for (int i=0; i<lager.length; i++)
			c.accept(lager[i]); 
	}
	
	//------------------ applyToSomeArticles  --------------------------------------
	/**
	 * wendet eine übergebene Operation auf alle Artikel, welche ein Filterkriterium erfüllen an
	 * 
	 * @param f - ein Prädikat-Objekt, welches das Filterkriterium implementiert
	 * @param c - ein Consumer der die auf die Artikel anzuwendende Operation implementiert
	 */
	public void applyToSomeArticles(Predicate<Artikel> f, Consumer<Artikel> c)
	{
		for(Artikel a : filter(f))
			c.accept(a);
	}
		
	//------------------ getArticles  --------------------------------------
	/**
	 * liefert eine sortierte Liste der Artikel, die ein gegebenes Filterkriterium erfüllen
	 * 
	 * @param f - ein Prädikat-Objekt, welches das Filterkriterium implementiert
	 * @param c - ein Prädikat-Objekt, welches das Sortierkriterium implementiert
	 * @return eine sortierte Liste der gefilterten Artikel
	 */
	public Artikel[] getArticles(Predicate<Artikel> f, BiPredicate<Artikel, Artikel> c)
	{
		List<Artikel> filtered = filter(f);
		Artikel[] result = filtered.toArray(new Artikel[filtered.size()]);
		sort(c, result);
		return result;
	}
}

