package ie.gmit.sw;

import java.util.Scanner;

import net.sourceforge.jFuzzyLogic.*;
import net.sourceforge.jFuzzyLogic.plot.*;

public class FuzzySalaryCalc {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// fuzzy inference loaded
		FIS fis = FIS.load("fcl/salary.fcl", true); // Load and parse the FCL

		// Display the linguistic variables and terms
		FunctionBlock functionBlock = fis.getFunctionBlock("Salary");
		JFuzzyChart.get().chart(functionBlock);

		// input values
		int iq;
		int qualifications;
		int workXp;
		int techScreen;

		System.out.print("Candidate Types:\n1 - poor\n2 - average\n3 - amazing\nChoice: ");
		int candidateType = input.nextInt();
		System.out.println();

		switch (candidateType) {
		case 1:
			// poor candidate < 25k
			iq = 70;
			qualifications = 5;
			workXp = 2;
			techScreen = 25;
			break;

		case 2:
			// average candidate > 27k AND < 40k
			iq = 100;
			qualifications = 7;
			workXp = 4;
			techScreen = 50;
			break;

		case 3:
			// amazing candidate > 40k
			iq = 140;
			qualifications = 8;
			workXp = 8;
			techScreen = 80;
			break;

		default:
			System.out.println("invalid input type, selecting average candidate.");
			// average candidate > 27k AND < 40k
			iq = 100;
			qualifications = 7;
			workXp = 4;
			techScreen = 50;
			break;
		}

		// set crisp values
		fis.setVariable("iq", iq); // 0 - 200
		fis.setVariable("qualifications", qualifications); // 0 - 10
		fis.setVariable("workXp", workXp); // 0 - 10
		fis.setVariable("techScreen", techScreen); // 0 - 100

		fis.evaluate(); // Execute the fuzzy inference engine
		Double salary = fis.getVariable("salary").getValue();
		System.out.printf(
				"inputs:\niq: %s (50 - 160)\nqualifications: Level %s/10\nworkXp: %s/10\ntechScreen: %s/100\n\n", iq,
				qualifications, workXp, techScreen);

		System.out.printf("calculated salary:\n€%.2f thousand euro per year", salary); // Output
																						// end
																						// result
	}

}
