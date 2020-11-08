import java.util.UUID;

public class test {

	public static void main(String[] args) {
		/*UUID uniqueKey = UUID.randomUUID();
		System.out.println ("ID: " + uniqueKey.toString());*/
		
		int uniqueId = (int) (System.nanoTime() & 0xfffffff);
		System.out.println ("ID: " + uniqueId);
		
		long uniqueId1 = System.nanoTime() & 0xfffffff;
		System.out.println ("ID: " + uniqueId1);
		
		String applno = "APPL-" + (System.nanoTime() & 0xfffffff);
		System.out.println ("applno: " + applno);
	}

}
