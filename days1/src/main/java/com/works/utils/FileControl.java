package com.works.utils;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Date;

public class FileControl {

    public boolean fileCreate() {
        boolean status = true;
      try {
          LocalDateTime now = LocalDateTime.now();
          String path = now.getYear()+"_"+now.getMonthValue()+"_"+now.getDayOfMonth();
          path = path+".txt";
          File file = new File(path);
          if ( !file.exists() ) {
              file.createNewFile();
          }
      }catch (Exception ex) {
          System.err.println("File Error : " + ex);
          status = false;
      }
      return status;
    }

}
