
import java.io.Serializable;
import java.util.Random;

public class Gate implements Serializable {

    public Gate() {

    }
    public String createGate() {
        Random r = new Random();
        int n = r.nextInt(3);
        String returner = "";
        switch (n) {
            case 0:
                returner = "A";
                break;
            case 1:
                returner = "B";
                break;
            case 2:
                returner = "C";
                break;
        }

        n = 1 + r.nextInt(18);
        returner += "" + n;

        return returner;

    }
}