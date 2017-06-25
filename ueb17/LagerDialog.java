package ueb17;

 
import java.io.*;
import java.util.Random;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;
    
/**
 *    Die Klasse:  LagerDialog.java
 *    Realisiert eine allgemeine Test-Klasse fuer
 *    die Lager-Klasse
 *
 * @version 3.0 Beta 13.01.2017
 * @author  Wolfgang Pauly
 *
 */

public class LagerDialog
{

//------------------KONSTANTEN----------------------------------
   private static final int  ERZEUGE_STANDARD_LAGER         =  0;
   private static final int  ERZEUGE_LAGER                  =  1;
   private static final int  LOESE_LAGER_AUF                =  2;
   private static final int  NEHME_ARTIKEL_IN_LAGER_AUF     =  3;
   private static final int  ENTFERNE_ARTIKEL_AUS_LAGER     =  4;
   private static final int  BUCHE_ZUGANG_BEI_LAGER_ARTIKEL =  5;
   private static final int  BUCHE_ABGANG_BEI_LAGER_ARTIKEL =  6;
   private static final int  PREISAENDERUNG                 =  7;
   private static final int  ZEIGE_BESTANDS_LISTE           =  8;
   private static final int  ZEIGE_LAGER                    =  9;

   private static final int  ERSTELLE_TEST_LAGER			= 50;
   private static final int  TEST_AUFGABE17_3_i				= 51;
   private static final int  TEST_AUFGABE17_3_ii			= 52;
   private static final int  TEST_AUFGABE17_3_iii			= 53;
   private static final int  TEST_AUFGABE17_3_iv				= 54;
   private static final int  TEST_AUFGABE17_3_v				= 55;
   private static final int  TEST_AUFGABE17_3_vi			= 56;
   private static final int  TEST_AUFGABE17_3_vii			= 57;
   private static final int  TEST_AUFGABE17_3_viii			= 58;
   private static final int  TEST_AUFGABE17_3_ix			= 59;

   
   private static final int  LAGER_IN_DATEI_SCHREIBEN       = 10;
   private static final int  LAGER_AUS_DATEI_EINLESEN       = 11;


   private static final int  ENDE                           = 99;



   private final String  FALSCHE_MENUPUNKT_AUSWAHL = "Ungueltige Menue-Auswahl !!!!";


   private enum auswahl { ARTIKEL, CD, DVD, BUCH };

   private StringBuffer auswahlString;
 

//--------- Eingabe Attribut --------------------------------
   private Scanner in;


//------------------Attribute-----------------------------------

