import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Collections;

public class Quiz extends Question{
	private String name;
	private ArrayList<Question> questions = new ArrayList<>();

	public Quiz(){

	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void addQuestion(Question question){
		questions.add(question);
	}
	public Quiz loadFromFile(String pathname) throws Exception{
		File file = new File(pathname);
		Scanner in = null;
		Quiz quiz = new Quiz();
		quiz.setName(pathname);
		try{ 
			in = new Scanner(file);
			
			if(!in.hasNext()){

				throw new InvalidQuizFormatException("file has not line"); 
			}
			
			while(in.hasNextLine()){
				String des = in.nextLine();
				if(des.equals("")){
				continue; 
				}
				//String des = in.nextLine();
				//String an = in.nextLine();
				if(des.contains("{blank}")){
					String an = in.nextLine();
					FillIn qu = new FillIn();
					qu.setDescription(des);
					qu.setAnswer(an);
					quiz.addQuestion(qu);

				}else{ 
					Test que = new Test();
					que.setDescription(des);
					
					String[] option = new String[4];
					for(int i = 0;i < 4;i++){
						String er = in.nextLine();
						option[i] = er;
					}
					que.setAnswer(option[0]);
					que.setOptions(option);
					quiz.addQuestion(que);

				}
			}
		}
		catch(InvalidQuizFormatException e){
			e.printStackTrace();
			System.exit(0);
		}
		catch(FileNotFoundException a){
			System.out.println("Such a file does not exist!");
			System.exit(0);
		}				
		return quiz;
	}
	public void start(){
		setName("JavaQuiz");
		java.util.Collections.shuffle(questions);
		System.out.println("==================================");
		System.out.println("WELCOME T0 \""+ getName() +"\" QUIZ!");
		System.out.println("__________________________________\n");
		Scanner input = new Scanner(System.in);
		Scanner scan = null;
		int right = 0;
		for(int i = 0;i < questions.size();i++){
			String q = (questions.get(i).getDescription());
				
			if(q.contains("{blank}")){
				System.out.println((i+1)+". "+q.replace("{blank}","_____"));
				System.out.println("---------------------------------------------");
				System.out.print("Type your answer: ");			
				String a = input.next();
				if((questions.get(i).getAnswer()).equalsIgnoreCase(a)){
					System.out.println("Correct!");
					System.out.println("__________________________________");
					right++;
				}else{
					System.out.println("Incorrect!");
					System.out.println("__________________________________");
				}

			}else{
				System.out.print((i+1)+". "+q);			
				System.out.println(questions.get(i).toString());
				System.out.println("------------------------------");
				System.out.print("Enter the correct choice: ");
				String a = input.next();
				int y = -1;
				while(y!=0){
					if(a.length()==1 && (a.equals("A") || a.equals("B") || a.equals("C") || a.equals("D"))) {
						y = 0;
						continue;
					}
					System.out.print("Invalid choice! Try again (Ex: A, B, ...): ");
					a = input.next();
				}
				int x = 0;
				if(a.equals("A")){
					x=0;
				}
				if(a.equals("B")){
					x=1;
				}
				if(a.equals("C")){
					x=2;
				}
				if(a.equals("D")){
					x=3;
				}
				String ansUser = ((Test)(questions.get(i))).getOptionAt(x);
				String rightAns = questions.get(i).getAnswer();
				if(ansUser.equals(rightAns)){
					System.out.println("Correct!");
					System.out.println("__________________________________");
					right++;
				}else{
					System.out.println("Incorrect!");
					System.out.println("__________________________________");
				}

			}
		}
		double percent = 100.0*right/questions.size();
		System.out.println("Correct Answers: " + right + "/" + questions.size() + " " + "(" + percent+"%)" );
	}
}