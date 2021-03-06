package nzgo.toolkit.core.sequences;

import jebl.evolution.sequences.BasicSequence;
import jebl.evolution.sequences.Sequence;
import jebl.evolution.sequences.SequenceType;
import jebl.evolution.taxa.Taxon;
import nzgo.toolkit.core.io.OTUsFileIO;
import nzgo.toolkit.core.logger.MyLogger;
import nzgo.toolkit.core.naming.Assembler;
import nzgo.toolkit.core.naming.NameUtil;

import java.io.*;
import java.util.List;

/**
 * JEBL Sequence Util
 * @author Thomas Hummel
 * @author Walter Xie
 */
public class SequenceUtil {

    public static void assembleSequenceLabels(List<Sequence> sequences, Assembler assembler) {
        for (int i = 0; i < sequences.size(); i++) {
            Sequence sequence = sequences.get(i);
            String taxon = assembler.getAssembledLabel(sequence.getTaxon().getName());

            if (!NameUtil.isEmptyNull(taxon)) {
                Sequence newSeq = new BasicSequence(SequenceType.NUCLEOTIDE, Taxon.getTaxon(taxon), sequence.getStates());
                sequences.set(i, newSeq);
            }
        }
    }

    /**
     * get sequence string from a list of sequences given the sequence label
     * @param sequenceLabel Label of query sequence
     * @param sequenceList Sequence list
     * @return amino acid sequence found from the list or null
     */
    public static String getSequenceStringFrom(String sequenceLabel, List<Sequence> sequenceList) {

        String seqName;

        for (Sequence sequence : sequenceList) {
            seqName = sequence.getTaxon().toString();
            if (seqName.contentEquals(sequenceLabel))
                return sequence.getString();
        }
        return null;

    }

    public static void splitFastaBy(File inFastaFile, String regex) throws IOException {
        String workPath = inFastaFile.getParent() + File.separator;
        String fileNameStem = inFastaFile.getName().substring(0, inFastaFile.getName().indexOf(""));

        File outFastaFile1 = new File(workPath + fileNameStem + "-1.fasta");
        PrintStream out1 = new PrintStream(new FileOutputStream(outFastaFile1));
        File outFastaFile2 = new File(workPath + fileNameStem + "-2.fasta");
        PrintStream out2 = new PrintStream(new FileOutputStream(outFastaFile2));

        BufferedReader reader = OTUsFileIO.getReader(inFastaFile, "the original");

        String line = reader.readLine();
        PrintStream out = out1;
        while (line != null) {

            if (line.startsWith(">")) {
                String label = line.substring(1);

                if (label.matches(regex)) {
                    out = out2;
                } else {
                    out = out1;
                }
            }

            out.println(line);
            line = reader.readLine();
        }

        reader.close();
        out1.flush();
        out1.close();
        out2.flush();
        out2.close();
    }

    // main
    public static void main(String[] args) throws IOException{
        if (args.length != 1) throw new IllegalArgumentException("Working path is missing in the argument !");

        String workPath = args[0];
        MyLogger.info("\nWorking path = " + workPath);

        File inFastaFile = new File(workPath + "NZ-insects-BOLD-2013-11-21-co1.fasta");
//        String regex = ".*\\|28S.*";
        String regex = ".*COI-3P.*";
        splitFastaBy(inFastaFile, regex);
    }
}
