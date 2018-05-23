package test_Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class launcher {

	// Source : https://www.mkyong.com/java/how-to-compare-dates-in-java/
	// source2 : https://www.mkyong.com/java/java-convert-date-to-calendar-example/
	// sources 3 :
	// https://jmdoudoux.developpez.com/cours/developpons/java/chap-utilisation_dates.php

	public static void main(String[] args) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate1 = new Date();

		// création d'un tableau contenant une série de date correspondant au 12
		// derniers
		Calendar cal1 = Calendar.getInstance();
		Calendar monCal[] = new Calendar[12]; //déclaration + initialisation

		for(int i=0; i<12; i++) {
			cal1 = Calendar.getInstance();
			cal1.add(Calendar.MONTH, -i);
			cal1.add(Calendar.DATE, -(Calendar.getInstance().getTime().getDate()-1));
			System.out.println("date " + i + " : " + cal1.getTime());
			monCal[i] = cal1;
		}
				

		// =============================================================================================================

		// création d'un tableau à trois variables (indice, nbTransaction, pognonTotal) de 12 éléments
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
		
		
		// Ajout / retrancher des mois et jour à une date (Calendar)
		Calendar currentDate = Calendar.getInstance();
		System.out.println("date avant retrait 12mois : " + currentDate.getTime());
		currentDate.add(Calendar.MONTH, -12);
		currentDate.add(Calendar.DATE, -(Calendar.getInstance().getTime().getDate()-1));
		System.out.println("date après retrait 12mois : " + currentDate.getTime());

	}

}