  private Lager firmenLager;

//------------------Konstruktoren-------------------------------
  /**
    * Standard-Konstruktor fuer LagerDialog
    */
   public LagerDialog()
   {
    firmenLager = null;
    in = new Scanner( System.in );

    auswahlString = new StringBuffer ("\n\nWelcher ArtikelArt wollen Sie aufnehmen ???\n\t");
    for (auswahl a : auswahl.values()) 
       {
        auswahlString.append(a).append("\tdann gib ---> ")
                     .append(a.ordinal()).append("\n\t");
       }
    auswahlString.append("waehle : ");
   }



//------------------ run         -------------------------------
   /**
    * run --> laesst die benutzergesteuerte Bedienung 
    *         des Lager-Test's ablaufen
    * 
    */
   public void run()
   {
         int auswahl, artikelNr;
     
         String lagerOrt, dateiName;
         int    lagerGroesse;
         double prozent;
         
     auswahl = 0;
     
     while ( auswahl != ENDE )
     {
      try
        {
         auswahl = auswahlFunktion();
         switch (auswahl)
           {
            case ERZEUGE_STANDARD_LAGER :
                 existiertFirmenLager( false );
                 lagerOrt = MyInputFunctions.readlnString(in, "Geben sie den Ort des Lagers ein : ");
                 firmenLager = new Lager(lagerOrt);
                 break; 

            case ERZEUGE_LAGER :
                 existiertFirmenLager( false );
                 lagerOrt = MyInputFunctions.readlnString(in, "Geben sie den Ort des Lagers ein : ");
                 lagerGroesse = MyInputFunctions.readlnInt(in, "Geben sie die Groesse des Lagers an : ");
                 firmenLager = new Lager(lagerGroesse, lagerOrt);
                 break; 

            case LOESE_LAGER_AUF :
                 existiertFirmenLager( true );
                 firmenLager = null;
                 break; 

            case NEHME_ARTIKEL_IN_LAGER_AUF :
                 existiertFirmenLager( true );
                 erzeugeLagerPosition();
                 break;

            case ENTFERNE_ARTIKEL_AUS_LAGER :
                 existiertFirmenLager( true );
                 artikelNr =  MyInputFunctions.readlnInt(in, "\n\tGeben Sie die Artikel-Nummer " +
                                              " des zu entfernenden Artikels ein : "
                                             );
                 firmenLager.entfernen( artikelNr);
                 break;

            case BUCHE_ZUGANG_BEI_LAGER_ARTIKEL :
                 existiertFirmenLager( true );
                 artikelNr =  MyInputFunctions.readlnInt(in, "\n\tGeben Sie die Artikel-Nummer " +
                                              " des zu buchenden Artikels ein : "
                                             );
                 firmenLager.bucheZugang( artikelNr, erfrageZugang() );
                 break;

            case BUCHE_ABGANG_BEI_LAGER_ARTIKEL :
                 existiertFirmenLager( true );
                 artikelNr =  MyInputFunctions.readlnInt(in, "\n\tGeben Sie die Artikel-Nummer " +
                                              " des zu buchenden Artikels ein : "
                                             );
                 firmenLager.bucheAbgang( artikelNr, erfrageAbgang() );
                 break;

            case PREISAENDERUNG :
                 existiertFirmenLager( true );
                 prozent =  MyInputFunctions.readlnDouble(in, "\n\tGeben Sie Prozentzahl " +
                                               "\n\t\t --> positive Zahl == Preiserhoehung," +
                                               "\n\t\t --> negative Zahl == Preisverminderung" +
                                               "\n\t ein : "
                                              );
                 firmenLager.preisaenderung( prozent );
                 break;

            case ZEIGE_BESTANDS_LISTE :
                 existiertFirmenLager( true );
                 System.out.println( firmenLager.ausgebenBestandsListe() );
                 break;
                 
            case ZEIGE_LAGER :
                 existiertFirmenLager( true );
                 System.out.println( firmenLager );
                 break;
                 
           case LAGER_IN_DATEI_SCHREIBEN :
                      dateiName = MyInputFunctions.readlnString(in,
                                         "\n\tGebe den Dateinamen zum Speichern " +
                                         "des Lagers ein : "
                                        );
                      try
                        {
                         write( firmenLager, dateiName );
                        }
                      catch ( DateiExistiertException dexex )
                        {
                         System.out.println( dexex  );
                        }
                       break;
           case LAGER_AUS_DATEI_EINLESEN :
                      dateiName = MyInputFunctions.readlnString( in,
                                         "\n\tGebe den Dateinamen zum Fuellen " +
                                         "des Lagers ein : "
                                        );
                      try
                        {
                         firmenLager = read( dateiName );
                        }
                      catch ( DateiExistiertNichtException dexnex )
                        {
                         System.out.println( dexnex  );
                        }
                      catch ( DateiNichtBearbeitbarException dnbex )
                        {
                         System.out.println( dnbex  );
                        }
                      catch ( ClassNotFoundException cnfex )
                        {
                         System.out.println( "\n\t\t!!!!! In der Datei ist KEIN" +
                                             "korrektes Objekt gespeichert !!!!"
                                           );
                        }
                      break;
             
           	case ERSTELLE_TEST_LAGER:
       	   		erstelleTestLager();
       	   		break;
       	   	
           	case TEST_AUFGABE17_3_i:
        	   	existiertFirmenLager( true );
        	   	test17_3_i();
        	   	break;
        	  
           	case TEST_AUFGABE17_3_ii:
       	   		existiertFirmenLager( true );
       	   		test17_3_ii();
	       	   	break;
	       	   	
           	case TEST_AUFGABE17_3_iii:
       	   		existiertFirmenLager( true );
       	   		test17_3_iii();
       	   		break;
	       	   	
           	case TEST_AUFGABE17_3_iv:
       	   		existiertFirmenLager( true );
       	   		test17_3_iv();
	       	   	break;
	       	   	
           	case TEST_AUFGABE17_3_v:
       	   		existiertFirmenLager( true );
	       	   	test17_3_v();
	       	   	break;
           	
           	case TEST_AUFGABE17_3_vi:
       	   		existiertFirmenLager( true );
	       	   	test17_3_vi();
	       	   	break;
	       	   	
           	case TEST_AUFGABE17_3_vii:
       	   		existiertFirmenLager( true );
	       	   	test17_3_vii();
	       	   	break;
           	
           	case TEST_AUFGABE17_3_viii:
       	   		existiertFirmenLager( true );
	       	   	test17_3_viii();
	       	   	break;
           	
           	case TEST_AUFGABE17_3_ix:
       	   		existiertFirmenLager( true );
	       	   	test17_3_ix();
	       	   	break;
        	
            case ENDE :
                 System.out.println("\n\nWeiterhin viel Spass bei der Arbeit !!!\n\n");
                 break;

            default :
                 System.out.println("\n\n Falsche Auswahl-Eingabe !!!\n");
                 break;
           }
        }
      catch ( NumberFormatException nfex )
        {  
         System.err.println("Folgende Ausnahme ist aufgetreten : \n\t" +
                            nfex + "\n\n"
                           );
        } 
      catch ( RuntimeException rex )
        {
         System.err.println("Folgende Ausnahme ist aufgetreten : \n\t" +
                            rex.toString() + "\n\n"
                           );
         rex.printStackTrace();
        } 
      catch ( Exception ex )
        {
         System.err.println("Folgende Ausnahme ist aufgetreten : \n\t" +
                            ex + "\n\n"
                           );
         ex.printStackTrace();
        } 
        
     }
    }
   
