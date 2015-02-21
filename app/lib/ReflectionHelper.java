/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lib;

/**
 *
 * @author Tiko Banyini <tiko@falcorp.co.za>
 */
public class ReflectionHelper {
    
    /**
   * Checks whether the specified class contains a field matching the specified name.
   *
   * @param clazz The class to check.
   * @param fieldName The field name.
   *
   * @return Returns <code>true</code> if the cass contains a field for the specified name, <code>
   *         false</code> otherwise.
   */
  public static boolean containsField(Class<?> clazz, String fieldName) {
    try {
      clazz.getDeclaredField( fieldName );
      return true;
    }
    catch ( NoSuchFieldException e ) {
      return false;
    }
  }
}
