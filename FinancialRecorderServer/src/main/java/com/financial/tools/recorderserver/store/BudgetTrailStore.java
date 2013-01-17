package com.financial.tools.recorderserver.store;

import java.util.List;

import com.financial.tools.recorderserver.entity.BudgetTrail;

public interface BudgetTrailStore {

	public void createBudgetTrail(BudgetTrail budgetTrail);

	public List<BudgetTrail> findBudgetTrailList(String userName);
}
