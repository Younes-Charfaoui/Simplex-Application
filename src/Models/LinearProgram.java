package Models;

/**
 * @definition this class is a model of
 * a linear Program, in which we gonna define all the operation we
 * can do on a Linear Program
 */

public class LinearProgram {

    private double[][] mVectorA;
    private double[] mVectorB;
    private double[] mVectorC;

    public LinearProgram(double[] mVectorB, double[] mVectorC, double[][] mVectorA) {
        this.mVectorA = mVectorA;
        this.mVectorB = mVectorB;
        this.mVectorC = mVectorC;
    }

    public double[][] getVectorA() {
        return mVectorA;
    }

    public void setVectorA(double[][] mVectorA) {
        this.mVectorA = mVectorA;
    }

    public double[] getVectorB() {
        return mVectorB;
    }

    public void setVectorB(double[] mVectorB) {
        this.mVectorB = mVectorB;
    }

    public double[] getVectorC() {
        return mVectorC;
    }

    public void setVectorC(double[] mVectorC) {
        this.mVectorC = mVectorC;
    }
}
