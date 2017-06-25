package ueb17;

 

import java.io.*;
    
/**
 *    ----> DateiExistiertException               
 *          --> Realisiert eine einfache DateiExistiertException
 *
 * @version	1.0 Beta 07.04.2017
 * @author	Wolfgang Pauly
 *
 */
  
class DateiExistiertException
       extends Exception
{
 public DateiExistiertException()
  {
   super();
  }

 public DateiExistiertException( String meldung )
  {
   super( meldung );
  }

  
 /**
   *    ueberprueft, ob das uebergebene File 
   *    existiert
   *    
   *    wirft dann eine DateiExistiertNichtException 
   *    
   *    @param datei zu ueberpruefendes File
   *
   */
  public static void existiertDatei( File datei )
         throws DateiExistiertException
  {
    if  ( datei.exists() )
      {
        throw new DateiExistiertException( 
                        "Die Datei mit dem Namen --> " +
                        datei.getName() + 
                        "<-- existiert !!!!\n\n"
                       );
      }
  }
}

