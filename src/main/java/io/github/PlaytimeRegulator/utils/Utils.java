package github.PlaytimeRegulator.utils;

public class Utils {
    public static String prefix = ">>";

    public static String getInfoMessage() {
        return "";
    }

    public static String getFormattedTime(int timeInSeconds) {
        int hours = timeInSeconds / 3600;
        int secondsLeft = timeInSeconds - hours * 3600;
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String formattedTime = "";
        if (hours < 10)
            formattedTime += "0";
        formattedTime += hours + ":";

        if (minutes < 10)
            formattedTime += "0";
        formattedTime += minutes + ":";

        if (seconds < 10)
            formattedTime += "0";
        formattedTime += seconds ;

        return formattedTime;
    }

    public static int getCurrentHourInt() {
        return (int) System.currentTimeMillis() / 1000 / 60;
    }
}
