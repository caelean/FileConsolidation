/**
 * @author Caelean Barnes
 * @version 1.0
 *  
 * FileConsolidation.java
 *
 * Consolidates all files in the current directory and in 
 * sub directories into a new directory
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class FileConsolidation
{
  public static void main(String[] args)
  {

    try
    {
      // Get the current directory
      File currentDir = new File(".");
      // Make the new directory
      File newDir = new File("CompiledDirectory");
      // Make sure it doesn't already exist
      if(!newDir.exists())
      {
        // Create the directory
        newDir.mkdir();
        System.out.println("New Directory Created");
      }
      // Don't create the directory
      else
        System.out.println("Directory Already exists");

      // Copy the files from the current directory into the new one
      copyFiles(currentDir, newDir);

    }
    // Exception handling
    catch(Exception e)
    {
      System.out.println("Exception");
      e.printStackTrace();
    }
  }
  
  /**
   * Copies all of the files in currentDir and it's subdirectories into newDir
   *
   * @param currentDir The current directory to copy files from
   *
   * @param newDir The new directory to copy files to
   */
  private static void copyFiles(File currentDir, File newDir) throws Exception
  {
    // Retrieve all of the files in the current directory
    File[] files = currentDir.listFiles();
    // Make sure they exist
    if(files != null)
    {
      // Iterate through them
      for(File file : files)
      {
        // Don't copy the java/class file (itself) or hidden file/directories
        if(!file.getName().startsWith("FileConsolidation.") 
            && !file.getName().startsWith("."))
          // If it's a directory, copy the files inside it to newDir
          if(file.isDirectory())
            copyFiles(file, newDir);
          // Otherwise it's a file
          else
          {
            // Get the path of the new directory
            String path = newDir.getAbsolutePath();
            // Create the copy
            File copy = new File(path + "/" + file.getName());
            
            System.out.println(file.getName() + " copied to " + copy.toPath());
            // Copy over the file. If it already exists, replace it
            Files.copy(file.toPath(), copy.toPath(), 
                StandardCopyOption.REPLACE_EXISTING);
          }
      }
    }
  }
}
