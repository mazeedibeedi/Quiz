package Client;

import Interfaces.Question;
import Quiz.QuestionImpl;
import Server.QuizService;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Ahmed on 3/1/2015.
 */
public class SetupClient {
    private QuizService server;

    public void serverConnection() {
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(1099);
            server = (QuizService) registry.lookup("quiz");
            System.out.println("Connected to QuizServer");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }

    }

    public int newQuiz() throws RemoteException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Name of Quiz: ");
        String name = sc.nextLine();
        name = whileBlank(name);
        int id = server.newQuiz(name);
        System.out.println("Quiz " + name + " has ID " + id);
        return id;
    }

    private String whileBlank(String parameter) {
        Scanner sc = new Scanner(System.in);
        while (parameter.trim().isEmpty()) {
            System.out.println("Cannot be blank. Please try again");
            parameter = sc.nextLine();
        }
        return parameter;
    }

    public void addQuestions(int id) {
        boolean finished = false;
        List<Question> questions = new ArrayList<Question>();
        Scanner sc = new Scanner(System.in);
        while (!finished) {
            System.out.println("Please enter a question: ");
            String question = sc.nextLine();
            question = whileBlank(question);

            System.out.println("Please enter the correct answer to this question: ");
            String correctAnswer = sc.nextLine();
            correctAnswer = whileBlank(correctAnswer);

            System.out.println("Please enter the number of wrong answers you want to include (must be at least 3): ");
            while (!sc.hasNextInt()) {
                System.out.println("Please enter a number: ");
            }
            int size = sc.nextInt();
            while (size < 3) {
                System.out.println("Please enter a number greater than or equal to 3: ");
                size = sc.nextInt();
            }
            List<String> possibleAnswers = new ArrayList<String>();
            for (int i = 0; i < size; i++) {
                String answer = sc.nextLine();
                answer = whileBlank(answer);
                possibleAnswers.add(answer);
            }
            Question q = new QuestionImpl(question, correctAnswer, possibleAnswers);
            questions.add(q);
            System.out.println("Add another question? Y/N");
            String yesOrNo = sc.nextLine();
        }
    }
}