   /**
    * testet die neuen Lager Funktionen aus Aufgabe 17.3
    * 
    */
   private void test17_3_i()
   {
	   //17.3.i
	   BiPredicate<Artikel, Artikel> sortierkriterium = (Artikel a, Artikel b) ->
	   {
		   //Sortierkriterium 1:
		   BiFunction<Artikel, Artikel, Integer> kriterium1 = (Artikel x, Artikel y) -> 
		   {
			   if (x instanceof Buch) 
			   {
				   if(y instanceof Buch)
					   return 0;
				   else return -1;
			   }
			   else if(x instanceof Cd)
			   {
				   if(y instanceof Cd)
					   return 0;
				   else if(y instanceof Buch)
					   return 1;
				   else
					   return -1;
			   }
			   else if(x instanceof Dvd)
			   {
				   if(y instanceof Dvd)
					   return 0;
				   else return 1;
			   }
			   return 0;
		   };
		   
		   //Alternative für Sortierkriterium 1:
		   BiFunction<Artikel, Artikel, Integer> kriterium1alt = (Artikel x, Artikel y) -> 
		   {
			   //alphabetische Sortierung nach Klassennamen
			   return x.getClass().getName().compareTo(y.getClass().getName());
		   };
		   
		   //Sortierkriterium 2:
		   BiFunction<Artikel, Artikel, Integer> kriterium2 = (Artikel x, Artikel y) -> 
		   {
			   return x.getBestand() - y.getBestand();
		   };
		   
		   //Sortierkriterium 3:
		   BiFunction<Artikel, Artikel, Integer> kriterium3 = (Artikel x, Artikel y) -> 
		   {
			   if(x.getPreis()-y.getPreis() > 0)
				   return 1;
			   else if (x.getPreis()-y.getPreis() < 0)
				   return -1;
			   else 
				   return 0;
		   };

		   int k1 = kriterium1.apply(a, b);
		   if(k1 != 0)
			   return k1 > 0 ? true : false;
		   else
		   {
			   int k2 = kriterium2.apply(a, b);
			   if(k2 != 0)
				   return k2 > 0 ? true : false;
			   else{
				   int k3 = kriterium3.apply(a, b);
				   return k3 >= 0 ? true : false;
			   }
		   }
	   };
	   Artikel[] sorted = firmenLager.getSorted(sortierkriterium);
	   for(int i=0; i<sorted.length;i++)
		   System.out.println(sorted[i]);
   }
   
