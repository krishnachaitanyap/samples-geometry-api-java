package stolstov;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.esri.core.geometry.OperatorImportFromWkt;
import com.esri.core.geometry.OperatorSimplifyOGC;

public final class OperatorSimplifyOGCTest {

	public static void main(String[] args) throws IOException {
		bytes[] bytes = Files.readAllBytes(Paths.get(args[0]));
		String text = new String(bytes, "US-ASCII");
		Geometry g = OperatorImportFromWkt.local().executeOGC(0,  text,  null);
		System.out.println(args[0] + " : " + g.getGeometryType() + 
				" " + ((MultiPath)g).getPointCount());
		System.out.print("Checking if simple... ");
		boolean isSimple = OperatorSimplifyOGC.local().isSimpleOGC(g,  null,  true, null, null);
		System.out.println(isSimple);
		System.out.print("Running siplify operation... ");
		Geometry gSimple = OperatorSimplifyOGC.local().execute(g, null, true, null);
		System.out.println("Done");
		System.out.print("Running siplify operation (should be simple now)... ");
		isSimple = OperatorSimplifyOGC.local().isSimpleOGC(gSimple,  null,  true, null, null);
		System.out.println(isSimple);
	}

}
