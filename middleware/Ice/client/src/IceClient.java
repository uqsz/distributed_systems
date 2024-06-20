import Demo.CalcPrx;
import Demo.Vector;
import Demo.Vectors2DHelper;
import com.zeroc.Ice.*;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.Exception;

public class IceClient {
	public static void main(String[] args) {
		int status = 0;
		Communicator communicator = null;
        Vector[] vectors;
        com.zeroc.Ice.InputStream in;
		try {
			communicator = Util.initialize(args);
			ObjectPrx base1 = communicator.stringToProxy("SimpleCalc:default -h localhost -p 10000");

//			CalcPrx obj1 = CalcPrx.checkedCast(base1);
			if (base1 == null) throw new Error("Invalid proxy");
            try
            {
                // add
                com.zeroc.Ice.OutputStream outAdd = new com.zeroc.Ice.OutputStream(communicator);
                outAdd.startEncapsulation();
                outAdd.writeInt(20);
                outAdd.writeInt(-10);
                outAdd.endEncapsulation();

                byte[] inParamsAdd = outAdd.finished();

                // norm
                com.zeroc.Ice.OutputStream outNorm = new com.zeroc.Ice.OutputStream(communicator);
                outNorm.startEncapsulation();
                outNorm.writeInt(3);
                outNorm.writeInt(4);
                outNorm.endEncapsulation();

                byte[] inParamsNorm = outNorm.finished();

                // addVectors
                com.zeroc.Ice.OutputStream outVectors = new com.zeroc.Ice.OutputStream(communicator);
                outVectors.startEncapsulation();

                vectors = new Vector[3];
//
                vectors[0] = new Vector(2,5);
                vectors[1] = new Vector(3,7);
                vectors[2] = new Vector(1,2);
//

                Vectors2DHelper.write(outVectors,vectors);
                outVectors.endEncapsulation();

                byte[] inParamsVectors = outVectors.finished();

                com.zeroc.Ice.Object.Ice_invokeResult r1 = base1.ice_invoke("add", OperationMode.Normal, inParamsAdd);
                com.zeroc.Ice.Object.Ice_invokeResult r2 = base1.ice_invoke("norm", OperationMode.Normal, inParamsNorm);
                com.zeroc.Ice.Object.Ice_invokeResult r3 = base1.ice_invoke("addVectors", OperationMode.Normal, inParamsVectors);
                if(r1.returnValue)
                {
                    in = new com.zeroc.Ice.InputStream(communicator, r1.outParams);
                    in.startEncapsulation();
                    long result = in.readInt();
                    in.endEncapsulation();
                    System.out.println(result);
                }
                if(r2.returnValue)
                {
                    in = new com.zeroc.Ice.InputStream(communicator, r2.outParams);
                    in.startEncapsulation();
                    double result = in.readDouble();
                    in.endEncapsulation();
                    System.out.println(result);
                }
                if(r3.returnValue)
                {
                    in = new com.zeroc.Ice.InputStream(communicator, r3.outParams);
//                    Vectors2DHelper.read(in);
//                    in.startEncapsulation();
//                    Vector[] v = Vectors2DHelper.read(in)[0].x;
//                    System.out.println(Vectors2DHelper.read(in));
//                    in.endEncapsulation();
                }
                else
                {
                    System.out.println("User Exception");
                }
            }
            catch(com.zeroc.Ice.LocalException ex)
            {
                System.out.println("Local Exception");
            }

		} catch (LocalException e) {
			e.printStackTrace();
			status = 1;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			status = 1;
		}
		if (communicator != null) { //clean
			try {
				communicator.destroy();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				status = 1;
			}
		}
		System.exit(status);
	}

}