package nzgo.toolkit.core.community;

import jebl.evolution.sequences.Sequence;
import nzgo.toolkit.core.naming.SampleNameParser;
import nzgo.toolkit.core.util.ArrayUtil;

import java.util.Arrays;

/**
 * Alpha Diversity for samples given a OTU
 * @author Walter Xie
 */
public class AlphaDiversity {

    SampleNameParser sampleNameParser = new SampleNameParser();

    protected int[] alphaDiversity; // only valid for given sample array

    public AlphaDiversity(String samplesBy, String[] samples, OTU otu) {
        setAlphaDiversity(samplesBy, samples, otu);
    }

    public void setAlphaDiversity(String samplesBy, String[] samples, OTU otu) {
        if (samples == null || samples.length < 1)
            throw new IllegalArgumentException("Error: sample array was not initialized: " + samples);

        alphaDiversity = new int[samples.length];

        for (Object sequence: otu) {
            String label = "";
            if (sequence instanceof Sequence) {
               label = ((Sequence) sequence).getTaxon().getName();
            } else {
               label = sequence.toString();
            }

            String sampleLocation = sampleNameParser.getSampleBy(samplesBy, label);
            int i = ArrayUtil.indexOf(sampleLocation, samples);
            if (i < 0) {
                throw new IllegalArgumentException("Error: missing sample location : " + sampleLocation +
                        " from samples array : " + Arrays.asList(samples));
            } else {
                alphaDiversity[i]++;
            }
        }
    }

    public int[] getAlphaDiversity() {
        return alphaDiversity;
    }

}
