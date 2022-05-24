public class ProjectionLine {

    public static int LeftSideLine(int dely , int constant) {
        int delx =  -1 * ((dely - 1450 + constant) / 5);
        return delx;
    }
    public static int RightSideLine(int dely, int constant) {
        int delx = (dely + 2050 + constant) / 5;
        return delx;
    }
}
