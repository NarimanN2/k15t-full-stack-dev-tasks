package com.k15t.pat.validator;

import org.passay.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PasswordConstraintValidator implements ConstraintValidator<Password, String> {

    private Logger logger = LoggerFactory.getLogger(PasswordConstraintValidator.class);

    @Override
    public void initialize(Password password) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        ArrayList<Rule> rules = getRules();
        PasswordValidator validator = new PasswordValidator(rules);
        RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid())
            return true;

        List<String> messages = validator.getMessages(result);

        for (String message : messages)
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();

        return false;
    }

    private ArrayList<Rule> getRules() {
        ArrayList<Rule> rules = new ArrayList<>();

        rules.add(new LengthRule(8, 16));
        rules.add(new AlphabeticalCharacterRule(1));
        rules.add(new DigitCharacterRule(1));
        rules.add(new WhitespaceRule());

        return rules;
    }
}
