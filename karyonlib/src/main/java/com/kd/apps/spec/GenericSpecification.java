package com.kd.apps.spec;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.kd.apps.Const;
import com.kd.apps.Utils;
import com.kd.apps.model.SearchCriteria;

/**
 * 
 * @author Pradyumna Roy
 *
 * @param <T>
 */
public class GenericSpecification<T> implements Specification<T> {

	private SearchCriteria criteria;

	public GenericSpecification(SearchCriteria criteria) {
		super();
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		if (criteria.getOperation().equalsIgnoreCase(">")) {
			return builder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue1().toString());
		} else if (criteria.getOperation().equalsIgnoreCase("<")) {
			return builder.lessThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue1().toString());
		} else if (criteria.getOperation().equalsIgnoreCase(":")) {
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue1() + "%");
			} else if (root.get(criteria.getKey()).getJavaType() == Boolean.class) {
				return builder.equal(root.get(criteria.getKey()), new Boolean(criteria.getValue1().toString()));
			} else {
				return builder.equal(root.get(criteria.getKey()), criteria.getValue1());
			}
		} else if (criteria.getOperation().equalsIgnoreCase("<>")) {
			// Currently handling only Date type operands for Between operator
			if (root.get(criteria.getKey()).getJavaType() == Date.class) {
				return buildDateBtwnPredicate(builder, root);
			}
		}
		return null;
	}

	private Predicate buildDateBtwnPredicate(CriteriaBuilder builder, Root<T> root) {
		Date startDate = Utils.parseDate(criteria.getValue1().toString(), Const.FILTER_DATE_FORMAT);
		Date endDate = Utils.parseDate(criteria.getValue2().toString(), Const.FILTER_DATE_FORMAT);

		if (startDate != null && endDate != null) {
			// Setting the limit for the start and end date irrespective of the
			// date format
			startDate = Utils.roundDown(startDate);
			endDate = Utils.roundUp(endDate);

			Predicate operand1 = builder.greaterThanOrEqualTo(root.<Date>get(criteria.getKey()), startDate);
			Predicate operand2 = builder.lessThanOrEqualTo(root.<Date>get(criteria.getKey()), endDate);
			return builder.and(operand1, operand2);
		}
		return null;
	}
}