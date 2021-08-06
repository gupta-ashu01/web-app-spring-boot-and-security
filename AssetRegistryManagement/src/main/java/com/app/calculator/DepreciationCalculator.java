package com.app.calculator;

import java.util.List;
import java.util.Map;

public interface DepreciationCalculator {
	
	public List<DepreciationOutput> calculate(DepreciationInput input);

}
