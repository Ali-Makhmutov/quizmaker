public class QuizMaker{
	public static void main(String[] args) throws Exception {
		Quiz quiz = new Quiz();
		quiz = quiz.loadFromFile(args[0]);
		quiz.start();

	}
}