import javax.swing.*;
public class UiTraining {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Minecraft");
        JOptionPane.showMessageDialog(null, "Welt wurde erstellt");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel leben = new JLabel("HP: 100");
        leben.setBounds(frame.getWidth()/2, 10, 100, 30);

        JLabel waffe = new JLabel("Waffe: " + new Faust().name);
        waffe.setBounds(700, 50, 100,30);
        JButton button = new JButton("Schaden");

        button.setBounds(20, 70, 120, 40);

        button.addActionListener(e -> {

            leben.setText("HP: 90");

        });

        frame.add(leben);
        frame.add(waffe);
        frame.add(button);

        frame.setVisible(true);

    }
}
