package Server;

import Interfaces.Question;
import Interfaces.Quiz;
import Interfaces.Score;
import Quiz.IdSingleton;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * The service in which the clients connect to in order to play/create quizzes
 * Created by Ahmed
 */
public interface QuizService extends Remote{
    /**
     * Returns the current ID
     * @return the current ID
     */
    public IdSingleton getQuizIDs() throws RemoteException;
    /**
     *
     * Creates a new quiz on the server and returns the quiz ID
     * @param quizName The name of the quiz
     * @return The ID of the newly created quiz
     */
    public int newQuiz(String quizName) throws RemoteException;

    /**
     * Returns the quiz with the specified ID
     * @param id The ID of the quiz
     * @return The quiz with the specified ID
     * @throws RemoteException
     */
    public Quiz getQuiz(int id) throws RemoteException;
    /**
     * Adds questions to a specified quiz ID
     * @param id The ID of the quiz that the questions will be placed in
     * @param questions A set of questions that will be added in the specified ID
     */
    public boolean addQuestions(int id, List<Question> questions) throws RemoteException;
    /**
     *
     * Closes the quiz with the given ID and returns the winner of the specified quiz
     * @param quizID The ID of the quiz
     * @return The full details of the winning player of the specified quiz
     */
    public Score closeQuiz(int quizID) throws RemoteException;
    /**
     *
     * Opens the quiz with the given ID
     * @param quizID The ID of the quiz
     */
    public void openQuiz(int quizID) throws RemoteException;
    /**
     *
     * Allows the specified player to play a quiz matching the given quiz ID with a given list of choices and returns that player's score
     * @param player The name of the player about to play the specified quiz
     * @param quizID The ID of the quiz about to be played
     * @param playerChoices A list of answers that the player has picked
     * @return The score the specific player achieved
     */
    public int playQuiz(String player, int quizID, List<String> playerChoices) throws RemoteException;
    /**
     *
     * Returns a list of the current available quizzes
     * @return List of all available quizzes
     */
    public List<Quiz> currentQuizzes() throws RemoteException;

    /**
     * Sets the current list of quizzes with the specified list
     * @param quizzes The list that will replace the current list
     * @throws RemoteException
     */
    public void setQuizzes(List<Quiz> quizzes) throws RemoteException;
    /**
     * Returns whether the answer selected by the player is the correct answer to the question
     * @param currentQuestion The question that is currently being asked
     * @param selectedAnswer The answer in which the player has selected
     */
    public boolean isCorrectAnswer(Question currentQuestion, String selectedAnswer) throws RemoteException;
    /**
     * Saves the changes to the server on a text file
     */
    public void flush() throws RemoteException;
}
