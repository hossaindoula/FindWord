package dict;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class FindWord {
	public List<String> find(String word){
		File inputFile = new File("src/db/dict.dic");
		String  line = null;
		List<String> list = new ArrayList<String>();
		try{
			FileInputStream stream=new FileInputStream(inputFile);
			InputStreamReader reader=new InputStreamReader(stream); 
			BufferedReader br = new BufferedReader(reader);
			//for each word in dictionary
			while ((line = br.readLine()) != null) {
				boolean flag = true;
				if(word.length() >= line.length()){
					//for each character in source word
					for(int i=0; i<line.length();i++){
						word = word.toLowerCase();
						line = line.toLowerCase();
						//if no of occurrence of each character in dictionary is less than that in source word
						if((word+" ").split(line.charAt(i)+"").length >=  (line+" ").split(line.charAt(i)+"").length){
							continue;
						}
						flag = false;
						break;
					}
					
					if(flag){
						list.add(line);
					}

				}

			}   
			br.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	return list;
	}
	

}
