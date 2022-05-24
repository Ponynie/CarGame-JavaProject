import java.util.Random;

public class SpeedBoost {
    public String booster;
    public String operator;
    public String number;
    public static int count = 0;
    
    public SpeedBoost() {
        Random random = new Random();
        if (count == 0 || count == 1 ) {
            this.operator = "+" ;
            this.number = String.valueOf(random.nextInt(50) + 1);
            count++;
        }
        else {
            switch (random.nextInt(7)) {
                case 0 : this.operator = "+" ; break;
                case 1 : this.operator = "+" ; break;
                case 2 : this.operator = "-" ; break;
                case 3 : this.operator = "-" ; break;
                case 4 : this.operator = "x" ; break;
                case 5 : this.operator = "%" ; break;
                case 6 : this.operator = "%" ; break;
            }
            if (this.operator.equals("+") || this.operator.equals("-")) 
                this.number = String.valueOf(random.nextInt(50) + 1);
            else if (this.operator.equals("%")) 
                this.number = String.valueOf(random.nextInt(3) + 1);
            else 
                this.number = String.valueOf(random.nextInt(2) + 1);
        }
        this.booster = this.operator + this.number;
    }

    public String Display() {
        return this.booster;
    }
    public int CalculateSpeed(int currentSpeed) {
        if (this.operator.equals("+")) {
            return currentSpeed + Integer.parseInt(this.number);
        }
        else if (this.operator.equals("-")) {
            return currentSpeed - Integer.parseInt(this.number);
        }
        else if (this.operator.equals("x")) {
            return currentSpeed * Integer.parseInt(this.number);
        }
        else {
            return currentSpeed / Integer.parseInt(this.number);
        }
    }
}
