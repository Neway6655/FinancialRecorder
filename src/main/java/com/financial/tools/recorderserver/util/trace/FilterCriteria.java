package com.financial.tools.recorderserver.util.trace;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * Key/Value map for filter criteria.
 * 
 * @author eortwyz
 * 
 */
public class FilterCriteria {

	private Map<String, Object> criteriaMap = Maps.newHashMap();

	public Map<String, Object> getCriteriaMap() {
		return criteriaMap;
	}

	public void setCriteriaMap(Map<String, Object> criteriaMap) {
		this.criteriaMap = criteriaMap;
	}

	public void addCriteria(String key, Object value) {
		this.criteriaMap.put(key, value);
	}

}
