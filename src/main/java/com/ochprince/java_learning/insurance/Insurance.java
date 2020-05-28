package com.ochprince.java_learning.insurance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class Insurance {

	private final static float APR = 0.05f;
	/**
	 * 保额(单位w)
	 */
	private double sumInsured;

	/**
	 * 单次缴费金额
	 */
	private double unitPrice;

	/**
	 * 缴费周期
	 */
	private Unit unit;

	/**
	 * 保障年份
	 */
	private int insuredYears;

	/**
	 * 缴费次数
	 */
	private int paymentTimes;

	/**
	 * 按当前缴费金额、频率和年化利率，到期后实际现金价值
	 */
	private double actualCost;

	/**
	 * 性价比，综合保额、实际现金价值、保障年限的计算结果
	 */
	private double costPerformance;

	/**
	 * 总支出
	 */
	private double totalCost;

	/**
	 * 到期返还比例
	 */
	private double restoreRat = 0;

	/**
	 * 当前每年额外存储金额，以在保障期后仍能获得保费等额现金价值
	 */
	private double extraSave;

	/**
	 * 额外存储满期现金价值
	 */
	private double extraTotalValue;

	public Insurance(double sumInsured, double unitPrice, Unit unit, int insuredYears, int paymentTimes) {
		this.sumInsured = sumInsured;
		this.unitPrice = unitPrice;
		this.unit = unit;
		this.insuredYears = insuredYears;
		this.paymentTimes = paymentTimes;
		this.totalCost = totalCost();
		this.actualCost = actualCost();
		this.costPerformance = costPerformance();
		this.extraSave = extraSave();
		this.extraTotalValue = extraTotalValue();
	}

	public Insurance(double sumInsured, double unitPrice, Unit unit, int insuredYears, int paymentTimes, double restoreRat) {
		this.sumInsured = sumInsured;
		this.restoreRat = restoreRat;
		this.unitPrice = unitPrice;
		this.unit = unit;
		this.insuredYears = insuredYears;
		this.paymentTimes = paymentTimes;
		this.totalCost = totalCost();
		this.actualCost = actualCost();
		this.costPerformance = costPerformance();
		this.extraSave = extraSave();
		this.extraTotalValue = extraTotalValue();
	}

	public static float getAPR() {
		return APR;
	}

	public double getSumInsured() {
		return sumInsured;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public Unit getUnit() {
		return unit;
	}

	public int getInsuredYears() {
		return insuredYears;
	}

	public int getPaymentTimes() {
		return paymentTimes;
	}

	public double getActualCost() {
		return actualCost;
	}

	public double getCostPerformance() {
		return costPerformance;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public double getRestoreRat() {
		return restoreRat;
	}

	public double getExtraSave() {
		return extraSave;
	}

	public double getExtraTotalValue() {
		return extraTotalValue;
	}

	private double extraTotalValue() {
		double totalValue = 0;
		for (int i = 0; i < insuredYears; i++) {
			double pow = Math.pow(1 + APR, i);
			totalValue += extraSave * pow;
		}
		return totalValue;
	}

	private double extraSave() {
		float v = 1 + APR;
		for (int i = 2; ; i++) {
			Double[] resArr = getResArr(1000 * i);
			int i1 = getIndex(v, resArr);
			if (i1 >= 0) {
				return resArr[i1];
			}
		}
	}

	private Double[] getResArr(int max) {
		Double[] values = new Double[1000];
		for (int i = 0; i < values.length; i++) {
			values[i] = (double) (max - 1000 + i);
		}
		return values;
	}

	private int getIndex(float v, Double[] values) {
		return binarySearch(values, 0, values.length, sumInsured * 10000, x -> {
			double price = 0;
			for (int i = 0; i < insuredYears; i++) {
				double pow = Math.pow(v, i);
				price += x * pow;
			}
			return price;
		}, Double::compareTo);
	}

	private <T> int binarySearch(T[] a, int fromIndex, int toIndex,
								 T key, Function<T, T> arrayMap, Comparator<? super T> c) {
		int low = fromIndex;
		int high = toIndex - 1;
		List<Integer> all = new ArrayList<>();

		while (low <= high) {
			int mid = (low + high) >>> 1;
			T midVal = a[mid];
			T apply = arrayMap.apply(midVal);
			int cmp = c.compare(apply, key);
			if (cmp < 0) {
				low = mid + 1; // top part
			} else if (cmp > 0) {
				all.add(mid);
				high = mid - 1; // bottom part
			} else {
				all.add(mid);
				return mid; // key found
			}
		}
		if (all.size() > 0) {
			T first = arrayMap.apply(a[all.get(0)]);
			int ind = 0;
			for (Integer index : all) {
				if (c.compare(first, arrayMap.apply(a[index])) > 0) {
					first = arrayMap.apply(a[index]);
					ind = index;
				}
			}
			return ind;
		}
		return -(low + 1);  // key not found.
	}

	private double totalCost() {
		return this.paymentTimes * this.unitPrice;
	}

	private double actualCost() {
		float v = 1 + APR;
		if (unit.equals(Unit.YEAR)) {
			double price = 0;
			for (int i = 0; i < paymentTimes; i++) {
				int base = insuredYears - i;
				base = base < 0 ? 0 : base;
				price += unitPrice * Math.pow(v, base);
			}
			return price - restoreRat * totalCost;
		} else {
			double price = 0;
			for (int i = 0; i < paymentTimes / 12; i++) {
				int base = insuredYears - i;
				base = base < 0 ? 0 : base;
				price += (unitPrice * 12) * Math.pow(v, base);
			}
			return price - restoreRat * totalCost;
		}
	}

	private double costPerformance() {
		double v1 = (sumInsured * 10000) / actualCost;
		return v1 * insuredYears;
	}

	@Override
	public String toString() {
		return "保险方案{" +
				"保额=" + sumInsured +
				"万, 每次交钱=" + unitPrice +
				"元, 交钱周期=" + unit +
				", 【总花费=" + String.format("%.2f", totalCost / 10000) +
				"万】, 到期返还=" + restoreRat +
				"倍, 【保障年限=" + insuredYears +
				"年】, 交钱次数=" + paymentTimes +
				"次, 【该方案保障期满实际花费=" + String.format("%.2f", actualCost / 10000) +
				"万】, 【额外需要存=" + extraSave +
				"元】, 性价比=" + String.format("%.2f", costPerformance) +
				'}';
	}

	public enum Unit {
		MOUTH,
		YEAR
	}
}
