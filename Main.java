import java.awt.*; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 
  
public class Main extends Frame { 
    public Main() 
    { 
        while(true){
        CursorTrack.TrackCursor(); // Method that returns mouse pos
        setVisible(true); 
        setSize(300, 200); 
        addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) 
            { 
                System.exit(0); 
                return;
            } 
        
        }); 
        }
    } 
    public void paint(Graphics g) 
    { 
        g.fillOval(100, 100, 100, 50); 
    } 
  
    public static void main(String[] args) 
    { 
        new Main(); 

    } 
}
