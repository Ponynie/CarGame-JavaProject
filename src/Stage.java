import javax.swing.*;

public class Stage extends JFrame {
    Dynamic dynamic;
    public Stage(String name) {
        super(name);
        dynamic = new Dynamic();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(dynamic);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }
}
