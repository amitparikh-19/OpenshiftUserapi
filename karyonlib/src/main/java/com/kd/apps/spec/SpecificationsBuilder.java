package com.kd.apps.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.kd.apps.Const;
import com.kd.apps.model.SearchCriteria;

/**
 * 
 * @author Pradyumna Roy
 *
 * @param <T>
 */
public class SpecificationsBuilder<T> {
	private final List<SearchCriteria> params;

	public SpecificationsBuilder() {
		params = new ArrayList<SearchCriteria>();
	}

	public SpecificationsBuilder<T> with(String key, String operation, Object value1) {
		params.add(new SearchCriteria(key, operation, value1));
		return this;
	}

	public SpecificationsBuilder<T> with(String key, String operation, Object value1, Object value2) {
		params.add(new SearchCriteria(key, operation, value1, value2));
		return this;
	}

	public Specification<T> build() {
		if (params.size() == 0) {
			return null;
		}

		List<Specification<T>> specs = new ArrayList<Specification<T>>();
		for (SearchCriteria param : params) {
			specs.add(new GenericSpecification<T>(param));
		}

		Specification<T> result = specs.get(0);
		for (int i = 1; i < specs.size(); i++) {
			result = Specifications.where(result).and(specs.get(i));
		}
		return result;
	}

	public Specification<T> buildSpecifications(String params) {

		if (params == null)
			return null;

		/**
		 * Pattern to match basic comparison operations. expected input:-
		 * fieldname:value
		 */
		Pattern pattern1 = Pattern.compile("(\\w+?)(:)([^,]*?),");
		Matcher matcher1 = pattern1.matcher(params + ",");
		while (matcher1.find()) {
			this.with(matcher1.group(1), matcher1.group(2), matcher1.group(3));
		}

		/**
		 * Pattern to match between operations for date operands. expected
		 * input:- datefieldname<>date1|date2 date1 and date2 are expected to
		 * match the @link{Const.FILTER_DATE_REGEX} regex pattern
		 */
		Pattern pattern2 = Pattern
				.compile("(\\w+?)(<>)(" + Const.FILTER_DATE_REGEX + ")\\|(" + Const.FILTER_DATE_REGEX + "),");
		Matcher matcher2 = pattern2.matcher(params + ",");
		while (matcher2.find()) {
			this.with(matcher2.group(1), matcher2.group(2), matcher2.group(3), matcher2.group(4));
		}
		return this.build();
	}
}
