package client;

import dto.Mesaj;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ChatPanel {
    private JTextArea textArea1;
    private JPanel panel1;
    private JTextArea textArea2;
    private JButton sendButton;
    private final static String HOST = "localhost";
    private final static int PORT = 4545;
    private String nume;

    public ChatPanel(String nume) throws IOException {
        this.nume = nume;
        connectToServer();
    }

    private void connectToServer() throws IOException {
        Socket server = new Socket(HOST, PORT);
        System.out.println("Connected !");

        ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(server.getInputStream());


        new Thread(()-> {
            while (true){
                try {
                    Mesaj mesaj = (Mesaj) in.readObject();
                    SwingUtilities.invokeAndWait(()-> textArea1.append(mesaj+ "\n"));
                }catch (Exception e){
                    e.printStackTrace();
                 }
            }
        }).start();

        System.out.println("All good!");
        sendButton.addActionListener(ev -> {
            String continut = textArea2.getText();
            Mesaj mesaj = Mesaj.of(nume, continut);
            try {
                out.writeObject(mesaj);
                out.flush();
                textArea2.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
    }
    public JPanel getPanel(){
        return panel1;
    }
}
