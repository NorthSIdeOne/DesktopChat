package client;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainClient {

    public static void main(String[] args) throws IOException {
        String username = JOptionPane.showInputDialog("Username: ");


        ChatPanel chat = new ChatPanel(username);

        JFrame frame = new JFrame();
        frame.setContentPane(chat.getPanel());
        //Ii spune swing-ului sa ajusteze intaltimea si latimea in fuctie de componentele pe care le are pe fereastra
        //Sa se vada tot cum trebuie

        frame.setTitle(username);
        frame.pack();
        frame.setMinimumSize(new Dimension(970, 500));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}
