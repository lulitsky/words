package org.ulitzky.service.extendable;

import org.ulitzky.service.extendable.rules.IRule;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by lulitzky on 26.03.18.
 */
public interface IProcessable {

    Optional<IProcessingResult> checkRules(ConcurrentSkipListSet<IProcessable> processingData);


    void setRules(final Collection<IRule> rules);
    Collection<IRule> getRules();
}
