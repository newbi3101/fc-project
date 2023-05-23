import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class flashcard implements ActionListener {
    private JFrame frame;
    private JButton addCard;
    private JButton editButton;
    private JPanel cardPanel;
    private JTextField question;
    private JTextField answer;
    private JButton doneButton;
    private JButton delCard;
    private JButton showAns;
    private JButton nextCP;
    private JButton backCP;
    private ArrayList<String> questions;
    private ArrayList<String> answers;
    private int currentIndex;

    public flashcard() {
        //------------MAIN FRAME-----------
        frame = new JFrame();
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        frame.setResizable(false);
        frame.setVisible(true);
    
        addCard = new JButton("Add new card");
        addCard.setBounds(50, 20, 150, 40);
        addCard.setFocusPainted(false);
        addCard.addActionListener(this);

        editButton = new JButton("Edit cards");
        editButton.setBounds(210, 20, 150, 40);
        editButton.setFocusPainted(false);
        editButton.addActionListener(this);
        //------------PANEL-----------
        cardPanel = new JPanel();
        cardPanel.setBackground(Color.gray);
        cardPanel.setLayout(new BorderLayout());
        cardPanel.setBounds(100, 300, 500, 350);

        frame.add(addCard);
        frame.add(editButton);
        frame.add(cardPanel);
        
        question = new JTextField("Enter question");
        question.setBounds(100, 120, 200, 30);

        answer = new JTextField("Enter answer");
        answer.setBounds(100, 180, 200, 30);

        doneButton = new JButton("Done");
        doneButton.setBounds(100, 220, 70, 40);
        doneButton.setFocusPainted(false);
        doneButton.addActionListener(this);

        showAns = new JButton("Show answer");
        showAns.setBounds(175, 290, 150, 40);
        showAns.setFocusPainted(false);
        showAns.addActionListener(this);

        nextCP = new JButton(">>>");
        nextCP.setBounds(335, 290, 150, 40);
        nextCP.setFocusPainted(false);
        nextCP.addActionListener(this);

        backCP = new JButton("<<<");
        backCP.setBounds(15, 290, 150, 40);
        backCP.setFocusPainted(false);
        backCP.addActionListener(this);

        delCard = new JButton("Del");
        delCard.setBounds(420, 10, 70, 40);
        delCard.setFocusPainted(false);
        delCard.addActionListener(this);

        questions = new ArrayList<>();
        answers = new ArrayList<>();
        currentIndex = 0;
    }

    public static void main(String[] args) {
        flashcard flashcard = new flashcard();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addCard) {
            frame.add(question);
            frame.add(answer);
            frame.add(doneButton);

            frame.repaint();
        }
        if (e.getSource() == doneButton) {
            String q = question.getText();
            String a = answer.getText();

            questions.add(q);
            answers.add(a);
            currentIndex = questions.size()-1;

            question.setText("Enter question");
            answer.setText("Enter answer");

            showCard();
            //------------FOR TESTING------------
            System.out.println("Current elems");
            System.out.println("Questions:");
            for (String question : questions) {
                System.out.println(question);
            }

            System.out.println("Answers:");
            for (String answer : answers) {
                System.out.println(answer);
            }
        }

        if (e.getSource() == showAns) {
            JOptionPane.showMessageDialog(frame, answers.get(currentIndex), "Answer", JOptionPane.INFORMATION_MESSAGE);
        }
        if (e.getSource() == nextCP) {
            currentIndex++;
            if (currentIndex >= questions.size()) {
                currentIndex = 0;
            }
            showCard();
        }
        if (e.getSource() == backCP) {
            currentIndex--;
            if (currentIndex < 0) {
                currentIndex = questions.size() - 1;
            }
            showCard();
        }
        if (e.getSource() == delCard) {
            if (!questions.isEmpty()) {
                questions.remove(currentIndex);
                answers.remove(currentIndex);
                currentIndex = 0;
                showCard();
            }
        }
    }

    private void showCard() {
        cardPanel.removeAll();

        if (!questions.isEmpty()) {
            JLabel cardLabel = new JLabel(questions.get(currentIndex));
            cardLabel.setHorizontalAlignment(JLabel.CENTER);
            cardLabel.setFont(new Font("Arial", Font.BOLD, 30));
            cardPanel.add(cardLabel);
            cardPanel.add(showAns);
            cardPanel.add(nextCP);
            cardPanel.add(backCP);
            cardPanel.add(delCard);
        }
        //frame.add(cardPanel);
        frame.repaint();
    }
}
