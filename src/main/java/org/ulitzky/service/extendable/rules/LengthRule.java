package org.ulitzky.service.extendable.rules;

import org.ulitzky.service.extendable.AbstractProcessableString;
import org.ulitzky.service.extendable.IProcessable;
import org.ulitzky.service.extendable.IProcessingResult;

import java.util.Optional;

/**
 * Created by lulitzky on 26.03.18.
 */
public class LengthRule implements IRule<AbstractProcessableString> {

    private final Integer minLength;
    private final Integer maxLength;

    public LengthRule(final Integer minLength, final Integer maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    public Optional<IProcessingResult> validate(final AbstractProcessableString processableObject, final Object... args) {
        boolean matches =  (((minLength == null) || (processableObject.getValue().length() >= minLength)) &&
                            ((maxLength == null) || (processableObject.getValue().length() <= maxLength)));

        if (matches) {
            return Optional.of(processableObject);
        } else {
            return Optional.empty();
        }
    }
}