   /**
    * testet die neuen Lager Funktionen aus Aufgabe 17.3
    * 
    */
   private void test17_3_ii(){
	   //17.3.ii
	   firmenLager.applyToArticles(a -> a.setPreis(a.getPreis()*0.9));
   }
   
   /**
    * testet die neuen Lager Funktionen aus Aufgabe 17.3
    * 
    */
   private void test17_3_iii(){   
	   //17.3.iii
	   firmenLager.applyToArticles(a -> a.setBezeichnung(a.getBezeichnung()+" Sonderangebot"));
   }
	   
   /**
    * testet die neuen Lager Funktionen aus Aufgabe 17.3
    * 
    */
   private void test17_3_iv(){
	   //17.3.iv
	   Consumer<Artikel> minus10Prozent =  a -> a.setPreis(a.getPreis()*0.9);
	   Consumer<Artikel> sonderangebot =  a -> a.setBezeichnung(a.getBezeichnung()+" Sonderangebot");
	   firmenLager.applyToArticles(minus10Prozent.andThen(sonderangebot));
   }
   
   /**
    * testet die neuen Lager Funktionen aus Aufgabe 17.3
    * 
    */
   private void test17_3_v(){
	   //17.3.v
	   firmenLager.applyToSomeArticles(a -> a instanceof Cd, a->a.setPreis(a.getPreis()*1.1));
   }
   
   /**
    * testet die neuen Lager Funktionen aus Aufgabe 17.3
    * 
    */
   private void test17_3_vi(){
	   //17.3.vi
	   firmenLager.applyToSomeArticles(a -> a.getBestand()<= 2, a->a.setPreis(a.getPreis()*0.95));
   }
   
   /**
    * testet die neuen Lager Funktionen aus Aufgabe 17.3
    * 
    */
   private void test17_3_vii(){
	   //17.3 vii
	   String gesuchterAutor = "Goethe";
	   Predicate<Artikel> filterAuthor = a ->
	   {
		 if(a instanceof Buch)
		 {
			 if(((Buch) a).getAutor().equals(gesuchterAutor))
				 return true;
		 }
		 return false;
	   };
	   firmenLager.applyToSomeArticles(filterAuthor, a->a.setPreis(a.getPreis()*0.95));
   }
	   
   /**
    * testet die neuen Lager Funktionen aus Aufgabe 17.3
    * 
    */
   private void test17_3_viii(){
	   //17.3.viii
	   String gesuchterAutor = "Goethe";
	   Predicate<Artikel> filterAuthor = a ->
	   {
		 if(a instanceof Buch)
		 {
			 if(((Buch) a).getAutor().equals(gesuchterAutor))
				 return true;
		 }
		 return false;
	   };
	   
	   firmenLager.applyToArticles(a -> {
		   if(a.getBestand()<= 2)
			   a.setPreis(a.getPreis()*0.95);

		   if(filterAuthor.test(a))
			   a.setPreis(a.getPreis()*0.95);
	   });
	   
	 //17.3.viii Alternative
	   Consumer<Artikel> l1 = a -> {
		   if(a.getBestand()<= 2)
			   a.setPreis(a.getPreis()*0.95);
	   };
	   Consumer<Artikel> l2 = a -> {
		   if(filterAuthor.test(a))
			   a.setPreis(a.getPreis()*0.95);
	   };
	   firmenLager.applyToArticles(l1.andThen(l2));
   }
   /**
    * testet die neuen Lager Funktionen aus Aufgabe 17.3
    * 
    */
   private void test17_3_ix(){
	   //17.3.ix
	   Artikel[] result = firmenLager.getArticles(a -> a instanceof Buch,  (a, b) -> 
		   ((Buch)a).getAutor().compareTo(((Buch)b).getAutor()) >= 0 ? true: false
	   );
	   for(int i=0;i<result.length;i++)
		   System.out.println(result[i]);
   }

