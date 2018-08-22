package 채팅프로그램_symmetric;

public class Symmetric_Key { 
	
	public String Encrypt(String text,int key){
		char[] temp = text.toCharArray();
		String result="";
		
		for(int i=0; i<text.length(); i++){
			if(temp[i] == ' ')
				temp[i] = ' ';
			else{
				for(int j=0; j<key; j++){
					temp[i]++;
					if(temp[i]=='{')
						temp[i]='a';
					if(temp[i]=='[')
						temp[i]='A';
				}
			}
			result = result + temp[i];
		}
		
		return result;
	}
	
	public String Decrypt(String text, int key){
		char[] temp = text.toCharArray();
		String result="";
		
		for(int i=0; i<text.length(); i++){
			if(temp[i] == ' ')
				temp[i] = ' ';
			else{
				for(int j=0; j<key; j++){
					temp[i]--;
					if(temp[i]=='`')
						temp[i]='z';
					if(temp[i]=='@')
						temp[i]='Z';
				}
			}
			result = result + temp[i];
		}
		return result;
	}
}
