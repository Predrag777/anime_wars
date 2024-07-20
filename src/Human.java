import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Human extends Fighter{

	//Po defaultu who=Human i serijal u kome se pojavio
	private String animeTitle;

	@SuppressWarnings("unused")
	public Human() throws NoFighterException {
		super("Human");
		try {
			BufferedReader buff=new BufferedReader(new FileReader("Fighters.txt"));
			String tempArr[]=null;
			Test.getA();int d=1;

			String line=buff.readLine();
			
			do {
				tempArr=line.split(", ");
				d++;
			}while((line=buff.readLine())!=null && !tempArr[1].equalsIgnoreCase(super.getWho()) || !Arena.isSafe(tempArr[0], Test.getA().getFighters()));

			if(line!=null && Arena.isSafe(tempArr[0], Test.getA().getFighters())) {
				this.animeTitle=tempArr[8];
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
		String result=super.generate().substring(0,super.generate().length());
		return result;
	}
	
	@Override
	public String toString() {
		String result=super.toString()+"\nAnime: "+animeTitle;	
		return result;///OVO POGLEDATI OBAVEZNO!!!!!!!!!!!!!!
	}
}



//Miroljub Petrovic, Human, 182, 80, Grey, Black, Mire.jpeg, BalkanInfo