   /**
    *  Erfragt vom Benutzer die als naechstes auszufuehrende Aktion
    *
    *  @return das Aktionskennzeichen
    */
    private int auswahlFunktion()
    {
      System.out.println (
             "\nErzeuge STANDARD-Lager              gib ---> " +
             ERZEUGE_STANDARD_LAGER +
             "\nErzeuge Lager                       gib ---> " +
             ERZEUGE_LAGER +
             "\nLoese Lager auf                     gib ---> " +
             LOESE_LAGER_AUF +
             "\n------------------------------------------------------" +
             "\nErzeuge  Artikel                    gib ---> " +
             NEHME_ARTIKEL_IN_LAGER_AUF +
             "\nLoesche  Artikel                    gib ---> " +
             ENTFERNE_ARTIKEL_AUS_LAGER +
             "\nBuche Zugang bei einem  Artikel     gib ---> " +
             BUCHE_ZUGANG_BEI_LAGER_ARTIKEL +
             "\nBuche Abgang bei einem  Artikel     gib ---> " +
             BUCHE_ABGANG_BEI_LAGER_ARTIKEL +
             "\n------------------------------------------------------" +
             "\nAendere Preise fuer alle Artikel    gib ---> " +
             PREISAENDERUNG +
             "\n------------------------------------------------------" +
             "\nZeige Bestandsliste des Lagers an   gib ---> " +
             ZEIGE_BESTANDS_LISTE +
             "\n\tLager in Datei speichern       -> " +
             LAGER_IN_DATEI_SCHREIBEN +
             "\n\tLager aus Datei einlesen          -> " +
             LAGER_AUS_DATEI_EINLESEN +
             "\nZeige Lager an                      gib ---> " +
             ZEIGE_LAGER +
             "\n------------------------------------------------------" +
             "\n" +
             "\n----------Tests für Aufgabe 17.3----------------------" +
             "\nErstelle Test-Lager					gib --->" +
             ERSTELLE_TEST_LAGER + 					
             "\nTest Aufgabe 17.3 i                 gib ---> " +
             TEST_AUFGABE17_3_i + 		
             "\nTest Aufgabe 17.3 ii                gib ---> " +
             TEST_AUFGABE17_3_ii + 	
             "\nTest Aufgabe 17.3 iii               gib ---> " +
             TEST_AUFGABE17_3_iii + 	
             "\nTest Aufgabe 17.3 iv                gib ---> " +
             TEST_AUFGABE17_3_iv + 	
             "\nTest Aufgabe 17.3 v                 gib ---> " +
             TEST_AUFGABE17_3_v + 	
             "\nTest Aufgabe 17.3 vi                gib ---> " +
             TEST_AUFGABE17_3_vi + 	
             "\nTest Aufgabe 17.3 vii               gib ---> " +
             TEST_AUFGABE17_3_vii + 	
             "\nTest Aufgabe 17.3 viii              gib ---> " +
             TEST_AUFGABE17_3_viii + 	
             "\nTest Aufgabe 17.3 ix                gib ---> " +
             TEST_AUFGABE17_3_ix + 	
             
             "\n" +
             "\n------------------------------------------------------" +
             "\nBeende Test                         gib ---> " +
             ENDE
            );
       return MyInputFunctions.readlnInt(in, "\t\t\t\tgib --->");
   }


   /**
    *  Ueberprueft ob schon ein Firmenlager vorhanden ist,
    * 
    *  @param bedingung == false -- bei Vorhandensein des Lagers wird eine
    *                               Runtime-Exception geworfen
    *                   == true  -- bei NICHT-Vorhandensein des Lagers wird eine
    *                               Runtime-Exception geworfen
    */
    private void existiertFirmenLager( boolean bedingung )
    {
        if ( bedingung )
          {
           if ( firmenLager == null )
             {
              throw new RuntimeException( " Firmenlager existiert noch nicht !!!! -- zuerst Lager anlegen ");
             };
          }
        else
         {
          if ( firmenLager != null )
            {
             throw new RuntimeException( " Firmenlager existiert schon !!!  -- zuerst Lager aufloesen ");
            }
         }
  
    }
 
   

