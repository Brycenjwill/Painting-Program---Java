import java.awt.*; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 
  
// A class that keeps track of the current cursor location


public class CursorTrack extends Frame { 
    public static void TrackCursor() 
    { 
    
        Point location = MouseInfo.getPointerInfo().getLocation();
        double x = location.getX();
        double y = location.getY();

        System.out.println("Current Mouse POS: [" +x+ "," +y+ "]"); //Print current mouse pos
    } 
}

