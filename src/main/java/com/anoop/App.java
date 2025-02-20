package com.anoop;

import com.anoop.service.FileHandlerService;
import com.anoop.service.impl.FileHandlerServiceImpl;

/**
 * Count Words!!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        FileHandlerService handler = new FileHandlerServiceImpl();
        handler.processWordFile();
    }
}