   /**
    *  Erfragt vom Benutzer die Daten fuer einen Artikel
    *  und veranlasst das Lager den erzeugen Artikel
    *  aufzunehmen
    * 
    */
   private void erzeugeLagerPosition()
           throws Exception
   {
       auswahl was = null;
       int wasEingabe = -10;

       //Attribute eines Artikels
       int    artikelNr, artikelBestand, artikelMindestBestand;
       double artikelPreis;
       String artikelBezeichnung;

       //Zusatz-Attribute einer CD
       String interpret, titel;
       int    anzahlMusikTitel;

       //Zusatz-Attribute eines DVD
       float  spieldauer;
       int    erscheinungsjahr;

       //Zusatz-Attribute eines Buches
       String autor, verlag;



      // wir fragen was soll erzeugt werden ???-1
      while ( (wasEingabe < 0) || (wasEingabe > auswahl.values().length) )
        {
          wasEingabe = MyInputFunctions.readlnInt(in,  auswahlString.toString() );
          if (wasEingabe >= 0 && wasEingabe < auswahl.values().length)
            {
             was = auswahl.values()[wasEingabe];
            }
        }
      


      // wir lesen die allgemeinen Artikel-Daten
      artikelNr =
             MyInputFunctions.readlnInt(in, "\n\tGeben Sie die Artikel-Nummer ein : ");
      artikelBezeichnung =
             MyInputFunctions.readlnString(in, "\n\tGeben Sie die Artikel-Bezeichnung ein : ");
      artikelBestand =
             MyInputFunctions.readlnInt(in, "\n\tGeben Sie den Artikel-Bestand ein : ");
      artikelPreis =
             MyInputFunctions.readlnDouble(in, "\n\tGeben Sie den Artikel-Preis ein : ");


      switch ( was )
        {
         case ARTIKEL :
                  firmenLager.anlegen( new Artikel( artikelNr, artikelBezeichnung,
                                                     artikelBestand,
                                                     artikelPreis
                                                 )
                                     );
                  break;
         case CD :
                  interpret =
                     MyInputFunctions.readlnString(in, "\n\tGeben Sie den Cd-Interpreten  ein : ");
                  titel =
                     MyInputFunctions.readlnString(in, "\n\tGeben Sie den Cd-Titel ein : ");
                  anzahlMusikTitel =
                     MyInputFunctions.readlnInt(in, "\n\tGeben Sie die MusikTitel-Anzahl ein : ");


                  firmenLager.anlegen( new Cd( artikelNr, artikelBezeichnung,
                                               artikelBestand,
                                               artikelPreis, interpret,
                                               titel, anzahlMusikTitel
                                             )
                                     );
                  break;
         case DVD :
                  titel =
                     MyInputFunctions.readlnString(in, "\n\tGeben Sie den Dvd-Titel ein : ");
                  spieldauer =
                     MyInputFunctions.readlnFloat(in, "\n\tGeben Sie die Dvd-Spieldauer ein : ");
                  erscheinungsjahr =
                     MyInputFunctions.readlnInt(in, "\n\tGeben Sie das Dvd-Erscheinugnsjahr ein : ");

                  firmenLager.anlegen( new Dvd( artikelNr, artikelBezeichnung,
                                               artikelBestand,
                                               artikelPreis, titel,
                                               spieldauer, erscheinungsjahr
                                             )
                                    );
                  break;
         case BUCH :
                  autor =
                     MyInputFunctions.readlnString(in, "\n\tGeben Sie den Buch-Autor  ein : ");
                  titel =
                     MyInputFunctions.readlnString(in, "\n\tGeben Sie den Buch-Titel ein : ");
                  verlag =
                     MyInputFunctions.readlnString(in, "\n\tGeben Sie den Buch-Verlag ein : ");
                  firmenLager.anlegen( new Buch( artikelNr, artikelBezeichnung,
                                                artikelBestand,
                                                artikelPreis, autor,
                                                titel, verlag
                                              )
                                    );
                  break;
        }
   } 
   


