import java.io.Serializable;

public class StateAction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1683670962607404357L;
	String sState;
	double[] dValAction;
	int iNActions;

	StateAction(String sAuxState, int iNActions) {
		sState = sAuxState;
		dValAction = new double[iNActions];
		this.iNActions = iNActions;
	}

	StateAction(String sAuxState, int iNActions, boolean bLA) {
		this(sAuxState, iNActions);
		if (bLA)
			for (int i = 0; i < iNActions; i++) // This constructor is used for LA and sets up initial probabilities
				dValAction[i] = 1.0 / iNActions;
	}

	public String sGetState() {
		return sState;
	}

	public double dGetQAction(int i) {
		return dValAction[i];
	}

	public int iGetNActions() {
		return iNActions;
	}
}