package org.example;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;

import static java.lang.System.out;

public class Main {

    @Option(name = "-numThread",  usage = "Sets the number of threads (must be greater than or equal to 1)", required = true)
    private int threads = 0;
    @Option(name = "-numBins",  usage = "Sets the number of bins (must be even and greater than or equal to 2)", required = true)
    private int bins = 0;


    public static void main(String[] args) {
        final Main instance = new Main(); // creating an instance of main class
        try {
            instance.getArgs(args);
            instance.setup();
        } catch (IOException ex) {
            out.println("An unexpected I/O Exception has been occurred: " + ex);
        }
    }

    public void setup(){
        System.out.println("Bins " + bins);
        System.out.println("Thread " + threads);
        process(threads,bins);
    }

    public static void process(int threads, int bins){

        int [] cells = new int[bins];
        BallManager ballManager = new BallManager(threads, cells);
        ballManager.startGaltonBoard();
        int toplam =0;
        for (int i=0; i<cells.length;i++) {
            System.out.println(i + "   " + cells[i]);
            toplam+= cells[i];
        }
        System.out.println("Number of requested thread: " + threads);
        System.out.println("Sum of bin values: " + toplam);
        if(threads == toplam){
            System.out.println("Nice work! Both of them are equal");
        }
    }

    private void getArgs(final String[] args) throws IOException {
        final CmdLineParser parser = new CmdLineParser(this);
        if (args.length < 1) {
            parser.printUsage(out);
            System.exit(-1);
        }
        try {
            parser.parseArgument(args);
            if (threads < 1 || bins < 1 || bins % 2 != 0) { // threads and bins must be greater than or equal to 1
                parser.printUsage(out);
                System.exit(-1);
            }
        } catch (CmdLineException ex) {
            out.println("Unable to parse command-line options: " + ex);
        }
    }

}

