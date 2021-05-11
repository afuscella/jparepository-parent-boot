package com.sap.nfe.library;

import org.junit.jupiter.api.Test;

import com.jparams.verifier.tostring.ToStringVerifier;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import com.sap.nfe.library.model.Model;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

public class ModelTest {

	private String[] packageNameArray = { Model.class.getPackage().getName() };

	@Test
	public void modelsShouldHaveGettersSettersAndValidEqualsAndToString() {
		for (String packageName : packageNameArray) {
			validateGettersAndSetters(packageName);
			validateEquals(packageName);
			validateToString(packageName);
		}
	}

	public void validateGettersAndSetters(String packageName) {
		Validator validator =
				ValidatorBuilder.create().with(new SetterMustExistRule(), new GetterMustExistRule()).with(new SetterTester(), new GetterTester())
						.build();
		validator.validate(packageName);
	}

	public void validateEquals(String packageName) {
		EqualsVerifier.forPackage(packageName).suppress(Warning.NONFINAL_FIELDS).verify();
	}

	public void validateToString(String packageName) {
		ToStringVerifier.forPackage(packageName, false).verify();
	}

}