   /**
    *  Erfragt vom Benutzer den Lagerzugang fuer einen Artikel
    *  
    *  @return die Zugangs-Zahl
    */
   private int erfrageZugang()
   {
    return MyInputFunctions.readlnInt(in, "\n\tGeben Sie die Artikel-Zugangszahl ein : ");
   }
   
   
   /**
    *  Erfragt vom Benutzer den Lagerabgang fuer einen Artikel
    *  
    *  @return die Abgangs-Zahl
    */
   private int erfrageAbgang()
   {
    return MyInputFunctions.readlnInt(in, "\n\tGeben Sie die Artikel-Abgangszahl ein : ");
   }   
   

  /**
   *    write --> Speichert die Lager in Datei
   *
   *    @param dateiName    der Dateiname
   *
   */
  public void write( Lager einLager, String dateiName )
         throws IOException, DateiExistiertException
  {
         ObjectOutputStream  out;
         File datei = new File( dateiName );

   DateiExistiertException.existiertDatei( datei );

   out = new ObjectOutputStream(
             new BufferedOutputStream(
                 new FileOutputStream( datei )
                 )
             );
   out.writeObject( einLager );
   out.close();
   out = null;
  }

/**
   *    read -> Liest  ein Lager aus Datei ein
   *
   *    @param    der Dateiname
   *
   */
  public Lager read(  String dateiName )
         throws DateiExistiertNichtException, DateiNichtBearbeitbarException,
                IOException, ClassNotFoundException
  {
         ObjectInputStream in;
         File datei = new File( dateiName );
         Lager leseLager = null;

   DateiExistiertNichtException.existiertDatei( datei );
   DateiExistiertNichtException.istDatei( datei );
   DateiNichtBearbeitbarException.istLesbar( datei );

   try
     {
       in = new ObjectInputStream(
               new BufferedInputStream(
                   new FileInputStream( datei )
                   )
               );
       leseLager = (Lager)in.readObject();
       in.close();
     }
   catch ( FileNotFoundException fnex )
     {
      DateiExistiertNichtException.existiertDatei( datei );
     }
   return leseLager;
  }
  
  /**
   * erstellt ein neues, zufällig befülltes Lager
   */
  private void erstelleTestLager()
  {
	  Random ran = new Random();
	  int anzahlBuecher = ran.nextInt(10);
	  int anzahlDvds = ran.nextInt(10);
	  int anzahlCds = ran.nextInt(10);
	  int lagerGroesse = 2 + anzahlBuecher + anzahlCds + anzahlDvds;
	  firmenLager = new Lager(lagerGroesse, "Saarbruecken");
	  
	  firmenLager.anlegen(new Buch(ran.nextInt(9000)+1000, "buch"+ran.nextInt(10000), ran.nextInt(5), ran.nextDouble() * 100, "Goethe", "titel"+ran.nextInt(100), "verlag"+ran.nextInt(100)));
	  firmenLager.anlegen(new Buch(ran.nextInt(9000)+1000, "buch"+ran.nextInt(10000), ran.nextInt(5), ran.nextDouble() * 100, "Goethe", "titel"+ran.nextInt(100), "verlag"+ran.nextInt(100)));  
	  for(int i=0; i<anzahlDvds; i++)
		  firmenLager.anlegen(new Dvd(ran.nextInt(9000)+1000, "dvd"+ran.nextInt(10000), ran.nextInt(100), ran.nextDouble() * 100, "titel"+ran.nextInt(100), ran.nextFloat() * 100, 2000));
	  for(int i=0; i<anzahlCds; i++)
		  firmenLager.anlegen(new Cd(ran.nextInt(9000)+1000, "cd"+ran.nextInt(10000), ran.nextInt(50), ran.nextDouble() * 100, "interpret"+ran.nextInt(100), "titel"+ran.nextInt(100), ran.nextInt(19)+1 ));
	  for(int i=0; i<anzahlBuecher; i++)
		  firmenLager.anlegen(new Buch(ran.nextInt(9000)+1000, "buch"+ran.nextInt(10000), ran.nextInt(5), ran.nextDouble() * 100, "autor"+ran.nextInt(100), "titel"+ran.nextInt(100), "verlag"+ran.nextInt(100)));
  }

   
   /**
    *  main
    */	 
   public static void main( String[] args )
   {
    LagerDialog tester = new LagerDialog();
    tester.run();
   }
}
