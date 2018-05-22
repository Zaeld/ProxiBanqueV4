package test_Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class launcher {

	// Source : https://www.mkyong.com/java/how-to-compare-dates-in-java/
	// source2 : https://www.mkyong.com/java/java-convert-date-to-calendar-example/
	// sources 3 :
	// https://www.jmdoudoux.fr/java/dej/chap-utilisation_dates.htm#utilisation_dates-2

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate1 = new Date();

		// création d'un tableau contenant une série de date correspondant au 12
		// derniers
		// mois=====================
		Calendar cal1 = Calendar.getInstance();
		cal1.add(Calendar.MONTH, -12);
		Calendar cal2 = Calendar.getInstance();
		cal2.add(Calendar.MONTH, -11);
		Calendar cal3 = Calendar.getInstance();
		cal3.add(Calendar.MONTH, -10);
		Calendar cal4 = Calendar.getInstance();
		cal4.add(Calendar.MONTH, -9);
		Calendar cal5 = Calendar.getInstance();
		cal5.add(Calendar.MONTH, -8);
		Calendar cal6 = Calendar.getInstance();
		cal6.add(Calendar.MONTH, -7);
		Calendar cal7 = Calendar.getInstance();
		cal7.add(Calendar.MONTH, -6);
		Calendar cal8 = Calendar.getInstance();
		cal8.add(Calendar.MONTH, -5);
		Calendar cal9 = Calendar.getInstance();
		cal9.add(Calendar.MONTH, -4);
		Calendar cal10 = Calendar.getInstance();
		cal9.add(Calendar.MONTH, -3);
		Calendar cal11 = Calendar.getInstance();
		cal10.add(Calendar.MONTH, -2);
		Calendar cal12 = Calendar.getInstance();
		cal12.add(Calendar.MONTH, -1);

		Calendar monCal[] = { cal1, cal2, cal3, cal4, cal5, cal6, cal7, cal8, cal9, cal10, cal11, cal11, cal12 };
		// =============================================================================================================

		// création d'un tableau à trois variable (indice, nbTransaction, pognonTotal de 12 élément
		float repport[][] = { { 0.0f, 0.0f }, { 0.0f, 0.0f }, { 0.0f, 0.0f }, { 0.0f, 0.0f }, { 0.0f, 0.0f },
				{ 0.0f, 0.0f }, { 0.0f, 0.0f }, { 0.0f, 0.0f }, { 0.0f, 0.0f }, { 0.0f, 0.0f }, { 0.0f, 0.0f },
				{ 0.0f, 0.0f } };

		
		
		
		// transformer une date en string
		Calendar currentDate1Calendar = Calendar.getInstance(); // doit être initialiser
		currentDate1Calendar.setTime(currentDate1);

		// obtenir un objet Date à partir d'un Calendar
		Date dateFromCal = cal1.getTime();

		// obtenir un string à partir d'une date (utiliser la ligne SimpleDateFormat)
		String str = sdf.format(dateFromCal);

		// obtenir un string à partir d'une date (utiliser la ligne SimpleDateFormat)

		// Ajout / retrancher des mois à une date (Calendar)
		Calendar currentDate = Calendar.getInstance();
		System.out.println("date avant retrait 12mois : " + currentDate.getTime());
		currentDate.add(Calendar.MONTH, -12);
		System.out.println("date après retrait 12mois : " + currentDate.getTime());

	}

}
