package com.financial.tools.recorderserver.payload;

import java.util.List;

import com.financial.tools.recorderserver.entity.BudgetTrail;

public class UserBudgetTrailResponse {

	private List<BudgetTrail> budgetTrailList;

	public UserBudgetTrailResponse() {
	}

	public UserBudgetTrailResponse(List<BudgetTrail> budgetTrailList) {
		this.budgetTrailList = budgetTrailList;
	}

	public List<BudgetTrail> getBudgetTrailList() {
		return budgetTrailList;
	}

	public void setBudgetTrailList(List<BudgetTrail> budgetTrailList) {
		this.budgetTrailList = budgetTrailList;
	}

}
