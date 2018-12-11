import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

/*Imprimir los estados y las probabilidades de las acciones en un fichero*/
public class LearningAutomata {

	final double dDecFactorLR = 0.99;
	final double dMINLearnRate = 0.05;
	double dLearnRate = 0.1;
	int iNewAction;
	int iLastAction;
	int iNumActions = 4;
	double dLastFunEval;
	StateAction oPresentStateAction;
	StateAction oLastStateAction;
	Vector<StateAction> oVStateActions = new Vector<>(10, 5);

	public LearningAutomata(int id) {
		String cadena;
		FileReader f = null;
		BufferedReader b = null;
		File file = new File("learningAutomata"+id+".txt");
		if (file.exists()) {
			try {
				f = new FileReader("learningAutomata"+id+".txt");
				b = new BufferedReader(f);
				while ((cadena = b.readLine()) != null) {
					double[] valActions = new double[4];
					for (int i = 0; i < cadena.split("/")[1].split(",").length; i++) {
						valActions[i] = Double.parseDouble(cadena.split("/")[1].split(",")[i]);
					}
					oVStateActions.addElement(new StateAction(cadena.split("/")[0], 4, valActions));
				}
			} catch (IOException e) {
				System.out.println("No existe el fichero");
			} finally {
				try {
					b.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int vGetNewActionAutomata(String sState, int iNActions, double dFunEval) {
		boolean bFound;
		StateAction oStateProbs;

		bFound = false;
		for (int i = 0; i < oVStateActions.size(); i++) {
			oStateProbs = (StateAction) oVStateActions.elementAt(i);
			if (oStateProbs.sState.equals(sState)) {
				oPresentStateAction = oStateProbs;
				bFound = true;
				break;
			}
		}
		if (!bFound) {
			oPresentStateAction = new StateAction(sState, iNActions, true);
			oVStateActions.add(oPresentStateAction);
		}

		if (oLastStateAction != null) {
			if (dFunEval - dLastFunEval > 0)
				for (int i = 0; i < iNumActions; i++)
					if (i == iLastAction)
						oLastStateAction.dValAction[i] += dLearnRate * (1.0 - oLastStateAction.dValAction[i]);
					else
						oLastStateAction.dValAction[i] *= (1.0 - dLearnRate);
		}

		double dValAcc = 0;
		double dValRandom = Math.random();
		for (int i = 0; i < iNActions; i++) {
			dValAcc += oPresentStateAction.dValAction[i];
			if (dValRandom < dValAcc) {
				iNewAction = i;
				break;
			}
		}

		oLastStateAction = oPresentStateAction;
		dLastFunEval = dFunEval;
		dLearnRate *= dDecFactorLR;
		if (dLearnRate < dMINLearnRate)
			dLearnRate = dMINLearnRate;

		return iNewAction;
	}

	public void writeOutputFile(int id) {
		FileWriter file = null;
		PrintWriter pw = null;

		try {
			file = new FileWriter("learningAutomata" + id + ".txt");
			pw = new PrintWriter(file);
			for (StateAction stateAction : oVStateActions) {
				pw.println(stateAction.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			pw.close();
		}

	}
}
