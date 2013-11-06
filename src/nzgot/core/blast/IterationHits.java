//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.11.05 at 10:15:55 AM NZDT 
//


package nzgot.core.blast;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * modified
 * @author Walter Xie
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "hit"
})
@XmlRootElement(name = "Iteration_hits")
public class IterationHits {

    /**
     * only return the first top hit for each iteration
     */
    public void reduceToFirstTopHit() {
        if (hit == null) {
            hit = new ArrayList<Hit>();
        } else if (hit.size() > 1) {
            Hit first = hit.get(0);
            hit = new ArrayList<>();
            hit.add(first);
        }
    }

    @XmlElement(name = "Hit")
    protected List<Hit> hit;

    /**
     * Gets the value of the hit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Hit }
     * 
     * 
     */
    public List<Hit> getHit() {
        if (hit == null) {
            hit = new ArrayList<Hit>();
        }
        return this.hit;
    }

}