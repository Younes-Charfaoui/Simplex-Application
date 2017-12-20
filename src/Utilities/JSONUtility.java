package Utilities;

import Models.LinearProgram;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @definition this class have the role
 * of getting all the Information we need
 * about a JSON file which contain our
 * linear program problem , in other words
 * it will parse the JSON file.
 */

public class JSONUtility {

    private final String mJsonText;

    //some constant filed to use them in parsing
    private static final String JSON_VARIABLE_DECISION_NUMBER = "numberOfVariableD";
    private static final String JSON_CONSTRAINT_NUMBER = "numberOfConstraint";
    private static final String JSON_STANDARDISE = "standardise";
    private static final String JSON_OBJECTIFY_FUNCTION = "objectifyFunction";
    private static final String JSON_CONSTRAINTS = "constraints";
    private static final String JSON_RESULT = "result";
    private static final String JSON_COEFFICIENT = "coefficient";
    private static final String JSON_SIGN = "sign";

    //member variable arrays to store the Vectors A, B and C
    private double[][] mVectorA;
    private double[] mVectorB;
    private double[] mVectorC;

    //default constructor
    public JSONUtility(String jsonText) {
        this.mJsonText = jsonText;
    }

    /**
     * this method will parse the JSON Text to the Vector A , B and C
     */
    private void parseJSONText() {
        //creating the JSON Root object
        JSONObject root = new JSONObject(mJsonText);

        //getting the number of variables, constraint and the boolean standardise
        int mNumberOfVariableD = root.getInt(JSON_VARIABLE_DECISION_NUMBER);
        int mNumberOfConstraint = root.getInt(JSON_CONSTRAINT_NUMBER);
        boolean mStandardise = root.getBoolean(JSON_STANDARDISE);

        int mNumberOfVariable = mNumberOfVariableD + mNumberOfConstraint;

        //creating Matrices base on this numbers
        mVectorA = new double[mNumberOfConstraint][mNumberOfVariable];
        mVectorB = new double[mNumberOfConstraint];
        mVectorC = new double[mNumberOfVariable];

        //initializing the values of the matrices A and C with 0
        for (int i = 0; i < mNumberOfVariable; i++) {
            for (int j = 0; j < mNumberOfConstraint; j++) {
                mVectorA[j][i] = 0;
            }
            mVectorC[i] = 0;
        }

        JSONArray constraints = root.getJSONArray(JSON_CONSTRAINTS);

        //filing the Vector B
        for (int i = 0; i < constraints.length(); i++) {
            mVectorB[i] = constraints.getJSONObject(i).getDouble(JSON_RESULT);
        }

        //filing the Vector C
        for (int i = 0; i < mNumberOfVariableD; i++) {
            mVectorC[i] = root.getJSONArray(JSON_OBJECTIFY_FUNCTION).getDouble(i);
        }

        /** if our problem is not standardise we have to standardise it, otherwise we will
         * just copy the values are in the JSON File
         **/
        if (!mStandardise) {
            for (int i = 0; i < constraints.length(); i++) {
                JSONArray coefficient = constraints.getJSONObject(i).getJSONArray(JSON_COEFFICIENT);
                for (int j = 0; j < coefficient.length(); j++) {
                    mVectorA[i][j] = coefficient.getDouble(j);
                }
                if (constraints.getJSONObject(i).getString(JSON_SIGN).equals("<=")) {
                    mVectorA[i][mNumberOfVariableD + i] = 1;
                } else if (constraints.getJSONObject(i).getString(JSON_SIGN).equals(">=")) {
                    mVectorA[i][mNumberOfVariableD + i] = -1;
                }
            }
        } else {
            for (int i = 0; i < constraints.length(); i++) {
                JSONArray coefficient = constraints.getJSONObject(i).getJSONArray(JSON_COEFFICIENT);
                for (int j = 0; j < coefficient.length(); j++) {
                    mVectorA[i][j] = coefficient.getDouble(j);
                }
            }
        }
    }

    /**
     * this method will return back the Linear Program
     *
     * @return LinearProgram
     */
    public LinearProgram getLinearProgram() {
        parseJSONText();
        return new LinearProgram(mVectorB, mVectorC, mVectorA);
    }

}
