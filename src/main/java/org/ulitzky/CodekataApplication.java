package org.ulitzky;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;
import org.ulitzky.service.ICodekataService;
import org.ulitzky.service.effective.EffectiveCodekataService;
import org.ulitzky.service.extendable.ExtendableCodekataService;
import org.ulitzky.service.readable.ReadableCodekataService;

/**
 * Created by lulitzky on 25.03.18.
 */
@Slf4j
public class CodekataApplication {

    private static ICodekataService readableService = new ReadableCodekataService();
    private static ICodekataService effectiveService = new EffectiveCodekataService();
    private static ICodekataService extendableService = new ExtendableCodekataService();


    public static void main(String[] args) throws Exception {
        CommandLine cmd = parseArgs(args);
        String inputFile = cmd.getOptionValue("input");
        String solutionOption =  cmd.getOptionValue("solution option");

        long startTimestamp = System.currentTimeMillis();
        switch (solutionOption.toUpperCase()) {
            case "READABLE":
                readableService.findSolution(inputFile);
                break;
            case "EFFECTIVE":
                effectiveService.findSolution(inputFile);
                break;
            case "EXTENDABLE":
                extendableService.findSolution(inputFile);
                break;

            default:
                log.error("Not supported solution option {}", solutionOption);
                break;
        }
        long endTimestamp = System.currentTimeMillis();
        log.info("Total running time {} ms", (endTimestamp-startTimestamp));

    }


    private static CommandLine parseArgs(final String[] args) throws  Exception {
        Options options = new Options();

        Option input = new Option("i", "input", true, "input file path");
        input.setRequired(false);
        options.addOption(input);

        Option solution = new Option("o", "solution option", true, "solution option");
        solution.setRequired(true);
        options.addOption(solution);

        CommandLineParser parser = new DefaultParser();

        return parser.parse(options, args);
    }

}
