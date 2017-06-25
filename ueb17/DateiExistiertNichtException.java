package ueb17;

 
import java.io.*;
    
/**
 *    ----> DateiExistiertNichtException               
 *          --> Realisiert eine einfache DateiExistiertNichtException
 *
 * @version	1.1 Beta 07.04.2017
 * @author	Wolfgang Pauly
 *
 */
  
  
class DateiExistiertNichtException
       extends Exception
{
 public DateiExistiertNichtException()
  {
   super();
  }

 public DateiExistiertNichtException( String meldung )
  {
   super( meldung );
  }
  
 /**
   *    ueberprueft, ob das uebergebene File 
   *    ein Directory ist;
   *    
   *    wirft dann eine DateiExistiertNichtException 
   *    
   *    @param datei zu ueberpruefendes File
   *    @throws DateiExistiertNichtException
   *
   */
  public static void istDatei( File datei )
         throws DateiExistiertNichtException
  {
    if  ( datei.isDirectory() )
      {
        throw new DateiExistiertNichtException( 
                        "Die Datei mit dem Namen --> " +
                        datei.getName() + 
                        "<--  ist ein Directory !!!!\n\n"
                       );
      }
  }
  
  
 /**
   *    ueberprueft, ob das uebergebene File 
   *    existiert
   *    
   *    wirft dann eine DateiExistiertNichtException 
   *    
   *    @param datei zu ueberpruefendes File
   *    @throws DateiExistiertNichtException
   *
   */
  public static void existiertDatei( File datei )
         throws DateiExistiertNichtException
  {
    if  ( !datei.exists() )
      {
        throw new DateiExistiertNichtException( 
                        "Die Datei mit dem Namen --> " +
                        datei.getName() + 
                        "<-- existiert NICHT !!!!\n\n"
                       );
      }
  }
}

