package com.prisao;
import java.util.Scanner;

import javax.swing.SwingUtilities;
import com.prisao.visao.AppGUI;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppGUI app = new AppGUI();
            app.setVisible(true);
        });
    }
}
