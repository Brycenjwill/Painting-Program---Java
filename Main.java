import java.awt.*;
import java.awt.event.*;

//Useful tracking: getLocation, getSize(returns a dimension obj)

public class Main extends Frame { 
    private MyMouseHandler handler;
    private Point location;
    private double cursorx;
    private double cursory;
    boolean clicked = false;


    public Main() 
    { 
        double[] cursore;

        handler  = new MyMouseHandler();
        this.addMouseListener( handler );

        while(true){
        
        setVisible(true); 
        setSize(700, 500);

        cursore = TrackCursor(); // Method that returns mouse pos
        cursorx = cursore[0];
        cursory = cursore[1];

        addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) 
            { 
                System.exit(0); 
                return;
            } 
        }); 
        location = getLocationOnScreen();
        }
    } 

    public void paint(Graphics g) 
    { 
        g.fillOval((int)cursorx-12, (int)cursory-12, 25, 25); //Draw something
    } 
  
s
    public static double[] TrackCursor() 
    { 
        double[] cursor ={0,0};
    
        Point location = MouseInfo.getPointerInfo().getLocation();
        double x = location.getX();
        double y = location.getY();
        cursor[0] = x;
        cursor[1] = y;
        return cursor; //Return a tuple of cursor coordinantes
    } 

    private class MyMouseHandler extends MouseAdapter
    {
      public void mousePressed( MouseEvent e )
      {
        if(cursorx > location.getX() & cursorx < (int)location.getX()+700)
        {
            if(cursory > location.getY() & cursory < (int)location.getY()+500){
                System.out.println("Click!!!!");
                repaint();
            }
        }

      }
  
    }
    public static void main(String[] args) 
    { 
        new Main(); 
    
    } 

}