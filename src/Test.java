
public class Test {

	static Arena a;
	
	
	public static Arena getA() {
		return a;
	}


	public void test(){
			a=new Arena(4);
			
			try {
				
				Fighter f1=new Saiyan();
				a.addFighter(f1);
				
				Fighter f2=new Human();
				a.addFighter(f2);
				
				
				Fighter f3=new Saiyan();
				a.addFighter(f3);
				
				Fighter f4=new Human();
				a.addFighter(f4);
			
				Fighter f6=new Human();
				a.addFighter(f6);
				
				Fighter f=new Saiyan();
				a.addFighter(f);
				
			
				
			/*	Fighter f7=new Human();
				a.addFighter(f7);*/
			/*	Fighter f1=new Fighter("Saiyan");
				a.addFighter(f1);
				
				Fighter f2=new Fighter("Saiyan");
				a.addFighter(f2);
				*/
			} catch (NoFighterException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			} catch (NoMoreSpaceException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
			}
			
			
			System.out.println(a.showAll());
	
		
	}
	
	
	public Fighter[] getFighters() {
		return a.getFighters();
	}
}