import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Fighter implements GenerateID{

	private String who;//Koga trazimo
	//Svaki borac ima: ime, ID, visinu, tezinu, boju tela i glave i sliku
	//ID se generise preko interfejsa tako daa se sastoji od 3 broja i 3 karaktera
	private String name;
	private String id;
	private int height;
	private int weight;
	private String colorBody;
	private String colorHead;
	private String img;
	private String file;
	
	@SuppressWarnings("unused")
	public Fighter(String who) throws NoFighterException{
		super();
		this.who=who;
		try {
			BufferedReader buff=new BufferedReader(new FileReader("Fighters.txt"));
			String tempArr[]=null;
			Test.getA();
			
			String line=buff.readLine();
			int i=0;
			
			do {
				tempArr=line.split(", ");
			}while((line=buff.readLine())!=null && !tempArr[1].equalsIgnoreCase(who) || !Arena.isSafe(tempArr[0], Test.getA().getFighters()));
		
				this.name=tempArr[0];
				this.id=generate();
				this.height=Integer.parseInt(tempArr[2]);
				this.weight=Integer.parseInt(tempArr[3]);
				this.colorBody=tempArr[4];
				this.colorBody=tempArr[5];
				this.img=tempArr[6];
				this.file=tempArr[7];
				buff.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
		
	
	public String getWho() {
		return who;
	}
	

	public String getName() {
		return name;
	}
	
	
	public String getImg() {
		return img;
	}

	
	
	
	public String getFile() {
		return file;
	}



	@Override
	public String toString() {
		return "Name: "+name+"\nID: "+id+"\nHeight: "+height+"\nWeight: "+weight;
	}
	
	
	@Override
	public String generate() {
		// TODO Auto-generated method stub
		String result="",num="";
		Random rand=new Random();
		for(int i=0;i<3;i++){
			result+=(char)(rand.nextInt(25)+65);//Velika slova pocinju od 65 u ascci
			num+=rand.nextInt(8)+1;
		}
		return result+num;
	}
	
	
	
}
