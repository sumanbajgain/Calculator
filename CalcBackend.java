/**
 * CalcBackend 
 */

import java.math.BigDecimal;


public class CalcBackend {

	private StringBuilder num1 = new StringBuilder();
	private StringBuilder num2 = new StringBuilder();
	int lastNumber = -1;
	private char operation = ' ';
	private double result = -99999.0;

	public void feedChar(char c) {
		if (c == 'N') {
			result = 0 - result;
		} else if (c == 'c') {
			num1 = new StringBuilder();
			num2 = new StringBuilder();
			operation = ' ';
			result = 0.0;
			lastNumber = -1;

		} else if (c == '+' || c == '-' || c == '*' || c == '/') {
			if (num1.length() > 0 && num2.length() > 0) {
				try {
					float n1 = Float.parseFloat(num1.toString());
					float n2 = Float.parseFloat(num2.toString());
					switch (operation) {
					case '+':
						result = n1 + n2;
						break;
					case '-':
						result = n1 - n2;
						break;
					case '*':
						result = n1 * n2;
						break;
					case '/':
						if (n2 == 0)
							result = -0.999999999999999111;
						else
							result = n1 / n2;
						break;
					}
				} catch (Exception e) {

				}
				num1 = new StringBuilder(result + "");
				num2 = new StringBuilder();
			}
			operation = c;
		} else if ((c >= '0' && c <= '9') || c == '.') {
			if (operation == ' ') {
				if (num1.length() == 0) {
					num1.append("0");
				}
				num1.append(c);
				lastNumber = 1;
				try {
					result = Float.parseFloat(num1.toString());
				} catch (Exception e) {
				}
			} else {
				if (num2.length() == 0) {
					num2.append("0");
				}
				num2.append(c);
				lastNumber = 2;
				result = Float.parseFloat(num2.toString());
			}
		} else if (c == 'q') {
			if (lastNumber == 1) {
				try {
					float parseFloat = Float.parseFloat(num1.toString());
					if (result < 0)
						result = -0.999999999999999111;
					else {
						result = Math.sqrt(parseFloat);
					}
				} catch (Exception e) {

				}
			} else if (lastNumber == 2) {
				try {
					float parseFloat = Float.parseFloat(num2.toString());
					if (parseFloat < 0)
						result = -0.999999999999999111;
					else {
						result = Math.sqrt(parseFloat);
						if (operation != ' ') {
							num2 = new StringBuilder(result + "");
						}
					}
				} catch (Exception e) {

				}
			} else if (lastNumber == 3) {
				if (result < 0)
					result = -0.999999999999999111;
				else
					result = Math.sqrt(result);
			}
		}

		if (c == '=') {
			if (num1.length() > 0 && num2.length() > 0 && operation != ' ') {
				try {
					float n1 = Float.parseFloat(num1.toString());
					float n2 = Float.parseFloat(num2.toString());
					switch (operation) {
					case '+':
						result = n1 + n2;
						break;
					case '-':
						result = n1 - n2;
						break;
					case '*':
						result = n1 * n2;
						break;
					case '/':
						if (n2 == 0)
							result = -0.999999999999999111;
						else
							result = n1 / n2;
						break;
					}
				} catch (Exception e) {

				}
				num1 = new StringBuilder(result + "");
				num2 = new StringBuilder();
			}
			lastNumber = 3;
		}

	}

	public double getDisplayVal() {
		BigDecimal bd = new BigDecimal(String.format("%.2f", result));
		if(num1.length() == 0 && num2.length() == 0 && result ==  -99999.0){
			return 0.0;
		}
		return bd.doubleValue();
	}
}