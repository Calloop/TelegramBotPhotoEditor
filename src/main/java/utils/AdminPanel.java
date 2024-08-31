package utils;

import org.telegram.telegrambots.meta.generics.BotSession;

import javax.swing.*;
import java.awt.*;

public class AdminPanel extends JFrame {
    private final JButton startButton;
    private JButton stopButton;
    private BotSession botSession;
    private final DefaultListModel<String> model;

    public AdminPanel() {
        super("Панель администратора");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        model = new DefaultListModel<>();
        JList<String> userList = new JList<>(model);

        startButton = new JButton("Включить бота");
        startButton.addActionListener(e -> {
            if (botSession != null && !botSession.isRunning()) {
                try {
                    activateBot();
                    startButton.setEnabled(false);
                    stopButton.setEnabled(true);
                } catch (Exception ex) {
                    System.err.println("Ошибка запуска сессии: " + ex.getMessage());
                }
            }
        });

        stopButton = new JButton("Выключить бота");
        stopButton.setEnabled(false);
        stopButton.addActionListener(e -> {
            if (botSession != null && botSession.isRunning()) {
                try {
                    shutdownBot();
                    startButton.setEnabled(true);
                    stopButton.setEnabled(false);
                } catch (Exception ex) {
                    System.err.println("Ошибка остановки сессии: " + ex.getMessage());
                }
            }
        });

        JPanel panel = new JPanel(new BorderLayout());

        JScrollPane scrollPane = new JScrollPane(userList);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        pack();
        setVisible(true);
    }

    public void setSession(BotSession botSession) {
        this.botSession = botSession;
    }

    public void addUser(String userName) {
        if (!model.contains(userName)) {
            model.addElement(userName);
            System.out.println("Новый пользователь: " + userName);
        }
    }

    private void activateBot() {
            new Thread(() -> botSession.start()).start();
    }

    private void shutdownBot() {
            new Thread(() -> {
                botSession.stop();
                System.out.println("Бот остановлен");
            }).start();
    }
}