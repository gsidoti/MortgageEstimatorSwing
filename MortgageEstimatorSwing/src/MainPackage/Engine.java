package MainPackage;
import org.apache.poi.ss.formula.functions.FinanceLib;
public class Engine {

	public static double HousingPayment(double GrossIncome)
	{
		return (GrossIncome/12) * 0.18;
	}
	
	public static double OblPayment(double GrossIncome, double monthlyDebt)
	{
		return ((GrossIncome/12)*.36)-monthlyDebt;
	}

	public static double allowedPayment(double GrossIncome, double monthlyDebt) {
		return Math.min(HousingPayment(GrossIncome),
				OblPayment(GrossIncome, monthlyDebt));
	}
	
	public static double pv(double OblPayment, double HousingPayment, double years, double interest, double down) {
		double option = 0;
		years = years*12;
		if(HousingPayment < OblPayment){
			option = HousingPayment;
			}else{
			option = OblPayment;
			}
	return - FinanceLib.pv(interest,years,option, down, false);
		}
}