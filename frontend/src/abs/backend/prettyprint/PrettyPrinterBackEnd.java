/**
 * Copyright (c) 2009-2011, The HATS Consortium. All rights reserved.
 * This file is licensed under the terms of the Modified BSD License.
 */
package abs.backend.prettyprint;

import java.io.File;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import abs.backend.common.InternalBackendException;
import abs.common.NotImplementedYetException;
import abs.frontend.ast.Model;
import abs.frontend.parser.Main;
import abs.frontend.tests.ABSFormatter;

public class PrettyPrinterBackEnd extends Main {
    private File outputfile;
    private boolean force = false;
    private boolean keepsugar = false;

    public static void main(final String... args) {
        doMain(args);
    }

    public static int doMain(final String... args) {
        int result = 0;
        PrettyPrinterBackEnd backEnd = new PrettyPrinterBackEnd();
        try {
            result = backEnd.compile(args);
        } catch (NotImplementedYetException e) {
            System.err.println(e.getMessage());
            result = 1;
        } catch (Exception e) {
            System.err.println("An error occurred during compilation:\n" + e.getMessage());

            if (backEnd.debug) {
                e.printStackTrace();
            }

            result = 1;
        }
        return result;
    }

    @Override
    public List<String> parseArgs(String[] args) throws InternalBackendException {
        List<String> restArgs = super.parseArgs(args);
        List<String> remainingArgs = new ArrayList<>();

        for (int i = 0; i < restArgs.size(); i++) {
            String arg = restArgs.get(i);
            if (arg.equals("-o")) {
                i++;
                if (i == restArgs.size()) {
                    throw new InternalBackendException("Missing output file name after '-o'");
                } else {
                    outputfile = new File(restArgs.get(i));
                    if (outputfile.exists()) {
                        outputfile.delete();
                    }
                }
            } else if (arg.equals("-f"))  {
                force = true;
            } else if (arg.equals("-keepsugar"))  {
                keepsugar = true;
            } else if (arg.equals("-prettyprint")) {
                // nothing to do
            } else {
                remainingArgs.add(arg);
            }
        }

        return remainingArgs;
    }

    /**
     * @param args
     * @throws Exception
     */
    public int compile(String[] args) throws Exception {
        final Model model = parseFiles(parseArgs(args).toArray(new String[0]));
        if (keepsugar) {
            model.doAACrewrite = false;
            model.doForEachRewrite = false;
        }
        analyzeFlattenAndRewriteModel(model);
        if (! force && (model.hasParserErrors() || model.hasErrors() || model.hasTypeErrors())) {
            printErrorMessage();
            return 1;
        }

        final PrintStream stream;
        final String loc;
        if (outputfile != null) {
            stream = new PrintStream(outputfile);
            loc = outputfile.getAbsolutePath();
        } else {
            stream = System.out;
            loc = "Standard Output Stream";
        }

        if (verbose) {
            System.out.println("Output ABS model source code to "+loc+"...");
        }

        PrintWriter writer = new PrintWriter(stream,true);
        ABSFormatter formatter = new DefaultABSFormatter(writer);
        model.doPrettyPrint(writer, formatter);
        return 0;
    }

    public static void printUsage() {
        System.out.println("ABS Pretty Printer (-prettyprint):\n"
                + "  -f             force pretty printing even if there are type errors\n"
                + "  -keepsugar     do not transform statements into basic core abs\n"
                + "  -o <file>      write output to <file> instead of standard output\n"
        );
    }

}
