package com.hanliang.strategy;

public class Minus extends AbstractCalculator implements ICalculator{

	@Override
	public Integer calculator(String exp) {
		int[] res = split(exp,"//+");
		return res[0]-res[1];
	}

}
