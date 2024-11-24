import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SortingVisualization extends JFrame {
    private JTextField inputField;
    private JTextArea resultArea;
    private JComboBox<String> sortMethodComboBox;

    public SortingVisualization() {
        setTitle("Sorting Visualization");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel atas untuk input
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(new Color(60, 120, 180));
        JLabel label = new JLabel("Enter numbers separated by spaces:");
        label.setForeground(Color.WHITE);
        inputField = new JTextField(20);
        topPanel.add(label);
        topPanel.add(inputField);

        // Panel tengah untuk metode sorting
        JPanel middlePanel = new JPanel(new FlowLayout());
        middlePanel.setBackground(new Color(100, 150, 200));
        JLabel methodLabel = new JLabel("Choose sorting method:");
        methodLabel.setForeground(Color.WHITE);
        String[] sortMethods = {"Bubble Sort", "Selection Sort"};
        sortMethodComboBox = new JComboBox<>(sortMethods);
        JButton sortButton = new JButton("Sort");
        sortButton.setBackground(new Color(60, 180, 120));
        sortButton.setForeground(Color.WHITE);
        sortButton.setFocusPainted(false);
        sortButton.setFont(new Font("Arial", Font.BOLD, 14));
        middlePanel.add(methodLabel);
        middlePanel.add(sortMethodComboBox);
        middlePanel.add(sortButton);

        // Panel bawah untuk hasil
        JPanel bottomPanel = new JPanel(new BorderLayout());
        resultArea = new JTextArea();
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(230, 240, 250));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        // Menambahkan panel ke frame
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Set aksi untuk tombol sort
        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performSort();
            }
        });
    }

    // Method untuk sorting
    private void performSort() {
        String inputText = inputField.getText();
        String[] inputStrings = inputText.trim().split("\\s+");
        int[] numbers = new int[inputStrings.length];

        // Konversi input ke integer
        try {
            for (int i = 0; i < inputStrings.length; i++) {
                numbers[i] = Integer.parseInt(inputStrings[i]);
            }
        } catch (NumberFormatException e) {
            resultArea.setText("Invalid input! Please enter only numbers separated by spaces.");
            return;
        }

        // Sorting berdasarkan metode
        String selectedMethod = (String) sortMethodComboBox.getSelectedItem();
        resultArea.setText("Original array: " + Arrays.toString(numbers) + "\n");

        if ("Bubble Sort".equals(selectedMethod)) {
            bubbleSort(numbers);
        } else if ("Selection Sort".equals(selectedMethod)) {
            selectionSort(numbers);
        }

        resultArea.append("\nSorted array (" + selectedMethod + "): " + Arrays.toString(numbers));
    }

    // Bubble sort
    private void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            resultArea.append("\nAfter pass " + (i + 1) + ": " + Arrays.toString(array));
        }
    }

    // Selection sort
    private void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
            resultArea.append("\nAfter pass " + (i + 1) + ": " + Arrays.toString(array));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SortingVisualization app = new SortingVisualization();
            app.setVisible(true);
        });
    }
}
