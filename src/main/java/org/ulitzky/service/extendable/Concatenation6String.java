package org.ulitzky.service.extendable;

import org.ulitzky.service.extendable.rules.ConcatenationRule;
import org.ulitzky.service.extendable.rules.IRule;
import org.ulitzky.service.extendable.rules.LengthRule;
import org.w3c.dom.views.AbstractView;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by lulitzky on 26.03.18.
 */
public class Concatenation6String extends AbstractProcessableString implements Comparable<Concatenation6String> {

    public Concatenation6String(final String value) {
        super(value);
        IRule<AbstractProcessableString> lengthRule = new LengthRule(6, 6);
        IRule<AbstractProcessableString> concatenationRule = new ConcatenationRule();
        setRules(Arrays.asList(lengthRule, concatenationRule));
    }

    @Override
    Optional<IProcessingResult> collectCheckResults(final List<IProcessingResult> results) {
        for (Object result : results) {
            if (result instanceof  ResultData) {
                return Optional.of ((ResultData) result);
            }
        }
        return Optional.empty();


    }

    @Override
    public int compareTo(final Concatenation6String o) {
        if (o != null) {
            return getValue().compareTo(o.getValue());
        } else {
            return 1;
        }
    }
}
