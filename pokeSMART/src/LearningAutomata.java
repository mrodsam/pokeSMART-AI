import java.util.Vector;

public class LearningAutomata {

	final double dDecFactorLR = 0.99;
	final double dMINLearnRate = 0.05;
	double dLearnRate = 0.1;
	int iNewAction;
	int iNumActions = 2;
	int iLastAction;
	double dLastFunEval;
	StateAction oPresentStateAction;
	StateAction oLastStateAction;
	Vector<StateAction> oVStateActions = new Vector<>(10, 5);

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
				for (int i = 0; i < iNActions; i++)
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
}
