package com.mycompany.mainproject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author iskandarguliyev
 */
import java.util.Arrays;
import java.util.Random;

//-------------- B) ------------------

public class MainProjectB {
    private double[] weights;
    private double bias;
    private double learningRate = 0.05; // Öğrenme oranı
    private int epochs = 10; // Epoch sayısı

    public MainProjectB(int inputSize) {
        // Girdi sayısı kadar rastgele küçük pozitif ağırlık oluştur
        Random random = new Random();
        this.weights = new double[inputSize];
        for (int i = 0; i < inputSize; i++) {
            this.weights[i] = random.nextDouble() * 0.01; // Daha küçük başlangıç ağırlıkları
        }
        this.bias = random.nextDouble() * 0.01;
    }

    public double forward(double[] inputs) {
        // Toplama işlemi: Girdiler ile ağırlıkların çarpımının toplamı
        double summation = 0;
        for (int i = 0; i < inputs.length; i++) {
            summation += inputs[i] * weights[i];
        }
        summation += bias;

        // Aktivasyon fonksiyonu: Doğrusal aktivasyon fonksiyonu
        double output = summation;

        return output;
    }

    // Ağırlıkları güncelle
    public void updateWeights(double[] inputs, double targetOutput) {
        double output = forward(inputs);
        double error = targetOutput - output;

        // Ağırlıkları güncelle, her bir ağırlığı ayrı ayrı güncelle
        for (int i = 0; i < weights.length; i++) {
            weights[i] += learningRate * error * inputs[i];
        }
        bias += learningRate * error;
    }

    public static void main(String[] args) {
        // Veri seti
        double[][] data = {
            {7.6, 11, 77},
            {8, 10, 70},
            {6.6, 8, 55},
            {8.4, 10, 78},
            {8.8, 12, 95},
            {7.2, 10, 67},
            {8.1, 11, 80},
            {9.5, 9, 87},
            {7.3, 9, 60},
            {8.9, 11, 88},
            {7.5, 11, 72},
            {7.6, 9, 58},
            {7.9, 10, 70},
            {8, 10, 76},
            {7.2, 9, 58},
            {8.8, 10, 81},
            {7.6, 11, 74},
            {7.5, 10, 67},
            {9, 10, 82},
            {7.7, 9, 62},
            {8.1, 11, 82}
        };

        // Girdi boyutu (özellik sayısı)
        int inputSize = data[0].length - 1;

        // Yapay sinir hücresi oluştur
        Neuron mainProject = new Neuron(inputSize);

        // Veri setini eğit
        for (int epoch = 0; epoch < mainProject.epochs; epoch++) {
            for (double[] example : data) {
                double[] inputs = Arrays.copyOfRange(example, 0,
                        inputSize);
                double targetOutput = example[inputSize];

                // Ağırlıkları güncelle
                mainProject.updateWeights(inputs, targetOutput);
            }
        }

        // Tahminleri ve hatayı listeleyerek yazdır
        System.out.println("Girdi Değerleri\tHedef Değer\tTahmini Değer");
        double mse = 0;
        for (double[] example : data) {
            double[] inputs = Arrays.copyOfRange(example, 0, inputSize);
            double targetOutput = example[inputSize];

            // Yapay sinir hücresinden çıktıyı al
            double output = mainProject.forward(inputs);

            // Hatayı topla
            mse += Math.pow(targetOutput - output, 2);

            // Yazdır
            System.out.printf("%.2f, %.2f\t%.2f\t\t%.2f%n",
                    (inputs[0])/10, inputs[1]/15, targetOutput/100,
                    output/100);
        }

        // MSE'yi yazdır
        mse /= data.length;
        System.out.println("MSE: " + mse);
    }
}
