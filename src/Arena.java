
public class Arena {

	private int num;
	private Fighter[] fighters;
	
	public Arena(int num) {
		super();
		if(num<=0)
			num=3;
		this.num=num;
		fighters=new Fighter[num];
	}

	
	
	
	public int getNum() {
		return num;
	}




	public Fighter[] getFighters() {
		return fighters;
	}




	public String addFighter(Fighter fighter) throws NoMoreSpaceException{
		for(int i=0;i<num;i++)
			if(fighters[i]==null) {
				fighters[i]=fighter;
				return "Borac je dodat";
			}
		throw new NoMoreSpaceException("Nema vise mesta");
	}
	
	public String delete(String name) {
		for(int i=0;i<num;i++) {
			if(fighters[i]!=null && fighters[i].getName().equalsIgnoreCase(name)) {
				if(i==num-1) {
					fighters[i]=null;
					return "Obrisan";
				}
				fighters[i]=null;
				for(int j=i;j<num-1;j++)
					fighters[j]=fighters[j+1];
				return "Obrisan";
			}
		}
		

		return"";
	}
	
	public static boolean isSafe(String name,Fighter f[]) {
		for(int i=0;i<f.length;i++) {
			if(f[i]!=null && f[i].getName().equalsIgnoreCase(name)) {
				return false;	
			}
		}
		
		return true;
		
	}
	
	public String showAll() {
		String s="";
		for(int i=0;i<fighters.length;i++) {
			if(fighters[i]!=null)
				s+=(i+1)+") "+fighters[i]+"\n";
		}
		return s;
	}
	
/*	
	public static void SS(Fighter f[]) {
		for(int i=0;i<f.length;i++) {
			if(f[i]==null)
				System.out.print("prazno ");
			else
				System.out.print(f[i].getName()+" ");
		}System.out.println();
	}
*/
}

