
public class Timer {
	private static long startTime;
	private static long endTime;
	private static boolean isStart;
	
	private static boolean isStopped() {
		return !isStart;
	}
	
	private static boolean isStarted() {
		return isStart;
	}
	
	private static void startTimer() {
		startTime=System.nanoTime();
		isStart=true;
	}
	
	/* start the time */
	public static void start() {
		if(isStopped())
			startTimer();
		else
			throw new RuntimeException("Timer has already started!\n");
	}
	
	private static void stopTimer() {
		endTime=System.nanoTime();
		isStart=false;
	}
	
	/* stop the time */
	public static void stop() {
		if(isStarted())
			stopTimer();
		else
			throw new RuntimeException("Timer didn't start!\n");
	}
	
	/* Finds the time in nanoseconds between Timer.start () and Timer.stop (). */
	public static long getElapsedTime() {
        if (isStopped()) {
            return endTime - startTime;
        } else {
            throw new RuntimeException("Time didn't stop!\n");
        }
 
    }
	
	/* Returns the time in mikro between Timer.start () and Timer.stop (). */
	public static double getElapsedMicroSeconds() {
        double seconds = (double) getElapsedTime() / 1000.0;
        return seconds;
    }
	
	/* Returns the time in miliseconds between Timer.start () and Timer.stop (). */
	public static double getElapsedMiliSeconds() {
        double seconds = (double) getElapsedTime() / 1000000.0;
        return seconds;
    }
 
    
     
	/* Returns the time in seconds between Timer.start () and Timer.stop (). */
    public static double getElapsedSeconds() {
        double seconds = (double) getElapsedTime() / 1000000000.0;
        return seconds;
    }  
	
}
