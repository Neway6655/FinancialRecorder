package com.financial.tools.recorderserver.util.trace;

import java.util.List;
import java.util.Map.Entry;

import org.jboss.logging.MDC;
import org.slf4j.Marker;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.MatchingFilter;
import ch.qos.logback.core.spi.FilterReply;

public class MDCLoggerFilter extends MatchingFilter {

	private List<Logger> loggerList;

	private List<FilterCriteria> filterCriteriaList;

	@Override
	public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
		boolean isLoggerMatch = false;
		// filter logger first.
		for (Logger tracedLogger : loggerList) {
			if (tracedLogger.getName().equals(logger.getName())) {
				isLoggerMatch = true;
				break;
			}
		}
		if (!isLoggerMatch) {
			return onMismatch;
		}

		boolean isMDCMatch = false;
		// filter MDC by filterCriteria.
		for (FilterCriteria filterCriteria : filterCriteriaList) {
			if (isMDCMatchFilterCriteria(filterCriteria)) {
				isMDCMatch = true;
			}
		}

		if (!isMDCMatch) {
			return onMismatch;
		}

		return onMismatch;
	}

	private boolean isMDCMatchFilterCriteria(FilterCriteria filterCriteria) {
		boolean isMatch = true;
		for (Entry<String, Object> criteriaEntry : filterCriteria.getCriteriaMap().entrySet()) {
			String key = criteriaEntry.getKey();
			if (!criteriaEntry.getValue().equals(MDC.get(key))) {
				isMatch = false;
				break;
			}
		}
		return isMatch;
	}

	public void setLoggerList(List<Logger> loggerList) {
		this.loggerList = loggerList;
	}

	public void setFilterCriteriaList(List<FilterCriteria> filterCriteriaList) {
		this.filterCriteriaList = filterCriteriaList;
	}

}
