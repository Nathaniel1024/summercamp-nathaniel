package Login;

public class load {
    public static void main(String[] args) {
        // Create and display the splash screen
        splashScreen l = new splashScreen();
        l.setVisible(true);
        
        // Create the login form but set it to invisible initially
        formLogin m = new formLogin();
        m.setVisible(false);

        try {
            // Simulate a loading process with a progress bar
            for (int x = 0; x <= 100; x++) {
                // Pause the thread for 10 milliseconds to simulate the loading delay
                Thread.sleep(10);
                
                // Update the splash screen with the current progress percentage
                l.progress.setText(Integer.toString(x) + "%");
                l.bar.setValue(x);

                // Once loading reaches 100%, close the splash screen and show the login form
                if (x == 100) {
                    l.dispose();  // Close the splash screen
                    m.setVisible(true);  // Show the login form
                }
            }
        } catch (Exception e) {
            // Handle any exceptions that might occur during the loading process
        }
    }
}
