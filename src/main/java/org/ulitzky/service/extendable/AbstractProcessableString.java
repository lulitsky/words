package org.ulitzky.service.extendable;

import lombok.extern.slf4j.Slf4j;
import org.ulitzky.service.extendable.rules.IRule;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by lulitzky on 26.03.18.
 */
@Slf4j
public abstract class AbstractProcessableString implements IProcessable, IProcessingResult {

    private final String value;

    private List<IRule> processingRules = new LinkedList<>();

    public AbstractProcessableString(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public Optional<IProcessingResult> checkRules(final ConcurrentSkipListSet<IProcessable> processingData) {
        final List<IProcessingResult> results = new LinkedList<>();
        for (IRule rule: processingRules) {
            Optional<IProcessingResult> result = rule.validate(this, processingData);
            if (result.isPresent()) {
                results.add(result.get());
            } else {
                return Optional.empty();
            }
        }
       return collectCheckResults(results);
    }

    abstract Optional<IProcessingResult> collectCheckResults(final List<IProcessingResult> results ) ;

    @Override
    public void setRules(final Collection<IRule> rules) {
        processingRules.addAll(rules);
    }

    @Override
    public Collection<IRule> getRules() {
        Collection<IRule> rulesCopy = new LinkedList<>();
        rulesCopy.addAll(processingRules);
        return rulesCopy;
    }
}
