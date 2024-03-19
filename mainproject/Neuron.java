/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.mainproject;
//------------------ A,C -------------------
import java.util.Arrays;
import java.util.Random;

public class Neuron {
    private double[] weights;
    private double bias;
    private double learningRate = 0.000001; // Öğrenme oranı
    int epochs = 10000; // Epoch sayısı

    public Neuron(int inputSize) {
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
        for (int epoch = 0; epoch < epochs; epoch++) {
            double output = forward(inputs);
            double error = targetOutput - output;

            // Ağırlıkları güncelle, her bir ağırlığı ayrı ayrı güncelle
            for (int i = 0; i < weights.length; i++) {
                weights[i] += learningRate * error * inputs[i];
            }
            bias += learningRate * error;
        }
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

        // Yapay sinir hücresi oluşturuyoruz
        Neuron mainProject = new Neuron(inputSize);

        // Her bir örnek için ağırlıkları güncelle, çıktıları hesaplama ve yazdırma
        for (double[] example : data) {
            double[] inputs = Arrays.copyOfRange(example, 0, inputSize);
            double targetOutput = example[inputSize];

            // Ağırlıkları güncelleme
            mainProject.updateWeights(inputs, targetOutput);

            // Yapay sinir hücresinden çıktıyı alma
            double output = mainProject.forward(inputs);

            System.out.println("Girdiler: " + Arrays.toString(inputs) +
                    ", Gerçek Çıktı: " + targetOutput + ", Tahmini Çıktı: " + output);
        }
        
        // Tahmin için 5 yeni giriş verisi oluşturuyoruxz
        double[][] newData = {
            {8.2, 10, 75},
            {7.8, 9, 65},
            {9.2, 12, 90},
            {7.4, 10, 68},
            {8.5, 11, 78}
        };

        System.out.println("\nYeni Veri Tahminleri:");

        // Yeni veriler için tahminlerde bulunması
        for (double[] newExample : newData) {
            double[] newInputs = Arrays.copyOfRange(newExample, 0,
                    inputSize);

            // Eğitilmiş modelden çıktının alınması‚
            double newOutput = mainProject.forward(newInputs);

            System.out.println("Yeni Girişler: " + Arrays.toString(newInputs)
                    + ", Tahmin edilen çıktı: " + newOutput);
        }
    }
}
