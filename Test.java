
import java.util.*;

public class Test extends Question {
	private String[] options;
	private int numOfOptions;
	private ArrayList<String> labels = new ArrayList<>();

	public Test(){
		labels.add("A");
		labels.add("B");
		labels.add("C");
		labels.add("D");

	}
	public void setOptions(String[] options){
		this.options = options;
	}
	public String getOptionAt(int numOfOptions){
		return options[numOfOptions];
	}
	public String toString(){
		String sr = "";
		java.util.Collections.shuffle(Arrays.asList(options));
		for(int i = 0;i < 4;i++){
			sr += "\n" + labels.get(i) + ") " + options[i];
			
		}
		return sr;
	}


}