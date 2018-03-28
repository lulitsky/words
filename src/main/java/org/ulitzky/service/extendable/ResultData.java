package org.ulitzky.service.extendable;

import org.ulitzky.service.ICodekataService;
import org.ulitzky.service.extendable.rules.IRule;

/**
 * Created by lulitzky on 26.03.18.
 */
public class ResultData implements IProcessingResult{

        private String suffix;
        private String prefix;
        private String result;

        public ResultData(final String word, final int prefixLength) {
            this.prefix = word.substring(0, prefixLength);
            this.suffix = word.substring(prefixLength);
            this.result = word;
        }

        @Override
        public String toString() {
            return new ExtendableCodekataService().buildResultString(this.getPrefix(), this.getSuffix(), this.getResult());

        }


        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(final String suffix) {
            this.suffix = suffix;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(final String prefix) {
            this.prefix = prefix;
        }

        public String getResult() {
            return result;
        }

        public void setResult(final String result) {
            this.result = result;
        }
}
