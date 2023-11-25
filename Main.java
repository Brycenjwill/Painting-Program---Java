import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//Useful tracking: getLocation, getSize(returns a dimension obj)

public class Main extends JFrame { 
    private MyMouseHandler handler;
    private myKeyHandler keyHandler;
    private Point location;
    private double cursorx;
    private double cursory;
    private double[] cursore;
    boolean clicked = false;
    private Color background = Color.WHITE;
    private double[][] drawn = new double[1][3];
    private Color[] drawnColors = new Color[1];
    private Color[] colors = {Color.green, Color.red, Color.black, Color.blue, Color.white};
    Color Curcolor = colors[0];

    int dex = 0;

    public Main() 
    { 
        System.out.println("Use the Left and Right arrow keys to change colors. Your current color is in the top right of the window. Avoid having the mouse over the window when changing keys, or you will draw unwanted circles.");
        handler  = new MyMouseHandler();
        this.addMouseListener( handler );
        keyHandler = new myKeyHandler();
        this.addKeyListener(keyHandler);

        setFocusable(true);

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
            } 
        }); 
        location = getLocationOnScreen();
        }
    } 

    public void paint(Graphics g) 
    { 

       g.setColor(background);
        g.fillRect(0, 0, 700, 500);
        g.setColor(Curcolor);
        g.fillRect(0,0,50,50);

        //Start drawing what has been drawn
        Color[] tempColors = new Color[drawnColors.length +1]; //Initialize a list to add drawn colors to
        int len = drawn.length-1;

        double[][] tempArray = new double[drawn.length +1][2]; //initialize new array to copy to as 1 larger than parent

        if(drawn.length >= 1){
        for(int i = 0; i < len; i++){ //While looping through array of previously draw, circles
            //Duplicate drawn and add one to end.
            tempArray[i] = drawn[i]; // copy existing draws over to temp array
            tempColors[i] = drawnColors[i]; //copy used color over to temp colors array
            g.setColor(tempColors[i]);
            g.fillOval((int)drawn[i][0]-12, (int)drawn[i][1]-12, 25, 25); //Draw current array item
            
        }
        drawn = tempArray; //Copy temp array over to drawn array
        drawnColors = tempColors;
    }
        //Finally, draw most recent item from where the cursor is now, and save this item to the end of the 
        g.setColor(Curcolor);
        g.fillOval((int)cursorx-12, (int)cursory-12, 25, 25); //Draw current item

        drawnColors[len] = Curcolor; //Add current color to end of used colors array
        drawn[len] = cursore;//Store current cursor position at the end of the drawn array=

    } 
  

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

                repaint();
            }
        }

      }
    }
    
    private class myKeyHandler extends KeyAdapter
    {
    public void keyPressed(KeyEvent e) {
  
          if (e.getKeyCode() == KeyEvent.VK_RIGHT) {


            if(dex == 4){
                dex = 0;
            }
            else{
                dex += 1;
            }


            Curcolor = colors[dex];
            repaint();
          }
          if (e.getKeyCode() == KeyEvent.VK_LEFT) {


            if(dex == 0){
                dex = 4;
            }
            else{
                dex -= 1;
            }

  
            Curcolor = colors[dex];
            repaint();
          }
  
      }
    }
    public static void main(String[] args) 
    { 
        new Main(); 
    
    } 

}