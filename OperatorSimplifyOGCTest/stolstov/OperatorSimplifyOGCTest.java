package stolstov;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.MultiPath;
import com.esri.core.geometry.OperatorImportFromWkt;
import com.esri.core.geometry.OperatorSimplifyOGC;

public final class OperatorSimplifyOGCTest {

	public static void main(String[] args) throws IOException {
		byte[] bytes = Files.readAllBytes(Paths.get(args[0]));
		String text = new String(bytes, "US-ASCII");
		Geometry g = OperatorImportFromWkt.local().execute(0, Geometry.Type.Unknown, text, null);
		System.out.println(args[0] + " : " + g.getType() + 
				" " + ((MultiPath)g).getPathCount() + " rings" + " " + ((MultiPath)g).getPointCount() + " vertices");
		System.out.print("Checking if simple... ");
		boolean isSimple = OperatorSimplifyOGC.local().isSimpleOGC(g,  null,  true, null, null);
		System.out.println(isSimple);
		System.out.print("Running simplify operation... ");
		Geometry gSimple = OperatorSimplifyOGC.local().execute(g, null, true, null);
		System.out.println("Simple" + " : " + g.getType() + 
				" " + ((MultiPath)gSimple).getPathCount() + " rings" + " " + ((MultiPath)gSimple).getPointCount() + " vertices");
		System.out.println("Done");
		System.out.print("Running simplify operation (should be simple now)... ");
		isSimple = OperatorSimplifyOGC.local().isSimpleOGC(gSimple,  null,  true, null, null);
		System.out.println(isSimple);
	}
}
