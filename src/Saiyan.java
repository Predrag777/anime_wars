import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Saiyan extends Fighter{

	//Sajanci po defaultu imaju who=Saiyan, specijalne napade, 
	private String kiBlasts[];

	@SuppressWarnings("unused")
	public Saiyan() throws NoFighterException {
		super("Saiyan");
		try {
			BufferedReader buff=new BufferedReader(new FileReader("Fighters.txt"));
			String tempArr[]=null;
			Test.getA();int d=1;

			String line=buff.readLine();
			
			do {
				tempArr=line.split(", ");
			}while((line=buff.readLine())!=null && !tempArr[1].equalsIgnoreCase(super.getWho()) || !Arena.isSafe(tempArr[0], Test.getA().getFighters()));

			if(line!=null && Arena.isSafe(tempArr[0], Test.getA().getFighters())) {
				
				int num=Integer.parseInt(tempArr[8]);
				String temp[]=new String[num];//Ovde bi inace koristio ArrayList
				for(int i=0;i<num;i++) 
					temp[i]=tempArr[8+i];
				this.kiBlasts=tempArr;
				return;
			}
			throw new NoFighterException("Nema vise boraca tipa: "+super.getWho());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String generate() {
		String result=super.generate().substring(0,super.generate().length()-2);
		return result;
	}
	
	@Override
	public String toString() {
		String result=super.toString()+"\nKi Blasts: ";	
		String ki="";
		for(int i=0;i<kiBlasts.length;i++) {
			if(kiBlasts[i]!=null)
				ki+=kiBlasts[i]+", ";
		}
		ki=ki.substring(0,ki.length()-2);
		return result;///OVO POGLEDATI OBAVEZNO!!!!!!!!!!!!!!
	}
	
}
