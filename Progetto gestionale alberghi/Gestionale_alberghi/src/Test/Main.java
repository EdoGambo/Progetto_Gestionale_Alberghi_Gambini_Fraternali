package Test;

import javax.swing.SwingUtilities;

import Hotel_view.MainView;


public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainView(); 
            }
        });
        
    }
}

