import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Nama : Muhammad Ryan Fikri Fakhrezi
 * NIM  : L0122114
 * Kelas: C
 */

public class PPBO_13_L0122114 extends JFrame implements ActionListener {
    private JTextField numField1, numField2, resultField;
    private JButton calculateButton;
    private JComboBox<String> operationChoice;

    public PPBO_13_L0122114() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 2, 10, 10));

        numField1 = new JTextField();
        numField2 = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        String[] operations = {"Penjumlahan", "Pengurangan", "Perkalian", "Pembagian"};
        operationChoice = new JComboBox<>(operations);

        calculateButton = new JButton("Hitung");
        calculateButton.addActionListener(this);

        add(new JLabel("Angka 1:"));
        add(numField1);
        add(new JLabel("Angka 2:"));
        add(numField2);
        add(new JLabel("Operasi:"));
        add(operationChoice);
        add(resultField);
        add(calculateButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double num1 = Double.parseDouble(numField1.getText());
        double num2 = Double.parseDouble(numField2.getText());
        String selectedOperation = (String) operationChoice.getSelectedItem();
        double result = 0;

        switch (selectedOperation) {
            case "Penjumlahan":
                result = num1 + num2;
                break;
            case "Pengurangan":
                result = num1 - num2;
                break;
            case "Perkalian":
                result = num1 * num2;
                break;
            case "Pembagian":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(this, "Pembagian dengan nol tidak dapat dilakukan.");
                }
                break;
        }

        resultField.setText(String.valueOf(result));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PPBO_13_L0122114());
    }
}
