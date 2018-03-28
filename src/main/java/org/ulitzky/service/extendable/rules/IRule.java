package org.ulitzky.service.extendable.rules;


import org.ulitzky.service.extendable.IProcessable;
import org.ulitzky.service.extendable.IProcessingResult;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by lulitzky on 26.03.18.
 */
public interface IRule<V extends IProcessable> {

    Optional<IProcessingResult> validate(final V processableObject, final Object... args);

}
