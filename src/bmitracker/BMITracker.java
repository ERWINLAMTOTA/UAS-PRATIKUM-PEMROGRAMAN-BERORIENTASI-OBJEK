package bmitracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BMITracker extends JFrame {

    private JLabel heightLabel, weightLabel, bmiLabel, recommendationLabel;
    private JTextField heightField, weightField, ageField;
    private JComboBox<String> genderComboBox, activityComboBox, goalComboBox;
    private JButton calculateButton, resetButton, backButton;
    private JTextArea resultArea, calorieArea, activityRecommendationArea;
    private JPanel leftPanel, rightPanel;

    public BMITracker() {
        setTitle("BMI Tracker & Kalkulator Kalori");
        setSize(1000, 700);  // Resize to fit new layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set main layout to BorderLayout
        setLayout(new BorderLayout(20, 20));

        // Left Panel (Input fields)
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);  // Add padding

        // Title Label
        JLabel titleLabel = new JLabel("BMI Tracker dan Kalkulator Kalori", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(34, 102, 122));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        leftPanel.add(titleLabel, gbc);

        // Height Label and Field (using cm)
        heightLabel = new JLabel("Tinggi Badan (cm):");
        heightLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        leftPanel.add(heightLabel, gbc);

        heightField = new JTextField();
        gbc.gridx = 1;
        leftPanel.add(heightField, gbc);

        // Weight Label and Field
        weightLabel = new JLabel("Berat Badan (kg):");
        weightLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        leftPanel.add(weightLabel, gbc);

        weightField = new JTextField();
        gbc.gridx = 1;
        leftPanel.add(weightField, gbc);

        // Age Label and Field
        JLabel ageLabel = new JLabel("Usia (tahun):");
        ageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        leftPanel.add(ageLabel, gbc);

        ageField = new JTextField();
        gbc.gridx = 1;
        leftPanel.add(ageField, gbc);

        // Gender ComboBox (Pria/Wanita)
        JLabel genderLabel = new JLabel("Jenis Kelamin:");
        genderLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        leftPanel.add(genderLabel, gbc);

        String[] genderOptions = {"Pria", "Wanita"};
        genderComboBox = new JComboBox<>(genderOptions);
        gbc.gridx = 1;
        leftPanel.add(genderComboBox, gbc);

        // Activity ComboBox (Tingkat Aktivitas)
        JLabel activityLabel = new JLabel("Tingkat Aktivitas:");
        activityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 5;
        leftPanel.add(activityLabel, gbc);

        String[] activityOptions = {"Sedentari", "Ringan", "Moderate", "Berat"};
        activityComboBox = new JComboBox<>(activityOptions);
        gbc.gridx = 1;
        leftPanel.add(activityComboBox, gbc);

        // Goal ComboBox (Tujuan)
        JLabel goalLabel = new JLabel("Tujuan:");
        goalLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 6;
        leftPanel.add(goalLabel, gbc);

        String[] goalOptions = {"Menurunkan Berat Badan", "Mempertahankan Berat Badan", "Menambah Massa Otot"};
        goalComboBox = new JComboBox<>(goalOptions);
        gbc.gridx = 1;
        leftPanel.add(goalComboBox, gbc);

        // Calculate Button
        calculateButton = new JButton("Hitung Kalori & BMI");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 16));
        calculateButton.setBackground(new Color(34, 102, 122));
        calculateButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        leftPanel.add(calculateButton, gbc);

        // Reset Button
        resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.setBackground(new Color(255, 77, 77));
        resetButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        leftPanel.add(resetButton, gbc);

        // Add the left panel to the left part of the layout
        add(leftPanel, BorderLayout.WEST);

        // Right Panel (Results)
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(new Color(240, 240, 240));

        // BMI label and result
        bmiLabel = new JLabel("BMI Anda:");
        bmiLabel.setFont(new Font("Arial", Font.BOLD, 18));
        rightPanel.add(bmiLabel);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.BOLD, 20));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setBackground(new Color(245, 245, 245));
        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        rightPanel.add(resultScrollPane);

        // Calorie label and result
        JLabel calorieLabel = new JLabel("Kebutuhan Kalori:");
        calorieLabel.setFont(new Font("Arial", Font.BOLD, 18));
        rightPanel.add(calorieLabel);

        calorieArea = new JTextArea();
        calorieArea.setEditable(false);
        calorieArea.setFont(new Font("Arial", Font.BOLD, 20));
        calorieArea.setLineWrap(true);
        calorieArea.setWrapStyleWord(true);
        calorieArea.setBackground(new Color(245, 245, 245));
        JScrollPane calorieScrollPane = new JScrollPane(calorieArea);
        rightPanel.add(calorieScrollPane);

        // Activity recommendation label and result
        JLabel activityRecommendationLabel = new JLabel("Rekomendasi Aktivitas:");
        activityRecommendationLabel.setFont(new Font("Arial", Font.BOLD, 18));
        rightPanel.add(activityRecommendationLabel);

        activityRecommendationArea = new JTextArea();
        activityRecommendationArea.setEditable(false);
        activityRecommendationArea.setFont(new Font("Arial", Font.BOLD, 20));
        activityRecommendationArea.setLineWrap(true);
        activityRecommendationArea.setWrapStyleWord(true);
        activityRecommendationArea.setBackground(new Color(245, 245, 245));
        JScrollPane activityRecommendationScrollPane = new JScrollPane(activityRecommendationArea);
        rightPanel.add(activityRecommendationScrollPane);

        // Add the right panel to the right part of the layout
        add(rightPanel, BorderLayout.CENTER);

        // Calculate Button Action
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double height = Double.parseDouble(heightField.getText());  // height in cm
                    double weight = Double.parseDouble(weightField.getText());
                    int age = Integer.parseInt(ageField.getText());
                    String gender = (String) genderComboBox.getSelectedItem();
                    String activity = (String) activityComboBox.getSelectedItem();
                    String goal = (String) goalComboBox.getSelectedItem();

                    // Calculate BMI
                    double bmi = weight / Math.pow(height / 100, 2); // Convert height to meters
                    resultArea.setText(String.format("BMI Anda: %.2f", bmi));

                    // Calculate Calories and Activity Recommendations
                    double dailyCalories = calculateCalories(age, weight, height, gender, activity);
                    calorieArea.setText(String.format("Kebutuhan Kalori Harian: %.2f kcal", dailyCalories));

                    String activityRecommendation = getActivityRecommendation(goal);
                    activityRecommendationArea.setText(activityRecommendation);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Harap masukkan data yang valid.");
                }
            }
        });

        // Reset Button Action
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                heightField.setText("");
                weightField.setText("");
                ageField.setText("");
                resultArea.setText("");
                calorieArea.setText("");
                activityRecommendationArea.setText("");
            }
        });
    }

    // Method to calculate daily calories based on Harris-Benedict formula
    private double calculateCalories(int age, double weight, double height, String gender, String activity) {
        double bmr = 0;
        if (gender.equals("Pria")) {
            bmr = 88.362 + (13.397 * weight) + (4.799 * height) - (5.677 * age); // Height in cm
        } else {
            bmr = 447.593 + (9.247 * weight) + (3.098 * height) - (4.330 * age); // Height in cm
        }

        double activityMultiplier = 1.2;
        switch (activity) {
            case "Ringan":
                activityMultiplier = 1.375;
                break;
            case "Moderate":
                activityMultiplier = 1.55;
                break;
            case "Berat":
                activityMultiplier = 1.725;
                break;
        }

        return bmr * activityMultiplier;
    }

    // Method to get activity recommendation based on user's goal
    private String getActivityRecommendation(String goal) {
        String recommendation = "";
        switch (goal) {
            case "Menurunkan Berat Badan":
                recommendation = "Rekomendasi Aktivitas: Cobalah latihan kardio seperti lari, bersepeda, atau berenang 4-5 kali seminggu.";
                break;
            case "Mempertahankan Berat Badan":
                recommendation = "Rekomendasi Aktivitas: Lakukan latihan aerobik moderat dan latihan kekuatan 2-3 kali seminggu.";
                break;
            case "Menambah Massa Otot":
                recommendation = "Rekomendasi Aktivitas: Fokus pada latihan kekuatan dan angkat beban untuk membangun massa otot.";
                break;
        }
        return recommendation;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BMITracker().setVisible(true);
            }
        });
    }
}
