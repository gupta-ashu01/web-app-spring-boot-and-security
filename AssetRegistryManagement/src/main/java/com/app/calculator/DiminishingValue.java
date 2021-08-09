package com.app.calculator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DiminishingValue implements DepreciationCalculator {

	@Override
	public List<DepreciationOutput> calculate(DepreciationInput input) {


		try {

			DateTimeFormatter acquiredDatePattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");

			LocalDate acquiredDate = LocalDate.parse(input.getDateAcquired(), acquiredDatePattern);

			//Date acquiredDate=new SimpleDateFormat("dd-MM-yyyy").parse(input.getDateAcquired());

			int yearOfDate = date1.getYear();

			int tempYearOfDate = 0;

			tempYearOfDate = yearOfDate + 1;

			DepreciationOutput depreciationOutput;

			double tempDeprBase = input.getDepreciableBase();

			double tempAssetDeprBase = input.getDepreciableBase();

			List<DepreciationOutput> depreciationOutputList = new ArrayList<>();

			for(int i = 0 ; i < input.getDepreciableLife()+1 ; i++) {

				depreciationOutput = new DepreciationOutput();


				String tempDate ="01-01-"+(tempYearOfDate);

				depreciationOutput.setDate(tempDate);

				long daysHeld = Duration.between(acquiredDate, LocalDate.parse(tempDate, acquiredDatePattern)).toDays();


				//Depr. Amount Calculation
				double tempDeprAmount = tempDeprBase * (daysHeld / 365) * (200*(input.getDepreciableLife()) / 100)

				depreciationOutput.setAmount(tempDeprAmount);


				// Book Value Calculation


				double tempBookValue = tempAssetDeprBase - tempDeprAmount;
				depreciationOutput.setBookValue( tempBookValue );





				tempYearOfDate = tempYearOfDate +1;
				tempDeprBase = tempDeprAmount;
				tempAssetDeprBase = tempBookValue;


			}

		} catch (ParseException e) {
			e.printStackTrace();
		}


		


		return null;
	}

}
