/*
 * Caelean Barnes
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
      File currentDir = new File(".");
      File newDir = new File("CompiledDirectory");
      if(!newDir.exists())
      {
        newDir.mkdir();
        System.out.println("New Directory Created");
      }
      else
      {
        System.out.println("Directory Already exists");
        return;
      }
      copyFiles(currentDir, newDir);

    }
    catch(Exception e)
    {
      System.out.println("Exception");
      e.printStackTrace();
    }
  }

  private static void copyFiles(File currentDir, File newDir) throws Exception
  {
    File[] files = currentDir.listFiles();
    if(files != null)
    {
      for(File file : files)
      {
        if(!file.getName().startsWith("FileConsolidation.") 
                        && !file.getName().startsWith("."))
          if(file.isDirectory())
            copyFiles(file, newDir);
          else
          {
            String path = newDir.getAbsolutePath();
            File copy = new File(path + "/" + file.getName());
            System.out.println(file.getName() + " copied to " + copy.toPath());

            Files.copy(file.toPath(), copy.toPath(), 
                       StandardCopyOption.REPLACE_EXISTING);
          }
      }
    }
  }
}
