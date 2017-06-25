package ueb17;

 
import java.io.*;
    
/**
 *    ----> DateiNichtBearbeitbarException              
 *          --> Realisiert eine einfache DateiNichtBearbeitbarException
 *
 * @version	1.0 Beta 07.04.2017
 * @author	Wolfgang Pauly
 *
 */ 


class DateiNichtBearbeitbarException 
       extends Exception
{
 public DateiNichtBearbeitbarException()
  {
   super();
  }

 public DateiNichtBearbeitbarException( String meldung )
  {
   super( meldung );
  }
  
 /**
   *    ueberprueft, ob das uebergebene File 
   *    bearbeitbar ( lesbar und beschreibbar ) ist
   *    
   *    wirft falls nicht  eine DateiNichtBearbeitbarException 
   *    
   *    @param datei zu ueberpruefendes File
   *
   */
  public static void istBearbeitbar( File datei )
         throws DateiNichtBearbeitbarException
  {
    if  ( !datei.canWrite() || !datei.canRead() )
      {
        throw new DateiNichtBearbeitbarException( 
                        "Die Datei mit dem Namen --> " +
                        datei.getName() + 
                        "<--  ist nicht bearbeitbar !!!!\n\n"
                       );
      }
  }

    
 /**
   *    ueberprueft, ob das uebergebene File 
   *    lesbar  ist
   *    
   *    wirft falls nicht  eine DateiNichtBearbeitbarException 
   *    
   *    @param datei zu ueberpruefendes File
   *
   */
  public static void istLesbar( File datei )
         throws DateiNichtBearbeitbarException
  {
    if  ( !datei.canWrite() )
      {
        throw new DateiNichtBearbeitbarException( 
                        "Die Datei mit dem Namen --> " +
                        datei.getName() + 
                        "<--  ist nicht lesbar !!!!\n\n"
                       );
      }
  }
  
  
 /**
   *    ueberprueft, ob das uebergebene File 
   *    beschreibbar  ist
   *    
   *    wirft falls nicht  eine DateiNichtBearbeitbarException 
   *    
   *    @param datei zu ueberpruefendes File
   *
   */
  public static void istBeschreibbar( File datei )
         throws DateiNichtBearbeitbarException
  {
    if  ( !datei.canRead() )
      {
        throw new DateiNichtBearbeitbarException( 
                        "Die Datei mit dem Namen --> " +
                        datei.getName() + 
                        "<--  ist nicht beschreibbar !!!!\n\n"
                       );
      }
  }  
}
