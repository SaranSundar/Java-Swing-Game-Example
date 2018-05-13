final public class FPScounter {
    private static int startTime;
    private static int endTime;
    private static int frameTimes = 0;
    private static short frames = 0;

    /**
     * Start counting the fps
     **/
    public void StartCounter() {
        //get the current time  
        startTime = (int) System.currentTimeMillis();
    }

    /**
     * stop counting the fps and display it at the console
     */
    public String StopAndPost() {
        //get the current time  
        endTime = (int) System.currentTimeMillis();
        //the difference between start and end times  
        frameTimes = frameTimes + endTime - startTime;
        //count one frame  
        ++frames;
        //if the difference is greater than 1 second (or 1000ms) post the results  
        if (frameTimes >= 1000) {
            //post results at the console  
            String fps = ("FPS: " + Long.toString(frames));
            //reset time differences and number of counted frames  
            frames = 0;
            frameTimes = 0;
            return fps;
        }
        else{
            return "wait";
        }
    }
}  