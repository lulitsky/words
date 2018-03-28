package org.ulitzky.service;

/**
 * Created by lulitzky on 25.03.18.
 */
public interface ICodekataService {
      void findSolution(final String fileName);

      default String buildResultString(final String prefix, final String suffix, final String candidate) {
            StringBuilder sb = new StringBuilder();
            sb.append(prefix).append(" + ").append(suffix).append(" => ").append(candidate);
            return sb.toString();
      }
}
