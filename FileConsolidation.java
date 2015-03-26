/*
 * Caelean Barnes
 *
 * FileConsolidation.java
 *
 * Consolidates all files in sub directories into a new directory
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.CopyOption.*;

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
        if(!file.getName().startsWith("FileConsolidation."))
        if(file.isDirectory())
          copyFiles(file, newDir);
        else
        {
          File copy = new File(newDir.getAbsolutePath() + "/" + file.getName());
          System.out.println(file.getName() + " copied to " + copy.toPath());
          Files.copy(file.toPath(), copy.toPath());
        }
      }
    }
  }
}
